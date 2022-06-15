package com.zcl.entity;

/**
 * 项目名称：ssm_hotel
 * 描述：房间类型
 *
 * @author zhong
 * @date 2022-05-19 21:26
 */
public class RoomType {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 类型名称
     */
    private String typename;
    /**
     * 类型图片
     */
    private String photo;
    /**
     * 价格
     */
    private Double price;
    /**
     * 可入住人数
     */
    private Integer livenum;
    /**
     * 床位数
     */
    private Integer bednum;

    /**
     * 房间数
     */
    private Integer roomnum;

    /**
     * 已预订数量
     */
    private Integer reservednum;

    /**
     * 可用房间数
     */
    private Integer avilablenum;

    /**
     * 已入住数
     */
    private Integer livednum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLivenum() {
        return livenum;
    }

    public void setLivenum(Integer livenum) {
        this.livenum = livenum;
    }

    public Integer getBednum() {
        return bednum;
    }

    public void setBednum(Integer bednum) {
        this.bednum = bednum;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getReservednum() {
        return reservednum;
    }

    public void setReservednum(Integer reservednum) {
        this.reservednum = reservednum;
    }

    public Integer getAvilablenum() {
        return avilablenum;
    }

    public void setAvilablenum(Integer avilablenum) {
        this.avilablenum = avilablenum;
    }

    public Integer getLivednum() {
        return livednum;
    }

    public void setLivednum(Integer livednum) {
        this.livednum = livednum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                ", photo='" + photo + '\'' +
                ", price=" + price +
                ", livenum=" + livenum +
                ", bednum=" + bednum +
                ", roomnum=" + roomnum +
                ", reservednum=" + reservednum +
                ", avilablenum=" + avilablenum +
                ", livednum=" + livednum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
