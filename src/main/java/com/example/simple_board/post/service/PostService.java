package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.reply.db.ReplyRepository;
import com.example.simple_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;


    public PostEntity create(
            PostRequest postRequest
    ){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get();
        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build()
                ;

        return postRepository.save(entity);

    }
   /*
            1. 게시글이 있는가?
            2. 비밀번호가 맞는가?*/
    public PostEntity view(PostViewRequest postViewRequest) {
         return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map(it ->{
                    // entity 존재
                    if (!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword()));
                    }
//                     답변글도 같이 작성
//                    var replyList = replyService.findAllByPostId(it.getId());
//                    it.setReplyList(replyList);
//                    이제 관계를 넣어서 자동으로 가져오기에 안 넣어줘도됨

                    return it;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다. " + postViewRequest.getPostId());
                        }
                );
    }

    public Api<List<PostEntity>> all(
            Pageable pageable
    ) {
        var list = postRepository.findAll(pageable);
        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build()
                ;

        var response = Api.<List<PostEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build()
                ;
        return response;
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it ->{
                    // entity 존재
                    if (!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword()));
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);

                    // 답변글 상태 변경
//                    var replyList = replyService.findAllByPostId(it.getId());
//                    for (var reply : replyList) {
//                        reply.setStatus("UNREGISTERED");
//                        replyRepository.save(reply);
//                    }
                    return it;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다. " + postViewRequest.getPostId());
                        }
                );
    }
}
