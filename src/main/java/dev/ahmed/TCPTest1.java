package dev.ahmed;



import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName: TCPTest1
 * Description:
 * Example 1: The client sends content to the server, and the server prints the content to the console.
 *
 * @author Ahmed Bughra
 * @create 2023-03-05
 */
public class TCPTest1 {

    // client
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1. Create a Socket
            InetAddress inetAddress = InetAddress.getByName("192.168.21.107"); //declare the other party's ip address
            int port = 8989;//declare the port number of the other party
            socket = new Socket(inetAddress, port);

            //2. Send data
            os = socket. getOutputStream();
            os.write("Hello, I am the client, please take care of me.".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3. Close the socket, close the stream
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Server
    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null; //blocking method
        InputStream is = null;
        try {
            //1. Create a ServerSocket
            int port = 8989;
            serverSocket = new ServerSocket(port);

            //2. Call accept() to receive the client's Socket
            socket = serverSocket. accept();
            System.out.println("The server is enabled");

            System.out.println("Received connection from " + socket.getInetAddress().getHostAddress() + "");

            //3. Receive data
            is = socket. getInputStream();
            byte[] buffer = new byte[5];
            int len;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); //A byte[] is maintained internally
            while ((len = is. read(buffer)) != -1) {
                //Wrong, there may be garbled characters.
// String str = new String(buffer, 0, len);
// System.out.print(str);

                //correct
                baos.write(buffer,0,len);
            }

            System.out.println(baos.toString());

            System.out.println("\nData received");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. Close Socket, ServerSocket, stream
            try {
                if (socket != null) {
                    socket.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (serverSocket != null) {
                    serverSocket.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
