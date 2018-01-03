/* File: EventDetails.java
 * Author: Abhiyush Mittal (axm159230@utdallas.edu)
 */

/*This class defines the structure of an event and declares various attributes for an event
 * such as: time at which an event occurs, event type, pointer to the next event in queue
 */
public class EventDetails {
	
	double event_time; //Time at which the event takes place
	int event_type; //Type of an event: Arrival or Departure
    EventDetails next_event;
    
    EventDetails(double time, int type)
    {
    	event_time = time;
    	event_type = type;
    	next_event = null;
    }
}
