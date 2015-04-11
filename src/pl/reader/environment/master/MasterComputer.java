package pl.reader.environment.master;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import pl.reader.environment.Computer;
import pl.reader.textreader.CharacterCounterMessage;
import pl.reader.textreader.MessageStatus;
import pl.reader.utils.CrcClient;

public class MasterComputer extends Computer {

	private int slaveNumber;
	private boolean running = true;
	private CrcClient crcC= new CrcClient();
	//int[] data1={5};
	//int crcTest;

	public MasterComputer(BlockingQueue queue, String n) {
		super(queue, n);
		type = "MasterComputer";
		// TODO Auto-generated constructor stub
	}

	public MasterComputer(BlockingQueue queue) {
		super(queue);
		type = "MasterComputer";
		// TODO Auto-generated constructor stub
	}

	public MasterComputer(BlockingQueue queue, String n, int slaveNo) {
		super(queue, n);
		type = "MasterComputer";
		slaveNumber = slaveNo;
		// TODO Auto-generated constructor stub
	}

	public void init() {

	}

	@Override
	public void run() {
		System.out.println(name + " started");
		int slaveFinished = 0;
		
/*		try {
			crcTest=crcC.getCrc16(data1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Wartoœc crcTest "+Integer.toHexString(crcTest));*/
		
		try {

			while (running) {
				Thread.sleep(sleepTime);
				CharacterCounterMessage ccm = myQueue.take();
				if (ccm.getStatus() == MessageStatus.LAST)
					slaveFinished++;
				if (slaveFinished == slaveNumber)
					running = false;
				System.out.println(ccm.toString());
				System.out.println("CRC: "+ccm.getCrcCode());
				System.out.println("=======================");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	public int getSlaveNumber() {
		return slaveNumber;
	}

	public void setSlaveNumber(int slaveNumber) {
		this.slaveNumber = slaveNumber;
	}

	/*
	 * public static void main(String[] args){
	 * 
	 * SlaveComputer a = new SlaveComputer("A"); SlaveComputer b = new
	 * SlaveComputer("B");
	 * 
	 * Thread ta = new Thread (a); Thread tb = new Thread (b); //a.run();
	 * //b.run(); ta.start(); tb.start();
	 * 
	 * int end = 0;
	 * 
	 * while(true){
	 * 
	 * if(a.getLoopCounter()==3){
	 * 
	 * try { a.stop(); ta.join(); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } //end++; }
	 * 
	 * if(b.getLoopCounter()==5){
	 * 
	 * try { b.stop(); tb.join(); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } //end++; }
	 * 
	 * if(!ta.isAlive() && !tb.isAlive()){
	 * System.out.println("Threads stoped - end of programm"); break; } }
	 * 
	 * }
	 */

}
