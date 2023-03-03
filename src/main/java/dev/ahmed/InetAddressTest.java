package dev.ahmed;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ClassName: InetAddressTest
 * Description:
 *
 * @author Ahmed Bughra
 * @create 2023-03-03
 */
public class InetAddressTest {
    public static void main(String[] args) {


        try {
            //1. Instantiate
            //getByName(String host): Get the instance of InetAddress corresponding to the specified ip
            InetAddress inet1 = InetAddress.getByName("192.168.23.31");
            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("www.google.com");
            System.out.println(inet2); //www.atguigu.com/122.228.95.175


            //getLocalHost(): Get an instance of InetAddress corresponding to the local ip
// InetAddress inet3 = InetAddress. getLocalHost();
// System.out.println(inet3);//DESKTOP-QCP2QPI/192.168.21.107
//
// InetAddress inet4 = InetAddress. getByName("127.0.0.1");
// System.out.println(inet4);

            //2. Two commonly used methods
// System.out.println(inet1.getHostName());//192.168.23.31
// System.out.println(inet1.getHostAddress());//192.168.23.31

            System.out.println(inet2.getHostName());//www.google.com
            System.out.println(inet2.getHostAddress());//122.228.95.175

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
