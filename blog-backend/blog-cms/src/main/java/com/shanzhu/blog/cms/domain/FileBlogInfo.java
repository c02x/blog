package com.shanzhu.blog.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * blog文件关联
 */
public class FileBlogInfo {

    private Long fileId;
    private Long blogId;
    private Long[] fileIds;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long[] getFileIds() {
        return fileIds;
    }

    public void setFileIds(Long[] fileIds) {
        this.fileIds = fileIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fileId", getFileId())
                .append("blogId", getBlogId())
                .toString();
    }

}
