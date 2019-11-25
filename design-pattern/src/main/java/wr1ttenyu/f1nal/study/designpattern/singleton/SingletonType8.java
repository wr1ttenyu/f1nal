package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 单例模式 枚举 总结：
 * 1. 避免多线程同步问题
 * 2. 还能防止反序列花重新创建新的对象
 * 3. 是 Effective Java作者 josh Bloch提倡的方式
 * 4. 推荐使用
 */
class SingletonType8 {

    public static void main(String[] args) {
        // 测试
        Singleton8 instance1 = Singleton8.INSTANCE;
        Singleton8 instance2 = Singleton8.INSTANCE;
        if (instance1 == instance2) System.out.println("单例模式 枚举");
        instance1.sayOK();

    }
}

enum Singleton8 {
    INSTANCE;

    public void sayOK() {
        System.out.println("ok");
    }
}

