package pl.reader.environment.master;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import pl.reader.control.IControler;
import pl.reader.environment.Computer;
import pl.reader.textreader.CharacterCounterMessage;
import pl.reader.textreader.MessageStatus;
import pl.reader.utils.CrcClient;

public class MasterComputer extends Computer {

	private int slaveNumber;
	private boolean running = true;
	private CrcClient crcC = new CrcClient();
	private BlockingQueue<CharacterCounterMessage>[] queues;
	private CharacterCounterMessage[] ccmr; //
	private boolean finished = false;
	// private int maybeStoped=0;

	// int[] data1={5};
	// int crcTest;

	private IControler IC;

	public MasterComputer() {
		super();
		type = "MasterComputer";
		// TODO Auto-generated constructor stub
	}

	public MasterComputer(BlockingQueue<CharacterCounterMessage>[] queue) {
		// super(queue);
		type = "MasterComputer";
		ccmr = new CharacterCounterMessage[queue.length];
		queues = queue;
		// TODO Auto-generated constructor stub
	}

	public MasterComputer(BlockingQueue<CharacterCounterMessage>[] queue,
			String n, int slaveNo) {
		this(queue);
		// type = "MasterComputer";
		slaveNumber = slaveNo;
		// TODO Auto-generated constructor stub
	}

	public void init() {

	}

	@Override
	public void run() {
		System.out.println(name + " started");
		int slaveFinished = 0;
		int round = 0;
		/*
		 * try { crcTest=crcC.getCrc16(data1); } catch (IOException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); }
		 * System.out.println("Wartoœc crcTest "+Integer.toHexString(crcTest));
		 */

		try {

			while (running) {

				Thread.sleep(sleepTime);
				String s = "";
				for (int i = 0; i < slaveNumber; i++) {

					if (queues[i].isEmpty()) {
						ccmr[i] = CharacterCounterMessage.createEmptyMessage();
						IC.getMaybeStoped()[i] = true;
					} else {
						ccmr[i] = queues[i].take();
					}
					if (ccmr[i].getStatus() == MessageStatus.LAST) {
						slaveFinished++;
						IC.getMaybeStoped()[i] = true;
					}
					if (slaveFinished == slaveNumber)
						running = false;
					if (ccmr[i].getStatus() != MessageStatus.LAST) {

						if (ccmr[i].getStatus() == MessageStatus.EMPTY) {
							s += "Slave no " + i + " not connected\n";
						} else if (ccmr[i].getCrcCode() != crcC
								.getCrc16(ccmr[i].getCounter())) {
							System.out.println("CRC ERROR");
							/*for(int j=0; j<ccmr[i].getCounter().length;j++){
								ccmr[i].getCounter()[j]=0;
							}*/
							
							s +="\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
							s += ccmr[i].getText()+"\n";
							s += "CRC error, message rejected\n";
							s +="!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
							ccmr[i]=null;
							continue;
						} else {
							System.out.println(ccmr[i].toString());

							System.out.println("CRC from message: "
									+ ccmr[i].getCrcCode());
							System.out.println("CRC calculated "
									+ crcC.getCrc16(ccmr[i].getCounter()));
							/*if (ccmr[i].getCrcCode() != crcC.getCrc16(ccmr[i]
									.getCounter()))
								System.out.println("CRC ERROR");*/
							System.out.println("=======================");
							System.out.println("=======================");
							s += ccmr[i].toString() + " - CRC from message: "
									+ ccmr[i].getCrcCode()
									+ " - CRC calculated "
									+ crcC.getCrc16(ccmr[i].getCounter())
									+ "\n";
						}
					}
				}

				round++;
				int[] tab = majorityVoting(ccmr);
				String sign = Character.getName(('a' + tab[0]));
				System.out.println("Round:" + round + " Most importance Char "
						+ sign.charAt(sign.length() - 1) + ": " + tab[1]
						+ " times - Voting status: " + tab[2]);
				System.out
						.println("************************=======================");
				System.out
						.println("************************=======================");
				IC.inform();
				s += "\n==============================\n";
				s += "Round:" + round + " Most importance Char "
						+ sign.charAt(sign.length() - 1) + ": " + tab[1]
						+ " times - Voting status: " + tab[2] + "\n"+" ALL = "+tab[3] +"  RESULT "+(double)tab[1]/(double)tab[3]+"\n";
				s += "==============================\n";

				int sss = 0;
				for (int ss = 0; ss < slaveNumber; ss++) {
					if (IC.getMaybeStoped()[ss])
						sss++;
				}

				if ((sss) != slaveNumber) {
					IC.addToOUT(s);

				} else {
					// running=false;
					// ss=0;

				}

			}

		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	private CharacterCounterMessage sumCharactersTable(
			CharacterCounterMessage[] m) {
		CharacterCounterMessage tab = CharacterCounterMessage
				.createPartMessage();
		for (int i = 0; i < m.length; i++) {
			if ( m[i]==null||m[i].getStatus() == MessageStatus.EMPTY)
				continue;
			for (int y = 0; y < tab.getCounter().length; y++) {
				tab.getCounter()[y] += m[i].getCounter()[y];
			}
			// fullImportance+=m[i];
		}
		return tab;
	}

	private int[] majorityVoting(CharacterCounterMessage[] m) {
		int fullImportance = 0;
		int biggestImportance = 0;
		int biggestImportanceIndex = 0;

		CharacterCounterMessage sumMessages = sumCharactersTable(m);

		for (int i = 0; i < sumMessages.getCounter().length; i++) {

			fullImportance += sumMessages.getCounter()[i];
			if (biggestImportance < sumMessages.getCounter()[i]) {
				biggestImportance = sumMessages.getCounter()[i];
				biggestImportanceIndex = i;
			}
		}

		int[] ret = new int[4];

		if (fullImportance <= 0)
			return ret;

		ret[0] = biggestImportanceIndex;
		ret[1] = biggestImportance;
		ret[3] = fullImportance;
		
		if (((double)biggestImportance / (double)fullImportance) > 0.5)
			ret[2] = 1;
		else
			ret[2] = 0; // 1 glosowanie udane, 0 w glosowaniu nie bylo zwyciezcy

		return ret;

	}

	public void stop() {
		running = false;
	}

	public int getSlaveNumber() {
		return slaveNumber;
	}

	public void setSlaveNumber(int slaveNumber) {
		this.slaveNumber = slaveNumber;
	}

	public void activeRunning() {
		running = true;
	}

	public void setIC(IControler iC) {
		IC = iC;
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
