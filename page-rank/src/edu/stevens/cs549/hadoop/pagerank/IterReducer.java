package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;
import java.util.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class IterReducer extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		/* 
		 * TODO: emit key:node+rank, value: adjacency list
		 * Use PageRank algorithm to compute rank from weights contributed by incoming edges.
		 * Remember that one of the values will be marked as the adjacency list for the node.
		 */
		double d = PageRankDriver.DECAY;
		double rank = 0.0;
		Iterator<Text> iterator = values.iterator();
		String ajacentlist = "";
		while(iterator.hasNext()) {
			String line = iterator.next().toString();
			if(!line.startsWith("ADJ:")) {
				rank += Double.valueOf(line);
			} else {
				ajacentlist = line.replaceAll("ADJ:", "");
			}
		}
		rank = 1 - d + rank * d;
		context.write(new Text(key + ";" + rank), new Text(ajacentlist));
		//Output is key is "vertex;rank" with rank computed from weights
		//Output value is comma-separated adjacency list
	}

}
