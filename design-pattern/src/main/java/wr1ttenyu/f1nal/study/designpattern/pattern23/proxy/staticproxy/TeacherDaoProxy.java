package wr1ttenyu.f1nal.study.designpattern.pattern23.proxy.staticproxy;

public class TeacherDaoProxy implements ITeacherDao {

    private ITeacherDao target;

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("静态代理开始.....");
        target.teach();
        System.out.println("静态代理结束.....");
    }

    @Override
    public ITeacherDao getTeacher() {
        return this;
    }
}
