
## ***Java相关

### 设置环境变量
- JAVA_HOME: C:\Program Files\Java\jdk1.8.0_111
- CLASSPATH: .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
- PATH
    - %Java_Home%\bin
    - %Java_Home%\jre\bin
    
### Windows 下查看端口占用并杀掉该进程(TODO)
- netstat -ano |findstr "端口号"

### Postman 使用

#### 传递时间参数
- postman.setGlobalVariable("now",Date.parse(new Date()));
	
### IDEA 使用

#### 显示方法分隔符
File –> settings –> Editor –> General -> Appearance –> 勾选 Show method separators

#### 单行显示多个 Tabs
File –> settings –> Editor –> General -> Editor Tabs –> Show tabs in single row 去掉√

#### 

#### 同一 SpringBoot 项目，不同端口，允许同时运行
Run 下拉框旁的 Edit configuration，勾选 Allow parallel run 

#### 配置类文档注释模板
- File –> settings –> Editor –> File and Code Templates –> Files

#### 修改 Maven 本地仓库地址
- 在自定义盘符下创建仓库目录(如D:/maven/.m2/repository)	
- 修改 Maven 的配置文件 settings.xml
```
<!-- 配置本地仓库路径 -->
<localRepository>D:\maven\.m2\repository</localRepository>
```
- 修改 IDEA 中的 Maven 配置
	- File -> Settings -> Maven -> 修改 Local repository 为本地仓库目录地址

## ***SprintBoot相关

### SpringBoot 项目终端打包命令
- mvn package clean -Dmaven.test.skip=true // 把之前打过的包通通干掉。
- mvn package -Dmaven.test.skip=true	// 重新打包。

### SpringBoot 项目启动时找不到或无法加载主类
- 解决：在 idea 控制台输入 mvn clean install 命令

### SpringBoot 项目打成 jar 包设置开机自启动
- 1、jar 包放置在指定位置
- 2、新建文件 xxx.bat 内容为jar包启动命令：java -jar E:\jar\xxx-1.0.0.1.jar(此时双击文件可以启动项目)
- 3、开始 -> 运行 -> 输入 gpedit.msc 确定 -> 计算机配置 -> windows 设置 -> 脚本(启动/关机)
	-> 双击启动 -> 添加，选择xxx.bat 应用。

## ***数据库相关

### sql命令

#### sql命令导入sql文件
`source xxx.sql`

#### 导出整个数据库
`mysqldump -u dbuser -p dbname > dbname.sql`(保存在当前目录下)

#### 查看表占用硬盘空间大小的 SQL 语句如下：(用M做展示单位，数据库名：test，表名：hello)
```
select concat(round(sum(DATA_LENGTH/1024/1024),2),'M') as table_size 
from information_schema.tables 
where table_schema='test' AND table_name='hello';
```

#### Mysql 添加用户并给用户赋予不同的权限
- 本地访问用户：
```
create user 'front'@'localhost' identified by '123456';
```
- 允许外网 IP 访问
```
create user 'front'@'%' identified by '123456';
```
- 授予用户通过外网IP对于该数据库的全部权限
```
grant all privileges on `testdb`.* to 'front'@'%' identified by '123456';
```
- 授予用户在本地服务器对该数据库的全部权限
```
grant all privileges on `testdb`.* to 'front'@'localhost' identified by '123456';
```
- 授予该用户通过外网IP对该服务器上所有数据库的全部权限
```
grant all privileges on *.* to 'front'@'%' identified by '123456';
```
- 授予用户在本地服务器对该服务器上所有数据库的全部权限
```
grant all privileges on *.* to 'front'@'localhost' identified by '123456';
```
- 刷新权限
```
flush privileges;
```

### 数据库相关问题
	
#### 数据库连接时报错 Public Key Retrieval is not allowed.
- 解决方案：url增加 &allowPublicKeyRetrieval=true
	
#### Expression #1 of SELECT list is not in GROUP BY clause and contains nonaggre
- 原因：MySQL 5.7.5及以上功能依赖检测功能。如果启用了ONLY_FULL_GROUP_BY SQL模式(默认情况下)，MySQL将拒绝选择列表
- 解决：select @@global.sql_mode，去掉ONLY_FULL_GROUP_BY，重新设置值。
    - set @@global.sql_mode='NO_ENGINE_SUBSTITUTION';	// Mysql 服务重启后可能会变化？？？
		
#### MySql的四种Blob类型
``` 
tinyblob(225B)
blob(65K)
mediunblob(16M)
longblob(4G)
```
#### Mysql增加触发器示例
```
create trigger tri_test_update
after update on test_update_trigger
for each row
begin
insert into test_log(update_id,log_text) values(NEW.id,concat(cast(NEW.age as char),'修改为：',cast(OLD.age as char)));
end
```

#### Mysql 开启并查看 binlog(Windows10中)
- 查询 binlog 是否开启
```
show global variables like '%log_bin%';
```
- 修改 C:\ProgramData\MySQL\MySQL Server 5.7 下的 my.ini 文件如下
```
# Binary Logging.
log-bin=mysql-bin
binlog-format=Row
```
- 查看 binlog 日志(TODO)

#### Mysql 开启 general_log(Windows10中)
- 查询 general_log 是否开启
```
show global variables like '%general%';
```
- 修改 C:\ProgramData\MySQL\MySQL Server 5.7 下的 my.ini 文件如下
```
general-log=1
```

#### 数据库安全防范措施建议
- 日志记录
    - 在业务允许的条件下，尽可能的开启详尽的日志记录，以便在案发后溯源追踪。包括但不限于操作系统日志、审计日志、Web访问日志、数据库连接登录日志、数据操作日志等。
- 数据备份
    - 定时进行关键数据的备份存储，遇到勒索加密也能从容应对。
- 密码强度
    - 不要用弱口令，数字、字母大小写、特殊符号一起上，一个好的密码可以帮你抗住一大半的攻击可能。
- 定期修改密码
    - 就算用上了高强度密码，也不是一劳永逸，除了技术攻击，还有社会工程学，一个密码走遍天下风险极高，时不时修改密码也是非常有必要的。
- 防火墙
    - 防火墙的重要性就不必多说了，用好防火墙，将绝大多数攻击者拒之门外。
- 专业的安全产品
    - 对于企业和政府单位，还需要专业级的安全产品，比如全流量分析产品用于案件回溯，找到对方是怎么进来的，便于找出系统漏洞，及时修补。  

### Navicat 激活步骤
- 双击 Navicat Keygen Patch v5.6.0 DFoX,点击 Patch -> Cracked
- 打开 Navicat，点击"注册"，回到 Patch 注册机，点击 Generate，将注册码填入 Navicat 点击"激活" 
- 点击手动激活，将"请求码"复制到注册机中，点击左下角的 Generate，将生成的激活码粘贴到 Navicat 即可

## ***中间件工具

### RabbitMQ
- Erlang
    - RabbitMQ 服务端代码是使用并发式语言 Erlang 编写的，安装 RabbitMQ 的前提是安装 Erlang
    - https://www.erlang.org/downloads 下载 Erlang 并安装
    - 配置 Erlang 环境变量
    ```
    C:\Program Files\erl7.1\bin
    ```
    - 终端输入 erl，显示版本号即为正确安装
- RabbitMQ
    - https://www.rabbitmq.com/download.html 下载 RabbitMQ 并安装
    - RabbitMQ 安装好后接下来安装 RabbitMQ-Plugins。打开命令行cmd，输入 RabbitMQ 的 sbin 目录，输入如下命令
    ```
    rabbitmq-plugins enable rabbitmq_management
    ```
    - 在当前目录下执行`rabbitmqctl status`，显示状态信息即为正确安装
- 双击 sbin 目录下的 rabbitmq-server.bat，访问http://localhost:15672，初始用户名密码分别为 guest、guest


## ***控制版本工具

### git 本地仓库初始化并提交到远程仓库
- git init
- git add .
- git commit -m "first commit"
- git branch -M main
- git remote add origin git@github.com:lingfeng23/foobar.git
- git push -u origin main

### git 拉取远程仓库
- git stash					// 备份当前的工作区的内容
- git pull/sourceTree点击拉取	// 拉取远程仓库 
- git stash pop				// 恢复工作区的相关内容

### git 提交步骤(VSCode)
- 修改文件
- 新建功能：功能名称 v1.0.1-xxx，基于 develop 新建分支 feature-xxx
- 在 VSCode 中执行 
	- git add . // 添加当前目录的所有文件到暂存区
	- npm run commit，填写描述等 // 提交暂存区到仓库区
		- Select the type of change that you're committing: feat:     A new feature
		- What is the scope of this change (e.g. component or file name): (press enter to skip) add thumbnail name
		- Write a short, imperative tense description of the change (max 74 chars):
		 (8) dbupdate
		- Provide a longer description of the change: (press enter to skip)
		 dbupdate
		- Are there any breaking changes? No
		- Does this change affect any open issues? No
	- git push // 推送
- sourceTree完成功能，合并到develop，删除分支
- 点击推送

### Git Flow

- Production 分支[master]
  也就是我们经常使用的 master 分支，这个分支最近发布到生产环境的代码，最近发布的 Release， 这个分支只能从其他分支合并，不能在这个分支直接修改

- Develop 分支[develop]
  这个分支是我们是我们的主开发分支，包含所有要发布到下一个 Release 的代码，这个主要合并与其他分支，比如 Feature 分支
  tag: dev-版本号-年月日时分

- Feature 分支[feature/[v0.0.1-***]]
  这个分支主要是用来开发一个新的功能，一旦开发完成，我们合并回 Develop 分支进入下一个 Release
  版本号为下一版本版本号
  tag: fea-版本号-年月日时分

- Release 分支[release/[v0.0.1-***]]
  当你需要一个发布一个新 Release 的时候，我们基于 Develop 分支创建一个 Release 分支，完成 Release 后，我们合并到 [Master]和[Develop]分支
  版本号为下一版本版本号
  tag: qa-版本号-[issues]-年月日时分

- Hotfix 分支[hotfix/[v0.0.1-***]]
  当我们在 Production 发现新的 Bug 时候，我们需要创建一个 Hotfix, 完成 Hotfix 后，我们合并回 Master 和 [Develop]分支，所以[Hotfix]的改动会进入下一个[Release]
  版本号为当前修正版本版本号
  tag: fix-版本号-[issues]-年月日时分
  

## ***操作系统相关
### Linux 操作相关

#### 常用的 Linux 命令

##### 文件和目录操作命令
- ls：全拼list，功能是列出目录的内容及其内容属性信息。
- cd：全拼change directory，功能是从当前工作目录切换到指定的工作目录。
- cp：全拼copy，其功能为复制文件或目录。
- find：查找的意思，用于查找目录及目录下的文件。
- mkdir：全拼make directories，其功能是创建目录。
- mv：全拼move，其功能是移动或重命名文件。
- pwd：全拼print working directory，其功能是显示当前工作目录的绝对路径。
- rename：用于重命名文件。
- rm：全拼remove，其功能是删除一个或多个文件或目录。
- rmdir：全拼remove empty directories，功能是删除空目录。
- touch：创建新的空文件，改变已有文件的时间戳属性。
- tree：功能是以树形结构显示目录下的内容。
- basename：显示文件名或目录名。
- dirname：显示文件或目录路径。
- chattr：改变文件的扩展属性。
- lsattr：查看文件扩展属性。
- file：显示文件的类型。
- md5sum：计算和校验文件的MD5值。
##### 查看文件及内容处理命令
- cat：全拼concatenate，功能是用于连接多个文件并且打印到屏幕输出或重定向到指定文件中。
- tactac：是cat的反向拼写，因此命令的功能为反向显示文件内容。
- more：分页显示文件内容。
- less：分页显示文件内容，more命令的相反用法。
- head：显示文件内容的头部。
- tail：显示文件内容的尾部。
- cut：将文件的每一行按指定分隔符分割并输出。    
- split：分割文件为不同的小片段。
- paste：按行合并文件内容。
- sort：对文件的文本内容排序。
- uniq：去除重复行。
- wc：统计文件的行数、单词数或字节数。
- iconv：转换文件的编码格式。
- dos2unix：将DOS格式文件转换成UNIX格式。
- diff：全拼difference，比较文件的差异，常用于文本文件。
- vimdiff：命令行可视化文件比较工具，常用于文本文件。
- rev：反向输出文件内容。
- grep/egrep：过滤字符串，三剑客老三。
- join：按两个文件的相同字段合并。
- tr：替换或删除字符。
- vi/vim：命令行文本编辑器。

##### 文件压缩及解压缩命令
- tar：打包压缩。oldboy
- unzip：解压文件。
- gzipgzip：压缩工具。
- zip：压缩工具

##### 信息显示命令
uname：显示操作系统相关信息的命令。
hostname：显示或者设置当前系统的主机名。
dmesg：显示开机信息，用于诊断系统故障。
uptime：显示系统运行时间及负载。
stat：显示文件或文件系统的状态。
du：计算磁盘空间使用情况。
df：报告文件系统磁盘空间的使用情况。
top：实时显示系统资源使用情况。
free：查看系统内存。
date：显示与设置系统时间。
cal：查看日历等时间信息。

##### 搜索文件命令
which：查找二进制命令，按环境变量PATH路径查找。
find：从磁盘遍历查找文件或目录。
whereis：查找二进制命令，按环境变量PATH路径查找。
locate：从数据库 (/var/lib/mlocate/mlocate.db) 查找命令，使用updatedb更新库。

##### 用户管理命令
useradd：添加用户。
usermod：修改系统已经存在的用户属性。
userdel：删除用户。
groupadd：添加用户组。
passwd：修改用户密码。
chage：修改用户密码有效期限。
id：查看用户的uid,gid及归属的用户组。
su：切换用户身份。
visudo：编辑/etc/sudoers文件的专属命令。
sudo：以另外一个用户身份(默认root用户)执行事先在sudoers文件允许的命令。

##### 基础网络操作命令
telnet：使用TELNET协议远程登录。
ssh：使用SSH加密协议远程登录。
scp：全拼secure copy，用于不同主机之间复制文件。
wget：命令行下载文件。
ping：测试主机之间网络的连通性。
route：显示和设置linux系统的路由表。
ifconfig：查看、配置、启用或禁用网络接口的命令。
ifup：启动网卡。
ifdown：关闭网卡。
netstat：查看网络状态。
ss：查看网络状态。

##### 深入网络操作命令
nmap：网络扫描命令。
lsof：全名list open files，也就是列举系统中已经被打开的文件。
mail：发送和接收邮件。
mutt：邮件管理命令。
nslookup：交互式查询互联网DNS服务器的命令。
dig：查找DNS解析过程。
host：查询DNS的命令。
traceroute：追踪数据传输路由状况。
tcpdump：命令行的抓包工具。

##### 有关磁盘与文件系统的命令
mount：挂载文件系统。
umount：卸载文件系统。
fsck：检查并修复Linux文件系统。
dd：转换或复制文件。
dumpe2fs：导出ext2/ext3/ext4文件系统信息。
dumpe：xt2/3/4文件系统备份工具。
fdisk：磁盘分区命令，适用于2TB以下磁盘分区。
parted：磁盘分区命令，没有磁盘大小限制，常用于2TB以下磁盘分区。
mkfs：格式化创建Linux文件系统。
partprobe：更新内核的硬盘分区表信息。
e2fsck：检查ext2/ext3/ext4类型文件系统。
mkswap：创建Linux交换分区。
swapon：启用交换分区。
swapoff：关闭交换分区。
sync：将内存缓冲区内的数据写入磁盘。
resize2fs：调整ext2/ext3/ext4文件系统大小。

##### 系统权限及用户授权相关命令
chmod：改变文件或目录权限。
chown：改变文件或目录的属主和属组。
chgrp：更改文件用户组。
umask：显示或设置权限掩码。

##### 查看系统用户登陆信息的命令
whoami：显示当前有效的用户名称，相当于执行id -un命令。
who：显示目前登录系统的用户信息。
w：显示已经登陆系统的用户列表，并显示用户正在执行的指令。
last：显示登入系统的用户。
lastlog：显示系统中所有用户最近一次登录信息。
users：显示当前登录系统的所有用户的用户列表。
finger：查找并显示用户信息。
    
##### 内置命令及其它
echo：打印变量，或直接输出指定的字符串
printf：将结果格式化输出到标准输出。
rpm：管理rpm包的命令。
yum：自动化简单化地管理rpm包的命令。
watch：周期性的执行给定的命令，并将命令的输出以全屏方式显示。
alias：设置系统别名。
unalias：取消系统别名。
date：查看或设置系统时间。
clear：清除屏幕，简称清屏。
history：查看命令执行的历史纪录。
eject：弹出光驱。
time：计算命令执行时间。
nc：功能强大的网络工具。
xargs：将标准输入转换成命令行参数。
exec：调用并执行指令的命令。
export：设置或者显示环境变量。
unset：删除变量或函数。
type：用于判断另外一个命令是否是内置命令。
bc：命令行科学计算器。

##### 系统管理与性能监视命令
chkconfig：管理Linux系统开机启动项。
vmstat：虚拟内存统计。
mpstat：显示各个可用CPU的状态统计。
iostat：统计系统IO。
sar：全面地获取系统的CPU、运行队列、磁盘 I/O、分页(交换区)、内存、 CPU中断和网络等性能数据。
ipcs：用于报告Linux中进程间通信设施的状态，显示的信息包括消息列表、共享内存和信号量的信息。
ipcrm：用来删除一个或更多的消息队列、信号量集或者共享内存标识。
strace：用于诊断、调试Linux用户空间跟踪器。我们用它来监控用户空间进程和内核的交互，比如系统调用、信号传递、进程状态变更等。
ltrace：命令会跟踪进程的库函数调用,它会显现出哪个库函数被调用。

##### 关机/重启/注销和查看系统信息的命令
shutdown：关机。
halt：关机。
poweroff：关闭电源。
logout：退出当前登录的Shell。
exit：退出当前登录的Shell。
Ctrl+d：退出当前登录的Shell的快捷键。

##### 进程管理相关命令
bg：将一个在后台暂停的命令，变成继续执行 (在后台执行)。
fg：将后台中的命令调至前台继续运行。
jobs：查看当前有多少在后台运行的命令。
kill：终止进程。
killall：通过进程名终止进程。
pkill：通过进程名终止进程。
crontab：定时任务命令。
ps：显示进程的快照。
pstree：树形显示进程。
nice/renice：调整程序运行的优先级。
nohup：忽略挂起信号运行指定的命令。
pgrep：查找匹配条件的进程。
runlevel：查看系统当前运行级别。
init：切换运行级别。
service：启动、停止、重新启动和关闭系统服务，还可以显示所有系统服务的当前状态。

##### 



### CentOS 操作相关

#### 常用的 Centos 命令
- 修改文件的只读/读写属性(到文件目录下执行)
	- chmod 666 xxx.txt(将文件属性改为读写)	
	- chmod 644 xxx.txt(将文件属性改为只读)

- 在Centos中创建文件
	- touch xxxxxx【文件名】
- 关闭防火墙
	- 1. 关闭防火墙：sudo systemctl stop firewalld.service
　　- 2. 关闭开机启动：sudo systemctl disable firewalld.service



## ***MidWay相关

### 常用命令
- npm run code // 执行自定义脚本，生成相关文件

### Sequelize
- 初始化数据库表：
	npx sequelize migration:generate --name=init-users
- 执行 migrate 进行数据库变更
	npx sequelize db:migrate
	- 单独执行一个种子文件
	npx sequelize db:seed --seed xxxxxx-yyy.js
	- 升级数据库调试命令
	node --inspect-brk=32000  node_modules\sequelize-cli\lib\sequelize db:migrate
	- 调试地址：chrome://inspect/#devices
	启动调试命令后打开地址，点击页面中 Remote Target 下面按钮进入调试页面

### yarn命令使用
- npm i yarn -g
	- -i：install 
	- -g：全局安装(global),使用 -g 或 --global
- yarn -version  yarn版本号
- 常用命令：
	- yarn / yarn install 等同于npm install 批量安装依赖
	- yarn add xxx 等同于 npm install xxx —save 安装指定包到指定位置
	- yarn remove xxx 等同于 npm uninstall xxx —save 卸载指定包
	- yarn add xxx —dev 等同于 npm install xxx —save-dev
	- yarn upgrade 等同于 npm update 升级全部包
	- yarn global add xxx 等同于 npm install xxx -g 全局安装指定包
	
## Docker相关

### Docker基本概念
- Client(客户端)： 	
	- 是Docker的用户端，可以接受用户命令和配置标识，并与Docker daemon通信。
- Images(镜像)： 	 				
	- 是一个只读模板，含创建Docker容器的说明，它与操作系统的安装光盘有点像。
- Containers(容器)： 
	- 镜像的运行实例，镜像与容器的关系类比面向对象中的类和对象。
- Registry(仓库)： 
	- 是一个集中存储与分发镜像的服务。最常用的Registry是官方的Docker Hub 。

### Dockerz组件
- Docker客户端和服务器
- Dokcer镜像
- Registry
- Docker容器

### Docker容器就是
- 一个镜像格式
- 一系列标准的操作
- 一个知性环境

### Docker常用命令

#### 镜像控制
- docker  search  [OPTIONS]  TERM // 搜索镜像
- docker  push  [OPTIONS]  NAME[:TAG] // 上传镜像
- docker  pull  [OPTIONS]  NAME[:TAG] // 下载镜像
- docker  commit [OPTIONS]  CONTAINER  NAME[:TAG] // 提交镜像
- docker  build  [OPTIONS]  PATH // 构建镜像
- docker  rmi [OPTIONS]  IMAGE  [IMAGE...] // 删除镜像
- docker  tag  SOURCE_IMAGE[:TAG]  TARGET_IMAGE[:TAG] // 增加镜像标签
- docker  images  [OPTIONS]  [REPOSITORY[:TAG]] // 查看所有镜像

#### 容器控制
- docker start/restart CONTAINER // 启动/重启容器
- docker stop/ kill CONTAINER // 停止/强停容器
- docker rm [OPTIONS] CONTAINER [CONTAINER...] // 删除容器(需要容器是停止的)
- docker rename CONTAINER CONTAINER_NEW // 重命名容器
- docker attach CONTAINER // 进入容器
- docker exec CONTAINER COMMAND // 执行容器命令
- docker -f logs [OPTIONS] CONTAINER // 查看容器日志，[-f]用来监控docker日志
- docker ps [OPTIONS] // 查看容器列表
- sudo docker inspect CONTAINER // 容器具体信息
- sudo docker top CONTAINER // 查看容器内的进程


#### 容器启动
- docker  run  [OPTIONS]  IMAGE  [COMMAND]  [ARG...]
	- -d : 后台运行容器，并返回容器ID
	- -i：以交互模式运行容器，通常与 -t 同时使用	
	- -t：为容器重新分配一个伪输入终端，通常与 -i 同时使用
	- -v：绑定挂载目录
	- --name="mycontainer": 为容器指定一个名称
	- --net="bridge": 指定容器的网络连接类型
	
#### 其他命令
- sudo docker info // 确保Docker已经就绪，查看docker信息
- docker run --help // docker命令帮助
- docker cp custom.conf Nginx:/etc/nginx/conf.d/ // 复制文件到容器
- docker container update --restart=always nginx // 更新容器启动项
- tail -f /var/log/messages // 查看docker日志

### 有三种方式可以指代唯一容器
- UUID(如：db4deb80d1c4)
- 长UUID(如：db4ddb4deb80d1c4edb4db4deb80d1c4deb80d1c4b80d1c4)
- 名称(如：my_cat)


## Node.js相关

### Node.js能做什么
- 具有复杂逻辑的网站；
- 基于社交网络的大规模 Web 应用；
- Web Socket 服务器；
- TCP/UDP 套接字应用程序；
- 命令行工具；
- 交互式终端程序；
- 带有图形用户界面的本地应用程序；
- 单元测试工具；
- 客户端 JavaScript 编译器。

### supervisor 会监视你对代码的改动，并自动重启 Node.js。
- npm install -g supervisor // 安裝 supervisor
- supervisor app.js // 使用 supervisor 命令启动 app.js

### 同步式 I/O 和异步式 I/O 的特点
|同步式 I/O(阻塞式)|异步式 I/O(非阻塞式)|
|----|----|
|利用多线程提供吞吐量|单线程即可实现高吞吐量|
|通过事件片分割和线程调度利用多核CPU|通过功能划分利用多核CPU|
|需要由操作系统调度多线程使用多核 CPU|可以将单进程绑定到单核 CPU|
|难以充分利用 CPU 资源|可以充分利用 CPU 资源|
|内存轨迹大，数据局部性弱|内存轨迹小，数据局部性强|
|符合线性的编程思维|不符合传统编程思维|

### 严格符合 CommonJS 规范的包应该具备以下特征：
- package.json 必须在包的顶层目录下；
- 二进制文件应该在 bin 目录下；
- JavaScript 代码应该在 lib 目录下；
- 文档应该在 doc 目录下；
- 单元测试应该在 test 目录下。
	- Node.js 对包的要求并没有这么严格，只要顶层目录下有 package.json，并符合一些规范即可。

### package.json 是 CommonJS 规定的用来描述包的文件，完全符合规范的 package.json 文件应该含有以下字段。
- name ：包的名称，必须是唯一的，由小写英文字母、数字和下划线组成，不能包含空格。
- description ：包的简要说明。
- version ：符合语义化版本识别规范的版本字符串。
- keywords ：关键字数组，通常用于搜索。
- maintainers ：维护者数组，每个元素要包含 name 、 email (可选)、 web (可选)字段。
- contributors ：贡献者数组，格式与 maintainers 相同。包的作者应该是贡献者数组的第一个元素。
- bugs ：提交bug的地址，可以是网址或者电子邮件地址。
- licenses ：许可证数组，每个元素要包含 type (许可证的名称)和  url (链接到许可证文本的地址)字段。
- repositories ：仓库托管地址数组，每个元素要包含 type  (仓库的类型，如  git )、url (仓库的地址)和  path (相对于仓库的路径，可选)字段。
- dependencies ：包的依赖，一个关联数组，由包名称和版本号组成。

### 本地模式与全局模式
|模 式|可通过 require 使用|注册PATH|
|----|----|----|
|本地模式|是|否|
|全局模式|否|是|
- 当我们要把某个包作为工程运行时的一部分时，通过本地模式获取，如果要
在命令行下使用，则使用全局模式安装。

### Node.js 调试命令
|命 令|功 能|
|----|----|
|run|执行脚本，在第一行暂停|
|restart|重新执行脚本|
|cont, c|继续执行，直到遇到下一个断点|
|next, n|单步执行|
|step, s|单步执行并进入函数|
|out, o|从函数中步出|
|setBreakpoint(), sb()|在当前行设置断点|
|setBreakpoint(‘f()’), sb(...)|在函数f的第一行设置断点|
|setBreakpoint(‘script.js’, 20), sb(...)|在 script.js 的第20行设置断点|
|clearBreakpoint, cb(...)|清除所有断点|
|backtrace, bt|显示当前的调用栈|
|list(5)|显示当前执行到的前后5行代码|
|watch(expr)|把表达式 expr 加入监视列表|
|unwatch(expr)|把表达式 expr 从监视列表移除|
|watchers|显示监视列表中所有的表达式和值|
|repl|在当前上下文打开即时求值环境|
|kill|终止当前执行的脚本|
|scripts|显示当前已加载的所有脚本|
|version|显示 V8 的版本|

### 全局对象
- process：用于描述当前 Node.js 进程状态的对象，提供了一个与操作系统的简单接口。
- console  用于提供控制台标准输出，它是由 Internet Explorer 的 JScript 引擎提供的调试工具，后来逐渐成为浏览器的事实标准。Node.js沿用了这个标准，提供与习惯行为一致的
console 对象，用于向标准输出流( stdout )或标准错误流( stderr )输出字符。

### 常用工具 util
- util.inherits(constructor, superConstructor) 是一个实现对象间原型继承的函数。
- util.inspect(object,[showHidden],[depth],[colors]) 是一个将任意对象转换为字符串的方法，通常用于调试和错误输出。

### 事件驱动 events(略)

### 文件系统 fs
- fs 模块是文件操作的封装，它提供了文件的读取、写入、更名、删除、遍历目录、链接等 POSIX 文件系统操作。fs 模块中所有的操作都提供了异步的和
同步的两个版本
#### fs 模块函数表
|功 能|异步函数|同步函数|
|----|----|----|
|打开文件|fs.open(path,flags, [mode], [callback(err,fd)])|fs.openSync(path, flags, [mode])|
|关闭文件|fs.close(fd, [callback(err)])|fs.closeSync(fd)|
|读取文件(文件描述符)|fs.read(fd,buffer,offset,length,position,[callback(err, bytesRead, buffer)])|fs.readSync(fd, buffer, offset,length, position)|
|写入文件(文件描述符)|fs.write(fd,buffer,offset,length,position,[callback(err, bytesWritten, buffer)])|fs.writeSync(fd, buffer, offset,length, position)|
|读取文件内容|fs.readFile(filename,[encoding],[callback(err, data)])|fs.readFileSync(filename,[encoding])|
|写入文件内容|fs.writeFile(filename, data,[encoding],[callback(err)])|fs.writeFileSync(filename, data,[encoding])|
|删除文件|fs.unlink(path, [callback(err)])|fs.unlinkSync(path)|
|创建目录|fs.mkdir(path, [mode], [callback(err)])|fs.mkdirSync(path, [mode])|
|删除目录|fs.rmdir(path, [callback(err)])|fs.rmdirSync(path)|
|读取目录|fs.readdir(path, [callback(err, files)])|fs.readdirSync(path)|
|获取真实路径|fs.realpath(path, [callback(err,resolvedPath)])|fs.realpathSync(path)|
|更名|fs.rename(path1, path2, [callback(err)])|fs.renameSync(path1, path2)|
|截断|fs.truncate(fd, len, [callback(err)])|fs.truncateSync(fd, len)|
|更改所有权|fs.chown(path, uid, gid, [callback(err)])|fs.chownSync(path, uid, gid)|
|更改所有权(文件描述符)|fs.fchown(fd, uid, gid, [callback(err)])|fs.fchownSync(fd, uid, gid)|
|更改所有权(不解析符号链接)|fs.lchown(path, uid, gid, [callback(err)])|fs.lchownSync(path, uid, gid)|
|更改权限|fs.chmod(path, mode, [callback(err)])|fs.chmodSync(path, mode)|
|更改权限(文件描述符)|fs.fchmod(fd, mode, [callback(err)])|fs.fchmodSync(fd, mode)|
|更改权限(不解析符号链接)|fs.lchmod(path, mode, [callback(err)])|fs.lchmodSync(path, mode)|
|获取文件信息|fs.stat(path, [callback(err, stats)])|fs.statSync(path)|
|获取文件信息(文件描述符)|fs.fstat(fd, [callback(err, stats)])|fs.fstatSync(fd)|
|获取文件信息(不解析符号链接)|fs.lstat(path, [callback(err, stats)])|fs.lstatSync(path)|
|创建硬链接|fs.link(srcpath, dstpath, [callback(err)])|fs.linkSync(srcpath, dstpath)|
|创建符号链接|fs.symlink(linkdata, path, [type],[callback(err)])|fs.symlinkSync(linkdata, path,[type])|
|读取链接|fs.readlink(path, [callback(err,linkString)])|fs.readlinkSync(path)|
|修改文件时间戳|fs.utimes(path, atime, mtime, [callback(err)])|fs.utimesSync(path, atime, mtime)|
|修改文件时间戳(文件描述符)|fs.futimes(fd, atime, mtime, [callback(err)])|fs.futimesSync(fd, atime, mtime)|
|同步磁盘缓存|fs.fsync(fd, [callback(err)])|fs.fsyncSync(fd)|

### HTTP服务器与客户端
