/* File: DiscreteTimeQueue.java
 * Author: Abhiyush Mittal (axm159230@utdallas.edu)
 */

import java.io.*;
import java.util.*;

public class DiscreteTimeQueue {

	public static void Evaluate_TheoreticalValues(int arr_rate)
	{
		//int arr_rate[] = {1,2,3,4,5,6,7,8,9};
		double a1 = (double)1/2;
		double a2 = (double)1/4;
		double a3 = (double)1/4;
		
		int serv_rate = 8;
		double r1 = (double)1/2;
		double r2 = (double)2/3;
		double r3 = (double)1/4;
		
		double s1 = 1-r1;
		double s2 = 1-r2;
		double s3 = 1-r3;
		
		double theta1 = 0.0;
		double theta2 = 0.0;
		double theta3 = 0.0;
		
		double EN1 = 0.0;
		double EN2 = 0.0;
		double EN3 = 0.0;
		
		double util1 = 0.0;
		double util2 = 0.0;
		double util3 = 0.0;
		
		System.out.println("THEORETICAL VALUES");
		System.out.println("-------------------");
			System.out.println("**** For Arrival Rate : " + arr_rate + " ****\n");
			theta1 = ((a1 + s3*a3 + s3*s2*a2) * arr_rate)/(1-s3*s2*s1);
			theta2 = ((a2 + s1*a1 + s1*s3*a3) * arr_rate)/(1-s3*s2*s1);
			theta3 = ((a3 + s2*a2 + s2*s1*a1) * arr_rate)/(1-s3*s2*s1);
			EN1 = ((theta1/serv_rate)*(theta1/serv_rate))/(1-(theta1/serv_rate));
			EN2 = ((theta2/serv_rate)*(theta2/serv_rate))/(1-(theta2/serv_rate));
			EN3 = ((theta3/serv_rate)*(theta3/serv_rate)) /(1-(theta3/serv_rate));
			util1 = theta1/serv_rate;
			util2 = theta2/serv_rate;
			util3 = theta3/serv_rate;
			
			System.out.println("=== Queue 1 ===");
			System.out.println("Throughput: " + theta1);
			System.out.println("Expected Number of Customers: " + EN1);
			System.out.println("Utilization: " + util1);
			System.out.println("\n=== Queue 2 ===");
			System.out.println("Throughput: " + theta2);
			System.out.println("Expected Number of Customers: " + EN2);
			System.out.println("Utilization: " + util2);
			System.out.println("\n=== Queue 3 ===");
			System.out.println("Throughput: " + theta3);
			System.out.println("Expected Number of Customers: " + EN3);
			System.out.println("Utilization: " + util3);
			System.out.println("\n");
		}
	
	
	public static void main(String args[])
	{
		int arr_rate[] = {1,2,3,4,5,6,7,8,9}; 
		double a1 = (double)1/2; //Probability that an arriving packet enters queue1
		double a2 = (double)1/4; //Probability that an arriving packet enters queue2
		double a3 = (double)1/4; //Probability that an arriving packet enters queue3
		
		int serv_rate = 8;
		double r1 = (double)1/2; //Probability that a packet from queue1 departs from the network
		double r2 = (double)2/3; //Probability that a packet from queue2 departs from the network
		double r3 = (double)1/4; //Probability that a packet from queue3 departs from the network
		
		double s1 = 1-r1; //Probability that a packet from queue1 enters queue2
		double s2 = 1-r2; //Probability that a packet from queue2 enters queue3
		double s3 = 1-r3; //Probability that a packet from queue3 enters queue1
		
		//int arrival_rate = 2;
		double util1 = 0.0, util2 = 0.0, util3 = 0.0;
		
		final int ARR_Queue1 = 0;
		final int ARR_Queue2 = 1;
		final int ARR_Queue3 = 2;
        final int DEP_Queue1 = 3;
        final int DEP_Queue2 = 4;
        final int DEP_Queue3 = 5;
        final int DEP_Q1_ARR_Q2 = 6;
        final int DEP_Q2_ARR_Q3 = 7;
        final int DEP_Q3_ARR_Q1 = 8;
        
        int num_of_cust = 0;            //counter to keep track of the number of components that enter the system
          
        double clock = 0.0;             // System clock
        int N1 = 0, N2 = 0, N3 = 0;                      // Number of customers in the system
        int num_of_dep = 0;             // Number of departures from system
        double EN1 = 0.0, EN2 = 0.0, EN3 = 0.0;                // For calculating E[N]
        boolean done = false;           // Check if end-condition is satisfied
      
        EventDetails currentEvent;
        EventQueue Elist, EQueue1, EQueue2, EQueue3;
        Elist = new EventQueue();
        EQueue1 = new EventQueue();     // Creating an event queue for customers entering Queue1
        EQueue2 = new EventQueue();
        EQueue3 = new EventQueue();
        RandomVar rv = new RandomVar();
        double uni_rv = 0.0;
        
        System.out.println("\nPRACTICAL VALUES");
        System.out.println("----------------");
        for(int arrival_rate=1; arrival_rate<=9; arrival_rate++)
        {
        	Elist.insert(rv.exponential_rv(a1*arrival_rate),ARR_Queue1);
            Elist.insert(rv.exponential_rv(a2*arrival_rate),ARR_Queue2);
            Elist.insert(rv.exponential_rv(a3*arrival_rate),ARR_Queue3);
        while(!done)
        {
            currentEvent = Elist.get();    //Get next event from list
            double prev = clock;             //Store old clock value
            clock = currentEvent.event_time;    //Update the system clock
          
            switch(currentEvent.event_type)
            {
            case ARR_Queue1: 
            	EN1 += N1*(clock-prev);                //  update system statistics
                N1++;                                 //  update system size
                num_of_cust++;                       //increment the counter
                Elist.insert(clock+rv.exponential_rv(a1*arrival_rate),ARR_Queue1); //  generate next arrival
                uni_rv = rv.uniform_rv();
                if (N1>=1) {
                	if(uni_rv <= r1)
                	{
                		Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Queue1);   //  generate its departure event
                	}
                	if(uni_rv > r1)
                	{
                		Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Q1_ARR_Q2);   //  generate its departure event
                	}
                }
                           
                util1+= (clock-prev);
                break;
            
            case ARR_Queue2:
            	EN2 += N2*(clock-prev);                //  update system statistics
                N2++;                                 //  update system size
                num_of_cust++;                       //increment the counter
                Elist.insert(clock+rv.exponential_rv(a2*arrival_rate),ARR_Queue2); //  generate next arrival
                uni_rv = rv.uniform_rv();
                if (N2>=1) {
                	if(uni_rv <= r2)
                	{
                		Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Queue2);   //  generate its departure event
                	}
                	if(uni_rv > r2)
                	{
                		Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Q2_ARR_Q3);   //  generate its departure event
                	}
                }
                           
                util1+= (clock-prev);
                break;
                
            case ARR_Queue3:
            	EN3 += N3*(clock-prev);                //  update system statistics
                N3++;                                 //  update system size
                num_of_cust++;                       //increment the counter
                Elist.insert(clock+rv.exponential_rv(a3*arrival_rate),ARR_Queue3); //  generate next arrival
                uni_rv = rv.uniform_rv();
                if (N3>=1) {
                	if(uni_rv <= r3)
                	{
                		Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Queue3);   //  generate its departure event
                	}
                	if(uni_rv > r3)
                	{
                		Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Q3_ARR_Q1);   //  generate its departure event
                	}
                }
                           
                util1+= (clock-prev);
                break;
                
            case DEP_Queue1:                             //if departure
                EN1 += N1*(clock-prev);                  //  update system statistics
                N1--;                                    //  decrement system size
                num_of_dep++;                            //  increment number of departures
                 if (N1>=1) {                            // If there are more customers
                    Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Queue1);   //  generate next departure 
                  }
                  util1+=(clock-prev);
                  break;
                  
            case DEP_Queue2:                             //if departure
                EN2 += N2*(clock-prev);                  //  update system statistics
                N2--;                                    //  decrement system size
                num_of_dep++;                            //  increment number of departures
                 if (N2>=1) {                            // If there are more customers
                    Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Queue2);   //  generate next departure 
                  }
                  util2+=(clock-prev);
                  break;
                  
            case DEP_Queue3:                             //if departure
                EN3 += N3*(clock-prev);                  //  update system statistics
                N3--;                                    //  decrement system size
                num_of_dep++;                            //  increment number of departures
                 if (N3>=1) {                            // If there are more customers
                    Elist.insert(clock+rv.exponential_rv(serv_rate),DEP_Queue3);   //  generate next departure 
                  }
                  util3+=(clock-prev);
                  break;
                  
            case DEP_Q1_ARR_Q2:                             //if departure and enter another queue
            	  Elist.insert(rv.exponential_rv(serv_rate),ARR_Queue2);
                  break;
                  
            case DEP_Q2_ARR_Q3:                             //if departure
            	  Elist.insert(rv.exponential_rv(serv_rate),ARR_Queue3);
                  break;
                  
            case DEP_Q3_ARR_Q1:                             //if departure
            	  Elist.insert(rv.exponential_rv(serv_rate),ARR_Queue1);
                  break;
              } 
            currentEvent = null;
            if(num_of_dep > 50000)
                done = true;//end condition
            }   	
        Evaluate_TheoreticalValues(arrival_rate);
        System.out.println("=== Queue 1 ===");
		System.out.println("Throughput: ");
		System.out.println("Expected Number of Customers: " + EN1/clock);
		System.out.println("Utilization: " + util1/clock);
		System.out.println("\n=== Queue 2 ===");
		System.out.println("Throughput: ");
		System.out.println("Expected Number of Customers: " + EN2/clock);
		System.out.println("Utilization: " + util2/clock);
		System.out.println("\n=== Queue 3 ===");
		System.out.println("Throughput: ");
		System.out.println("Expected Number of Customers: " + EN3/clock);
		System.out.println("Utilization: " + util3/clock);
		System.out.println("\n");
        }
	}
  }
