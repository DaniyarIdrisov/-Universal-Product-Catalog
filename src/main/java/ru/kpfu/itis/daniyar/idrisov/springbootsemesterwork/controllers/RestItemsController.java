package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CategoryDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest.RestCategoriesService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest.RestCommentService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest.RestItemsService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest.RestOrdersService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class RestItemsController {

    @Autowired
    private RestItemsService itemsService;

    @Autowired
    private RestOrdersService ordersService;

    @Autowired
    private RestCategoriesService categoryService;

    @Autowired
    private RestCommentService commentService;

    @PermitAll
    @ApiOperation(value = "Получение всех продуктов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItems() {
        return ResponseEntity.ok(itemsService.getAllItems());
    }

    @ApiOperation(value = "Получение определенного продуктов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @GetMapping("/items/{item-id}")
    @ResponseBody
    public ResponseEntity<ItemDto> getItemById(@PathVariable("item-id") Long itemId) {
        return ResponseEntity.ok(itemsService.getById(itemId));
    }

    @ApiOperation(value = "Добавление продукта")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = ItemDto.class)})
    @PostMapping("/items")
    @ResponseBody
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemsService.addItem(itemDto));
    }

    @ApiOperation(value = "Изменение продукта")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно изменено", response = ItemDto.class)})
    @PatchMapping("/items/{item-id}")
    @ResponseBody
    public ResponseEntity<ItemDto> updateItem(@PathVariable("item-id") Long itemId, @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemsService.updateById(itemId, itemDto));
    }

    @ApiOperation(value = "Удаление продукта")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно удалено")})
    @DeleteMapping("/items/{item-id}")
    @ResponseBody
    public ResponseEntity<?> deleteItem(@PathVariable("item-id") Long itemId) {
        itemsService.deleteById(itemId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Получение всех заказов по определенному продукту")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @GetMapping("/items/{item-id}/orders")
    @ResponseBody
    public ResponseEntity<List<OrderDto>> getOrdersByItemId(@PathVariable("item-id") Long itemId) {
        return ResponseEntity.ok(ordersService.getOrdersByItemId(itemId));
    }

    @ApiOperation(value = "Получение категории по определенному продукту")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @GetMapping("/items/{item-id}/category")
    @ResponseBody
    public ResponseEntity<CategoryDto> getCategoryByItemId(@PathVariable("item-id") Long itemId) {
        return ResponseEntity.ok(categoryService.getCategoryByItemId(itemId));
    }

    @ApiOperation(value = "Получение категории по определенному продукту")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @GetMapping("/items/{item-id}/comments")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> getCommentsByItemId(@PathVariable("item-id") Long itemId) {
        return ResponseEntity.ok(commentService.getCommentByItemId(itemId));
    }

}
