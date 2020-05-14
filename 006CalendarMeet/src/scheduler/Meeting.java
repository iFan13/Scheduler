package scheduler;

public class Meeting {
	private int start;
	private int end;
	
	public Meeting(int inputStart, int inputEnd){
		this.start=inputStart;
		this.end=inputEnd;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "Meeting [start=" + start + ", end=" + end + "]";
	}
	
	
}
