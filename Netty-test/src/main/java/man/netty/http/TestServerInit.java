package man.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpServerCodec;
import man.netty.simple.NettyServerHandler;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class TestServerInit extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        pipeline.addLast(new TestServerHandler());
    }
}
