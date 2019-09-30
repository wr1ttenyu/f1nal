package wr1ttenyu.f1nal.study.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask(new CallableDemo());
        new Thread(futureTask).start();
        String result = null;
        try {
            result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("醒了一起啪啪啪吧");
    }
}

class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("i am going to sleep -----");
        Thread.sleep(5000);
        return "我睡醒了  = - =";
    }
}