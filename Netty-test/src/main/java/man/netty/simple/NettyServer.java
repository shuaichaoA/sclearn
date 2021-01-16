package man.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import man.netty.http.TestServerHandler;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG, 128)
//                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new HttpServerCodec());
//                        pipeline.addLast(new ChunkedWriteHandler());
//                        pipeline.addLast(new HttpObjectAggregator(8192));
//                        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                        pipeline.addLast(new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
                        pipeline.addLast(new NettyServerHandler());
                    }
                });
        ChannelFuture cf = bootstrap.bind(6668).sync();
        cf.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("绑定成功");
            } else {
                System.out.println("绑定失败");

            }
        });
        cf.channel().closeFuture().sync();

    }
}


