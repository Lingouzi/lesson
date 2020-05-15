package top.ybq87.builder;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/14
 */
public class BuilderPattern {
    
    /**
     * 以组装电脑为例，我告诉组装员需要什么配件，他帮我装好
     * @param args
     */
    public static void main(String[] args) {
        BuilderFactory builderFactory = new BuilderFactory.Builder()
                .cpu("intel")
                .Board("华硕")
                .HHD("ssd")
                .screen("dell")
                .build();
        System.out.println(builderFactory.toString());
    }
}

class BuilderFactory {
    
    private String CPU;
    private String Screen;
    private String HHD;
    private String Board;
    
    public BuilderFactory(String CPU, String screen, String HHD, String board) {
        this.CPU = CPU;
        Screen = screen;
        this.HHD = HHD;
        Board = board;
    }
    
    public static class Builder {
        
        private String CPU;
        private String Screen;
        private String HHD;
        private String Board;
        
        public Builder cpu(String CPU) {
            this.CPU = CPU;
            return this;
        }
        
        public Builder screen(String Screen) {
            this.Screen = Screen;
            return this;
        }
        
        public Builder HHD(String HHD) {
            this.HHD = HHD;
            return this;
        }
        
        public Builder Board(String Board) {
            this.Board = Board;
            return this;
        }
        
        public BuilderFactory build() {
            return new BuilderFactory(this.CPU, this.Screen, this.HHD, this.Board);
        }
    }
    
    @Override
    public String toString() {
        return "BuilderFactory{" +
                "CPU='" + CPU + '\'' +
                ", Screen='" + Screen + '\'' +
                ", HHD='" + HHD + '\'' +
                ", Board='" + Board + '\'' +
                '}';
    }
}
