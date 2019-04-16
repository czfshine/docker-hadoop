FROM czfshine/openjdk8:latest

# 安装ssh服务
RUN apt-get update && \
    apt-get install -y openssh-server && \
    apt-get clean &&\
    apt-get autoclean && \
    rm -rf /var/lib/apt/lists/*

#自己免密登录自己,因为所有hadoop镜像都是基于这个镜像,
#所以,每个节点的公钥和私钥都一样

RUN ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

#启动时启动ssh 服务
CMD [ "sh", "-c", "service ssh start; bash"]