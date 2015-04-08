package pl.reader.textreader;

public class CharacterCounterMessage {
	
	
	private int[] counter = new int[26];
	
	private MessageStatus status;
	
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

	public String toString(){
		String text="";
		for(int i=0;i<counter.length;i++){
			int c = 'a'+i;
			if(counter[i]<=0) continue;
			text+="Character "+(char)c+" = "+counter[i]+"\n";
		}
		
		return text;
	}
	
	

}
