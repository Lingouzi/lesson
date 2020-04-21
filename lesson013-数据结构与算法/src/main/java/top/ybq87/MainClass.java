package top.ybq87;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/21
 */
public class MainClass {
    
    public static void main(String[] args) {
        // 线程安全的 ArrayList
        List arrayList = Collections.synchronizedList(new ArrayList<>());
        arrayList = new CopyOnWriteArrayList();
        arrayList.add("2");
        System.out.println(arrayList);
    }
}
