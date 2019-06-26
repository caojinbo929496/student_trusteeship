package com.meng.student.trusteeship.entity.fuel;

/**
 * 加油卡DTO类，用于传特定信息给安卓客户端
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class RefuelingRecordDTO {
    /**
     * 里程数
     */
    private Double milege;
    /**
     * 加油金额
     */
    private Double tradingMoney;
    /**
     * 加油时间
     */
    private Long tradingTime;

    public Double getMilege() {
        return milege;
    }

    public void setMilege(Double milege) {
        this.milege = milege;
    }

    public Double getTradingMoney() {
        return tradingMoney;
    }

    public void setTradingMoney(Double tradingMoney) {
        this.tradingMoney = tradingMoney;
    }

    public Long getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Long tradingTime) {
        this.tradingTime = tradingTime;
    }

    public RefuelingRecordDTO() {
    }

    public RefuelingRecordDTO(Double milege, Double tradingMoney, Long tradingTime) {
        this.milege = milege;
        this.tradingMoney = tradingMoney;
        this.tradingTime = tradingTime;
    }

    @Override
    public String toString() {
        return "FuelCardDTO{" +
                "milege=" + milege +
                ", tradingMoney=" + tradingMoney +
                ", tradingTime=" + tradingTime +
                '}';
    }
}
