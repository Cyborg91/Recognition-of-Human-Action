/**
 * 
 */
package view;

//import anomaly.view.*;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.profileing.TrainEventDetection;
import anomaly.com.profileing.TrainEventDetection1;

/**
 * @author Owner
 *
 */
public class TrainingView extends JInternalFrame implements InternalFrameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6278670260171608692L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	//private JLabel name = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	public static JButton jButtonStartDetect = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private static JPanel jPanelImage = null;
	private JCheckBox jCheckBoxTrainAppend = null;
	private TrainEventDetection eventDetection = null;
	private String filename = null;
	private static JLabel jLabelsplitimage = null;
	public static JPanel jPanelDetectionShow = null;
	private static JLabel jLabelBlobImage = null;
	private JLabel jLabel3 = null;
	public static JTextField jTextFieldDetectedFrameName = null;
	private static JPanel topview = null;
	
	//public static JCheckBox jCheckBoxVoiceEffect = null;
	//TOP VIEW DECLARATION
	
	//private JPanel jContentPane1 = null;
	private JLabel jLabel4 = null;
	//private JLabel name = null;
	private JTextField jTextField1 = null;
	private JButton jButton1 = null;
	public static JButton jButtonStartDetect1 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel21 = null;
	private static JPanel jPanelImage1 = null;
	private JCheckBox jCheckBoxTrainAppend1 = null;
	private TrainEventDetection1 eventDetection1 = null;
	private String filename1 = null;
	private static JLabel jLabelsplitimage1 = null;
	public static JPanel jPanelDetectionShow1 = null;
	private static JLabel jLabelBlobImage1 = null;
	private JLabel jLabel31 = null;
	public static JTextField jTextFieldDetectedFrameName1 = null;
	/**
	 * This is the default constructor
	 */
	
	public TrainingView() {
		super();
		initialize();
		setColor();
		setColor1();
		//this.addInternalFrameListener(this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1000, 721);
		this.setTitle("Training View Window");
		this.setResizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		
		this.setVisible(true);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			//this.add(topview());
			jLabel3 = new JLabel();
			jLabel3.setBounds(new java.awt.Rectangle(18,330,141,22));
			jLabel3.setText("Detected Frame Name :");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new java.awt.Rectangle(360,47,91,16));
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setText("Blob Frame");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new java.awt.Rectangle(72,47,116,16));
			jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel10.setText("Splitted Frame");
						
			//name.setBounds(new java.awt.Rectangle(1,5,56,16));
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(9,14,56,16));
			jLabel.setText("Source :");
			//top view
			jLabel31 = new JLabel();
			jLabel31.setBounds(new java.awt.Rectangle(18,680,142,23));
			jLabel31.setText("Detected Frame Name :");
			jLabel21 = new JLabel();
			jLabel21.setBounds(new java.awt.Rectangle(360,397,91,16));
			jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel21.setText("Blob Frame");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new java.awt.Rectangle(72,398,116,16));
			jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel11.setText("Splitted Frame");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(9,364,56,26));
			jLabel1.setText("Source :");
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			//jContentPane.add(name, null);
			
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButtonStartDetect(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(getJPanelImage(), null);
			jContentPane.add(getJPanelImage1(), null);
			jContentPane.add(getJCheckBoxTrainAppend(), null);
			jContentPane.add(getJPanelDetectionShow(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJTextFieldDetectedFrameName(), null);
			
			//jContentPane.add(getJCheckBoxVoiceEffect(), null);
			
			
			
			//jContentPane.add(jLabel4, null);
			//jContentPane.add(name, null);
			
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(getJButton1(), null);
			//jContentPane.add(getJButtonStartDetect1(), null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(jLabel21, null);
			//jContentPane.add(getJPanelImage1(), null);
			jContentPane.add(getJCheckBoxTrainAppend1(), null);
			jContentPane.add(getJPanelDetectionShow1(), null);
			jContentPane.add(jLabel31, null);
			jContentPane.add(getJTextFieldDetectedFrameName1(), null);//*/
		}
		return jContentPane;
	}
	/*private JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			jLabel31 = new JLabel();
			jLabel31.setBounds(new java.awt.Rectangle(18,330,141,22));
			jLabel31.setText("Detected Frame Name :");
			jLabel21 = new JLabel();
			jLabel21.setBounds(new java.awt.Rectangle(360,47,91,16));
			jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel21.setText("Blob Frame");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new java.awt.Rectangle(72,48,116,16));
			jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel11.setText("Splitted Frame");
			//name.setText("FRONT VIEW");
			//
			
			
			
			//name.setBounds(new java.awt.Rectangle(1,5,56,16));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(9,14,56,16));
			jLabel1.setText("Source :");
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(null);
			jContentPane1.add(jLabel4, null);
			//jContentPane.add(name, null);
			
			jContentPane1.add(getJTextField1(), null);
			jContentPane1.add(getJButton1(), null);
			jContentPane1.add(getJButtonStartDetect1(), null);
			jContentPane1.add(jLabel11, null);
			jContentPane1.add(jLabel21, null);
			jContentPane1.add(getJPanelImage1(), null);
			jContentPane1.add(getJCheckBoxTrainAppend1(), null);
			jContentPane1.add(getJPanelDetectionShow1(), null);
			jContentPane1.add(jLabel31, null);
			jContentPane1.add(getJTextFieldDetectedFrameName1(), null);
			//jContentPane.add(getJCheckBoxVoiceEffect(), null);
		}
		return jContentPane;
	}*/
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new java.awt.Rectangle(77,14,465,19));
		}
		return jTextField;
	}
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new java.awt.Rectangle(77,364,465,19));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new java.awt.Rectangle(570,13,89,19));
			jButton.setText("Browse");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openFile();
				}
			});
		}
		return jButton;
	}
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new java.awt.Rectangle(570,375,89,19));
			jButton1.setText("Browse");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openFile1();
				}
			});
		}
		return jButton1;
	}
	@SuppressWarnings("static-access")
	private void openFile(){
		FileDialog openDialog = new FileDialog(new JFrame(),"Open File",FileDialog.LOAD);
		openDialog.setFilenameFilter(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.endsWith("avi"))return true;
				return false;
			}
		
		});
		openDialog.show();
		if(openDialog.getDirectory()!= null && openDialog.getFile() != null){
		filename = openDialog.getDirectory()+openDialog.getFile();
		if(!filename.endsWith("avi")){
			new JOptionPane().showMessageDialog(null,"The File name is incorrect.");
		}
		else{
			jTextField.setText(filename);
		}
		}
		else{
			new JOptionPane().showMessageDialog(null,"The File name is incorrect.");
		}
		
		
	}
	
	@SuppressWarnings({"static-access","static-access", "deprecation"})
	private void openFile1(){
		FileDialog openDialog = new FileDialog(new JFrame(),"Open File",FileDialog.LOAD);
		openDialog.setFilenameFilter(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.endsWith("avi"))return true;
				return false;
			}
		
		});
		openDialog.show();
		if(openDialog.getDirectory()!= null && openDialog.getFile() != null){
		filename = openDialog.getDirectory()+openDialog.getFile();
		if(!filename.endsWith("avi")){
			new JOptionPane().showMessageDialog(null,"The File name is incorrect.");
		}
		else{
			jTextField1.setText(filename);
		}
		}
		else{
			new JOptionPane().showMessageDialog(null,"The File name is incorrect.");
		}
		
		
	}

	/**
	 * This method initializes jButtonStartDetect	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonStartDetect() {
		if (jButtonStartDetect == null) {
			jButtonStartDetect = new JButton();
			jButtonStartDetect.setBounds(new java.awt.Rectangle(694,373,140,23));
			jButtonStartDetect.setText("Start Training");
			jButtonStartDetect.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					filename = jTextField.getText();
					filename1=jTextField1.getText();
					if(filename == null || filename.equals("") || !filename.endsWith("avi")){
						new JOptionPane().showMessageDialog(null,"Front view The File name is incorrect.");
					}
					else if(filename1 == null || filename1.equals("") || !filename1.endsWith("avi")){
						new JOptionPane().showMessageDialog(null,"Top view The File name is incorrect.");
					}
					else{
						eventDetection = new TrainEventDetection();
						eventDetection.eventCapturing(filename,jCheckBoxTrainAppend.isSelected());
						TrainingView.jButtonStartDetect.setEnabled(false);
						eventDetection1 = new TrainEventDetection1();
						eventDetection1.eventCapturing1(filename1,jCheckBoxTrainAppend1.isSelected());
						//TrainingView.jButtonStartDetect1.setEnabled(false);
					}
				}
			});
		}
		return jButtonStartDetect;
	}
	/*private JButton getJButtonStartDetect1() {
		if (jButtonStartDetect1 == null) {
			jButtonStartDetect1 = new JButton();
			jButtonStartDetect1.setBounds(new java.awt.Rectangle(694,463,140,23));
			jButtonStartDetect1.setText("Start Training");
			jButtonStartDetect1.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					filename = jTextField1.getText();
					if(filename == null || filename.equals("") || !filename.endsWith("avi")){
						new JOptionPane().showMessageDialog(null,"The File name is incorrect.");
					}
					else{
						eventDetection1 = new TrainEventDetection1();
						eventDetection1.eventCapturing1(filename,jCheckBoxTrainAppend1.isSelected());
						TrainingView.jButtonStartDetect1.setEnabled(false);
					}
				}
			});
		}
		return jButtonStartDetect1;
	}

	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelImage() {
		if (jPanelImage == null) {
			jLabelBlobImage = new JLabel();
			//jLabelBlobImage.setBounds(new java.awt.Rectangle(317,3,312,293));
			jLabelBlobImage.setBounds(new java.awt.Rectangle(317,3,200,200));
			jLabelBlobImage.setText("");
			jLabelsplitimage = new JLabel();
			jLabelsplitimage.setText("");
			//jLabelsplitimage.setBounds(new java.awt.Rectangle(5,2,309,297));
			jLabelsplitimage.setBounds(new java.awt.Rectangle(5,2,300,200));
			jPanelImage = new JPanel();
			jPanelImage.setLayout(null);
			jPanelImage.setBounds(new java.awt.Rectangle(9,62,642,307));
			jPanelImage.add(jLabelsplitimage, null);
			jPanelImage.add(jLabelBlobImage, null);
		}
		return jPanelImage;
	}
	private JPanel getJPanelImage1() {
		if (jPanelImage1 == null) {
			jLabelBlobImage1 = new JLabel();
			//jLabelBlobImage.setBounds(new java.awt.Rectangle(317,3,312,293));
			jLabelBlobImage1.setBounds(new java.awt.Rectangle(317,3,200,200));
			jLabelBlobImage1.setText("");
			jLabelsplitimage1 = new JLabel();
			jLabelsplitimage1.setText("");
			//jLabelsplitimage.setBounds(new java.awt.Rectangle(5,2,309,297));
			jLabelsplitimage1.setBounds(new java.awt.Rectangle(5,2,300,200));
			jPanelImage1 = new JPanel();
			jPanelImage1.setLayout(null);
			jPanelImage1.setBounds(new java.awt.Rectangle(9,462,642,307));
			jPanelImage1.add(jLabelsplitimage1, null);
			jPanelImage1.add(jLabelBlobImage1, null);
		}
		return jPanelImage1;
	}
public static void setSplitImage(Image image){
	
	ImageIcon imageIcon = new ImageIcon(image);
	jLabelsplitimage.setIcon(imageIcon);
}
	public static void setBlobImage(Image image){
		jLabelBlobImage.setIcon(new ImageIcon(image));
	}
	
	public static void setSplitImage1(Image image){
		
		ImageIcon imageIcon = new ImageIcon(image);
		jLabelsplitimage1.setIcon(imageIcon);
	}
		public static void setBlobImage1(Image image){
			jLabelBlobImage1.setIcon(new ImageIcon(image));
		}
	/**
	 * This method initializes jCheckBoxTrainAppend	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxTrainAppend() {
		if (jCheckBoxTrainAppend == null) {
			jCheckBoxTrainAppend = new JCheckBox();
			jCheckBoxTrainAppend.setBounds(new java.awt.Rectangle(695,35,157,26));
			jCheckBoxTrainAppend.setSelected(true);
			jCheckBoxTrainAppend.setText("Append Training Event");
		}
		return jCheckBoxTrainAppend;
	}
	private JCheckBox getJCheckBoxTrainAppend1() {
		if (jCheckBoxTrainAppend1 == null) {
			jCheckBoxTrainAppend1 = new JCheckBox();
			jCheckBoxTrainAppend1.setBounds(new java.awt.Rectangle(695,400,157,26));
			jCheckBoxTrainAppend1.setSelected(true);
			jCheckBoxTrainAppend1.setText("Append Training Event");
		}
		return jCheckBoxTrainAppend1;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDetectionShow() {
		if (jPanelDetectionShow == null) {
			jPanelDetectionShow = new JPanel();
			jPanelDetectionShow.setBounds(new java.awt.Rectangle(685,153,182,148));
		}
		return jPanelDetectionShow;
	}
	private JPanel getJPanelDetectionShow1() {
		if (jPanelDetectionShow1 == null) {
			jPanelDetectionShow1= new JPanel();
			jPanelDetectionShow1.setBounds(new java.awt.Rectangle(685,493,182,148));
		}
		return jPanelDetectionShow1;
	}
	
	@SuppressWarnings("unused")
	private JPanel topview() {
		if (topview == null) {
			topview = new JPanel();
			topview .setBounds(new java.awt.Rectangle(18,380,582,548));
		}
		return topview ;
	}
	private void setColor(){
		new Thread() {
		
			//@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
				jPanelDetectionShow.setBackground(Color.GREEN);
				//jPanelDetectionShow1.setBackground(Color.GREEN);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		
		}.start();
	}
private void setColor1(){
		new Thread() {
		
			//@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
				jPanelDetectionShow1.setBackground(Color.GREEN);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		
		}.start();
	}
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		ShowProcess.jMenuItemTraining.setEnabled(true);
	}

	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes jTextFieldDetectedFrameName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldDetectedFrameName() {
		if (jTextFieldDetectedFrameName == null) {
			jTextFieldDetectedFrameName = new JTextField();
			jTextFieldDetectedFrameName.setBounds(new java.awt.Rectangle(170,300,381,20));
		}
		return jTextFieldDetectedFrameName;
	}
	private JTextField getJTextFieldDetectedFrameName1() {
		if (jTextFieldDetectedFrameName1 == null) {
			jTextFieldDetectedFrameName1 = new JTextField();
			jTextFieldDetectedFrameName1.setBounds(new java.awt.Rectangle(170,640,381,20));
		}
		return jTextFieldDetectedFrameName1;
	}

	/**
	 * This method initializes jCheckBoxVoiceEffect	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	/*private JCheckBox getJCheckBoxVoiceEffect() {
		if (jCheckBoxVoiceEffect == null) {
			jCheckBoxVoiceEffect = new JCheckBox();
			jCheckBoxVoiceEffect.setBounds(new java.awt.Rectangle(701,90,153,21));
			jCheckBoxVoiceEffect.setText("Voice Effect");
		}
		return jCheckBoxVoiceEffect;
	}
  //  @jve:decl-index=0:visual-constraint="10,10"*/
}
