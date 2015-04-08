package pl.reader.environment;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import pl.reader.textreader.CharacterCounterMessage;

public abstract class Computer implements Runnable {
	
	protected String name;
	protected String type;
	protected long sleepTime = 2000;
	
	protected BlockingQueue<CharacterCounterMessage> myQueue;
	
	public Computer(BlockingQueue queue){
		myQueue = queue;
		type="Computer";
	}
	public Computer(BlockingQueue queue, String n){
		this(queue);
		name = n;
	}
	
	public abstract void init();
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
