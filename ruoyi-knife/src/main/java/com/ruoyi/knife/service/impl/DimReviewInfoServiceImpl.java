package com.ruoyi.knife.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.knife.mapper.DimReviewInfoMapper;
import com.ruoyi.knife.domain.DimReviewInfo;
import com.ruoyi.knife.service.IDimReviewInfoService;

/**
 * 追溯号管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
@Service
public class DimReviewInfoServiceImpl implements IDimReviewInfoService 
{
    @Autowired
    private DimReviewInfoMapper dimReviewInfoMapper;

    /**
     * 查询追溯号管理
     * 
     * @param reviewId 追溯号管理主键
     * @return 追溯号管理
     */
    @Override
    public DimReviewInfo selectDimReviewInfoByReviewId(Long reviewId)
    {
        return dimReviewInfoMapper.selectDimReviewInfoByReviewId(reviewId);
    }

    /**
     * 查询追溯号管理列表
     * 
     * @param dimReviewInfo 追溯号管理
     * @return 追溯号管理
     */
    @Override
    public List<DimReviewInfo> selectDimReviewInfoList(DimReviewInfo dimReviewInfo)
    {
        return dimReviewInfoMapper.selectDimReviewInfoList(dimReviewInfo);
    }

    /**
     * 新增追溯号管理
     * 
     * @param dimReviewInfo 追溯号管理
     * @return 结果
     */
    @Override
    public int insertDimReviewInfo(DimReviewInfo dimReviewInfo)
    {
        dimReviewInfo.setCreateTime(DateUtils.getNowDate());
        return dimReviewInfoMapper.insertDimReviewInfo(dimReviewInfo);
    }

    /**
     * 修改追溯号管理
     * 
     * @param dimReviewInfo 追溯号管理
     * @return 结果
     */
    @Override
    public int updateDimReviewInfo(DimReviewInfo dimReviewInfo)
    {
        return dimReviewInfoMapper.updateDimReviewInfo(dimReviewInfo);
    }

    /**
     * 批量删除追溯号管理
     * 
     * @param reviewIds 需要删除的追溯号管理主键
     * @return 结果
     */
    @Override
    public int deleteDimReviewInfoByReviewIds(Long[] reviewIds)
    {
        return dimReviewInfoMapper.deleteDimReviewInfoByReviewIds(reviewIds);
    }

    /**
     * 删除追溯号管理信息
     * 
     * @param reviewId 追溯号管理主键
     * @return 结果
     */
    @Override
    public int deleteDimReviewInfoByReviewId(Long reviewId)
    {
        return dimReviewInfoMapper.deleteDimReviewInfoByReviewId(reviewId);
    }
}
