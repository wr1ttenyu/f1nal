package wr1ttenyu.f1nal.study.juc;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestScheduledExecutorService {

    public static void main(String[] args) {
        /**
         *  @param corePoolSize the number of threads to keep in the pool, even
         *         if they are idle, unless {@code allowCoreThreadTimeOut} is set
         *  @param threadFactory the factory to use when the executor
         *         creates a new thread
         *  @param handler the handler to use when execution is blocked
         *         because the thread bounds and queue capacities are reached
         */
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
            new MyThreadFactory(), new MyRejectedExecutionHandler());
        // TODO 这里虽然有 MyRejectedExecutionHandler 但是底层好像没有控制队列的长度 看看能不能改 顺便看看 ScheduledThreadPoolExecutor 底层实现
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("定时任务每隔两秒执行喽-----");
        }, 0,2, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
    }
}
