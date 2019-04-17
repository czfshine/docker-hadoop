#!/usr/bin/env bash
#在宿主机运行
#将项目目录下的有关文件复制到~/hadoop/下,这样可以在docker容器内获取到
set -v
#复制
cp -r generater ~/hadoop/
cp -f runner/*.sh ~/hadoop/

#生成测试数据
cd ~/hadoop/
python generater/wordcount.py