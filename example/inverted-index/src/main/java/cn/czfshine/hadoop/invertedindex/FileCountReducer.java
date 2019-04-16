package cn.czfshine.hadoop.invertedindex;

import cn.czfshine.hadoop.invertedindex.Data.FileCount;
import cn.czfshine.hadoop.invertedindex.Data.OutputArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class FileCountReducer extends Reducer<Text, FileCount, Text, Text> {

    public void reduce(Text key,Iterable<FileCount> values,Context context) throws IOException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        values.forEach((fileCount ->
                stringBuilder.append(fileCount.toString())));
        Text text = new Text();
        text.set(stringBuilder.toString());
        context.write(key,text);
    }
}
