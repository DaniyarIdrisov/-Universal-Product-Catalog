package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.exceptions.GetException;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Comment;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.CommentsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.UsersRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;

import java.util.List;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto.fromModel;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<CommentDto> getCommentsByItemId(Long itemId) {
        return fromModel(commentsRepository.findAllByItem_Id(itemId));
    }

    @Override
    public void addComment(CommentDto commentDto, Long itemId, String username) {
        Item item = itemsRepository.findById(itemId).orElse(null);
        User user = usersRepository.findUserByLogin(username).orElse(null);
        Comment comment = Comment.builder()
                .text(commentDto.getText())
                .item(item)
                .user(user)
                .build();
        commentsRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        Comment comment = commentsRepository.findById(commentId).orElse(null);
        if (comment == null) throw new GetException("There are no comments with this id!");
        return fromModel(commentsRepository.findById(commentId).orElse(null));
    }

    @Override
    public void updateComment(Long commentId, CommentDto commentDto) {
        Comment comment = commentsRepository.findById(commentId).orElse(null);
        comment.setText(commentDto.getText());
        commentsRepository.save(comment);
    }

}
