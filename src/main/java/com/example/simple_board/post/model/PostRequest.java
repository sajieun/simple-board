package com.example.simple_board.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {

    private Long boardId=1L; // 1번으로 임시고정

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 5,max = 20)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
