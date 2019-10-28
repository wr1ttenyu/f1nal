package wr1ttenyu.f1nal.study.juc;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool {

    public static void main(String[] args) {
        /**
         *  corePoolSize 核心线程数 不因闲置而销毁
         *  maximumPoolSize 最大线程数
         *  keepAliveTime 最大空闲时间
         *  TimeUnit unit 最大空闲时间单位
         *  BlockingQueue<Runnable> workQueue 任务队列
         *  ThreadFactory 线程生成工厂
         *  RejectedExecutionHandler handler 超溢任务处理
         */
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), new MyThreadFactory(), new MyRejectedExecutionHandler());
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyTask("任务" + i));
        }

        executorService.shutdown();
    }
}

class MyTask implements Runnable {

    private String taskName;

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---> 工作任务名称:" + taskName + ",被执行---");
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}

class MyTaskWithReturn implements Callable<String> {

    private String taskName;

    public MyTaskWithReturn(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("工作任务名称:" + taskName + ",被执行---");
        return "工作任务名称:" + taskName + ",被执行---";
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}

class MyThreadFactory implements ThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "我的线程" + threadNum.getAndIncrement() + "号");
    }
}

class MyRejectedExecutionHandler implements RejectedExecutionHandler {
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
            MyTask myTask = (MyTask)objTask;
            // 通过上面一系列的转化  我们最终可以在 MyRejectedExecutionHandler 中获取我们自己 runnable 实现
            // 之后我们可以将 自己runnable实现 中的参数 例如 保存在redis中 后续任务量下降后 再次执行
            System.out.println("工作任务:" + myTask.getTaskName() + ",被拒绝---");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}