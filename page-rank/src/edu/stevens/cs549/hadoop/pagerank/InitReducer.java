package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;
import java.util.Iterator;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class InitReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		/* 
		 * TODO: Output key: node+rank, value: adjacency list
		 */

		int defualtrank = 1;
		Iterator<Text> v = values.iterator();
		while(v.hasNext()) {
			// emit node+rank, value
			context.write(new Text(key + ";" + defualtrank), v.next());
		}
		//Output key is "vertex;rank" (initial rank is 1)
		//Output value is comma-separated list of vertices
	}
}
