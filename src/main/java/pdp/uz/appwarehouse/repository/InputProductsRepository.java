package pdp.uz.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.appwarehouse.entity.InputProducts;

public interface InputProductsRepository extends JpaRepository<InputProducts,Integer> {
    @Query("select i from InputProducts i where i.input.id = ?1")
    Page findAllByInputId(int inId, Pageable pageable);
}
