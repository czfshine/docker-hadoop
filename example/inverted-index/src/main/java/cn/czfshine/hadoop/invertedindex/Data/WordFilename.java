package cn.czfshine.hadoop.invertedindex.Data;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 单词,文件名的元组对.
 */
public class WordFilename implements WritableComparable<WordFilename> {
    public WordFilename() {
    }

    /**
     * 单词
     */
    private Text word =new Text();
    /**
     * 单词所在的文件名
     */
    private Text filename = new Text();

    public WordFilename(String wordstr, String filenamestr) {
        this.word.set(wordstr);
        this.filename.set(filenamestr);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        word.write(dataOutput);
        filename.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        word.readFields(dataInput);
        filename.readFields(dataInput);
    }

    public Text getWord() {
        return word;
    }

    public Text getFilename() {
        return filename;
    }

    @Override
    public int compareTo(WordFilename wordFilename) {
        int i = word.compareTo(wordFilename.word);
        if(i==0){
            return filename.compareTo(wordFilename.filename);
        }
        return i;
    }
}
