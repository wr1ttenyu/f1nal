package wr1ttenyu.f1nal.study.juc;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

    // 发现自己写的 ForkJoinTask 还没有普通for循环快
    @Test
    public void testRecursiveTask() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 100000000000L);

        long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start ,end).toMillis());  //毫秒 146 6394 56984
    }

    @Test
    public void testSimpleSum() {
        Instant start = Instant.now();

        long sum = 0;
        for (long i = 1; i <= 100000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start ,end).toMillis()); //毫秒 52 4991 40244
    }

    @Test
    public void testStreamSum() {
        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0L, 100000000000L).parallel().sum();
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start ,end).toMillis()); //毫秒 54 4939 32005
    }
}
