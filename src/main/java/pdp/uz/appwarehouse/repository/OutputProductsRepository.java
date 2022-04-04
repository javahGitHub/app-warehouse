package pdp.uz.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.appwarehouse.entity.OutputProducts;

@Repository
public interface OutputProductsRepository extends JpaRepository<OutputProducts,Integer> {
    Page findAllByOutputId(int id, Pageable pageable);
}
