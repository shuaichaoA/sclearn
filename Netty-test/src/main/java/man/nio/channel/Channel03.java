package man.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel03 {
    public static void main(String[] args) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        FileInputStream fileInputStream = new FileInputStream("E:\\file.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileoutputStream = new FileOutputStream("E:\\file_copy.txt");
        FileChannel channel1 = fileoutputStream.getChannel();
        while (true) {
            allocate.clear();

            int read = channel.read(allocate);
            if (read != -1) {
                allocate.flip();
                channel1.write(allocate);
            } else {
                break;
            }

        }
    }
}
