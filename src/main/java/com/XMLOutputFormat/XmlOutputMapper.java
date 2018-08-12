package com.XMLOutputFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class XmlOutputMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
  @Override
  protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    String[] strings = value.toString().split(",");
    for(String str:strings){
      context.write(new Text(str),new IntWritable(1));
    }
  }
}
