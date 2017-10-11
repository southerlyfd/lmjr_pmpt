/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @ClassName: BillBidRec.java
 * @Description: 拍卖单的所有竞价记录表
 * @author jianghb
 * @date 2017年9月14日下午4:23:46
 */
@Entity
public class BillBidRec implements Serializable{
	
	private static final long serialVersionUID = 4903562320767964188L;

	// 主键
	private Integer id;
	
	// 用户竞拍号
	private String auctionNum;
	
	// 出价时间
	private Date bidTime;
	
	// 出价金额
	private BigDecimal bidAmount;
	
	// 拍卖单
	private Bill bill;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getAuctionNum() {
		return auctionNum;
	}

	public void setAuctionNum(String auctionNum) {
		this.auctionNum = auctionNum;
	}

	@Column(nullable = false)
	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	@Column(nullable = false)
	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auctionNum == null) ? 0 : auctionNum.hashCode());
		result = prime * result + ((bidAmount == null) ? 0 : bidAmount.hashCode());
		result = prime * result + ((bidTime == null) ? 0 : bidTime.hashCode());
		result = prime * result + ((bill == null) ? 0 : bill.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BillBidRec other = (BillBidRec) obj;
		if (auctionNum == null) {
			if (other.auctionNum != null)
				return false;
		} else if (!auctionNum.equals(other.auctionNum))
			return false;
		if (bidAmount == null) {
			if (other.bidAmount != null)
				return false;
		} else if (!bidAmount.equals(other.bidAmount))
			return false;
		if (bidTime == null) {
			if (other.bidTime != null)
				return false;
		} else if (!bidTime.equals(other.bidTime))
			return false;
		if (bill == null) {
			if (other.bill != null)
				return false;
		} else if (!bill.equals(other.bill))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
