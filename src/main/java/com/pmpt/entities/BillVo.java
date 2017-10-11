package com.pmpt.entities;

import java.math.BigDecimal;

public class BillVo {
    private Integer id;
    private BigDecimal pledgePrice;
    private BigDecimal fee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPledgePrice() {
        return pledgePrice;
    }

    public void setPledgePrice(BigDecimal pledgePrice) {
        this.pledgePrice = pledgePrice;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
