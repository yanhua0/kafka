package com.test.netty;

import com.test.netty.handler.NioWebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
@Slf4j
public class Server {
    public static void main(String args[]) {
        log.info("正在启动服务器");
        int processors = Runtime.getRuntime().availableProcessors();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup(processors);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {


                    ch.pipeline().addLast("logging", new LoggingHandler("DEBUG"));// 设置log监听器，并且日志级别为debug，方便观察运行流程
                    ch.pipeline().addLast("http-codec", new HttpServerCodec());// 设置解码器
                    ch.pipeline().addLast("aggregator", new HttpObjectAggregator(1024 * 1024));// 聚合器，使用websocket会用到
                    ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());// 用于大数据的分区传输
                    // 空闲10分钟之后触发

                    ch.pipeline().addLast(new IdleStateHandler(10, 10, 10, TimeUnit.MINUTES));
                    ch.pipeline().addLast("handler", new NioWebSocketHandler());// 自定义的业务handler
                }

            });
            Channel channel = bootstrap.bind("0.0.0.0",11101).sync().channel();
            log.info("服务器启动成功：" + channel);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("运行出错：" + e);
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
            log.info("服务器已关闭");
        }
    }
}
