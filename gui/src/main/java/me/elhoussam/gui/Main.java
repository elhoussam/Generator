package me.elhoussam.gui;

	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import static me.elhoussam.mvn.generator.NumToLet.print ;
 
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			String a = Main.class.getResource("/view/Sample.fxml").toString(); 
			print( a +"\n");
			BorderPane root = (BorderPane)FXMLLoader.load(Main.class.getResource("/view/Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Nombre Generator");
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> { print("\nExit ...\n"); System.exit(0); });			
			primaryStage.setResizable(false);		
			primaryStage.setAlwaysOnTop(true);	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
