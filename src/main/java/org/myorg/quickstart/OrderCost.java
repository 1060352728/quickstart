package org.myorg.quickstart;

/**
 * @Auther: likui
 * @Date: 2019/9/19 21:47
 * @Description:
 */
public class OrderCost {

    private String parentOrderNo;

    private String memberId;

    private String unionId;

    private String createTime;

    public String getParentOrderNo() {
        return parentOrderNo;
    }

    public void setParentOrderNo(String parentOrderNo) {
        this.parentOrderNo = parentOrderNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderCost{" +
                "parentOrderNo='" + parentOrderNo + '\'' +
                ", memberId='" + memberId + '\'' +
                ", unionId='" + unionId + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
