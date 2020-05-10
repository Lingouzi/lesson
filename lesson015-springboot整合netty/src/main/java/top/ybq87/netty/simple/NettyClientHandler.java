package top.ybq87.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

/**
 * @author ly
 * @blog http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @email 664162337@qq.com
 * @wechat ly19870316 / 公众号：林子曰
 * @date 2020/5/10
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    
    /**
     * 通道就绪时就触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("..client....send>>" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好", CharsetUtil.UTF_8));
    }
    
    /**
     * 有读取事件时，触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client received msg>>" + buf.toString(CharsetUtil.UTF_8));
    }
}
