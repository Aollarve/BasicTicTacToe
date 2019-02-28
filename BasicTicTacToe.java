import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BasicTicTacToe {
	
	static int Cplay = -1; // Cplay initialized to 1 
	static char Clet = 'O';
	static int GameOver = 0;
	
	static void computerPlay(char[] board) {
		
		minimax(board, Clet);
		if (Cplay != -1) {
			System.err.print("Computer Play: ");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Cplay+1);
			System.out.flush();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board[Cplay] = Clet;
		}
	}
	
	static int minimax(char[] board, char p) {
		if(gameOver(board) == 1 && p != Clet) { // Player can't play because computer won or tied
			return 10;
		} else if(gameOver(board) == 1) { // Computer can't play because player won
			return -10;
		} else if(gameOver(board) == -1) { // Player or Computer can't play because there is a tie
			return 0;
		}
		
		int[] score = new int[9];
		int[] move = new int[9];
		char op = 'X';
		if(p == 'X') {
			op = 'O';
		}
		
		for(int i=0; i < 9; i++) {
			if(board[i] != 'X' && board[i] != 'O') {
					char[] boardCopy = new char[9];
					boardCopy = board.clone();
					boardCopy[i] = p;
					score[i] = minimax(boardCopy, op);
					move[i] = 1;
				
			}
		}
		
		if(p == Clet) {
			
			int max = Integer.MIN_VALUE;
			for(int i=0; i < 9; i++) {
				if(move[i] == 1) {
					char[] boardCopy = new char[9];
					boardCopy = board.clone();
					boardCopy[i] = p;
					if(gameOver(boardCopy) == 1 && score[i] == 10) {
						max = score[i];
						Cplay = i;
					} else if(score[i] > max) {
						max = score[i];
						Cplay = i;
					}
				}
			}
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			for(int i=0; i < 9; i++) {
				if(move[i] == 1 && score[i] < min) {
						min = score[i];
				}
			}
			return min;
		}
	}
	
	static int gameOver(char[] board) {
		if((board[0] != '-' && board[0] == board[1] && board[1] == board[2])
				||(board[3] != '-' && board[3] == board[4] && board[4] == board[5])
				||(board[6] != '-' && board[6] == board[7] && board[7] == board[8])
				||(board[0] != '-' && board[0] == board[3] && board[3] == board[6])
				||(board[1] != '-' && board[1] == board[4] && board[4] == board[7])
				||(board[2] != '-' && board[2] == board[5] && board[5] == board[8])
				||(board[0] != '-' && board[0] == board[4] && board[4] == board[8])
				||(board[2] != '-' && board[2] == board[4] && board[4] == board[6])) {
			return 1;
		} else if(board[0] != '-' && board[1] != '-' && board[2] != '-' && board[3] != '-'
				&& board[4] != '-' && board[5] != '-' && board[6] != '-' && board[7] != '-'
				&& board[8] != '-') {
			return -1;
		} else {
			return 0;
		}
	}
	
	static boolean gameOver2(char[] board) {
		if((board[0] != '-' && board[0] == board[1] && board[1] == board[2])
				||(board[3] != '-' && board[3] == board[4] && board[4] == board[5])
				||(board[6] != '-' && board[6] == board[7] && board[7] == board[8])
				||(board[0] != '-' && board[0] == board[3] && board[3] == board[6])
				||(board[1] != '-' && board[1] == board[4] && board[4] == board[7])
				||(board[2] != '-' && board[2] == board[5] && board[5] == board[8])
				||(board[0] != '-' && board[0] == board[4] && board[4] == board[8])
				||(board[2] != '-' && board[2] == board[4] && board[4] == board[6])) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	static boolean gameWon(char[] board, int position, char letter) {
		
		switch(position) {
		case 1:
			if((board[1] == letter && board[2] == letter)
				||(board[4] == letter && board[8] == letter)
				||(board[3] == letter && board[6] == letter)) {
				return true;
			} else {
				return false;
			}
		case 2:
			if((board[0] == letter && board[2] == letter)
				||(board[4] == letter && board[7] == letter)) {
				return true;
			} else {
				return false;
			}
		case 3:
			if((board[0] == letter && board[1] == letter)
				||(board[5] == letter && board[8] == letter)
				||(board[4] == letter && board[6] == letter)) {
				return true;
			} else {
				return false;
			}
		case 4:
			if((board[0] == letter && board[6] == letter)
				||(board[4] == letter && board[5] == letter)) {
				return true;
			} else {
				return false;
			}
		case 5:
			if((board[0] == letter && board[8] == letter)
				||(board[3] == letter && board[5] == letter)
				||(board[1] == letter && board[7] == letter)
				||(board[2] == letter && board[6] == letter)) {
				return true;
			} else {
				return false;
			}
		case 6:
			if((board[2] == letter && board[8] == letter)
				||(board[3] == letter && board[4] == letter)) {
				return true;
			} else {
				return false;
			}
		case 7:
			if((board[0] == letter && board[3] == letter)
				||(board[7] == letter && board[8] == letter)) {
				return true;
			} else {
				return false;
			}
		case 8:
			if((board[1] == letter && board[4] == letter)
				||(board[7] == letter && board[8] == letter)) {
				return true;
			} else {
				return false;
			}
		case 9:
			if((board[2] == letter && board[5] == letter)
				||(board[6] == letter && board[7] == letter)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	

	
	public static void main(String[] args) {
		char[] board = new char[9]; // board to play TicTacToe
		Arrays.fill(board, '-');
		Scanner sc = new Scanner(System.in);
		
		int exit = 0;
		char pbyp = ' '; // stores the letter the player is playing 
		
		while(exit != 1) {
				System.err.print("Would you like to play X or O? (E to exit) ");
				pbyp = sc.next().charAt(0);
				pbyp = Character.toUpperCase(pbyp);
				while(pbyp != 'X' && pbyp != 'O' && pbyp != 'E') {
					System.err.print("Incorrect Input. Would you like to play X or O? (E to exit) ");
					pbyp = sc.next().charAt(0);
					pbyp = Character.toUpperCase(pbyp);
				}
				
				if(pbyp == 'O') {
					Clet = 'X';
				} else if(pbyp == 'X') {
					Clet = 'O';
				} else {
					break;
				}

			while(gameOver(board) != 1 && gameOver(board) != -1) {
				if(Clet == 'X') {
					computerPlay(board);
					System.err.println();
					for(int i=0; i < 9; i++) {
						if((i+1)%3 == 0) {
							System.err.println(board[i]);
						} else {
							System.err.print(board[i] );
						}
					}
					if (gameOver(board) == 1 || gameOver(board) == -1) {
						break;
						
					}
				}
				System.err.println();
				System.err.print("Make your play 1-9 (E to exit): ");
				int play = 0;
				if(sc.hasNextInt()) {
					play = sc.nextInt();
				} else {
					sc.next();
				}
				while(play < 1 || play > 9 || board[play-1] != '-') {
					System.err.print("Invalid Move. Make your play 1-9: ");
					if(sc.hasNextInt()) {
						play = sc.nextInt();
					} else {
						sc.next();
					}
				}

				board[play-1] = pbyp;
				if(Clet == 'O') {
					computerPlay(board);
				}
				for(int i=0; i < 9; i++) {
					if((i+1)%3 == 0) {
						System.err.println(board[i]);
					} else {
						System.err.print(board[i] );
					}
				}
			}
			
			if(gameOver(board) == 1) {
				System.err.println("The Computer just won!");
			} else {
				System.err.println("There was a tie!");
			}
			System.err.println();
			Arrays.fill(board, '-');
			
		}
		
	}

}
