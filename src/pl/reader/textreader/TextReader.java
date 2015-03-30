package pl.reader.textreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
	
	private File textFile;
	private FileReader textFileReader;
	private BufferedReader in;
	
	private boolean lastLine=false; //znacznik ze byla juz ostatnia linia
	
	public TextReader(){
		
	}
	public TextReader(File f){
		textFile = f;
	}
	
	
	public void prepareFile() throws IOException{
		textFileReader = new FileReader(textFile);
		in = new BufferedReader(textFileReader);
	}
	
	
	public CharacterCounterMessage readNextLine(){
		String line;
		
		try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			lastLine = true;
			return CharacterCounterMessage.createEmptyMessage();
		}
		
		if(line==null){
			lastLine = true;
			return CharacterCounterMessage.createLastMessage();
		}
		
		CharacterCounterMessage message = CharacterCounterMessage.createPartMessage();
		
		int[] counter = message.getCounter();
		for(char c:line.toCharArray()){
			if(c>='a' && c<='z') counter[c-'a']++;
		}
				
		message.setCounter(counter);
		
		return message;
	}
	
	public static void main(String[] args){
		File f = new File("D:\\tmp.txt");
		
		TextReader tr = new TextReader(f);
		try {
			tr.prepareFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CharacterCounterMessage mess = tr.readNextLine();
		System.out.println(mess.toString());
		
		mess = tr.readNextLine();
		System.out.println(mess.toString());
		
		
		
	}

}
