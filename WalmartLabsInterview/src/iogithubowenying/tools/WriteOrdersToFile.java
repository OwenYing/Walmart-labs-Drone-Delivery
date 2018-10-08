package iogithubowenying.tools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import iogithubowenying.delivery.DroneOrder;

/**
 * WriteOrdersToFile Tool class can write 
 * lines to specified file. 
 * 
 * @author Owen
 *
 */

public class WriteOrdersToFile {

	/**
	 * Write lines to file, the lines are stored
	 * in a List, each line is an element in List.
	 * Using US-ASCII encoding
	 * @param path
	 * @param orders
	 */
	public static void writeLinesToFile(String path, List<String> orders) {
		Path textFile = Paths.get(path);
		Charset charset = Charset.forName("US-ASCII");
		
		try {
			Files.write(textFile, orders, charset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeOrdersToFile(String path, List<DroneOrder> orders) {
		List<String> printOrder = new ArrayList<>();
		for(DroneOrder cur : orders) {
			String printable = cur.getOrderID().getOrderID() + " " 
					 	+ cur.getDirection().getOrderDirection() + " "
					 	+ TimeConvert.longToStr(cur.geteTime());
			printOrder.add(printable);
		}
		writeLinesToFile(path, printOrder);
	}
	
}
