package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class OnlineDictionaryController implements Initializable
{ 
	private LocalDictionaryEntry[] dictionary=new LocalDictionaryEntry[40000];    //存储所有本地单词的对象数组 
	private int numberOfEntry=0;                    //词条总数
	
	//三个用于网络翻译的API接口类
	baiduFanyiAPI.Main main_baiduFanyi=new baiduFanyiAPI.Main();
	jinshanFanyiAPI.JinshanTranslate main_jinshanFanyi=new jinshanFanyiAPI.JinshanTranslate();
	youdaoFanyiAPI.YoudaoTranslate main_youdaoFanyi=new youdaoFanyiAPI.YoudaoTranslate();
	
	//负责与服务器进行通信的类
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
/*	
	//读入本地词典 
	public void readInLocalDictionary()
	{
		try {
			myDictionary=new DictionaryData("d:/dictionary.txt");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return;
		}
	} 
*/

	//如果输入合法，返回真，否则返回假
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
		//读入本地词典并检查网络连接
//		readInLocalDictionary();
		checkInternet(); 
		   
		//先清空
		taResult1.setText("");
	    taResult2.setText("");
	    taResult3.setText("");
		
	    String input=tfInput.getText().trim();  
	    if (input.length()==0) {
	    	tfStatus.setText("请输入单词");
	    	return;
	    }
	    if (!isLegal(input)) return;
	    input=input.toLowerCase();
	    
	    //从服务器获得用户点赞信息
	    int[] praises=ConmunicateWithServer.getNumOfPraises(input);
	    
	    //根据复选框和点赞次数综合决定显示次序
	    //一个未选
	    if (!cbBaidu.isSelected() && !cbYoudao.isSelected() && !cbJinshan.isSelected()){
	    	tfStatus.setText("要想获得结果，至少要选择一个翻译结果");
	    	return;
	    }
	    //只选1个
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
	    //选了2个
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
	    //三个都选
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
	
	public int findAssociationWord(String[] result,String entry)
	{ 
		int idx=0; 
		for (int i=0;i<numberOfEntry && idx<500;i++)
			if ((dictionary[i].getWord()).startsWith(entry)){
				result[idx++]=dictionary[i].getWord();
			} 
		return idx;
	}
	
	public class InputListener implements Runnable
	{ 
		private String[] words=new String[500];  //联想的单词 
		 
		//创建联想框事件监听 
		public void run()
		{ 
			tfInput.textProperty().addListener(new InvalidationListener(){
				public void invalidated(Observable ov){   
					findAssociationWord(words,tfInput.getText()); 
					similarWordList.setItems(FXCollections.observableArrayList(words));  
				}
			});
		}
	} 
	
	@Override  
	public void initialize(URL url, ResourceBundle rb) {  
		 
		//读入本地词典文件
	    //本地词典
		Scanner input = null;
		try {
			input = new Scanner(new File("d:/dictionary.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		int i=0;
		while (input.hasNext())
		{
			String oneEntry = input.nextLine(); 
			String[] details = oneEntry.split("\t"); 
			
			if (details[0].equals("ID")) continue;
			dictionary[i]=new LocalDictionaryEntry(Integer.parseInt(details[0]),
			details[1],details[2],details[3]); 
			i++; 
			numberOfEntry++;
		}  
		
		//增加对输入框的监听
		Runnable inputListener=new InputListener();
		Thread threadInputListener=new Thread(inputListener);  
		threadInputListener.start();  //启动监听线程 
	}   
}