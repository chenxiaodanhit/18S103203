package MyChessAndGoGagame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Chess.BoardDisplay;
import Chess.InputHandler;
import Chess.Player;
import Chess.Position;

public class MyChessAndGoGame {

	public static void main(String args[]) {
		System.out.println("Please input the type of the game,choose Gogame please input 0, choose Chess please input 1£¡");
		Scanner input_game = new Scanner(System.in);
		int input_number = input_game.nextInt();
		if(input_number == 0) 
		{   
			System.out.println("you choose the Gogame!");
			Gogame.Game go = new Gogame.Game();
		}
		else if(input_number == 1) 
		{
			System.out.println("you choose the Chess!");
			InputHandler handler = new InputHandler();
	        Scanner scanner = new Scanner(System.in);
	        Player Player1 = new Player("");
	        Player Player2 = new Player("");
	        System.out.println("Please enter the names of Player 1 and Player 2 in turn.");
	        Player1.name = scanner.nextLine();
	        Player2.name = scanner.nextLine();
	        int flag = -1;
	        List<String> replay = new ArrayList<String>();
	        
	        Chess.Game game = new Chess.Game();
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
		else{
			System.out.println("sorry, the type of your game is not exist!");
			
			
		}
	}
	
}
