package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;

import java.util.List;

public interface CommentsService {

    List<CommentDto> getCommentsByItemId(Long itemId);

    void addComment(CommentDto commentDto, Long itemId, String username);

    void deleteComment(Long commentId);

    CommentDto getCommentById(Long commentId);

    void updateComment(Long commentId, CommentDto commentDto);

}
