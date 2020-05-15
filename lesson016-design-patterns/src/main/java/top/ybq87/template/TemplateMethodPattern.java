package top.ybq87.template;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/14
 */
public class TemplateMethodPattern {
    
    public static void main(String[] args) {
        AbstractTemplate runner = new TemplateRunner();
        runner.play();
        System.out.println(">>>>>>>>>>>>");
        AbstractTemplate swimer = new TemplateSwimer();
        swimer.play();
    }
}

/**
 * 父模板，定义了方法，可能自己实现一部分，大部分实现交给子类。
 * 其中的模板方法定义了构建顺序
 */
abstract class AbstractTemplate {
    
    /**
     * 交给子类实现
     */
    protected abstract void before();
    
    protected abstract void playing();
    
    protected abstract void after();
    
    public final void play() {
        init();
        before();
        playing();
        after();
        destory();
    }
    
    protected void init() {
        System.out.println("初始化..");
    }
    
    protected void destory() {
        System.out.println("销毁..");
    }
}

class TemplateRunner extends AbstractTemplate {
    
    
    @Override
    public void before() {
        System.out.println("穿鞋子..");
    }
    
    @Override
    public void playing() {
        System.out.println("跑步..");
    }
    
    @Override
    public void after() {
        System.out.println("脱鞋子..");
    }
}

class TemplateSwimer extends AbstractTemplate {
    
    
    @Override
    public void before() {
        System.out.println("带护目镜..");
    }
    
    @Override
    public void playing() {
        System.out.println("游泳..");
    }
    
    @Override
    public void after() {
        System.out.println("取下护目镜..");
    }
}

