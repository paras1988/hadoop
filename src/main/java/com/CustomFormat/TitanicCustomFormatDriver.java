package com.CustomFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;
import java.io.IOException;

public class TitanicCustomFormatDriver {
    private static Logger logger = Logger.getLogger(TitanicCustomFormatDriver.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException,
            InterruptedException {

        logger.info("TitanicCustomFormatDriver Job Started");
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "TitanicCustomFormatDriver starts");
        job.setJarByClass(TitanicCustomFormatDriver.class);

        job.setInputFormatClass(TitanicInputFormat.class);
        job.setMapperClass(TitanicMapper.class);
        job.setMapOutputKeyClass(Key_Value.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(TitanicReducer.class);

        job.setOutputKeyClass(Key_Value.class);
        job.setOutputValueClass(IntWritable.class);

        job.setOutputFormatClass(TextOutputFormat.class);

        Path out=new Path(args[1]);
        out.getFileSystem(conf).delete(out);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);
    }
}
