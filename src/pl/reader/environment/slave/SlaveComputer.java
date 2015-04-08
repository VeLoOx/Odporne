package pl.reader.environment.slave;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import pl.reader.environment.Computer;
import pl.reader.textreader.TextReader;

public class SlaveComputer extends Computer {
	
	//private long time;
	private boolean running=false;
	private TextReader textReader = null;
	private File textFile;
	//private int loopCounter = 0;
	
	
	public SlaveComputer(BlockingQueue queue, String n) {
		super(queue, n);
		type="SlaveComputer";
		// TODO Auto-generated constructor stub
	}

	public SlaveComputer(BlockingQueue queue) {
		super(queue);
		type="SlaveComputer";
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		textReader = new TextReader(textFile);
		try {
			textReader.prepareFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(File file){
		textFile = file;
		init();
	}

	public void run() {
		// TODO Auto-generated method stub
		/*time = System.currentTimeMillis();
		running = true;
		
		while (running){
			if(System.currentTimeMillis()-time > 2000) 
				{System.out.println("Watek "+name+" - "+time);
				loopCounter++;
				time = System.currentTimeMillis();
				}
		}
		
		System.out.println("Watek "+name+" STOPED");*/
		
	}
	
	public void stop(){
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public TextReader getTextReader() {
		return textReader;
	}

	public void setTextReader(TextReader textReader) {
		this.textReader = textReader;
	}

	public File getTextFile() {
		return textFile;
	}

	public void setTextFile(File textFile) {
		this.textFile = textFile;
	}
	
	
	
	/*public int getLoopCounter(){
		return loopCounter;
	}*/

}