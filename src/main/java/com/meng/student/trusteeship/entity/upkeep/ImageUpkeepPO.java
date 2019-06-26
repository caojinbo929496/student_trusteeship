package com.meng.student.trusteeship.entity.upkeep;

/**
 * @author fengqigui
 * @date 2018/3/12 13:20:00
 * 保养的清单图片实体
 */
public class ImageUpkeepPO {

    /**
     * 主键 ID
     */
    private String uuid;

    /**
     * 保养主键 ID
     */
    private String upkeepId;

    /**
     * 保养清单的相对路径
     */
    private String path;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUpkeepId() {
        return upkeepId;
    }

    public void setUpkeepId(String upkeepId) {
        this.upkeepId = upkeepId == null ? null : upkeepId.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}