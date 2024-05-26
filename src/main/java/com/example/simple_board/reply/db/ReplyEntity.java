package com.example.simple_board.reply.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long costId;

    private String userName;

    private String password;

    private String title;
    //만약 text형식을 string으로 오류가 났을 땐 이렇게 설정해주면 된다
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime repliedAt;




}
