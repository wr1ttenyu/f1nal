package wr1ttenyu.f1nal.study.designpattern.pattern23.iterator;

import java.util.Iterator;
import java.util.List;

public class ComputerCollegeIterator implements Iterator<Department> {

    List<Department> departments;

    int position;

    public ComputerCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if(position >= departments.size() || departments.get(position) == null) {
            return false;
        }
        return true;
    }

    @Override
    public Department next() {
        return departments.get(position++) ;
    }

    @Override
    public void remove() {
        departments.set(position, null);
    }
}
