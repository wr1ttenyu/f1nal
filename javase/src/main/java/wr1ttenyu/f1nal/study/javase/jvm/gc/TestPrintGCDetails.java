package wr1ttenyu.f1nal.study.javase.jvm.gc;

public class TestPrintGCDetails {

    /**
     * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+UseSerialGC
     *
     * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+UseParNewGC
     *
     * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+UseParallelGC
     *
     * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] byteArray = new byte[50 * 1024 * 1024];
    }
}
