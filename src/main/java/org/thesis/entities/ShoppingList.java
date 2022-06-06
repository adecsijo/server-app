package org.thesis.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_LIST")
@Getter @Setter @NoArgsConstructor
public class ShoppingList {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHOPPING_LIST_ID", referencedColumnName = "ID")
    private ShoppingListByUser shoppingListByUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private Item item;

    @Column(name = "ITEM_COUNT", nullable = false)
    private Double itemCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UNIT_ID", referencedColumnName = "ID")
    private Unit unit;
}
