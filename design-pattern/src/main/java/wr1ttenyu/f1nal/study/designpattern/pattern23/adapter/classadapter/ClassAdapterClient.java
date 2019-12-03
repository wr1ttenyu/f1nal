package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter;

/**
 * 类适配器模式注意事项和细节：
 *  1. Java是单继承机制，所以类适配器需要继承src类这一点算是一个缺点，因为这就
 *      限制了dst必须是接口的形式，有一定的局限性；
 *  2. src类的方法会在 Adapter 中暴露出来，也增加了使用的成本
 *  3. 由于其继承了 src 类，所以它可以根据需求重写 src 类的方法，使得 Adapter 的灵活性增强了。
 *
 *  应用实例说明
 *  以生活中充电器的例子来讲解适配器， 充电器本身相当于 Adapter， 220V 交流电
 *  相当于 src (即被适配者)， 我们的目 dst(即目标)是 5V 直流电， 使用对象适配器模式完成
 */
public class ClassAdapterClient {

    public static void main(String[] args) {
        Voltage5VPhone voltage5VPhone = new Voltage5VPhone();
        Voltage220To5Adapter adapter = new Voltage220To5Adapter();
        voltage5VPhone.charging(adapter);
    }
}
