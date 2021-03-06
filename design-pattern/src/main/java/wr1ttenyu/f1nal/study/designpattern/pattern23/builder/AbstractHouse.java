package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public abstract class AbstractHouse {

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void buildRoof();

    public void build() {
        buildBasic();
        buildWalls();
        buildRoof();
    }
}
