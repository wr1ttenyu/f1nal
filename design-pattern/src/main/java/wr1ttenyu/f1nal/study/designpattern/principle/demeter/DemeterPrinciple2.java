package wr1ttenyu.f1nal.study.designpattern.principle.demeter;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DemeterPrinciple2 {

    public static void main(String[] args) {
        SchoolManager1 schoolManager = new SchoolManager1();
        schoolManager.printAllEmployee(new CollegeManager1());
    }
}

// 代码1分析的问题：
// CollegeEmployee 不是 SchoolManager 直接朋友 而是一个陌生的类，这样就违背了 迪米特法则
// 解决方案：
// 将 打印 CollegeEmployee 的逻辑抽取到 CollegeManager1 中去编写
class SchoolManager1 {
    public List<SchoolEmployee> getAllSchoolEmployee() {
        List<SchoolEmployee> employees = new ArrayList<>(3);
        SchoolEmployee schoolEmployee;
        for (int i = 0; i < 3; i++) {
            schoolEmployee = new SchoolEmployee();
            schoolEmployee.setId(i);
            employees.add(schoolEmployee);
        }
        return employees;
    }

    public void printAllEmployee(CollegeManager1 collegeManager) {
        System.out.println("---------------- 学校总部员工  ----------------");
        List<SchoolEmployee> allSchoolEmployee = getAllSchoolEmployee();
        allSchoolEmployee.stream().forEach((schoolEmployee) ->
                System.out.println("学校员工,id:" + schoolEmployee.getId()));

        System.out.println("---------------- 学院员工  ----------------");
        collegeManager.printAllCollegeEmployee();
    }
}

class CollegeManager1 {
    public List<CollegeEmployee> getAllCollegeEmployee() {
        List<CollegeEmployee> employees = new ArrayList<>(6);
        CollegeEmployee collegeEmployee;
        for (int i = 0; i < 6; i++) {
            collegeEmployee = new CollegeEmployee();
            collegeEmployee.setId(i);
            employees.add(collegeEmployee);
        }
        return employees;
    }

    public void printAllCollegeEmployee() {
        List<CollegeEmployee> allCollegeEmployee = getAllCollegeEmployee();
        System.out.println("---------------- 学院员工----------------");
        allCollegeEmployee.stream().forEach((collegeEmployee) ->
                System.out.println("学院员工,id:" + collegeEmployee.getId()));
    }
}