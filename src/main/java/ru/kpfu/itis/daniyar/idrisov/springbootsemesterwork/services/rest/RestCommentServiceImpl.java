package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.CommentsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;

import java.util.List;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto.fromModel;

@Service
public class RestCommentServiceImpl implements RestCommentService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CommentsRepository commentsRepository;


    @Override
    public List<CommentDto> getCommentByItemId(Long itemId) {
        Item item = itemsRepository.findById(itemId).orElse(null);
        return fromModel(commentsRepository.findAllByItemContains(item));
    }

}
