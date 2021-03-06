package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("建造普通房子的地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("建造普通房子的墙体");
    }

    @Override
    public void buildRoof() {
        System.out.println("建造普通房子的房顶");
    }
}
