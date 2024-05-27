package com.example.simple_board.reply.controller;

import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDeleteRequest;
import com.example.simple_board.reply.model.ReplyRequest;
import com.example.simple_board.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("")
    public ReplyEntity create(
            @Valid
            @RequestBody ReplyRequest replyRequest
            ){
        return replyService.create(replyRequest);
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody ReplyDeleteRequest replyDeleteRequest
    ){
        replyService.delete(replyDeleteRequest);
    }

    @DeleteMapping("/id/{id}")
    public void delete2(
            @PathVariable ReplyEntity id
    ){
        replyService.delete(id);
    }


}
