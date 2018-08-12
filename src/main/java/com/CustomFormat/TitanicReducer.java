package com.CustomFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TitanicReducer extends Reducer<Key_Value,IntWritable,Key_Value,IntWritable> {
    @Override
    protected void reduce(Key_Value key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        context.write(key, new IntWritable(sum));
    }
}
