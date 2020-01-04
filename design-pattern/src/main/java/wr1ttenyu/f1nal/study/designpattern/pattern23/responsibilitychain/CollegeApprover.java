package wr1ttenyu.f1nal.study.designpattern.pattern23.responsibilitychain;

import java.math.BigDecimal;

public class CollegeApprover extends Approver {

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPurchaseAmount().compareTo(new BigDecimal(10000)) <= 0
                && purchaseRequest.getPurchaseAmount().compareTo(new BigDecimal(5000)) >= 0) {
            System.out.println("这个 " + purchaseRequest.getPurchaseReason() + " 的 " + purchaseRequest
                    .getPurchaseAmount() + " 元采购，被我 " + getName() + " 处理了");
            return;
        } else {
            getNextApprover().processRequest(purchaseRequest);
        }
    }
}
