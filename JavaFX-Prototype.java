//	package book_examples;
package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class JavaFX extends Application {

	 private static ArrayList<Color> colors = new ArrayList<Color>(); 
	 private static ArrayList<Polygon> triangles = new ArrayList<Polygon>(); 
	
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
      
		//Method to create 7 Polygons in board shape
		ArrayList<Polygon> polygons = createPolygons();
		
		//Add all 7 polygons from list
		for (int i = 0; i < polygons.size(); i++) {
			root.getChildren().add(polygons.get(i));
		}
		
		//create triangles		
//		ArrayList<Polygon> triangles = createPolygons();
		addTriangles(polygons, root);
		
        /////////////////////////////////////////////////////////////////////
        
        FileIO file = new FileIO();
		//									in.txt out.txt s
		File inFile = new File("src/src/input3.txt");
		

		ArrayList<Hexagon> list = file.readData(inFile);

		Board board = new Board();/////////////////////////
		
		for (int i = 0; i < 7; i ++) {
			board.positions.add(new Hexagon());///////////////////
		}
		
		ArrayList<Hexagon> leftOver = file.readData(inFile);
		
		///////////////////////////////////////////////////////////////////////		
		
		//rotate triangles
			//number triangles
		
		
		
		//rotate polygons
		
		//locking animation
							
		////////////////////////////////////////////////////////////////

		Scene scene = new Scene(root, 850, 650);
	
		primaryStage.setTitle("MyJavaFX"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

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
	
	public void addTriangles(ArrayList<Polygon> polygons, Pane root) {
				
//		ArrayList<Polygon> triangles = new ArrayList<Polygon>();
		
		
//		x                y  center					412.5, 125.0);
//		112.5,           100
//		300+112.5 , 25 + 100
//		2nd last  , 1
		
		for (int i = 0; i < polygons.size(); i++) {
			double centerX = polygons.get(i).getPoints().get(10) + 112.5;
			double centerY = polygons.get(i).getPoints().get(1) + 100;
			
			for (int j = 0; j <= 8; j+=2) {
				
				//Triangle 1
				Polygon triangle = new Polygon(polygons.get(i).getPoints().get(j), 
											   polygons.get(i).getPoints().get(j+1), 
											   polygons.get(i).getPoints().get(j+2), 
											   polygons.get(i).getPoints().get(j+3),  
											   centerX, centerY);
				
				int randomNum = (int)(Math.random()*(5-0+1));  
				triangle.setFill(colors.get(randomNum));
				triangle.setStroke(Color.BLACK);
				triangle.setStrokeWidth(2);
				
				triangles.add(triangle);
				root.getChildren().add(triangle);
				
				
				//add last triangle
				if (j == 0) {

					// x12 , y13
					// x0,  y1
					//center
					Polygon lastTriangle = new Polygon(polygons.get(i).getPoints().get(j), 
												   polygons.get(i).getPoints().get(j+1), 
												   polygons.get(i).getPoints().get(10), 
												   polygons.get(i).getPoints().get(11),  
												   centerX, centerY);
									 
					lastTriangle.setFill(colors.get(randomNum));
					lastTriangle.setStrokeWidth(2);
					
					triangles.add(triangle);
					root.getChildren().add(lastTriangle);
				}				
			}
		}

		//Triangle 1
//		x1, y1, 
//	    x2, y2,
//		center-x, center-y
		
		//Triangle 2
//		x2, y2
//		x3, y3
//		center-x, center-y
	
	}
		
	
}
