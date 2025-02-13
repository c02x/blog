package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.CmsTag;
import com.shanzhu.blog.cms.mapper.CmsBlogTagMapper;
import com.shanzhu.blog.cms.mapper.CmsTagMapper;
import com.shanzhu.blog.cms.service.ICmsTagService;
import com.shanzhu.blog.common.exception.ServiceException;
import com.shanzhu.blog.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 标签管理Service业务层处理
 */
@Service
public class CmsTagServiceImpl implements ICmsTagService {

    @Resource
    private CmsTagMapper cmsTagMapper;

    @Resource
    private CmsBlogTagMapper cmsBlogTagMapper;

    /**
     * 查询标签管理
     *
     * @param tagId 标签管理主键
     * @return 标签管理
     */
    @Override
    public CmsTag selectCmsTagByTagId(Long tagId) {
        return cmsTagMapper.selectCmsTagByTagId(tagId);
    }

    /**
     * 查询标签管理列表
     *
     * @param cmsTag 标签管理
     * @return 标签管理
     */
    @Override
    public List<CmsTag> selectCmsTagList(CmsTag cmsTag) {
        List<CmsTag> cmsTagList = cmsTagMapper.selectCmsTagList(cmsTag);
        for (CmsTag tag : cmsTagList) {
            int blogNum = cmsBlogTagMapper.countBlogByTagId(tag.getTagId());
            tag.setBlogNum(blogNum);
        }
        return cmsTagList;
    }

    /**
     * 新增标签管理
     *
     * @param cmsTag 标签管理
     * @return 结果
     */
    @Override
    public int insertCmsTag(CmsTag cmsTag) {
        List<CmsTag> cmsTagList = cmsTagMapper.selectCmsTagListByTagName(cmsTag.getTagName());
        if (cmsTagList.size() > 0) {
            throw new ServiceException("标签名称已存在");
        }
        cmsTag.setCreateTime(DateUtils.getNowDate());
        return cmsTagMapper.insertCmsTag(cmsTag);
    }

    /**
     * 修改标签管理
     *
     * @param cmsTag 标签管理
     * @return 结果
     */
    @Override
    public int updateCmsTag(CmsTag cmsTag) {
        List<CmsTag> cmsTagList = cmsTagMapper.selectCmsTagListByTagName(cmsTag.getTagName());
        if (cmsTagList.size() > 0) {
            for (CmsTag tag : cmsTagList) {
                if (!cmsTag.getTagId().equals(tag.getTagId())) {
                    throw new ServiceException("标签名称已存在");
                }
            }
        }
        cmsTag.setUpdateTime(DateUtils.getNowDate());
        return cmsTagMapper.updateCmsTag(cmsTag);
    }

    /**
     * 批量删除标签管理
     *
     * @param tagIds 需要删除的标签管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsTagByTagIds(Long[] tagIds) {
        for (Long tagId : tagIds) {
            //删除标签文章关联表信息
            cmsBlogTagMapper.deleteBlogTagByTagId(tagId);
        }
        return cmsTagMapper.deleteCmsTagByTagIds(tagIds);
    }

    /**
     * 删除标签管理信息
     *
     * @param tagId 标签管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsTagByTagId(Long tagId) {
        //删除标签文章关联表信息
        cmsBlogTagMapper.deleteBlogTagByTagId(tagId);
        return cmsTagMapper.deleteCmsTagByTagId(tagId);
    }
}
