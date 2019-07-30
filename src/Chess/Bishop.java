package Chess;


public class Bishop extends Piece {

	public Bishop(PieceColor color){
		super(PieceType.Bishop, color, validMoves(), true);
	}


	private static Action[] validMoves(){
		return	new Action[]{	new Action(1, 1, false, false), new Action(1, -1, false, false),
	                        new Action(-1, 1, false, false), new Action(-1, -1, false, false)};
	}
}
