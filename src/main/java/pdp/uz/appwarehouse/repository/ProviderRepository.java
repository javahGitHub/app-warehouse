package pdp.uz.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
