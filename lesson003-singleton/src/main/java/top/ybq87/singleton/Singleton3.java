package top.ybq87.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 静态代码块的方式。
 * 应用的地方，读取配置文件属性值。
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/16
 */
public class Singleton3 {
    
    private String info;
    
    public static final Singleton3 INSTANCE;
    
    static {
        Properties properties = new Properties();
        try {
            properties
                    .load(Singleton3.class.getClassLoader().getResourceAsStream("prop.properties"));
            String info1 = properties.getProperty("info");
            INSTANCE = new Singleton3(info1);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    
    private Singleton3(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
