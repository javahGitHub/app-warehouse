package pdp.uz.appwarehouse.repository;

import org.apache.coyote.ajp.AjpAprProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.appwarehouse.entity.Attachment;

@Repository
public interface AttachmentRepository   extends JpaRepository<Attachment,Integer> {
}
