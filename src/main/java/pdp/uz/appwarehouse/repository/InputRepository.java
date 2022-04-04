package pdp.uz.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input,Integer> {
    Page findAllByWareHouseId(Integer whId, Pageable pageable);
}
