package com.example.ennead.service;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.BoardRequestDto;
import com.example.ennead.dto.BoardResponseDto;
import com.example.ennead.dto.CategoryResponseDto;
import com.example.ennead.entity.Board;
import com.example.ennead.entity.Category;
import com.example.ennead.entity.User;
import com.example.ennead.repository.BoardRepository;
import com.example.ennead.repository.CategoryRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Transactional
    public BoardResponseDto getBoard(Long boardNo) {
        Board board=findId(boardNo);
//        viewCountUp(boardNo,board,request,response);
        board.setBoardCount(board.getBoardCount()+1);

        return new BoardResponseDto(board);
    }
    @Transactional
    public Board updateBoard(BoardRequestDto requestDto, Long boardNo , User user) {
        Board board=findId(boardNo);
        confirmTokenId(board  , user );
        board.update(requestDto);
        return board;

    }

    public ApiResponseDto deleteBoard(Long boardNo,User user) {
        Board board=findId(boardNo);
        confirmTokenId(board  , user );

        boardRepository.delete(board);
        return new ApiResponseDto("댓글 삭제 성공", 200);
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


//    public void viewCountUp(Long boardNo, Board board, HttpServletRequest request, HttpServletResponse response) {
//        Cookie oldCookie = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("boardView")) { // 쿠키가 있고 boardView 쿠키가 존재할때 그 값을 oldcookie 에 넣음
//                    oldCookie = cookie;
//
//                }
//            }
//        }
//        if (oldCookie != null) {
//            if (!oldCookie.getValue().contains("[" + boardNo.toString() + "]")) {
//                board.setBoardCount(board.getBoardCount()+1);
//                oldCookie.setValue(oldCookie.getValue() + "_[" + boardNo + "]");
//                oldCookie.setPath("/");
//                oldCookie.setMaxAge(60 * 60 * 24);
//                response.addCookie(oldCookie); // 널이아니라면 boardView가 존재한다는것임 그리고 그 벨류에 id값이 포함되지않았다면  <- postview 쿠키가 존재할때
//                                                // 게시글을 읽은적이 없으니까 조회수를 올리고 그 게시글을 쿠키에 추가함
//            }                                   // 널이아니고 존재한다면 boardView도 있고 해당하는 쿠키도 있다는 뜻이기때문에 조회수를 안올림 <- postview 쿠키가 존재하는데
//        } else {                                                                                                                 //해당 게시글의 쿠키값 또한 boardView에 존재할때
//            board.setBoardCount(board.getBoardCount()+1);                                                                       // -> 해당 게시글을 읽었던적이 있음
//            Cookie newCookie = new Cookie("boardView","[" + boardNo + "]"); // 널이라면 처음 게시글을 클릭했다는뜻이므로 boardView쿠키를 생성하고 해당 게시글 조회수를 1 올리고 addCookie를 함
//            newCookie.setPath("/");                                                                                                 // <- 아예 boardView쿠키가 없을때 :  아직 아무게시글도 읽지않음
//            newCookie.setMaxAge(60 * 60 * 24);                                                                                       // -> 읽지않았으니까 조회수 1올리고 최초상태이기때문에
//            response.addCookie(newCookie);                                                                                          // -> boardView라는 쿠키를 만들어서 새로운 쿠키를 생성함
//        }
//    } // -> 같은 사용자가 조회수를 중복으로 늘리지않게 하는 코드


}
