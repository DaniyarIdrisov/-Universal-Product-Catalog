package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    private String text;

    private String username;

    private Long userId;

    public static CommentDto fromModel(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .userId(comment.getUser().getId())
                .username(comment.getUser().getLogin())
                .id(comment.getId())
                .text(comment.getText())
                .build();
    }

    public static List<CommentDto> fromModel(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::fromModel)
                .collect(Collectors.toList());
    }

}
