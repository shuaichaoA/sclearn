package man.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.SimpleTimeZone;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.util.CharsetUtil.UTF_16;
import static io.netty.util.CharsetUtil.UTF_8;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {
            System.out.println("msg类型：" + msg.getClass());
            System.out.println("客户端地址:" + channelHandlerContext.channel().remoteAddress());
            HttpRequest message = (HttpRequest) msg;
            if (new URI(message.getUri()).getPath().equals(("/favicon.ico"))) {
                System.out.println("拒绝请求！");
                return;
            }
            // 返回信息构建
            ByteBuf content = Unpooled.copiedBuffer("Hello,我是服务器", UTF_16);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }

    }
}
