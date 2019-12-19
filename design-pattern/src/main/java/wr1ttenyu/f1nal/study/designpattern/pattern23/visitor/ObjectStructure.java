package wr1ttenyu.f1nal.study.designpattern.pattern23.visitor;

import java.util.LinkedList;
import java.util.List;

public class ObjectStructure {

    private List<Person> persons = new LinkedList<>();

    public void attach(Person person) {
        persons.add(person);
    }
    public void detach(Person person) {
        persons.remove(person);
    }

    // 显示测评情况
    public void display(Action action) {
        for (Person person : persons) {
            person.accept(action);
        }
    }
}
