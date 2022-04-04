package pdp.uz.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.appwarehouse.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByPassword(String password);
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select * from worker w join worker_warehouse_list wwl on w.id=wwl.worker_id where wwl.warehouse_id=:id ",nativeQuery = true)
    Page getAllByWareHouseId(Pageable pageable, int id);

}
