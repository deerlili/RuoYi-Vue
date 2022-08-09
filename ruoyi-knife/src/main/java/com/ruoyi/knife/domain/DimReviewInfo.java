package com.ruoyi.knife.domain;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reviewId", getReviewId())
            .append("wholeKnifeId", getWholeKnifeId())
            .append("knifeHandleDrawId", getKnifeHandleDrawId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
