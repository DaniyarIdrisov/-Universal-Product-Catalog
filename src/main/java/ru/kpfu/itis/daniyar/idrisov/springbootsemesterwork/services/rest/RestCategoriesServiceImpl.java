package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.CategoriesRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto.fromModel;

@Service
public class RestCategoriesServiceImpl implements RestCategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public CategoryDto getCategoryByItemId(Long itemId) {
        Item item = itemsRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
        return fromModel(categoriesRepository.findCategoryByItemsContains(item).orElse(null));
    }

}
