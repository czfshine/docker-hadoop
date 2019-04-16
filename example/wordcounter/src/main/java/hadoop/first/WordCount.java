package hadoop.first;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by bee on 3/25/17.
 */
public class WordCount {


    public static void deleteOutputDir(Configuration conf, String outputdir){
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            fileSystem.delete(new Path(outputdir),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
            throws IOException, ClassNotFoundException, InterruptedException {

        //FileUtil.deleteDir("output");
        Configuration conf = new Configuration();

        String[] otherArgs = new String[]{"input/test.txt","output"};
        if (otherArgs.length != 2) {
            System.err.println("Usage:Merge and duplicate removal <in> <out>");
            System.exit(2);
        }
        deleteOutputDir(conf,"output");
        Job job = Job.getInstance(conf, "WordCount");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}