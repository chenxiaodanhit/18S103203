package Chess;

public class Tile {

    private Piece piece;
    @SuppressWarnings("unused")
	private final TileColor color;

    public enum TileColor{
        White, Black
    }

    public Tile(TileColor color){
        this.color = color;
    }

    public Tile(TileColor color, Piece piece){
        this.color = color;
        this.piece = piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public String getValue(){
        if(piece != null){
            return "[" + piece.getCharValue() + "]";
        } else {
            return "[ ]";
        }
    }

    public boolean isEmpty(){
        return piece == null;
    }

    public void empty(){
        piece = null;
    }
}
