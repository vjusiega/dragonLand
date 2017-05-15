package dragonComponents;

import java.io.IOException;

import javax.swing.JFileChooser;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class ReadFile {


	
	private String path;
	
	public ReadFile(String filePath) {
		path = filePath;
	}
	
	public String[] OpenFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numOfLines = readLines();
		String[] textData= new String[numOfLines];
		
		for(int i=0; i<numOfLines;  i++){
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
	
	public int readLines() throws IOException{
		FileReader fileRead = new FileReader(path);
		BufferedReader bf = new BufferedReader(fileRead);
		
		int numOfLines=0;
		String aLine = "";
		
		while((aLine = bf.readLine())!=null){
			numOfLines++;
		}
		bf.close();
		
		return numOfLines;
	}

}
