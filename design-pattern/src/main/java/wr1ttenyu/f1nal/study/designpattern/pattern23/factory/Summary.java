package wr1ttenyu.f1nal.study.designpattern.pattern23.factory;

import java.util.Calendar;

/**
 * 设计模式工厂模式总结
 * 工厂模式的意义：当需要根据条件去创建的某一类对象的某一种或多种时，我们可以将创建对象的逻辑封装到一个类中，
 *      调用方通过该类的方法来创建需要的对象，达到创建该种类对象的逻辑和主项目逻辑的解耦
 *      从而提高项目的扩展性和可维护性
 *
 * 三个变种：
 * 1. 简单工厂模式：如果需要创建的对象种类较少且创建过程比较简单 可以使用该方式
 * 2. 工厂方法模式：使用抽象类作为父类 让子类去继承 优势是在可以从父类里填充一些子类间通用的属性和方法
 * 3. 抽象工厂模式：使用接口作为最顶层 让子类去实现  子类实现的自由度更大
 *      适合复杂且自定义程度较高的对象创建
 *
 * 设计模式的 依赖抽象原则 在工厂模式中的体现：
 *  1.变量不要持有具体类的引用，比如我们的Pizza，我们持有的是父类的引用，而不是具体的某一种Pizza，
 *      创建过程交给了工厂去做，我们不直接通过某一具体类去new Pizza
 *  2.不要让类继承具体的类，而是去继承抽象类或者是实现 interface
 *  3.不要覆盖基类中已经实现的方法
 */
public class Summary {

    public static void main(String[] args) {
        //工厂模式在JDK中的应用 {@link java.util.Calendar}
        // 简单工厂模式的静态方法模式
        Calendar calendar = Calendar.getInstance();
    }
}
