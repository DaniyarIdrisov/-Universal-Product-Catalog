package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {

    private Long id;

    private String label;

    private String description;

    private String filename;

    private Double price;

    private String categoryName;

    public static ItemDto fromModel(Item item) {
        if (item == null) {
            return null;
        }
        return ItemDto.builder()
                .id(item.getId())
                .label(item.getLabel())
                .description(item.getDescription())
                .filename(item.getFilename())
                .price(item.getPrice())
                .categoryName(item.getCategory().getCategoryName())
                .build();
    }

    public static List<ItemDto> fromModel(List<Item> items) {
        return items.stream()
                .map(ItemDto::fromModel)
                .collect(Collectors.toList());
    }

}
