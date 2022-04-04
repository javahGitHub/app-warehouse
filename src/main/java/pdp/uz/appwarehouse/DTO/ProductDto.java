package pdp.uz.appwarehouse.DTO;

import lombok.Data;
import pdp.uz.appwarehouse.entity.Attachment;
import pdp.uz.appwarehouse.entity.Category;
import pdp.uz.appwarehouse.entity.Currency;
import pdp.uz.appwarehouse.entity.Measurement;

import javax.persistence.*;
@Data
public class ProductDto {

    private String name;

    private int categoryId;

    private int  photoId;

    private int measurementId;

}
