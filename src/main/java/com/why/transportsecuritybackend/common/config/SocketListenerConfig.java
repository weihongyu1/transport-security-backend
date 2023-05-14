package com.why.transportsecuritybackend.common.config;

import com.why.transportsecuritybackend.tcp.ServerTcp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * socket监听配置
 *
 * @author why
 * @date 2023/04/29 21:14
 **/
@Slf4j
@Component
public class SocketListenerConfig implements CommandLineRunner {

    private final SocketConfig socketConfig;

    @Autowired
    public SocketListenerConfig(SocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        ServerSocket server = null;
        Socket socket = null;
        server = new ServerSocket(socketConfig.getPort());
        log.info("设备服务器已启动，监听端口："+ socketConfig.getPort());
        //创建线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                socketConfig.getPoolCore(),
                socketConfig.getPoolMax(),
                socketConfig.getPoolKeep(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(socketConfig.getPoolQueueInit()),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        //循环监听
        while (true){
            socket = server.accept();
            pool.execute(new ServerTcp(socket));
        }
    }
}
