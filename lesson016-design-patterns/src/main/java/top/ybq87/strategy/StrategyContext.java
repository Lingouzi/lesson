package top.ybq87.strategy;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/13
 */
public class StrategyContext {
    
    private Strategy strategy;
    
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }
    
    void sort(int[] arr) {
        strategy.sort(arr);
    }
}
