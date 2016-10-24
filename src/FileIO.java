import java.io.*;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileIO {
	
	/*
	 * Singleton Class
	 */
	private static FileIO  fileIO   = new FileIO();	
	private Document doc;
	Scanner scanner;
	String content;
	
	//CONSTRUCTORS
	private FileIO(){
		
	}
	public static FileIO getFileIO() {
		return fileIO;
	}
	
	//FUNCTIONS
	public String fileToString(String fileName) throws IOException{
		/*
		 * dosya adýný alýp, bu dosyanýn içerðini string olarak döndüren method
		 */
		FileReader fileReader = null;
	    String content = null;
	    
	    try {
	         fileReader = new FileReader("C:\\Users\\celalkd\\workspace_\\WikiMining\\resources\\"+fileName+".txt");
	         int c;	         
	         while ((c = fileReader.read()) != -1) {	        	 
	        	 if(content==null){
	        		 content = Character.toString ((char) c);
	        	 }else content += Character.toString ((char) c);
	         }
	      }finally {
	         if (fileReader != null) {
	        	 fileReader.close();
	         }	         
	    }
	    return content;
	}
	public boolean check404(String URL){
		setDoc(null);		
		try {
			setDoc(Jsoup.connect(URL).get());//404 gelirse catche düþer
		} catch (IOException e) {
			System.out.println(" *404: "+URL);
			return false;
		}	
		System.out.println(" *checked: "+URL);
    	return true;
	}
	
	//GETTER_SETTER METHODLARI
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
}
