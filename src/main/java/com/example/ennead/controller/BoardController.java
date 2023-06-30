package com.example.ennead.controller;

import com.example.ennead.dto.BoardRequestDto;
import com.example.ennead.dto.BoardResponseDto;
import com.example.ennead.dto.CategoryResponseDto;
import com.example.ennead.service.BoardService;
import lombok.RequiredArgsConstructor;
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
    public BoardResponseDto postBoard(@RequestParam("category")String name ,@RequestBody BoardRequestDto requestDto){
        return boardService.postBoard(requestDto , name);

    }
    @GetMapping("/board") // 특정 카테고리 게시글 조회
    public CategoryResponseDto getCategoryBoards(@RequestParam("category")String name){
        return boardService.getCategoryBoards(name);
    }
    @GetMapping("/board/{board_no}") // 특정 게시글 조회
    public BoardResponseDto getBoard(@PathVariable Long board_no){
        return boardService.getBoard(board_no);}
    @PutMapping("/board/{board_no}") // 게시글 수정
    public BoardResponseDto updateBoard(@PathVariable Long board_no,
                                        @RequestBody BoardRequestDto requestDto){
        return new BoardResponseDto(boardService.updateBoard(requestDto,board_no));
    }
    @DeleteMapping("/board/{board_no}") // 게시글 삭제
    public String deleteBoard(@PathVariable Long board_no){
        return boardService.deleteBoard(board_no);
    }


}
