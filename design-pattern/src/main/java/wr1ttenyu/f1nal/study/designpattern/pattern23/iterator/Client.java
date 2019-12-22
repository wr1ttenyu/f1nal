package wr1ttenyu.f1nal.study.designpattern.pattern23.iterator;

import java.util.Iterator;

/**
 * 看一个具体的需求
 * 编写程序展示一个学校院系结构： 需求是这样， 要在一个页面中展示出学校的院系组成， 一个学校有多个学院，
 * 一个学院有多个系
 *
 * 传统的方式的问题分析
 * 1) 将学院看做是学校的子类， 系是学院的子类， 这样实际上是站在组织大小来进行分层次的
 * 2) 实际上我们的要求是 ： 在一个页面中展示出学校的院系组成， 一个学校有多个学院， 一个学院有多个系， 因
 * 此这种方案， 不能很好实现的遍历的操作
 * 3) 解决方案： => 迭代器模式 showDepartment.puml
 */
public class Client {

    public static void main(String[] args) {
        InfoCollege infoCollege = new InfoCollege();
        infoCollege.addDepartment(new Department("信息安全", "信息安全"));
        infoCollege.addDepartment(new Department("网络安全", "网络安全"));
        infoCollege.addDepartment(new Department("服务器安全", "服务器安全"));
        infoCollege.addDepartment(new Department("通信安全", "通信安全"));

        ComputerCollege computerCollege = new ComputerCollege();
        computerCollege.addDepartment(new Department("计算器科学与技术", "计算器科学与技术"));
        computerCollege.addDepartment(new Department("软件工程", "软件工程"));
        computerCollege.addDepartment(new Department("计算器网络", "计算器网络"));
        computerCollege.addDepartment(new Department("黑客技术", "黑客技术"));

        Iterator<Department> infoCollegeIterator = infoCollege.createIterator();
        Iterator<Department> computerCollegeIterator = computerCollege.createIterator();

        iteratorCollegeDept(infoCollegeIterator, infoCollege.getName());
        iteratorCollegeDept(computerCollegeIterator, computerCollege.getName());
    }

    public static void iteratorCollegeDept(Iterator<Department> departmentIterator, String name) {
        System.out.println("================= " + name + " =================");
        while (departmentIterator.hasNext()) {
            Department next = departmentIterator.next();
            System.out.println("Department Name: " + next.getName() + " | Department Desc: " + next.getDesc());
        }
    }
}
