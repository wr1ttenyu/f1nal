package wr1ttenyu.f1nal.study.designpattern.pattern23.iterator;

import java.util.Iterator;

interface College {

    String getName();

    Iterator<Department> createIterator();

    void addDepartment(Department department);
}
