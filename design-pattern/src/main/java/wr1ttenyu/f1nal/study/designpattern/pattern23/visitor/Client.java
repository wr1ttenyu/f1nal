package wr1ttenyu.f1nal.study.designpattern.pattern23.visitor;

/**
 * 测评系统的需求
 * 完成测评系统需求
 * 1) 将观众分为男人和女人，对歌手进行测评，当看完某个歌手表演后，得到他们对该歌手不同的评价(评价有不
 *      同的种类，比如成功、失败等)
 * 传统设计：
 *    如果简单设计为 观众分类继承 Person 然后在各个子类中实现评价方法，则当投票类型时，
 * 则需要修改所有 Person 子类的评价方法，因为评价方法的实现在不同子类中 逻辑不同，违反ocp原则
 *
 * 引出 ---  访问者模式
 *  Singer.puml
 *
 *  Person 相当于一种 Element 是一种数据结构
 *  Acton 定义了对  每一种Person实现类的 抽象访问方法
 *  ObjectStructure 则是 Element 的持有者，当一个访问请求过来时，
 *      ObjectStructure接收请求并遍历持有的 Element 去执行请求的Action
 *
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());

        Success success = new Success();
        objectStructure.display(success);

        Fail fail = new Fail();
        objectStructure.display(fail);
    }
}
