package src;

import java.util.ArrayList;

import javafx.scene.shape.Polygon;

public class Hexagon {

	//Data Field
	public char[] colors = new char[6];
	public int tileNum;
	public int positionNumber;
	public Polygon tile;
	public ArrayList<Polygon> triangles;

	//Constructor
	public Hexagon(char[] colors, int tileNum) {
		this.colors = colors;
		this.tileNum = tileNum;
		this.tile = createTile(this.tileNum);
		this.triangles = new ArrayList<>();
	}

	//Copy constructor
	public Hexagon(Hexagon copy) {
		for (int i = 0; i < this.colors.length; i++) {
			this.colors[i] = copy.colors[i];
		}
		this.tileNum = copy.tileNum;
		this.positionNumber = copy.positionNumber;
		this.tile = copy.tile;
	}

	public Hexagon() {

	}


	//Getters and Setters	


	public ArrayList<Polygon> getTriangles() {
		return triangles;
	}

	public void setTriangles(ArrayList<Polygon> triangles) {
		this.triangles = triangles;
	}

	public void setTile(Polygon tile) {
		this.tile = tile;
	}
	
	public int getTileNum() {
		return tileNum;
	}

	public char[] getColors() {
		return colors;
	}

	public Polygon getTile() {
		return tile;
	}

	public void setColors(char[] colors) {
		this.colors = colors;
	}

	public void setTileNum(int tileNum) {
		this.tileNum = tileNum;
	}

	public int getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(int positionNumber) {
		this.positionNumber = positionNumber;
	}

	//rotate the colors of hexagon (Shift right by 1)
	public void rotate() {

		char temp = this.colors[colors.length - 1];

		//shift right		
		for (int i = this.colors.length - 2; i >= 0; i--) {
			this.colors[i + 1] = this.colors[i];
		}

		this.colors[0] = temp;		
	}
	

	@Override
	public String toString() {
		String output = "";

		output += "Tile #" + this.tileNum + "   ";

		for (int i = 0; i < this.colors.length; i++) {
			output += this.colors[i] + "   ";
		}


		return output;
	}

	@Override
	public boolean equals(Object i2) {
		if (!(i2 instanceof Hexagon)) {
			return false;
		}

		Hexagon temp = (Hexagon)i2;



		boolean numEqual = this.tileNum == temp.tileNum;

		boolean colorEqual = true;

		for (int i = 0; i < this.colors.length; i++ ) {
			colorEqual = this.colors[i] == temp.colors[i];
		}



		return numEqual && colorEqual;
	}

	public void copyColors(Hexagon hexagon) {
		this.colors = new char[6];
		for (int i = 0; i < this.colors.length; i++) {
			this.colors[i] = hexagon.colors[i];
		}
	}

	public Polygon createTile(int tileNum) {
		if (tileNum == 1) {					
			return new Polygon( 350.0, 230.0, 
					475.0, 230.0, 
					525.0, 330.0, 
					475.0, 430.0,  
					350.0, 430.0, 
					300.0, 330.0);
		}

		if (tileNum == 2) {
			return new Polygon( 350.0, 25.0, 
					475.0, 25.0, 
					525.0, 125.0, 
					475.0, 225.0,  
					350.0, 225.0, 
					300.0, 125.0);
		}

		if (tileNum == 3) {                         //tile 2
			return new Polygon( 530.0, 130.0, 
					655.0, 130.0, 
					705.0, 230.0, 
					655.0, 330.0,  
					531.0, 330.0, 
					480.0, 230.0);
		}

		if (tileNum == 4) {                         //tile 3
			
		return new Polygon( 530.0, 335.0, 
				655.0, 335.0, 
				705.0, 435.0, 
				655.0, 535.0, 	 
				531.0, 535.0, 
				480.0, 435.0);
	}

		if (tileNum == 5) {                        //tile 4
			
		return new Polygon( 350.0, 435.0, 
				475.0, 435.0, 
				525.0, 535.0, 
				475.0, 635.0,  
				350.0, 635.0, 
				300.0, 535.0);
	}

		if (tileNum == 6) {					
			return new Polygon( 170.0, 335.0, 
					295.0, 335.0, 
					345.0, 435.0, 
					295.0, 535.0, 	 
					171.0, 535.0, 
					120.0, 435.0);
		}
		if (tileNum == 7) {
			return new Polygon( 170.0, 130.0, 
					295.0, 130.0, 
					345.0, 230.0, 
					295.0, 330.0,  
					171.0, 330.0, 
					120.0, 230.0);
		}
		return null;

	}


}
