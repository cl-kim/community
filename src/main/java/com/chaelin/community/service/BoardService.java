package com.chaelin.community.service;

import com.chaelin.community.dto.BoardDTO;
import com.chaelin.community.dto.PageRequestDTO;
import com.chaelin.community.dto.PageResultDTO;
import com.chaelin.community.domain.entity.Board;
import org.springframework.security.core.Authentication;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);
    BoardDTO read(Long bno);

    void modify(BoardDTO dto);

    void remove(Long bno);

    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
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
                .build();

        return dto;
    }
}
