package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String boardName;

    private String status;


    @OneToMany(mappedBy = "board")
    @Where(clause = "status = 'REGISTERED'")
    @Builder.Default // default로 리스트 오브가 만들어질 수 있도록
    private List<PostEntity> postEntityList = List.of();
}
