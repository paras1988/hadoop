package com.XMLOutputFormat;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;

import java.io.IOException;

public class XmlOutputWriter extends FileOutputFormat<Text, IntWritable>{


  public RecordWriter<Text, IntWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
    String file_extension = ".xml";
    Path file = getDefaultWorkFile(job,file_extension);
    FileSystem fs = file.getFileSystem(job.getConfiguration());
    return new XmlRecordWriter(fs.create(file, false));
  }
}
