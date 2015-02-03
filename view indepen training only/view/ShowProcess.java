package view;

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
import anomaly.view.ShowProcess1;

public class ShowProcess extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	//private JPanel jContentPane1 = null;
	private static JTextArea jTextArea = null;
	private static JTextArea jTextArea1 = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	private TrainingView trainingView = null;
	private ShowProcess1 topview = null;
	private AnomalyDetectionView anomalyDetectionView = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	public static JMenuItem jMenuItemTraining = null;
	public static JMenuItem jMenuItemAnomalyDetection = null;
	private JMenu jMenu1 = null;
	private JMenuItem jMenuItem2 = null;
	private JMenuItem jMenuItem3 = null;
	private JDesktopPane jDesktopPane = null;
	private JDesktopPane jDesktopPane1 = null;
	/**
	 * This is the default constructor
	 */
	public ShowProcess() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(986, 730);
		this.setJMenuBar(getJJMenuBar());
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		//this.setContentPane(getJContentPane1());
		this.setTitle("Anomaly Detection System");
		this.setVisible(true);
		this.setResizable(true);
		//jTextArea1=new JTextArea("Top view",500,344);
		//this.add(jTextArea1);
		
	}
	public static void println(){
		jTextArea.append("\n");
		//jTextArea1.append("\n");
	}
	public static void println(Object str){
		print(str);
	}
	public static void print(Object str){
		jTextArea.append(str + "\n");
		//jTextArea1.append(str + "\n");
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new java.awt.Rectangle(0,10,936,344));
			jScrollPane.setViewportView(getJTextArea());
						
		}
		return jScrollPane;
	}
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new java.awt.Rectangle(0,400,936,944));
			jScrollPane1.setViewportView(getJTextArea1());
			
		}
		return jScrollPane1;
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
			jMenu.add(getJMenuItemTraining());
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
					topview=new ShowProcess1();
					topview.setVisible(true);
					
					//jDesktopPane1.add(topview);
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
	 * 	* @return javax.swing.JMenuItem	
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
							new FileWriter(filename).write(ShowProcess.jTextArea.getText());
							//new FileWriter(filename).write(ShowProcess.jTextArea1.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"The File Name is InCorrect.");
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
			//jDesktopPane.add(getJScrollPane1(), null);
			
		}
		return jDesktopPane;
	}
	private JDesktopPane getJDesktopPane1() {
		if (jDesktopPane1 == null) {
			jDesktopPane1 = new JDesktopPane();
			jDesktopPane1.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
			jDesktopPane1.add(getJScrollPane1(), null);
			//jDesktopPane.add(getJScrollPane1(), null);
			
		}
		return jDesktopPane1;
	}

	public static void main(String args[]){
		new ShowProcess();
		
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
	/*private JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(new BorderLayout());
			jContentPane1.add(getJDesktopPane1(), java.awt.BorderLayout.WEST);
		}
		return jContentPane1;
	}*/
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
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setRows(10);
		}
		return jTextArea1;
	}
}  //  @jve:decl-index=0:visual-constraint="-110,10"
