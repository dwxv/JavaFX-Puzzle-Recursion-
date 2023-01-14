package src;

import java.math.BigInteger;
import java.util.ArrayList;

public class Board {

	public ArrayList<Hexagon> positions = new ArrayList<Hexagon>(7);
	public BigInteger solutionNumber;
	public int timesThisMethodWasCalled = 1;
	public int spaces = 0;
	
	
	public Board(ArrayList<Hexagon> elements) {
		this.positions = elements;
		this.solutionNumber = new BigInteger("0");
	}

	public Board(Hexagon hex) {
		this.positions.add(hex);
		this.solutionNumber = new BigInteger("0");
	}
	
	public Board() {
		this.solutionNumber = new BigInteger("0");
	}
	
	//Checks to see if the sides match 
	public void printMatch() {
		String output = "";				
		System.out.println(output);
	}
	
	
	//Print Board and tiles
	@Override
	public String toString() {
		String output = "";
		
		for (int i = 0; i < this.positions.size(); i++) {
			output += "Position " + (i) + ": Tile #" + this.positions.get(i).getTileNum();
			for (int j = 0; j < this.positions.get(i).getColors().length; j++) {
				output += "    " + this.positions.get(i).getColors()[j];
			}
			output += "\n";
		}
		
		return output;
	}
	
}
