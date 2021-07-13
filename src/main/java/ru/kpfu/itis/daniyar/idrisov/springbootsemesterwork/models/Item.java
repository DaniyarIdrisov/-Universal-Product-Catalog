package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String description;

    @Column(name = "filename")
    private String filename;

    private Double price;

    @ManyToMany(mappedBy = "items")
    List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<Comment> comments;

    public static Item fromDto(ItemDto itemDto) {
        if (itemDto == null) {
            return null;
        }
        return Item.builder()
                .id(itemDto.getId())
                .label(itemDto.getLabel())
                .description(itemDto.getDescription())
                .filename(itemDto.getFilename())
                .price(itemDto.getPrice())
                .build();
    }

    public static List<Item> fromDto(List<ItemDto> itemDtoList) {
        return itemDtoList.stream()
                .map(Item::fromDto)
                .collect(Collectors.toList());
    }

}
