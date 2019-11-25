package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 单例设计模式的八种方式
 * 1. 饿汉式（静态常量） {@link Singleton1}
 * 2. 饿汉式（静态代码块） {@link Singleton2}
 * 3. 懒汉式（线程不安全） {@link Singleton3}
 * 4. 懒汉式（线程安全，同步方法） {@link Singleton4}
 * 5. 懒汉式（线程不安全，同步代码块） {@link Singleton5}
 * 6. 双重检查 {@link Singleton6}
 * 7. 静态内部类 {@link Singleton7}
 * 8. 枚举 {@link Singleton8}
 *
 * 推荐使用顺序 枚举 > 静态内部类 > 双重检查 > 饿汉式
 *
 * JDK源码中 单例模式的使用 {@link Runtime} 饿汉式（静态常量）
 *
 * 单例模式注意事项和细节说明：
 * 1. 单例模式保证了 系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建和销毁的对象，
 *      使用单例模式可以提高系统性能
 * 2. 当想获取实例的时候不是new对象，而是调用获取对象实例的方法
 * 3. 使用场景：
 *      需要频繁的进行创建和销毁的对象、创建对象时耗时过多或耗费资源过多（即：重量级对象），但又经常用到的对象、
 *      工具类对象、频繁访问数据库或文件的对象（比如数据源、session工厂等）
 */
interface Summary {
}