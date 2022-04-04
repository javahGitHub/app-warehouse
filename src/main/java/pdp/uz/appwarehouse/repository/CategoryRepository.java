package pdp.uz.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.appwarehouse.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
