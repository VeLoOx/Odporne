package pl.reader.slave;

public class SlaveComputer implements Runnable {
	
	private long time;
	private boolean running=false;
	private int loopCounter = 0;
	
	private String name;
	
	public SlaveComputer(String n){
		name = n;
	}

	public void run() {
		// TODO Auto-generated method stub
		time = System.currentTimeMillis();
		running = true;
		
		while (running){
			if(System.currentTimeMillis()-time > 2000) 
				{System.out.println("Watek "+name+" - "+time);
				loopCounter++;
				time = System.currentTimeMillis();
				}
		}
		
		System.out.println("Watek "+name+" STOPED");
		
	}
	
	public void stop(){
		running = false;
	}
	
	public int getLoopCounter(){
		return loopCounter;
	}

}
