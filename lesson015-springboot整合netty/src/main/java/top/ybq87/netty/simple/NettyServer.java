package top.ybq87.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/10
 */
public class NettyServer {
    
    public static void main(String[] args) throws InterruptedException {
        /*
        1、创建 bossgroup 和 workergroup
        2、bossgroup 只处理链接请求，
        3、业务处理交给 workergroup
        4、2 个 group 都是无限循环
         */
        ServerBootstrap bootstrap;
        // group 含有的子线程（NioEventLoop）的个数 = cpu 核数 * 2
        // 也可以在构造方法指定初始数量
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            
            // 创建服务器端的启动对象，配置启动参数
            bootstrap = new ServerBootstrap();
            // 使用链式编程的方式来进行设置
            bootstrap
                    // 2 个线程组
                    .group(boss, worker)
                    // 使用 NioServerSocketChannel 作为服务器通道实现
                    .channel(NioServerSocketChannel.class)
                    // 线程队列等待链接的个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动链接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //给 workergroup 的 EventLoop 对应的管道设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 给 pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("server init ready...");
            System.out.println("bind port..8080");
            ChannelFuture sync = bootstrap.bind(8080).sync();
            System.out.println("");
            
            // 对关闭通道进行监听, 有 closeFuture 事件的时候才执行
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
        
    }
}
