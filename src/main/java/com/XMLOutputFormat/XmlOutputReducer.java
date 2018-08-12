package com.XMLOutputFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class XmlOutputReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

  @Override
  protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
    int i=0;
    for(IntWritable intWritable:values){
      i +=intWritable.get();
    }
    context.write(key,new IntWritable(i));
  }
}
