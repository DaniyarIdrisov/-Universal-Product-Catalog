package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;

import java.util.List;

public interface RestItemsService {

    List<ItemDto> getAllItems();

    ItemDto getById(Long itemId);

    ItemDto addItem(ItemDto itemDto);

    ItemDto updateById(Long itemId, ItemDto itemDto);

    void deleteById(Long itemId);

}
