package org.test.webSocket.service.server2;//package webSocket.service.server2;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.webSocket.Message;
//
//import javax.websocket.OnOpen;
//import javax.websocket.server.PathParam;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.*;
//
////@Component
////访问服务端的url地址
////@ServerEndpoint(value = "/websocket/{id}")
//public class WebSocketServer {
//
//    /**
//     * 连接建立成功调用的方法*/
//    @OnOpen
//    public void onOpen(@PathParam(value = "id") String id, Session session) {
//        this.session = session;
//        this.id = id;//接收到发送消息的人员编号
//        webSocketSet.put(id, this);     //加入set中
//        addOnlineCount();           //在线数加1
//        log.info("用户"+id+"加入！当前在线人数为" + getOnlineCount());
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("websocket IO异常");
//        }
//      //  Function<String,Void> s=this::ss;
//        synchronized (session.getAsyncRemote()) {
//
//        }
//
//
//
//    }
//public static void main(String[] args){
//
//   ExecutorService executorService=Executors.newFixedThreadPool(3);
//
//    final String[] ss = {"123","1234"};
//       for(String s:ss){
//
//           executorService.execute(()->{
//
//               synchronized (s){
//                   try {
//                       System.out.println(s);
//                       Thread.sleep(Long.parseLong("2000"));
//                   } catch (Exception e) {
//                       e.printStackTrace();
//                   }
//               }
//
//
//           });
//
//
//       }
//
//
//
//
//
//}
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose() {
//        webSocketSet.remove(this);  //从set中删除
//        subOnlineCount();           //在线数减1
//        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息*/
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        log.info("来自客户端的消息:" + message);
//        //可以自己约定字符串内容，比如 内容|0 表示信息群发，内容|X 表示信息发给id为X的用户
//        String sendMessage = message.split("[|]")[0];
//        String sendUserId = message.split("[|]")[1];
//        try {
//            if(sendUserId.equals("0"))
//                sendtoAll(sendMessage);
//            else
//                sendtoUser(sendMessage,sendUserId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     *
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
//        error.printStackTrace();
//    }
//
//
//    public void sendMessage(String message) throws IOException {
//        this.session.getBasicRemote().sendText(message);
//    }
//
//    /**
//     * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
//     * @param message
//     * @param sendUserId
//     * @throws IOException
//     */
//    public void sendtoUser(String message,String sendUserId) throws IOException {
//        if (webSocketSet.get(sendUserId) != null) {
//            if(!id.equals(sendUserId))
//                webSocketSet.get(sendUserId).sendMessage( "用户" + id + "发来消息：" + " <br/> " + message);
//            else
//                webSocketSet.get(sendUserId).sendMessage(message);
//        } else {
//            //如果用户不在线则返回不在线信息给自己
//            sendtoUser("当前用户不在线",id);
//        }
//    }
//
//    /**
//     * 发送信息给所有人
//     * @param message
//     * @throws IOException
//     */
//    public void sendtoAll(String message) {
//        for (String key : webSocketSet.keySet()) {
//            try {
//                webSocketSet.get(key).sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
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
//
//    public void addQueue(String content,String id) throws InterruptedException {
//        Map<String,Session>  sessionMap=user.get(id);
//        sessionMap.forEach((x,y)->{
//            try {
//            //    map.get(y).put(content);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//
//    }
//    public void sendMessage(Message message){
//map.put(message.getSession().getId(),session.getAsyncRemote().sendText(message.getMessage()));
//
//
//    }
//    public void broast(String content,String id){
//
//    }
//    private static int onlineCount = 0;
//    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();
//
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//    private static Logger log = LogManager.getLogger(WebSocketServer.class);
//    private String id = "";
//    public static Map<String, Map<String,Session>> user=new ConcurrentHashMap<>();
//    public static Map<String, Session> stringSessionMap=new ConcurrentHashMap<>();
//  //  public static Map<String, Consumer<Message>> con=new ConcurrentHashMap<>();
//    public static Map<String,Future<Void>> map=new ConcurrentHashMap<>();
//    public static BlockingQueue<Message> blockingQueue=new LinkedBlockingQueue<>(2000);
//
//   public void consumer() throws InterruptedException {
//
//       ExecutorService executorService=Executors.newFixedThreadPool(stringSessionMap.size());
//user.remove("213");
//                map.forEach((key,value)->{
//                    executorService.execute(()->{
//
//                    });
//
//                });
//
//
//
//   }
//
//}
