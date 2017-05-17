/**
 * 
 */
package dragonComponents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jenniber Franco
 *
 */
public class WriteFile {

	/**
	 * 
	 */
	private String info;
	
	public WriteFile() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		BufferedWriter bw = null;
		
		try{
			//will be replaced later with method to make string holding data
			String stuff = "string sdsadas";
			
			File file = new File("C:/Users/Student 8/Desktop/test.txt");
			
			if(!file.exists())file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(stuff);
			
			System.out.println("File written Successfully");
			
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
			try{
				if(bw!=null)bw.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedWriter"+ex);
			}
		}
	}

}
