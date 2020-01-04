package wr1ttenyu.f1nal.study.designpattern.pattern23.responsibilitychain;

import java.math.BigDecimal;

public class ViceSchoolManagerApprover extends Approver {

    public ViceSchoolManagerApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPurchaseAmount().compareTo(new BigDecimal(30000)) <= 0
                && purchaseRequest.getPurchaseAmount().compareTo(new BigDecimal(10000)) >= 0) {
            System.out.println("这个 " + purchaseRequest.getPurchaseReason() + " 的 " + purchaseRequest
                    .getPurchaseAmount() + " 元采购，被我 " + getName() + " 处理了");
            return;
        } else {
            getNextApprover().processRequest(purchaseRequest);
        }
    }
}
