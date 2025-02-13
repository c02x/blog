package com.shanzhu.blog.cms.controller;

import com.shanzhu.blog.cms.domain.FileNoticeInfo;
import com.shanzhu.blog.cms.domain.SysFileInfo;
import com.shanzhu.blog.cms.service.IFileNoticeInfoService;
import com.shanzhu.blog.cms.service.ISysFileInfoService;
import com.shanzhu.blog.common.core.controller.BaseController;
import com.shanzhu.blog.common.core.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 通知公告文件Controller
 */
@RestController
@RequestMapping("/fileNoticeInfo")
public class FileNoticeInfoController extends BaseController {

    @Resource
    private IFileNoticeInfoService fileNoticeInfoService;

    @Resource
    private ISysFileInfoService fileInfoService;

    /**
     * 新增通知公告文件
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody FileNoticeInfo fileNoticeInfo) {
        List<FileNoticeInfo> fileNoticeList = new ArrayList<>();
        Long noticeId = fileNoticeInfo.getNoticeId();
        Long[] fileIds = fileNoticeInfo.getFileIds();
        for (Long fileId : fileIds) {
            FileNoticeInfo info = new FileNoticeInfo();
            info.setFileId(fileId);
            info.setNoticeId(noticeId);
            fileNoticeList.add(info);
        }
        fileNoticeInfoService.batchFileNotice(fileNoticeList);
        return toAjax(1);
    }

    /**
     * 删除通知公告文件
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds) {
        for (Long id : noticeIds) {
            List<FileNoticeInfo> fileNoticeInfos = fileNoticeInfoService.selectFileNoticeList(id);
            fileNoticeInfos.forEach((FileNoticeInfo fileNoticeInfo) -> {
                Long fileId = fileNoticeInfo.getFileId();
                fileInfoService.deleteSysFileInfoByFileId(fileId);
            });
        }
        fileNoticeInfoService.deleteFileNotice(noticeIds);
        return toAjax(1);
    }

    /**
     * 根据通知公告编号获取文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId) {
        List<SysFileInfo> sysFileInfoList = new ArrayList<>();
        List<FileNoticeInfo> fileNoticeInfos = fileNoticeInfoService.selectFileNoticeList(noticeId);
        fileNoticeInfos.forEach((FileNoticeInfo fileNoticeInfo) -> {
            Long fileId = fileNoticeInfo.getFileId();
            SysFileInfo sysFileInfo = fileInfoService.selectSysFileInfoByFileId(fileId);
            if (sysFileInfo != null) {
                sysFileInfoList.add(sysFileInfo);
            }
        });
        return AjaxResult.success(sysFileInfoList);
    }

}
