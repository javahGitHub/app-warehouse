package pdp.uz.appwarehouse.DTO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class IndividualDto {

    private String name;
    private boolean active=true;
    private String phoneNumber;
}
