package cn.czfshine.hadoop.trade;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Mapper<A, B, C, D> extends org.apache.hadoop.mapreduce.Mapper<A, B, C, D> {
    private Type[] types;

    public Mapper() {
        // 获取T.class
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] typeArray = parameterizedType.getActualTypeArguments();
            if (null != typeArray && typeArray.length > 0) {
                types = typeArray;
            }
        }
    }

    public Type[] getTypes() {
        return types;
    }
}
