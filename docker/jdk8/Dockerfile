FROM ubuntu:xenial

#设置清华大学镜像站的软件源,快一点
COPY sources.list /etc/apt/sources.list
# 安装openjdk
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get clean &&\
    apt-get autoclean && \
    rm -rf /var/lib/apt/lists/*
# 环境变量
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

CMD [ "sh", "-c", "bash"]