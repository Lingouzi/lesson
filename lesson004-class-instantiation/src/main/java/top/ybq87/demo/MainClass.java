package top.ybq87.demo;

/**
 * 多态的思想实际上是把“做什么”和“谁去做”分离开来，要实现这一点，归根结底先要消除类型之间的耦合关系
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/4/17
 */
public class MainClass {
    
    public static void main(String[] args) {
        Animal duck = new Duck();
        Animal chicken = new Chicken();
        
        AnimalSound animalSound = new AnimalSound();
        animalSound.sound(duck);
        animalSound.sound(chicken);
    }
}
