#### 1、队列

#### 2、阻塞队列
默认有 7 个，是 Collection 接口的子接口 Queue、BlockingQueue 的几个实现类
##### 2.1、ArrayBlockingQueue
数组结构组成的有界阻塞对联
##### 2.2、LinkedBlockingQueue
链表结构组成的有界队列（但是默认长度是 Integer.MAX_VALUE），相当于无限了

##### 2.3、SynchronousQueue
不存储元素的阻塞队列，即单个元素的队列。有且只有一个。


#### Synchronized 和 ReentrantLock 的区别
1、原始构成
Synchronized 是关键字，属于 jvm 层面（使用的是 monitor，且有 2 次退出，为了保证异常也退出。）
ReentrantLock 是 api 层面，是 java5 之后的一个类

2、使用方法
Synchronized 不需要用户去手动释放锁，会自动释放
ReentrantLock 需要用户手动释放

3、可否中断
Synchronized 不可中断，除非异常或者运行完成
ReentrantLock 可以
 * 设置超时方法 tryLock
 * interrupt() 方法直接中断

4、加锁是否公平
Synchronized 默认非公平
ReentrantLock 默认非公平，但是可以设置为公平锁

5、锁绑定多个条件 Condition
Synchronized 没有
ReentrantLock 用来实现分组唤醒需要唤醒的线程，可以精确唤醒
  





