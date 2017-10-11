/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @ClassName: AuctionBill.java
 * @Description: 拍买单
 * @author jianghb
 * @date 2017年9月14日下午4:02:55
 */
@Entity
public class AuctionBill implements Serializable{
	
	private static final long serialVersionUID = 2527191967286255242L;

	// 主键
	private Integer id;

	// 订单编号
	private String billNo;

	// 起始时间(付保证金时间)
	private Date startDate;

	// 卖家上架商品的支付方式
	private String payType;
	
	// 保证金(删)
	private BigDecimal bond;

	// 买家
	private LoginAccout buyerId;

	// 订单状态(枚举)
	private String billStatus;

	// 用户竞拍号
	private String auctionNum;
	
	// 个人出价记录
	private List<AucBillBidRec> aucBillBidRecs;
	
	// 买单
	private Bill billId;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@Column(nullable = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(nullable = false)
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(nullable = false)
	public BigDecimal getBond() {
		return bond;
	}

	public void setBond(BigDecimal bond) {
		this.bond = bond;
	}

	@OneToOne
	public LoginAccout getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(LoginAccout buyerId) {
		this.buyerId = buyerId;
	}

	@Column(nullable = false)
	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	@Column(nullable = false)
	public String getAuctionNum() {
		return auctionNum;
	}

	public void setAuctionNum(String auctionNum) {
		this.auctionNum = auctionNum;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<AucBillBidRec> getAucBillBidRecs() {
		return aucBillBidRecs;
	}

	public void setAucBillBidRecs(List<AucBillBidRec> aucBillBidRecs) {
		this.aucBillBidRecs = aucBillBidRecs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aucBillBidRecs == null) ? 0 : aucBillBidRecs.hashCode());
		result = prime * result + ((auctionNum == null) ? 0 : auctionNum.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result + ((billStatus == null) ? 0 : billStatus.hashCode());
		result = prime * result + ((bond == null) ? 0 : bond.hashCode());
		result = prime * result + ((buyerId == null) ? 0 : buyerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((payType == null) ? 0 : payType.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuctionBill other = (AuctionBill) obj;
		if (aucBillBidRecs == null) {
			if (other.aucBillBidRecs != null)
				return false;
		} else if (!aucBillBidRecs.equals(other.aucBillBidRecs))
			return false;
		if (auctionNum == null) {
			if (other.auctionNum != null)
				return false;
		} else if (!auctionNum.equals(other.auctionNum))
			return false;
		if (billNo == null) {
			if (other.billNo != null)
				return false;
		} else if (!billNo.equals(other.billNo))
			return false;
		if (billStatus == null) {
			if (other.billStatus != null)
				return false;
		} else if (!billStatus.equals(other.billStatus))
			return false;
		if (bond == null) {
			if (other.bond != null)
				return false;
		} else if (!bond.equals(other.bond))
			return false;
		if (buyerId == null) {
			if (other.buyerId != null)
				return false;
		} else if (!buyerId.equals(other.buyerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (payType == null) {
			if (other.payType != null)
				return false;
		} else if (!payType.equals(other.payType))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@OneToOne
	public Bill getBillId() {
		return billId;
	}

	public void setBillId(Bill billId) {
		this.billId = billId;
	}
	
}
