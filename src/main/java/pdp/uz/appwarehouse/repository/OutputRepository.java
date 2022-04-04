package pdp.uz.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {


    Page findAllByWareHouseId(int id, Pageable pageable);

}
