package com.shanzhu.blog.cms.domain;

import com.shanzhu.blog.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 留言点赞实体类
 */
public class CmsMessageLike extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long messageId;
    private Long userId;

    /**
     * 点赞数量
     */
    private Long likeNum;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("messageId", getMessageId())
                .append("userId", getUserId())
                .append("likeNum", getLikeNum())
                .toString();
    }
}
