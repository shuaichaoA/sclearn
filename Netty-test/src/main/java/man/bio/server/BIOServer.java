package man.bio.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        final ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务启动了。。。");
        while (true) {
            System.out.println("等待连接");
            final Socket accept = serverSocket.accept();
            System.out.println("客户端接入");
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        byte[] bytes = new byte[1024];
                        InputStream inputStream = accept.getInputStream();
                        while (true) {
                            int read = inputStream.read(bytes);
                            if (read > -1) {
                                System.out.println(new String(bytes, 0, read));
                            } else {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

        }

    }
}
