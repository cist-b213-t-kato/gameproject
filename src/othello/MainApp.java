package othello;

import javax.swing.*;

public class MainApp extends JFrame {

	public MainApp(){
		setTitle("Othello");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel panel = new MainPanel();
		getContentPane().add(panel);
		pack();
	}
	
	public static void main(String[] args){
	
		MainApp app = new MainApp();
		app.setVisible(true);
			
		
	}
	
}