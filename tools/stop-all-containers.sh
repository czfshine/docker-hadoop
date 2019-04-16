#!/bin/bash

if [$# = 1]; then
	N=$1
else
	# the default node number is 3
	N=${1:-3}
fi
sudo docker rm -f hadoop-master 
i=1
while [ $i -lt $N ]
do
	sudo docker rm -f hadoop-slave$i 
	i=$(( $i + 1 ))
done 
