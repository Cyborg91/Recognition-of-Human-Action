package com.profileing;

//this class is used to split the video as frames

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.control.FramePositioningControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;

import view.AnomalyDetectionView;
import view.ShowProcess;
import view.TrainingView;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

class FrameFormation {

	File Monitoring_video;
	Player player;
	FramePositioningControl fPC;
	FrameGrabbingControl fGC;
	private int framePosition;
	private boolean b;
	public  FrameFormation(){
		
	}
	public FrameFormation(String url){
		framePosition = 0;
		try {
			//create the player for the given video url
			player = Manager.createRealizedPlayer(new MediaLocator(url));

			ShowProcess.println("url = "+ url +"\nplayer ="+ player);
			String framePositioningControl = "javax.media.control.FramePositioningControl"; 
			String frameGrabbingControl = "javax.media.control.FrameGrabbingControl"; 

			//create FramePositionControl object for get frame position using player object
			fPC = (FramePositioningControl) player.getControl(framePositioningControl);

			//create FrameGrabbingControl object for get the frames using player object
			fGC = (FrameGrabbingControl)player.getControl(frameGrabbingControl);

		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public Image[] convertToFrames(String url){
		int i=1;
		int seek = 0;
		Image img []  = null;//= new Image[0] ;
		ArrayList al =
			
			
			
			new ArrayList();
		
		try{
			Player player = Manager.createRealizedPlayer(new MediaLocator(url));
			ShowProcess.println("url = "+ url +"\nplayer ="+ player);
			String framePositioningControl = "javax.media.control.FramePositioningControl"; 
			String frameGrabbingControl = "javax.media.control.FrameGrabbingControl"; 
			FramePositioningControl fPC = (FramePositioningControl) player.getControl(framePositioningControl);
			FrameGrabbingControl fGC = (FrameGrabbingControl)player.getControl(frameGrabbingControl);
			ShowProcess.println("fpc"+fPC);
			ShowProcess.println("fGc"+fGC);
			while(i == (seek = fPC.seek(i))){
				//ShowProcess.println("i ="+i+" seek="+seek); 
				// Constructor of BuffetToImage accepts a videoFormate object and 
				// converts in to AWT Image objects
			//	ShowProcess.println(i);
				BufferToImage bToImg= new BufferToImage((VideoFormat)fGC.grabFrame().getFormat());
				Image image =  bToImg.createImage(fGC.grabFrame());
				Image image2 =  BufferedImageToImage(convert(image));
				FrameToImage(image2,"SplitedFrames\\VBPro"+i+".jpeg");
				b = al.add(image2);
				if(i++ == 200)break;
			}
			
//			img = (NamedImage[]) al.toArray();
			img = new Image[al.size()];
			//ShowProcess.println("al.size() = "+al.size());
			for(int j=0; j < al.size(); j++){
				img[j] = (Image) al.get(j);
			}
			ShowProcess.println("image length: "+img.length);
			}catch(Exception e){
				ShowProcess.println("Exception "+ e); 
			}
			System.gc();
	
			return img;
	}
	//return the true if the video has next frame
	public boolean hasNext(){
		framePosition++;
		return (framePosition == fPC.seek(framePosition));
	}
	//get frames
	public Image nextFrame(){
		// create the buffer based upon the format
		BufferToImage bToImg= new BufferToImage((VideoFormat)fGC.grabFrame().getFormat());

		//get the image
		Image image =  bToImg.createImage(fGC.grabFrame());

		//convert the grayscale image
		Image image2=  BufferedImageToImage(convert(image));

		FrameToImage(image2,"SplitedFrames\\VBPro"+framePosition+".jpeg");
		try{
		TrainingView.jTextFieldDetectedFrameName.setText("SplitedFrames\\VBPro"+framePosition+".jpeg");
		}
		catch (NullPointerException e) {
			// TODO: handle exception
		}
		try{
		AnomalyDetectionView.jTextFieldDetectedFrameName.setText("SplitedFrames\\VBPro"+framePosition+".jpeg");
		}
		catch (NullPointerException e) {
			// TODO: handle exception
		}
		return image2;
	}
	//covert bufferedImage to image
	private Image BufferedImageToImage(BufferedImage i){
		return Toolkit.getDefaultToolkit().createImage(i.getSource());
	}
	//convert image to bufferedImage
	public BufferedImage convert(Image im)
	{
		ImageIcon tempimageicon=new ImageIcon(im);
		//getwidth
		int width = tempimageicon.getIconWidth();//500;
		//getheight
		int height =tempimageicon.getIconHeight();// 500;
		//convert corlor image to grayscale bufferedimage
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
	    Graphics bg = bi.getGraphics();
	    bg.drawImage(im, 0, 0, null);
	    bg.dispose();
	    return bi;
	 }

	public String getMediaPath(){
		
		String videoName =  Messages.getString("videoName");
		String videoDir =  Messages.getString("videoDir");
		String dir = System.getProperty("user.dir"); 
		String path = dir+"\\"+videoDir+"\\"+videoName;
		FrameFormation ff = new FrameFormation();
		ShowProcess.println("path = "+path); 
		return "file:"+path;
	}
	//convert image as jpeg file
	private void FrameToImage(Image image,String path){
		ImageIcon tempimageicon=new ImageIcon(image);
		//getwidth
		int width = tempimageicon.getIconWidth();//500;
		//getheight
		int height =tempimageicon.getIconHeight();// 500;
		
		/*
		 * the following set of standard code  is to create the jpeg file for the image
		 */
		//create BufferedImage object with parameter of height and width
		BufferedImage bi = new BufferedImage(width,height,1);
		try{
			FileOutputStream f = new FileOutputStream(path);
			//create the graphics object
			Graphics g = bi.createGraphics();
			g.drawImage(image,0,0,null);
			g.dispose();
			//using encoder only we write the image object to file
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(f);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			//set the quality for the jpeg image
			param.setQuality((float)((double)75 / 100D), true);
			// create the jpeg file
			encoder.encode(bi, param);
			f.close();
		}catch(Exception e)
		{
			ShowProcess.println("Jpeg Writer"+ e);
		}

	}
	public int saveImageObj(Image[] image){
		int i=0;
		for(i=0; i < image.length; i++){
				//created image is stored in the following path 
				
				String path="SplitedFrames\\VBPro"+i+".jpeg";
				//create the ImageIcon for image to get width and height
				ImageIcon tempimageicon=new ImageIcon(image[i]);
				//getwidth
				int width = tempimageicon.getIconWidth();//500;
				//getheight
				int height =tempimageicon.getIconHeight();// 500;
				
				/*
				 * the following set of standard code  is to create the jpeg file for the image
				 */
				//create BufferedImage object with parameter of height and width
				BufferedImage bi = new BufferedImage(width,height,1);
				try{
					FileOutputStream f = new FileOutputStream(path);
					//create the graphics object
					Graphics g = bi.createGraphics();
					g.drawImage(image[i],0,0,null);
					g.dispose();
					//using encoder only we write the image object to file
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(f);
					JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
					//set the quality for the jpeg image
					param.setQuality((float)((double)75 / 100D), true);
					// create the jpeg file
					encoder.encode(bi, param);
					f.close();
				}catch(Exception e)
				{
					ShowProcess.println("Jpeg Writer"+ e);
				}
		}
		return i;
	}
	
	
	public static void main(String arg[]){
		
		FrameFormation ff = new FrameFormation();
		String url = ff.getMediaPath();
		ff.convertToFrames(url);
//		Image image[] = ff.convertToFrames(url);
//		int noImgSaved = ff.saveImageObj(image);
//		ShowProcess.print(noImgSaved+" Image files are saved");
	}
}
