package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public class CommonHouseBuilder extends HouseBuilder {
    @Override
    public void buildBasic() {
        house.setBasic("普通房子地基");
        System.out.println("建造普通房子的地基");
    }

    @Override
    public void buildWalls() {
        house.setWall("普通房子墙体");
        System.out.println("建造普通房子的墙体");
    }

    @Override
    public void buildRoof() {
        house.setRoof( "普通房子房顶");
        System.out.println("建造普通房子的房顶");
    }
}
