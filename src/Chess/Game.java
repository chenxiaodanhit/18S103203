package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import Chess.Piece.PieceColor;
import Chess.Piece.PieceType;


public class Game {

    private final Board board;
    private boolean isFinished;
    private PieceColor currentPlayer;

    public Game(){
        board = new Board();
        currentPlayer = PieceColor.White;
        isFinished = false;
    }

    /**
     * @return returns true if move was played, false if move was illegal
     */
    public boolean playMove(Position from, Position to){
        if(isValidMove(from, to, false)) {
            Tile fromTile = board.getBoardArray()[from.Y()][from.X()];
            Piece pieceToMove = fromTile.getPiece();

            Tile toTile = board.getBoardArray()[to.Y()][to.X()];
            toTile.setPiece(pieceToMove);

            fromTile.empty();
            endTurn();
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return returns the current ChessBoard associated with the game.
     */
    public Board getBoard(){
        return board;
    }

    /**
     * @return returns whether the given ChessGame is finished.
     */
    public boolean isFinished(){
        return isFinished;
    }

    private void endTurn(){
        currentPlayer = (currentPlayer == PieceColor.White)
            ? PieceColor.Black
            : PieceColor.White;
    }

    // Function that checks if any piece can prevent check for the given color
    // This includes whether the King can move out of check himself.
    private boolean isCheckPreventable(PieceColor color){
        boolean canPreventCheck = false;
        Position[] locations = board.getAllPiecesLocationForColor(color);

        for(Position location : locations){
            Tile fromTile = board.getTileFromPosition(location);
            Piece piece = fromTile.getPiece();
            Position[] possibleMoves = validMovesForPiece(piece, location);

            for(Position newLocation : possibleMoves){
                Tile toTile = board.getTileFromPosition(newLocation);
                Piece toPiece = toTile.getPiece();

                //temporarily play the move to see if it makes us check
                toTile.setPiece(piece);
                fromTile.empty();

                //if we're no longer check
                if (!isKingCheck(color)){
                    canPreventCheck = true;
                }

                //revert temporary move
                toTile.setPiece(toPiece);
                fromTile.setPiece(piece);
                if(canPreventCheck){ // early out
                    System.out.printf("Prevented with from:" + fromTile + ", to: " + toTile);
                    return canPreventCheck;
                }
            }
        }
        return canPreventCheck;
    }

    private boolean isColorCheckMate(PieceColor color){
        if(!isKingCheck(color)) return false;//if not check, then we're not mate
        return !isCheckPreventable(color);
    }

    private boolean isKingCheck(PieceColor kingColor){
        Position kingLocation = board.getKingLocation(kingColor);
        return canOpponentTakeLocation(kingLocation, kingColor);
    }

    private boolean canOpponentTakeLocation(Position location, PieceColor color){
        PieceColor opponentColor = Piece.opponent(color);
        Position[] piecesLocation = board.getAllPiecesLocationForColor(opponentColor);

        for(Position fromPosition: piecesLocation) {
            if (isValidMove(fromPosition, location, true))
                return true;
        }
        return false;
    }

    /**
     * @param from the position from which the player tries to move from
     * @param to the position the player tries to move to
     * @param hypothetical if the move is hypothetical, we disregard if it sets the from player check
     * @return a boolean indicating whether the move is valid or not
     */
    private boolean isValidMove(Position from, Position to, boolean hypothetical){
        Tile fromTile = board.getTileFromPosition(from);
        Tile toTile = board.getTileFromPosition(to);
        Piece fromPiece = fromTile.getPiece();
        Piece toPiece = toTile.getPiece();

        if (fromPiece == null){
            return false;
        } else if (fromPiece.getColor() != currentPlayer) {
            return false;
        } else if (toPiece != null && toPiece.getColor() == currentPlayer) {
            return false;
        } else if (isValidMoveForPiece(from, to)){
            //if hypothetical and valid, return true
            if(hypothetical) return true;

            //temporarily play the move to see if it makes us check
            toTile.setPiece(fromPiece);
            fromTile.empty();
            if (isKingCheck(currentPlayer)){//check that move doesn't put oneself in check
                toTile.setPiece(toPiece);
                fromTile.setPiece(fromPiece);
                return false;
            }

            //if mate, finish game
            if (isColorCheckMate(Piece.opponent(currentPlayer)))
                isFinished = true;

            //revert temporary move
            toTile.setPiece(toPiece);
            fromTile.setPiece(fromPiece);

            return true;
        }
        return false;
    }

    // A utility function that gets all the possible moves for a piece, with illegal ones removed.
    // NOTICE: Does not check for counter-check when evaluating legality.
    //         This means it mostly checks if it is a legal move for the piece in terms
    //         of ensuring its not taking one of its own, and within its 'possibleMoves'.
    // Returns the Positions representing the Tiles to which the given piece
    // can legally move.
    private Position[] validMovesForPiece(Piece piece, Position currentLocation){
            return piece.hasRepeatableMoves()
                ? validMovesRepeatable(piece, currentLocation)
                : validMovesNonRepeatable(piece, currentLocation);
    }

    // Returns the Positions representing the Tiles to which the given piece
    // can legally move.
    private Position[] validMovesRepeatable(Piece piece, Position currentLocation) {
        Action[] moves = piece.getMoves();
        ArrayList<Position> possibleMoves = new ArrayList<Position>();

        for(Action move: moves){
            for(int i = 1; i < 7; i++){
                int newX = currentLocation.X() + move.x * i;
                int newY = currentLocation.Y() + move.y * i;
                if (newX < 0 || newX > 7 || newY < 0 || newY > 7) break;

                Position toLocation = new Position(newX, newY);
                Tile tile = board.getTileFromPosition(toLocation);
                if (tile.isEmpty()) {
                    possibleMoves.add(toLocation);
                } else {
                    if (tile.getPiece().getColor() != piece.getColor())
                        possibleMoves.add(toLocation);
                    break;
                }
            }
        }
        return possibleMoves.toArray(new Position[0]);
    }

    private Position[] validMovesNonRepeatable(Piece piece, Position currentLocation) {
        Action[] moves = piece.getMoves();
        ArrayList<Position> possibleMoves = new ArrayList<Position>();

        for(Action move: moves){
            int currentX = currentLocation.X();
            int currentY = currentLocation.Y();
            int newX = currentX + move.x;
            int newY = currentY + move.y;
            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) continue;
            Position newLocation = new Position(newX,newY);
            if (isValidMoveForPiece(currentLocation, newLocation)) possibleMoves.add(newLocation);
        }
        return possibleMoves.toArray(new Position[0]);
    }

    // Checks whether a given move from from one Position to another is valid.
    private boolean isValidMoveForPiece(Position from, Position to){
        Piece fromPiece = board.getTileFromPosition(from).getPiece();
        boolean repeatableMoves = fromPiece.hasRepeatableMoves();

        return repeatableMoves
            ? isValidMoveForPieceRepeatable(from, to)
            : isValidMoveForPieceNonRepeatable(from, to);
    }

    // Check whether a given move is valid for a piece without repeatable moves.
    private boolean isValidMoveForPieceRepeatable(Position from, Position to) {
        Piece fromPiece = board.getTileFromPosition(from).getPiece();
        Action[] validMoves = fromPiece.getMoves();

        int xMove = to.X() - from.X();
        int yMove = to.Y() - from.Y();

        for(int i = 1; i <= 7; i++){
            for(Action move : validMoves) {

                //generally check for possible move
                if (move.x * i == xMove && move.y * i == yMove) {

                    //if move is generally valid - check if path is valid up till i
                    for (int j = 1; j <= i; j++){
                        Tile tile = board.getTileFromPosition(new Position(from.X() + move.x * j, from.Y() +move.y * j));
                        //if passing through non empty tile return false
                        if (j != i && !tile.isEmpty())
                            return false;

                        //if last move and toTile is empty or holds opponents piece, return true
                        if (j == i && (tile.isEmpty() || tile.getPiece().getColor() != currentPlayer))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    // Check whether a given move is valid for a piece with repeatable moves.
    private boolean isValidMoveForPieceNonRepeatable(Position from, Position to){
        Piece fromPiece = board.getTileFromPosition(from).getPiece();
        Action[] validMoves = fromPiece.getMoves();
        Tile toTile = board.getTileFromPosition(to);

        int xMove = to.X() - from.X();
        int yMove = to.Y() - from.Y();

        for (Action move : validMoves) {
            if (move.x == xMove && move.y == yMove) {
                if (move.onTakeOnly){//if move is only legal on take (pawns)
                    if (toTile.isEmpty()) return false;

                    Piece toPiece = toTile.getPiece();
                    return fromPiece.getColor() != toPiece.getColor();//if different color, valid move

                    //handling first move only for pawns - they should not have moved yet
                } else if (move.firstMoveOnly) {
                    return toTile.isEmpty() && isFirstMoveForPawn(from, board);
                } else {
                    return toTile.isEmpty()||toTile.getPiece().getColor() != currentPlayer;
                }
            }
        }
        return false;
    }

    // Determine wheter the Pawn at 'from' on 'board' has moved yet.
    public boolean isFirstMoveForPawn(Position from, Board board){
        Tile tile = board.getTileFromPosition(from);
        if (tile.isEmpty() || tile.getPiece().getPieceType() != PieceType.Pawn) {
            return false;
        } else {
            PieceColor color = tile.getPiece().getColor();
            return (color == PieceColor.White)
                ? from.Y() == 6
                : from.Y() == 1;
        }
    }
    
    
    public static void main(String args[]) throws Exception {
        InputHandler handler = new InputHandler();
        Scanner scanner = new Scanner(System.in);
        Player Player1 = new Player("");
        Player Player2 = new Player("");
        System.out.println("Please enter the names of Player 1 and Player 2 in turn.");
        Player1.name = scanner.nextLine();
        Player2.name = scanner.nextLine();
        int flag = -1;
        List<String> replay = new ArrayList<String>();
        
        Game game = new Game();
        BoardDisplay.clearConsole();
        BoardDisplay.printBoard(game.getBoard());
        while (!game.isFinished()) {
        	if(flag == -1)
            System.out.println(Player1.name+"!"+" Enter move (eg. A2-A3): ");
        	else {
                System.out.println(Player2.name+"!"+" Enter move (eg. A2-A3): ");	
			}
            String input = scanner.nextLine();

            if (!handler.isValid(input)) {
                System.out.println("Invalid input!");
                System.out.println("Valid input is in form: A2-A3");
            } else {
                Position from = handler.getFrom(input);
                Position to = handler.getTo(input);

                boolean movePlayed = game.playMove(from, to);
                if (!movePlayed)
                    System.out.println("Illegal move!");
                else {
                	if(flag == -1)
                	replay.add(Player1.name+": "+input);
                	else {
                    	replay.add(Player2.name+": "+input);	
					}
                    BoardDisplay.clearConsole();
                    BoardDisplay.printBoard(game.getBoard());
                    flag = -1*flag;
                }
            }
        }
        scanner.close();
        System.out.println("Game has finished. Thanks for playing.");
    }
}
