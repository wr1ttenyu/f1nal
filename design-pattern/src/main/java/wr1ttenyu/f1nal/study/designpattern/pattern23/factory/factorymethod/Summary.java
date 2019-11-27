package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod;

/**
 * 看一个新的需求
 * 披萨项目新的需求： 客户在点披萨时， 可以点不同口味不同地方的披萨， 比如 北京的奶酪 pizza、 北京的胡椒 pizza 或
 * 者是伦敦的奶酪 pizza、 伦敦的胡椒 pizza。
 * 思路 1
 * 使用简单工厂模式， 创建不同的简单工厂类， 比如 BJPizzaSimpleFactory、 LDPizzaSimpleFactory 等等.从当前
 * 这个案例来说， 也是可以的， 但是考虑到项目的规模， 以及软件的可维护性、 可扩展性并不是特别好
 * 思路 2
 * 使用工厂方法模式
 *
 * 工厂方法模式介绍
 * 1) 工厂方法模式设计方案： 将披萨项目的实例化功能抽象成抽象方法， 在不同的口味点餐子类中具体实现。
 * 2) 工厂方法模式： 定义了一个创建对象的抽象方法， 由子类决定要实例化的类。
 * 3) 核心思想：工厂方法模式将对象的实例化推迟到子类
 */
public class Summary {
}
