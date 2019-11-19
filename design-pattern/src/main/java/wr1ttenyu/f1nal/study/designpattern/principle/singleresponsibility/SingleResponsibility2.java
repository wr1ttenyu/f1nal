package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;

public class SingleResponsibility2 {

    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");

        SkyVehicle skyVehicle = new SkyVehicle();
        skyVehicle.run("飞机");
    }
}

/**
 * 方案2分析
 * 1. 遵守了单一职责原则
 * 2. 但是这样代码的改动量比较大，需要建立多个类，同时要修改客户端代码（即调用处代码）
 * 3. 改进：直接修改 {@link Vehicle}，改动的代码会比较少 ---> {@link Vehicle2}
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路运行");
    }
}

class SkyVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在天空运行");
    }
}