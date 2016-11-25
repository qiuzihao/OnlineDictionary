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
	//�����������緭���API�ӿ���
	baiduFanyiAPI.Main main_baiduFanyi=new baiduFanyiAPI.Main();
	jinshanFanyiAPI.JinshanTranslate main_jinshanFanyi=new jinshanFanyiAPI.JinshanTranslate();
	youdaoFanyiAPI.YoudaoTranslate main_youdaoFanyi=new youdaoFanyiAPI.YoudaoTranslate();
	
	//���������������ͨ�ŵ���
	ConmunicateWithServer cws=new ConmunicateWithServer();

	
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
	
	//��������Ƿ�����
	public boolean checkInternet()  
	{
		try {
			if (Main.testConnection()) {
				tfStatus.setText("����������"); 
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfStatus.setText("��������δ���ӣ�");
		return false;
	}
	
	//if the input string is a legal word ,return true; otherwise return false;
	public boolean isLegal(String input) throws Exception
	{
		for (int i=0;i<input.length();i++)
		{ 
			if (!Character.isAlphabetic(input.charAt(i)))
			{
				tfStatus.setText("�������뺬�з���ĸ�ַ���");
				return false;
			}
		}
		return true;
	}
	
	//btSearch  btShare
	@FXML  
	private void btSearchPressed(ActionEvent event) throws Exception {  
		checkInternet();
		
		//�����
		taResult1.setText("");
	    taResult2.setText("");
	    taResult3.setText("");
		
	    String input=tfInput.getText().trim();  
	    if (input.length()==0) {
	    	tfStatus.setText("�����뵥��");
	    	return;
	    }
	    if (!isLegal(input)) return;
	    input=input.toLowerCase();
	    
	    //�ӷ���������û�������Ϣ
	    int[] praises=ConmunicateWithServer.getNumOfPraises(input);
	    
	    //���ݸ�ѡ��͵��޴����ۺϾ�����ʾ����
	    //һ��δѡ
	    if (!cbBaidu.isSelected() && !cbYoudao.isSelected() && !cbJinshan.isSelected()){
	    	tfStatus.setText("Ҫ���ý��������Ҫѡ��һ��������");
	    	return;
	    }
	    //ֻѡ1��
	    else if (cbBaidu.isSelected() && !cbYoudao.isSelected() && !cbJinshan.isSelected()){
	    	taResult1.setText(main_baiduFanyi.baiduSearch(input));
	    	return;
	    }
	    else if (!cbBaidu.isSelected() && cbYoudao.isSelected() && !cbJinshan.isSelected()){
	    	taResult1.setText(YoudaoTranslate.youdaoSearch(input));
	    	return;
	    }
	    else if (!cbBaidu.isSelected() && !cbYoudao.isSelected() && cbJinshan.isSelected()){
	    	taResult1.setText(JinshanTranslate.jinshanSearch(input));
	    	return;
	    }
	    //ѡ��2��
	    else if (cbBaidu.isSelected() && cbYoudao.isSelected() && !cbJinshan.isSelected()){
	    	if (praises[0]>praises[1]){
	    		taResult1.setText(main_baiduFanyi.baiduSearch(input));
	    		taResult2.setText(YoudaoTranslate.youdaoSearch(input));
	    		return;
	    	}
	    	taResult1.setText(YoudaoTranslate.youdaoSearch(input));
    		taResult2.setText(main_baiduFanyi.baiduSearch(input)); 
	    	return;
	    }
	    else if (cbBaidu.isSelected() && !cbYoudao.isSelected() && cbJinshan.isSelected()){
	    	if (praises[0]>praises[2]){
	    		taResult1.setText(main_baiduFanyi.baiduSearch(input));
	    		taResult2.setText(JinshanTranslate.jinshanSearch(input));
	    		return;
	    	}
	    	taResult1.setText(JinshanTranslate.jinshanSearch(input));
    		taResult2.setText(main_baiduFanyi.baiduSearch(input)); 
	    	return;
	    }
	    else if (!cbBaidu.isSelected() && cbYoudao.isSelected() && cbJinshan.isSelected()){
	    	if (praises[1]>praises[2]){
	    		taResult1.setText(YoudaoTranslate.youdaoSearch(input));
	    		taResult2.setText(JinshanTranslate.jinshanSearch(input));
	    		return;
	    	}
	    	taResult1.setText(JinshanTranslate.jinshanSearch(input));
    		taResult2.setText(YoudaoTranslate.youdaoSearch(input)); 
	    	return;
	    }
	    //������ѡ
	    String s1="",s2="",s3="";
	    if (praises[0]>=praises[1]&&praises[1]>=praises[2]) {s1=main_baiduFanyi.baiduSearch(input);  //012
	    	s2=YoudaoTranslate.youdaoSearch(input);s3=JinshanTranslate.jinshanSearch(input);}
	    else if (praises[0]>=praises[1]&&praises[0]>=praises[2]&&praises[2]>=praises[1]) {s1=main_baiduFanyi.baiduSearch(input);//021
    		s2=JinshanTranslate.jinshanSearch(input);s3=YoudaoTranslate.youdaoSearch(input);}
	    
	    else if (praises[1]>=praises[2]&&praises[2]>=praises[0]) {s1=YoudaoTranslate.youdaoSearch(input);//120
    		s2=JinshanTranslate.jinshanSearch(input);s3=main_baiduFanyi.baiduSearch(input);}
	    else if (praises[1]>=praises[2]&&praises[1]>=praises[0]&&praises[0]>=praises[2]) {s1=YoudaoTranslate.youdaoSearch(input);//102
    		s2=main_baiduFanyi.baiduSearch(input);s3=JinshanTranslate.jinshanSearch(input);}
	    
	    else if (praises[2]>=praises[1]&&praises[1]>=praises[0]) {s1=JinshanTranslate.jinshanSearch(input);//210
    		s2=YoudaoTranslate.youdaoSearch(input);s3=main_baiduFanyi.baiduSearch(input);}
	    else if (praises[2]>=praises[1]&&praises[2]>=praises[0]&&praises[0]>=praises[1]) {s1=JinshanTranslate.jinshanSearch(input);//201
    		s2=main_baiduFanyi.baiduSearch(input);s3=YoudaoTranslate.youdaoSearch(input);}
	    taResult1.setText(s1);
	    taResult2.setText(s2);
	    taResult3.setText(s3);
	    
	}  
	@FXML  
	private void btSharePressed(ActionEvent event) {  
	    tfInput.setText("share");  
	}  
		
	//cbBaidu cbYoudao cbJinshan
	@FXML  
	private void cbBaiduChoosed(ActionEvent event) {   
		
	} 
	@FXML  
	private void cbYoudaoChoosed(ActionEvent event) {  
		
	} 
	@FXML  
	private void cbJinshanChoosed(ActionEvent event) {  
		
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