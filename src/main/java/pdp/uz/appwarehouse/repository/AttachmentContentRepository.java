package pdp.uz.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appwarehouse.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
