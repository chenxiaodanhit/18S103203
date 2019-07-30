package Chess;

public abstract class Piece {
    private final PieceType type;
    private final PieceColor color;
    private final Action[] moves;
    private final String name;
    private final char charValue;
    private final boolean repeatableMoves;

    protected Piece(PieceType type, PieceColor color, Action[] moves, boolean repeatableMoves){
        this.type = type;
        this.color = color;
        this.moves = moves;
        this.repeatableMoves = repeatableMoves;
        name = type.name();
        charValue = type.name().trim().charAt(0);
    }

    public enum PieceType {
        Pawn, Rook, Knight, Bishop, Queen, King
    }

    public enum PieceColor {
        White, Black
    }
    public Action[] getMoves(){ return moves; }

    public String getName(){ return name; }

    public PieceColor getColor(){ return color; }

    public char getCharValue(){ return charValue; }

    public boolean hasRepeatableMoves(){ return repeatableMoves; }

    public PieceType getPieceType() {return type; }

    public static PieceColor opponent(PieceColor color) {
        return (color == PieceColor.Black) ? PieceColor.White : PieceColor.Black;
    }

}
