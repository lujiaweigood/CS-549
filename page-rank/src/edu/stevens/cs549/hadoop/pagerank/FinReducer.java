package edu.stevens.cs549.hadoop.pagerank;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FinReducer extends Reducer<DoubleWritable, Text, Text, Text> {
	
	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		if (context.getCacheFiles() != null && context.getCacheFiles().length > 0) {
			try {
				super.setup(context);
				URI uri = context.getCacheFiles()[0];
				if (uri != null) {
					System.out.println("Mapping File: " + FileUtils.readFileToString(new File("./cache"), "UTF-8"));
				} else {
					System.out.println("NO MAPPING FILE");
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println(">>>>>> NO CACHE FILES AT ALL");
		}
	}

	public void reduce(DoubleWritable key, Iterable<Text> values, Context context) throws IOException,
			InterruptedException {
		/* 
		 * TODO: For each value, emit: key:value, value:-rank
		 */
		Iterator<Text> iterator = values.iterator();
		String node;
		while(iterator.hasNext()) {
			node = iterator.next().toString();
			context.write(new Text(node), new Text(String.valueOf(0 - key.get())));
		}
		//Output (vertex, -rank) for each vertex in the list
	}
}
