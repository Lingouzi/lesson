package top.ybq87.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

/**
 * 自定义 handler，需要继承 netty 规定的某个 handlerAdapter
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/10
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    
    /**
     *
     * @param ctx 上下文，含有管道 pipeline，通道，地址
     * @param msg 客户端发送来的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx >> " + ctx);
        System.out.println("thread name >>" + Thread.currentThread().getName());
        // 这个 bytebuf 是 netty 提供的，不是 nio 中的 bytebuffer
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("msg>>" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("client address>> " + ctx.channel().remoteAddress());
        // super.channelRead(ctx, msg);
    }
    
    /**
     * 数据读取完毕之后，返回一些信息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello....", CharsetUtil.UTF_8));
    }
    
    /**
     * 发生异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
