package uz.pdp.task2.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.entity.AttachmentContent;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.repository.AttachmentContentRepository;
import uz.pdp.task2.repository.AttachmentRepository;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows

    // CREATE
    public Result uploadFile(MultipartHttpServletRequest request){

         Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment=new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File saved",true,savedAttachment.getId());
    }

    // GET All
    public List<Attachment> infoFiles(){
        List<Attachment> attachments = attachmentRepository.findAll();
        return attachments;
    }

    // GET BY ID
    public Attachment infoFileById(@PathVariable Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            return attachment;
        }   return new Attachment();
    }


    // GET BY ID
    @GetMapping("/download/{id}")
    public Attachment downloadFileById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
    Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
    if (optionalAttachment.isPresent()) {
         Attachment attachment = optionalAttachment.get();

         Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
         if (contentOptional.isPresent()) {
             AttachmentContent attachmentContent = contentOptional.get();
             response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName()+ "\"");
             response.setContentType(attachment.getContentType());
             FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
         }
     }
        return new Attachment();
   }


    // DELETE BY ID
    public boolean deleteFile(Integer id) {
        try {
            attachmentContentRepository.deleteById(id);
            attachmentRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
}
}
