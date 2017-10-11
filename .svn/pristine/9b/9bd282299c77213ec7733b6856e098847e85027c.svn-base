package com.pmpt.entities;

import com.pmpt.common.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "promotion")
public class Promotion {
    @Id
    private String id;
    @NotNull
    public String name; // 活动名称
    @NotNull
    public String description; // 活动描述
    @NotNull
    public Date startDate; // 开始时间
    @NotNull
    public Date endDate; // 结束时间
    @NotNull
    public Date createDate; // 创建日期
    @NotNull
    public Integer priority; // 排序编号
    @NotNull
    public String srcURI; // 展示图路径
    @NotNull
    public String type; // 活动类型
    @NotNull
    public String redirectURI; //活动URL
    @NotNull
    public String promotionStatus; // 促销现状
    @NotNull
    public boolean delete; // 是否有效（逻辑删除）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSrcURI() {
        return srcURI;
    }

    public void setSrcURI(String srcURI) {
        this.srcURI = srcURI;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /*@Override
    public String toString() {
        return "Promotion{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                ", priority=" + priority +
                ", srcURI='" + srcURI + '\'' +
                ", type='" + type + '\'' +
                ", redirectURI='" + redirectURI + '\'' +
                ", promotionStatus='" + promotionStatus + '\'' +
                ", delete=" + delete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Promotion promotion = (Promotion) o;

        if (delete != promotion.delete) return false;
        if (id != null ? !id.equals(promotion.id) : promotion.id != null) return false;
        if (name != null ? !name.equals(promotion.name) : promotion.name != null) return false;
        if (description != null ? !description.equals(promotion.description) : promotion.description != null)
            return false;
        if (startDate != null ? !startDate.equals(promotion.startDate) : promotion.startDate != null) return false;
        if (endDate != null ? !endDate.equals(promotion.endDate) : promotion.endDate != null) return false;
        if (createDate != null ? !createDate.equals(promotion.createDate) : promotion.createDate != null) return false;
        if (priority != null ? !priority.equals(promotion.priority) : promotion.priority != null) return false;
        if (srcURI != null ? !srcURI.equals(promotion.srcURI) : promotion.srcURI != null) return false;
        if (type != null ? !type.equals(promotion.type) : promotion.type != null) return false;
        if (redirectURI != null ? !redirectURI.equals(promotion.redirectURI) : promotion.redirectURI != null)
            return false;
        return promotionStatus != null ? promotionStatus.equals(promotion.promotionStatus) : promotion.promotionStatus == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (srcURI != null ? srcURI.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (redirectURI != null ? redirectURI.hashCode() : 0);
        result = 31 * result + (promotionStatus != null ? promotionStatus.hashCode() : 0);
        result = 31 * result + (delete ? 1 : 0);
        return result;
    }*/
}
