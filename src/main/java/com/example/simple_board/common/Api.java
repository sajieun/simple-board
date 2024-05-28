package com.example.simple_board.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 페이지 수 제한
public class Api<T> {

    private T body;

    private Pagination pagination;
}
