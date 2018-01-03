# 3-node-unidirectional-ring-network
Design a simulation for a 3-node unidirectional ring network to calculate expected number of customers in each queue, throughput and utilization for each queue

Simulation of Open Queueing Networks
====================================

Project Description:
---------------------
In this program we are implementing three-node unidirectional ring network by simulating open queueing networks and assuming that each queue is an independent M/M/1 queueing system. The program outputs the number of customers in the queue after 500000 departures from the system, the average number of customers in the system, throughput and utilization of each queue in the entire simulation.

Platform: 
----------
Java (jdk 1.8.0_66, jre 1.8.0_66), Eclipse for Java (4.6.0 Neon)

Compiler:
----------
Eclipse Compiler for Java (ECJ), an open source incremental compiler used by the Eclipse project.

Operating System:
------------------
Windows 10 (32-bit Operating System, x64-based processor)

Files:
------
1) RandomVar.java: File containing functions for generating uniform and exponential random variables.
2) EventDetails.java: File containing structure of an event and declares various attributes for an event such as: time at which an event occurs, event type etc.
3) EventQueue.java: File containing functions for inserting and removing Events from EventLists.
4) DiscreteTimeQueue.java : File containing main program executing the simulation.

   
Instructions for compiling and running the source code:
- Import all the java files in Eclipse and keep them under same package by creating a New Project.
- Save the files.
- Go to Run -> Run As -> Java Application
- Give required inputs on the Console window
