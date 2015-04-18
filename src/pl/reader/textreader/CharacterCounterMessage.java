package pl.reader.textreader;

import java.util.Random;

public class CharacterCounterMessage {
	
	
	private int[] counter = new int[26];
	
	private MessageStatus status;
	
	private String text;
	
	private int crcCode;
	
	private CharacterCounterMessage(MessageStatus ms){
		status = ms;
	}
	
	public static CharacterCounterMessage createFirstMessage(){
		return new CharacterCounterMessage(MessageStatus.FIRST);
	}
	public static CharacterCounterMessage createPartMessage(){
		return new CharacterCounterMessage(MessageStatus.PART);
	}
	public static CharacterCounterMessage createLastMessage(){
		return new CharacterCounterMessage(MessageStatus.LAST);
	}
	public static CharacterCounterMessage createEmptyMessage(){
		return new CharacterCounterMessage(MessageStatus.EMPTY);
	}

	public int[] getCounter() {
		return counter;
	}

	public void setCounter(int[] counter) {
		this.counter = counter;
	}
	
	public MessageStatus getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString(){
		String text="";
		text+="Message from" +this.text+" -- \n";
		for(int i=0;i<counter.length;i++){
			int c = 'a'+i;
			if(counter[i]<=0) continue;
			text+="Character "+(char)c+" = "+counter[i]+"\n";
		}
		
		return text;
	}

	public int getCrcCode() {
		return crcCode;
	}

	public void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}
	
	public void falseData(){
		Random rand = new Random();
		int number = rand.nextInt((25 - 1) + 1) + 1;
		System.out.println("Pozycja w tablicy "+ number);
		//counter[number]=rand.nextInt((123456 - 0) + 1) + 0;
		System.out.println("Wpisana wartosc "+ (counter[number]=rand.nextInt((123 - 0) + 1) + 0));
	}

}
