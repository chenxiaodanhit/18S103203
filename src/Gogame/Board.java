package Gogame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Board extends Panel implements Action {
	int x = -1, y = -1, chessColor = 1;
	Player player1 = new Player("");
	Player player2 = new Player("");
	List<Position> positions = new ArrayList<Position>();
	Button startbutton = new Button("reset");
	Button inputButton = new Button("start");
	TextField text_1 = new TextField("white please"), text_2 = new TextField(""), // white please
			text_3 = new TextField("white'name"), text_4 = new TextField("black'name");

	Board() {
		add(inputButton);
		inputButton.setBounds(35, 5, 60, 26);
		inputButton.addActionListener(this);
		// inputButton.addFocusListener(this);

		add(text_3);
		text_3.setBounds(115, 5, 90, 24);
		text_3.addFocusListener(this);
		// text_3.setEditable(true);

		add(text_4);
		text_4.setBounds(315, 5, 90, 24);
		text_4.addFocusListener(this);
		text_4.setEditable(true);

		add(startbutton);
		startbutton.setBounds(35, 36, 60, 26);
		startbutton.setEnabled(false);
		startbutton.addActionListener(this);

		add(text_1);
		text_1.setBounds(115, 36, 90, 24);
		text_1.setEnabled(false);
		text_1.setEditable(false);

		add(text_2);
		text_2.setBounds(315, 36, 90, 24);
		text_2.setEnabled(false);
		text_2.setEditable(false);

		setSize(440, 440);
		setLayout(null);
		setBackground(Color.CYAN);
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		for (int i = 80; i <= 440; i = i + 20) {
			g.drawLine(40, i, 400, i);
		}

		for (int j = 40; j <= 400; j = j + 20) {
			g.drawLine(j, 80, j, 440);
		}

		g.fillOval(97, 137, 6, 6);// Draw five Central points.
		g.fillOval(337, 137, 6, 6);
		g.fillOval(97, 377, 6, 6);
		g.fillOval(337, 377, 6, 6);
		g.fillOval(217, 257, 6, 6);
	}

	public void focusGained(FocusEvent e) {
		Component com = (Component) e.getSource();// Write down which component acts.
		if (com == text_3) {
			text_3.setText("");
		} else if (com == text_4) {
			text_4.setText("");
		}

	}

	public void focusLost(FocusEvent e) {

	}// Functions in the interface, not used here.

	public void mousePressed(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			x = (int) e.getX();
			y = (int) e.getY();

			int a = (x + 10) / 20, b = (y + 10) / 20;
			ChessPoint_white chesspoint_white = new ChessPoint_white(this);
			ChessPoint_black chesspoint_black = new ChessPoint_black(this);
			
			// Left Click to play chess

			if (x / 20 < 2 || y / 20 < 4 || x / 20 > 19 || y / 20 > 21) {

			} // Avoid checkers out of bounds.

			else {
				if (player1.name.length() == 0 || player2.name.length() == 0) {
					JOptionPane.showMessageDialog(this,
							"please input the player's name,and click start button before you start chess!", "reminder",
							JOptionPane.WARNING_MESSAGE);
				}

				else if (player1.name.equals("white'name")) {
					JOptionPane.showMessageDialog(this, "please input the white player's name,and click start button",
							"reminder", JOptionPane.WARNING_MESSAGE);
				} else if (player2.name.equals("black'name")) {
					JOptionPane.showMessageDialog(this, "please input the black player's name,and click input button",
							"reminder", JOptionPane.WARNING_MESSAGE);
				} else {
					if (chessColor == 1) {
						this.add(chesspoint_white);
						chesspoint_white.setBounds(a * 20 - 10, b * 20 - 10, 20, 20);// Make sure it's at the center.
						positions.add(new Position(player1.name,a * 20, b * 20));
						chessColor = chessColor * (-1);
						text_2.setText(this.player2.name + " please");
						text_1.setText("");
					}

					else if (chessColor == -1) {
						this.add(chesspoint_black);
						chesspoint_black.setBounds(a * 20 - 10, b * 20 - 10, 20, 20);
						positions.add(new Position(player2.name,a * 20, b * 20));
						chessColor = chessColor * (-1);
						text_1.setText(this.player1.name + " please");
						text_2.setText("");
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == inputButton) {
			player1.name = text_3.getText().trim();
			player2.name = text_4.getText().trim();

			if (player1.name.length() == 0 || player2.name.length() == 0) {
				JOptionPane.showMessageDialog(this,
						"you did not complete the information or you have input the illegal characteristics!",
						"reminder", JOptionPane.WARNING_MESSAGE);
			} else if (player1.name.equals("white'name")) {
				JOptionPane.showMessageDialog(this, "please input the white player's name", "reminder",
						JOptionPane.WARNING_MESSAGE);
			} else if (player2.name.equals("black'name")) {
				JOptionPane.showMessageDialog(this, "please input the balck player's name", "reminder",
						JOptionPane.WARNING_MESSAGE);
			} else if (text_3.getText().equals(text_4.getText())) {
				JOptionPane.showMessageDialog(this,
						"you have input the same name for two different players, please reinput the players name",
						"reminder", JOptionPane.WARNING_MESSAGE);
			} else {
				inputButton.setEnabled(false);
				// text_3.removeFocusListener(this);
				text_3.setEnabled(false);
				text_4.setEnabled(false);
				startbutton.setEnabled(true);
				// text_1.setEnabled(true);
				text_1.setText(player1.name + " please");
				// text_2.setEnabled(true);
			}
		}

		else if (e.getSource() == startbutton) {

			this.removeAll();// Remove all components. (Note that paint content will not be removed, nor will
								// listeners already associated with components be removed)
			System.out.println("The replay board is as follows (chessboard coordinates range from (40,80) to (400,440))");
			for(int i = 0;i<positions.size();i++) {
				System.out.println(positions.get(i).getn()+":"+"("+positions.get(i).getx()+","+positions.get(i).gety()+")");
			}

			inputButton.setEnabled(true);
			text_3.setEnabled(true);
			text_4.setEnabled(true);
			startbutton.setEnabled(false);
			text_1.setEnabled(false);
			text_2.setEnabled(false);

			chessColor = 1;// Reset to white chess player first.

			add(startbutton);
			startbutton.setBounds(35, 36, 60, 26);

			add(text_1);
			text_1.setBounds(115, 36, 90, 24);
			text_1.setText("white please");

			add(text_2);
			text_2.setBounds(315, 36, 90, 24);
			text_2.setText("");

			add(inputButton);
			inputButton.setBounds(35, 5, 60, 26);

			add(text_3);
			text_3.setText("white'name");
			text_3.addFocusListener(this);
			text_3.setBounds(115, 5, 90, 24);

			add(text_4);
			text_4.setText("black'name");
			text_4.setBounds(315, 5, 90, 24);

			player1.name = "";
			player2.name = "";
		}

	}
}