package pdp.uz.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.controller.WareHouseController;
import pdp.uz.appwarehouse.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse,Integer> {
    boolean existsByNames(String name);
}
