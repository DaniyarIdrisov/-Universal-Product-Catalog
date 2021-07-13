package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;

public interface RestCategoriesService {

    CategoryDto getCategoryByItemId(Long itemId);

}
