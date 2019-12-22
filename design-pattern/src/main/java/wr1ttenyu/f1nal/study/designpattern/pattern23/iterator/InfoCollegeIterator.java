package wr1ttenyu.f1nal.study.designpattern.pattern23.iterator;

import java.util.Iterator;

public class InfoCollegeIterator implements Iterator<Department> {

    Department[] departments;

    int position;

    public InfoCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if(position >= departments.length || departments[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Department next() {
        return departments[position++] ;
    }

    @Override
    public void remove() {
        departments[position] = null;
    }
}
