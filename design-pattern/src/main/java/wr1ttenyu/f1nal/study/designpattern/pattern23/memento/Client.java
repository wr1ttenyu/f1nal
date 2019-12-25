package wr1ttenyu.f1nal.study.designpattern.pattern23.memento;

/**
 * 游戏角色状态恢复问题
 *      游戏角色有攻击力和防御力，在大战 Boss 前保存自身的状态(攻击力和防御力)，当大战 Boss 后攻击力和防御
 * 力下降，从备忘录对象恢复到大战前的状态\
 *
 * 传统解决方式：
 *      一个对象对应一个备份状态的对象
 * 传统的方式的问题分析
 * 1) 一个对象， 就对应一个保存对象状态的对象， 这样当我们游戏的对象很多时， 不利于管理， 开销也很大.
 * 2) 传统的方式是简单地做备份， new 出另外一个对象出来， 再把需要备份的数据放到这个新对象， 但这就暴露了
 *      对象内部的细节
 * 3) 解决方案： => 备忘录模式
 *
 */
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("最初的起点");
        originator.showState();

        System.out.println("-------- 存档 --------");
        CareTaker careTaker = new CareTaker();
        Memento memento = originator.createMemento();
        careTaker.addMemento(memento);

        System.out.println("-------- 继续前进 --------");
        originator.setState("蚌埠大帝国");
        originator.showState();

        System.out.println("-------- 回档 --------");
        originator.recoverFromMemento(careTaker.getMemento(0));
        originator.showState();

    }
}
