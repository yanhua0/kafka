package server;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.layered.TFramedTransport;
import service.PersonServiceImpl;
import thrift.generated.PersonService;

public class ThriftServer {
    public static void main(String[] args) throws Exception {

        // 定义服务器使用的socket类型
        TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(8899);

        // 创建服务器参数
        THsHaServer.Args arg = new THsHaServer.Args(tNonblockingServerSocket).minWorkerThreads(2).maxWorkerThreads(4);

        // 请求处理器
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        // 配置传输数据的格式
        arg.protocolFactory(new TCompactProtocol.Factory());
        // 配置数据传输的方式
        arg.transportFactory(new TFramedTransport.Factory());
        // 配置处理器用来处理rpc请求
        arg.processorFactory(new TProcessorFactory(processor));

        // 本示例中使用半同步半异步方式的服务器模型
        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        // 启动服务
        server.serve();
    }
}
