package wr1ttenyu.f1nal.study.designpattern.pattern23.template;

public abstract class SoymilkMaker {

    // moban
    public final void make() {
        System.out.println("--------------- 开始制作豆浆 ---------------");
        select();
        add();
        soak();
        beat();
    }

    //  选材
    public abstract void select();

    //  加入材料
    public abstract void add();

    //  浸泡
    public abstract void soak();

    //  打磨
    public abstract void beat();
}
