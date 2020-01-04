package wr1ttenyu.f1nal.study.designpattern.pattern23.responsibilitychain;

import java.math.BigDecimal;

public class PurchaseRequest {

    private BigDecimal purchaseAmount;

    private String purchaseReason;

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getPurchaseReason() {
        return purchaseReason;
    }

    public void setPurchaseReason(String purchaseReason) {
        this.purchaseReason = purchaseReason;
    }
}
