package cn.czfshine.hadoop.trade;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static java.lang.Double.compare;

@Data
public class TradeBean implements WritableComparable<TradeBean> {

    private String username;
    private double income;
    private double payment;

    @Override
    public int compareTo(TradeBean tradeBean) {
        double a = getProfit();
        double b = tradeBean.getProfit();
        return (a != b) ? ((a > b) ? -1 : 1) : compare(tradeBean.income, income);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(username);
        dataOutput.writeDouble(income);
        dataOutput.writeDouble(payment);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        username = dataInput.readUTF();
        income = dataInput.readDouble();
        payment = dataInput.readDouble();

    }

    @Override
    public String toString() {
        return username + "\t" + income + "\t" + payment + "\t" + (getProfit());
    }

    public double getProfit() {
        return income - payment;
    }
}
