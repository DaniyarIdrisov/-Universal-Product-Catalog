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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sum_price")
    private Double sumPrice;

    @Column(name = "order_time")
    private String orderTime;

    private String address;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "order_items",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
