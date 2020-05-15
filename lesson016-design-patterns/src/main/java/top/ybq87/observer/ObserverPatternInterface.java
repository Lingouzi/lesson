package top.ybq87.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/14
 */
public class ObserverPatternInterface {
    
    public static void main(String[] args) {
        InterfaceSubject subject = new ConcreteSubject2();
        InterfaceObserver observer3 = new ConcreteObserver3();
        InterfaceObserver observer4 = new ConcreteObserver4();
        subject.regist(observer3);
        subject.regist(observer4);
        
        // 模拟状态变更，省略了变更方法，
        subject.notifyAllObserver();
    }
}

interface InterfaceSubject {
    
    /**
     * 注册观察者
     */
    void regist(InterfaceObserver observer);
    
    /**
     * 删除观察者
     */
    void remove(InterfaceObserver observer);
    
    /**
     * 通知观察者变化
     */
    void notifyAllObserver();
}

interface InterfaceObserver {
    
    /**
     * 接收到变化
     */
    void update();
}

class ConcreteSubject2 implements InterfaceSubject {
    
    private volatile List<InterfaceObserver> observers = new CopyOnWriteArrayList<>();
    
    @Override
    public void regist(InterfaceObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void remove(InterfaceObserver observer) {
        observers.removeIf(observer::equals);
    }
    
    @Override
    public void notifyAllObserver() {
        for (InterfaceObserver observer : observers) {
            observer.update();
        }
    }
}

class ConcreteObserver3 implements InterfaceObserver {
    
    @Override
    public void update() {
        System.out.println("》》》》ConcreteObserver3 收到通知");
    }
}

class ConcreteObserver4 implements InterfaceObserver {
    
    @Override
    public void update() {
        System.out.println("》》》》ConcreteObserver4 收到通知");
    }
}
