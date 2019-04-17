#!/usr/bin/env bash
set -v
#上传数据(input文件夹)到hdfs

name=`hostname`
if [[ ! $name == "hadoop-master" ]];then
    echo "要在hadoop的master机器执行这个脚本"
    exit 1
fi

if [ ! -d "./input" ]; then
    echo "待上传的input文件夹不存在"
    exit 1
fi

hadoop dfs -mkdir /user/
hadoop dfs -mkdir /user/root/
hadoop dfs -put ./input/ /user/root/
hadoop dfs -ls /user/root/input