package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OnlineDictionaryController implements Initializable
{
	/************************/
	/*        Tag 1         */
	/************************/
	@FXML private Button btSearch;
	@FXML private TextField tfInput;
	@FXML private Button btShare;
	
	@FXML private CheckBox cbBaidu;
	@FXML private CheckBox cbYoudao;
	@FXML private CheckBox cbJinshan;
	
	@FXML private TextField translateResult1;
	@FXML private TextField translateResult2;
	@FXML private TextField translateResult3;
	 
	@FXML private Button praise1;
	@FXML private Button praise2;
	@FXML private Button praise3;
	
	@FXML private ListView<String> similarWordList;
	
	//btSearch  btShare
	@FXML  
	private void btSearchPressed(ActionEvent event) {  
	    tfInput.setText("search");  
	}  
	@FXML  
	private void btSharePressed(ActionEvent event) {  
	    tfInput.setText("share");  
	}  
		
	//cbBaidu cbYoudao cbJinshan
	@FXML  
	private void cbBaiduChoosed(ActionEvent event) {  
	    tfInput.setText("baidu");  
	} 
	@FXML  
	private void cbYoudaoChoosed(ActionEvent event) {  
	    tfInput.setText("youdao");  
	} 
	@FXML  
	private void cbJinshanChoosed(ActionEvent event) {  
	    tfInput.setText("jinshan");  
	} 
		
	//praise1 praise2 praise3
	@FXML  
	private void praise1Pressed(ActionEvent event) {  
	    tfInput.setText("p1");  
	}  
	@FXML  
	private void praise2Pressed(ActionEvent event) {  
	    tfInput.setText("p2");  
	}  
	@FXML  
	private void praise3Pressed(ActionEvent event) {  
	    tfInput.setText("p3");  
	}
	
	
	/*****************************/
	/*         Tag2              */
	/*****************************/
	@FXML private TextField usernameForLogIn;
	@FXML private PasswordField passwordForLogIn;
	@FXML private Button btLogIn;
	@FXML private TextArea usersAtPresent;
	
	@FXML  
	private void btLogInPressed(ActionEvent event) {  
	    tfInput.setText("LogIn");  
	}
	
	
	/*****************************/
	/*         Tag3              */
	/*****************************/
	@FXML private TextField usernameForSignIn;
	@FXML private PasswordField passwordForSignIn;
	@FXML private TextField password2ForSignIn; 
	@FXML private Button btSignIn;
	
	@FXML  
	private void btSignInPressed(ActionEvent event) {  
	    tfInput.setText("SignIn");  
	}
	
	
	/****************************/
	/*     Initialization       */
	/****************************/
	@Override  
	public void initialize(URL url, ResourceBundle rb) {  
	      // TODO  
	}  
	
}