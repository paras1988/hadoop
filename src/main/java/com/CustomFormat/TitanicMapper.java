package com.CustomFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;

public class TitanicMapper extends Mapper<Key_Value,IntWritable,Key_Value,IntWritable> {
    private Logger logger = Logger.getLogger(TitanicMapper.class);

    @Override
    protected void map(Key_Value key, IntWritable value, Context context) throws IOException, InterruptedException {
        logger.info(key.getX()+"**"+key.getY()+"**"+value);
        context.write(key,new IntWritable(1));
    }
}
