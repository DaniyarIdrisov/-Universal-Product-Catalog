package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.exceptions.GetException;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Category;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.CategoriesRepository;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto.fromModel;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return fromModel(categoriesRepository.findAll());
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .build();
        categoriesRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoriesRepository.deleteById(categoryId);
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoriesRepository.findById(categoryId).orElse(null);
        category.setCategoryName(categoryDto.getCategoryName());
        categoriesRepository.save(category);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoriesRepository.findById(categoryId).orElse(null);
        if (category == null) throw new GetException("There are no categories with this id!");
        return fromModel(category);
    }

    @Override
    public boolean hasChildren(Long categoryId) {
        Category category = categoriesRepository.findById(categoryId).orElse(null);
        assert category != null;
        if (!category.getItems().isEmpty()) {
            return true;
        }
        return false;
    }

}
