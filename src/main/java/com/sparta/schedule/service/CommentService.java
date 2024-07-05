package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;
    public CommentService(ScheduleService scheduleService, CommentRepository commentRepository){
        this.scheduleService = scheduleService;
        this.commentRepository = commentRepository;
    }

    public CommentResponseDto createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Comment comment = new Comment(requestDto, user);
        if (comment.getComment() == null){
            throw  new IllegalArgumentException("내용을 입력해주세요.");
        }
        Schedule schedule = scheduleService.findSchedule(comment.getSchedule().getId());
        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;

    }

    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        Comment comment = findComment(commentId);
        if(!comment.getUser().getId().equals(user.getId())){
            throw  new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        } else {
            comment.update(commentId, requestDto);
        }
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
        return commentResponseDto;
    }


    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = findComment(commentId);
        return new CommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDtos.add(new CommentResponseDto(comment));
        }
        return commentResponseDtos;
    }

    public String deleteComment(Long commentId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Comment comment = findComment(commentId);
        if(!comment.getUser().getId().equals(user.getId())){
            throw  new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        } else {
            commentRepository.delete(comment);
            return "삭제되었습니다.";
        }
    }


    private Comment findComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }
}
