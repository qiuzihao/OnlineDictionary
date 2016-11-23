package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OnlineDictionaryController implements Initializable
{
	@FXML
	private Button btSearch;
	
	@FXML
	private TextField tfInput;
	
	@FXML  
    private void btSearchPressed(ActionEvent event) {  
        tfInput.setText("very good");  
    }  
	
	@Override  
	public void initialize(URL url, ResourceBundle rb) {  
	      // TODO  
	}  
	
}