package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

import java.util.ArrayList;
import java.util.List;

public class University extends OrganizationComponent {

    private List<OrganizationComponent> colleges = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent component) {
        colleges.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        colleges.remove(component);
    }

    @Override
    void print() {
        System.out.println("------------- " + getName() + " -------------");
        for (OrganizationComponent college : colleges) {
            college.print();
        }
    }
}
