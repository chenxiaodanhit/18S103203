package Gogame;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
class ChessPoint_white extends Piece{
	Board GogameBoard = null;

	ChessPoint_white(Board p) {
		setSize(20, 20);
		GogameBoard = p;
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(0, 0, 20, 20);
	}

	public void mousePressed(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			GogameBoard.remove(this);
			GogameBoard.chessColor = -1;
			GogameBoard.text_1.setText("");
			GogameBoard.text_2.setText(GogameBoard.player2.name + " please");
		}
	}

	public void mouseReleased(MouseEvent e) {
	
	}

	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
    
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >= 2) {
			GogameBoard.remove(this);
		}
	}
}
