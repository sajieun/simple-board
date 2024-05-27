package com.example.simple_board.reply.service;

import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.db.ReplyRepository;
import com.example.simple_board.reply.model.ReplyDeleteRequest;
import com.example.simple_board.reply.model.ReplyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyEntity create(
            ReplyRequest replyRequest
    ) {
        var entity = ReplyEntity.builder()
                .postId(replyRequest.getPostId())
                .userName(replyRequest.getUserName())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getTitle())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);

    }

    public List<ReplyEntity> findAllByPostId(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
    }

    public void delete(@Valid ReplyDeleteRequest replyDeleteRequest) {
        replyRepository.findById(replyDeleteRequest.getId())
                .map(it -> {
                    if (!it.getId().equals(replyDeleteRequest.getId())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException("해당 답변이 존재하지 않습니다. " + replyDeleteRequest.getId());
                    }
                    it.setStatus("UNREGISTERED");
                    replyRepository.save(it);
                    return it;
                });
    }


}
