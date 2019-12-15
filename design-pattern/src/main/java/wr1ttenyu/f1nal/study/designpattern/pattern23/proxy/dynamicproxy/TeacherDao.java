package wr1ttenyu.f1nal.study.designpattern.pattern23.proxy.dynamicproxy;

public class TeacherDao implements ITeacherDao{

    @Override
    public void teach() {
        System.out.println("老师在讲课.......");
    }

    @Override
    public ITeacherDao getTeacher() {
        return this;
    }
}
