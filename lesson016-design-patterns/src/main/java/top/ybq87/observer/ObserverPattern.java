package top.ybq87.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/14
 */
public class ObserverPattern {
    
    /**
     * 观察者模式
     * subject 中维护观察者的信息，因为要通知观察者变化
     * ConcreteSubject 具体被观察者
     * observer，观察者。
     * ConcreteObserver 具体观察者
     * @param args
     */
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        AbstractObserver observer1 = new ConcreteObserver1();
        AbstractObserver observer2 = new ConcreteObserver2();
        
        subject.regist(observer1);
        subject.regist(observer2);
        
        subject.setState(11);
    }
}

abstract class AbstractObserver {
    
    public abstract void update(Integer state);
}

abstract class AbstractSubject {
    
    List<AbstractObserver> observers = new ArrayList<>();
    
    void regist(AbstractObserver observer) {
        observers.add(observer);
    }
    
    abstract void notifyAllObserver(Integer state);
}

class ConcreteSubject extends AbstractSubject {
    
    private Integer state;
    
    /**
     * 状态发生改变，那么通知观察者
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
        notifyAllObserver(state);
    }
    
    @Override
    void notifyAllObserver(Integer state) {
        for (AbstractObserver observer : observers) {
            observer.update(state);
        }
    }
}

class ConcreteObserver1 extends AbstractObserver {
    
    @Override
    public void update(Integer state) {
        System.out.println("》》》》》 ConcreteObserver1 state:" + state + 1);
    }
}

class ConcreteObserver2 extends AbstractObserver {
    
    @Override
    public void update(Integer state) {
        System.out.println("》》》》》 ConcreteObserver2 state:" + state * 2);
    }
}
