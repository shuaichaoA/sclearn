package man.nio.selector.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class serverTest {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 绑定端口-配置非阻塞
        socketChannel.socket().bind(new InetSocketAddress(6666));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待1s");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey s = iterator.next();
                if (s.isAcceptable()) {
                    try {
                        // 创建客户端注册到selector
                        SocketChannel accept = socketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println("获取一个客户端："+accept.hashCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                if (s.isReadable()) {
                    SocketChannel channel = (SocketChannel) s.channel();
                    try {
                        ByteBuffer attachment = (ByteBuffer) s.attachment();
                        channel.read(attachment);

                        System.out.println("收到内容：" + new String(attachment.array()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                iterator.remove();

            }
        }


    }
}
