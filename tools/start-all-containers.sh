#!/bin/bash

if [$# = 1]; then
	N=$1
else
	# the default node number is 3
	N=${1:-3}
fi

# start hadoop master container
sudo docker rm -f hadoop-master 
echo "start hadoop-master container..."
sudo docker run	-itd \
                --net=hadoop-bridge \
                -p 50070:50070 \
                -p 8088:8088 \
                --name hadoop-master \
                --hostname hadoop-master \
				-v ~/hadoop:/data\
                czfshine/hadoop-base:2.7.7 

# start hadoop slave container
i=1
while [ $i -lt $N ]
do
	sudo docker rm -f hadoop-slave$i 
	echo "start hadoop-slave$i container..."
	sudo docker run -itd \
	                --net=hadoop-bridge \
	                --name hadoop-slave$i \
	                --hostname hadoop-slave$i \
	                -v ~/hadoop:/data\
					czfshine/hadoop-base:2.7.7 
	i=$(( $i + 1 ))
done 

# get into hadoop master container
sudo docker exec -it hadoop-master bash
