package com.chaelin.community.service;

import com.chaelin.community.dto.BoardDTO;
import com.chaelin.community.dto.PageRequestDTO;
import com.chaelin.community.dto.PageResultDTO;
import com.chaelin.community.domain.entity.Board;
import com.chaelin.community.domain.entity.QBoard;
import com.chaelin.community.domain.repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService{
    private final BoardRepository repository;

    @Override
    public Long register(BoardDTO dto) {

        Board entity = dtoToEntity(dto);

        repository.save(entity);
        return entity.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO); //검색 조건 처리

        Page<Board> result = repository.findAll(booleanBuilder, pageable); //Querydsl 사용

        Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO read(Long bno) {

        Optional<Board> result = repository.findById(bno);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public void remove(Long bno) {

        repository.deleteById(bno);

    }

    @Override
    public void modify(BoardDTO dto) {

        //업데이트 하는 항목은 '제목', '내용'
        Optional<Board> result = repository.findById(dto.getBno());

        if (result.isPresent()) {

            Board entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qBoard.bno.gt(0L); // gno > 0 조건만 생성

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }


        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qBoard.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qBoard.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qBoard.writer.contains(keyword));
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
