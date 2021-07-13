package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.CategoriesService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.ItemsService;

import java.util.List;

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ConversionService conversionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/itemsPage")
    public String getItemsPage(Model model) {
        List<ItemDto> itemDtoList = itemsService.getAllItems();
        List<CategoryDto> categoryDtoList = categoriesService.getAllCategories();
        model.addAttribute("items", itemDtoList);
        model.addAttribute("categories", categoryDtoList);
        return "items";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addItem")
    public String addItem(ItemDto itemDto, @RequestParam(value = "category_id", required = false) Long categoryId, @RequestParam(value = "item_price", required = false) String priceString, @RequestParam(value = "upload_file", required = false) MultipartFile file) {
        Double price = conversionService.convert(priceString, Double.class);
        if (itemDto.getLabel().equals("") || itemDto.getDescription().equals("") || price == null) {
            return "redirect:/itemsPage";
        }
        String filename = itemsService.uploadFile(file);
        if (filename == null) {
            return "redirect:/itemsPage";
        }
        itemsService.addItem(itemDto, categoryId, price, filename);
        return "redirect:/itemsPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/deleteItem/{item_id}")
    public String deleteItem(@PathVariable("item_id") Long itemId) {
        if (itemsService.hasChildren(itemId)) {
            return "redirect:/deleteFail";
        }
        itemsService.deleteItem(itemId);
        return "redirect:/itemsPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editItemPage/{item_id}")
    public String editItemPage(@PathVariable("item_id") Long itemId, Model model) {
        ItemDto itemDto = itemsService.getItemById(itemId);
        List<CategoryDto> categoryDtoList = categoriesService.getAllCategories();
        model.addAttribute("item", itemDto);
        model.addAttribute("categories", categoryDtoList);
        return "edit_items";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/editItem/{item_id}")
    public String editItem(@PathVariable("item_id") Long itemId, ItemDto itemDto, @RequestParam(value = "category_id", required = false) Long categoryId, @RequestParam(value = "item_price", required = false) String priceString, @RequestParam(value = "upload_new_file", required = false) MultipartFile newFile) {
        itemsService.updateItem(itemId, itemDto, categoryId, priceString, newFile);
        return "redirect:/editItemPage/{item_id}";
    }

}
