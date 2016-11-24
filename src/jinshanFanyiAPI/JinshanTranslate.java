package jinshanFanyiAPI;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; 
import java.net.URL;

public class JinshanTranslate {

    public static void main(String[] args) {
        new ReadByPost().start();
    }
}

class ReadByPost extends Thread{
    @Override
    public void run() {  
    	String keyword="apple";
        String str,result="";
        
        try { 
        	URL url = new URL("http://dict-co.iciba.com/api/dictionary.php?key=AA40B51CD58144F36356B7E9DB19E811&w="+keyword); 
        	            InputStream is = url.openStream(); 
        	            InputStreamReader isr = new InputStreamReader(is,"UTF-8"); 
        	            BufferedReader br = new BufferedReader(isr); 
        	            while((str = br.readLine()) != null) 
        	                result+=str; 
        	            br.close(); 
            } catch(IOException e) { 
        	  System.out.println(e); 
        }
        System.out.println(result);
        
        int explains_index=result.toString().indexOf("acceptation")+12;
        int end_index=result.toString().indexOf("</acceptation>");
        String expla=result.substring(explains_index,end_index);
        System.out.println(expla);
      
        String[] tokens=result.split("orig");
        int num_sentences=(tokens.length-1)/2; 
        
        for (int i=0;i<num_sentences;i++)
        {
        	int en_sentence_index=tokens[2*i+1].indexOf(">")+1;
            int en_end_index=tokens[2*i+1].indexOf(".</");
            String en_sentence=tokens[2*i+1].substring(en_sentence_index,en_end_index);
            System.out.println(en_sentence);
            
            int ch_sentence_index=tokens[2*i+2].indexOf("><")+8;
            int ch_end_index=tokens[2*i+2].indexOf(".</");
            String ch_sentence=tokens[2*i+2].substring(ch_sentence_index,ch_end_index);
            System.out.println(ch_sentence);
        }
        
     }
}