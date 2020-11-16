## 面试集锦

### Java 基础 + 集合 + 多线程 + JVM

#### Java 基础

##### 接口和抽象类的区别是什么？
- 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始接口方法可以有默认实现)，而抽象类可以有非抽象的方法。
- 接口中除了 static、final 变量，不能有其他变量，而抽象类中则不一定。
- 一个类可以实现多个接口，但只能实现一个抽象类。接口自己本身可以通过 extends 关键字扩展多个接口。
- 接口方法默认修饰符是 public，抽象方法可以有 public、protected 和 default 这些修饰符(抽象方法就是为了被重写所以不能使用 private 关键字修饰！)。
- 从设计层面来说，抽象是对类的抽象，是一种模板设计，而接口是对行为的抽象，是一种行为的规范。

##### == 与 equals 的区别是什么？
- == : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象(基本数据类型==比较的是值，引用数据类型==比较的是内存地址)。
- equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况: 
    - 情况 1: 类没有覆盖 equals() 方法。则通过 equals() 比较该类的两个对象时，等价于通过“==”比较这两个对象。
    - 情况 2: 类覆盖了 equals() 方法。一般，我们都覆盖 equals() 方法来比较两个对象的内容是否相等；若它们的内容相等，则返回 true (即认为这两个对象相等)。

##### BIO、NIO 和 AIO 有什么区别?
- BIO (Blocking I/O): 同步阻塞 I/O 模式，数据的读取写入必须阻塞在一个线程内等待其完成。在活动连接数不是特别高(小于单机 1000)的情况下，这
种模型是比较不错的，可以让每一个连接专注于自己的 I/O 并且编程模型简单，也不用过多考虑系统的过载、限流等问题。线程池本身就是一个天然的漏斗，可以缓
冲一些系统处理不了的连接或请求。但是，当面对十万甚至百万级连接的时候，传统的 BIO 模型是无能为力的。因此，我们需要一种更高效的 I/O 处理模型来应对更高的并发量。
- NIO (Non-blocking/New I/O): NIO 是一种同步非阻塞的 I/O 模型，在 Java 1.4 中引入了 NIO 框架，对应 java.nio 包，提供了 Channel,
Selector, Buffer 等抽象。NIO 中的 N 可以理解为 Non-blocking，不单纯是 New。它支持面向缓冲的，基于通道的 I/O 操作方法。 NIO 提供了与传统
BIO 模型中的 Socket 和 ServerSocket 相对应的 SocketChannel 和 ServerSocketChannel 两种不同的套接字通道实现,两种通道都支持阻塞和非阻塞
两种模式。阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。对于低负载、低并发的应用程序，可以使用同步阻塞
I/O 来提升开发速率和更好的维护性；对于高负载、高并发的(网络)应用，应使用 NIO 的非阻塞模式来开发
- AIO (Asynchronous I/O): AIO 也就是 NIO 2。在 Java 7 中引入了 NIO 的改进版 NIO 2,它是异步非阻塞的 IO 模型。异步 IO 是基于事件和回调
机制实现的，也就是应用操作之后会直接返回，不会堵塞在那里，当后台处理完成，操作系统会通知相应的线程进行后续的操作。AIO 是异步 IO 的缩写，虽然 NIO
在网络操作中，提供了非阻塞的方法，但是 NIO 的 IO 行为还是同步的。对于 NIO 来说，我们的业务线程是在 IO 操作准备好时，得到通知，接着就由这个线程
自行进行 IO 操作，IO 操作本身是同步的。

##### Java 序列化和反序列化
- 序列化：把对象转换为字节序列的过程称为对象的序列化。
- 反序列化：把字节序列恢复为对象的过程称为对象的反序列化。
- 被 transient 关键字修饰的属性不会被序列化, static 属性也不会被序列化。 
    - 序列化是针对对象而言的, 而 static 属性优先于对象存在, 随着类的加载而加载, 所以不会被序列化。
为什么需要显式指定 serialVersionUID 的值？
    - 如果不显示指定 serialVersionUID, JVM 在序列化时会根据属性自动生成一个 serialVersionUID, 然后与属性一起序列化, 再进行持久化或网络
    传输。在反序列化时, JVM 会再根据属性自动生成一个新版 serialVersionUID, 然后将这个新版 serialVersionUID 与序列化时生成的旧版
    serialVersionUID 进行比较, 如果相同则反序列化成功, 否则报错.
    - 显式指定 serialVersionUID 后就解决了序列化与反序列化产生的 serialVersionUID 不一致的问题。
    
#### JVM

##### Java 对象的创建过程
- 类加载检查: 虚拟机遇到一条 new 指令时，首先将去检查这个指令的参数是否能在常量池中定位到这个类的符号引用，并且检查这个符号引用代表的类是否已被
加载过、解析和初始化过。如果没有，那必须先执行相应的类加载过程。
- 分配内存: 在类加载检查通过后，接下来虚拟机将为新生对象分配内存。对象所需的内存大小在类加载完成后便可确定，为对象分配空间的任务等同于把一块确定
大小的内存从 Java 堆中划分出来。分配方式有 “指针碰撞” 和 “空闲列表” 两种，选择那种分配方式由 Java 堆是否规整决定，而Java堆是否规整又由所采用
的垃圾收集器是否带有压缩整理功能决定。
- 初始化零值: 内存分配完成后，虚拟机需要将分配到的内存空间都初始化为零值(不包括对象头)，这一步操作保证了对象的实例字段在 Java 代码中可以不赋
初始值就直接使用，程序能访问到这些字段的数据类型所对应的零值。
- 设置对象头: 初始化零值完成之后，虚拟机要对对象进行必要的设置，例如这个对象是那个类的实例、如何才能找到类的元数据信息、对象的哈希吗、对象的
GC 分代年龄等信息。 这些信息存放在对象头中。 另外，根据虚拟机当前运行状态的不同，如是否启用偏向锁等，对象头会有不同的设置方式。
- 执行 init 方法: 在上面工作都完成之后，从虚拟机的视角来看，一个新的对象已经产生了，但从 Java 程序的视角来看，对象创建才刚开始，<init> 方法
还没有执行，所有的字段都还为零。所以一般来说，执行 new 指令之后会接着执行 <init> 方法，把对象按照程序员的意愿进行初始化，这样一个真正可用的对象
才算完全产生出来。

##### 对类加载器的了解?
JVM 中内置了三个重要的 ClassLoader，除了 BootstrapClassLoader 其他类加载器均由 Java 实现且全部继承自java.lang.ClassLoader: 
- BootstrapClassLoader(启动类加载器) : 最顶层的加载类，由C++实现，负责加载 %JAVA_HOME%/lib目录下的jar包和类或者或被 -Xbootclasspath
参数指定的路径中的所有类。
- ExtensionClassLoader(扩展类加载器) : 主要负责加载目录 %JRE_HOME%/lib/ext 目录下的jar包和类，或被 java.ext.dirs 系统变量所指定的路径下的jar包。
- AppClassLoader(应用程序类加载器) :面向我们用户的加载器，负责加载当前应用classpath下的所有jar包和类。

##### 双亲委派模型介绍
每一个类都有一个对应它的类加载器。系统中的 ClassLoder 在协同工作的时候会默认使用 双亲委派模型 。即在类加载的时候，系统会首先判断当前类是否被加载过。
已经被加载的类会直接返回，否则才会尝试加载。加载的时候，首先会把该请求委派该父类加载器的 loadClass() 处理，因此所有的请求最终都应该传送到顶层的
启动类加载器 BootstrapClassLoader 中。当父类加载器无法处理时，才由自己来处理。当父类加载器为null时，会使用启动类加载器 BootstrapClassLoader
作为父类加载器。

#####  Minor Gc和Full GC 有什么不同？
大多数情况下，对象在新生代中 eden 区分配。当 eden 区没有足够空间进行分配时，虚拟机将发起一次Minor GC。
- 新生代 GC(Minor GC):指发生新生代的的垃圾收集动作，Minor GC非常频繁，回收速度一般也比较快。
- 老年代 GC(Major GC/Full GC):指发生在老年代的GC，出现了 Major GC经常会伴随至少一次的Minor GC(并非绝对)，Major GC的速度一般会比
Minor GC的慢10倍以上。

#### Java 集合

##### HashMap 和 Hashtable 的区别
- 线程是否安全: HashMap 是非线程安全的，HashTable 是线程安全的；HashTable 内部的方法基本都经过 synchronized 修饰。(如果你要保证线程安
全的话可以使用 ConcurrentHashMap)；
- 效率: 因为线程安全的问题，HashMap 要比 HashTable 效率高一点。另外，HashTable 基本被淘汰，不要在代码中使用它；
- 对 Null key 和 Null value的支持: HashMap 中，null 可以作为键，这样的键只有一个，可以有一个或多个键所对应的值为 null。但是在 HashTable
中 put 进的键值只要有一个 null，直接抛出 NullPointerException。
- 初始容量大小和每次扩充容量大小的不同 : 
    - 创建时如果不指定容量初始值，Hashtable 默认的初始大小为11，之后每次扩充，容量变为原来的2n+1。
    - HashMap 默认的初始化大小为16。之后每次扩充，容量变为原来的2倍。
    - 创建时如果给定了容量初始值，那么 Hashtable 会直接使用给定的大小，而 HashMap 会将其扩充为2的幂次方大小
    (HashMap 中的 tableSizeFor()方法保证)。也就是说 HashMap 总是使用2的幂作为哈希表的大小
- 底层数据结构: JDK1.8 以后的 HashMap 在解决哈希冲突时有了较大的变化，当链表长度大于阈值(默认为8)时，将链表转化为红黑树，以减少搜索时间。
Hashtable 没有这样的机制。

##### ConcurrentHashMap 和 Hashtable 的区别
ConcurrentHashMap 和 Hashtable 的区别主要体现在实现线程安全的方式上不同。
- 底层数据结构: JDK1.7的 ConcurrentHashMap 底层采用 分段的数组+链表 实现，JDK1.8 采用的数据结构跟HashMap1.8的结构一样，数组+链表/红黑
二叉树。Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构类似都是采用 数组+链表 的形式，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的；
- 实现线程安全的方式(重要):    
    - 在 JDK1.7 的时候，ConcurrentHashMap(分段锁) 对整个桶数组进行了分割分段(Segment)，每一把锁只锁容器其中一部分数据，多线程访问容器里不
    同数据段的数据，就不会存在锁竞争，提高并发访问率。 到了 JDK1.8 的时候已经摒弃了Segment的概念，而是直接用 Node 数组+链表+红黑树的数据结构
    来实现，并发控制使用 synchronized 和 CAS 来操作。(JDK1.6以后 对 synchronized锁做了很多优化) 整个看起来就像是优化过且线程安全的
    HashMap，虽然在 JDK1.8中还能看到 Segment 的数据结构，但是已经简化了属性，只是为了兼容旧版本；
    - Hashtable(同一把锁) :使用 synchronized 来保证线程安全，效率非常低下。当一个线程访问同步方法时，其他线程也访问同步方法，可能会进入阻塞
    或轮询状态，如使用 put 添加元素，另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。

##### Arraylist 和 LinkedList 的区别
- 是否保证线程安全: ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
- 底层数据结构: Arraylist 底层使用的是 Object 数组；LinkedList 底层使用的是 双向链表 数据结构(JDK1.6之前为循环链表，JDK1.7取消了循环。
- 插入和删除是否受元素位置的影响: 
    - ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。 比如: 执行add(E e) 方法的时候， ArrayList 会默认在将指定的
    元素追加到此列表的末尾，这种情况时间复杂度就是O(1)。但是如果要在指定位置 i 插入和删除元素的话(add(int index, E element) )时间复杂度
    就为 O(n-i)。因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的(n-i)个元素都要执行向后位/向前移一位的操作。 
    - LinkedList 采用链表存储，所以对于add(E e)方法的插入，删除元素时间复杂度不受元素位置的影响，近似 O(1)，如果是要在指定位置i插入和删
    除元素的话((add(int index, E element)) 时间复杂度近似为o(n))因为需要先移动到指定位置再插入。
- 是否支持快速随机访问: LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于get(int index) 方法)。
- 内存空间占用: ArrayList 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要
消耗比 ArrayList 更多的空间(因为要存放直接后继和直接前驱以及数据)。
      
#### Java 多线程并发

##### sleep() 方法和 wait() 方法区别和共同点?
- 两者最主要的区别在于: sleep 方法没有释放锁，而 wait 方法释放了锁 。
- 两者都可以暂停线程的执行。
- Wait 通常被用于线程间交互/通信，sleep 通常被用于暂停执行。
- wait() 方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 notify() 或者 notifyAll() 方法。sleep() 方法执行完成后，线程会自动
苏醒。或者可以使用 wait(long timeout)超时后线程会自动苏醒。

##### 多线程的上下文切换
多线程的上下文切换是指 CPU 控制权由一个已经正在运行的线程切换到另外一个就绪并等待获取 CPU 执行权的线程的过程。

##### synchronized 关键字和 volatile 关键字的区别
- volatile 关键字是线程同步的轻量级实现，所以 volatile 性能肯定比 synchronized 关键字要好。但是 volatile 关键字只能用于变量而
synchronized 关键字可以修饰方法以及代码块。synchronized 关键字在 JavaSE1.6 之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的
偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升，实际开发中使用 synchronized 关键字的场景还是更多一些。
- 多线程访问 volatile 关键字不会发生阻塞，而 synchronized 关键字可能会发生阻塞。
- volatile 关键字能保证数据的可见性，但不能保证数据的原子性。synchronized 关键字两者都能保证。
- volatile 关键字主要用于解决变量在多个线程之间的可见性，而 synchronized 关键字解决的是多个线程之间访问资源的同步性。

##### Hashtable 的 size()方法中明明只有一条语句"return count"，为什么还要做同步？
- 同一时间只能有一条线程执行固定类的同步方法，但是对于类的非同步方法，可以多条线程同时访问。所以，这样就有问题了，可能线程 A 在执行 Hashtable 的
put 方法添加数据，线程 B 则可以正常调用 size()方法读取 Hashtable 中当前元素的个数，那读取到的值可能不是最新的，可能线程 A 添加了完了数据，但是
没有对 size++，线程 B 就已经读取 size 了，那么对于线程 B 来说读取到的 size 一定是不准确的。而给 size()方法加了同步之后，意味着线程 B 调用
size()方法只有在线程 A 调用 put 方法完毕之后才可以调用，这样就保证了线程安全性。
- CPU 执行代码，执行的不是 Java 代码，Java 代码最终是被翻译成机器码执行的，机器码才是真正可以和硬件电路交互的代码。即使你看到 Java 代码只有一行，
甚至你看到 Java 代码编译之后生成的字节码也只有一行，也不意味着对于底层来说这句语句的操作只有一个。一句"return count"假设被翻译成了三句汇编语句
执行，一句汇编语句和其机器码做对应，完全可能执行完第一句，线程就切换了。

##### ConcurrentHashMap 使用原理
- 工作机制(分片思想): 它引入了一个“分段锁”的概念，具体可以理解为把一个大的 Map 拆分成 N 个小的 segment，
根据 key.hashCode() 来决定把 key 放到哪个 HashTable 中，可以提供相同的线程安全，但是效率提升 N 倍，默认提升16倍。
- 应用: 当读>写时使用，适合做缓存，在程序启动时初始化，之后可以被多个线程访问。
- hash冲突: 
    - 简介: HashMap 中调用 hashCode() 方法来计算 hashCode，由于在Java中两个不同的对象可能有一样的 hashCode，
    所以不同的键可能有一样的 hashCode，从而导致冲突的产生。
    - hash 冲突解决: 使用平衡树来代替链表，当同一 hash 中的元素数量超过特定的值便会由链表切换到平衡树。
- 无锁读: ConcurrentHashMap 之所以有较好的并发性是因为 ConcurrentHashMap 是无锁读和加锁写，并且利用了分段锁(不是在所有的 entry 
上加锁，而是在一部分 entry 上加锁)
- 解決 Map 的并发问题方案
    - HashMap 不是线程安全的；Hashtable 线程安全，但效率低，因为 Hashtable 是使用 synchronized 的，所有线程竞争同一把
      锁；而 ConcurrentHashMap 不仅线程安全而且效率高，因为它包含一个 segment 数组，将数据分段存储，给每一段数据配一把锁，也就
      是所谓的锁分段技术。

##### ThreadPoolExecutor 创建线程池中参数的理解
- corePoolSize: 核心线程数（线程池的基本大小）当我们提交一个任务到线程池时就会创建一个线程来执行任务.当我们需要执行的任务数大于核心线程数了就不
再创建，如果我们调用了 prestartAllCoreThreads()方法线程池就会为我们提前创建好所有的基本线程。
- maximumPoolSize: 最大线程数，线程池允许创建的最大线程数。如果队列已经满了，且已创建的线程数小于最大线程数，则线程池就会创建新的线程来执行任务。
如果我们的队列是用的无界队列，这个参数是不会起作用的，因为我们的任务会一直往队列中加，队列永远不会满（内存允许的情况）。
- keepAliveTime: 空闲线程最大生存时间。当前线程数大于核心线程数时，结束多余的空闲线程等待新任务的最长时间。
- unit: 线程存活时间的的单位。可选的单位有 days、hours 等。
- workQueue: 任务队列。
- threadFactory: 用户设置创建线程的工厂，我们可以通过这个工厂来创建有业务意义的线程名字。(默认使用)
- RejectedExecutionHandler: 线程池拒绝策略。当队列和线程池都满了说明线程池已经处于饱和状态。必须要采取一定的策略来处理新提交的任务。
    - 线程池参数如何设置？
        - 一般我们如果任务为耗时 IO 型比如读取数据库、文件读写以及网略通信的的话这些任务不会占据很多 cpu 的资源但是会比较耗时：线程数设置为2倍
        CPU 数以上，充分的来利用 CPU 资源。
        - 一般我们如果任务为 CPU 密集型的话比如大量计算、解压、压缩等这些操作都会占据大量的 cpu。所以针对于这种情况的话一般设置线程数为：1倍 
        cpu+1。为啥要加1，很多说法是备份线程。
        - 如果既有 IO 密集型任务，又有 CPU 密集型任务，这种该怎么设置线程大小？这种的话最好分开用线程池处理，IO 密集的用 IO 密集型线程池处理，
        CPU 密集型的用 cpu 密集型处理。以上都只是理算情况下的估算而已，真正的合理参数还是需要看看实际生产运行的效果来合理的调整的。
##### CopyOnWriteArrayList 类的实现思路(写时复制，读写分离)
- Copy-On-Write, 也就是写时复制策略；末尾的 ArrayList 表示数据存放在一个数组里。在对元素进行增删改时，先把现有的数据数组拷贝一份，然后增删改
都在这个拷贝数组上进行，操作完成后再把原有的数据数组替换成新数组。这样就完成了更新操作。
- 但是这种写入时复制的方式必定会有一个问题，因为每次更新都是用一个新数组替换掉老的数组，如果不巧在更新时有一个线程正在读取数据，那么读取到的就是老
数组中的老数据。其实这也是读写分离的思想，放弃数据的强一致性来换取性能的提升。
CopyOnWriteArrayList 类的总结：
    - CopyOnWriteArrayList 采用读写分离，写时复制方式实现线程安全，具有弱一致性。
    - CopyOnWriteArrayList 因为每次写入时都要扩容复制数组，写入性能不佳。
    - CopyOnWriteArrayList 在修改元素时，为了保证 volatile 语义，即使元素没有任何变化也会重新赋值，
    - 在高版 JDK 中，得益于 synchronized 锁升级策略， CopyOnWriteArrayList 的加锁方式采用了 synchronized。

##### Lock 接口/ReentrantLock 和 synchronized 的区别(TODO)


##### Java 中 CyclicBarrier 和 CountdownLatch 有什么区别
- CountDownLatch 简单的说就是一个线程等待，直到他所等待的其他线程都执行完成并且调用 countDown()方法发出通知后，当前线程才可以继续执行。
- CyclicBarrier 是所有线程都进行等待，直到所有线程都准备好进入 await()方法之后，所有线程同时开始执行！
- CountDownLatch 的计数器只能使用一次。而 CyclicBarrier 的计数器可以使用 reset() 方法重置，所以 CyclicBarrier 能处理更为复杂的业务场景，
比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
- CyclicBarrier 还提供其他有用的方法，比如 getNumberWaiting 方法可以获得 CyclicBarrier 阻塞的线程数量；isBroken 方法用来知道阻塞的线程
是否被中断，如果被中断返回 true，否则返回 false。

### 计算机网络 + 数据结构 + 算法

#### 计算机网络

##### 一次完整的 HTTP 请求过程
域名解析 --> 发起 TCP 的3次握手 --> 建立 TCP 连接后发起 http 请求 --> 服务器响应 http 请求，浏览器得到 html 代码 
--> 浏览器解析 html 代码，并请求 html 代码中的资源(如js、css、图片等) --> 浏览器对页面进行渲染呈现给用户

##### Cookie 和 Session 有什么区别？
- Cookie 和 Session 都是用来跟踪浏览器用户身份的会话方式，但是两者的应用场景不太一样。
- Cookie 一般用来保存用户信息，比如
    - ①我们在 Cookie 中保存已经登录过的用户信息，下次访问网站的时候页面可以自动帮你把登录的一些基本信息给填了；
    - ②一般的网站都会有保持登录也就是说下次你再访问网站的时候就不需要重新登录了，这是因为用户登录的时候我们可以存放了一个 Token 在 Cookie 中，
    下次登录的时候只需要根据 Token 值来查找用户即可(为了安全考虑，重新登录一般要将 Token 重写)；
    - ③登录一次网站后访问网站其他页面不需要重新登录。
- Session 的主要作用就是通过服务端记录用户的状态。 
    - 典型的场景是购物车，当你要添加商品到购物车的时候，系统不知道是哪个用户操作的，因为 HTTP 协议是无状态的。服务端给特定的用户创建特定的
    Session 之后就可以标识这个用户并且跟踪这个用户了。
- Cookie 数据保存在客户端(浏览器端)，Session 数据保存在服务器端，相对来说 Session 安全性更高。如果要在 Cookie 中存储一些敏感信息，不要直接
写入 Cookie 中，最好能将 Cookie 信息加密然后使用到的时候再去服务器端解密。

##### HTTP 和 HTTPS 的区别？
- 端口: HTTP的URL由“http://”起始且默认使用端口80，而HTTPS的URL由“https://”起始且默认使用端口443。
- 安全性和资源消耗: HTTP协议运行在TCP之上，所有传输的内容都是明文，客户端和服务器端都无法验证对方的身份。HTTPS是运行在SSL/TLS之上的HTTP协议，
SSL/TLS 运行在TCP之上。所有传输的内容都经过加密，加密采用对称加密，但对称加密的密钥用服务器方的证书进行了非对称加密。所以说，HTTP 安全性没有
HTTPS高，但是 HTTPS 比HTTP耗费更多服务器资源。 
    - 对称加密: 密钥只有一个，加密解密为同一个密码，且加解密速度快，典型的对称加密算法有DES、AES等；
    - 非对称加密: 密钥成对出现(且根据公钥无法推知私钥，根据私钥也无法推知公钥)，加密解密使用不同密钥(公钥加密需要私钥解密，私钥加密需要公钥解
    密)，相对对称加密速度较慢，典型的非对称加密算法有RSA、DSA等。
    

### 设计模式

### 数据库

#### Mysql

##### 一个完整数据库设计的六个阶段
- 需求分析: 分析用户的需求，包括数据、功能和性能需求； 
- 概念结构设计: 主要采用E-R模型进行设计，包括画E-R图； 
- 逻辑结构设计: 通过将E-R图转换成表，实现从E-R模型到关系模型的转换，进行关系规范化；
- 数据库物理设计: 主要是为所设计的数据库选择合适的存储结构和存取路径； 
- 数据库的实施: 包括编程、测试和试运行； 
- 数据库运行与维护: 系统的运行与数据库的日常维护。

##### MyISAM 和 InnoDB 的区别
MyISAM 是 MySQL 的默认数据库引擎(5.5版之前)。虽然性能极佳，而且提供了大量的特性，包括全文索引、压缩、空间函数等，但 MyISAM 不支持事务和行级
锁，而且最大的缺陷就是崩溃后无法安全恢复。不过，5.5版本之后，MySQL 引入了 InnoDB(事务性数据库引擎)，MySQL 5.5版本后默认的存储引擎为 InnoDB。

大多数时候我们使用的都是 InnoDB 存储引擎，但是在某些情况下使用 MyISAM 也是合适的比如读密集的情况下。(如果你不介意 MyISAM 崩溃恢复问题的话)。

两者的对比: 
- 是否支持行级锁: MyISAM 只有表级锁(table-level locking)，而 InnoDB 支持行级锁(row-level locking)和表级锁,默认为行级锁。
- 是否支持事务和崩溃后的安全恢复: MyISAM 强调的是性能，每次查询具有原子性,其执行速度比 InnoDB 类型更快，但是不提供事务支持。但是 InnoDB 提供事
务支持事务，外部键等高级数据库功能。 具有事务(commit)、回滚(rollback)和崩溃修复能力(crash recovery capabilities)的
事务安全(transaction-safe (ACID compliant))型表。
- 是否支持外键: MyISAM 不支持，而 InnoDB 支持。
- 是否支持 MVCC: 仅 InnoDB 支持。应对高并发事务, MVCC 比单纯的加锁更高效;MVCC只在 READ COMMITTED 和 REPEATABLE READ 两个隔离级别下工作;
MVCC 可以使用乐观(optimistic)锁和悲观(pessimistic)锁来实现;各数据库中 MVCC 实现并不统一。
- ......

##### Mysql 有哪几种锁？
- 表级锁
    - 开销小，加锁快，不会出现死锁；锁定粒度大，发生锁冲突的概率高，并发度最低。
- 行级锁
    - 开销大，加锁慢，会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也最高。
- 页面锁
    - 开销和加锁时间界于表锁和行锁之间；会出现死锁；锁定粒度界于表锁和行锁之间，并发度一般。

##### 对 Mysql 中索引的理解
MySQL 索引使用的数据结构主要有 BTree 索引和哈希索引。对于哈希索引来说，底层的数据结构就是哈希表，因此在绝大多数需求为单条记录查询的时候，可以选择
哈希索引，查询性能最快；其余大部分场景，建议选择 BTree 索引。

MySQL 的 BTree 索引使用的是B树中的 B+Tree，但对于主要的两种存储引擎的实现方式是不同的。
- MyISAM: B+Tree 叶节点的 data 域存放的是数据记录的地址。在索引检索的时候，首先按照 B+Tree 搜索算法搜索索引，如果指定的 Key 存在，则取出其
data 域的值，然后以 data 域的值为地址读取相应的数据记录。这被称为“非聚簇索引”。
- InnoDB: 其数据文件本身就是索引文件。相比 MyISAM，索引文件和数据文件是分离的，其表数据文件本身就是按 B+Tree 组织的一个索引结构，树的叶节点
data 域保存了完整的数据记录。这个索引的 key 是数据表的主键，因此 InnoDB 表数据文件本身就是主索引。这被称为“聚簇索引(或聚集索引)”。而其余的索引
都作为辅助索引，辅助索引的 data 域存储相应记录主键的值而不是地址，这也是和 MyISAM 不同的地方。在根据主索引搜索时，直接找到 key 所在的节点即可取
出数据；在根据辅助索引查找时，则需要先取出主键的值，再走一遍主索引。 因此，在设计表的时候，不建议使用过长的字段作为主键，也不建议使用非单调的字段作
为主键，这样会造成主索引频繁分裂。

##### 如何理解事务的四大特性？
- 原子性(Atomicity): 事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用；
- 一致性(Consistency): 执行事务前后，数据保持一致，多个事务对同一个数据读取的结果是相同的；
- 隔离性(Isolation): 并发访问数据库时，一个用户的事务不被其他事务所干扰，各并发事务之间数据库是独立的；
- 持久性(Durability): 一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库发生故障也不应该对其有任何影响。 

##### 如何理解三个范式
- 第一范式: 1NF 是对属性的原子性约束，要求属性具有原子性，不可再分解；
- 第二范式: 2NF 是对记录的惟一性约束，要求记录有惟一标识，即实体的惟一性；
- 第三范式: 3NF 是对字段冗余性的约束，即任何字段不能由其他字段派生出来，它要求字段没有冗余。。
范式设计的优缺点
- 优点: 可以尽量得减少数据冗余，使得更新快，体积小
- 缺点: 对于查询需要多个表进行关联，减少写的效率增加读的效率，更难进行索引优化
反范式化的优缺点
- 优点: 可以减少表的关联，可以更好的进行索引优化
- 缺点: 数据冗余以及数据异常，数据的修改需要更多的成本

##### 索引，主键，唯一索引，联合索引的区别，对数据库的性能有什么影响(从读写两方面)
- 索引是一种特殊的文件(InnoDB 数据表上的索引是表空间的一个组成部分)，它们包含着对数据表里所有记录的引用指针。
- 普通索引(由关键字 KEY 或 INDEX 定义的索引)的唯一任务是加快对数据的访问速度。
- 普通索引允许被索引的数据列包含重复的值。如果能确定某个数据列将只包含彼此各不相同的值，在为这个数据列创建索引的时候就应该用关键字 UNIQUE 把它
定义为一个唯一索引。也就是说，唯一索引可以保证数据记录的唯一性。
- 主键，是一种特殊的唯一索引，在一张表中只能定义一个主键索引，主键用于唯一标识一条记录，使用关键字 PRIMARY KEY 来创建。
- 索引可以覆盖多个数据列，如像 INDEX(columnA, columnB)索引，这就是联合索引。
- 索引可以极大的提高数据的查询速度，但是会降低插入、删除、更新表的速度，因为在执行这些写操作时，还要操作索引文件。

##### BLOB 和 TEXT 有什么区别？
- BLOB 是一个二进制对象，可以容纳可变数量的数据，TEXT 是一个不区分大小写的 BLOB。
- BLOB 和 TEXT 类型之间的唯一区别在于对 BLOB 值进行排序和比较时区分大小写，对 TEXT 值不区分大小写。

##### NOW()、CURRENT_DATE()和 CURRENT_TIME() 有什么区别？
- NOW() 命令用于显示当前年份，月份，日期，小时，分钟和秒。
- CURRENT_DATE() 仅显示当前年份，月份和日期。
- CURRENT_TIME() 仅显示当前时分秒。

##### Mysql 中的四种事务隔离级别
- 未提交读(Read UnCommitted)，事务中的修改，即使没提交对其他事务也是可见的。事务可能读取未提交的数据，造成脏读。
- 提交读(Read Committed)，一个事务开始时，只能看见已提交的事务所做的修改。事务未提交之前，所做的修改对其他事务是不可见的。也叫不可重复读，同
一个事务多次读取同样记录可能不同。
- 可重复读(RepeatTable Read)，同一个事务中多次读取同样的记录结果时结果相同。
- 可串行化(Serializable)，最高隔离级别，强制事务串行执行。

##### explain 在性能分析中的使用
- select_type，有几种值: 
    - simple(表示简单的 select，没有 union 和子查询)，
    - primary(有子查询，最外面的 select 查询就是 primary )，
    - union(union 中的第二个或随后的 select 查询，不依赖外部查询结果)，
    - dependent union(union中的第二个或随后的 select 查询，依赖外部查询结果)
- type，有几种值: 
    - system(表仅有一行(=系统表)，这是 const 连接类型的一个特例)，
    - const(常量查询), 
    - ref(非唯一索引访问，只有普通索引)，
    - eq_ref(使用唯一索引或组件查询)，
    - all(全表查询)，
    - index(根据索引查询全表)，
    - range(范围查询)
- possible_keys: 表中可能帮助查询的索引
- key，选择使用的索引
- key_len，使用的索引长度
- rows，扫描的行数，越大越不好
- extra，有几种值: 
    - Only index(信息从索引中检索出，比扫描表快)，
    - Using where (使用 where 限制)，
    - Using filesort (可能在内存或磁盘排序)，
    - Using temporary(对查询结果排序时使用临时表)

##### Mysql调优(TODO)
- 创建时优化
    - 选择合适的字段属性: 类型、长度、是否允许 NULL 等，尽量把字段设为 not null，以便查询时对比是否为 null；
- 查询时优化
    - 要尽量避免全表扫描，首先考虑在 where 及 order by 涉及到的列上建立索引；
    - 导致引擎放弃索引而进行全表扫描的情况: 
        - 在 where 子句中对字段进行 null 值判断、使用 ！= 或 <> 操作符
        - 在 where 子句中使用 or 来连接条件，一个字段有索引，一个字段没有索引
        - 使用 in 和 not in
        - 模糊查询，若要提高效率，可以考虑字段建立前置索引或用全文检索
        - 在 where 子句中对字段进行函数操作
    - Update 语句，如果只更改1、2个字段，不要Update全部字段，否则频繁调用会引起明显的性能消耗，同时带来大量日志。

##### 如何对大表进行优化？(TODO)


##### B-Tree 索引和哈希索引的区别(TODO)


##### Mysql 常用SQL语句
- show index from xxx[tableName]; // 查询某张表中所有索引

#### Redis

##### 为什么要用 Redis/为什么要用缓存？
- 高性能: 
    - 假如用户第一次访问数据库中的某些数据。这个过程会比较慢，因为是从硬盘上读取的。将该用户访问的数据存在缓存中，这样下一次再访问这些数据的时候就
    可以直接从缓存中获取了。操作缓存就是直接操作内存，所以速度相当快。如果数据库中的对应数据改变的之后，同步改变缓存中相应的数据即可！
- 高并发: 
    - 直接操作缓存能够承受的请求是远远大于直接访问数据库的，所以我们可以考虑把数据库中的部分数据转移到缓存中去，这样用户的一部分请求会直接到缓存这
    里而不用经过数据库。

##### 为什么要用 Redis 而不用 Map/Guava 做缓存？
- 缓存分为本地缓存和分布式缓存。以 Java 为例，使用自带的 Map 或者 Guava 实现的是本地缓存，最主要的特点是轻量以及快速，生命周期随着 jvm 的
销毁而结束，并且在多实例的情况下，每个实例都需要各自保存一份缓存，缓存不具有一致性。
- 使用 Redis 或 memcached 之类的称为分布式缓存，在多实例的情况下，各实例共用一份缓存数据，缓存具有一致性。缺点是需要保持 Redis 或 
memcached 服务的高可用，整个程序架构上较为复杂。    

##### Redis 常见数据结构以及使用场景分析
- String(常用命令: set, get, decr, incr, mget 等。)
    - String数据结构是简单的key-value类型，value其实不仅可以是String，也可以是数字。 常规key-value缓存应用； 常规计数: 微博数，粉丝数等。
- Hash(常用命令: hget, hset, hgetall 等。)
    - hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象，后续操作的时候，你可以直接仅仅修改这个对象中的某个字段的
    值。比如我们可以 hash 数据结构来存储用户信息，商品信息等等。
- List(常用命令: lpush, rpush, lpop, rpop, lrange 等)
    - list 就是链表，Redis list 的应用场景非常多，也是Redis最重要的数据结构之一，比如微博的关注列表，粉丝列表，消息列表等功能都可以用Redis的
    list 结构来实现。
    - Redis list 的实现为一个双向链表，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销。
    - 另外可以通过 lrange 命令，就是从某个元素开始读取多少个元素，可以基于 list 实现分页查询，这个很棒的一个功能，基于 Redis 实现简单的高性能
    分页，可以做类似微博那种下拉不断分页的东西(一页一页的往下走)，性能高。
- Set(常用命令: sadd, spop, smembers, sunion 等)
    - set 对外提供的功能与 list 类似是一个列表的功能，特殊之处在于 set 是可以自动排重的。
    - 当你需要存储一个列表数据，又不希望出现重复数据时，set 是一个很好的选择，并且 set 提供了判断某个成员是否在一个 set 集合内的重要接口，这个也
    是 list 所不能提供的。可以基于 set 轻易实现交集、并集、差集的操作。
    - 比如: 在微博应用中，可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis 可以非常方便的实现如共同关注、共同粉丝、共同
    喜好等功能。这个过程也就是求交集的过程，具体命令如下: 
    `sinterstore key1 key2 key3     将交集存在key1内`
- Sorted Set(常用命令: zadd,zrange,zrem,zcard等)
    - 和 set 相比，sorted set 增加了一个权重参数 score，使得集合中的元素能够按 score 进行有序排列。
    - 在直播系统中，实时排行信息包含直播间在线用户列表，各种礼物排行榜，弹幕消息(可以理解为按消息维度的消息排行榜)等信息，适合使用 Redis 中的
     Sorted Set 结构进行存储。
     
##### Redis 的回收策略(淘汰策略)
- volatile-lru: 从已设置过期时间的数据集(server.db[i].expires)中挑选最近最少使用的数据淘汰
- volatile-ttl: 从已设置过期时间的数据集(server.db[i].expires)中挑选将要过期的数据淘汰
- volatile-random: 从已设置过期时间的数据集(server.db[i].expires)中任意选择数据淘汰
- allkeys-lru: 从数据集(server.db[i].dict)中挑选最近最少使用的数据淘汰
- allkeys-random: 从数据集(server.db[i].dict)中任意选择数据淘汰
- no-enviction(驱逐): 禁止驱逐数据
    - 使用策略规则: 
        - 如果数据呈现幂律分布，也就是一部分数据访问频率高，一部分数据访问频率低，则使用 allkeys-lru
        - 如果数据呈现平等分布，也就是所有的数据访问频率都相同，则使用 allkeys-random
    - volatile 和 allkeys 规定了是对已设置过期时间的数据集淘汰数据还是从全部数据集淘汰数据，后面的 lru、ttl 以及 random 是三种不同的淘汰
    策略，再加上一种 no-enviction 永不回收的策略。
    - Redis 内存数据集上升到一定大小的时候，就会施行数据淘汰策略，保证 Redis 中的数据是热点数据。
    
##### Redis 过期键的删除策略
- 定时删除:在设置键的过期时间的同时，创建一个定时器 timer，让定时器在键的过期时间来临时，立即执行对键的删除操作。
- 惰性删除:放任键过期不管，但是每次从键空间中获取键时，都检查取得的键是否过期，如果过期的话，就删除该键;如果没有过期，就返回该键。
- 定期删除:每隔一段时间程序就对数据库进行一次检查，删除里面的过期键。至于要删除多少过期键，以及要检查多少个数据库，则由算法决定。

##### 如何保证缓存与数据库双写时的数据一致性？
- 一般情况下我们都是这样使用缓存的: 先读缓存，缓存没有的话，就读数据库，然后取出数据后放入缓存，同时返回响应。这种方式很明显会存在缓存和数据库的数据
不一致的情况。
- 只要用缓存，就可能会涉及到缓存与数据库双存储双写，只要是双写，就一定会有数据一致性的问题，那么你如何解决一致性问题？
    - 一般来说，就是如果系统不是严格要求缓存+数据库必须一致性的话，缓存可以稍微的跟数据库偶尔有不一致的情况，最好不要做这个方案，读请求和写请求串行
    化，串到一个内存队列里去，这样就可以保证一定不会出现不一致的情况
    - 串行化之后，就会导致系统的吞吐量会大幅度的降低，用比正常情况下多几倍的机器去支撑线上的一个请求。

##### Redis 常见性能问题和解决方案(TODO)

### 基础框架

#### Mybatis

##### `#{}和${}的区别是什么？`
- ${}是 Properties 文件中的变量占位符，它可以用于标签属性值和 sql 内部，属于静态文本替换，比如${driver}会被静态替换为 com.mysql.jdbc.Driver。
- `#{}是 sql 的参数占位符，Mybatis 会将 sql 中的#{}替换为?号，在 sql 执行前会使用 PreparedStatement 的参数设置方法，按序给 sql 的?号占
位符设置参数值，比如 ps.setInt(0, parameterValue)，#{item.name} 的取值方式为使用反射从参数对象中获取 item 对象的 name 属性值，相当于
param.getItem().getName()`

##### 通常一个 Xml 映射文件都会写一个 Dao 接口与之对应，请问 Dao 接口的工作原理是什么？Dao 接口里的方法，参数不同时，方法能重载吗？
- Dao 接口，就是人们常说的 Mapper 接口，接口的全限名，就是映射文件中的 namespace 的值，接口的方法名，就是映射文件中 MappedStatement 的
id 值，接口方法内的参数，就是传递给 sql 的参数。Mapper 接口是没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为 key 值，可唯一
定位一个 MappedStatement，举例: com.malf.mappers.StudentDao.findStudentById，可以唯一找到 namespace 为 com.malf.mappers.StudentDao
下面 id = findStudentById 的 MappedStatement。在 Mybatis 中，每一个`<select>、<insert>、<update>、<delete>`标签，都会被解析为一
个 MappedStatement 对象。
- Dao 接口里的方法，是不能重载的，因为是全限名+方法名的保存和寻找策略。
- Dao 接口的工作原理是 JDK 动态代理，Mybatis 运行时会使用 JDK 动态代理为 Dao 接口生成代理 proxy 对象，代理对象 proxy 会拦截接口方法，
转而执行 MappedStatement 所代表的 sql，然后将 sql 执行结果返回。

##### Mybatis 的一级、二级缓存:
- 一级缓存: 基于 PerpetualCache 的 HashMap 本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该 Session 中的所有
 Cache 就将清空，默认打开一级缓存。
- 二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap 存储，其存储作用域为 Mapper(Namespace)，并且可自定义存储源，如 Ehcache。
默认不打开二级缓存，要开启二级缓存，使用二级缓存属性类需要实现 Serializable 序列化接口(可用来保存对象的状态),可在它的映射文件中配置<cache/>。
- 3)对于缓存数据更新机制，当某一个作用域(一级缓存 Session/二级缓存 Namespaces)进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被 clear。

##### Mybatis 是否支持延迟加载？如果支持，它的实现原理是什么？
- Mybatis 仅支持 association 关联对象和 collection 关联集合对象的延迟加载，association 指的就是一对一，collection 指的就是一对多查询。
在 Mybatis配置文件中，可以配置是否启用延迟加载 lazyLoadingEnabled=true|false。
- 它的原理是，使用 CGLIB 创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用 a.getB().getName()，拦截器 invoke()方法发现 
a.getB()是 null 值，那么就会单独发送事先保存好的查询关联 B 对象的 sql，把 B 查询上来，然后调用 a.setB(b)，于是 a 的对象 b 属性就有值了，
接着完成 a.getB().getName()方法的调用。


#### Spring 

##### 什么是 Spring 框架？
- Spring 是一种轻量级开发框架，旨在提高开发人员的开发效率以及系统的可维护性。
- 我们一般说 Spring 框架指的都是 Spring Framework，它是很多模块的集合，使用这些模块可以很方便地协助我们进行开发。这些模块是: 核心容器、数据访问/集成,、Web、AOP(面向切面编程)、工具、消息和测试模块。比如: Core Container 中的 Core 组件是Spring 所有组件的核心，Beans 组件和 Context 组件是实现IOC和依赖注入的基础，AOP组件用来实现面向切面编程。
- Spring 官网列出的 Spring 的 6 个特征:
    - 核心技术 : 依赖注入(DI)，AOP，事件(events)，资源，i18n，验证，数据绑定，类型转换，SpEL。
    - 测试 : 模拟对象，TestContext框架，Spring MVC 测试，WebTestClient。
    - 数据访问 : 事务，DAO支持，JDBC，ORM，编组XML。
    - Web支持 : Spring MVC和Spring WebFlux Web框架。
    - 集成 : 远程处理，JMS，JCA，JMX，电子邮件，任务，调度，缓存。
    - 语言 : Kotlin，Groovy，动态语言。
    
##### 对于 Spring IoC 和 AOP 的理解
- IOC
    - IoC(Inverse of Control:控制反转)是一种设计思想，就是将原本在程序中手动创建对象的控制权，交由 Spring 框架来管理。 IoC 容器是 Spring 用
    来实现 IoC 的载体， IoC 容器实际上就是个Map(key，value), Map 中存放的是各种对象。
    - 将对象之间的相互依赖关系交给 IoC 容器来管理，并由 IoC 容器完成对象的注入。这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。 
    IoC 容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。 
    - Spring 时代我们一般通过 XML 文件来配置 Bean，后来开发人员觉得 XML 文件来配置不太好，于是 SpringBoot 注解配置就慢慢开始流行起来。
- AOP 
    - AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任(例如事务处理、日志管理、权限
    控制等)封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。
    - Spring AOP 就是基于动态代理的，如果要代理的对象，实现了某个接口，那么 Spring AOP 会使用 JDK Proxy，去创建代理对象，而对于没有实现接口
    的对象，就无法使用 JDK Proxy 去进行代理了，这时候 Spring AOP 会使用 Cglib ，这时候 Spring AOP 会使用 Cglib 生成一个被代理对象的子类
    来作为代理。

##### 有哪些类型的通知(Advice)？
通知是个在方法执行前或执行后要做的动作，实际上是程序执行时要通过 Spring AOP 框架触发的代码段。
- Before - 这些类型的 Advice 在 joinpoint 方法之前执行，并使用 @Before 注解标记进行配置。
- After Returning - 这些类型的 Advice 在连接点方法正常执行后执行，并使用 @AfterReturning 注解标记进行配置。
- After Throwing - 这些类型的 Advice 仅在 joinpoint 方法通过抛出异常退出并使用 @AfterThrowing 注解标记配置时执行。
- After (finally) - 这些类型的 Advice 在连接点方法之后执行，无论方法退出是正常还是异常返回，并使用 @After 注解标记进行配置。
- Around - 这些类型的 Advice 在连接点之前和之后执行，并使用 @Around 注解标记进行配置。


##### Spring 中的 bean 的作用域有哪些？
- singleton: 唯一 bean 实例，Spring 中的 bean 默认都是单例的。
- prototype: 每次请求都会创建一个新的 bean 实例。
- request: 每一次 HTTP 请求都会产生一个新的 bean，该 bean 仅在当前 HTTP request 内有效。
- session: 每一次 HTTP 请求都会产生一个新的 bean，该 bean 仅在当前 HTTP session 内有效。
- global-session: 全局 session 作用域，仅仅在基于 portlet 的 web 应用中才有意义，Spring5已经没有了。Portlet 是能够生成语义代
码(例如: HTML)片段的小型 Java Web 插件。它们基于 portlet 容器，可以像 servlet 一样处理 HTTP 请求。但是，与 servlet 不同，每个 portlet
都有不同的会话。

##### Spring 中的 bean 是线程安全的吗？
- 在 @Controller/@Service 等容器中，默认情况下，scope 值是单例(singleton)的，也是线程不安全的。
- 尽量不要在 @Controller/@Service 等容器中定义静态变量，不论是单例(singleton)还是多实例(prototype)都是线程不安全的。
- 默认注入的 Bean 对象，在不设置 scope 的时候也是线程不安全的。
- 一定要定义变量的话，用 ThreadLocal 来封装，这个是线程安全的。

##### Spring 框架中用到了哪些设计模式？
- 工厂设计模式: Spring 使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
- 代理设计模式: Spring AOP 功能的实现。
- 单例设计模式: Spring 中的 Bean 默认都是单例的。
- 模板方法模式: Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
- 包装器设计模式: 我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。
- 观察者模式: Spring 事件驱动模型就是观察者模式很经典的一个应用。
- 适配器模式: Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配 Controller。
- ……

##### @RestController 和 @Controller 的区别
- Controller 返回一个页面
    - 单独使用 @Controller 不加 @ResponseBody 的话一般使用在要返回一个视图的情况，这种情况属于比较传统的Spring MVC 的应用，对应于前后端不
    分离的情况。
- @RestController 返回 JSON 或 XML 形式数据
    - @RestController 只返回对象，对象数据直接以 JSON 或 XML 形式写入 HTTP 响应(Response)中，这种情况属于 RESTful Web服务，这也是目前
    日常开发所接触的最常用的情况(前后端分离)。
- @Controller + @ResponseBody 返回 JSON 或 XML 形式数据
    - 在 Spring4 之前开发 RESTful Web 服务的话，你需要使用 @Controller 并结合 @ResponseBody注解，也就是说 
    @Controller + @ResponseBody = @RestController(Spring 4 之后新加的注解)
    - @ResponseBody 注解的作用是将 Controller 的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到HTTP 响应(Response)对象的 body
     中，通常用来返回 JSON 或者 XML 数据，返回 JSON 数据的情况比较多。

##### @Component 和 @Bean 的区别是什么？
- 作用对象不同: @Component 注解作用于类，而 @Bean 注解作用于方法。
- @Component 通常是通过类路径扫描来自动侦测以及自动装配到 Spring 容器中(我们可以使用 @ComponentScan 注解定义要扫描的路径从中找出标识了需要
装配的类自动装配到 Spring 的 bean 容器中)。@Bean 注解通常是我们在标有该注解的方法中定义产生这个 bean, @Bean 告诉了 Spring 这是某个类的实例，
当我需要用它的时候还给我。
- @Bean 注解比 Component 注解的自定义性更强，而且很多地方我们只能通过 @Bean 注解来注册 bean。比如当我们引用第三方库中的类需要装配到 Spring
容器时，则只能通过 @Bean 来实现(如下)。
    ```
    @Bean
    public OneService getService(status) {
        case (status)  {
            when 1:
                return new ServiceImpl1();
            when 2:
                return new ServiceImpl2();
            when 3:
                return new ServiceImpl3();
        }
    }
    ```
##### 将一个类声明为 Spring 的 bean 的注解有哪些？
我们一般使用 @Autowired 注解自动装配 bean，要想把类标识成可用于 @Autowired 注解自动装配的 bean 的类,采用以下注解可实现: 
- @Component: 通用的注解，可标注任意类为 Spring 组件。如果一个Bean不知道属于哪个层，可以使用 @Component 注解标注。
- @Repository: 对应持久层即 Dao 层，主要用于数据库相关操作。
- @Service: 对应服务层，主要涉及一些复杂的逻辑，需要用到 Dao 层。
- @Controller: 对应 Spring MVC 控制层，主要用于接受用户请求并调用 Service 层返回数据给前端页面。

### 工具和中间件

#### Nginx

##### 负载均衡的几种方式
- 轮询（默认）
- weight，代表权，权越高优先级越高
```
upstream myserver {
    server  127.0.0.1:8111  weight=1;
    server  127.0.0.1:8222  weight=2;
}
```
- fair，按后端服务器的响应时间来分配请求，响应时间短的优先分配
```
upstream myserver {
    server  127.0.0.1:8111;
    server  127.0.0.1:8222;
    fair;
}
```
- ip_hash, 每个请求按照访问 ip 的 hash 结果分配，这样每一个访客固定的访问一个后端服务器，可以解决 session 的问题
```
upstream myserver {
    ip_hash;
    server  127.0.0.1:8111;
    server  127.0.0.1:8222;
}
```
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
    - 需要合并到 master 的时候，合并分支的步骤: 
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


