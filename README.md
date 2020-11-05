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
- 解決 Map 的并发问题方案
    - HashMap 不是线程安全的；Hashtable 线程安全，但效率低，因为 Hashtable 是使用 synchronized 的，所有线程竞争同一把
      锁；而 ConcurrentHashMap 不仅线程安全而且效率高，因为它包含一个 segment 数组，将数据分段存储，给每一段数据配一把锁，也就
      是所谓的锁分段技术。
      
#### Java 多线程并发

### 计算机网络 + 数据结构 + 算法

### 设计模式

### 数据库

#### Mysql

##### 一个完整数据库设计的六个阶段
- 需求分析：分析用户的需求，包括数据、功能和性能需求； 
- 概念结构设计：主要采用E-R模型进行设计，包括画E-R图； 
- 逻辑结构设计：通过将E-R图转换成表，实现从E-R模型到关系模型的转换，进行关系规范化；
- 数据库物理设计：主要是为所设计的数据库选择合适的存储结构和存取路径； 
- 数据库的实施：包括编程、测试和试运行； 
- 数据库运行与维护：系统的运行与数据库的日常维护。

##### Mysql调优
- 选择合适的字段属性：类型、长度、是否允许 NULL 等，尽量把字段设为 not null，以便查询时对比是否为 null；
- 要尽量避免全表扫描，首先考虑在 where 及 order by 涉及到的列上建立索引；
- 导致引擎放弃索引而进行全表扫描的情况：
    - 在 where 子句中对字段进行 null 值判断、使用 ！= 或 <> 操作符
    - 在 where 子句中使用 or 来连接条件，一个字段有索引，一个字段没有索引
    - 使用 in 和 not in
    - 模糊查询，若要提高效率，可以考虑字段建立前置索引或用全文检索
    - 在 where 子句中对字段进行函数操作
- Update 语句，如果只更改1、2个字段，不要Update全部字段，否则频繁调用会引起明显的性能消耗，同时带来大量日志。

#### Redis

##### 为什么要用 redis/为什么要用缓存？
- 高性能：
    - 假如用户第一次访问数据库中的某些数据。这个过程会比较慢，因为是从硬盘上读取的。将该用户访问的数据存在缓存中，这样下一次再访问这些数据的时候就
    可以直接从缓存中获取了。操作缓存就是直接操作内存，所以速度相当快。如果数据库中的对应数据改变的之后，同步改变缓存中相应的数据即可！
- 高并发：
    - 直接操作缓存能够承受的请求是远远大于直接访问数据库的，所以我们可以考虑把数据库中的部分数据转移到缓存中去，这样用户的一部分请求会直接到缓存这
    里而不用经过数据库。

##### 为什么要用 redis 而不用 map/guava 做缓存？
    - 缓存分为本地缓存和分布式缓存。以 Java 为例，使用自带的 map 或者 guava 实现的是本地缓存，最主要的特点是轻量以及快速，生命周期随着 jvm 的
    销毁而结束，并且在多实例的情况下，每个实例都需要各自保存一份缓存，缓存不具有一致性。
    - 使用 redis 或 memcached 之类的称为分布式缓存，在多实例的情况下，各实例共用一份缓存数据，缓存具有一致性。缺点是需要保持 redis 或 
    memcached 服务的高可用，整个程序架构上较为复杂。    

##### redis 常见数据结构以及使用场景分析
- String(常用命令: set,get,decr,incr,mget 等。)
    - String数据结构是简单的key-value类型，value其实不仅可以是String，也可以是数字。 常规key-value缓存应用； 常规计数：微博数，粉丝数等。
- Hash(常用命令： hget,hset,hgetall 等。)
    - hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象，后续操作的时候，你可以直接仅仅修改这个对象中的某个字段的
    值。比如我们可以 hash 数据结构来存储用户信息，商品信息等等。
- List(常用命令: lpush,rpush,lpop,rpop,lrange等)
    - list 就是链表，Redis list 的应用场景非常多，也是Redis最重要的数据结构之一，比如微博的关注列表，粉丝列表，消息列表等功能都可以用Redis的
    list 结构来实现。
    - Redis list 的实现为一个双向链表，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销。
    - 另外可以通过 lrange 命令，就是从某个元素开始读取多少个元素，可以基于 list 实现分页查询，这个很棒的一个功能，基于 redis 实现简单的高性能
    分页，可以做类似微博那种下拉不断分页的东西（一页一页的往下走），性能高。
- Set(常用命令： sadd,spop,smembers,sunion 等)
    - set 对外提供的功能与 list 类似是一个列表的功能，特殊之处在于 set 是可以自动排重的。
    - 当你需要存储一个列表数据，又不希望出现重复数据时，set 是一个很好的选择，并且 set 提供了判断某个成员是否在一个 set 集合内的重要接口，这个也
    是 list 所不能提供的。可以基于 set 轻易实现交集、并集、差集的操作。
    - 比如：在微博应用中，可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis 可以非常方便的实现如共同关注、共同粉丝、共同
    喜好等功能。这个过程也就是求交集的过程，具体命令如下：
    `sinterstore key1 key2 key3     将交集存在key1内`
- Sorted Set(常用命令： zadd,zrange,zrem,zcard等)
    - 和set相比，sorted set 增加了一个权重参数 score，使得集合中的元素能够按 score 进行有序排列。
    - 在直播系统中，实时排行信息包含直播间在线用户列表，各种礼物排行榜，弹幕消息（可以理解为按消息维度的消息排行榜）等信息，适合使用 Redis 中的
     Sorted Set 结构进行存储。

##### 如何保证缓存与数据库双写时的数据一致性？
- 一般情况下我们都是这样使用缓存的：先读缓存，缓存没有的话，就读数据库，然后取出数据后放入缓存，同时返回响应。这种方式很明显会存在缓存和数据库的数据
不一致的情况。
- 你只要用缓存，就可能会涉及到缓存与数据库双存储双写，你只要是双写，就一定会有数据一致性的问题，那么你如何解决一致性问题？
    - 一般来说，就是如果你的系统不是严格要求缓存+数据库必须一致性的话，缓存可以稍微的跟数据库偶尔有不一致的情况，最好不要做这个方案，读请求和写请求串行
    化，串到一个内存队列里去，这样就可以保证一定不会出现不一致的情况
    - 串行化之后，就会导致系统的吞吐量会大幅度的降低，用比正常情况下多几倍的机器去支撑线上的一个请求。

### 基础框架

#### Mybatis
- `#{}和${}的区别是什么？`
    - ${}是 Properties 文件中的变量占位符，它可以用于标签属性值和 sql 内部，属于静态文本替换，比如${driver}会被静态替换为com.mysql.jdbc.Driver。
    - `#{}是 sql 的参数占位符，Mybatis 会将 sql 中的#{}替换为?号，在 sql 执行前会使用 PreparedStatement 的参数设置方法，按序给 sql 的?号占位符设置参数值，比如 ps.setInt(0, parameterValue)，#{item.name} 的取值方式为使用反射从参数对象中获取 item 对象的 name 属性值，相当于 param.getItem().getName()`
- 最佳实践中，通常一个 Xml 映射文件，都会写一个 Dao 接口与之对应，请问，这个 Dao 接口的工作原理是什么？Dao 接口里的方法，参数不同时，方法能重载吗？
    - Dao 接口，就是人们常说的 Mapper 接口，接口的全限名，就是映射文件中的 namespace 的值，接口的方法名，就是映射文件中 MappedStatement 的
    id 值，接口方法内的参数，就是传递给 sql 的参数。Mapper 接口是没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为 key 值，可唯一
    定位一个 MappedStatement，举例：com.malf.mappers.StudentDao.findStudentById，可以唯一找到 namespace 为 com.malf.mappers.StudentDao
    下面 id = findStudentById 的 MappedStatement。在 Mybatis 中，每一个<select>、<insert>、<update>、<delete>标签，都会被解析为一
    个 MappedStatement 对象。
    - Dao 接口里的方法，是不能重载的，因为是全限名+方法名的保存和寻找策略。
    - Dao 接口的工作原理是 JDK 动态代理，Mybatis 运行时会使用 JDK 动态代理为 Dao 接口生成代理 proxy 对象，代理对象 proxy 会拦截接口方法，
    转而执行 MappedStatement 所代表的 sql，然后将 sql 执行结果返回。

#### Spring 

##### 什么是 Spring 框架？
- Spring 是一种轻量级开发框架，旨在提高开发人员的开发效率以及系统的可维护性。
- 我们一般说 Spring 框架指的都是 Spring Framework，它是很多模块的集合，使用这些模块可以很方便地协助我们进行开发。这些模块是：核心容器、数据访问/集成,、Web、AOP（面向切面编程）、工具、消息和测试模块。比如：Core Container 中的 Core 组件是Spring 所有组件的核心，Beans 组件和 Context 组件是实现IOC和依赖注入的基础，AOP组件用来实现面向切面编程。
- Spring 官网列出的 Spring 的 6 个特征:
    - 核心技术 ：依赖注入(DI)，AOP，事件(events)，资源，i18n，验证，数据绑定，类型转换，SpEL。
    - 测试 ：模拟对象，TestContext框架，Spring MVC 测试，WebTestClient。
    - 数据访问 ：事务，DAO支持，JDBC，ORM，编组XML。
    - Web支持 : Spring MVC和Spring WebFlux Web框架。
    - 集成 ：远程处理，JMS，JCA，JMX，电子邮件，任务，调度，缓存。
    - 语言 ：Kotlin，Groovy，动态语言。
    
##### Spring 框架中用到了哪些设计模式？
- 工厂设计模式: Spring 使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
- 代理设计模式: Spring AOP 功能的实现。
- 单例设计模式: Spring 中的 Bean 默认都是单例的。
- 模板方法模式: Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
- 包装器设计模式: 我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。
- 观察者模式: Spring 事件驱动模型就是观察者模式很经典的一个应用。
- 适配器模式: Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配 Controller。
- ……




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


