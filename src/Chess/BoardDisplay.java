package Chess;


public class BoardDisplay {

    public static void printBoard(Board board){
        clearConsole();
        Tile[][] b = board.getBoardArray();

        System.out.println();
        System.out.println("      [A][B][C][D][E][F][G][H] \n");
        for(int i = 0; i < 8; i++) {
            System.out.print("[" + (8 - i) + "]   ");

            for (int j = 0; j < 8; j++){
                System.out.print(b[i][j].getValue());
            }

            System.out.println("   [" + (8 - i) + "]");
        }

        System.out.println("\n      [A][B][C][D][E][F][G][H]\n");
    }

    /**
     * Universal console clear for both Windows and Unix machines.
     */
    public static void clearConsole(){
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                //ASCII escape code
                System.out.print("Chess");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
            System.out.println("Error while trying to clear console");
        }
    }
}
