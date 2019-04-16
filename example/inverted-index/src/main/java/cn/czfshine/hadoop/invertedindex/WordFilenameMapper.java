package cn.czfshine.hadoop.invertedindex;

import cn.czfshine.hadoop.invertedindex.Data.WordFilename;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 *  算法中的第一个mapper,输出单词和文件名.
 *
 *  算法内容在{@link cn.czfshine.hadoop.invertedindex}
 */
public class WordFilenameMapper extends Mapper<Object,Text, WordFilename, IntWritable> {
    private static final IntWritable one = new IntWritable(1);
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());

        while (itr.hasMoreTokens()) {
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String name = inputSplit.getPath().getName();
            WordFilename wordFilename = new WordFilename( itr.nextToken(),name);
            context.write(wordFilename, one);
        }
    }
}
