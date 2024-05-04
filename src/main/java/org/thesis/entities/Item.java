package org.thesis.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "ITEM_BY_SHOPPING_LIST",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"}))
@Getter @Setter @NoArgsConstructor
public class Item {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOPPING_LIST_ID", referencedColumnName = "ID", nullable = false)
    private ShoppingList shoppingListId;
}
