package com.ruoyi.knife.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 追溯号管理对象 dim_review_info
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
public class DimReviewInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 追溯号 */
    private Long reviewId;

    /** 整刀号 */
    @Excel(name = "整刀号")
    private Long wholeKnifeId;

    /** 刀柄号 */
    @Excel(name = "刀柄号")
    private Long knifeHandleDrawId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 刀具类型 */
    @Excel(name = "刀具类型")
    private String knifeType;

    /** 寿命 */
    @Excel(name = "寿命")
    private String knifeLife;

    /**
     * 插入次数
     * 用于保存一些程序调用或者组装的数据
     * 在数据中没有对应的字段
     */
    private transient String num;

    public void setNum(String num)
    {
        this.num = num;
    }

    public String getNum()
    {
        return num;
    }

    public void setReviewId(Long reviewId) 
    {
        this.reviewId = reviewId;
    }

    public Long getReviewId() 
    {
        return reviewId;
    }
    public void setWholeKnifeId(Long wholeKnifeId) 
    {
        this.wholeKnifeId = wholeKnifeId;
    }

    public Long getWholeKnifeId() 
    {
        return wholeKnifeId;
    }
    public void setKnifeHandleDrawId(Long knifeHandleDrawId) 
    {
        this.knifeHandleDrawId = knifeHandleDrawId;
    }

    public Long getKnifeHandleDrawId() 
    {
        return knifeHandleDrawId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setKnifeType(String knifeType) 
    {
        this.knifeType = knifeType;
    }

    public String getKnifeType() 
    {
        return knifeType;
    }
    public void setKnifeLife(String knifeLife) 
    {
        this.knifeLife = knifeLife;
    }

    public String getKnifeLife() 
    {
        return knifeLife;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reviewId", getReviewId())
            .append("wholeKnifeId", getWholeKnifeId())
            .append("knifeHandleDrawId", getKnifeHandleDrawId())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("knifeType", getKnifeType())
            .append("updateTime", getUpdateTime())
            .append("knifeLife", getKnifeLife())
            .append("num", getNum())
            .toString();
    }
}
