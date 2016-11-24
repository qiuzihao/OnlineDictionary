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
import jinshanFanyiAPI.JinshanTranslate;
import youdaoFanyiAPI.YoudaoTranslate;


public class OnlineDictionaryController implements Initializable
{ 
	
	baiduFanyiAPI.Main main_baiduFanyi=new baiduFanyiAPI.Main();
	jinshanFanyiAPI.JinshanTranslate main_jinshanFanyi=new jinshanFanyiAPI.JinshanTranslate();
	youdaoFanyiAPI.YoudaoTranslate main_youdaoFanyi=new youdaoFanyiAPI.YoudaoTranslate();
	
	
	/****************************/
	/*           Tag 1          */
	/****************************/
	@FXML private Button btSearch;
	@FXML private TextField tfInput;
	@FXML private Button btShare;
	
	@FXML private CheckBox cbBaidu;
	@FXML private CheckBox cbYoudao;
	@FXML private CheckBox cbJinshan;
	
	@FXML private TextArea taResult1;
	@FXML private TextArea taResult2;
	@FXML private TextArea taResult3;
	
	@FXML private TextField tfStatus;
	 
	@FXML private Button praise1;
	@FXML private Button praise2;
	@FXML private Button praise3;
	
	@FXML private ListView<String> similarWordList;
	
	//检查网络是否连接
	public boolean checkInternet()  
	{
		try {
			if (Main.testConnection()) {
				tfStatus.setText("网络已连接"); 
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfStatus.setText("错误：网络未连接！");
		return false;
	}
	
	//if the input string is a legal word ,return true; otherwise return false;
	public boolean isLegal(String input) throws Exception
	{
		for (int i=0;i<input.length();i++)
		{ 
			if (!Character.isAlphabetic(input.charAt(i)))
			{
				tfStatus.setText("错误：输入含有非字母字符！");
				return false;
			}
		}
		return true;
	}
	
	//btSearch  btShare
	@FXML  
	private void btSearchPressed(ActionEvent event) throws Exception {  
		checkInternet();
	    String input=tfInput.getText().trim();  
	    if (input.length()==0) return;
	    if (!isLegal(input)) return;
	    input=input.toLowerCase();
	    taResult1.setText(main_baiduFanyi.baiduSearch(input));
	    taResult2.setText(YoudaoTranslate.youdaoSearch(input));
	    taResult3.setText(JinshanTranslate.jinshanSearch(input));
	    
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