package uz.pdp.task2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.service.AttachmentService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/upload")
    public HttpEntity<?> upload(MultipartHttpServletRequest request){
        Result result = attachmentService.uploadFile(request);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/infos")
    public HttpEntity<?> fileInfo(){
        List<Attachment> attachments = attachmentService.infoFiles();
        return ResponseEntity.ok(attachments);
    }

    @GetMapping("/info/{id}")
    public HttpEntity<?> infoFileById(@PathVariable Integer id) {
        Attachment attachment = attachmentService.infoFileById(id);
        return ResponseEntity.status(attachment != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(attachment);
    }


     @GetMapping("/download/{id}")
    public HttpEntity<?> downloadFileById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentService.downloadFileById(id,response);
        return  ResponseEntity.status(attachment !=null ?HttpStatus.OK:HttpStatus.CONFLICT).body(attachment);
    }


    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteFile(@PathVariable Integer id){
        try {
            boolean isDeleted=attachmentService.deleteFile(id);
            return ResponseEntity.status(isDeleted?HttpStatus.OK:HttpStatus.CONFLICT).body("File deleted");
        }catch (Exception e){
        return ResponseEntity.notFound().build();
    }}


}
