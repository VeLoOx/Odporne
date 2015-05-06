package pl.reader.environment.slave;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import pl.reader.environment.Computer;
import pl.reader.textreader.CharacterCounterMessage;
import pl.reader.textreader.MessageStatus;
import pl.reader.textreader.TextReader;
import pl.reader.utils.CrcClient;

public class SlaveComputer extends Computer {
	
	//private long time;
	private boolean running=true;
	private TextReader textReader = null;
	private File textFile;
	//private int loopCounter = 0;
	private CrcClient crcC= new CrcClient();
	private boolean falseData=false;
	private boolean chaos=false;
	private int chaosElement=0;
	private int chaosNumber=0;
	
	



	public SlaveComputer(BlockingQueue queue, String n) {
		super(queue, n);
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
		System.out.println(name+" started");
		createChaos();
		while(running){
		
		CharacterCounterMessage ccm = textReader.readNextLine();
		if(ccm.getStatus()==MessageStatus.LAST){
			running=false;
			//return;
		}
		try {
			if(chaos){
				
				int[] table = new int [26];
				table[chaosElement]=chaosNumber;
				ccm.setCounter(table);
				/*for(int i =0; i<ccm.getCounter().length;i++){
					ccm.getCounter()
				}*/
			}
			ccm.setCrcCode(crcC.getCrc16(ccm.getCounter()));
			ccm.setText(this.getName());
			if(falseData){
				ccm.falseData();
			}
			
			myQueue.put(ccm);
			Thread.sleep(sleepTime);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
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
	
	public boolean isFalseData() {
		return falseData;
	}

	public void setFalseData() {
		falseData = !falseData;
	}



	public boolean isChaos() {
		return chaos;
	}



	public void setChaos() {
		chaos=!chaos;
	}
	
	public void createChaos(){
		Random rand = new Random();
		 
		setChaosElement(rand.nextInt((25 - 1) + 1) + 1);
		setChaosNumber(rand.nextInt((123 - 0) + 1) + 0);
		System.out.println("ChaosElement: "+ chaosElement);
		System.out.println("ChaosNumber: "+ chaosNumber);
		
	}

	public int getChaosElement() {
		return chaosElement;
	}

	public void setChaosElement(int chaosElement) {
		this.chaosElement = chaosElement;
	}

	public int getChaosNumber() {
		return chaosNumber;
	}

	public void setChaosNumber(int chaosNumber) {
		this.chaosNumber = chaosNumber;
	}
	
	/*public int getLoopCounter(){
		return loopCounter;
	}*/

}
