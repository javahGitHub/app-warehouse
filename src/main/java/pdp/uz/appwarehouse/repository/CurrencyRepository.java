package pdp.uz.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.entity.Currency;


public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByNames(String name);
}
