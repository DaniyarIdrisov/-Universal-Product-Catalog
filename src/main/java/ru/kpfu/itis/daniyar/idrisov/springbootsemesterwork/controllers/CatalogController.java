package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.aspects.PublicLog;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.CategoriesService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.ItemsService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CategoriesService categoriesService;

    @PublicLog
    @PermitAll
    @GetMapping("/catalog")
    public String catalogPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CategoryDto> categoryDtoList = categoriesService.getAllCategories();
        CategoryDto categoryDto = (CategoryDto) session.getAttribute("showCategory");
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("showCategory", categoryDto);
        if (session.getAttribute("showItems") == null) {
            List<ItemDto> itemDtoList = itemsService.getAllItems();
            model.addAttribute("items", itemDtoList);
        }
        else {
            List<ItemDto> itemDtoList = (List<ItemDto>) session.getAttribute("showItems");
            model.addAttribute("items", itemDtoList);
        }
        return "catalog";
    }

    @PermitAll
    @PostMapping("/showItemsByCategory")
    public String showItemsByCategory(@RequestParam(value = "category_id", required = false) Long categoryId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (categoryId != -1) {
            CategoryDto categoryDto = categoriesService.getCategoryById(categoryId);
            List<ItemDto> itemDtoList = itemsService.getItemsByCategoryId(categoryId);
            session.setAttribute("showItems", itemDtoList);
            session.setAttribute("showCategory", categoryDto);
            return "redirect:/catalog";
        }
        session.removeAttribute("showItems");
        session.removeAttribute("showCategory");
        return "redirect:/catalog";
    }


    @PermitAll
    @PostMapping("/addItemToCart")
    public String addItemToCart(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ItemDto> items;
        if (session.getAttribute("items") == null) {
            items = new ArrayList<>();
        } else {
            items = (List<ItemDto>) session.getAttribute("items");
        }
        items.add(itemsService.getItemById(id));
        session.setAttribute("items", items);
        return "redirect:/catalog";
    }

    @SneakyThrows
    @PermitAll
    @GetMapping("/catalogSearch")
    public void catalogSearch(HttpServletRequest request, HttpServletResponse response) {
        String query = request.getParameter("query");
        String category = request.getParameter("category");
        List<ItemDto> items;
        if (category == null) {
            items = itemsService.getItemsByQuery(query);
        }
        else {
            Long categoryId = Long.valueOf(category);
            items = itemsService.getItemsByQueryAndCategoryId(query, categoryId);
        }
        JSONArray jsonArray = new JSONArray();
        for (ItemDto item: items) {
            jsonArray.put(new JSONObject(item));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("objects", jsonArray);
        response.setContentType("text/json");
        response.getWriter().write(jsonObject.toString());
    }

}
