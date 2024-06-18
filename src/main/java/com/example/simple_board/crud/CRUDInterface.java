package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<DTO> {
    DTO create(DTO dto);

    Optional<DTO> read(Long id);

    DTO update(DTO t);

    // 반환하지 않아서 void
    void delete(Long id);

    Api<List<DTO>> list(Pageable pageable);
}
