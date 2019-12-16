package wr1ttenyu.f1nal.study.designpattern.pattern23.template;

public class RedBeansSoyMilkMaker extends SoymilkMaker {

    @Override
    public void select() {
        System.out.println("选用红豆和配料");
    }

    @Override
    public void add() {
        System.out.println("红豆和配料加入豆浆机中");
    }

    @Override
    public void soak() {
        System.out.println("启动豆浆机浸泡红豆和配料");
    }

    @Override
    public void beat() {
        System.out.println("启动豆浆机开始打磨红豆和配料");
    }
}
