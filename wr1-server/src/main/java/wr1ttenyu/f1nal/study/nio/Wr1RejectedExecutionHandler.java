package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
class Wr1RejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        FutureTask futureTask = (FutureTask) r;
        try {
            // callable 实际是一个 java.util.concurrent.Executors.RunnableAdapter
            Field callable = futureTask.getClass().getDeclaredField("callable");
            // 设置 callable 可访问
            callable.setAccessible(true);
            // task 是 java.util.concurrent.Executors.RunnableAdapter 中实际对应的 runnable
            Field task = callable.get(futureTask).getClass().getDeclaredField("task");
            // 设置 task 可访问
            task.setAccessible(true);
            // 获取 objTask 实例
            Object objTask = task.get(callable.get(futureTask));
            Wr1ServerTask wr1ServerTask = (Wr1ServerTask)objTask;
            // 通过上面一系列的转化  我们最终可以在 Wr1RejectedExecutionHandler 中获取我们自己 runnable 实现
            // 之后我们可以将 自己runnable实现 中的参数 例如 保存在redis中 后续任务量下降后 再次执行
            SelectionKey sk = wr1ServerTask.getSk();
            SocketChannel sChannel = (SocketChannel) sk.channel();
            InetSocketAddress remoteAddress = null;
            remoteAddress = (InetSocketAddress) sChannel.getRemoteAddress();
            String hostAddress = remoteAddress.getAddress().getHostAddress();
            int port = remoteAddress.getPort();
            if (sk.isAcceptable()) {
                log.info("give up from {}:{} accept event", hostAddress, port);
            } else if (sk.isReadable()) {
                log.info("give up from {}:{} read event", hostAddress, port);
            } else if(sk.isWritable()) {
                log.info("give up to {}:{} write event", hostAddress, port);
            }
        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            log.error("have a exception msg:{} stack-trace:{}", e.getMessage(), e.getStackTrace());
        }
    }
}