package com.example.ennead.service;

import com.example.ennead.dto.BoardRequestDto;
import com.example.ennead.dto.BoardResponseDto;
import com.example.ennead.dto.CategoryResponseDto;
import com.example.ennead.entity.Board;
import com.example.ennead.entity.Category;
import com.example.ennead.entity.User;
import com.example.ennead.repository.BoardRepository;
import com.example.ennead.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@RequiredArgsConstructor
@Service

public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    public List<BoardResponseDto> getBoards() {
        List<BoardResponseDto> boardResponseDtoList =  new ArrayList<>();
        List<Board> boardList =  boardRepository.findAll();
        for (Board board : boardList) {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board);
            boardResponseDtoList.add(boardResponseDto);
        }
        return boardResponseDtoList;
    }

    public BoardResponseDto postBoard(BoardRequestDto requestDto, String name , User user) {
        Category category=CategoryName(name);
        Board board=new Board(category,requestDto,user);
        return new BoardResponseDto(boardRepository.save(board));
    }

    public BoardResponseDto getBoard(Long boardNo) {
        Board board=findId(boardNo);
        return new BoardResponseDto(board);
    }
    @Transactional
    public Board updateBoard(BoardRequestDto requestDto, Long boardNo , User user) {
        Board board=findId(boardNo);
        confirmTokenId(board  , user );
        board.update(requestDto);
        return board;

    }

    public String deleteBoard(Long boardNo,User user) {
        Board board=findId(boardNo);
        confirmTokenId(board  , user );

        boardRepository.delete(board);
        return "삭제완료";
    }
    public CategoryResponseDto getCategoryBoards(String name) {
        Category category=CategoryName(name);
        Collections.reverse(category.getBoardList());
        List<BoardResponseDto> boardDtoList = new ArrayList<>();
        for (Board board :  category.getBoardList()){
            BoardResponseDto boardResponseDto = new  BoardResponseDto(board);
            boardDtoList.add(boardResponseDto);
        }
        return new CategoryResponseDto(boardDtoList,category);
    }
    public Board findId(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
    }
    private Category CategoryName(String name){
        if (categoryRepository.findByName(name) != null)
            return categoryRepository.findByName(name);
        else {
            throw new IllegalArgumentException("해당 카테고리가 존재하지않습니다");
        }
    }
    private void confirmTokenId(Board board , User user ) {
        if (board.getNickname().equals(user.getNickname())){
            System.out.println("작성한 글이 맞습니다");
        } else {
            throw new IllegalArgumentException("해당 유저가 작성한글이 아닙니다");
        }
    }


}
