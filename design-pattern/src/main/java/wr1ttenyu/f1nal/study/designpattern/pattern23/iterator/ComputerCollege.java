package wr1ttenyu.f1nal.study.designpattern.pattern23.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComputerCollege implements College {

    private List<Department> departments = new ArrayList<>();

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public Iterator<Department> createIterator() {
        return new ComputerCollegeIterator(departments);
    }

    @Override
    public void addDepartment(Department department) {
        departments.add(department);
    }
}
