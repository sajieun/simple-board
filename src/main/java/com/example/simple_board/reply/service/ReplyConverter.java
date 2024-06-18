package com.example.simple_board.reply.service;

import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyDto, ReplyEntity> {

    private  final PostRepository postRepository;
    @Override
    public ReplyDto toDto(ReplyEntity replyEntity) {

        ReplyDto.builder()
                .id(replyEntity.getId())
                .postId(replyEntity.getPost().getId())
                .status(replyEntity.getStatus())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .userName(replyEntity.getUserName())
                .repliedAt(replyEntity.getRepliedAt())
                .build()
                ;
        return null;
    }

    @Override
    public ReplyEntity toEntity(ReplyDto replyDto) {

        var postEntity = postRepository.findById(replyDto.getPostId());

        return ReplyEntity.builder()
                .id(replyDto.getId()) // null이면 save !null update
                .post(postEntity.orElseGet(() -> null)) // get으로 가져오거나 없으면 null 로 return
                .status((replyDto.getStatus() != null) ? replyDto.getStatus() : "REGISTERED")
                .title(replyDto.getTitle())
                .content(replyDto.getContent())
                .userName(replyDto.getUserName())
                .repliedAt((replyDto.getRepliedAt() != null) ? replyDto.getRepliedAt() :LocalDateTime.now())
                .build()
                ;

    }
}
