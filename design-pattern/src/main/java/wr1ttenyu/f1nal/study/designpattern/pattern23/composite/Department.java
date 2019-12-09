package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

/**
 * 叶子节点 只有打印方法
 */
public class Department extends OrganizationComponent {

    public Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    void print() {
        System.out.println(getName());
    }

}