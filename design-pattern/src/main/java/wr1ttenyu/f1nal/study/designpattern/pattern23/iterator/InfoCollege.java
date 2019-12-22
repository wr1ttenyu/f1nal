package wr1ttenyu.f1nal.study.designpattern.pattern23.iterator;

import java.util.Iterator;

public class InfoCollege implements College {

    private Department[] departments = new Department[10];

    private int collegeNum;

    @Override
    public String getName() {
        return "信息管理学院";
    }

    @Override
    public Iterator<Department> createIterator() {
        return new InfoCollegeIterator(departments);
    }

    @Override
    public void addDepartment(Department department) {
        departments[collegeNum++] = department;
    }
}
