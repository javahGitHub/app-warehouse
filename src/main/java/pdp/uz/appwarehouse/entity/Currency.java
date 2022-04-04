package pdp.uz.appwarehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.appwarehouse.entity.templete.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Currency extends AbsEntity {

}
