//package org.webSocket.service.server;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.*;
//import java.util.function.Consumer;
//
//
//
//@Component
//@Slf4j
//public class WebSocketServer {
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;
//    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    public static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
//    public static Map<String, Session> stringSessionMap = new ConcurrentHashMap<>();
//    public static Map<Session, BlockingQueue<Consumer<String>>> map = new ConcurrentHashMap<>();
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//
//    /**
//     * 连接建立成功调用的方法
//     */
//    @OnOpen
//    public void onOpen(Session session) {
//        this.session = session;
//        webSocketSet.add(this);
//        //加入set中
//        addOnlineCount();           //在线数加1
//        stringSessionMap.put(session.getId(), session);
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
//        try {
//            sendMessage("连接成功...");
//        } catch (Exception e) {
//            System.out.println("websocket IO异常");
//        }
//
//
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose() {
//        webSocketSet.remove(this);  //从set中删除
//        subOnlineCount();           //在线数减1
//        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
//
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("来自客户端的消息:" + message);
//        //群发消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        System.out.println("发生错误");
//        error.printStackTrace();
//    }
//
//
//    public void sendMessage(String message) {
//        ExecutorService executorService= Executors.newFixedThreadPool(10);
//        executorService.execute(()->{
//           sendM(message);
//        });
//        executorService.execute(()->{
//           sendM(message);
//        });
//    }
//
//  private void sendM(String message){
//      Long start = System.currentTimeMillis();
//    Session s=  stringSessionMap.get("123");
//      Session s3 =stringSessionMap.get("123");
//      Session s2 =stringSessionMap.get("123");
//      Session s1 =stringSessionMap.get("123");
//    stringSessionMap.forEach((key, value) -> {
//          try {
//               synchronized (value) {
//                   value.getBasicRemote().sendText(message);
//               }
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//      });
//      Long end = System.currentTimeMillis();
//      log.info("发送花费时间{}ms", end - start);
//  }
//    /**
//     * 群发自定义消息
//     */
//    public static void sendInfo(String message) throws IOException {
//        System.out.println(message);
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (Exception e) {
//                continue;
//            }
//        }
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnlineCount() {
//        WebSocketServer.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocketServer.onlineCount--;
//    }
//}
