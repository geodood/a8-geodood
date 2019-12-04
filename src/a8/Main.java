package a8;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		ConwaysGameOfLifeModel model = new ConwaysGameOfLifeModel();
		ConwaysGameOfLifeView view = new ConwaysGameOfLifeView();
		ConwaysGameOfLifeController controller = new ConwaysGameOfLifeController(model,view);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's Game Of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main_frame.setContentPane(view);
		
		main_frame.pack();
		main_frame.setVisible(true);
	}
}
