# docker-hadoop
A dockerfile for setting up a full Hadoop cluster server 

一套在ubuntu 下生成和部署Hadoop的Docker镜像的配置文件集与示例。

包括:

* [ ] 环境配置文件

* [ ] 生成 docker image 的dockerfile 

* [ ] 启动和配置docker的docker-compose的配置文件

* [ ] IDEA 的相关配置文件

* [ ] 几个简单的示例项目


[TOP]

# -1. 基础概念

* Hadoop 一个大数据的分布式计算框架
* Docker 一个轻量级的容器服务

> Hadoop 是一个分布式的计算机框架,显然要有多台机器.这时候就需要有多台机器.可以用虚拟机模拟,但是虚拟机太重,性能损耗大.
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

# 2.测试镜像和启动

# 3. docker-compose 配置和启动

# 4.IDEA的配置

# 5. 示例项目

