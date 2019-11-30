package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public class HighHouseBuilder extends HouseBuilder {
    @Override
    public void buildBasic() {
        house.setBasic("高楼地基");
        System.out.println("建造高楼的地基");
    }

    @Override
    public void buildWalls() {
        house.setWall("高楼墙体");
        System.out.println("建造高楼的墙体");
    }

    @Override
    public void buildRoof() {
        house.setRoof("高楼房顶");
        System.out.println("建造高楼的房顶");
    }
}
