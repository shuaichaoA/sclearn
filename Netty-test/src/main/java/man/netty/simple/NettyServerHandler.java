package man.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

import static io.netty.util.CharsetUtil.UTF_8;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        StudentPOJO.Student student = (StudentPOJO.Student) msg;
        System.out.println("id:-- " + student.getId());
        System.out.println("name:-- " + student.getName());
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端消息" + buf.toString(UTF_8));
//        System.out.println("客户端地址:" + ctx.channel().remoteAddress());
//        ctx.channel().eventLoop().schedule(() -> {
//            try {
//                Thread.sleep(10 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端-2", UTF_8));
//        },5, TimeUnit.SECONDS);
        System.out.println("...GO ON...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端-1", UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
