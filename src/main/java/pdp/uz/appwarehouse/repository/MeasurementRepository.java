package pdp.uz.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByNames(String name);
}
