package wr1ttenyu.f1nal.study.designpattern.pattern23.builder;

/**
 * 盖房项目需求
 * 1) 需要建房子： 这一过程为打桩、 砌墙、 封顶
 * 2) 房子有各种各样的， 比如普通房， 高楼， 别墅， 各种房子的过程虽然一样， 但是要求不要相同的.
 * 3) 请编写程序， 完成需求
 *
 * 传统方式分析：
 * 1) 优点是比较好理解， 简单易操作。
 */
public class Client {

    public static void main(String[] args) {
        AbstractHouse houseBuilder = new CommonHouse();
        houseBuilder.build();
    }
}
