package com.oinzo.somoim;

import com.oinzo.somoim.controller.dto.BoardCreateRequest;
import com.oinzo.somoim.controller.dto.BoardResponse;
import com.oinzo.somoim.domain.board.entity.ClubBoard;
import com.oinzo.somoim.domain.board.repository.ClubBoardRepository;
import com.oinzo.somoim.domain.board.service.ClubBoardService;
import com.oinzo.somoim.domain.club.entity.Club;
import com.oinzo.somoim.domain.club.repository.ClubRepository;
import com.oinzo.somoim.domain.user.entity.User;
import com.oinzo.somoim.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardControllerTest {

    @Autowired
    ClubBoardService clubBoardService;

    @Autowired
    ClubBoardRepository clubBoardRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("게시판 생성 테스트")
    void addBoard() {
        // given
        BoardCreateRequest newBoard = new BoardCreateRequest("NORMAL","게시판 테스트","URL","자유");
        Club club = clubRepository.findById(1L).orElseThrow();
        User user = userRepository.findById(7L).orElseThrow();
        // when
        ClubBoard board = clubBoardRepository.save(ClubBoard.from(newBoard,club,user));
        // then
        Assertions.assertEquals(board.getTitle(),"NORMAL");
    }

    @Test
    @DisplayName("클럽 게시판 전체 조회 테스트")
    void clubBoardList() {
        // given
        // when
        List<BoardResponse> boardList =  clubBoardService.allClubBoardList(1L);
        // then
        assertTrue(boardList.size()>1);

    }

    @Test
    @DisplayName("게시판 상세 조회 테스트")
    void readBoard() {
        // given
        // when
        BoardResponse board = clubBoardService.readBoard(1L);
        // then
        assertEquals(board.getTitle(),"제목");
    }

    @Test
    @DisplayName("게시판 수정 테스트")
    void updateBoard() {
        // given
        BoardCreateRequest newBoard = new BoardCreateRequest("NORMAL","게시판 테스트","URL","자유");
        Club club = clubRepository.findById(1L).orElseThrow();
        User user = userRepository.findById(7L).orElseThrow();
        ClubBoard board = clubBoardRepository.save(ClubBoard.from(newBoard,club,user));
        // when
        BoardCreateRequest updateBoard = new BoardCreateRequest("NORMAL","수정된 테스트","URL","자유");
        BoardResponse updateBoard1 =clubBoardService.updateBoard(board.getId(),updateBoard,7L);
        assertEquals(updateBoard1.getTitle(),"NORMAL");
    }
}