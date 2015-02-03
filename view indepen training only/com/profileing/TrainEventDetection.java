package com.profileing;
//check the event if it is new then added into vector

// note: the command is available in anomlyeventdetection

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.AnomalyDetectionView;
import view.ShowProcess;
import view.TrainingView;



import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class TrainEventDetection implements Runnable{
	
	Event event [];
	int imgPixels[];
	int numEvents;
	Vector vector;
	// to store the vector object which have event object in file format
	String serialize_Path = Messages.getString("BackUpPath"); //$NON-NLS-1$
	//to convert object to file
	DataSerialization dataSerialization;
	//get background image
	BackgroundCapturing bg;
	//store the no of different frames
	private int differentFrames;
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0, noOfPixelsSame,noOfPixelsDiffed,noOfBlack,noOfWhite;
	int yOfx1 = 0,yOfx2 =0, xOfy1 = 0, xOfy2 = 0;
	int indexOfx1,indexOfx2,indexOfy1,indexOfy2;
	int maxI, minI, maxDiff, minDiff;
	int width, height;
	int blackPixelVal = -16777216;
	int whitePixelVal = -1;
	int currentFramePixels[];	
	Image tmpImage = null;
	Thread thread = null;
	private String path = "";
	private boolean append;
//constructor
	public TrainEventDetection(){
		//serialize the object using dataserilization class
		dataSerialization = new DataSerialization();
		//get bcakground image
		bg = new BackgroundCapturing();
		//store the event object in vector
		vector = new Vector();
		//to start  multiprocess
		thread = new Thread(this);
	}
	public void run(){
		//append the "file:" before the path for split as frames

		path="file:"+path;

		//get background image from backgroundcapturing class
		Image image = bg.readBackGround();

		//get the array pixel using getpixel method for given image as argument
		int bgPixels[] = bg.getPixels(image);// Getting background pixels

		//get the background image width, height
		width = bg.width;
		height = bg.height;

		ShowProcess.println("width = " + width + "  height = " + height); //$NON-NLS-1$ //$NON-NLS-2$
		try{
			//if append is true the vector get the past events also
			if(append){
				vector = (Vector) dataSerialization.dataDeSerialize(serialize_Path);
			}
			ShowProcess.println();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//create the  frameformation object for given video url
		FrameFormation frameFormation =new FrameFormation(path);

		//get the each frame using frameformation object using the following method hasNext(),nextFrame()
		for(int i = 0; frameFormation.hasNext() ; i++){
			x1 = 0;	y1 = 0; x2 = 0; y2 = 0;
			//get the each frame
			tmpImage = frameFormation.nextFrame();
			//get the pixel array for the tmpimage
			currentFramePixels  = bg.getPixels(tmpImage);
			noOfPixelsSame=0;
			noOfPixelsDiffed=0;
			noOfBlack = 0;
			noOfWhite = 0;
			boolean flag = true;
			//get each pixel to compare			
			for(int j = 0; j < bgPixels.length; j++){
				//compare the pixel
				if(conPixelCheck(currentFramePixels, bgPixels, j, width,height)){
					//if it is equal then increase the samepixel variable
					noOfPixelsSame++;
					//ShowProcess.println(j+"equal "+currentFramePixels[j]+" "+ bgPixels[j]);	
				}else{
					//if it is differenced then find no of changed black pixel and while pixel
					noOfPixelsDiffed++;
					if(currentFramePixels[j]==blackPixelVal)noOfBlack++;
					else if(currentFramePixels[j]== whitePixelVal)noOfWhite++;

					//get (x1,y1),(x2,y2) for the blob frame
					if(flag){
						int r,g,b;
						ShowProcess.println(currentFramePixels[j]+" == " +bgPixels[j]); //$NON-NLS-1$
						r=0xff & (currentFramePixels[j]>>16);
						g=0xff & (currentFramePixels[j]>>8);
						b=0xff & currentFramePixels[j];
						ShowProcess.println("current  r=" +r +"g = " +g +" b = " +b); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						
						r=0xff & (bgPixels[j]>>16);
						g=0xff & (bgPixels[j]>>8);
						b=0xff & bgPixels[j];
						ShowProcess.println("back  r=" +r +"g = " +g +" b = " +b); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						
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
						 */					
					}
					//get least left postion
					if(x1 > j%width){
						x1 = j%width;
						yOfx1 =(int)(j/width);
						indexOfx1 = j;
					}
					// get last right position
					if(x2 < j%width){
						x2 = j%width;
						yOfx2 = (int)(j/width);
						indexOfx2 = j;
					}
					//get the top position
					if(y1 > (int)j/width){
						y1 = (int)j/width;
						xOfy1 = j%width;
						indexOfy1 = j;
					}
					//get the bottom postion
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
			ShowProcess.println("Frame of no "+i); //$NON-NLS-1$
			ShowProcess.println("Blob : begin (" + x1 +"," + y1 +") end (" + x2 +"," + y2 +")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			ShowProcess.println("White different :" + noOfWhite); //$NON-NLS-1$
			ShowProcess.println("Black different :" + noOfBlack); //$NON-NLS-1$
			ShowProcess.println("Different Pixels :" + noOfPixelsDiffed); //$NON-NLS-1$
			ShowProcess.println();
			//create the blobframes using the found (x1,y1),(x2,y2) position
			createBolbFrame(new Point(x1,y1), new Point(x2,y2), currentFramePixels,	i,width);
			//create the event
			Event event1 = new Event("blob-frames/"+i+".jpeg",new Point(x1,y1), new Point(x2,y2),height, width, noOfWhite, noOfBlack,noOfPixelsDiffed);
			//check the new event1
			eventChecking(event1);
			
			/*if(i==60)
			 break;	*/		
				TrainingView.setSplitImage(tmpImage);
		}//end of i loop
		ShowProcess.println("length :"   + vector.size()); //$NON-NLS-1$
		ShowProcess.println("different frames :" +differentFrames);
		ShowProcess.println();
		//finally serialize the vector object in file
		dataSerialization.dataSerialize(vector,serialize_Path);
		for(int  i=0;i<vector.size();i++){
			Event e1 = (Event) vector.get(i);
			ShowProcess.println(e1);
		}
		/*ShowProcess.println("First = "+ts.first());
		 ShowProcess.println("Last = "+ts.last());*/
//		thread.stop();
		// enable the startdetect button
		JOptionPane.showMessageDialog(null,"Front view Trainig Finished");
		TrainingView.jButtonStartDetect.setEnabled(true);
		//if(TrainingView.jCheckBoxVoiceEffect.isSelected()){
			//Textspeak.speakPlainText("Your given work is finished Your given work is finished Your given work is finished");
		

	}
/*	private void ThreadStop(){
		try{
		thread = new Thread(this);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
*/

	// this method is called from the main program
	public void eventCapturing(String path,boolean append){
		// assign input video path
		this.path = path;
		/*
		*appent is the boolean variable if it is true then the past events are consider for the traning phase
		* or otherwise the events only consider for training phase.
		*/
		this.append = append;
		//start the thread it will call the run method
		thread.start();
	}
	private void eventChecking(Event e){
		Event e1;
		boolean flag=false;
		//check the current event with past events
		for(int  i=0;i<vector.size();i++){
			e1 = (Event) vector.get(i);
			if(e1.equals(e)){
				flag = true;
				break;
			}
		}
		//the current event is not avail in vector then added into vector
		if(!flag){
			vector.add(e);
			differentFrames++;
			TrainingView.jPanelDetectionShow.setBackground(java.awt.Color.red);
		/*	if(TrainingView.jCheckBoxVoiceEffect.isSelected())
				Textspeak.speakPlainText("A new Event is added in Buffer");*/
		}
	}

	//check the square of the current pixel
	private boolean conPixelCheck(int current_frame_pixel[],int back_frame_pixel[],int pixelPosition,int imageWidth,int imageHeight){
		//if the pixel is not same then check with hunderd neighbour pixels 
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
//				/					ShowProcess.print((m+n) + " ");
				if((m+n)<(imageWidth*imageHeight))
					if(!check(current_frame_pixel[m+n],back_frame_pixel[m+n])){
						diff++;
					}
			}
//			ShowProcess.println();
		}
		/*			ShowProcess.println();
		 ShowProcess.println("#####################################");
		 */
		
		//the changed pixels is greater than 50% the the pixel is confirmed different pixel
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
		
		r1=0xff & (back>>16);
		g1=0xff & (back>>8);
		b1=0xff & back;
		//check the both pixel same or not
		if(Math.abs(r-r1)<=25 && Math.abs(g-g1)<=25 && Math.abs(b-b1)<=25){
			return true;
		}
		return false;
	}

	private void createBolbFrame(Point start,Point end,int currentFrame[],int currentFrameNo,int width){
		/*		start = new Point(0,0);
		 end = new Point(127,95);*/
		
		int imgheight = end.y-start.y+1;
		int imgwidth = end.x-start.x+1;
		ShowProcess.println("start :"+ start); //$NON-NLS-1$
		ShowProcess.println("end :"  + end); //$NON-NLS-1$
		ShowProcess.println("width  :"+imgwidth + " height :" + imgheight); //$NON-NLS-1$ //$NON-NLS-2$
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
//			/			if(p==10)break;
		}
		ShowProcess.println("######################################################"); //$NON-NLS-1$
//		ShowProcess.println();
		
		Image img1=new Applet().createImage(new MemoryImageSource(imgwidth,imgheight,createFrame,0,imgwidth));
		TrainingView.setBlobImage(img1);
		//Image img1=new Applet().createImage(new MemoryImageSource(imgwidth,imgheight,curre(imgwidth,imgheight,currentFrame,0,imgwidth));Frame,o)
		try {
			f = new FileOutputStream("blob-frames/"+currentFrameNo+".jpeg"); //"enc-frames/enc_start3.jpeg"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowProcess.println("blob-frames/"+currentFrameNo+".jpeg" + " created"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		TrainEventDetection ed = new TrainEventDetection();
		ed.eventCapturing("E:/anomaly detect/Video/VASU.avi",true);
	}
}
