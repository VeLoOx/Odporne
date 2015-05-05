package pl.reader.environment;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import pl.reader.control.IControler;
import pl.reader.environment.master.MasterComputer;
import pl.reader.environment.slave.SlaveComputer;
import pl.reader.textreader.CharacterCounterMessage;

public class Cluster {
	
	int slaveNumber = 4;
	
	private Thread masterThread;
	private Thread[] slavesThreads;
	
	private Computer masterC;
	private Computer[] slavesC;
	
	private int activeSlave=0;
	private IControler IC;
	
	private BlockingQueue<CharacterCounterMessage>[] messageQueue;
	
	public Cluster(){
		this(4);
	}
	
	public Cluster(int slaveNuber){
		this.slaveNumber = slaveNuber;
		messageQueue = new ArrayBlockingQueue[slaveNumber];
		slavesThreads = new Thread[slaveNumber];
		slavesC = new SlaveComputer[slaveNumber];
		
		masterC = new MasterComputer(messageQueue, "MASTER_COMPUTER",slaveNumber);
		masterThread = new Thread(masterC);
		
		//initialization slaves
		for(int i=0;i<slaveNumber;i++){
			messageQueue[i] = new ArrayBlockingQueue<>(1024); 
			slavesC[i] = new SlaveComputer(messageQueue[i], "SLAVE_COMPUTER No. "+i);
			slavesThreads[i] = new Thread(slavesC[i]);
			//slavesThreads[i].suspend();
			
		}
		
	}
	
	public void initSlaves(File...f){
		for(int i=0;i<slaveNumber;i++){
			slavesC[i].init(f[i]);
			
		}
	}
	
	public void startComputers(){
		//((MasterComputer)masterC).activeRunning();
		
		
		masterThread.start();
		
		for(int i=0;i<slaveNumber;i++){
			slavesThreads[i].start();
			activeSlave++;
			IC.getMaybeStoped()[i]=false;
		}
	}
	
	public void pauseComputers(){
		masterThread.suspend();
		for(int i=0;i<slaveNumber;i++){
			if(IC.getMaybeStoped()[i]) continue;
			slavesThreads[i].suspend();
			activeSlave--;
			IC.getMaybeStoped()[i]=true;
			}
	}
	
	public void pauseSlave(int i){
		if(IC.getMaybeStoped()[i]) return;
		slavesThreads[i].suspend();
		activeSlave--;
		IC.getMaybeStoped()[i]=true;
	}
	
	public void resumeSlave(int i){
		if(!IC.getMaybeStoped()[i]) return;
		slavesThreads[i].resume();
		activeSlave++;
		IC.getMaybeStoped()[i]=false;
	}
	
	public void resumeComputers(){
		masterThread.resume();
		for(int i=0;i<slaveNumber;i++){
			if(IC.getMaybeStoped()[i]){
			slavesThreads[i].resume();
			activeSlave++;
			IC.getMaybeStoped()[i]=false;
			}
		}
	}
	
	public void stopComputers(){
		try {
			//masterThread.resume();
			
			//masterThread.destroy();
			for(int i=0;i<slaveNumber;i++){
				if(IC.getMaybeStoped()[i]){
					slavesThreads[i].resume();
					
				}
				slavesC[i].stop();
				slavesThreads[i].join();
				//slavesThreads[i].destroy();
				activeSlave--;
				IC.getMaybeStoped()[i]=true;
				masterC.stop();
				masterThread.join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//private
	
	public Computer getMasterC() {
		return masterC;
	}
	
	public int getActiveSlave(){
		return activeSlave;
	}
	public void setIC(IControler i){
		IC=i;
	}

	public static void main(String[] args){
		File [] files = new File[4];
		File f = new File("D:\\tmp1.txt");
		files[0]=new File("D:\\tmp1.txt");
		files[1]=new File("D:\\tmp2.txt");
		files[2]=new File("D:\\tmp3.txt");
		files[3]=new File("D:\\tmp4.txt");
		
		Cluster cluster = new Cluster(4);
		cluster.initSlaves(files);
		cluster.startComputers();
		
		
	}

}
