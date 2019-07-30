package Chess;

public class Rook extends Piece {

	public Rook(PieceColor color){
		super(PieceType.Rook, color, validMoves(), true);
	}


	private static Action[] validMoves(){
		return new Action[]{	new Action(1, 0, false, false), new Action(0, 1, false, false),
                            new Action(-1, 0, false, false), new Action(0, -1, false, false)};
	}
}
