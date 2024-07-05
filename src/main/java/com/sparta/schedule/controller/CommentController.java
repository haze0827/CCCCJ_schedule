package com.sparta.schedule.controller;



import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }



    //생성
    @PostMapping
    public CommentResponseDto createComment(@Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication) {
        return commentService.createComment(requestDto,authentication);
    }

    // 단건조회
    @GetMapping("/{commentId}")
    public CommentResponseDto getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    // 전체조회
    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }

    //수정
    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication){
        return commentService.updateComment(commentId, requestDto, authentication);
    }

    //삭제
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl authentication){
        return commentService.deleteComment(commentId, authentication);
    }


}

