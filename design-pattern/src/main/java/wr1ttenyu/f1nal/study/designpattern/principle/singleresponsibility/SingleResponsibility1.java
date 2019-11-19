package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;

public class SingleResponsibility1 {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }


}

/**
 * 交通工具类
 * 方式1
 * 1. 在方式1的 run 方法中违反了单一职责原则 飞机不能再公路上跑
 * 2. 解决方式 建多个交通工具类 负责运行在不同领域的交通工具
 */
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上跑");
    }
}