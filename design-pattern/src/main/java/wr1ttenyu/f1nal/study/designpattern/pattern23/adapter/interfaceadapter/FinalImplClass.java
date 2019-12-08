package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.interfaceadapter;

/**
 * 接口适配器模式介绍
 * 1) 一些书籍称为： 适配器模式(Default Adapter Pattern)或缺省适配器模式。
 * 2) 核心思路： 当不需要全部实现接口提供的方法时， 可先设计一个抽象类实现接口， 并为该接口中每个方法提供
 *      一个默认实现（空方法） ， 那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求
 * 3) 适用于一个接口不想使用其所有的方法的情况。
 */
public class FinalImplClass extends AbstractImpl {

    @Override
    public void method1() {
        System.out.println("最终实现method1");
    };

    @Override
    public void method2() {
        System.out.println("最终实现method2");
    };
}
