/* File: EventQueue.java
 * Author: Abhiyush Mittal (axm159230@utdallas.edu)
 */

import java.io.*;
import java.util.*;

/* This class contains functions for inserting and removing Events from EventQueues.*/
public class EventQueue
{
	EventDetails head; //Points to the first event in the event queue
	int event_Count;    //the total number of events in the event queue
	
	EventQueue()
	{
		event_Count = 0;
		head = null;
	}
	
	
	//Returning the event from the head of the event list
	public EventDetails get()
	{
		if (event_Count == 0) 
		{
		    return null;
		}
		else 
		{
		    event_Count--;
		    EventDetails event_det;
		    event_det = head;
		    head = head.next_event;
		    event_det.next_event = null;
		    return event_det;
		 }
	}

	//Inserting a new event into the event list/queue
	// time - The time at which the event takes place
	// type - The type of event: Arrival or Departure
	
	public void insert(double time,int type)
	{
		event_Count++;
		EventDetails event_det = new EventDetails(time,type);
		if(head == null)//If the event list is empty
		{
			head = event_det;//add the event at the head of the list
			event_det.next_event = null;
		}
		else if(head.event_time >= event_det.event_time)
		{
			event_det.next_event = head;//If the event is earlier than all the other
			head = event_det;//existing events place it at the head of the queue
			
		}
		else
		{			                                
			// Otherwise, search for the correct location sorted by time.
	        EventDetails event_index;                       
		    event_index = head;
		    while (event_index.next_event != null) 
		    {
			    if (event_index.next_event.event_time < event_det.event_time) 
			    {
				    event_index = event_index.next_event;
			    }
			    else 
			    {
				    break;
			    }
			}
			event_det.next_event = event_index.next_event;
			event_index.next_event = event_det;
		}
	}
	

    //Clear all Events from the EventQueue
    public void clear()
    {
        EventDetails event_ptr;
        while(head != null)
        {
	       event_ptr = head;
	       head = head.next_event;
	       event_ptr.next_event = null;
	       event_ptr = null;  //Removing event_ptr- not sure
        }
        event_Count = 0;
    }

    
    //Remove and return first event of given type
    public EventDetails remove(int type)
    {
        if (event_Count == 0 || head == null) 
        {
            return null;
        }
        else 
        {
            EventDetails event_ptr;
            EventDetails prev_event = null;
            event_ptr = head;

            while(event_ptr != null)
            {
                if (event_ptr.event_type == type) 
                {
                    if (prev_event == null) 
                    {
                         head = event_ptr.next_event;
                         event_ptr.next_event = null;
                         return event_ptr;
                    }
                    else 
                    {
                         prev_event.next_event = event_ptr.next_event;
                         event_ptr.next_event = null;
                         return event_ptr;
                    }
                }
                else 
                {
                    prev_event = event_ptr;
                    event_ptr = event_ptr.next_event;
                }
             }
          return null;
       }
    }
}
	


	