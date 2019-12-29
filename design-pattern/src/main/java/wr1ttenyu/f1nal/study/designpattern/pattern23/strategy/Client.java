package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy;

/**
 * 需求：
 * 编写鸭子项目， 具体要求如下:
 * 1) 有各种鸭子(比如 野鸭、 北京鸭、 水鸭等， 鸭子有各种行为， 比如 叫、 飞行等)
 * 2) 显示鸭子的信息
 *
 * 传统方案：
 * 所有鸭子继承一个父类，各自实现父类的方法
 *
 * 策略模式解决鸭子问题 Strategy.puml
 *
 * 思路分析:
 *      分别封装行为接口，实现算法族，超类里面放行为接口对象，在子类里具体设定行为对象。
 * 原则就是：
 *      分离变化部分，抽象接口，基于接口编程各种功能。-- 此模式让行为的变化独立于算法的使用者 --
 */
public class Client {

    public static void main(String[] args) {
        Duck domesticDuck = new DomesticDuck();
        Duck wildDuck = new WildDuck();

        domesticDuck.fly();
        domesticDuck.quack();

        wildDuck.fly();
        wildDuck.quack();

    }
}
