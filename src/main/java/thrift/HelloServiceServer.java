package thrift;
import org.apache.thrift.TProcessor; 
 import org.apache.thrift.protocol.TBinaryProtocol; 
 import org.apache.thrift.protocol.TBinaryProtocol.Factory; 
 import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer; 
 import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException; 

 public class HelloServiceServer { 
    /** 
     * 启动 Thrift 服务器
     * @param args 
     */ 
    public static void main(String[] args) { 
        try { 
        	TProcessor processor = new Hello.Processor(new HelloServiceImpl()); 
            TServerTransport serverTransport = new TServerSocket(7911);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            
/*            // 设置服务端口为 7911 
            TServerSocket serverTransport = new TServerSocket(7911); 
            // 设置协议工厂为 TBinaryProtocol.Factory 
            Factory proFactory = new TBinaryProtocol.Factory(); 
            // 关联处理器与 Hello 服务的实现
            TProcessor processor = new Hello.Processor(new HelloServiceImpl()); 
            TServer server = new TThreadPoolServer(processor, serverTransport,proFactory); */
            System.out.println("Start server on port 7911..."); 
            server.serve(); 
        } catch (TTransportException e) { 
            e.printStackTrace(); 
        } 
    } 
 }