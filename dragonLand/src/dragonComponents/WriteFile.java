/**
 * 
 */
package dragonComponents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

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
//		this.info = info;
	}

	public static void main(String[] args) {
		BufferedWriter bw = null;
		
		try{
			//will be replaced later with method to make string holding data
			String stuff = "string sdsadas";
			
			File file = getSaveLocation();//new File("C:/Users/Student 8/Desktop/test.txt");
			
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
	
	private static File getSaveLocation() {
		JButton open = new JButton();
		JFileChooser chooser=new JFileChooser();
		chooser.setDialogTitle("Choose a file to save data.");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(chooser.showOpenDialog(open)== JFileChooser.APPROVE_OPTION){
			//Open JFileChooser
			return chooser.getSelectedFile();
		}
		return null;
		
		
//		JFileChooser chooser = new JFileChooser();
//		   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
//		   int result = chooser.showSaveDialog(this);
//
//		   if (result == chooser.APPROVE_OPTION) { 
//		      return chooser.getSelectedFile();
//		   } else {
//		      return null;
		   
	}

}
