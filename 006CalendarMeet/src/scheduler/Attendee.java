package scheduler;

import java.util.*;

//class responsible for keeping track of attendee ID, blocked off times, start and end day times
public class Attendee{
	private String name;
	private ArrayList<Meeting> meetings;
	
	public Attendee() {
		this.meetings = new ArrayList<Meeting>(); //initialize meeting list
		Scanner in = new Scanner(System.in);
		System.out.print("\nEnter Attendee name: ");
		this.name = in.nextLine();
		
		System.out.print("\nEnter when attendee's day starts [format: ##:##]: ");
		Meeting meeting = new Meeting(0, stringTimeToMinutes(in.nextLine().trim())); 
		meetings.add(meeting); //create and add artificial meeting to block out when day starts
		
		System.out.print("\nEnter when attendee's day ends [format: ##:##]: ");
		meeting = new Meeting(stringTimeToMinutes(in.nextLine().trim()), 1440); 
		meetings.add(meeting); //create and add artificial meeting to block out when day ends
		
		addMeeting();		
	}
	
	private void addMeeting() {
		String meetingTime = null;
		//add meetings until quit
		do{
		System.out.print("\nCreate meeting start & end, q to stop adding meetings [format: ##:##, ##:##]: ");
		Scanner in = new Scanner(System.in);
		meetingTime = in.nextLine();
		
		if (meetingTime.toLowerCase().equals("q")) break;
		String[] meetingTimeSplit = meetingTime.split(",", 0);
		Meeting meeting = new Meeting(stringTimeToMinutes(meetingTimeSplit[0].trim()),stringTimeToMinutes(meetingTimeSplit[1].trim()));
		System.out.println(meeting.toString());
		meetings.add(meeting);
		
		}while(true);
		
		//sort new list of meetings
		Collections.sort(meetings, Comparator.comparing(Meeting::getStart));
				
	}
	
	public int stringTimeToMinutes(String time) {
		int minutes;
		String[] input = time.split(":",0);
		minutes = Integer.parseInt(input[0])*60+Integer.parseInt(input[1]);
		return minutes;
	}
		
	public String getName() {
		return name;
	}

	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}

	
}
