package pdp.uz.appwarehouse.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private byte[] bytes;

    @OneToOne
    private Attachment attachment;

}
