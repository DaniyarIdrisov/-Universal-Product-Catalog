package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;

import java.util.List;

public interface ItemsService {

    List<ItemDto> getAllItems();

    ItemDto getItemById(Long id);

    void addItem(ItemDto itemDto, Long categoryId, Double price, String filename);

    String uploadFile(MultipartFile file);

    void deleteItem(Long itemId);

    void updateItem(Long itemId, ItemDto itemDto, Long categoryId, String priceString, MultipartFile file);

    List<ItemDto> getItemsByCategoryId(Long categoryId);

    List<ItemDto> getItemsByQuery(String query);

    boolean hasChildren(Long itemId);

    List<ItemDto> getAllByOrder(Long orderId);

    List<ItemDto> getItemsByQueryAndCategoryId(String query, Long categoryId);

}
