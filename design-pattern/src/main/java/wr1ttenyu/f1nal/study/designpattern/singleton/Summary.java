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
 */
interface Summary {
}