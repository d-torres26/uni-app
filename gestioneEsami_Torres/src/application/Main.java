package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());*/
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Sample.fxml"));
	    	Parent Sample=(Parent)loader.load();
	    	Scene scene=new Scene(Sample);
	    	SampleController controller=loader.getController();
	    	primaryStage.setTitle("Home");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMaxWidth(820);
			primaryStage.setMaxHeight(650);
			primaryStage.setResizable(false);
			controller.loadData();
			controller.update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
