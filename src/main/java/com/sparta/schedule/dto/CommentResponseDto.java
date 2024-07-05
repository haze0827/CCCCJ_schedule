package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentid;
    private String comment;
    private String userName;
    private Long scheduleid;

    public CommentResponseDto(Comment comment) {
        this.commentid = comment.getCommentid();
        this.comment = comment.getComment();
        this.userName = comment.getUser().getUserName();
        this.scheduleid = comment.getSchedule().getId();
    }

    public CommentResponseDto(Long commentid, String comment, String userName, Long scheduleid) {
        this.commentid = commentid;
        this.comment = comment;
        this.userName = userName;
        this.scheduleid = scheduleid;
    }
}
