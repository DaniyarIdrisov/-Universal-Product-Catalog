package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;

import java.util.List;

public interface RestCommentService {

    List<CommentDto> getCommentByItemId(Long itemId);

}
