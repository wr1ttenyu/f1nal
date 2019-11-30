package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public class HouseBuilderDirector {

    private HouseBuilder houseBuilder;

    public HouseBuilderDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    // 如果处理建房子的流程 交给指挥者
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.buildRoof();
        return houseBuilder.buildHouse();
    }


    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }
}
