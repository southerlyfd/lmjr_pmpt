package com.pmpt.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @ClassName: Goods
 * @Description: 商品
 * @author: 汪洋
 * @date: 2017年8月15日 下午6:34:57
 */
@Entity
@NamedQueries({

		@NamedQuery(name = "findGoodsById", query = "select goods from Goods goods where goods.id =:goodsId")

})
public class Goods implements Serializable {

	private static final long serialVersionUID = -7731791993447300264L;

	// 主键
	private Integer id;

	// 商品名称
	private String gName;

	// 别名(俗名取代)
//	private String alias;

	// 产地(俗名取代)
//	private String production;

	// 品名
	private String commodity;

	// 规格
	private String spec;

	// 材质
	private String quality;

	// 商品细节图片展示列表
	private List<GoodsPic> goodsPics;

	// 商品描述
	private String goodsDes;
	
	// 商品视频展示
	private String videoURL;
	
	// 俗名
	private ProductName productName;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

/*	@Column(nullable = false)
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}*/

/*	@Column(nullable = false)
	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}*/

	@Column(nullable = false)
	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	@Column(nullable = false)
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "goodId")
	public List<GoodsPic> getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(List<GoodsPic> goodsPics) {
		this.goodsPics = goodsPics;
	}

	@Column
	public String getGoodsDes() {
		return goodsDes;
	}

	public void setGoodsDes(String goodsDes) {
		this.goodsDes = goodsDes;
	}

	@Column
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result + ((gName == null) ? 0 : gName.hashCode());
		result = prime * result + ((goodsDes == null) ? 0 : goodsDes.hashCode());
		result = prime * result + ((goodsPics == null) ? 0 : goodsPics.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
		result = prime * result + ((videoURL == null) ? 0 : videoURL.hashCode());
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
		Goods other = (Goods) obj;
		if (commodity == null) {
			if (other.commodity != null)
				return false;
		} else if (!commodity.equals(other.commodity))
			return false;
		if (gName == null) {
			if (other.gName != null)
				return false;
		} else if (!gName.equals(other.gName))
			return false;
		if (goodsDes == null) {
			if (other.goodsDes != null)
				return false;
		} else if (!goodsDes.equals(other.goodsDes))
			return false;
		if (goodsPics == null) {
			if (other.goodsPics != null)
				return false;
		} else if (!goodsPics.equals(other.goodsPics))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		if (videoURL == null) {
			if (other.videoURL != null)
				return false;
		} else if (!videoURL.equals(other.videoURL))
			return false;
		return true;
	}

	@Column
	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	@ManyToOne
	public ProductName getProductName() {
		return productName;
	}

	public void setProductName(ProductName productName) {
		this.productName = productName;
	}

}
