package com.example.simple_board.reply.db;

import com.example.simple_board.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private PostEntity post;

    private String userName;

    private String status;

    private String title;

    //만약 text형식을 string으로 오류가 났을 땐 이렇게 설정해주면 된다
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime repliedAt;

}
