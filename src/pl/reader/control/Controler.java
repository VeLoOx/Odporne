package pl.reader.control;

import java.io.File;

import pl.reader.environment.Cluster;
import pl.reader.environment.master.MasterComputer;
import pl.reader.view.MainStage;

public class Controler implements IControler {
	
	private Cluster cluster;
	private MainStage ms;
	
	public Controler(){};
	public Controler(MainStage m){
		ms=m;
	}
	
	public void init(){
		File [] files = new File[4];
		File f = new File("D:\\tmp1.txt");
		files[0]=new File("D:\\tmp1.txt");
		files[1]=new File("D:\\tmp2.txt");
		files[2]=new File("D:\\tmp3.txt");
		files[3]=new File("D:\\tmp4.txt");
		
		cluster = new Cluster(4);
		cluster.initSlaves(files);
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
	@Override
	public void inform() {
		// TODO Auto-generated method stub
		ms.shuffleAnimation();
	}

}
