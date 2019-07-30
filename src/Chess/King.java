package Chess;



public class King extends Piece{

	public King(Piece.PieceColor color){
		super(PieceType.King, color, validMoves(), false);
	}

	private static Action[] validMoves(){
		return new Action[]{	new Action(1, 0, false, false), new Action(0, 1, false, false),
                        	new Action(-1, 0, false, false), new Action(0, -1, false, false),
                        	new Action(1, 1, false, false), new Action(1, -1, false, false),
                        	new Action(-1, 1, false, false), new Action(-1, -1, false, false)};
	}
}
