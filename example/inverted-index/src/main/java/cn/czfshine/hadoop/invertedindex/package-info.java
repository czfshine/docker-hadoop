/**
 *
 *  > 生成文件的倒排索引
 *
 * ### 技术点:
 * * 如何自定义数据类型
 * * 多个mapreduce如何衔接
 *
 * ### 算法要求
 *
 * * input
 *
 * 多个类似如下的文件:
 * ```text
 * hadoop  google  scau
 *
 * map  hadoop  reduce
 *
 * hive  hello  hbase
 * ```
 * * output
 * 类似的键值对:
 * ```text
 * hadoop  a.txt->2,b.txt->1
 * map       a.txt->1,b.txt->1
 * ```
 * > 单词所在的文件名和数量
 *  *  算法如下:
 *  *   map    1: word + file name             -> (word + file name,1)
 *  *   reduce 1: (word + file name,{1,2,...}) -> (word + file name,xxx)
 *  *   map    2: (word + file name,xxx)       -> (word ,file name+xxx)
 *  *   reduce 2: (word ,{file name+xxx})      -> (word ,[file name+xxx])
 *  *
 *  *   类对应关系 :
 *  *      map 1            : {@link cn.czfshine.hadoop.invertedindex.WordFilenameMapper}
 *  *      reduce 1         : {@link cn.czfshine.hadoop.invertedindex.WordFilenameReducer}
 *  *      map 2            : {@link cn.czfshine.hadoop.invertedindex.FileCountMapper}
 *  *      reduce 2         : {@link cn.czfshine.hadoop.invertedindex.FileCountReducer}
 *  *      word + file name : {@link cn.czfshine.hadoop.invertedindex.Data.WordFilename}
 *  *      file name+xxx    : {@link cn.czfshine.hadoop.invertedindex.Data.FileCount}
 */
package cn.czfshine.hadoop.invertedindex;
