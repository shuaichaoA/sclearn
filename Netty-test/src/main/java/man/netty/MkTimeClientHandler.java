package man.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/** * @ClassName MkTimeClientHandler * @Description 接受时间消息 * @Author Java猫说 * @Date 2020/1/4 0004 14:04 **/
public class MkTimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        byte[] req = new byte[m.readableBytes()];
        m.readBytes(req);
        String serverTime = new String(req);
        System.out.println(serverTime + " From Server.");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}