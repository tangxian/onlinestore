package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 养车开支记录表
 * </p>
 *
 * @author TANGXIAN
 * @since 2019-01-08
 */
@TableName("car_detail")
public class Detail extends Model<Detail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 养车开销项目（1:油费,2:高速费,3:车辆保养,4:停车费）
     */
    private Integer item;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 累计公里数
     */
    private String km;
    /**
     * 创建时间
     */
    private Date time;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户id
     */
    private Integer userid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Detail{" +
        ", id=" + id +
        ", item=" + item +
        ", amount=" + amount +
        ", km=" + km +
        ", time=" + time +
        ", remark=" + remark +
        ", userid=" + userid +
        "}";
    }
}
