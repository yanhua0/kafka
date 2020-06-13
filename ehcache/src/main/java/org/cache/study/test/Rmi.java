package org.cache.study.test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Rmi {
    public static void main(String[] args) throws Exception {
        while (true){
            int port = 8888;
            Thread.sleep(2000);
            String url = "rmi://localhost:8888/demo.zookeeper.rmi.server.HelloServiceImpl";
            LocateRegistry.createRegistry(port);
            Naming.rebind(url, new HelloServiceImpl());
        }

    }

}
