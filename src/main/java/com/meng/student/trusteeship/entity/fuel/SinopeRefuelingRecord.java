package com.meng.student.trusteeship.entity.fuel;

/**
 * 完全对应中石化交易记录字段的实体类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class SinopeRefuelingRecord {
    /**
     * 交易金额
     */
    private Double amount;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 加油数量
     */
    private Double litre;
    /**
     * 油品
     */
    private String oilName;
    /**
     * 单价
     */
    private Double price;
    /**
     * 交易时间
     */
    private String opeTime;
    /**
     * 奖励分值
     */
    private Double reward;
    /**
     * 加油地点
     */
    private String nodeTag;
    /**
     * 交易类型听，‘加油’对应0，‘圈存’对应1
     */
    private String traName;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getLitre() {
        return litre;
    }

    public void setLitre(Double litre) {
        this.litre = litre;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public String getNodeTag() {
        return nodeTag;
    }

    public void setNodeTag(String nodeTag) {
        this.nodeTag = nodeTag;
    }

    public String getTraName() {
        return traName;
    }

    public void setTraName(String traName) {
        this.traName = traName;
    }

    public SinopeRefuelingRecord() {
    }

    public SinopeRefuelingRecord(Double amount, Double balance, Double litre, String oilName, Double price, String opeTime, Double reward, String nodeTag, String traName) {
        this.amount = amount;
        this.balance = balance;
        this.litre = litre;
        this.oilName = oilName;
        this.price = price;
        this.opeTime = opeTime;
        this.reward = reward;
        this.nodeTag = nodeTag;
        this.traName = traName;
    }

    @Override
    public String toString() {
        return "SinopeRefuelingRecord{" +
                "amount=" + amount +
                ", balance=" + balance +
                ", litre=" + litre +
                ", oilName='" + oilName + '\'' +
                ", price=" + price +
                ", opeTime='" + opeTime + '\'' +
                ", reward=" + reward +
                ", nodeTag='" + nodeTag + '\'' +
                ", traName='" + traName + '\'' +
                '}';
    }
}
