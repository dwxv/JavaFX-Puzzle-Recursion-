package src;



//cooment

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

//import src.FileIO;



public class MainTester {




	public static void main(String[] args) {

		FileIO file = new FileIO();
		//									in.txt out.txt s
		File inFile = new File("src/src/input3.txt");
		

		ArrayList<Hexagon> list = file.readData(inFile);

		Board board = new Board();/////////////////////////
		
		for (int i = 0; i < 7; i ++) {
			board.positions.add(new Hexagon());///////////////////
			
			
		}
		
		ArrayList<Hexagon> leftOver = file.readData(inFile);
				

		//		if (allColorsMatch(board)) {
		//			System.out.println(board);
		//		}

		//		Board board = new Board(list.get(0));



		solve(board, list, leftOver);///////////////////////////////////////////////////////

		System.out.println("Solutions: " + board.solutionNumber);///////////////////////////////
	}

	public static void solve(Board board, ArrayList<Hexagon> list, ArrayList<Hexagon> leftOver) {		
		solve(board, list, leftOver, 0);
		if (board.solutionNumber.equals("0")) {
			System.out.println("No Solution");
		}
	}

	public static void solve(Board board, ArrayList<Hexagon> list, ArrayList<Hexagon> leftOver,int boardPosition) {


		//Base Case
		if (allColorsMatch(board)) {
			board.solutionNumber = board.solutionNumber.add(new BigInteger("1"));

//						if (board.solutionNumber == 1) {
			System.out.println("Solution #" + board.solutionNumber + ":");
			System.out.println(board);
//						}


		}
		
		//recursive case
		else {
			for (int i = 0; i < list.size(); i++) { 	//for every tile i 


				
//				board.positions.set(boardPosition, list.get(i));
//				board.positions.get(boardPosition).setPositionNumber(boardPosition);
//
//				if (boardPosition == 0 && leftOver.size() > 0 && leftOver.get(0).equals(list.get(i))) {
//					leftOver.remove((Hexagon)list.get(i));
//				}
//				list.remove(i);
				
				//////////////////////////////////////////////////////////////////////////////
//				board.positions.set(boardPosition, list.get(i));
//				board.positions.get(boardPosition).positionNumber = boardPosition;

				if (boardPosition == 0 && leftOver.size() > 0 && leftOver.get(0).equals(list.get(i))) {
					board.positions.set(boardPosition, leftOver.get(0));
					board.positions.get(boardPosition).positionNumber = boardPosition;
					leftOver.remove((Hexagon)list.get(i));
					
				}
				else if (boardPosition == 7) {
					System.out.println(board);
					System.exit(0);
				}
				else if (list.size() > 0) {
					board.positions.set(boardPosition, list.get(i));
					board.positions.get(boardPosition).positionNumber = boardPosition;
				}
				
				list.remove(i);

				

				for (int j = 0; j < board.positions.get(boardPosition).colors.length; j++) {	//for every color j
					
					

					if (noConflicts(board, boardPosition)) {										//    check if colors match
						
						//MIGHT NEED A FOR LOOP : REPLACE leftover(0) w/ leftover(i)
						for (int ii = 0; ii < leftOver.size(); ii++) {
							if (leftOver.size() > 0 && board.positions.get(boardPosition).getTileNum() == leftOver.get(ii).getTileNum()) {
								
								//MAKE A DEEP COPY
								
								leftOver.get(ii).copyColors(board.positions.get(boardPosition));
							}
						}
						
						solve(board, list, leftOver, boardPosition + 1);									//          if match, recurse ///////////
					}
					
					//if all tiles tried, switch out the tile with the next one
					if (board.positions.get(boardPosition).getPositionNumber() == 0 &&
							leftOver.size() > 0 && boardPosition == 0 && list.size() > 0)  {
						
						
						list.add(0, board.positions.get(boardPosition));
						board.positions.set(boardPosition, leftOver.get(0));
						
						for (int a = 0; a < list.size(); a++) {
							if (list.get(a).equals(leftOver.get(0))) {
								list.remove(a);
							}
						}						
						
//						for (int c = 0; c < list.size(); c++) {
//							if (list.get(c).equals((Hexagon)leftOver.get(0))) {
//								list.remove(c);
//							}
//						}
						
						//Sort list
						for (int a = 0; a < list.size() - 1; a++) {
							if (list.get(a).tileNum > list.get(a + 1).tileNum) {
								Hexagon temp = new Hexagon(list.get(a));
								list.set(a, list.get(a + 1));
								list.set(a + 1, temp);
							}
						}
						leftOver.remove(0);
					}
					else {
						board.positions.get(boardPosition).rotate();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					}

				}


				//backtrack
				list.add(0, board.positions.get(boardPosition));

				//Sort list
				for (int a = 0; a < list.size() - 1; a++) {
					if (list.get(a).tileNum > list.get(a + 1).tileNum) {
						Hexagon temp = new Hexagon(list.get(a));
						list.set(a, list.get(a + 1));
						list.set(a + 1, temp);
					}
				}

				//				board.positions.remove(board.positions.get(listPosition));
				//				board.positions.remove(board.positions.get(i));
				board.positions.set(boardPosition, new Hexagon());


			}
		}



	}
	

	public static boolean allColorsMatch(Board board) {


		for (int i = 0; i < board.positions.size(); i++) {
			if (board.positions.get(i).getTileNum() == 0) {
				return false;
			}
		}

		//THIS IS TO CHECK THOSE ADJACENTTO CENTER TILE (INNER CIRCLE)

		int nextTileColor = 3;
		for (int i = 0, nextTile = 1; i < 6; i++, nextTile++) {

			//LOOP BACK THE NUMBER
			if (nextTileColor > 5) {
				nextTileColor = 0;
			}

			//CHECK IF TILE COLORS MATCH
			if (board.positions.get(0).colors[i] !=
					board.positions.get(nextTile).colors[nextTileColor]) {
				return false;
			}

			nextTileColor++;
		}


		//THIS IS TO CHECK THOSE ADJACENT IN THE OUTER TILES (OUTER CIRCLE)

		int firstOuterTile = 1; 		//1,2,3,4,5,6 -> 0,1,2,3
		int firstOuterTileColor = 2;    //3,4,5,6 -> 0,1,2,3,4,5
		int secondOuterTile = 2;        //3,4,5,6,7 -> 0,1,2,3,4
		int secondOuterTileColor = 5;   //6 -> 0,1,2,3,4,5,6 ->

		for (int i = 0; i < 6; i++) {
			//reset the cycle of numbers
			if (firstOuterTile > 6) {
				firstOuterTile = 0;
			}
			if (firstOuterTileColor > 5) {
				firstOuterTileColor = 0;
			}
			if (secondOuterTile > 5) {
				secondOuterTile = 0;
			}
			if (secondOuterTileColor > 5) {
				secondOuterTileColor = 0;
			}

			if (board.positions.get(firstOuterTile).colors[firstOuterTileColor] != 
					board.positions.get(secondOuterTile).colors[secondOuterTileColor]) {
				return false;
			}

			firstOuterTile++;
			firstOuterTileColor++;
			secondOuterTile++;
			secondOuterTileColor++;
		}

		if ( (board.positions.size() == 7) && 
				(board.positions.get(0).colors[5] != board.positions.get(6).colors[2]) &&
				(board.positions.get(5).colors[0] != board.positions.get(6).colors[3]) && 
				(board.positions.get(6).colors[1] != board.positions.get(1).colors[4])) {
			return false;
		}

		return true;
	}

	public static boolean noConflicts(Board board, int boardPosition) {

		if (boardPosition == 0) {
			return true;
		}

		//////////////////////////////////////////////////////////////////////////or boardPosition == 1??
		//if center and north match colors
		//		if ((board.positions.size() == 2) && 
		//				(board.positions.get(0).colors[i - 1] == 
		//				 board.positions.get(i).colors[i+2])) {
		//			
		//			return true;
		//		}
		if ((boardPosition == 1) && 
				(board.positions.get(0).colors[0] == 
				board.positions.get(1).colors[3])) {

			return true;
		}


		if ((boardPosition == 2) &&
				(board.positions.get(0).colors[1] == 
				board.positions.get(2).colors[4]) && 
				(board.positions.get(1).colors[2] == 
				board.positions.get(2).colors[5])) {
			return true;
		}

		if ((boardPosition == 3) &&
				(board.positions.get(0).colors[2] == 
				board.positions.get(3).colors[5]) && 
				(board.positions.get(2).colors[3] == 
				board.positions.get(3).colors[0])) {
			return true;
		}

		if ((boardPosition == 4) &&
				(board.positions.get(0).colors[3] == 
				board.positions.get(4).colors[0]) &&
				board.positions.get(3).colors[4] ==
				board.positions.get(4).colors[1]) {
			return true;
		}

		if ((boardPosition == 5) &&
				(board.positions.get(0).colors[4] == 
				board.positions.get(5).colors[1]) &&
				board.positions.get(4).colors[5] ==
				board.positions.get(5).colors[2]) {
			return true;
		}

		//
		if ( (boardPosition == 6) && 
				(board.positions.get(0).colors[5] == board.positions.get(6).colors[2]) &&
				(board.positions.get(5).colors[0] == board.positions.get(6).colors[3]) && 
				(board.positions.get(6).colors[1] == board.positions.get(0).colors[4])) {
			return true;
		}

		return false;
	}


}
