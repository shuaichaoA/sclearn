package man.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupServer {


    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static int PORT = 6667;

    public GroupServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listren() throws IOException {
        while (true) {
            if (selector.select(2000) == 0) {
                System.out.println("服务器等待2s");
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    SocketChannel accept = serverSocketChannel.accept();
                    System.out.println(accept.getRemoteAddress() + "--上线了！！！");
                }
                if (key.isReadable()) {
                }
                if (key.isWritable()) {
                }
                if (key.isConnectable()) {
                }
                iterator.remove();
            }
        }
    }

    public void readData(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = channel.read(buffer);
        if (count > 0) {
            String msg = new String(buffer.array());
            System.out.println("from 客户端：" + msg);
            // 转发给其他客户端

        }

    }

    public static void main(String[] args) {

    }
}
