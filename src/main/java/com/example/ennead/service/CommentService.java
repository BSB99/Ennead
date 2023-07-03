package com.example.ennead.service;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.CommentRequestDto;
import com.example.ennead.dto.CommentResponseDto;
import com.example.ennead.entity.Board;
import com.example.ennead.entity.Comment;
import com.example.ennead.entity.User;
import com.example.ennead.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BoardService boardService;
    public CommentResponseDto createComment(String data, Long boardNo, CommentRequestDto request) {
        User user = userService.getUser(data);
        Board board = boardService.findName(boardNo);

        Comment comment = new Comment(request, user, board);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(String data, Long postNo, Long commentNo, CommentRequestDto request) {
        User user = userService.getUser(data);
        Board board = boardService.findName(postNo);
        Comment comment = findComment(commentNo);

        commentVerificate(comment, user, board);

        comment.setContent(request.getContent());

        return new CommentResponseDto(comment);
    }

    public ApiResponseDto deleteComment(String data, Long postNo, Long commentNo) {
        User user = userService.getUser(data);
        Board board = boardService.findName(postNo);
        Comment comment = findComment(commentNo);

        commentVerificate(comment, user, board);

        commentRepository.delete(comment);

        return new ApiResponseDto("댓글 삭제 성공", 200);
    }

    private Comment findComment(Long no) {
        return commentRepository.findById(no).orElseThrow(() -> {
            throw new IllegalArgumentException("없는 댓글 입니다.");
        });
    }

    private void commentVerificate(Comment comment, User user, Board board) {
        if (comment.getBoard().getId() != board.getId()) {
            throw new IllegalArgumentException("게시글 번호를 확인해주세요");
        }

        if (comment.getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("작성자가 아닙니다!");
        }
    }
}
