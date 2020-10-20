package man.nio.selector.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class clientTest {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);


        if (!channel.connect(address)) {
            while (!channel.finishConnect()) {
                System.out.println("......");
            }
        }
        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());
        channel.write(wrap);
        System.in.read();
    }
}
