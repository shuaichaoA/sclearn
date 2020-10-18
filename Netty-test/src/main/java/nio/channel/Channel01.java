package nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello尚硅谷";
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        byte[] bytes = str.getBytes();
        allocate.put(bytes);
        allocate.flip();

        FileOutputStream fileInputStream = new FileOutputStream("01.txt");
        FileChannel channel = fileInputStream.getChannel();
        int write = channel.write(allocate);

    }
}
