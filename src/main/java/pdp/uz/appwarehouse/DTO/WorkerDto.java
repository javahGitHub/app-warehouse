package pdp.uz.appwarehouse.DTO;

import lombok.Data;
import pdp.uz.appwarehouse.entity.WareHouse;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
public class WorkerDto{

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String position;
    private String password;
    private boolean active;
    private List<Integer> wareHousesId;

}