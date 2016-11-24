package baiduFanyiAPI;

import java.io.UnsupportedEncodingException; 

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20161124000032658";
    private static final String SECURITY_KEY = "aAXP7Y_NMYxpdyN6CMUu";

    public static void main(String[] args) throws UnsupportedEncodingException {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "add";
        String resultGet=api.getTransResult(query, "auto", "zh");
        System.out.println(resultGet);
         
        int startPos=resultGet.indexOf("dst")+6;
        int endPos=resultGet.lastIndexOf('"');
        String resultProcessed=convert(resultGet.substring(startPos, endPos)); 
        System.out.println(resultProcessed);
    }
    
    public String baiduSearch(String input) throws UnsupportedEncodingException
    {
    	TransApi api = new TransApi(APP_ID, SECURITY_KEY);
 
        String resultGet=api.getTransResult(input, "auto", "zh"); 
         
        int startPos=resultGet.indexOf("dst")+6;
        int endPos=resultGet.lastIndexOf('"');
        String resultProcessed=convert(resultGet.substring(startPos, endPos)); 
        return resultProcessed+'\n';
    }
    
    public static String convert(String utfString){  
        StringBuilder sb = new StringBuilder();  
        int i = -1;  
        int pos = 0;  
          
        while((i=utfString.indexOf("\\u", pos)) != -1){  
            sb.append(utfString.substring(pos, i));  
            if(i+5 < utfString.length()){  
                pos = i+6;  
                sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
            }  
        }  
          
        return sb.toString();  
    } 

}