package nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel04 {
    public static void main(String[] args) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        FileInputStream fileInputStream = new FileInputStream("E:\\file.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileoutputStream = new FileOutputStream("E:\\file_copy2.txt");
        FileChannel channel1 = fileoutputStream.getChannel();
        channel.transferTo(0,channel.size(),channel1);
    }
}
