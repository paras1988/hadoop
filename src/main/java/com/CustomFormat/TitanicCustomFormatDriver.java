package com.CustomFormat;

/*
Find out the number of people who died and survived, along with their genders.
Column 1: PassengerId
Column 2: Survived (survived=0 & died=1)
Column 3: Pclass
Column 4: Name
Column 5: Sex
Column 6: Age
 */
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

    public static void run(String input,String output) throws IOException, ClassNotFoundException,
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

        FileInputFormat.addInputPath(job,new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        job.waitForCompletion(true);
    }
}
