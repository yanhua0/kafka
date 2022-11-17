package com.test.netty;

import com.test.netty.handler.NioClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        //1.定义服务类
        Bootstrap clientBootstap = new Bootstrap();

        //2.定义执行线程组
        EventLoopGroup worker = new NioEventLoopGroup();

        //3.设置线程池
        clientBootstap.group(worker);

        //4.设置通道
        clientBootstap.channel(NioSocketChannel.class);

        //5.添加Handler
        clientBootstap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                System.out.println("client channel init!");
                ChannelPipeline pipeline = channel.pipeline();
                // pipeline.addLast("StringDecoder",new StringDecoder());
                pipeline.addLast("StringEncoder",new StringEncoder());
                pipeline.addLast("ClientHandler",new NioClientHandler());
            }
        });

        //6.建立连接
        ChannelFuture channelFuture = clientBootstap.connect("0.0.0.0",11101);
        try {
            //7.测试输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.println("请输入：");
                String msg = bufferedReader.readLine();
//                Person person=new Person(IdleState.READER_IDLE,true);

                channelFuture.channel().writeAndFlush(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //8.关闭连接
            worker.shutdownGracefully();
        }
    }
}
