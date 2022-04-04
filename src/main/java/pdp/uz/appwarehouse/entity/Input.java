package pdp.uz.appwarehouse.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Timestamp date;

    @ManyToOne(cascade = {CascadeType.ALL})
    private WareHouse wareHouse;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Provider provider;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Currency currency;

    @Column(nullable = false,unique = true)
    private String invoiceNumber;

    @Column(nullable = false,unique = true)
    private String code;
}
