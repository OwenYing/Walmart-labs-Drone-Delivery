==================================
Drone Delivery Challenge         |
==================================
Author: Tiancheng(Owen) Ying
Date: 09/17/2018
==================================
Usage(Jar File): 
    java -jar DroneDelivery.jar -input input_file_path [-output output_file_path] [-scheduler scheduler_type] [-print Y/N]
Usage(.class File): 
    java iogithubowenying.test.Test -input input_file_path [-output output_file_path] [-scheduler scheduler_type] [-print Y/N]
	
Options: 
    -output    : Output file path, default as "./result.txt"   
    -scheduler : dynamic , (Shortest order first, first come first serve)(also as default)
                 unfair  , (Detractors Tasks always be delivered at the very last)
                 limited , (only deliver from 6am - 10pm + dynamic mode)
                 unfair_limited , (unfair mode + limited mode)
    -print     : print result in terminal or not. "Y" print; "N" not print, default as "N"

==================================
Compile:
	1. As Eclipse Project:
		|-- Import Project into Eclipse
		|-- Compile
		|-- Right click the project --> Run as --> Run Configurations --> Set main class as iogithubowenying.test.Test
		|-- Export the project as Runnable Jar File
			|-- Right click on the project
			|-- Click export as Runnable Jar File
		|-- Run
			|-- Follows the Usage of Jar File
		
	2. Using command line
		|-- Enter root directory of the project
			|-- src : source files
			|-- bin : binary executables
			|-- test: some test files
		|-- find ./src -name "*.java" > source.txt
			|-- find all the java file under src and export into source.txt
		|-- javac -d bin @source.txt
			|-- Compile all the java files listed in source.txt
			|-- Output the binary files into bin folder
		|-- Enter bin file
		|-- Run
			|-- Follows the Usage of .class File


==================================
Assumptions:
	1. OrderDirection format strictly follows: N5S10.
	2. OrderTime always within a day, since there is no date information.
	3. Orders come as a data stream, you never know when and what is the next order.
	4. OrderDistance is always within the range of the Drone.
	5. OrderID format strictly follows: WMXXXX (4 digits)
	6. When only one order comes, deliver it regardless its distance, since you never know when is the next order.
	7. NPS Calculation:
		 0 <= Waiting Time <= 1 hour : Promoters
		 1 < Waiting Time <= 3 hours : Neutral
		 3 < Waiting Time            : Detractors
	8. Time is always valid and are in the same day.
	9. When meeting the situation: only one order comes, the delivery time > 3 hours(Must be detractors). 
	   Instead of deliver it at the very last, the scheduler is going to deliver this since the scheduler
	   doesn't know when is the next order's going to come.
		

==================================
Dynamic Input Schedule Algorithm:
    When only 1 order comes, deliver; When more than 1 order is waiting, do the shortest
     |--The reason when only one order comes then deliver, is that you never know when will the next 
       order come. So you need to deliver it. 
     |--When many tasks come at the same time, or many tasks come in the middle of delivering 
       an order, always deliver the shortest. The reason is if you want to make more people
       wait less time, you need to deliver short distance order, which can make others wait
       for the shortest time; 

==================================
Implementation:
	|-- Use a queue to store the tasks (which actually simulate input task stream)
    |-- Use a Priority Queue to store waiting tasks, the task with the shorter distance
        	    comes first.
    |-- When PriorityQueue is empty(No task at all), poll task queue(One new task comes) and offer to PriorityQueue
    |-- When PriorityQueue is not empty(Currently there is task waiting to deliver),
            poll the task from PriorityQueue, deliver
    |-- When delivering, if there are other tasks comes, poll task queue and offer them to PriorityQueue
        		(Calculate the finish time of current delivery, poll task queue's task if they come before the 
             finish time, then offer them to waiting queue, which is PriorityQueue)


==================================
Graph Example:
   				     --------------------                          -----------------------------
  User  <--Deliver	   WaitingTasksQueue      <--Offer tasks               Upcoming Tasks
   					 ---------------------                         -----------------------------
                         Ascending                                Simulate Continuously Coming Tasks


==================================
Hierarchical Structure:
	
	Order(I)           -->      DroneOrder
	OrderDirection(I)  -->      DroneOrderDirection
	OrderID(I)         -->      DroneOrderID
	OrderTime(I)       -->      DroneOrderTime
	
	DynamicInputStreamScheduler              -->      DynamicInputWithWaitScheduler
	DynamicInputStreamLimitedTimeScheduler   -->      DynamicInputStreamLimitedTimeWithWaitScheduler
	
	RandomTestGenerator(T)
	NPSCalculator(T)
	ReadOrdersFromFile(T)
	TimeConvert(T)
	WriteOrdersToFile(T)
	
	Test(Main)
	TestGenerator(JunitTest)
	
  I: Interface, T: Tool class

==================================
To be completed in the future:
	1. Starve situation
	2. Time outside of 6:00am - 10:00pm  need to wait till the next day
	3. Should work under -- Different ID format; Different position format; Different timestamp format
		eg: If not Manhattan distance ? 
	4. Order which is tooooooo far away (eg: cross state) --> push to corresponding warehouse
	5. If memory is filled up by the input stream ?
	6. How about drone can carry N goods at a time?
	7. Certain goods(eg:food) need to be delivered within a time? (Can't be in waiting queue for too long)
	8. If current task is too far, wait for a certain time.
	9. What if there are multiple Drones doing the delivery job 
		Multi-threads with synchronizing, lock waiting queue
	10. Scheduler can set a extra waiting time as "et". When far order comes, wait an extra time of "et",
	   if there is no shorter ones coming, then deliver; otherwise, deliver the shorter one.
	   the threshold of et is
	   				et = (EarlierLongerTime - LaterShorterTime)/2   (I calculate it with Math)
	   Since LaterShorterTime is unknown when we only have the EarlierLongerTime,
	   we can estimate it as 
	   				LaterShorterTime = (1/n) * EarlierLongerTime 
	   So the final et can be estimated as
	   				et = ((n - 1)/n) EarlierLongerTime / 2
	   in which, n is set by the programmer, EarlierLongerTime = n * LaterShorterTime
	   But this also has an issue: Suppose we have a EarlierLongerTime = t,  it waited for t/2. After
	   waiting, it is delivered, and as soon as it is delivered, a shorter task comes. If long task
	   didn't wait, it should have come back earlier so that the shorter task won't be detractors. But
	   since long task wait for t/2, the shorter task now becomes detractors. The solution to this problem
	   is to calculate the possibilities of wait or not wait situation, and choose whether use wait or not.
	   
==================================	
	test the program
	review the whole program --> whole structure and logic
  3.design pattern + OOD + database + JS
  1.resume
    leetcode
    multi-thread
  2.java fundamental --> java course source code
    why walmart? what can I bring
    behavior questions
    review schedulers, how to extend the abilities