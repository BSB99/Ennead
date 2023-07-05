package com.example.ennead.controller;

import com.example.ennead.dto.*;
import com.example.ennead.entity.Board;
import com.example.ennead.security.UserDetailsImpl;
import com.example.ennead.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/all") // 게시글 조회
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();

    }
    @PostMapping("/board") // 게시글 등록
    public BoardResponseDto postBoard(@RequestParam("category")String name,
                                      @RequestBody BoardRequestDto requestDto ,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.postBoard(requestDto , name , userDetails.getUser());
    }
    @GetMapping("/board") // 특정 카테고리 게시글 조회
    public PageCategoryBoardDto getCategoryBoards(@RequestParam("category")String name,
                                                  @RequestParam("page")int page){
        Page<Board> pageBoard = boardService.getCategoryBoards(name,page-1);

        PageInfo pageInfo = new PageInfo((int)pageBoard.getTotalElements(),page);
        List<BoardResponseDto> boardList=pageBoard.map(BoardResponseDto::new).getContent();

        return new PageCategoryBoardDto(boardList,pageInfo);
    }
    @GetMapping("/board/{board_no}") // 특정 게시글 조회 -> 조회할때 조회수도 1올라감
    public BoardResponseDto getBoard(@PathVariable Long board_no,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        return boardService.getBoard(board_no,request,response);
    }
    @PutMapping("/board/{board_no}") // 게시글 수정
    public BoardResponseDto updateBoard(@PathVariable Long board_no,
                                        @RequestBody BoardRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        Board board = boardService.updateBoard(requestDto,board_no,userDetails.getUser());
        return new BoardResponseDto(board,boardService.ListComment(board));
    }
    @DeleteMapping("/board/{board_no}") // 게시글 삭제
    public ApiResponseDto deleteBoard(@PathVariable Long board_no,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.deleteBoard(board_no,userDetails.getUser());
    }


}
