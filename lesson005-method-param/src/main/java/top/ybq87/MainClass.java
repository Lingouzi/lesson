package top.ybq87;

import com.sun.tools.javac.util.ArrayUtils;
import java.util.Arrays;

/**
 * 形参：方法的参数列表称为形参
 * 实参：调用方法时，传递的参数为实参
 *
 * 规则：
 * 1、形参是基本数据类型的就是 值传递，
 *      基本数据类型：int，double、float、boolean、byte、char、short、long
 * 2、实参是引用数据类型的，传递的是地址值
 *      2.1、传递地址值
 *      2.2、特殊类，String 和包装类【基本类型的包装类】对象具有不可变性
 *          什么是不可变性？就是一旦创建它的字面量，那么就不会发生改变，想要改变变量的值，只能重新创建一个，然后指过去。
 * 方法调用栈
 * 常量池、堆、栈
 *
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
        int i = 1;
        String str = "ybq";
        Integer num = 128;
        int[] arr = {1, 2, 3, 4, 5, 6};
        MyData myData = new MyData();
        
        // i, str, num, arr, myData 这里就是实参
        change(i, str, num, arr, myData);
        
        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("num = " + num);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("myData.a = " + myData.a);
    }
    
    /**
     * 这里的 int j, String s, Integer n, int[] a, MyData m 就是形参
     * @param j
     * @param s
     * @param n
     * @param a
     * @param m
     */
    private static void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += ".top";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }
}

class MyData {
    
    int a = 10;
}