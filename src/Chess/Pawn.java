package Chess;

public class Pawn extends Piece {

	public Pawn(PieceColor color){
		super(PieceType.Pawn, color, validMoves(color), false);
	}

	private static Action[] validMoves(PieceColor color){
        if (color == PieceColor.Black){
            return new Action[]{new Action(0, 1, false, false), new Action(0, 2, true, false),
                              new Action(1, 1, false, true), new Action(-1, 1, false, true)};
        } else {
            return new Action[]{new Action(0, -1, false, false), new Action(0, -2, true, false),
                              new Action(1, -1, false, true), new Action(-1, -1, false, true)};
        }
	}
}
