package man.nio.buffer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ScaAndGatBuffer {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel open = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8000);
        open.socket().bind(address);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        int len = 8;
        while (true) {

            SocketChannel accept = open.accept();

            int read = 0;
            while (read < len) {
                long i = accept.read(byteBuffers);
                read += i;
            }



        }

    }
}
