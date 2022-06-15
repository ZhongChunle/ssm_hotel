package com.zcl.entity;

public class Room {
    private Integer id;
    /**
     * 图片
     */
    private String photo;
    /**
     * 房间号
     */
    private String roomnum;
    /**
     * 房间类型id
     */
    private Integer roomtypeid;
    private String typeName;
    /**
     * 楼层id
     */
    private Integer floorid;
    private String name;

    //房间状态(1-已预订 2-已入住 3-可预订)
    private Integer status;
    private String statusStr;

    public String getStatusStr() {
        // 判断状态
        if(status != null){
            switch (status) {
                case 1:
                    statusStr = "已预定";
                    break;
                    case 2 :
                        statusStr = "已入住";
                        break;
                        case 3 :
                            statusStr = "可预定";
                            break;
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    /**
     * 要求
     */
    private String roomrequirement;
    /**
     * 备注
     */
    private String remark;
    /**
     * 房间描述
     */
    private String roomdesc;
    /**
     * 房间类型价格
     */
    private Double price;

    /**
     * 床位数
     */
    private Integer bedNum;

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Integer roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getFloorid() {
        return floorid;
    }

    public void setFloorid(Integer floorid) {
        this.floorid = floorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoomrequirement() {
        return roomrequirement;
    }

    public void setRoomrequirement(String roomrequirement) {
        this.roomrequirement = roomrequirement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoomdesc() {
        return roomdesc;
    }

    public void setRoomdesc(String roomdesc) {
        this.roomdesc = roomdesc;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", roomnum='" + roomnum + '\'' +
                ", roomtypeid=" + roomtypeid +
                ", typeName='" + typeName + '\'' +
                ", floorid=" + floorid +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", roomrequirement='" + roomrequirement + '\'' +
                ", remark='" + remark + '\'' +
                ", roomdesc='" + roomdesc + '\'' +
                ", price=" + price +
                ", bedNum=" + bedNum +
                '}';
    }
}
