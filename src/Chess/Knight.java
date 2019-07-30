package Chess;

public class Knight extends Piece{

	public Knight(Piece.PieceColor color){
		super(PieceType.Knight, color, validMoves(), false);
	}


	private static Action[] validMoves(){
		return new Action[]{	new Action(2, 1, false, false), new Action(1, 2, false, false),
	                    	new Action(2, -1, false, false), new Action(-1, 2, false, false),
	                        new Action(2, -1, false, false), new Action(-1, 2, false, false),
	                        new Action(-2, 1, false, false), new Action(1, -2, false, false),
	                        new Action(-2, -1, false, false), new Action(-1, -2, false, false),
	                        new Action(-2, -1, false, false), new Action(-1, -2, false, false)};
	}
}
