package cn.czfshine.hadoop.invertedindex.Data;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 文件名和单词数量的元组对.
 *
 */
public class FileCount implements Writable {

    public FileCount() {
    }

    /**
     * 单词所在的文件名
     */
    private Text filename=new Text();

    public FileCount(Text filename, IntWritable count) {
        this.filename = filename;
        this.count = count;
    }

    /**
     * 单词数量
     */
    private IntWritable count=new IntWritable();

    public FileCount(String filenamestr, int countint) {
        filename.set(filenamestr);
        count.set(countint);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        filename.write(dataOutput);
        count.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        filename.readFields(dataInput);
        count.readFields(dataInput);
    }

    public Text getFilename() {
        return filename;
    }

    public IntWritable getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "{" +
                  filename +
                "->" + count +
                '}';
    }

    public FileCount clone(){
        return new FileCount(filename,count);
    }
}
