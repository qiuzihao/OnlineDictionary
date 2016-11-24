package application;
 
import java.net.HttpURLConnection;
import java.net.URL;

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
    
    //�ж��Ƿ����������磬���Ϸ���true�����򷵻�false
    public static boolean testConnection() throws Exception {
    	  int status = 404;
    	  try {
    	   URL urlObj = new URL("http://www.baidu.com");
    	   HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
    	   oc.setUseCaches(false);
    	   oc.setConnectTimeout(3000); // ���ó�ʱʱ��
    	   status = oc.getResponseCode();// ����״̬
    	   if (200 == status) {
    	    // 200�������ַ˳����ͨ 
    	    return true;
    	   }
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	   throw e;
    	  } 
    	  return false;
    	 
    }

 
    public static void main(String[] args) {
        launch(args);
    }
}
