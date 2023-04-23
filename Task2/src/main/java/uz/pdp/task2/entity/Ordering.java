package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String  nameBuyer;

    @Column(nullable = false,unique = true)
    private String  phoneNumber;

    @Column(nullable = false,unique = true)
    private String  email;

    private String  details;

    private String  description;


    @OneToOne
    private Address addressOfBuyer;

    @OneToOne
    private ShoppingVenture shoppingVenture;

}
