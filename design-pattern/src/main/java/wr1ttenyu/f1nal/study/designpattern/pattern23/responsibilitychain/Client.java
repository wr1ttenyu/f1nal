package wr1ttenyu.f1nal.study.designpattern.pattern23.responsibilitychain;

import java.math.BigDecimal;

/**
 * 需求
 * 学校 OA 系统的采购审批项目： 需求是
 * 采购员采购教学器材
 * 1) 如果金额 小于等于 5000, 由教学主任审批 （0<=x<=5000）
 * 2) 如果金额 小于等于 10000, 由院长审批 (5000<x<=10000)
 * 3) 如果金额 小于等于 30000, 由副校长审批 (10000<x<=30000)
 * 4) 如果金额 超过 30000 以上， 有校长审批 ( 30000<x)
 * 请设计程序完成采购审批项目
 *
 * 传统方案：
 *      接收到一个请求之后，使用 分支判断 来根据采购金额调用对应的 Approver(审批人) 完成审批
 *
 * 传统方式的问题分析：
 *      1. 如果各个级别的人员审批金额发生变化，在客户端也要实现对应变化
 *      2. 客户端必须明确知道 有多少个审批级别
 *      3. 这样 对一个采购请求进行处理 和 Approver 就存在强耦合关系，不利于代码维护
 *
 * 解决方案：
 *     职责链模式
 */
public class Client {

    public static void main(String[] args) {
        DepartmentApprover departmentApprover = new DepartmentApprover("杀币辅导员");
        CollegeApprover collegeApprover = new CollegeApprover("杀币院长");
        ViceSchoolManagerApprover viceSchoolManagerApprover  = new ViceSchoolManagerApprover("杀币副校长");
        SchoolManagerApprover schoolManagerApprover = new SchoolManagerApprover("杀币校长");

        // 构成环形
        departmentApprover.setNextApprover(collegeApprover);
        collegeApprover.setNextApprover(viceSchoolManagerApprover);
        schoolManagerApprover.setNextApprover(departmentApprover);

        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setPurchaseAmount(BigDecimal.valueOf(18000));
        purchaseRequest.setPurchaseReason("安工大重新整顿");

        departmentApprover.processRequest(purchaseRequest);
    }
}
