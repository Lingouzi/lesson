package top.ybq87;

import java.util.concurrent.CountDownLatch;

/**
 * 枚举类可以当成数据库用，提高效率
 *
 *
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/19
 */
public class EnumTest {
    
    public static void main(String[] args) {
        // 如果不使用 CountDownLatch，线程执行有先后顺序，虽然班长在主线程，但是也可能先执行完。
        CountDownLatch countDownLatch = new CountDownLatch(3);
        
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 学生" + finalI + "走了");
                // 计数 -1
                countDownLatch.countDown();
            }, Country.getCountry(finalI).getMessage()).start();
        }
        try {
            // 等待计数为 0，结束 await。主线程唤醒继续执行。
            countDownLatch.await();
            System.out.println("班长最后走");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

enum Country {
    ONE(1, "齐国"),
    TWO(2, "楚国"),
    THREE(3, "燕国");
    
    private Integer code;
    private String message;
    
    Country(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public static Country getCountry(int idx) {
        for (Country c : Country.values()) {
            if (c.code == idx) {
                return c;
            }
        }
        return null;
    }
}
