package com.ruoyi.knife.service;

import java.util.List;
import com.ruoyi.knife.domain.DimReviewInfo;

/**
 * 追溯号管理Service接口
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
public interface IDimReviewInfoService 
{
    /**
     * 查询追溯号管理
     * 
     * @param reviewId 追溯号管理主键
     * @return 追溯号管理
     */
    public DimReviewInfo selectDimReviewInfoByReviewId(Long reviewId);

    /**
     * 查询追溯号管理列表
     * 
     * @param dimReviewInfo 追溯号管理
     * @return 追溯号管理集合
     */
    public List<DimReviewInfo> selectDimReviewInfoList(DimReviewInfo dimReviewInfo);

    /**
     * 新增追溯号管理
     * 
     * @param dimReviewInfo 追溯号管理
     * @return 结果
     */
    public int insertDimReviewInfo(DimReviewInfo dimReviewInfo);

    /**
     * 修改追溯号管理
     * 
     * @param dimReviewInfo 追溯号管理
     * @return 结果
     */
    public int updateDimReviewInfo(DimReviewInfo dimReviewInfo);

    /**
     * 批量删除追溯号管理
     * 
     * @param reviewIds 需要删除的追溯号管理主键集合
     * @return 结果
     */
    public int deleteDimReviewInfoByReviewIds(Long[] reviewIds);

    /**
     * 删除追溯号管理信息
     * 
     * @param reviewId 追溯号管理主键
     * @return 结果
     */
    public int deleteDimReviewInfoByReviewId(Long reviewId);
}
