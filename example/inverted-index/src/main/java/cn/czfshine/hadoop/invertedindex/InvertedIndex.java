package cn.czfshine.hadoop.invertedindex;

import cn.czfshine.hadoop.invertedindex.Data.FileCount;
import cn.czfshine.hadoop.invertedindex.Data.OutputArray;
import cn.czfshine.hadoop.invertedindex.Data.WordFilename;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class InvertedIndex {
    public static void deleteOutputDir(Configuration conf, String outputdir){
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            fileSystem.delete(new Path(outputdir),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String inpath="input/index";
        String out1 = "output/index/word";
        String out2 = "output/index/index";
        deleteOutputDir(conf,"output/index");
        Job job1 = Job.getInstance(conf, "InvertedIndex");
        job1.setJarByClass(InvertedIndex.class);
        job1.setMapperClass(WordFilenameMapper.class);
        job1.setReducerClass(WordFilenameReducer.class);
        job1.setMapOutputKeyClass(WordFilename.class);
        job1.setMapOutputValueClass(IntWritable.class);
        job1.setOutputKeyClass(WordFilename.class);
        job1.setOutputValueClass(IntWritable.class);

        job1.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(inpath));
        FileOutputFormat.setOutputPath(job1, new Path(out1));

        Job job2 = Job.getInstance(conf, "InvertedIndex");
        job2.setJarByClass( InvertedIndex.class);
        job2.setMapperClass(FileCountMapper.class);
        job2.setReducerClass(FileCountReducer.class);

        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(FileCount.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);


        job2.setInputFormatClass(SequenceFileInputFormat.class);
        FileInputFormat.addInputPath(job2, new Path(out1));
        FileOutputFormat.setOutputPath(job2, new Path(out2));

        if(job1.waitForCompletion(true)){
            System.exit(job2.waitForCompletion(true) ? 0 : 1);
        }

    }

}
