package pl.reader.control;

public interface IControler {

	public void inform();
	public void addToOUT(String n);
	public String getOUT();
	public String getLastOutMessage();
	public boolean[] getMaybeStoped();
	public boolean[] getStopedStep();
	public boolean isStepMode();
}
