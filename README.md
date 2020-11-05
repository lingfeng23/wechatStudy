## 面试集锦

### Java 基础 + 集合 + 多线程 + JVM

#### Java 基础

#### JVM

#### Java 集合
- ConcurrentHashMap 使用原理
    - 工作机制(分片思想)：它引入了一个“分段锁”的概念，具体可以理解为把一个大的 Map 拆分成 N 个小的 segment，
    根据 key.hashCode() 来决定把 key 放到哪个 HashTable 中，可以提供相同的线程安全，但是效率提升 N 倍，默认提升16倍。
    - 应用：当读>写时使用，适合做缓存，在程序启动时初始化，之后可以被多个线程访问。
    - hash冲突：
        - 简介：HashMap 中调用 hashCode() 方法来计算 hashCode，由于在Java中两个不同的对象可能有一样的 hashCode，
        所以不同的键可能有一样的 hashCode，从而导致冲突的产生。
        - hash 冲突解决：使用平衡树来代替链表，当同一 hash 中的元素数量超过特定的值便会由链表切换到平衡树。
    - 无锁读：ConcurrentHashMap 之所以有较好的并发性是因为 ConcurrentHashMap 是无锁读和加锁写，并且利用了分段锁(不是在所有的 entry 
    上加锁，而是在一部分 entry 上加锁)
- 解決 map 的并发问题方案
    - HashMap 不是线程安全的；Hashtable 线程安全，但效率低，因为 Hashtable 是使用 synchronized 的，所有线程竞争同一把
      锁；而 ConcurrentHashMap 不仅线程安全而且效率高，因为它包含一个 segment 数组，将数据分段存储，给每一段数据配一把锁，也就
      是所谓的锁分段技术。
#### Java 多线程并发

### 计算机网络 + 数据结构 + 算法

### 设计模式

### 数据库

#### Mysql 调优

##### 版本A
- 选择合适的字段属性：类型、长度、是否允许 NULL 等，尽量把字段设为 not null，以便查询时对比是否为 null；
- 要尽量避免全表扫描，首先考虑在 where 及 order by 涉及到的列上建立索引；
- 导致引擎放弃索引而进行全表扫描的情况：
    - 在 where 子句中对字段进行 null 值判断、使用 ！= 或 <> 操作符
    - 在 where 子句中使用 or 来连接条件，一个字段有索引，一个字段没有索引
    - 使用 in 和 not in
    - 模糊查询，若要提高效率，可以考虑字段建立前置索引或用全文检索
    - 在 where 子句中对字段进行函数操作
- Update 语句，如果只更改1、2个字段，不要Update全部字段，否则频繁调用会引起明显的性能消耗，同时带来大量日志。

### 基础框架

#### Spring 
- 什么是 Spring 框架？

    Spring 是一种轻量级开发框架，旨在提高开发人员的开发效率以及系统的可维护性。
    
    我们一般说 Spring 框架指的都是 Spring Framework，它是很多模块的集合，使用这些模块可以很方便地协助我们进行开发。这些模块是：核心容器、数据访问/集成,、Web、AOP（面向切面编程）、工具、消息和测试模块。比如：Core Container 中的 Core 组件是Spring 所有组件的核心，Beans 组件和 Context 组件是实现IOC和依赖注入的基础，AOP组件用来实现面向切面编程。
    
    Spring 官网列出的 Spring 的 6 个特征:
    - 核心技术 ：依赖注入(DI)，AOP，事件(events)，资源，i18n，验证，数据绑定，类型转换，SpEL。
    - 测试 ：模拟对象，TestContext框架，Spring MVC 测试，WebTestClient。
    - 数据访问 ：事务，DAO支持，JDBC，ORM，编组XML。
    - Web支持 : Spring MVC和Spring WebFlux Web框架。
    - 集成 ：远程处理，JMS，JCA，JMX，电子邮件，任务，调度，缓存。
    - 语言 ：Kotlin，Groovy，动态语言。


### 微服务/分布式

### 版本控制工具

#### Git
实际开发中的 Git 命令大全

##### 创建仓库/初始化/提交操作
- git init
    - 初始化仓库操作，这样才能用git进行代码管理。
- git clone 仓库地址
    - 复制远程仓库的代码到本地。
- git add XXX
    - 添加本地的某个新文件到本地仓库，但是，此时只是提交到了本地仓库，并没有提交到远程仓库。
- git add .
    - 这个操作和上面的区别在于，这个命令会添加所有的新文件，也就是当前目录下的。
- git commit -m 'message'
    - 提交代码到本地仓库，并没有到远程仓库。
- git commit -am 'message'
    - 这个命令将上面两个步骤 add 和 commit 合二为一。

##### 日志查看/信息显示
- git log
    - 这个命令主要用于查看提交日志
- git status
    - 可以用来查看仓库的状态

##### 分支管理
- git branch XXX 
    - 可以在远程界面创建分支，或者使用命令 git branch XXX
    
    创建的新分支的代码一般是来自于 master 的，所以，比如你创建了新分支 test，那么 test 分支的代码是和 master 的代码是一样的。
- git checkout XXX
    - 切换到 XXX 分支，然后我们再到XXX分支进行功能的开发工作。
- git checkout -b XXX
    - 创建并且直接切换到 XXX 分支
    
    当需要进行新的功能开发的时候，可以直接创建新分支，然后直接切换
- git branch
    - 直接查看本地的所有分支，并且当前处于哪个分支。
- git branch -a
    - 查看本地和远程所有分支
- git merge
    - 需要合并到 master 的时候，合并分支的步骤：
        - 切换到 master 分支，git checkout master
        - 合并 XXX 分支，git merge XXX
        - 这时候如果有冲突就需要解决冲突了。
- git branch -D XXX
    - 删除本地分支
- git push origin –delete XXX
    - 删除远程分支(删除远程分支属于危险操作，如果权限不合理，可能会出现大问题。)

##### 更新管理
- git push origin XXX(远程仓库名称)
    - 提交代码到远程
-  git pull origin XXX
    - 拉取远程代码到本地

##### 版本回退
- git reset –hard XXX
    - 把当前分支的代码全部回退到以前的一个版本，不可逆转，需要谨慎使用。
    - git reset --hard 766f905f
- git reflog
    - 查看所有的 head 的记录。
    
## 微信公众号学习集锦

###目录


