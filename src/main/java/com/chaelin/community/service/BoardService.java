package com.chaelin.community.service;

import com.chaelin.community.dto.BoardDTO;
import com.chaelin.community.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface BoardService {

    Long register(BoardDTO dto);

    Page<Board> getList(Pageable pageable);
    BoardDTO read(Long bno);

    void modify(BoardDTO dto);

    void remove(Long bno);


    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .hit(dto.getHit())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity){

        BoardDTO dto  = BoardDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .writer(entity.getWriter())
                .hit(entity.getHit())
                .build();

        return dto;
    }
}
