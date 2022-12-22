package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class FinMapper extends Mapper<LongWritable, Text, DoubleWritable, Text> {

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException, IllegalArgumentException {
		String line = value.toString(); // Converts Line to a String
		/*
		 * TODO output key:-rank, value: node
		 * See IterMapper for hints on parsing the output of IterReducer.
		 */
		String[] sections = line.split("\t"); // nodeId+nodeName \t rank

		if (sections.length > 2)
		{
			throw new IOException("Incorrect data format");
		}
		if (sections.length != 2) {
			return;
		}
		String[] noderank = sections[0].split(";");// split vertex;rank
		context.write(new DoubleWritable(0 - Double.valueOf(noderank[1])), new Text(noderank[0]));
		//Output key is -rank (to sort in reverse order)
		//Output value is vertex
	}

}