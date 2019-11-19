package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;

public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle = new Vehicle2();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.runAir("飞机");
        vehicle.runWater("轮船");
    }


}

/**
 * 方案3分析
 * 1. 这种修改方式没有对原来的客户端代码做出大的修改，没有新增类，只是增加原有类的方法
 * 2. 这里虽然没有在类的级别上遵守单一职责原则，但是在方法级别上遵守单一职责原则
 */
class Vehicle2 {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路运行");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + "在天空运行");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + "在水中运行");
    }
}