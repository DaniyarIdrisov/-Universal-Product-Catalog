package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.CategoriesService;

import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/categoriesPage")
    public String getCategoriesPage(Model model) {
        List<CategoryDto> categoryDtoList = categoriesService.getAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "categories";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addCategory")
    public String addCategory(CategoryDto categoryDto) {
        if (categoryDto.getCategoryName().equals("")) {
            return "redirect:/categoriesPage";
        }
        categoriesService.addCategory(categoryDto);
        return "redirect:/categoriesPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/deleteCategory/{category_id}")
    public String deleteCategory(@PathVariable("category_id") Long categoryId) {
        if (categoriesService.hasChildren(categoryId)) {
            return "redirect:/deleteFail";
        }
        categoriesService.deleteCategory(categoryId);
        return "redirect:/categoriesPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editCategoryPage/{category_id}")
    public String getEditCategoryPage(@PathVariable("category_id") Long categoryId, Model model) {
        CategoryDto categoryDto = categoriesService.getCategoryById(categoryId);
        model.addAttribute("category", categoryDto);
        return "edit_categories";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/editCategory/{category_id}")
    public String editCategory(@PathVariable("category_id") Long categoryId, CategoryDto categoryDto) {
        if (categoryDto.getCategoryName().equals("")) {
            return "redirect:/editCategoryPage/{category_id}";
        }
        categoriesService.updateCategory(categoryId, categoryDto);
        return "redirect:/editCategoryPage/{category_id}";
    }

}
