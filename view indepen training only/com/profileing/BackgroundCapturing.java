package com.profileing;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import view.ShowProcess;

public class BackgroundCapturing {
	int height;
	int width;
	//provide pixel array for the given image
	public int[] getPixels(Image image){		
		int bk_ImgPixels[];

		//create the imageIcon for get the width,height
		ImageIcon ii = new ImageIcon(image,"");

		height = ii.getIconHeight();
		width = ii.getIconWidth();

		//create the pixel array
		bk_ImgPixels = new int[height*width];

		//create pixelgrabber  object
		PixelGrabber pixelGrabber =new PixelGrabber(image, 0, 0, width, height,bk_ImgPixels,0, width);
		//get the pixel array using this method
		try{
			pixelGrabber.grabPixels();
		}catch(InterruptedException e){
		}
		return bk_ImgPixels;
	}

	//provide the background image
	public Image readBackGround(){
		Image image;
		//		The Next line Reads the background file name form Property file
		// get image name from property file using Messages class
		// Messages class provide the value for your given key
		String imgName =Messages.getString("BackgroundCapturing.bgImage");

		//get the image directory
		String imgDir = Messages.getString("BackgroundCapturing.imgDir");

		//form the full path for the image
		String imgPath =System.getProperty("user.dir")+"\\"+imgDir+"\\"+imgName;
		ShowProcess.println("image path " + imgPath);

		//Toolkit class provide the image object for given path
		image = Toolkit.getDefaultToolkit().getImage(imgPath);
		return image;
	}
	public static void main(String args[]){
		
		BackgroundCapturing bg = new BackgroundCapturing();
		Image img = bg.readBackGround();
		int im []=bg.getPixels(img);
		for(int i=0; i < im.length; i++ ){
			
//		ShowProcess.println(i+" = "+im[i]);
			/*ShowProcess.println("Black = "+((255 << 24)|(0<<16)|(0<<8)|0) );
			ShowProcess.println("White = "+((255 << 24)|(255<<16)|(255<<8)|255) );
			ShowProcess.println("Black = "+((0<<16)|(0<<8)|0) );
			ShowProcess.println("White = "+((255<<16)|(255<<8)|255) );*/
		
		}
		
	}
}

