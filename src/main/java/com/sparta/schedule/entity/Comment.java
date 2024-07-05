package com.sparta.schedule.entity;

import com.sparta.schedule.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "scheduleid")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, User user) {
        this.comment = requestDto.getComment();
        this.schedule = requestDto.getSchedule();
        this.user = user;
    }

    public void update(Long commentid, CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
