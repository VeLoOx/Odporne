package pl.reader.master;

import pl.reader.slave.SlaveComputer;

public class MasterComputer {
	
	public static void main(String[] args){
		
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
		
	}

}
