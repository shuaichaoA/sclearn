package man.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        //启动服务端
        new Server().run(port);
    }

    void run(int port) throws Exception {
        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        try {
            //引导类
            ServerBootstrap b = new ServerBootstrap();
            b.group(acceptorGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //MkTimeServerHandler属于业务Handler
                            socketChannel.pipeline().addLast(new MkTimeServerHandler());
                        }
                    });
            //阻塞直到异步绑定服务器完成
            ChannelFuture f = b.bind(port).sync();
            //阻塞直到Channel关闭
            f.channel().closeFuture().sync();
        } finally {
            acceptorGroup.shutdownGracefully();
        }
    }
}
