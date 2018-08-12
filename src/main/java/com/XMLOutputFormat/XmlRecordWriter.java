package com.XMLOutputFormat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class XmlRecordWriter extends RecordWriter<Text, IntWritable> {
  private FSDataOutputStream out;

  XmlRecordWriter(FSDataOutputStream out) throws IOException {
    this.out = out;
    this.out.writeBytes("<Output>\n");
  }

  public synchronized void write(Text key, IntWritable value) throws IOException, InterruptedException {
    out.writeBytes("<record>\n");
    this.writeStyle("key", key.toString());
    this.writeStyle("value", value.toString());
    out.writeBytes("</record>\n");
  }

  private void writeStyle(String xml_tag,String tag_value) throws IOException{
    out.writeBytes("<"+xml_tag+">"+tag_value+"</"+xml_tag+">\n");
  }

  public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
    try {
      out.writeBytes("</Output>\n");
    } finally {
      out.close();
    }
  }
}
