package cn.czfshine.hadoop.invertedindex.Data;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;

public class OutputArray extends ArrayWritable {
    public OutputArray(Class<? extends Writable> valueClass) {
        super(valueClass);
    }

    public OutputArray(Class<? extends Writable> valueClass, Writable[] values) {
        super(valueClass, values);
    }

    public OutputArray(String[] strings) {
        super(strings);
    }

    @Override
    public String toString() {
        String[] strings = this.toStrings();
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for (String s:strings
             ) {
            sb.append(s);
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
