package cn.czfshine.hadoop.invertedindex;

import cn.czfshine.hadoop.invertedindex.Data.WordFilename;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class WordFilenameReducer extends Reducer<WordFilename, IntWritable,WordFilename,IntWritable> {
    private IntWritable result = new IntWritable();
    public void reduce(WordFilename key, Iterable<IntWritable> values,
                       Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        IntWritable val;
        for (Iterator i = values.iterator(); i.hasNext(); sum += val.get()) {
            val = (IntWritable) i.next();
        }
        this.result.set(sum);
        context.write(key, this.result);
    }
}
