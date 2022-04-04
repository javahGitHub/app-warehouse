package pdp.uz.appwarehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.appwarehouse.entity.templete.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AbsEntity {
    @ManyToOne(optional = false)
    private Category category;

    @OneToOne(cascade = {CascadeType.ALL})
    private Attachment photo;

    private String code;

    @ManyToOne(optional = false)
    private Measurement measurement;

}
