package com.meng.student.trusteeship.entity.maintain;

/**
 * @author fengqigui
 * @date 2018/3/12 14:00:00
 * 维修故障图片实体类
 */
public class ImageMaintainFaultPO {

    /**
     * ID
     */
    private String uuid;

    /**
     * 维修的ID
     */
    private String maintainId;

    /**
     * 图片的相对路径
     */
    private String path;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId == null ? null : maintainId.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }


}