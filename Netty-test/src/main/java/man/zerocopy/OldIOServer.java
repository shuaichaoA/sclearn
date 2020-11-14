package man.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);

        while (true) {
            Socket accept = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());

            byte[] bytes = new byte[4096];
            while (true) {
                int read = dataInputStream.read(bytes, 0, bytes.length);
                if (-1 == read) {
                    break;
                }
            }
        }
    }
}
