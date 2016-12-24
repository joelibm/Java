import java.util.Random;
public class connect4
{
	public static void main(String[] args) {
		boolean again = true;
		boolean mode;
		while (again == true){
			try{
				System.out.println("1 or 2 players?");
				int n = Integer.parseInt(System.console().readLine());
				if (n==1){
					onePlayer();
					again = playAgain();					
				}
				else if (n==2){
					twoPlayers();
					again = playAgain();
				}
				else if (n==0){
					again = false;
				}
				else{
					System.out.println("Please enter '1' or '2'.");
				}
			} catch (Exception NumberFormatException) {
				System.out.println("Please enter '1' or '2'!");
			}
			
		}
	}
	public static boolean playAgain() {
		System.out.println("Play again? (y/n)");
		String answer;
		boolean success = false;
		boolean again = false;
		while (success == false){
			try{
				answer = System.console().readLine();
				if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y'){
					success = true;
					again = true;
				}
				else{
					success = true;
					again = false;
				}
			} catch (Exception StringIndexOutOfBoundsException) {
				System.out.println("Please enter 'y' or 'n'.");
				success = false;
			}
		}
		return again;
	}
	public static void onePlayer() {
		boolean win = false;
		int player = 1;
		int move = 0;
		int[][] cell = new int[7][6] ;
		cell = init(cell);
		while (win == false && 0 <= move && move <=6){
			try{
				if (player == 1){
					move = choose(player);
					cell = play(cell, move, player);
					board(cell);
					win = victory(cell,player);
					if (win){
						System.out.println("Player "+player+" wins!");
					}
					player = 2;
				}
				else if (player == 2){
					cell = ai(cell);
					board(cell);
					win = victory(cell,player);
					if (win){
						System.out.println("Player "+player+" wins!");
					}
					player = 1;
				}
			}catch (Exception ArrayIndexOutOfBoundsException){
				System.out.println("Game Over");
			}
		}
	}
	public static void twoPlayers() {
		boolean win = false;
		int player = 1;
		int move = 0;
		int[][] cell = new int[7][6] ;
		cell = init(cell);
		while (win == false && 0 <= move && move <=6){
			try{
				if (player == 1){
					move = choose(player);
					cell = play(cell, move, player);
					board(cell);
					win = victory(cell,player);
					if (win){
						System.out.println("Player "+player+" wins!");
					}
					player = 2;
				}
				else if (player ==2){
					move = choose(player);
					cell = play(cell, move, player);
					board(cell);
					win = victory(cell,player);
					if (win){
						System.out.println("Player "+player+" wins!");
					}
					player = 1;
				}
			}catch (Exception ArrayIndexOutOfBoundsException){
				System.out.println("Game Over");
			}
		}
	}
	public static void board(int[][] cell) {
		int m = 0;
		int n = 0;
		for(n=0;n<6;n++){
			for(m=0;m<7;m++){
				if (cell[m][n] == 2){
					System.out.print("O ");}
				else if (cell[m][n] == 1){
					System.out.print("X ");}
				else {
					System.out.print("- ");};
			}
			System.out.println();
		}
		System.out.println();
	}
	public static int[][] init(int[][] cell){
		int m = 0;
		int n = 0;
		for(n=0;n<6;n++){
			for(m=0;m<7;m++){
				cell[m][n] = 0;
			}
		}
		return cell;
	}
	public static int choose(int player) { //try catch isnt working
		boolean success = false;
		int column = 0;
		System.out.println("Player "+player+", place piece in which row? (1-7)");
		while (success == false){
			try{
				column = Integer.parseInt(System.console().readLine());
				if (column < 8 && column >= 0){
					success =true;
				}
				else{
					System.out.println("Please enter a number 1-7 to play, 0 to quit.");
				}
			} catch (Exception ArrayIndexOutOfBoundsException){
				System.out.println("Please enter a number 1-7 to play, 0 to quit.");
				success = false;
			}
		}
		return column-1;
	}
	public static int[][] play(int[][]cell, int column, int player){
		boolean done = false;
		int row = 5;
		while (done == false){
			if (row < 0){
				System.out.println("That column is full! Choose again.");
				column = choose(player);
				row = 5;
			}
			else if (cell[column][row] == 0){
				cell[column][row] = player;
				done = true;
			}
			else{
				row--;
			}			
		}
		return cell;
	}
	public static int[][] undo(int[][]cell, int column, int player){
		boolean done = false;
		int row = 0;
		while (done == false){
			if (row > 5){
				System.out.print("That column is full! Choose again.");
				column = choose(player);
				row = 0;
			}
			else if (cell[column][row] == player){
				cell[column][row] = 0;
				done = true;
			}
			else{
				row++;
			}			
		}
		return cell;
	}
	public static boolean victory (int[][]cell, int player){
		int column = 0;
		int row = 0;
		boolean win = false;
		//row checker
		for (row = 0; row < 6; row++){
			for (column = 0; column < 4; column++){
				if ((player == cell[column][row]) && (player == cell[column+1][row]) && (player == 
				cell[column+2][row]) && (player == cell[column+3][row])){
					win = true;
					break;
				}
			}
		}
		//column checker
		for (column = 0; column < 7; column++){
			for (row = 0; row < 3; row++){
				if ((player == cell[column][row]) && (player == cell[column][row+1]) && (player == 
				cell[column][row+2]) && (player == cell[column][row+3])){
					win = true;
					break;
				}
			}
		}
		//down diagonal checker
		for (column = 0; column <= 3; column++){
			for (row = 0; row < 3; row++){
				if ((player == cell[column][row]) && (player == cell[column+1][row+1]) && (player == 
				cell[column+2][row+2]) && (player == cell[column+3][row+3])){
					win = true;
					break;
				}
			}
		}
		//up diagonal checker
		for (column = 6; column >= 3; column--){
			for (row = 0; row < 3; row++){
				if ((player == cell[column][row]) && (player == cell[column-1][row+1]) && (player == 
				cell[column-2][row+2]) && (player == cell[column-3][row+3])){
					win = true;					
					break;
				}
			}
		}
		return win;
	}
	public static int[][] ai(int[][] cell){
		int[][] copy = new int[7][6];
		System.arraycopy(cell, 0, copy, 0, cell.length);
		boolean played = false;
		Random rand = new Random();
		if (played == false){  //ai victory checker
			for (int column = 0; column < 7 ; column++){
				if (copy[column][0] == 0){
					play(copy, column, 2);
					if(victory(copy, 2)){
						undo(copy, column, 2);
						play(cell, column, 2);
						System.out.println(column);
						played = true;
						break;
					}
					else{
						undo(copy, column, 2);
					}
				}
			}
		}
		if (played == false){  //player victory checker
			for (int column = 0; column < 7 ; column++){
				if (copy[column][0] == 0){
					play(copy, column, 1);
					if(victory(copy, 1)){
						undo(copy, column, 1);
						play(cell, column, 2);
						System.out.println(column);
						played = true;
						break;
					}
					else{
						undo(copy, column, 1);
						}
				}
			}
		}
		while (played == false){  //random play
			int n = rand.nextInt(6);
			if (cell[n][0] == 0 && cell[n][1] == 0){
				play(copy, n, 2);
				play(copy, n, 1);
				if(victory(copy, 1)){
					undo(copy, n, 1);
					undo(copy, n, 2);
					played = false;
				}
				else{
					undo(copy, n, 1);
					undo(copy, n, 2);
					play(cell,n,2);
					System.out.println(n+1);
					played = true;
				}
			}
			else if (cell[n][0] ==0){
				play(cell,n,2);
				System.out.println(n+1);
				played = true;
			}
		}
		return cell;
	}
}
