package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Category;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Order;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.CategoriesRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.OrdersRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto.fromModel;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Value("${file.path}")
    private String filePath;

    @Override
    public List<ItemDto> getAllItems() {
        return ItemDto.fromModel(itemsRepository.findAll());
    }

    @Override
    public ItemDto getItemById(Long id) {
        return fromModel(itemsRepository.findById(id).orElse(null));
    }

    @Override
    public void addItem(ItemDto itemDto, Long categoryId, Double price, String filename) {
        Category category = categoriesRepository.findById(categoryId).orElse(null);
        Item item = Item.builder()
                .label(itemDto.getLabel())
                .description(itemDto.getDescription())
                .filename(filename)
                .price(price)
                .category(category)
                .build();
        itemsRepository.save(item);
    }

    @SneakyThrows
    @Override
    public String uploadFile(MultipartFile file) {
        if (file != null && file.getBytes().length != 0) {
            String filename = UUID.randomUUID() + ".png";
            File convertFile = new File(filePath + filename);
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            return filename;
        }
        else {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public void deleteItem(Long itemId) {
        Item item = itemsRepository.findById(itemId).orElse(null);
        Files.delete(Paths.get(filePath + item.getFilename()));
        itemsRepository.deleteById(itemId);
    }

    @SneakyThrows
    @Override
    public void updateItem(Long itemId, ItemDto itemDto, Long categoryId, String priceString, MultipartFile file) {
        Item item = itemsRepository.findById(itemId).orElse(null);
        if (!itemDto.getLabel().equals("")) {
            item.setLabel(itemDto.getLabel());
        }
        if (!itemDto.getDescription().equals("")) {
            item.setDescription(itemDto.getDescription());
        }
        if (categoryId != -1) {
            Category category = categoriesRepository.findById(categoryId).orElse(null);
            item.setCategory(category);
        }
        Double price = conversionService.convert(priceString, Double.class);
        if (price != null) {
            item.setPrice(price);
        }
        String oldFilename = item.getFilename();
        String newFilename = uploadFile(file);
        if (newFilename != null) {
            Files.delete(Paths.get(filePath + oldFilename));
            item.setFilename(newFilename);
        }
        itemsRepository.save(item);
    }

    @Override
    public List<ItemDto> getItemsByCategoryId(Long categoryId) {
        Category category = categoriesRepository.findById(categoryId).orElse(null);
        List<Item> categories = category.getItems();
        for (Item item:categories) {
            System.out.println(item.getLabel());
        }
        return fromModel(itemsRepository.findAllByCategory(category));
    }

    @Override
    public List<ItemDto> getItemsByQuery(String query) {
        return fromModel(itemsRepository.findAllByLabelContains(query));
    }

    @Override
    public boolean hasChildren(Long itemId) {
        Item item = itemsRepository.findById(itemId).orElse(null);
        assert item != null;
        if (!item.getComments().isEmpty() || !item.getOrders().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public List<ItemDto> getAllByOrder(Long orderId) {
        Order order = ordersRepository.findById(orderId).orElse(null);
        return fromModel(itemsRepository.findAllByOrdersContains(order));
    }

    @Override
    public List<ItemDto> getItemsByQueryAndCategoryId(String query, Long categoryId) {
        return fromModel(itemsRepository.findAllByLabelContainsAndAndCategory_Id(query, categoryId));
    }

}
