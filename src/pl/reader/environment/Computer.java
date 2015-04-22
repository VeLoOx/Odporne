package pl.reader.environment;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import pl.reader.textreader.CharacterCounterMessage;

public abstract class Computer implements Runnable {
	
	protected String name;
	protected String type;
	protected long sleepTime = 1000;
	
	protected BlockingQueue<CharacterCounterMessage> myQueue;
	
	public Computer(){
		
		type="Computer";
	}
	public Computer(BlockingQueue queue, String n){
		myQueue = queue;
		name = n;
	}
	
	
	public abstract void init();
	public abstract void stop();
	public void init(File f){
		
	};
	
	public String getName() {
		return name;
	}
	public String getType() {
		
		return type;
	}
	public BlockingQueue<CharacterCounterMessage> getMyQueue() {
		return myQueue;
	}
	public long getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	
	
}
