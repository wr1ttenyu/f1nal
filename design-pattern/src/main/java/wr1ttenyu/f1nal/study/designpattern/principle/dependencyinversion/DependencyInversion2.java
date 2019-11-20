package wr1ttenyu.f1nal.study.designpattern.principle.dependencyinversion;

public class DependencyInversion2 {

    public static void main(String[] args) {
        Person1 person = new Person1();
        person.receive(new Email1());
        person.receive(new Wechat());
    }

}

// 目标：完成Person接收消息
// 方案1如下 分析：
// 1. 方案简单，比较容易想到，适合简单问题
// 2. 如果有其他消息类型接入，比如微信，qq等，则Person必须新增方法，随着消息类型的增多，
//  会造成大量方法在 Person 类中生成
// 3. 解决思路：定义一个统一的消息接收抽象层，把消息接收的具体实现放到各个消息的实现类中，
//  而Person和各个消息接收实现类去依赖这个抽象层
class Person1 {

    public void receive(IReceiver receiver) {
        receiver.getInfo();
    }
}

interface IReceiver {
    void getInfo();
}

class Email1 implements IReceiver{

    public void getInfo() {
        System.out.println("email消息：hello，wr1ttenyu");
    }
}

class Wechat implements IReceiver{

    public void getInfo() {
        System.out.println("微信消息：hello，wr1ttenyu");
    }
}


