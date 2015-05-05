package pl.reader.control;

import java.io.File;

import pl.reader.environment.Cluster;
import pl.reader.environment.master.MasterComputer;
import pl.reader.view.MainStage;

public class Controler implements IControler {
	
	private Cluster cluster;
	private MainStage ms;
	private long round; //runda = zrobienie glosowania
	private long lastRound; //ostania runda animacji;
	private String out = "";
	private String lastOutMessage="";
	private boolean[] maybeStoped = new boolean[4];
	File [] files = new File[4];
	File f;
	
	public Controler(){};
	
	public Controler(MainStage m){
		ms=m;
	}
	
	public void init(){
	
		f = new File("D:\\tmp1.txt");
		files[0]=new File("D:\\tmp1.txt");
		files[1]=new File("D:\\tmp2.txt");
		files[2]=new File("D:\\tmp3.txt");
		files[3]=new File("D:\\tmp4.txt");
		
		cluster = new Cluster(4);
		cluster.initSlaves(files);
		cluster.setIC(this);
		((MasterComputer)cluster.getMasterC()).setIC(this);
	}
	
	public void start(){
		cluster.startComputers();
	}
	public void pauseAll(){
		cluster.pauseComputers();
	}
	public void resumeAll(){
		cluster.resumeComputers();
	}
	public void stopAll(){
		cluster.stopComputers();
	}
	public void pause(int i){
		cluster.pauseSlave(i);
	}
	public void resume(int i){
		cluster.resumeSlave(i);
	}
	@Override
	public void inform() {
		// TODO Auto-generated method stub
		round++;
		//ms.shuffleAnimation();
	}
	public boolean checkAnimRound(){
		if (lastRound!=round) {
			lastRound=round;
			return true;
		} else return false;
	}
	
	public void addToOUT(String n){
		out+=n;
		lastOutMessage=n;
	}
	public String getOUT(){
		return out;
	}
	public String getLastOutMessage(){
		
		return lastOutMessage;
	}
	
	public int getActiveSlave(){
		if(cluster==null) return 0;
		return cluster.getActiveSlave();
	}
	@Override
	public boolean[] getMaybeStoped() {
		// TODO Auto-generated method stub
		return maybeStoped;
	}
	
	public void reset(){
		f = new File("D:\\tmp1.txt");
		files[0]=new File("D:\\tmp1.txt");
		files[1]=new File("D:\\tmp2.txt");
		files[2]=new File("D:\\tmp3.txt");
		files[3]=new File("D:\\tmp4.txt");
		cluster = new Cluster(4);
		cluster.initSlaves(files);
		cluster.setIC(this);
		((MasterComputer)cluster.getMasterC()).setIC(this);
	}

}
