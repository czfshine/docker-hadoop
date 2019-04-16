

# docker-hadoop

A dockerfile for setting up a full Hadoop cluster server 

一套在ubuntu 下生成和部署Hadoop的Docker镜像的配置文件集与示例。

包括:

* [ ] 环境配置文件
* [ ] 生成 docker image 的dockerfile 
* [ ] 启动和配置docker的docker-compose的配置文件
* [ ] IDEA 的相关配置文件
* [ ] 一些文章博客教程
* [ ] 几个简单到复杂的示例项目





[TOC]

# -1. 基础概念

* Hadoop 一个大数据的分布式计算框架
* Docker 一个轻量级的容器服务

> Hadoop 是一个分布式的计算机框架,显然要有多台机器(当然可以搭伪分布式的).这时候就需要有多台机器.可以用虚拟机模拟,但是虚拟机太重,性能损耗大.
>
> Docker 是一个轻量级的容器服务,相当于一个虚拟机,不过损耗的资源较小

* Docker 镜像 包括运行一个程序所需的所有文件
* Volumes 卷 一个Docker的图层,作用于某个正在运行的容器的镜像上面

> Docker镜像一旦生成即不可变的,但是我们的程序要生成或获取一些动态的文件.Volume就是来解决这个问题的,它将Host的一个路径与容器的一个路径关联起来,容器读写该路径下的文件会被代理到Host关联的路径下.

* 端口映射 将容器的端口和主机的端口关联起来

> 我们的Hadoop 的datanode和namenode什么的都会监听同样的端口,我们将这些端口映射到主机的不同端口上,可以从主机访问各个容器的状态.

* docker-compose 用来启动和配置多个镜像

> 我们启动一个hadoop的docker集群,必然有多个容器要先后启动,他们的端口和文件映射都需要独立的进行配置,docker-compose是一个用来的帮助我们完成这些功能的小工具



# 0.安装依赖

在宿主机上需要用到的程序大概有(不完全):

* `docker-ce` 社区版就行

* `maven` java 的库和项目管理程序

* `idea` java的IDE

   

# 1.生成镜像



### 1.0 前言

> 其实很多人(包括我)都搭建过镜像,然后上传到Docker Hub的,区别(可能)只是里面的配置文件不一样,所以不想自己搭建的可以用搭好的,然后再改改.不过不保证可用性,下面是一些~~看起来~~比较有用的镜像:
>
> * https://hub.docker.com/r/sequenceiq/hadoop-docker
>
>    

### 1.1 参考资料

* https://github.com/docker/labs/tree/master/developer-tools/java/  官方的docker java 示例
  * https://github.com/docker/labs/tree/master/developer-tools/java-debugging 同上
* https://hub.docker.com/_/openjdk openjdk的docker 

### 1.2 镜像继承结构 

* `ubuntu:xenial` 基础的ubuntu系统镜像
  * `hadoop-base` 包括jdk,hadoop等需要用到的安装包
    * `hadoop-master` 主机
    * `hadopp-slave` 计算节点

### 1.3 基础镜像搭建
我们要将运行hadoop所需的文件,程序安装到该镜像

> 这里分成多个不同层次的镜像构建,还没研究docker构建的缓存细节,所以这样当做手动使用缓存,不需要每次都从零开始构建
* 先搭个jdk8的环境 
> todo :这里为了省事直接apt get的,比较大,下一次改成解压二进制包试试大小
```
cd ./docker/jdk8
docker build -t czfshine/openjdk8 .
```
测试一下:
```
docker run -t -i czfshine/openjdk8:latest
java -version
```
* 然后是ssh
```
cd ./docker/ssh
docker build -t czfshine/ssh .
```
同样的测试一下
```
docker run -t -i czfshine/ssh
ssh localhost
```

下面正式构建hadoop镜像了

* 首先cd到`docker/hadoop-base`文件夹下
* 执行`./download.sh` 它将从清华大学镜像站下载2.7.7版本的Hadoop
    这一步当然可以写在Dockerfile里面,可是考虑到每一次构建镜像都要下载一次太慢了,就算了:)
* 执行`clean.sh` 删掉hadoop里面的文档,这些我们不需要打包到镜像的

```
 docker build -t czfshine/hadoop-base:2.7.7 .
```

# 2.测试镜像和启动

# 3. docker-compose 配置和启动

# 4.IDEA的配置

# 5. 示例项目

