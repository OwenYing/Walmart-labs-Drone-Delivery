package iogithubowenying.test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import iogithubowenying.delivery.DroneOrder;
import iogithubowenying.scheduler.DynamicInputStreamLimitedTimeScheduler;
import iogithubowenying.scheduler.DynamicInputStreamLimitedTimeWithWaitScheduler;
import iogithubowenying.tools.ReadOrdersFromFile;
import iogithubowenying.tools.WriteOrdersToFile;

class TestGenerator {

	@Test
	void test() {

//			Queue<DroneOrder> tasks = RandomTestGenerator.generateRandomTestOrder(100, 20, 20, 0, 60*60*24);
//			ArrayList<DroneOrder> temp = new ArrayList<>();
//			while(!tasks.isEmpty()) {
//				temp.add(tasks.poll());
//			}
//			RandomTestGenerator.writeTestCasesToFile("/Users/Owen/Desktop/random.txt", temp);		
		
		
		
		String inputFilePath = "/Users/Owen/Desktop/testunfairlimited.txt";
//		String outputFilePath = "./result.txt";
//		String outputFilePath = "/Users/Owen/Desktop/IT/JavaWorkspace/WalmartLabsInterview/test/result.txt";

		
		// Scheduling
		List<String> order = null;
		try {
			order = ReadOrdersFromFile.readLinesFromFile(inputFilePath);
		} catch (NoSuchFileException e) {
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Create tasks queue to store incoming tasks
		Queue<DroneOrder> tasks = new LinkedList<>();
		for(String cur : order) {
			tasks.offer(ReadOrdersFromFile.parseDroneOrder(cur)); // Read orders from file
		}

		// Use scheduler to schedule tasks
		DynamicInputStreamLimitedTimeWithWaitScheduler scheduler = new DynamicInputStreamLimitedTimeWithWaitScheduler(tasks, 6*60*60, 22*60*60);
		scheduler.start();  // start scheduling
		System.out.println(scheduler);
		System.out.println("finished");
		// Write schedule result to file
		//WriteOrdersToFile.writeLinesToFile(outputFilePath, scheduler.getPrintResult());
	}

}



//public static void pastTest() {
	// TODO Auto-generated method stub
//	System.out.println("WM123".matches("WM\\d{4}"));
//	
//	System.out.println("S3E100".matches("[NEWS]\\d+[NEWS]\\d+"));
//	
//	System.out.println("00:12:100".matches("\\d{2}:\\d{2}:\\d{2}"));
//	
//	DroneOrderTime test = new DroneOrderTime("01:00:36");
//	System.out.println(test.getOrderTime());
	
//	DroneOrderID id = new DroneOrderID("WM0052");
//	System.out.println(id.geteID());
//	DroneOrderDirection dir =  new DroneOrderDirection("N999W1000");
//	System.out.println(dir.getOrderDistance());
//	DroneOrderTime time = new DroneOrderTime("01:00:00");
//	System.out.println(time.getOrderTime());
//	DroneOrder check = new DroneOrder(id, dir, time);
//	System.out.println(check);
//	System.out.println(check.geteID());
//	System.out.println(check.getEdistance());
//	System.out.println(check.geteTime());
	
//	System.out.println(TimeConvert.intToStr(4200));
//}
