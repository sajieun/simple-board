package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Where;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore // 이걸 안 넣으면 반복돼서 오류
    @ToString.Exclude // 이걸 안 넣으면 반복돼서 오류
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(mappedBy = "post")
    @Where(clause = "status = 'REGISTERED'")
    @Builder.Default
    @org.hibernate.annotations.OrderBy(clause = "id desc") // 내림차순으로 정렬
    private List<ReplyEntity> replyList = List.of();

}
