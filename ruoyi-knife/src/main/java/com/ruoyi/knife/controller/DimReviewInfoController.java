package com.ruoyi.knife.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.knife.domain.DimReviewInfo;
import com.ruoyi.knife.service.IDimReviewInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 追溯号管理Controller
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/knife/revireInfo")
public class DimReviewInfoController extends BaseController
{
    @Autowired
    private IDimReviewInfoService dimReviewInfoService;

    /**
     * 查询追溯号管理列表
     */
    @PreAuthorize("@ss.hasPermi('knife:revireInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(DimReviewInfo dimReviewInfo)
    {
        startPage();
        List<DimReviewInfo> list = dimReviewInfoService.selectDimReviewInfoList(dimReviewInfo);
        return getDataTable(list);
    }

    /**
     * 导出追溯号管理列表
     */
    @PreAuthorize("@ss.hasPermi('knife:revireInfo:export')")
    @Log(title = "追溯号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DimReviewInfo dimReviewInfo)
    {
        List<DimReviewInfo> list = dimReviewInfoService.selectDimReviewInfoList(dimReviewInfo);
        ExcelUtil<DimReviewInfo> util = new ExcelUtil<DimReviewInfo>(DimReviewInfo.class);
        util.exportExcel(response, list, "追溯号管理数据");
    }

    /**
     * 获取追溯号管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('knife:revireInfo:query')")
    @GetMapping(value = "/{reviewId}")
    public AjaxResult getInfo(@PathVariable("reviewId") Long reviewId)
    {
        return AjaxResult.success(dimReviewInfoService.selectDimReviewInfoByReviewId(reviewId));
    }

    /**
     * 新增追溯号管理
     */
    @PreAuthorize("@ss.hasPermi('knife:revireInfo:add')")
    @Log(title = "追溯号管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DimReviewInfo dimReviewInfo)
    {
        return toAjax(dimReviewInfoService.insertDimReviewInfo(dimReviewInfo));
    }

    /**
     * 修改追溯号管理
     */
    @PreAuthorize("@ss.hasPermi('knife:revireInfo:edit')")
    @Log(title = "追溯号管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DimReviewInfo dimReviewInfo)
    {
        return toAjax(dimReviewInfoService.updateDimReviewInfo(dimReviewInfo));
    }

    /**
     * 删除追溯号管理
     */
    @PreAuthorize("@ss.hasPermi('knife:revireInfo:remove')")
    @Log(title = "追溯号管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reviewIds}")
    public AjaxResult remove(@PathVariable Long[] reviewIds)
    {
        return toAjax(dimReviewInfoService.deleteDimReviewInfoByReviewIds(reviewIds));
    }
}
