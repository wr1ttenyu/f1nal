package wr1ttenyu.f1nal.study.juc;

public class XiaomaLesson {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        new Thread(myTask).start();

        System.out.printf("[Thread : %s]Starting.....\n", Thread.currentThread().getName());

        System.out.printf("MyTask is complete : %s\n", myTask.isComplete());
    }

    static class MyTask implements Runnable {
        private boolean complete = false;

        @Override
        public void run() {
            System.out.printf("[Thread : %s]Starting.....\n", Thread.currentThread().getName());
            complete = true;
            System.out.printf("[Thread : %s]End.....\n", Thread.currentThread().getName());
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }
    }
}

