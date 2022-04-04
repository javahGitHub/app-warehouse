package pdp.uz.appwarehouse.DTO;

import lombok.Data;
import pdp.uz.appwarehouse.entity.Input;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.entity.Product;

import javax.persistence.*;
import java.sql.Date;

@Data
public class InputProductsDto {

    private int id;

    private Integer productId;
    private Integer measurementId;
    private double amount;
    private double price;
    private String expireDate;//Format{"dd/MM/yy"}
    private Integer inputId;
}
