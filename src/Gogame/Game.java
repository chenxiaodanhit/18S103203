package Gogame;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Game extends Frame {
	Board GogameBoard = new Board();

	public Game() {
		add(GogameBoard);
		GogameBoard.setBounds(70, 90, 440, 460);

		Label label = new Label("click to point,doubled_click to remove,right click to back", Label.CENTER);
		add(label);
		label.setBounds(70, 55, 440, 24);
		label.setBackground(Color.CYAN);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});//Close window support.

		setLayout(null);
		setVisible(true);
		setSize(600, 570);
	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		Game go = new Game();
	}
}