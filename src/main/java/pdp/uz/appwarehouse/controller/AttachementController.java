package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachement")
public class AttachementController {

    @Autowired
    AttachmentService attachmentService;


    //Upload Attachement
    @PostMapping("/upload")
    public Result uploadController(MultipartHttpServletRequest request){
        Result result = attachmentService.uploadAttachement(request);
        return result;
    }

    //Download Attachement
    @GetMapping("/download/{id}")
    public void uploadController(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        attachmentService.downloadAttachement(response,id);
    }
}
