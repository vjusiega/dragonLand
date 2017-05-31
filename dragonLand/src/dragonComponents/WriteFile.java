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
	private static String info;
	private static String fileName;
	
	public WriteFile(String info, String fileName) {
		this.info = info;
		this.fileName = fileName+".txt";
	}

	public static void main(String[] args) {
		BufferedWriter bw = null;
		File file = getSaveLocation();
		
		try{
			bw = new BufferedWriter(new FileWriter(file));
			//will be replaced later with method to make string holding data
			
			//new File("C:/Users/Student 8/Desktop/test.txt");
			
			if(!file.exists())file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(info);
			
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
		File file = null;
		
		JButton open = new JButton();
		final JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(open);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}
		
//		JButton open = new JButton();
//		JFileChooser chooser = new JFileChooser();
//		chooser.setCurrentDirectory(new java.io.File("."));
//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		chooser.setDialogTitle("Choose a directory to save data.");
//		chooser.setAcceptAllFileFilterUsed(false);
//		if(chooser.showOpenDialog(open)== JFileChooser.APPROVE_OPTION){
//			//Open JFileChooser
//			file= chooser.getSelectedFile();
//			System.out.println("getCurrentDirectory(): "+chooser.getCurrentDirectory());
//			System.out.println("getSelectedFile() : "+chooser.getSelectedFile());
//		}
		
		return file;
		   
	}

}
