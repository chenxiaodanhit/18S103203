package Chess;


import java.util.ArrayList;

public class Board {
    private final Tile[][] board;

    public Board(){
        board = new Tile[8][8];
        initializeBoard();
        fillBoard();
    }

    public Tile[][] getBoardArray(){
        return board;
    }

    private void initializeBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if (j % 2 + i == 0) board[i][j] = new Tile(Tile.TileColor.Black);
                else board[i][j] = new Tile(Tile.TileColor.White);
            }
        }
    }

    //Will break on boards with no Kings of 'color'. Should never happen.
    public Position getKingLocation(Piece.PieceColor color){
        Position location = new Position(-1,-1);
        for (int x = 0; x <= 7; x++){
            for (int y = 0; y <= 7 ; y++){
                if (!board[y][x].isEmpty()) {
                    Piece piece = board[y][x].getPiece();
                    if (piece.getColor() == color && piece instanceof King){
                       location = new Position(x, y);
                    }
                }
            }
        }
        return location;
    }

    public Position[] getAllPiecesLocationForColor(Piece.PieceColor color){
        ArrayList<Position> locations = new ArrayList<Position>();
        for (int x = 0; x <= 7; x++){
            for (int y = 0; y <= 7; y++){
               if(!board[y][x].isEmpty() && board[y][x].getPiece().getColor() == color)
                   locations.add(new Position(x,y));
            }
        }
        return locations.toArray(new Position[0]);//allocate new array automatically.
    }

    public Tile getTileFromPosition(Position Position){
        return board[Position.Y()][Position.X()];
    }

    /*
    Initial filler of board
     */
    private void fillBoard(){
        //pawns
        for(int i = 0; i < 8; i++){
        board[1][i].setPiece(new Pawn(Piece.PieceColor.Black));
        board[6][i].setPiece(new Pawn(Piece.PieceColor.White));
        }

        //rooks
        board[0][0].setPiece(new Rook(Piece.PieceColor.Black));
        board[0][7].setPiece(new Rook(Piece.PieceColor.Black));
        board[7][0].setPiece(new Rook(Piece.PieceColor.White));
        board[7][7].setPiece(new Rook(Piece.PieceColor.White));

        //knight
        board[0][1].setPiece(new Knight(Piece.PieceColor.Black));
        board[0][6].setPiece(new Knight(Piece.PieceColor.Black));
        board[7][1].setPiece(new Knight(Piece.PieceColor.White));
        board[7][6].setPiece(new Knight(Piece.PieceColor.White));

        //bishop
        board[0][2].setPiece(new Bishop(Piece.PieceColor.Black));
        board[0][5].setPiece(new Bishop(Piece.PieceColor.Black));
        board[7][2].setPiece(new Bishop(Piece.PieceColor.White));
        board[7][5].setPiece(new Bishop(Piece.PieceColor.White));

        //queens
        board[0][3].setPiece(new Queen(Piece.PieceColor.Black));
        board[7][3].setPiece(new Queen(Piece.PieceColor.White));

        //kings
        board[0][4].setPiece(new King(Piece.PieceColor.Black));
        board[7][4].setPiece(new King(Piece.PieceColor.White));
    }
}
