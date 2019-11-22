package wr1ttenyu.f1nal.study.designpattern.principle.demeter;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DemeterPrinciple1 {

    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}

@Getter
@Setter
class SchoolEmployee {
     private Integer id;
}

@Getter
@Setter
class CollegeEmployee {
    private Integer id;
}

// 代码分析
// 在 SchoolManager 中，SchoolEmployee 和 CollegeManager 是直接朋友关系，它们出现在了方法签名中
// CollegeEmployee 不是 直接朋友 而是一个陌生的类，这样就违背了 迪米特法则
class SchoolManager {
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

    public void printAllEmployee(CollegeManager collegeManager) {
        System.out.println("---------------- 学校总部员工  ----------------");
        List<SchoolEmployee> allSchoolEmployee = getAllSchoolEmployee();
        allSchoolEmployee.stream().forEach((schoolEmployee) ->
                System.out.println("学校员工,id:" + schoolEmployee.getId()));

        List<CollegeEmployee> allCollegeEmployee = collegeManager.getAllCollegeEmployee();
        System.out.println("---------------- 学院员工----------------");
        allCollegeEmployee.stream().forEach((collegeEmployee) ->
                System.out.println("学院员工,id:" + collegeEmployee.getId()));
    }
}

class CollegeManager {
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
}