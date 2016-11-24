package application;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
 
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("OnlineDictionaryUI.fxml"));
        Scene scene = new Scene(root, 750, 480);
        stage.setTitle("OnlineDictionary");
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
