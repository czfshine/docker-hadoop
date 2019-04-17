#!/usr/bin/env bash
#执行倒排索引的程序
set -e
set -v

./check-host.sh

hadoop jar hadoop.inverted-index-1.0-SNAPSHOT.jar cn.czfshine.hadoop.invertedindex.InvertedIndex
hadoop dfs -ls /user/root/output/index
hadoop dfs -cat /user/root/output/index/part-r-00000 | head -10