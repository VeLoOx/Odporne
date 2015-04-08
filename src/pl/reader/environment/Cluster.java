package pl.reader.environment;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import pl.reader.environment.master.MasterComputer;
import pl.reader.environment.slave.SlaveComputer;
import pl.reader.textreader.CharacterCounterMessage;

public class Cluster {
	
	int slaveNumber = 4;
	
	private Thread master;
	private Thread[] slaves;
	
	private Computer masterC;
	private Computer[] slavesC;
	
	private BlockingQueue<CharacterCounterMessage> messageQueue;
	
	public Cluster(){
		this(4);
	}
	
	public Cluster(int slaveNuber){
		this.slaveNumber = slaveNuber;
		messageQueue = new ArrayBlockingQueue<>(2048);
		slaves = new Thread[slaveNumber];
		slavesC = new SlaveComputer[slaveNumber];
		
		masterC = new MasterComputer(messageQueue, "MASTER_COMPUTER");
		master = new Thread(masterC);
		
		//initialization slaves
		for(int i=0;i<slaveNumber;i++){
			slavesC[i] = new SlaveComputer(messageQueue, "SLAVE_COMPUTER No "+i);
			slaves[i] = new Thread(slavesC[i]);
		}
		
	}
	
	public void initSlaves(File...f){
		for(int i=0;i<slaveNumber;i++){
			slavesC[i].init(f[i]);
		}
	}
	
	public void startComputers(){
		master.start();
		
		for(int i=0;i<slaveNumber;i++){
			slaves[i].start();
		}
	}
	
	//private
	
	public static void main(String[] args){
		File f = new File("D:\\tmp.txt");
		
		Cluster cluster = new Cluster(1);
		cluster.initSlaves(f);
		cluster.startComputers();
		
		
	}

}
