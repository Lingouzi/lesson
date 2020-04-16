package top.ybq87.singleton;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * singleton 设计模式
 * singleton 类在整个系统中只能有一个实例对象，可以被获取和使用的代码模式
 * 例如：
 *      jvm 的运行环境的 Runtime 类
 *
 * 要点
 * 1、只能有一个实例【构造器私有化】
 * 2、必须自行创建，不能让其他人创建【所以要用静态变量保存实例】
 * 3、必须向整个系统提供这个实例【对外提供，1：public，2：get 方法】
 *
 * 创建
 * 1、饿汉，立即创建【饿汉不存在线程安全问题，因为类初始化时，直接创建了对象】
 * 2、懒汉，延迟创建，
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class MainClass {
    
    public static void main(String[] args) {
        // // 1.
        // System.out.println(Singleton1.singleton1);
        // // 2. 枚举被重写了 tostring，所以打印的就是枚举的名称
        // System.out.println(Singleton2.INSTANCE);
        // // 3.
        // System.out.println(Singleton3.INSTANCE.getInfo());
        // System.out.println(Singleton3.INSTANCE);
        //
        // // 4.1 单线程访问
        // for (int i = 0; i < 10; i++) {
        //     System.out.println(Singleton4.getInstance());
        // }
        // 4.2 多线程访问, 可能出现问题，不是一定。
        // for (int i = 0; i < 10; i++) {
        //     new Thread(() -> {
        //         System.out.println(Singleton4.getInstance());
        //         try {
        //             TimeUnit.SECONDS.sleep(1);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }, "threadname").start();
        // }
        
        // 5. 线程安全的
        // for (int i = 0; i < 10; i++) {
        //     new Thread(() -> {
        //         System.out.println(Singleton5.getInstance());
        //         try {
        //             TimeUnit.SECONDS.sleep(1);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }, "threadname").start();
        // }
        //
        
        // 6.
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Singleton6.getInstance());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "threadname").start();
        }
        
    }
    
}
