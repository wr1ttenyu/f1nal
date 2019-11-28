package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public class CommonHouse extends AbstractHouse {
    @Override
    void buildBasic() {
        System.out.println("建造普通房子的地基");
    }

    @Override
    void buildWalls() {
        System.out.println("建造普通房子的墙体");
    }

    @Override
    void buildRoof() {
        System.out.println("建造普通房子的房顶");
    }
}
