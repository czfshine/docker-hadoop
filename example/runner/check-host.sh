#!/usr/bin/env bash
#检查主机名

name=`hostname`
if [[ ! $name == "hadoop-master" ]];then
    echo $name
    echo "要在hadoop的master机器执行这个脚本"
    exit 1
fi
exit 0