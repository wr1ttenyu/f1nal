package wr1ttenyu.f1nal.study.designpattern.principle.dependencyinversion;

/**
 * dependency inversion principle
 *
 * 1. 高层模块不应该依赖底层模块，二者都应该依赖其抽象
 * 2. 抽象不应该依赖细节，细节应该依赖抽象
 * 3. 依赖倒转（倒置）的中心思想是面向接口编程
 * 4. 依赖倒转原则是基于这样的设计理念：相对于细节的多变性，抽象的东西要稳定的多。
 *  以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。在java中，抽象指的是接口或者抽象类，细节就是具体的实现类。
 * 5. 使用接口或抽象类的目的是指定好的规范，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成。
 *
 * 依赖关系传递的三种方式：
 * 1. 接口传递
 * 2. 构造器传递
 * 3. setter传递
 *
 * 依赖倒转原则的注意事项和细节
 * 1. 低层模块尽量都要有抽象类或接口，或者两者都有，程序稳定性更好
 * 2. 变量的声明类型 尽量是 抽象类或接口，这样我们的变量引用和实际对象间，就存在一个缓冲层，
 *  利用程序扩展和优化
 * 3. 继承时遵循里氏替换原则
 */
interface Summary {

}

interface ITv {
    void play();
}

/**
 * 1. 接口传递
 */
interface IInterTvController {
    void open(ITv tv);
}

class InterfaceTranTvController implements IInterTvController {
    @Override
    public void open(ITv tv) {
        tv.play();
    }
}

/**
 * 2. 构造器传递
 */
interface IConTranTvController {
    void open();
}

class ConstructTranTvController implements IConTranTvController {

    private ITv tv;

    public ConstructTranTvController(ITv tv) {
        this.tv = tv;
    }

    @Override
    public void open() {
        tv.play();
    }
}

/**
 * 3. Setter方法传递
 */
interface ISetterTranTvController {
    void open();

    void setTv(ITv tv);
}

class SetterTranTvController implements ISetterTranTvController {

    private ITv tv;

    @Override
    public void setTv(ITv tv) {
        this.tv = tv;
    }

    @Override
    public void open() {
        tv.play();
    }
}





