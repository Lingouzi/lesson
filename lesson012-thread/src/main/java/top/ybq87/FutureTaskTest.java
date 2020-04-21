package top.ybq87;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/20
 */
public class FutureTaskTest {
    
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallableThread());
        new Thread(futureTask, "线程名称").start();
        try {
            // get() 方法最好留到程序最后
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnableThread implements Runnable {
    
    @Override
    public void run() {
        System.out.println("....MyRunnableThread.");
    }
}

class MyCallableThread implements Callable<Integer> {
    
    
    @Override
    public Integer call() throws Exception {
        System.out.println("...MyCallableThread");
        return 1024;
    }
}
