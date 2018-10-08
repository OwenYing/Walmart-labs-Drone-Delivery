package iogithubowenying.scheduler;

import java.util.LinkedList;
import java.util.Queue;

import iogithubowenying.delivery.DroneOrder;


/**
 * Deliver in limited time + Do detractor tasks at very last.
 * In this case, we can maximize NPS as much as possible
 * 
 * @author Owen
 *
 */
public class DynamicInputStreamLimitedTimeWithWaitScheduler extends DynamicInputStreamLimitedTimeScheduler {
	
	/**
	 * Task queue, simulate continuously coming tasks
	 */
	private Queue<DroneOrder> tasks;

	/**
	 * Deliver in limited time + Do detractor tasks at very last
	 * @param tasks Orders stream
	 * @param start In seconds, the time the drone start working
	 * @param end   In seconds, the time the drone end working
	 */
	public DynamicInputStreamLimitedTimeWithWaitScheduler(Queue<DroneOrder> tasks, int start, int end) {
		super(tasks, start, end);
		if(start < 0 || end < 0 || start > end) {
			System.out.println("Working time not permitted");
//			throw new Exception();
			return;
		}
		this.tasks = super.getTasks();
	}

	@Override
	public void start() {
		// Detractors be delivered at very last
		// Edge case
		if(tasks.isEmpty())
			return;
		// Move the detractors to the back of the task
		tasks = new DynamicInputWithWaitScheduler(new LinkedList<DroneOrder>()).rearrangeTasks(tasks);
		
		super.setTasks(tasks);
		super.start();
	
	}	
}
