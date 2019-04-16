package cn.czfshine.hadoop.invertedindex;

import cn.czfshine.hadoop.invertedindex.Data.FileCount;
import cn.czfshine.hadoop.invertedindex.Data.WordFilename;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FileCountMapper extends Mapper<WordFilename, IntWritable, Text,FileCount> {
    public void map(WordFilename key,IntWritable value,Context context) throws IOException, InterruptedException {
        FileCount fileCount = new FileCount(key.getFilename().toString(), value.get());
        context.write(key.getWord(),fileCount);
    }
}
