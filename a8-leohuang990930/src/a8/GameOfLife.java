package a8;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import accessory.Widget;

public class GameOfLife {

	public static void main(String[] args) {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's game");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create panel for content. Uses BorderLayout. */
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		/* Create ExampleWidget component and put into center
		 * of content panel.
		 */
		int width = Integer.parseInt(JOptionPane.showInputDialog("Enter the width(integer between 10 and 500)"));
		int height = Integer.parseInt(JOptionPane.showInputDialog("Enter the height(integer between 10 and 500)"));

		Widget ttt = new Widget(width,height);
		top_panel.add(ttt, BorderLayout.CENTER);


		/* Pack main frame and make visible. */
		
		main_frame.pack();
		main_frame.setVisible(true);
	}
}
