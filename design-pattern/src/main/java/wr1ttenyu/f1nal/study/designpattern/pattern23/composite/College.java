package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {

    private List<OrganizationComponent> departments = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent component) {
        departments.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        departments.remove(component);
    }

    @Override
    void print() {
        System.out.println("============== " + getName() + " ==============");
        for (OrganizationComponent department : departments) {
            department.print();
        }
    }
}
