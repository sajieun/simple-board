package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardDto create(
            @Valid // 검증해주는 아이
            @RequestBody BoardRequest boardRequest //  무조건 @RequestBody 해줘야함
    ){
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardDto view(
            @PathVariable Long id
    ){
        var entity = boardService.view(id);
        log.info("result : {}",entity); // 이거를 쓰려면 PostEntity에 @ToString.Exclude
        return boardService.view(id);
    }

}
