package pdp.uz.appwarehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.appwarehouse.entity.templete.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AbsEntity {

    @ManyToOne
    private Category parentCategory;

}
