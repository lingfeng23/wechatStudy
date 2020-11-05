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



## 微信公众号学习集锦

###目录


