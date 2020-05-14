package scheduler;

import java.util.*;

public class Scheduler {
	private static ArrayList<Attendee> attendeeList;
	private static ArrayList<Meeting> availability;
	private static ArrayList<Meeting> mergeBlocked;
	

	public static void main(String[] args) {
		
		//request number of attendees
		attendeeList = new ArrayList<Attendee>();
		availability = new ArrayList<Meeting>();
		mergeBlocked = new ArrayList<Meeting>();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of attendees: ");
		int attendeeCount = in.nextInt();
		while (attendeeCount >0){ //create attendee's & their schedules
			Attendee placeholder = new Attendee();
			attendeeList.add(placeholder);
			attendeeCount--;
		}
		
		//request duration of meeting
		System.out.println("Enter duration of meeting: ");
		int duration = in.nextInt();
		
		//merge all attendee's time tables
		for (int i = 0; i<attendeeList.size();i++) {
			for (int j = 0; j < attendeeList.get(i).getMeetings().size(); j++) {
				availability.add(attendeeList.get(i).getMeetings().get(j));
			}	
		}
		//sort the merged time table		
		Collections.sort(availability, Comparator.comparing(Meeting::getStart));
		
		mergeBlocked.add(availability.get(0));
		availability.remove(0);
		
		//clean all blocked off times into one merged array of resultant blocked off times
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
	
		//"invert" blocked off with available times
		meetingIterator = mergeBlocked.iterator();
		while (meetingIterator.hasNext()) {
			if (mergeBlocked.size() <2) {mergeBlocked.remove(0); break;}
			if (mergeBlocked.get(0).getEnd()+duration <= mergeBlocked.get(1).getStart()) {
				availability.add(new Meeting(mergeBlocked.get(0).getEnd(),mergeBlocked.get(1).getStart()));
			}
			mergeBlocked.remove(0);
		}
		
		//print available times in text
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
		available = Integer.toString(startHr) + ":" +Integer.toString(startMin) + "-"+ Integer.toString(endHr)+":"+Integer.toString(endMin);
		
		return available;
	}
	
}
