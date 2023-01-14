//	package book_examples;
package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import javafx.scene.shape.*;


public class JavaFX2 extends Application {

	private static ArrayList<Color> colors = new ArrayList<Color>();  
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	double newTranslateX, newTranslateY;


	@Override // Override the start method in the Application class

	public void start(Stage primaryStage) {

		//populate colors with colors
		populateColors();

		// Create the Pane
		Pane root = new Pane();        	
		Background background = createBackground();
		root.setBackground(background);          
		root.setPrefSize(850, 650);    // Set the size of the Pane

		//Creating an image (for stars)
		createBackgroundImage(root);     


		/////////////////////////////////////////////////////////////////////

		FileIO file = new FileIO();
		//									in.txt out.txt s
		File inFile = new File("src/src/input4.txt");


		ArrayList<Hexagon> list = file.readData(inFile);

		//Add all 7 polygons from list
		for (int i = 0; i < list.size(); i++) {			
			root.getChildren().add(list.get(i).getTile());
		}

		//create triangles		
		addTriangles(list, root);

		//		Board board = new Board();/////////////////////////
		//		
		//		for (int i = 0; i < 7; i ++) {
		//			board.positions.add(new Hexagon());///////////////////
		//		}
		//		
		//		ArrayList<Hexagon> leftOver = file.readData(inFile);
		//		
		///////////////////////////////////////////////////////////////////////	


		//rotate triangles

		//rotate polygons

		//locking animation

		////////////////////////////////////////////////////////////////

		Scene scene = new Scene(root, 850, 650);

		/////////////////////////////////////////////////////////////////////////////////////
		scene.setOnKeyPressed(e -> txtFieldKeyPressed(e, list));
		////////////////////////////////////////////////////////////////////////////////////////////////
		primaryStage.setTitle("MyJavaFX"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}
	private void txtFieldKeyPressed(KeyEvent e, ArrayList<Hexagon> list) {		
		int firstDigit = 0;
		
		boolean firstDigitPressed = false;
		
		//Check if digit to add to first Digit
		if (e.getCode().isDigitKey()) {
			firstDigit = Integer.parseInt(e.getCode().toString());
			firstDigitPressed = true;
		}

		int secondDigit = 0;
		boolean secondDigitPressed = false;
		
		if (e.getCode() == KeyCode.DIGIT1) {
			System.out.println("1 key was pressed");	
			
			if (firstDigitPressed) {
				secondDigit = 1;
			}			
		}
		if (e.getCode() == KeyCode.DIGIT2) {
			System.out.println("2 pressed");
			if (firstDigitPressed) {
				secondDigit = 2;
			}
		}
		if (e.getCode() == KeyCode.DIGIT3) {
			System.out.println("3 pressed");
			secondDigit = 3;
		}
		if (e.getCode() == KeyCode.DIGIT4) {
			System.out.println("4 pressed");
			secondDigit = 4;
		}
		if (e.getCode() == KeyCode.DIGIT5) {
			System.out.println("5 pressed");
			secondDigit = 5;
		}
		if (e.getCode() == KeyCode.DIGIT6) {
			System.out.println("6 pressed");
			secondDigit = 6;
		}
		if (e.getCode() == KeyCode.DIGIT7) {
			System.out.println("7 pressed");
			secondDigit = 7;
		}
		
		if (secondDigitPressed) {
			switchTiles(firstDigit, secondDigit - 1, list);
		}

	}
	private void switchTiles(int firstDigit, int secondDigit, ArrayList<Hexagon> list) {
		
		char[] firstColors = new char[6];
		for (int j = 0; j < list.get(firstDigit).colors.length; j++) {
			firstColors[j] = list.get(firstDigit).colors[j];
		}
		
		char[] secondColors = new char[6];
		for (int j = 0; j < list.get(secondDigit).colors.length; j++) {
			secondColors[j] = list.get(secondDigit).colors[j];
		}
		
		list.get(firstDigit).setColors(secondColors);
		list.get(secondDigit).setColors(firstColors);
	}
	/**

	 * The main method is only needed for the IDE with limited

	 * JavaFX support. Not needed for running from the command line.

	 */
	public static void main(String[] args) { 
		Application.launch(args);
	}

	//populate colors with colors
	public void populateColors() {
		colors.add(Color.web("#6a8f6b")); // Soft Green
		colors.add(Color.web("#CE2D32")); // Soft red
		colors.add(Color.web("#4973f2")); // Soft blue
		colors.add(Color.web("#f28749")); // Soft orange
		colors.add(Color.web("#9674d4")); // Soft purple
		colors.add(Color.web("#FFCB01")); // Soft yellow
		//		colors.add(Color.web("#1b2040")); // DARK blue
	}

	public Background createBackground() {		
		//Color Fading Gradient for Background
		LinearGradient linearGrad = new LinearGradient(
				0,   // start X 
				0,   // start Y
				0,   // end X
				1, // end Y
				true, // proportional
				CycleMethod.NO_CYCLE, // cycle colors
				// stops
				new Stop(0.1f, Color.rgb(1, 30, 61, 1.0)),		//orange rgb 225, 150, 68
				new Stop(1.0f, Color.rgb(160, 90, 136, 1.0)));


		//Background Color Fill
		BackgroundFill backgroundFill =
				new BackgroundFill(
						linearGrad,
						new CornerRadii(5),
						new Insets(5)
						);

		return new Background(backgroundFill);
	}

	public void createBackgroundImage(Pane root) {
		Image image;
		try {
			image = new Image(new FileInputStream("src/src/Starfield.png"));

			//Setting the image view 
			ImageView imageView = new ImageView(image); 

			//Setting the position of the image  850, 650
			imageView.setX(-250); 
			imageView.setY(-150); 

			//setting the fit height and width of the image view 
			imageView.setFitHeight(1355); 
			imageView.setFitWidth(1350); 

			//Setting the preserve ratio of the image view 
			imageView.setPreserveRatio(true);  

			root.getChildren().add(imageView);  

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}

	public ArrayList<Polygon> createPolygons() {

		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		//+150 x, -25 y
		Polygon polygon = new Polygon( 350.0, 25.0, 
				475.0, 25.0, 
				525.0, 125.0, 
				475.0, 225.0,  
				350.0, 225.0, 
				300.0, 125.0);

		//+205 y axis
		Polygon polygon2 = new Polygon( 350.0, 230.0, 
				475.0, 230.0, 
				525.0, 330.0, 
				475.0, 430.0,  
				350.0, 430.0, 
				300.0, 330.0);

		//+205 y axis
		Polygon polygon3 = new Polygon( 350.0, 435.0, 
				475.0, 435.0, 
				525.0, 535.0, 
				475.0, 635.0,  
				350.0, 635.0, 
				300.0, 535.0);

		// x axis +200 - 20 y axis -100
		Polygon polygon4 = new Polygon( 530.0, 130.0, 
				655.0, 130.0, 
				705.0, 230.0, 
				655.0, 330.0,  
				531.0, 330.0, 
				480.0, 230.0);

		// y axis +205
		Polygon polygon5 = new Polygon( 530.0, 335.0, 
				655.0, 335.0, 
				705.0, 435.0, 
				655.0, 535.0, 	 
				531.0, 535.0, 
				480.0, 435.0);

		// x axis -400 - 20 y axis -100
		Polygon polygon6 = new Polygon( 170.0, 130.0, 
				295.0, 130.0, 
				345.0, 230.0, 
				295.0, 330.0,  
				171.0, 330.0, 
				120.0, 230.0);

		// y axis +205
		Polygon polygon7 = new Polygon( 170.0, 335.0, 
				295.0, 335.0, 
				345.0, 435.0, 
				295.0, 535.0, 	 
				171.0, 535.0, 
				120.0, 435.0);
		polygons.add(polygon);
		polygons.add(polygon2);
		polygons.add(polygon3);
		polygons.add(polygon4);
		polygons.add(polygon5);
		polygons.add(polygon6);
		polygons.add(polygon7);

		return polygons;
	}

	public void addTriangles(ArrayList<Hexagon> list, Pane root) {

		//cycle through tiles
		for (int i = 0; i < list.size(); i++) {
			double centerX = list.get(i).getTile().getPoints().get(10) + 112.5;
			double centerY = list.get(i).getTile().getPoints().get(1) + 100;
			char[] colorList = list.get(i).getColors();

			//add last triangle
			Polygon lastTriangle = new Polygon(list.get(i).getTile().getPoints().get(0), 
					list.get(i).getTile().getPoints().get(1), 
					list.get(i).getTile().getPoints().get(10), 
					list.get(i).getTile().getPoints().get(11),  
					centerX, centerY);

			setFillTriangle(lastTriangle, colorList[5]);
			lastTriangle.setStroke(Color.BLACK);
			lastTriangle.setStrokeWidth(2);
			list.get(i).getTriangles().add(lastTriangle);
			root.getChildren().add(lastTriangle);

			int tileNum = i;
			lastTriangle.addEventHandler(MouseEvent.MOUSE_PRESSED,
					new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					list.get(tileNum).rotate();
					rotateTile(list.get(tileNum).getTriangles(), list.get(tileNum).getColors());
				}
			});

			////////////////////////////////////////////////////////////////////////////////
//			lastTriangle.setOnMousePressed(event -> pressed(event, lastTriangle));
//			lastTriangle.setOnMouseDragged(event -> dragged(event, lastTriangle));
			//			lastTriangle.setOnMouseReleased(event -> released(event, lastTriangle, list, tileNum, root));	


			/////////////////////////////////////////////////////////////////////////////////

			//cycle through tile points and list of colors provided
			for (int j = 0, k = 0; j <= 8 && k < list.get(i).getColors().length; j+=2, k++) {

				//Triangle 1
				Polygon triangle = new Polygon(list.get(i).getTile().getPoints().get(j), 
						list.get(i).getTile().getPoints().get(j+1), 
						list.get(i).getTile().getPoints().get(j+2), 
						list.get(i).getTile().getPoints().get(j+3),  
						centerX, centerY);				
				setFillTriangle(triangle, colorList[k]);				
				triangle.setStroke(Color.BLACK);
				triangle.setStrokeWidth(2);
				list.get(i).getTriangles().add(triangle);
				root.getChildren().add(triangle);


				triangle.addEventHandler(MouseEvent.MOUSE_PRESSED,
						new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						list.get(tileNum).rotate();
						rotateTile(list.get(tileNum).getTriangles(), list.get(tileNum).getColors());				
					}					
				});
				
				
				

				

				////////////////////////////////////////////////////////////////////////////////
				//				triangle.setOnMousePressed(event -> pressed(event, triangle));
				//				triangle.setOnMouseDragged(event -> dragged(event, triangle));
				//				triangle.setOnDragOver(event -> released(event, triangle, list, tileNum, root));	

				//				root.setOnKeyPressed(event -> txtFieldKeyReleased(event));



				/////////////////////////////////////////////////////////////////////////////////

			}
		}
	}

	public void setFillTriangle(Polygon triangle, char color) {
		//[O, R, B, B, O, O]
		//		colors.add(Color.web("#6a8f6b")); // Soft Green
		//		colors.add(Color.web("#CE2D32")); // Soft red
		//		colors.add(Color.web("#4973f2")); // Soft blue
		//		colors.add(Color.web("#f28749")); // Soft orange
		//		colors.add(Color.web("#9674d4")); // Soft purple
		//		colors.add(Color.web("#FFCB01")); // Soft yellow

		if (color == 'G') {
			triangle.setFill(colors.get(0));
		}
		if (color == 'R') {
			triangle.setFill(colors.get(1));
		}
		if (color == 'B') {
			triangle.setFill(colors.get(2));
		}
		if (color == 'O') {
			triangle.setFill(colors.get(3));
		}
		if (color == 'P') {
			triangle.setFill(colors.get(4));
		}
		if (color == 'Y') {
			triangle.setFill(colors.get(5));
		}
	}


	public void rotateTile(ArrayList<Polygon> triangles, char[] cs) {

		/////////////////////////////////////////////////////////////////////////

		for (int i = 0; i < triangles.size(); i++) {
			setFillTriangle(triangles.get(i), cs[i]);
		}

		/////////////////////////////////////////////////////////////////////////

	}
	public void pressed(MouseEvent t, Polygon triangle) {
		//		triangle.setFill(Color.BURLYWOOD);
		//		orgSceneX = t.getSceneX();
		//		orgSceneY = t.getSceneY();
		//		orgTranslateX = ((Polygon)(t.getSource())).getTranslateX();
		//		orgTranslateY = ((Polygon)(t.getSource())).getTranslateY();
	}
	public void dragged(MouseEvent t, Polygon triangle) {
		//
		//		double offsetX = t.getSceneX() - orgSceneX;
		//		double offsetY = t.getSceneY() - orgSceneY;
		//		newTranslateX = orgTranslateX + offsetX;
		//		newTranslateY = orgTranslateY + offsetY;
		//		((Polygon)(t.getSource())).setTranslateX(newTranslateX);
		//		((Polygon)(t.getSource())).setTranslateY(newTranslateY);

	}
	public void released(DragEvent event, Polygon triangle,  ArrayList<Hexagon> list, int tileNum, Pane root) {

		//		((Polygon)(t.getSource())).setTranslateX(orgSceneX);
		//		((Polygon)(t.getSource())).setTranslateY(orgSceneY);

		//if dragged into tile 2, 
		if (event.getSceneX() > 412 && event.getSceneX() < 525) {
			if (event.getSceneY() > 25 && event.getSceneY() < 225) {	

				System.out.println("Tile: " + list.get(tileNum).getTileNum());
				char[] colors1 = list.get(tileNum).getColors();				
				char[] colors2 = list.get(tileNum + 1).getColors();

				list.get(tileNum).setColors(colors2);
				list.get(tileNum + 1).setColors(colors1);



				System.out.println("wroks");
			}
		}


		//if dragged into tile 3 . 300:525.. 230:430
		if (event.getSceneX() > 300 && event.getSceneX() < 525) {
			if (event.getSceneY() > 230 && event.getSceneY() < 430) {	


			}
		}

		//if dragged into tile 4 . 300-525.. 435:635
		if (event.getSceneX() > 300 && event.getSceneX() < 525) {
			if (event.getSceneY() > 435 && event.getSceneY() < 635) {	


			}
		}

		//if dragged into tile 5 . 480-705.. 130:330
		if (event.getSceneX() > 480 && event.getSceneX() < 705) {
			if (event.getSceneY() > 130 && event.getSceneY() < 330) {	


			}
		}

		//if dragged into tile 6 . 480-705.. 335:535
		if (event.getSceneX() > 480 && event.getSceneX() < 705) {
			if (event.getSceneY() > 335 && event.getSceneY() < 535) {	


			}
		}

		//if dragged into tile 7 . 120-345.. 130:330
		if (event.getSceneX() > 120 && event.getSceneX() < 345) {
			if (event.getSceneY() > 130 && event.getSceneY() < 330) {	


			}
		}

		//if dragged into tile  . 120-345.. 335:535
		if (event.getSceneX() > 120 && event.getSceneX() < 345) {
			if (event.getSceneY() > 335 && event.getSceneY() < 535) {	


			}
		}

	}
}
