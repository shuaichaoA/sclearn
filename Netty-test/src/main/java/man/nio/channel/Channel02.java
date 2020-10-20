package man.nio.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel02 {
    public static void main(String[] args) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(22);

        FileInputStream fileInputStream = new FileInputStream("E:\\file.txt");
        FileChannel channel = fileInputStream.getChannel();
        int write = channel.read(allocate);
        System.out.println(new String(allocate.array()));
        fileInputStream.close();
    }
}
