/**
 * 
 */
package anomaly.view;

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

import anomaly.com.profileing.TrainEventDetection1;

/**
 * @author Owner
 *
 */
public class TrainingView extends JInternalFrame implements InternalFrameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	public static JButton jButtonStartDetect = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private static JPanel jPanelImage = null;
	private JCheckBox jCheckBoxTrainAppend = null;
	private TrainEventDetection1 eventDetection = null;
	private String filename = null;
	private static JLabel jLabelsplitimage = null;
	public static JPanel jPanelDetectionShow = null;
	private static JLabel jLabelBlobImage = null;
	private JLabel jLabel3 = null;
	public static JTextField jTextFieldDetectedFrameName = null;
	public static JCheckBox jCheckBoxVoiceEffect = null;
	/**
	 * This is the default constructor
	 */
	public TrainingView() {
		super();
		initialize();
		setColor();
		this.addInternalFrameListener(this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(904, 441);
		this.setTitle("Training  Window");
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
			jLabel3 = new JLabel();
			jLabel3.setBounds(new java.awt.Rectangle(18,380,141,22));
			jLabel3.setText("Detected Frame Name :");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new java.awt.Rectangle(360,47,91,16));
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setText("Blob Frame");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(72,48,116,16));
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1.setText("Splitted Frame");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(9,14,56,16));
			jLabel.setText("Source :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButtonStartDetect(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJPanelImage(), null);
			jContentPane.add(getJCheckBoxTrainAppend(), null);
			jContentPane.add(getJPanelDetectionShow(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJTextFieldDetectedFrameName(), null);
			jContentPane.add(getJCheckBoxVoiceEffect(), null);
		}
		return jContentPane;
	}

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
	@SuppressWarnings("deprecation")
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
			JOptionPane.showMessageDialog(null,"The File name is incorrect.");
		}
		else{
			jTextField.setText(filename);
		}
		}
		else{
			JOptionPane.showMessageDialog(null,"The File name is incorrect.");
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
			jButtonStartDetect.setBounds(new java.awt.Rectangle(694,173,140,23));
			jButtonStartDetect.setText("Start Training");
			jButtonStartDetect.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					filename = jTextField.getText();
					if(filename == null || filename.equals("") || !filename.endsWith("avi")){
						JOptionPane.showMessageDialog(null,"The File name is incorrect.");
					}
					else{
						eventDetection = new TrainEventDetection1();
						eventDetection.eventCapturing1(filename,jCheckBoxTrainAppend.isSelected());
						TrainingView.jButtonStartDetect.setEnabled(false);
					}
				}
			});
		}
		return jButtonStartDetect;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelImage() {
		if (jPanelImage == null) {
			jLabelBlobImage = new JLabel();
			jLabelBlobImage.setBounds(new java.awt.Rectangle(317,3,312,293));
			jLabelBlobImage.setText("");
			jLabelsplitimage = new JLabel();
			jLabelsplitimage.setText("");
			jLabelsplitimage.setBounds(new java.awt.Rectangle(5,2,309,297));
			jPanelImage = new JPanel();
			jPanelImage.setLayout(null);
			jPanelImage.setBounds(new java.awt.Rectangle(9,62,642,307));
			jPanelImage.add(jLabelsplitimage, null);
			jPanelImage.add(jLabelBlobImage, null);
		}
		return jPanelImage;
	}
public static void setSplitImage(Image image){
	
	ImageIcon imageIcon = new ImageIcon(image);
	jLabelsplitimage.setIcon(imageIcon);
}
	public static void setBlobImage(Image image){
		jLabelBlobImage.setIcon(new ImageIcon(image));
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

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDetectionShow() {
		if (jPanelDetectionShow == null) {
			jPanelDetectionShow = new JPanel();
			jPanelDetectionShow.setBounds(new java.awt.Rectangle(685,223,182,148));
		}
		return jPanelDetectionShow;
	}
	private void setColor(){
		new Thread() {
		
			//@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
				jPanelDetectionShow.setBackground(Color.GREEN);
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
		ShowProcess1.jMenuItemTraining.setEnabled(true);
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
			jTextFieldDetectedFrameName.setBounds(new java.awt.Rectangle(170,379,481,20));
		}
		return jTextFieldDetectedFrameName;
	}

	/**
	 * This method initializes jCheckBoxVoiceEffect	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxVoiceEffect() {
		if (jCheckBoxVoiceEffect == null) {
			jCheckBoxVoiceEffect = new JCheckBox();
			jCheckBoxVoiceEffect.setBounds(new java.awt.Rectangle(701,90,153,21));
			jCheckBoxVoiceEffect.setText("Voice Effect");
		}
		return jCheckBoxVoiceEffect;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
