package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

public abstract class AbstractHouse {

    abstract void buildBasic();

    abstract void buildWalls();

    abstract void buildRoof();

    public void build() {
        buildBasic();
        buildWalls();
        buildRoof();
    }
}
