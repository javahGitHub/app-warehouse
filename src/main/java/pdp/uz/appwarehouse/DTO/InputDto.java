package pdp.uz.appwarehouse.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDto {

    private Timestamp date;
    private Integer wareHouseId;
    private Integer providerId;
    private Integer currencyId;
    private String invoiceNumber;
    private String code;

}
