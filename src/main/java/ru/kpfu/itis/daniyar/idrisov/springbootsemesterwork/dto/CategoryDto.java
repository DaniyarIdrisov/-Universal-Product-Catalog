package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Category;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private Long id;

    private String categoryName;

    public static CategoryDto fromModel(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    public static List<CategoryDto> fromModel(List<Category> categories) {
        return categories.stream()
                .map(CategoryDto::fromModel)
                .collect(Collectors.toList());
    }

}
