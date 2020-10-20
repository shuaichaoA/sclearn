package man.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class MkTimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("One Channel Connect");
        //获取系统时间戳的字符串
        String serverTime = new Date(System.currentTimeMillis()).toString();
        //创建一个 ByteBuf 保存特定字节串
        ByteBuf resp = Unpooled.copiedBuffer(serverTime.getBytes());
        //将 ByteBuf 发送给客户端
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //异常关闭
        ctx.close();
    }
}