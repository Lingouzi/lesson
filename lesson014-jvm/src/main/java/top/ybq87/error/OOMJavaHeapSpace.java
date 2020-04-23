package top.ybq87.error;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/23
 */
public class OOMJavaHeapSpace {
    
    public static void main(String[] args) {
        // 测试堆溢出，只需要构建超大的对象，为了测试设置一下堆的大小
        // 跑起来之前设置运行参数 ：-Xms10m -Xmx10m -XX:+PrintGCDetails
        // -Xms10m -Xmx10m ；设置堆的最小和最大内存为 10m
        // byte 占用 1 个字节，8 bit，
        // 这里申请 11m 的内存。
        byte[] bytes = new byte[11 * 1024 * 1024];
        
    }
}
