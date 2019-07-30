package Chess;

public class Queen extends Piece{

	public Queen(Piece.PieceColor color){
		super(PieceType.Queen, color, validMoves(), true);
	}


	private static Action[] validMoves(){
		return new Action[]{	new Action(1, 0, false, false), new Action(0, 1, false, false),
                          new Action(-1, 0, false, false), new Action(0, -1, false, false),
                          new Action(1, 1, false, false), new Action(1, -1, false, false),
                          new Action(-1, 1, false, false), new Action(-1, -1, false, false)};
	}
}
