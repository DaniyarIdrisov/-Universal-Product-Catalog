package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

}
