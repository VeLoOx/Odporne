package pl.reader.environment.master;

import java.util.concurrent.BlockingQueue;

import pl.reader.environment.Computer;


public class MasterComputer extends Computer {

	
	
	
	
	public MasterComputer(BlockingQueue queue, String n) {
		super(queue, n);
		type="MasterComputer";
		// TODO Auto-generated constructor stub
	}

	public MasterComputer(BlockingQueue queue) {
		super(queue);
		type="MasterComputer";
		// TODO Auto-generated constructor stub
	}
	
	public void init(){}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*public static void main(String[] args){
		
		SlaveComputer a = new SlaveComputer("A");
		SlaveComputer b =  new SlaveComputer("B");
		
		Thread ta = new Thread (a);
		Thread tb = new Thread (b);
		//a.run();
		//b.run();
		ta.start();
		tb.start();
		
		int end = 0;
		
		while(true){
			
			if(a.getLoopCounter()==3){
				
				try {
					a.stop();
					ta.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//end++;
			}
			
			if(b.getLoopCounter()==5){
				
				try {
					b.stop();
					tb.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//end++;
			}
			
			if(!ta.isAlive() && !tb.isAlive()){
				System.out.println("Threads stoped - end of programm");
				break;
			}
		}
		
	}*/

}
