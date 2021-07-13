package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;

import java.util.List;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto.fromModel;

@Service
public class RestItemsServiceImpl implements RestItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public List<ItemDto> getAllItems() {
        return fromModel(itemsRepository.findAll());
    }

    @Override
    public ItemDto getById(Long itemId) {
        return fromModel(itemsRepository.findById(itemId).orElse(null));
    }

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = Item.builder()
                .description(itemDto.getDescription())
                .label(itemDto.getLabel())
                .filename(itemDto.getFilename())
                .price(itemDto.getPrice())
                .build();
        itemsRepository.save(item);
        return fromModel(item);
    }

    @Override
    public ItemDto updateById(Long itemId, ItemDto itemDto) {
        Item item = itemsRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
        item.setDescription(itemDto.getDescription());
        item.setLabel(itemDto.getLabel());
        item.setFilename(itemDto.getFilename());
        item.setPrice(itemDto.getPrice());
        itemsRepository.save(item);
        return fromModel(item);
    }

    @Override
    public void deleteById(Long itemId) {
        itemsRepository.deleteById(itemId);
    }

}
