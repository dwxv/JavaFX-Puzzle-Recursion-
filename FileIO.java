package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	
	public ArrayList<Hexagon> readData(File inFile) {
		ArrayList<Hexagon> list = new ArrayList<>();
		
		try {
			Scanner read = new Scanner(inFile);
			
			while(read.hasNextLine()) {
				String nextLine = read.nextLine();
				String[] tokens = nextLine.split("Tile ");
				
				
				//Every Line broken down like this:
				//Before: "Tile 1: A,A,A,A,A,A"
				//After: 	|	 |      |
				//			|	 |      |
				//		  "Tile "|      |
				//				"1: "   |
				//				   "A,A,A,A,A,A"
				switch(tokens[1].substring(0, 3)) {
				
					case "1: ":
						//CODE
						 String[] temp1 = tokens[1].split(",");
						 char[] colors1 = new char[temp1.length];
						 colors1[0] = temp1[0].charAt(3);
						 
						 for (int i = 1; i < temp1.length; i++) {
								colors1[i] = temp1[i].charAt(0);
						 }
						 
						 list.add(0, new Hexagon(colors1, 1));
						break;
					case "2: ":
						//CODE
						 String[] temp2 = tokens[1].split(",");
						 char[] colors2 = new char[temp2.length];
						 colors2[0] = temp2[0].charAt(3);
						 
						 for (int i = 1; i < temp2.length; i++) {
								colors2[i] = temp2[i].charAt(0);
						 }
						 
						 list.add(1, new Hexagon(colors2, 2));
						break;
					case "3: ":
						//CODE
						 String[] temp3 = tokens[1].split(",");
						 char[] colors3 = new char[temp3.length];
						 colors3[0] = temp3[0].charAt(3);
						 
						 for (int i = 1; i < temp3.length; i++) {
								colors3[i] = temp3[i].charAt(0);
						 }
						 
						 list.add(2, new Hexagon(colors3, 3));
						break;
						
					case "4: ":
						//CODE
						 String[] temp4 = tokens[1].split(",");
						 char[] colors4 = new char[temp4.length];
						 colors4[0] = temp4[0].charAt(3);
						 
						 for (int i = 1; i < temp4.length; i++) {
								colors4[i] = temp4[i].charAt(0);
						 }
						 
						 list.add(3, new Hexagon(colors4, 4));
						break;
					
					case "5: ":
						//CODE
						 String[] temp5 = tokens[1].split(",");
						 char[] colors5 = new char[temp5.length];
						 colors5[0] = temp5[0].charAt(3);
						 
						 for (int i = 1; i < temp5.length; i++) {
								colors5[i] = temp5[i].charAt(0);
						 }
						 
						 list.add(4, new Hexagon(colors5, 5));
						break;	
						
					case "6: ":
						//CODE
						 String[] temp6 = tokens[1].split(",");
						 char[] colors6 = new char[temp6.length];
						 colors6[0] = temp6[0].charAt(3);
						 
						 for (int i = 1; i < temp6.length; i++) {
								colors6[i] = temp6[i].charAt(0);
						 }
						 
						 list.add(5, new Hexagon(colors6, 6));
						break;
					case "7: ":
						//CODE
						 String[] temp7 = tokens[1].split(",");
						 char[] colors7 = new char[temp7.length];
						 colors7[0] = temp7[0].charAt(3);
						 
						 
						 for (int i = 1; i < temp7.length; i++) {
								colors7[i] = temp7[i].charAt(0);
						 }
						 
						 list.add(6, new Hexagon(colors7, 7));
						break;
					default:
						//nothing
						break;
				}
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return list;
	}

}
