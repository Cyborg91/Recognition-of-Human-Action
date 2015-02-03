package anomaly.com.profileing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import view.DetectionShow;

import anomaly.view.AnomalyDetectionView;
import anomaly.view.ShowProcess1;
//import voice.Textspeak;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class AnomalyEventDetect implements Runnable {

	Event event [];
	int imgPixels[],i=0;
	int numEvents;
	Vector vector;
	int differentFrames;
	int totalFrames;
	String serialize_Path = Messages.getString("BackUpPath"); //$NON-NLS-1$
	DataSerialization dataSerialization;
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0, noOfPixelsSame,noOfPixelsDiffed,noOfBlack,noOfWhite;
	int yOfx1 = 0,yOfx2 =0, xOfy1 = 0, xOfy2 = 0;
	int indexOfx1,indexOfx2,indexOfy1,indexOfy2;
	int maxI, minI, maxDiff, minDiff;
	int width, height;
	int blackPixelVal = -16777216;
	int whitePixelVal = -1;
	BackgroundCapturing bg;
	int currentFramePixels[];	
	FrameFormation frameFormation = null;
	Image tmpImage = null;
	private String path = "";
	Thread thread = null;

public AnomalyEventDetect(){
	dataSerialization = new DataSerialization();
	bg = new BackgroundCapturing();
	thread = new Thread(this);
	vector = new Vector();
}

	public void eventCapturing(String path){
		path = "file:"+path;
		ShowProcess1.println(path);
		this.path = path;
		thread.start();
	}
	private void eventChecking(Event e){
		Event e1;
		boolean flag=false;
		for(int  i=0;i<vector.size();i++){
			e1 = (Event) vector.get(i);
			ShowProcess1.println(e1.getBlobName());
			AnomalyDetectionView.jTextFieldExistBlobName.setText(""+i);
			if(e1.equals(e)){
				flag = true;
				break;
			}
		}
		if(!flag){
//			new JOptionPane().showMessageDialog(null,"Different event");
//			DetectionShow.jContentPane.setBackground(java.awt.Color.red);
			AnomalyDetectionView.jPanelDetectionShow.setBackground(Color.red);
			DetectionShow.jContentPane.setBackground(java.awt.Color.red);
			if(AnomalyDetectionView.jPanelDetectionShow.getBackground()==Color.red)
			{
				
				i++;
				
				}
		}
			
			differentFrames++;
			String s=Integer.toString(i);
			AnomalyDetectionView.jTextFieldExistBlobName.setText(s);
			differentFrames++;
			
			//if(AnomalyDetectionView.jCheckBoxVoiceEffect.isSelected())
			//Textspeak.speakPlainText("anomaly is detected");
		
	}
	//check the square of the current pixel
	private boolean conPixelCheck(int current_frame_pixel[],int back_frame_pixel[],int pixelPosition,int imageWidth,int imageHeight){
		int CheckSquareWidth = 10;
		int diff=0;
		if((pixelPosition%imageWidth)>imageWidth/2 && (pixelPosition/imageWidth)>imageHeight/2){
			pixelPosition-=CheckSquareWidth;
			pixelPosition = pixelPosition-(imageWidth* CheckSquareWidth);
		}
		if((pixelPosition%imageWidth)>imageWidth/2){
			pixelPosition-=CheckSquareWidth;
		}
		else if((pixelPosition/imageWidth)>imageHeight/2){
			pixelPosition = pixelPosition-(imageWidth* CheckSquareWidth);
		}
			for(int m=pixelPosition,l=0;l<CheckSquareWidth;m+=imageWidth,l++){
				for(int n=0;n<CheckSquareWidth;n++){
///					ShowProcess.print((m+n) + " ");
					if((m+n)<(imageWidth*imageHeight))
						if(!check(current_frame_pixel[m+n],back_frame_pixel[m+n])){
							diff++;
						}
				}
//				ShowProcess.println();
			}
/*			ShowProcess.println();
			ShowProcess.println("#####################################");
*/
		if(diff<(CheckSquareWidth*CheckSquareWidth)/2){
			return true;
		}
		return false;
	}
	// check rgb value of the back ground image and current image 
	private boolean check(int current,int back){
		int r,g,b;
		int r1,g1,b1;
		r=0xff & (current>>16);
		g=0xff & (current>>8);
		b=0xff & current;
//		ShowProcess.println("current  r=" +r +"g = " +g +" b = " +b);

		r1=0xff & (back>>16);
		g1=0xff & (back>>8);
		b1=0xff & back;
//		ShowProcess.println("back  r1=" +r1 +"g1 = " +g1 +" b1 = " +b1);
		if(Math.abs(r-r1)<=25 && Math.abs(g-g1)<=25 && Math.abs(b-b1)<=25){
//		if(Math.abs(r-r1)<=20 && Math.abs(g-g1)<=20 && Math.abs(b-b1)<=20){
//		if(Math.abs(r-r1)<=10 && Math.abs(g-g1)<=10 && Math.abs(b-b1)<=10){
			return true;
		}
		return false;
	}
	private void createBlobFrame(Point start,Point end,int currentFrame[],int currentFrameNo,int width){
/*		start = new Point(0,0);
		end = new Point(127,95);*/

 		int imgheight = end.y-start.y+1;
		int imgwidth = end.x-start.x+1;
		ShowProcess1.println("start :"+ start); //$NON-NLS-1$
		ShowProcess1.println("end :"  + end); //$NON-NLS-1$
		ShowProcess1.println("width  :"+imgwidth + " height :" + imgheight); //$NON-NLS-1$ //$NON-NLS-2$
		FileOutputStream f = null;
		Graphics g;
		JPEGEncodeParam param;
		JPEGImageEncoder encoder;
		BufferedImage bi;
		bi = new BufferedImage(imgwidth,imgheight,1);
		g = bi.createGraphics();
		int createFrame[] = new int[imgheight*imgwidth];
		int i = 0;

		for(int p=start.y;p<=end.y;p++){
			for(int q=start.x;q<=end.x;q++){
				createFrame[i]=currentFrame[(p*width) + q];
//				ShowProcess.print(" "+q+ "," + p+ "=" +(p*width+q));
				i++;
			}
//			ShowProcess.println();
///			if(p==10)break;
		}
		ShowProcess1.println("######################################################"); //$NON-NLS-1$
//		ShowProcess.println();

		Image img1=new Applet().createImage(new MemoryImageSource(imgwidth,imgheight,createFrame,0,imgwidth));
		AnomalyDetectionView.setBlobImage(img1);
		//Image img1=new Applet().createImage(new MemoryImageSource(imgwidth,imgheight,curre(imgwidth,imgheight,currentFrame,0,imgwidth));Frame,o)
		try {
			f = new FileOutputStream("anomaly/blob-frames/"+currentFrameNo+".jpeg"); //"enc-frames/enc_start3.jpeg"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowProcess1.println("anomaly/blob-frames/"+currentFrameNo+".jpeg" + " created"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		g.drawImage(img1,0,0,null);
		g.dispose();
		encoder = JPEGCodec.createJPEGEncoder(f);
		param = encoder.getDefaultJPEGEncodeParam(bi);
//		param.setQuality((float)((double)75 / 100D), true);
		try {
			//create file for given images
			encoder.encode(bi, param);
			f.close();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		AnomalyEventDetect ed = new AnomalyEventDetect();
		ed.eventCapturing("");
	}

	public void run() {
		// TODO Auto-generated method stub
		frameFormation =new FrameFormation(path);
		
		Image image = bg.readBackGround();
		int bgPixels[] = bg.getPixels(image);// Getting background pixels
		width = bg.width;
		height = bg.height;
		ShowProcess1.println("width = " + width + "  height = " + height); //$NON-NLS-1$ //$NON-NLS-2$

		vector = (Vector) dataSerialization.dataDeSerialize(serialize_Path);
		
		ShowProcess1.println("length :"   + vector.size()); //$NON-NLS-1$
		ShowProcess1.println();
		for(int  i=0;i<vector.size();i++){
			Event e1 = (Event) vector.get(i);
			ShowProcess1.println(e1);
		}

		for(int i = 0; frameFormation.hasNext() ; i++){
			x1 = 0;	y1 = 0; x2 = 0; y2 = 0;
tmpImage = frameFormation.nextFrame();
			currentFramePixels  = bg.getPixels(tmpImage);
			noOfPixelsSame=0;
			noOfPixelsDiffed=0;
			noOfBlack = 0;
			noOfWhite = 0;
			boolean flag = true;
			
			for(int j = 0; j < bgPixels.length; j++){
//				ShowProcess.println(currentFramePixels[j]+" == " +bgPixels[j]);
//				if(currentFramePixels[j] == bgPixels[j]){
//				if(check(currentFramePixels[j],bgPixels[j])){
				if(conPixelCheck(currentFramePixels, bgPixels, j, width,height)){
					noOfPixelsSame++;
					//ShowProcess.println(j+"equal "+currentFramePixels[j]+" "+ bgPixels[j]);	
				}else{
					noOfPixelsDiffed++;
					if(currentFramePixels[j]==blackPixelVal)noOfBlack++;
					else if(currentFramePixels[j]== whitePixelVal)noOfWhite++;
//					if(j==0){
					if(flag){
						int r,g,b;
						ShowProcess1.println(currentFramePixels[j]+" == " +bgPixels[j]); //$NON-NLS-1$
						r=0xff & (currentFramePixels[j]>>16);
						g=0xff & (currentFramePixels[j]>>8);
						b=0xff & currentFramePixels[j];
						ShowProcess1.println("current  r=" +r +"g = " +g +" b = " +b); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

						r=0xff & (bgPixels[j]>>16);
						g=0xff & (bgPixels[j]>>8);
						b=0xff & bgPixels[j];
						ShowProcess1.println("back  r=" +r +"g = " +g +" b = " +b); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

						flag= false;

						x1 = j%width;
						yOfx1 = (int)(j/width);
						indexOfx1 = j;

						x2 = j%width;
						yOfx2 = (int)(j/width);
						indexOfx2 = j;

						y1 = (int)(j/width);
						xOfy1 = j%width;
						indexOfy1 = j;

						y2 = (int)(j/width);
						xOfy2 = j%width;
						indexOfy2 = j;

						/*						ShowProcess.print(j + "In Frame "+i +" A("+x1+","+yOfx1+")"+" B("+x2+","+yOfx2+")" );
						ShowProcess.println(" C("+y1+","+xOfy1+")"+" D("+y2+","+xOfy2+")" );
						ShowProcess.println();
						 */					}
					if(x1 > j%width){
						x1 = j%width;
						yOfx1 =(int)(j/width);
						indexOfx1 = j;
					}
					if(x2 < j%width){
						x2 = j%width;
						yOfx2 = (int)(j/width);
						indexOfx2 = j;
					}
					if(y1 > (int)j/width){
						y1 = (int)j/width;
						xOfy1 = j%width;
						indexOfy1 = j;
					}
					if(y2 < (int)j/width){
						y2 = (int)j/width;
						xOfy2 = j%width;
						indexOfy2 = j;
					}
					//ShowProcess.println(j+" NOT equal "+currentFramePixels[j]+" "+ bgPixels[j]);	
				}//end of else
			}// end of j loop
			/*ts.add(noOfPixelsDiffed);
			 ShowProcess.println("In frame "+i+",  "+noOfPixelsSame+" Pixels are same with BGImage"  );
			 ShowProcess.println("In frame "+ i +",  "+noOfPixelsDiffed+" Pixels are Differed with BGImage " );
			 ShowProcess.println();*/	
			System.gc();

			/*
			 ShowProcess.println("In Frame "+i +" x1 ="+x1);
			 ShowProcess.println("In Frame "+i +" yOfx1 ="+yOfx1);
			 ShowProcess.println("In Frame "+i +" x2 ="+x2);
			 ShowProcess.println("In Frame "+i +" yOfx2 ="+yOfx2);

			 ShowProcess.println("In Frame "+i +" y1 ="+y1);
			 ShowProcess.println("In Frame "+i +" xOfy1 ="+xOfy1);
			 ShowProcess.println("In Frame "+i +" y2 ="+y2);
			 ShowProcess.println("In Frame "+i +" xOfy2 ="+xOfy2);*/

//			ShowProcess.print("In Frame "+i +" A("+x1+","+yOfx1+")"+" B("+x2+","+yOfx2+")" );
//			ShowProcess.println(" C("+y1+","+xOfy1+")"+" D("+y2+","+xOfy2+")" );
			ShowProcess1.println("Frame of no "+i); //$NON-NLS-1$
			ShowProcess1.println("Blob : begin (" + x1 +"," + y1 +") end (" + x2 +"," + y2 +")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			ShowProcess1.println("White different :" + noOfWhite); //$NON-NLS-1$
			ShowProcess1.println("Black different :" + noOfBlack); //$NON-NLS-1$
			ShowProcess1.println("Different Pixels :" + noOfPixelsDiffed); //$NON-NLS-1$
			ShowProcess1.println();
			createBlobFrame(new Point(x1,y1), new Point(x2,y2), currentFramePixels,	i,width);
			Event event1 = new Event("anomaly/blob-frames/"+i+".jpeg",new Point(x1,y1), new Point(x2,y2),height,width, noOfWhite, noOfBlack,noOfPixelsDiffed);
			eventChecking(event1);
			AnomalyDetectionView.setSplitImage(tmpImage);
			/*if(i==60)
	break;	*/		
			totalFrames = i;
		}//end of i loop
		/*ShowProcess.println("First = "+ts.first());
		 ShowProcess.println("Last = "+ts.last());*/
		ShowProcess1.println("no_of_frames :" + totalFrames);
		ShowProcess1.println("different  frames : "+differentFrames);
		AnomalyDetectionView.jButtonStartDetect.setEnabled(true);
		//if(AnomalyDetectionView.jCheckBoxVoiceEffect.isSelected()){
			//Textspeak.speakPlainText("Your given work is finished Your given work is finished Your given work is finished");
		}
		
	}

