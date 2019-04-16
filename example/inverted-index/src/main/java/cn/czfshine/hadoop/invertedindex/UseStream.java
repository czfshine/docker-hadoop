package cn.czfshine.hadoop.invertedindex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UseStream {

    public static void main(String[] args) {

        /* 建立模拟的数据
            a.txt包含内容
                hadoop  google  scau
                map  hadoop  reduce
                hive  hello  hbase
            b.txt 包括内容
                hadoop  google  scau
                map  hadoop  reduce
                czfshine 123 456
        */
        HashMap<String, List<String>> data = new HashMap<>();

        ArrayList<String> strings = new ArrayList<>(Arrays.asList(
                "hadopp","google","scau","map","hadoop","reduce",
                "hive","hello","hbase"));
        data.put("a.txt",strings);

        ArrayList<String> strings2 = new ArrayList<>(Arrays.asList(
                "hadopp","google","scau","map","hadoop","reduce",
                "czfshine","123","456"));

        data.put("b.txt",strings2);
/*
        data.entrySet().stream().flatMap((k)-> {
            System.out.println(k);
            return k.getValue().stream();
        });
*/

    }
}
