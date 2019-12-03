package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.objectadapter;

import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter.Voltage220V;
import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter.Voltage5VPhone;

/**
 * 对象适配器模式介绍
 * 1.基本思路和类适配器的模式相同，只是将 Adapter 与 src 的关系，从继承改为了聚合，
 *      让 Adapter 持有 src的实例。
 * 2.根据“合成复用原则”，在系统中尽量使用关联关系（聚合）来代替继承关系。
 * 3.对象适配器模式是适配器模式常用的一种
 *
 * 应用实例说明
 * 以生活中充电器的例子来讲解适配器， 充电器本身相当于 Adapter， 220V 交流电
 *      相当于 src (即被适配者)， 我们的目 dst(即目标)是 5V 直流电， 使用对象适配器模式完成
 */
public class ObjectAdapterClient {

    public static void main(String[] args) {
        Voltage5VPhone voltage5VPhone = new Voltage5VPhone();
        Voltage220To5Adapter adapter = new Voltage220To5Adapter();
        Voltage220V voltage220V = new Voltage220V();
        adapter.setVoltage220V(voltage220V);
        voltage5VPhone.charging(adapter);
    }
}
