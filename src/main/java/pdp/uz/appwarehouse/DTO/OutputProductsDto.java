package pdp.uz.appwarehouse.DTO;


import lombok.Data;

@Data
public class OutputProductsDto {

    private Integer productId;
    private Integer measurementId;
    private double amount;
    private double price;
    private Integer outputId;

}
