# Java network programming

## 1. Overview of network programming

Java is a language that can be used for developing network applications on the Internet. It provides support for network applications at the language level, and programmers can easily develop common network applications.

The network class library provided by Java can realize painless network connection. The underlying details of networking are hidden in Java's native installation system and controlled by the JVM. Java implements a cross-platform network library, and programmers are facing a unified network programming environment.

### 1.1 Software Architecture

- **C/S architecture**: The full name is Client/Server structure, which refers to the client and server structure. Common programs include QQ, Meituan app, 360 Security Guard and other software.

- **B/S architecture**: The full name is Browser/Server structure, which refers to the browser and server structure. Common browsers include IE, Google, Firefox, etc.


Both architectures have their own advantages, but no matter what kind of architecture, they are inseparable from the support of the network. **Network programming** is a program that realizes the communication between two computers under a certain protocol.

### 1.2 Network Basics

- **computer network:**
  Interconnect computers distributed in different geographic regions and specialized external devices with communication lines to form a large-scale and powerful network system, so that many computers can easily transmit information to each other, share hardware, software, data information and other resources .

- **Purpose of network programming: **Exchange data and communicate with other computers directly or indirectly through network protocols.

- **There are three main problems in network programming:**
  - Question 1: How to accurately locate one or more hosts on the network
  - Question 2: How to locate a specific application on the host
  - Question 3: After finding the host, how to transmit data reliably and efficiently

## 2. Network communication elements

### 2.1 How to realize the communication between the hosts in the network

- Addresses of both parties to the correspondence
  -IP
  - The port number
- Certain rules: communication between different hardware, operating systems, all this requires a certain rule. And we call this rule a protocol, that is, a network communication protocol.


### 2.2 Communication element 1: IP address and domain name

#### 2.2.1 IP address

**IP address: Internet Protocol Address (Internet Protocol Address)**, commonly known as IP. An IP address is used to uniquely number a computer device on a network. If we compare "personal computer" to "a phone", then "IP address" is equivalent to "telephone number".

**IP address classification method 1:**

- `IPv4`: It is a 32-bit binary number, usually divided into 4 bytes, expressed in the form of `a.b.c.d`, expressed in dotted `decimal`, such as `192.168.65.100`. Among them, a, b, c, and d are all decimal integers between 0 and 255.

  -

  - Up to 4.2 billion can be represented this way. Among them, 3 billion are in North America, 400 million in Asia, and 290 million in China. Early 2011 was exhausted.

  - IP address = network address + host address
    - Network address: Identifies the network segment where the computer or network device resides
    - Host Address: Identifies a specific host or network device

    Among them, category E is used for scientific research.

- `IPv6`: Due to the vigorous development of the Internet, the demand for IP addresses is increasing, but the network address resources are limited, making the allocation of IP more and more tense.

  In order to expand the address space, it is planned to redefine the address space through IPv6, using 128-bit address length, a total of 16 bytes, written as 8 unsigned integers, each integer is represented by four hexadecimal digits, and colons are used between numbers (:)separate. For example: `ABCD:EF01:2345:6789:ABCD:EF01:2345:6789`, according to the conservative method to estimate the actual addresses that can be allocated by IPv6, more than 1,000 addresses can still be allocated per square meter of the entire earth, this solves the problem Solved the problem of insufficient network address resources. On June 6, 2012, the Internet Society held the World IPv6 Launch Day. On this day, the global IPv6 network was officially launched. Many well-known websites, such as Google, Facebook, and Yahoo, began to permanently support IPv6 access at 0:00 GMT (8:00 Beijing time) on the same day. In June 2018, the three major operators and Alibaba Cloud announced that they will provide IPv6 services in an all-round way, and plan to help China's Internet truly realize "IPv6 Only" by 2025.

  In the design process of IPv6, in addition to solving the problem of address shortage once and for all, other problems that cannot be solved in IPv4 are also considered, mainly including end-to-end IP connection, quality of service (QoS), security, multicast, mobile compatibility, plug and play, etc.

**IP address classification method two:**

Public addresses (used by the World Wide Web) and private addresses (used by local area networks). The address starting with 192.168. is a private address, and the range is 192.168.0.0--192.168.255.255, which is specially used for the internal use of the organization.

**Common commands:**

- To view the IP address of the machine, enter in the console:

```java
ipconfig
```

- Check whether the network is connected, enter in the console:

```java
ping space IP address
ping 220.181.57.216
```

**Special IP address:**

- Local loopback address (hostAddress): `127.0.0.1`
- Hostname (hostName): `localhost`

#### 2.2.2 Domain name

Hosts on the Internet have two ways to represent addresses:

- Domain name (hostName): www.google.com
- IP address (hostAddress): 202.108.35.210

**Domain name resolution:**Because the IP address numbers are not easy to remember, domain names appear. The domain name is easy to remember. After entering the domain name of a host when connecting to the network, the domain name server (DNS, Domain Name System, Domain Name System) is responsible for converting the domain name into an IP address, so that a connection can be established with the host.

1. Enter www . qq .com domain name in the browser, the operating system will first check whether its local `hosts file` has this URL mapping relationship, if so, it will first call this IP address mapping to complete domain name resolution.
2. If there is no mapping of this domain name in hosts, search `Local DNS Resolver Cache` to see if there is a mapping relationship of this URL. If so, return directly to complete domain name resolution.
3. If there is no corresponding URL mapping relationship between hosts and the local DNS resolver cache, it will first find the preferred DNS server set in the TCP/IP parameters. Here we call it `local DNS server`. When this server receives a query, If the domain name to be queried is included in the local configuration area resources, the resolution result will be returned to the client to complete the domain name resolution, which is authoritative.
4. If the domain name to be queried is not resolved by the local DNS server area, but the server has "cached" the URL mapping relationship, then call this IP address mapping to complete the domain name resolution, which is not authoritative.
5. If the local zone file and cache resolution of the local DNS server are invalid, then query according to the settings of the local DNS server (whether to set a forwarder). If the forwarding mode is not used, the local DNS will send the request to 13 root DNS, the root After receiving the request, the DNS server will determine who is authorized to manage the domain name (.com), and will return an IP responsible for the top-level domain name server. After the local DNS server receives the IP information, it will contact the server responsible for the .com domain. After the server responsible for the .com domain receives the request, if it cannot resolve it, it will find a lower-level DNS server address (http://qq.com) that manages the .com domain to the local DNS server. When the local DNS server receives this address, it will look for the (http://qq.com) domain server, repeat the above actions, and query until it finds the www.qq.com host.
6. If the forwarding mode is used, the DNS server will forward the request to the upper-level DNS server for resolution. If the upper-level server cannot resolve, or find the root DNS or transfer the request to the upper-level , and cycle through it. Regardless of whether the local DNS server uses forwarding or root hints, the result is finally returned to the local DNS server, and the DNS server then returns to the client.

### 2.3 Communication Element 2: Port Number

Network communication is essentially communication between two processes (applications). Each computer has many processes, so how to distinguish these processes during network communication?

If **IP address** can uniquely identify a device in the network, then **port number** can uniquely identify the process (application) in the device.

Different processes, set different port numbers.

- **Port number: an integer represented by two bytes, its value range is 0~65535**.
  - Recognized ports: 0~1023. Occupied by predefined service communication, such as: HTTP (80), FTP (21), Telnet (23)
  - Register port: 1024~49151. Assigned to a user process or application. Such as: Tomcat (8080), MySQL (3306), Oracle (1521).
  - Dynamic/private ports: 49152~65535.

If the port number is occupied by another service or application, it will cause the current program to fail to start.

### 2.4 Communication Element 3: Network Communication Protocol

Multiple computers can be connected through a computer network. Computers in the same network need to abide by certain rules when connecting and communicating, just like a car driving on the road must obey the traffic rules.

- `Network Communication Protocol`: In a computer network, these rules of connection and communication are called network communication protocols, which make uniform regulations on data transmission format, transmission rate, transmission steps, error control, etc. Both parties must communicate at the same time Comply with it to complete the data exchange.

**New problem: The network protocol involves too much content and is too complicated. How to solve? **

Computer network communication involves a lot of content, such as specifying source and destination addresses, encryption and decryption, compression and decompression, error control, flow control, routing control, how to implement such a complex network protocol? `Communication protocol layered thinking`.

When developing a protocol, break down complex components into simpler ones and compound them. The most commonly used compound method is the hierarchical method, that is, `the same layer can communicate, the upper layer can call the next layer, and there is no relationship with the next layer`. Each layer does not affect each other, which is conducive to the development and expansion of the system.

There are two sets of reference models

- OSI Reference Model: The model is too idealized to be widely promoted on the Internet
- TCP/IP Reference Model (or TCP/IP Protocol): The de facto international standard.

In the figure above, the OSI reference model: the model is 'too idealistic' and has not been widely promoted on the Internet. TCP/IP reference model (or TCP/IP protocol): the de facto `international standard`.

- **TCP/IP protocol:** Transmission Control Protocol/Internet Protocol (Transmission Control Protocol/Internet Protocol), TCP/IP is known for its two main protocols: Transmission Control Protocol (TCP) and Internet Protocol (IP) The name is actually a set of protocols, including multiple interrelated protocols with different functions. It is the most basic and extensive protocol of the Internet.


**Introduction to the four layers in the TCP/IP protocol:**

- `Application Layer`: The application layer determines the communication activities when providing application services to users. The main protocols are: HTTP protocol, FTP protocol, SNMP (Simple Network Management Protocol), SMTP (Simple Mail Transfer Protocol) and POP3 (short for Post Office Protocol 3, that is, the third version of the Post Office Protocol), etc.
- `Transport layer`: It mainly enables network programs to communicate. During network communication, TCP protocol or UDP protocol can be used. The TCP (Transmission Control Protocol) protocol, that is, the Transmission Control Protocol, is a connection-oriented, reliable, byte-stream-based transport layer communication protocol. UDP (User Datagram Protocol, User Datagram Protocol): It is a connectionless transport layer protocol that provides transaction-oriented simple and unreliable information transmission services.
- `Network layer`: The network layer is the core of the entire TCP/IP protocol, supporting data communication between networks. It is mainly used to group the transmitted data and send the packet data to the target computer or network. The IP protocol is a very important protocol. IP (Internet Protocol) is also known as Internet Protocol. The responsibility of IP is to transfer data from source to destination. It transmits something called a data packet between the source address and the destination address, and it also provides the function of reassembling the data size to meet the requirements of different networks for the packet size.

* `Physical + data link layer`: The link layer is used to define the physical transmission channel, usually the driver protocol for some network connection devices, such as the driver for optical fiber and network cable.


## 2. Talk about the transport layer protocol: TCP and UDP protocol

The communication protocol is relatively complex, and the `java.net` package contains classes and interfaces that provide low-level communication details. We can use these classes and interfaces directly to focus on network program development without considering the details of communication.

The `java.net` package provides support for two common network protocols:

- **UDP**: User Datagram Protocol (User Datagram Protocol).
- **TCP**: Transmission Control Protocol.

### 2.1 TCP protocol and UDP protocol

**TCP protocol:**

- Two application processes for TCP protocol communication: client and server.
- Before using the TCP protocol, you must `establish a TCP connection` to form a data transmission channel based on the byte stream
- Before transmission, use the "three-way handshake" method, point-to-point communication, which is `reliable`
  - The TCP protocol uses the `retransmission mechanism`. When a communication entity sends a message to another communication entity, it needs to receive confirmation from another communication entity. If no confirmation from another communication entity is received, it will repeat just now sent message.
- It is possible to perform `big data transfer` during the connection
- After the transmission is completed, it is necessary to `release the established connection, which is inefficient`

**UDP protocol:**

- Two application processes for UDP protocol communication: the sending end and the receiving end.
- Encapsulate data, source, and destination into data packets (the basic unit of transmission), `no need to establish a connection`
- Regardless of whether the sender is ready or not, the receiver will not confirm receipt, and the integrity of the data cannot be guaranteed, so it is `unreliable`
- The size of each datagram is limited to `64K`
- No need to release resources at the end of sending data, low overhead, high communication efficiency`
- Applicable scenarios: transmission of audio, video and common data. e.g. video conferencing

>TCP Life Example: Calling
>
> UDP life case: sending text messages, sending telegrams

### 2.2 Three-way handshake

In the TCP protocol, in the preparation stage of sending data, there are three interactions between the client and the server to ensure the reliability of the connection.

- The first handshake, the client initiates a TCP connection request to the server
- The second handshake, the server sends an acknowledgment for the client TCP connection request
- The third handshake, the client sends an acknowledgment of confirmation


> 1. The client will randomly set an initial sequence number seq=x, and set SYN=1, indicating that this is a SYN handshake message. Then you can send this SYN message to the server, indicating that a connection is initiated to the server, and then the client is in the state of `synchronization has been sent`.
>
> 2. After the server receives the SYN message from the client, it also randomly assigns an initial sequence number (seq=y), and sets ack=x+1, indicating that it has received the data before x from the client, and hopes that the client will send it next time The data starts at x+1.
     > Set SYN=1 and ACK=1. Indicates that this is a SYN handshake and ACK confirmation response message. Finally, the message is sent to the client, and the message does not contain application layer data, and then the server is in the state of `synchronized received`.
>
> 3. After the client receives the message from the server, it will also respond to the server with the last response message, and set ACK to 1, indicating that this is a response message
     > ack=y+1 , indicating that the data before y from the server has been received, and it is hoped that the data sent by the server next time will start from y+1.
     > Finally, send the message to the server. This time the message can carry data, and then the client is in the connection established state. After the server receives the response message from the client, it also enters the `connection established` state.

After the three-way handshake is completed and the connection is established, the client and server can start data transmission. Due to this connection-oriented feature, the TCP protocol can guarantee the security of transmitted data, so it is widely used, such as downloading files, browsing web pages, etc.

### 2.3 Wave four times

In the TCP protocol, after sending data, it needs to wave four times when releasing the connection.

* The first wave: the client proposes to the server to end the connection, `let the server do the final preparations`. At this time, the client is in a half-closed state, which means that it no longer sends data to the server, but it can still receive data.
* The second wave: After the server receives the client's request to release the connection, it will `send the last data to the client`. And inform the upper application process to no longer receive data.
* The third wave: After the server sends the data, it will `send a message to release the connection` to the client. Then the client knows that the connection can be officially released after receiving it.
* The fourth waving: After receiving the last release message from the server, the client must `reply a completely disconnected message`. In this way, the server will completely release the connection after receiving it. Here the client, after sending the last message, will wait for 2MSL, because it is possible that the server has not received the last message, and if the server has not received it for a long time, it will send the message to the client again to release the connection. If the client receives it within the waiting time, it will resend the last message and restart the timer. If you do not receive it after waiting for 2MSL, then disconnect completely.


> 1. The client intends to disconnect and sends a FIN message to the server (the FIN flag is set to 1, 1 means FIN, 0 means not), a sequence number will be specified in the FIN message, and then the client enters FIN_WAIT_1 state. That is, the client sends a connection release message segment (FIN message), specifies the sequence number seq = u, actively closes the TCP connection, and waits for the server's confirmation.
>
> 2. After the server receives the connection release message segment (FIN message), it sends an ACK response message to the client, and uses the serial number seq+1 of the client's FIN message segment as the confirmation sequence number of the ACK response message segment ack = seq+1 = u + 1. Then the server enters the CLOSE_WAIT (waiting to close) state, at this time the TCP is in a half-closed state (what will be described below as a half-closed state), and the connection from the client to the server is released. After the client receives the ACK response segment from the server, it enters the FIN_WAIT_2 state.
>
> 3. The server also intends to disconnect, and sends a connection release (FIN) segment to the client, and then the server enters the LASK_ACK (final confirmation) state, waiting for the client's confirmation. The server's connection release (FIN) message segment has FIN=1, ACK=1, sequence number seq=m, and confirmation sequence number ack=u+1.
>
> 4. After the client receives the connection release (FIN) message segment from the server, it will send an ACK response message segment to the server, and use the confirmation number ack of the connection release (FIN) message segment as the ACK response message segment The sequence number seq of the connection release (FIN) message segment is used as the sequence number seq+1 to confirm the sequence number ack.
>
> After that, the client enters the TIME_WAIT (time waiting) state, and after the server receives the ACK response segment, the server enters the CLOSE (closed) state, and the connection to this server has been closed. When the client is in the TIME_WAIT state, the TCP has not been released at this time, and it needs to wait for 2MSL before the client enters the CLOSE state.
>

## 3. Network programming API

### 3.1 InetAddress class

The InetAddress class mainly represents the IP address, and has two subclasses: Inet4Address and Inet6Address.

The InetAddress class does not provide a public constructor, but provides the following static methods to obtain InetAddress instances

* public static InetAddress getLocalHost()
* public static InetAddress getByName(String host)
* public static InetAddress getByAddress(byte[] addr)

InetAddress provides the following commonly used methods

* public String getHostAddress() : returns the IP address string (in text form)
* public String getHostName() : Get the hostname of this IP address
* public boolean isReachable(int timeout): test whether the address can be reached


### 3.2 Socket class

- The uniquely identified IP address and port number on the network are combined to form a uniquely identifiable identifier socket (Socket).
- The use of sockets (Socket) to develop network applications has long been widely used, so that it has become a de facto standard. Network communication is actually communication between Sockets.


- There must be a Socket at both ends of the communication, which is the endpoint of the communication between the two machines.

- Socket allows the program to treat the network connection as a stream, and the data is transmitted between two Sockets through IO.

- Generally, the application that initiates communication is the client, and the application that waits for the communication request is the server.

- Socket classification:
  - Stream socket: use TCP to provide reliable byte stream service
    - ServerSocket: This class implements a TCP server socket. The server socket waits for requests to come in over the network.
    - Socket: This class implements client sockets (also called "sockets"). A socket is an endpoint for communication between two machines.
  - Datagram sockets: use UDP to provide "best effort" datagram services
    - DatagramSocket: This class represents a socket used to send and receive UDP datagram packets.

### 3.3 Socket-related APIs

#### 3.3.1 ServerSocket class

**Constructor of the ServerSocket class:**

* ServerSocket(int port): Create a server socket bound to a specific port.

**Common methods of the ServerSocket class:**

* Socket accept(): Listen for and accept connections to this socket.

#### 3.3.2 Socket class

**Common construction methods of the Socket class**:

* public Socket(InetAddress address,int port): Creates a stream socket and connects it to the specified port number at the specified IP address.
* public Socket(String host,int port): Creates a stream socket and connects it to the specified port number on the specified host.

**Common methods of the Socket class**:

* public InputStream getInputStream(): Returns the input stream of this socket, which can be used to receive messages
* public OutputStream getOutputStream(): Returns the output stream of this socket, which can be used to send messages
* public InetAddress getInetAddress(): The remote IP address to which this socket is connected, or null if the socket is not connected.
* public InetAddress getLocalAddress(): Get the local address bound to the socket.
* public int getPort(): The remote port number this socket is connected to, or 0 if the socket is not connected yet.
* public int getLocalPort(): Returns the local port this socket is bound to. Returns -1 if the socket has not been bound.
* public void close(): Close this socket. Once a socket is closed, it cannot be used for further network connections (i.e. it cannot be reconnected or rebinded). A new socket object needs to be created. Closing this socket will also close the socket's InputStream and OutputStream.
* public void shutdownInput(): If something is read from the socket input stream after calling shutdownInput() on the socket, the stream will return EOF (End of File). That is, no data can be received on the input stream from this socket.
* public void shutdownOutput(): Disables the output stream for this socket. For TCP sockets, any previously written data will be sent, followed by TCP's normal connection termination sequence. The socket output stream will throw an IOException if writing to the socket output stream after shutdownOutput() has been called on the socket. That is, no data can be sent over this socket's output stream.

**Note:** Calling the shutdownInput() and shutdownOutput() methods of Socket successively only closes the input stream and output stream, which is not equal to calling the close() method of Socket. After the communication is over, the close() method of Socket still needs to be called, because only this method will release the resources occupied by Socket, such as the occupied local port number, etc.

#### 3.3.3 DatagramSocket class

**Common methods of the DatagramSocket class:**

* public DatagramSocket(int port) creates a datagram socket and binds it to the specified port on the local host. The socket will be bound to a wildcard address, and the IP address is chosen by the kernel.
* public DatagramSocket(int port,InetAddress laddr) creates a datagram socket and binds it to the specified local address. The local port must be between 0 and 65535 (both inclusive). If the IP address is 0.0.0.0, the socket will be bound to a wildcard address, the IP address being chosen by the kernel.
* public void close() closes this datagram socket.
* public void send(DatagramPacket p) sends a datagram packet from this socket. A DatagramPacket contains information indicating: the data to be sent, its length, the IP address of the remote host, and the port number of the remote host.
* public void receive(DatagramPacket p) Receives a datagram packet from this socket. When this method returns, the DatagramPacket's buffer is filled with the received data. The datagram packet also contains the sender's IP address and the port number on the sender's machine. This method blocks until a datagram is received. The length field of the datagram packet object contains the length of the received message. If the message is longer than the packet length, the message will be truncated.
* public InetAddress getLocalAddress() Gets the local address bound to the socket.
* public int getLocalPort() Returns the port number on the local host to which this socket is bound.
* public InetAddress getInetAddress() returns the address of this socket connection. Returns null if the socket is not connected.
* public int getPort() returns the port of this socket. Returns -1 if the socket is not connected.

#### 3.3.4 DatagramPacket class

**Common methods of the DatagramPacket class:**

* public DatagramPacket(byte[] buf,int length) constructs DatagramPacket, which is used to receive data packets with length length. The length parameter must be less than or equal to buf.length.
* public DatagramPacket(byte[] buf,int length,InetAddress address,int port) constructs a datagram packet, which is used to send the packet with length length to the specified port number on the specified host. The length parameter must be less than or equal to buf.length.
* public InetAddress getAddress() returns the IP address of the machine to which this datagram is to be sent or received.
* public int getPort() returns the port number of a remote host to which this datagram is to be sent or received.
* `public byte[] getData()` returns the data buffer. The data received or to be sent starts at offset offset in the buffer and continues for length length.
* `public int getLength()` returns the length of the data to be sent or received.

## 4. TCP network programming

### 4.1 Communication Model

The socket-based TCP programming of the Java language is divided into server-side programming and client-side programming, and its communication model is shown in the figure:


### 4.2 Development steps

**The client program consists of the following four basic steps:**

* Create Socket: Construct a Socket class object according to the IP address or port number of the specified server. If the server responds, a communication line from the client to the server is established. If the connection fails, an exception will occur.
* Open the input/output stream connected to the Socket: use the getInputStream() method to obtain the input stream, use the getOutputStream() method to obtain the output stream, and perform data transmission
* Read/write operations on Socket according to a certain protocol: read the information put into the line by the server through the input stream (but cannot read the information put into the line by itself), and write the information into the line through the output stream.
* Close Socket: disconnect the client to the server, release the line

**The server-side program consists of the following four basic steps:**

* Call ServerSocket(int port): Create a server-side socket and bind it to the specified port. Used to monitor client requests.
* Call accept(): Listen for connection requests, if the client requests a connection, accept the connection and return the communication socket object.
* Call getOutputStream() and getInputStream () of the Socket class object: get the output stream and input stream, and start sending and receiving network data.
* Close the Socket object: the client access ends and the communication socket is closed.

### 4.3 Examples and Exercises

> Example 1: The client sends content to the server, and the server prints the content to the console.
>
> Example 2: The client sends a file to the server, and the server saves the file locally.
>
> Example 3: Send a file from the client to the server, and the server saves it locally. And return "send successfully" to the client. and close the corresponding connection.
>

Exercise 1: The server reads the picture and sends it to the client, and the client saves the picture locally

Exercise 2: The client sends text to the server, and the server converts the text to uppercase and returns it to the client.

**Demonstrates a single client-server communication:**

Requirements: The client connects to the server and sends "lalala" to the service after the connection is successful. After the server receives the message, it returns "Welcome to login" to the client. After receiving the message, the client disconnects



**Demonstrating multiple communications between multiple clients and servers:**

Under normal circumstances, the server should not only accept one client request, but should continuously accept all requests from the client, so Java programs usually call the accept() method of ServerSocket continuously through a loop.

If the server side wants to "simultaneously" process the requests of multiple clients, the server side needs to allocate a separate thread for each client to process, otherwise "simultaneous" cannot be achieved.

When we studied IO streams before, we mentioned the decorator design pattern. This design enables no matter what kind of node stream the underlying IO stream is: whether it is a file stream or a stream generated by a network socket, the program can package it into a processing Streams can even be multi-packed, thus providing more convenient handling.

Case requirements: Multiple clients connect to the server and communicate multiple times

* After each client is successfully connected, input English words or Chinese idioms from the keyboard and send them to the server
* After receiving the message from the client, the server returns the word "inverted" to the client
* The client receives the "word" returned by the server and prints it
* Disconnect from server when client enters "stop"
* Multiple clients can send "words" to the server at the same time, and the server can process requests from multiple clients "simultaneously"

### 4.4 Case: chat room


### 4.5 Understand client and server

- client:
  - customize
  - browser (browser---server)

- Server:
  - customize
  - Tomcat server

## 5. UDP network programming

UDP (User Datagram Protocol, User Datagram Protocol): It is a connectionless transport layer protocol that provides transaction-oriented simple and unreliable information transmission services, similar to SMS.

### 5.1 Communication Model

The UDP protocol is a **connection-oriented** protocol. Non-connection-oriented means that there is no need to establish a connection with the other party before formal communication, and it will be sent directly regardless of the status of the other party. As for whether the other party can receive the data content, UDP The protocol cannot be controlled, so the UDP protocol is an **unreliable** protocol. The advantage of connectionless is that it is fast, saves memory space and traffic, because maintaining connections requires the creation of a large number of data structures. UDP will try its best to deliver data, but it does not guarantee reliable delivery. There is no TCP confirmation mechanism and retransmission mechanism. If it is not transmitted to the peer due to network reasons, UDP will not return an error message to the application layer.

The UDP protocol is an information transfer service for data packets. UDP has no buffer at the sending end, and the packets delivered by the application layer are directly delivered to the IP layer after the header is added. They will not be merged or split, but a complete packet will be delivered at a time. For example, if we want to send a message of 100 bytes, we will send 100 bytes by calling the send() method once, and the receiver also needs to use the receive() method to receive 100 bytes at a time, and we cannot use a loop to get 10 bytes each time Byte, get ten times this way.

The UDP protocol has no congestion control, so when the network is congested, it will not reduce the rate at which the host sends data. Although there is a buffer at the receiving end of UDP, this buffer is only responsible for receiving, and does not guarantee whether the arrival order of UDP packets is consistent with the order of sending. Because during network transmission, due to the existence of network congestion, it is very likely that the first sent message will arrive later than the later sent message. If the buffer is full at this time, the packets arriving later will be discarded directly. This is very important for real-time applications, such as: video calls, live broadcast and other applications.

Therefore, UDP is suitable for application environments that only transmit a small amount of data at a time and do not require high reliability. The datagram size is limited to less than 64K.


Classes DatagramSocket and DatagramPacket implement network programs based on UDP protocol.

The UDP datagram is sent and received through the datagram socket DatagramSocket. The system does not guarantee that the UDP datagram will be delivered to the destination safely, nor can it be sure when it will arrive.

The DatagramPacket object encapsulates the UDP datagram, and the datagram contains the IP address and port number of the sending end and the IP address and port number of the receiving end.

Each datagram in the UDP protocol gives complete address information, so there is no need to establish a connection between the sender and the receiver. It's like sending a courier package.

### 5.2 Development steps

**The sender program consists of the following four basic steps:**

* Create DatagramSocket: By default, the port number is randomly assigned by the system.
* Create DatagramPacket: The data to be sent is represented by a byte array, and the length of the data to be sent, the IP address and port number of the receiver are specified.
* Call the send method of the DatagramSocket class object: send the datagram DatagramPacket object.
* Close the DatagramSocket object: the sender program ends and the communication socket is closed.

**The receiver program consists of the following four basic steps:**

* Create DatagramSocket: Specify the listening port number.
* Create DatagramPacket: Specify the byte array for receiving data, which acts as a temporary data buffer, and specifies the maximum data length that can be received.
* Call the receive method of the DatagramSocket class object: receive the datagram DatagramPacket object. .
* Close DatagramSocket: The receiver program ends and the communication socket is closed.

### 5.3 Demonstrate sending and receiving messages

Network programming based on the UDP protocol still needs to establish a Socket at both ends of the communication instance, but there is no virtual link between the two Sockets. These two Sockets are only objects for sending and receiving datagrams. Java provides the DatagramSocket object As a Socket based on the UDP protocol, use DatagramPacket to represent the datagrams sent and received by DatagramSocket.

#### Example 1:

sender:

```java
DatagramSocket ds = null;
try {
     ds = new DatagramSocket();
     byte[] by = "hello,atguigu.com".getBytes();
     DatagramPacket dp = new DatagramPacket(by, 0, by.length, InetAddress.getByName("127.0.0.1"), 10000);
     ds. send(dp);
} catch (Exception e) {
     e.printStackTrace();
} finally {
     if (ds != null)
         ds. close();
}

```

Receiving:

```java
DatagramSocket ds = null;
try {
     ds = new DatagramSocket(10000);
     byte[] by = new byte[1024*64];
     DatagramPacket dp = new DatagramPacket(by, by.length);
     ds. receive(dp);
     String str = new String(dp. getData(), 0, dp. getLength());
     System.out.println(str + "--" + dp.getAddress());
} catch (Exception e) {
     e.printStackTrace();
} finally {
     if (ds != null)
         ds. close();
}

```

#### Example 2:

**Sender:**

```java
package com.atguigu.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Send {

     public static void main(String[] args) throws Exception {
// 1. Create a DatagramSocket at the sending end
         DatagramSocket ds = new DatagramSocket();

         // data to send
         ArrayList<String> all = new ArrayList<String>();
         all.add("Silicon Valley makes the world have no difficult technology to learn!");
         all.add("Learn high-end and cutting-edge IT technology to come to Silicon Valley!");
         all.add("Silicon Valley makes your dream more concrete!");
         all.add("Silicon Valley makes your efforts more valuable!");

         // Receiver's IP address
         InetAddress ip = InetAddress.getByName("127.0.0.1");
         // Receiver's listening port number
         int port = 9999;
         //send multiple datagrams
         for (int i = 0; i < all. size(); i++) {
// 2. Create a data packet DatagramPacket
             byte[] data = all. get(i). getBytes();
             DatagramPacket dp = new DatagramPacket(data, 0, data.length, ip, port);
// 3. Call the sending method of Socket
             ds. send(dp);
         }

// 4. Close the Socket
         ds. close();
     }
}
```

**Receiving :**

```java
package com.atguigu.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receive {

     public static void main(String[] args) throws Exception {
// 1. To establish a DatagramSocket at the receiving end, you need to specify the listening port number of the local end
         DatagramSocket ds = new DatagramSocket(9999);

         //Always monitor the data
         while(true){
             //2, create a data packet DatagramPacket
             byte[] buffer = new byte[1024*64];
             DatagramPacket dp = new DatagramPacket(buffer,buffer.length);

             //3. Call the receiving method of Socket
             ds. receive(dp);

             //4, Unpack the data
             String str = new String(dp.getData(),0,dp.getLength());
             System.out.println(str);
         }

// ds. close();
     }
}
```

## 6. URL Programming

### 6.1 URL class

- URL (Uniform Resource Locator): Uniform Resource Locator, which represents the address of a resource on the Internet.

- We can access various network resources on the Internet through URL, such as the most common www, ftp sites. By parsing a given URL, the browser can find corresponding files or other resources on the network.

- The basic structure of a URL consists of 5 parts:

```http
<transport protocol>://<host name>:<port number>/<file name>#fragment name? parameter list
```

- For example: http://192.168.1.100:8080/helloworld/index.jsp#a?username=shkstart&password=123
  - Fragment name: the anchor point, such as reading a novel, directly locate the chapter
  - Parameter list format: parameter name=parameter value&parameter name=parameter value....

- To represent URLs, class URL is implemented in java.net. We can initialize a URL object with the following constructor:

  - public URL (String spec): A URL object can be constructed through a string representing the URL address. For example:

    ```http
    URL url = new URL("http://www.atguigu.com/");
    ```

  - public URL(URL context, String spec): Constructs a URL object from a base URL and a relative URL. For example:

    ```http
    URL downloadUrl = new URL(url, “download.html”)
    ```

    public URL(String protocol, String host, String file); For example:

    ```http
    URL url = new URL("http", "www.atguigu.com", "download.html");
    ```

  - public URL(String protocol, String host, int port, String file); For example:

    ```java
    URL gamelan = new URL("http", "www.atguigu.com", 80, “download.html”);
    ```

- The constructors of the URL class all declare that a non-runtime exception is thrown, and this exception must be handled, usually using a try-catch statement to catch it.


### 6.2 Common methods of URL class

After a URL object is generated, its properties cannot be changed, but these properties can be obtained through its given methods:

- public String getProtocol( ) Get the protocol name of the URL

- public String getHost( ) Get the host name of the URL

- public String getPort( ) Get the port number of the URL

- public String getPath( ) Get the file path of the URL

- public String getFile( ) Get the file name of the URL

- public String getQuery( ) Get the query name of the URL

```java
URL url = new URL("http://localhost:8080/examples/myTest.txt");
System.out.println("getProtocol() :"+url.getProtocol());
System.out.println("getHost() :"+url.getHost());
System.out.println("getPort() :"+url.getPort());
System.out.println("getPath() :"+url.getPath());
System.out.println("getFile() :"+url.getFile());
System.out.println("getQuery() :"+url.getQuery());
```

### 6.3 URLConnection class for HTTP protocol

- URL method openStream(): can read data from the network
- If you want to output data, such as sending some data to the server-side CGI (the abbreviation of Common Gateway Interface-Common Gateway Interface, which is the interface between the user browser and the server-side application program), you must first establish a connection with the URL, Then you can read and write it, you need to use URLConnection at this time.
- URLConnection: Represents a connection to the remote object referenced by the URL. When establishing a connection with a URL, first generate a corresponding URLConnection object through the method openConnection() on a URL object. If the connection process fails, an IOException will be raised.
  - URL netchinaren = new URL ("http://www.atguigu.com/index.shtml");
  - URLConnectonn u = netchinaren.openConnection( );

- The input stream and output stream obtained through the URLConnection object can interact with existing CGI programs.
  - public Object getContent( ) throws IOException

  - public int getContentLength( )
  - public String getContentType( )
  - public long getDate( )
  - public long getLastModified( )
  - **public InputStream getInputStream ( ) throws IOException**
  - public OutputSteram getOutputStream( )throws IOException

### 6.4 Summary

- Computers located in the network have unique IP addresses so that different hosts can be distinguished from each other.

- Client-server is one of the most common network application models. A server is a piece of hardware or software that provides a specific service to its clients. A client is a user application program used to access services provided by a server. The port number is where a service is accessed and it is used to distinguish multiple services on the same physical computer. Sockets are used to connect clients and servers, and each communication session between client and server uses a different socket. The TCP protocol is used to implement connection-oriented sessions.

- The functions related to networking in Java are defined in the java.net package. Java represents an IP address with an InetAddress object, which has two fields: the host name (String) and the IP address (int).

- Classes Socket and ServerSocket realize the client-server program based on TCP protocol. Socket is a connection between client and server, and the details of connection creation are hidden. This connection provides a secure data transmission channel, because the TCP protocol can solve the problems of data loss, damage, repetition, disorder and network congestion during transmission, and it ensures reliable data transmission.

- The classes URL and URLConnection provide the most advanced networking applications. The location of network resources in URLs can be used to uniformly represent various network resources on the Internet. Through the URL object, the connection between the current application program and the network resource represented by the URL can be created, so that the current program can read the data of the network resource, or transmit its own data to the network.







