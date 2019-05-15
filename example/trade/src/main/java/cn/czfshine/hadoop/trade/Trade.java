package cn.czfshine.hadoop.trade;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Trade {
    @Slf4j
    public static class Map1 extends Mapper<Object, Text, Text, TradeBean> {
        private TradeBean tradeBean = new TradeBean();
        private Text outkey = new Text();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("\\s+");
            if (split.length < 4) {
                log.warn(String.valueOf(split.length));
                return;
            }

            tradeBean.setUsername(split[0]);
            tradeBean.setIncome(Double.valueOf(split[1]));
            tradeBean.setPayment(Double.valueOf(split[2]));
            outkey.set(split[0])` `;
            context.write(outkey, tradeBean);
            System.out.println(outkey.toString());

        }
    }

    public static class Reduce1 extends Reducer<Text, TradeBean, Text, TradeBean> {
        private TradeBean out = new TradeBean();

        @Override
        protected void reduce(Text key, Iterable<TradeBean> values, Context context) throws IOException, InterruptedException {

            double incomesum = 0;
            double paymentsum = 0;
            for (TradeBean tradeBean : values) {
                incomesum += tradeBean.getIncome();
                paymentsum += tradeBean.getPayment();
            }
            out.setUsername(key.toString());
            out.setIncome(incomesum);
            out.setPayment(paymentsum);

            context.write(key, out);

        }
    }

    public static class Map2 extends Mapper<Text, TradeBean, TradeBean, NullWritable> {

        private NullWritable nullWritable = NullWritable.get();

        @Override
        protected void map(Text key, TradeBean value, Context context) throws IOException, InterruptedException {
            context.write(value, nullWritable);
        }
    }


    public static class Reduce2 extends Reducer<TradeBean, NullWritable, TradeBean, NullWritable> {
        @Override
        protected void reduce(TradeBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }

    public static void deleteOutputDir(Configuration conf, String outputdir) {
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            fileSystem.delete(new Path(outputdir), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Configuration conf = new Configuration();
        String inpath = "input/trade";
        String out1 = "output/trade/1";
        String out2 = "output/trade/2";
        deleteOutputDir(conf, "output/trade");

        Trade trade = new Trade();

        Job job1 = trade.createJob(conf, inpath, out1, Map1.class, Reduce1.class);
        Job job2 = trade.createJob(conf,out1,out2,Map2.class,Reduce2.class);

        job1.setOutputFormatClass(SequenceFileOutputFormat.class);
        job2.setInputFormatClass(SequenceFileInputFormat.class);


        if (job1.waitForCompletion(true)) {
            System.exit(job2.waitForCompletion(true) ? 0 : 1);
        }


    }

    private Job createJob(Configuration conf, String inpath, String out1,
                          Class<? extends Mapper> mapclazz, Class<? extends Reducer> reduceclazz)
            throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        Job job1 = Job.getInstance(conf, "InvertedIndex");
        job1.setJarByClass(this.getClass());
        job1.setMapperClass(mapclazz);

        Mapper mapper = mapclazz.getConstructor().newInstance();
        job1.setMapOutputKeyClass(Class.forName(mapper.getTypes()[2].getTypeName()));
        job1.setMapOutputValueClass(Class.forName(mapper.getTypes()[3].getTypeName()));
        job1.setReducerClass(Reduce1.class);


        Reducer reducer = reduceclazz.getConstructor().newInstance();

        job1.setOutputKeyClass(Class.forName(reducer.getTypes()[2].getTypeName()));
        job1.setOutputValueClass(Class.forName(reducer.getTypes()[3].getTypeName()));

        FileInputFormat.addInputPath(job1, new Path(inpath));
        FileOutputFormat.setOutputPath(job1, new Path(out1));
        return job1;
    }
}
