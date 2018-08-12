package com.CustomFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

import java.io.IOException;

public class TitanicRecordReader extends RecordReader<Key_Value,IntWritable> {

    private Key_Value key;
    private IntWritable value;
    private LineRecordReader reader = new LineRecordReader();

    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        reader.initialize(inputSplit,taskAttemptContext);
    }

    public boolean nextKeyValue() throws IOException, InterruptedException {
        boolean gotNextKeyValue = reader.nextKeyValue();
        if(gotNextKeyValue){
            if(key==null){
                key = new Key_Value();
            }
            if(value == null){
                value = new IntWritable();
            }
            Text line = reader.getCurrentValue();
            String[] tokens = line.toString().split(",");
            key.setX(new String(tokens[1]));
            key.setY(new String(tokens[4]));
            value.set(new Integer(1));
        }
        else {
            key = null;
            value = null;
        }
        return gotNextKeyValue;
    }

    public Key_Value getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    public IntWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    public float getProgress() throws IOException, InterruptedException {
        return reader.getProgress();
    }

    public void close() throws IOException {
        reader.close();
    }
}
