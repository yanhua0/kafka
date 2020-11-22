package com.test.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * TODO description
 *
 * @author zoujulin [zou.julin@unisinsight.com]
 * @date 2020/10/14 18:45
 * @since 1.0
 */
public class NioClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("client receive msg:"+o.toString());
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
}
