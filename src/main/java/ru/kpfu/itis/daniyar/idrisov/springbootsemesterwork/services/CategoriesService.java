package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;

import java.util.List;

public interface CategoriesService {

    List<CategoryDto> getAllCategories();

    void addCategory(CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    void updateCategory(Long categoryId, CategoryDto categoryDto);

    CategoryDto getCategoryById(Long categoryId);

    boolean hasChildren(Long categoryId);

}
