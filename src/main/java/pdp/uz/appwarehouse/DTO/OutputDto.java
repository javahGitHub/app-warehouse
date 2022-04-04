package pdp.uz.appwarehouse.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {

    private Timestamp date;
    private Integer wareHouseId;
    private Integer clientId;
    private Integer currencyId;
    private String invoiceNumber;
    private String code;


}
