package view;
import anomaly.view.*;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import view.AnomalyDetectionView;

public class DetectionShow extends JFrame implements Runnable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JPanel jContentPane = null;

private Thread thread;
	/**
	 * This is the default constructor
	 */
	public DetectionShow() {
		super();
		initialize();
		thread = new Thread(this);
		thread.start();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLocation(700,0);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Detection Show");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(java.awt.Color.green);
		}
		return jContentPane;
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			DetectionShow.jContentPane.setBackground(java.awt.Color.green);
		AnomalyDetectionView.setColor();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
