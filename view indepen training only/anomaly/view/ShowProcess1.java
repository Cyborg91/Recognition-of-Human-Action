package anomaly.view;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowProcess1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4809908105416191301L;
	private JPanel jContentPane = null;
	private static JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
	private TrainingView trainingView = null;
	private AnomalyDetectionView anomalyDetectionView = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	public static JMenuItem jMenuItemTraining = null;
	public static JMenuItem jMenuItemAnomalyDetection = null;
	private JMenu jMenu1 = null;
	private JMenuItem jMenuItem2 = null;
	private JMenuItem jMenuItem3 = null;
	private JDesktopPane jDesktopPane = null;
	/**
	 * This is the default constructor
	 */
	public ShowProcess1() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(986, 571);
		this.setJMenuBar(getJJMenuBar());
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Top view window");
		this.setVisible(true);
		this.setResizable(true);	
	}
	public static void println(){
		jTextArea.append("\n");
	}
	public static void println(Object str){
		print(str);
	}
	public static void print(Object str){
		jTextArea.append(str + "\n");
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new java.awt.Rectangle(0,-1,936,344));
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Process");
			//jMenu.add(getJMenuItemTraining());
			jMenu.add(getJMenuItemAnomalyDetection());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemTraining() {
		if (jMenuItemTraining == null) {
			jMenuItemTraining = new JMenuItem();
			jMenuItemTraining.setText("Training");
			jMenuItemTraining.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					trainingView = new TrainingView();
					trainingView.setVisible(true);
					trainingView.setLocation(10,0);
					trainingView.setLayer(2);
					jDesktopPane.add(trainingView);
					jMenuItemTraining.setEnabled(false);
				}
			});
		}
		return jMenuItemTraining;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAnomalyDetection() {
		if (jMenuItemAnomalyDetection == null) {
			jMenuItemAnomalyDetection = new JMenuItem();
			jMenuItemAnomalyDetection.setText("AnamolyDetection");
			jMenuItemAnomalyDetection
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							
						}
					});
		}
		return jMenuItemAnomalyDetection;
	}

	/**
	 * This method initializes jMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("File");
			jMenu1.add(getJMenuItem2());
			jMenu1.add(getJMenuItem3());
		}
		return jMenu1;
	}

	/**
	 * This method initializes jMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Save");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FileDialog saveDialog = new FileDialog(new JFrame(),"Save File",FileDialog.SAVE);
					saveDialog.show();
					if(saveDialog.getDirectory()!= null && saveDialog.getFile() != null){
					String filename = saveDialog.getDirectory()+saveDialog.getFile() + ".log";
						try {
							new FileWriter(filename).write(ShowProcess1.jTextArea.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{
						new JOptionPane().showMessageDialog(null,"The File Name is InCorrect.");
					}

				}
			});
		}
		return jMenuItem2;
	}

	/**
	 * This method initializes jMenuItem3	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Exit");
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jMenuItem3;
	}

	/**
	 * This method initializes jDesktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jDesktopPane = new JDesktopPane();
			jDesktopPane.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
			jDesktopPane.add(getJScrollPane(), null);
		}
		return jDesktopPane;
	}

	public static void main(String args[]){
		new ShowProcess1();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJDesktopPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setRows(10);
		}
		return jTextArea;
	}

}  //  @jve:decl-index=0:visual-constraint="-110,10"
