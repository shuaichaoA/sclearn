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
            if (selector.select() > 0) {

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                        System.out.println(accept.getRemoteAddress() + "--上线了！！！");
                    }
                    if (key.isReadable()) {
                        readData(key);
                    }
                    if (key.isWritable()) {
                    }
                    if (key.isConnectable()) {
                    }
                    iterator.remove();
                }
            } else {

            }
        }
    }

    public void readData(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = 0;
            count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from 客户端：" + msg);
                // 转发给其他客户端
                sendMsgToOther(msg, channel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    // 转发消息
    public void sendMsgToOther(String msg, SocketChannel self) {
        System.out.println("服务器消息转发中.....");
        selector.keys().forEach(key -> {
            if (key.channel() instanceof SocketChannel && key.channel() != self) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) throws IOException {
        GroupServer groupServer = new GroupServer();
        groupServer.listren();
    }
}
