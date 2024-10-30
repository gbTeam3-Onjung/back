<<<<<<< HEAD
package com.app.back.controller.attachment;

import com.app.back.domain.attachment.AttachmentDTO;
import com.app.back.domain.attachment.AttachmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/attachment/*")
@Slf4j
public class AttachmentController {

    @PostMapping("upload")
    @ResponseBody
    public AttachmentDTO upload(@RequestParam("file")List<MultipartFile> files) throws IOException {
//        String rootPath = "D:/dev/OnjungSpring/back/src/main/resources/static/files" + getPath();
        String rootPath = "C:/upload/" + getPath();
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        UUID uuid = UUID.randomUUID();

        attachmentDTO.setAttachmentFilePath(getPath());

        File directory = new File(rootPath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        for(int i=0; i<files.size(); i++){
            files.get(i).transferTo(new File(rootPath, uuid.toString() + "_" + files.get(i).getOriginalFilename()));
            attachmentDTO.setAttachmentFileName(uuid.toString() + "_" + files.get(i).getOriginalFilename());

            if(files.get(i).getContentType().startsWith("image")){
                FileOutputStream fileOutputStream = new FileOutputStream(new File(rootPath, "t_" + uuid.toString() + "_" + files.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(files.get(i).getInputStream(), fileOutputStream, 100, 100);
                fileOutputStream.close();
            }
        }

        return attachmentDTO;
    }

    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
=======
//package com.app.back.controller.attachment;
//
//import com.app.back.domain.attachment.AttachmentDTO;
//import com.app.back.domain.attachment.AttachmentVO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import net.coobird.thumbnailator.Thumbnailator;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/attachment/*")
//@Slf4j
//public class AttachmentController {
//
//    @PostMapping("upload")
//    @ResponseBody
//    public AttachmentDTO upload(@RequestParam("file")List<MultipartFile> files) throws IOException {
////        String rootPath = "D:/dev/OnjungSpring/back/src/main/resources/static/files" + getPath();
//        String rootPath = "C:/upload/" + getPath();
//        UUID uuid = UUID.randomUUID();
//
//        File directory = new File(rootPath);
//        if(!directory.exists()){
//            directory.mkdirs();
//        }
//
//        for(int i=0; i<files.size(); i++){
//            files.get(i).transferTo(new File(rootPath, files.get(i).getOriginalFilename()));
//            reviewDTO.setAttachmentFileName(uuid.toString() + "_" + files.get(i).getOriginalFilename());
//
//            if(files.get(i).getContentType().startsWith("image")){
//                FileOutputStream fileOutputStream = new FileOutputStream(new File(rootPath, "t_" + uuid.toString() + "_" + files.get(i).getOriginalFilename()));
//                Thumbnailator.createThumbnail(files.get(i).getInputStream(), fileOutputStream, 100, 100);
//                fileOutputStream.close();
//            }
//        }
//
//    }
//}
>>>>>>> 2d6aacc74750ecea2fa4a97dce10f7f587c5046e
