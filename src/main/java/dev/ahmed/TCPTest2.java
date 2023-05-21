package dev.ahmed;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * ClassName: TCPTest2
 * Description:
 * Example 2: The client sends a file to the server, and the server saves the file locally.
 * @author Ahmed Bughra
 * @create 2023-05-22
 */
public class TCPTest2 {
    /*
     * Note: Because it involves the closing of related resources, you need to use try-catch-finally to handle exceptions
     * */
    //client
    @Test
    public void client() throws IOException {
        //1. Create Socket
        //Specify the ip address and port number of the other party (that is, the server side)
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        int port = 9090;
        Socket socket = new Socket(inetAddress,port);

        //2. Create an instance of File and an instance of FileInputStream
        File file = new File("pic.jpg");
        FileInputStream fis = new FileInputStream(file);
        //3. Get the output stream through Socket
        OutputStream os = socket. getOutputStream();

        //4. Read and write data
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis. read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        System.out.println("data sent");

        //5. Close the Socket and related streams
        os. close();
        fis. close();
        socket. close();

    }

    //Server
    @Test
    public void server() throws IOException {
        //1. Create ServerSocket
        int port = 9090;
        ServerSocket serverSocket = new ServerSocket(port);
        //2. Receive socket from client:accept()
        Socket socket = serverSocket. accept();

        //3. Get an input stream through Socket
        InputStream is = socket. getInputStream();

        //4. Create an instance of the File class and an instance of FileOutputStream
        File file = new File("pic_copy.jpg");
        FileOutputStream fos = new FileOutputStream(file);

        //5. Read and write process
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }

        System.out.println("Data received");

        //6. Close the related Socket and stream
        fos. close();
        is. close();
        socket. close();
        serverSocket. close();
    }
}