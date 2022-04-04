package pdp.uz.appwarehouse.entity;

import lombok.Data;
import pdp.uz.appwarehouse.entity.templete.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Client extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String phoneNumber;
}
