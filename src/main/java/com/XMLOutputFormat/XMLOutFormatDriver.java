package com.XMLOutputFormat;

/**
 * Let’s implement a word count program in MapReduce and write a custom output
 * format which stores the key and value in XML format.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import java.io.IOException;

public class XMLOutFormatDriver {
  private static Logger logger = Logger.getLogger(XMLOutFormatDriver.class);

  public static void run(String input,String output) throws IOException, ClassNotFoundException, InterruptedException {
    logger.info("XMLOutFormatDriver Job Started");

    Configuration conf = new Configuration();

    Job job = Job.getInstance(conf, "XMLOutFormatDriver starts");

    job.setJarByClass(XMLOutFormatDriver.class);

    job.setInputFormatClass(TextInputFormat.class);
    job.setMapperClass(XmlOutputMapper.class);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);

    job.setReducerClass(XmlOutputReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    job.setOutputFormatClass(XmlOutputWriter.class);

    FileInputFormat.addInputPath(job,new Path(input));
    FileOutputFormat.setOutputPath(job, new Path(output));
    job.waitForCompletion(true);
  }
}
