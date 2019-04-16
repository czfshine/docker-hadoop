#Hadoop 示例

## 0 环境
* Hadoop 2.7.7 
* JDK 8

## 1 wordcounter 
> 最基本的单词计数

## 2 inverted-index
> 生成文件的倒排索引

### 技术点:
* 如何自定义数据类型
* 多个mapreduce如何衔接

### 算法要求

* input

多个类似如下的文件:
```text
hadoop  google  scau

map  hadoop  reduce

hive  hello  hbase
```
* output
类似的键值对:
```text
hadoop  a.txt->2,b.txt->1
map       a.txt->1,b.txt->1   
```
> 单词所在的文件名和数量

### 算法描述
```text
算法如下:
 *   map    1: word + file name             -> (word + file name,1)
 *   reduce 1: (word + file name,{1,2,...}) -> (word + file name,xxx)
 *   map    2: (word + file name,xxx)       -> (word ,file name+xxx)
 *   reduce 2: (word ,{file name+xxx})      -> (word ,[file name+xxx])
```
这个算法我也在想有没有一步mapreduce能够完成的,分析了一下,它需要两步归并,第一步的单词数的归并,第二步是相同单词不同文件的归并,是没法同时归并两步的,Combine相当于一步归并.

注意事项:
* 每个job各阶段的输入输出类型要手动指定,自定义类型要有无参构造函数
* 自定义输出格式