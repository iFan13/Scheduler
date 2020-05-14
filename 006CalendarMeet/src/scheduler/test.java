package scheduler;

import java.util.ArrayList;
import java.util.Iterator;

public class test {
		
	public static void main(String[] args) {
		ArrayList<Meeting> availability = new ArrayList<Meeting>();
		ArrayList<Meeting> mergeBlocked = new ArrayList<Meeting>();
		availability.add(new Meeting(0,480)); //0-8
		availability.add(new Meeting(0,540)); //0-9
		availability.add(new Meeting(540,600)); //9-10
		availability.add(new Meeting(630,660)); //1030-11
		availability.add(new Meeting(780,840)); //13-14
		availability.add(new Meeting(810,870)); //13.5-14.5
		availability.add(new Meeting(960,1440)); //16-24
		availability.add(new Meeting(1020,1440)); //17-24

		
		mergeBlocked.add(availability.get(0));
		availability.remove(0);
		
		Iterator<Meeting> meetingIterator =  availability.iterator();
		
		while (meetingIterator.hasNext()) {
			if (mergeBlocked.get(mergeBlocked.size()-1).getEnd() >= availability.get(0).getStart() && mergeBlocked.get(mergeBlocked.size()-1).getStart() <= availability.get(0).getStart()) {
				mergeBlocked.get(mergeBlocked.size()-1).setEnd(availability.get(0).getEnd());
			}		
			
			if (mergeBlocked.get(mergeBlocked.size()-1).getEnd() <= availability.get(0).getStart()){
				mergeBlocked.add(availability.get(0));
			}
			availability.remove(0);
		
		}
		
	
		
		meetingIterator = mergeBlocked.iterator();
		while (meetingIterator.hasNext()) {
			if (mergeBlocked.size() <2) {mergeBlocked.remove(0); break;}
			if (mergeBlocked.get(0).getEnd()+30 <= mergeBlocked.get(1).getStart()) {
				availability.add(new Meeting(mergeBlocked.get(0).getEnd(),mergeBlocked.get(1).getStart()));
			}
			mergeBlocked.remove(0);
		}
		
		System.out.println("Available meeting times: ");
		for (int i = 0;i< availability.size();i++) {
			System.out.println(timeToString(availability.get(i)));
		}
		
		
		
		
	}

	public static String timeToString(Meeting meeting) {
		String available;
		int startHr = meeting.getStart()/60;
		int startMin = meeting.getStart()%60;
		int endHr = meeting.getEnd()/60;
		int endMin = meeting.getEnd()%60;
		available = Integer.toString(startHr) + ":" +String.format("%02d", startMin) + "-"+ Integer.toString(endHr)+":"+String.format("%02d", endMin);
		
		return available;
	}
	
	 
}
