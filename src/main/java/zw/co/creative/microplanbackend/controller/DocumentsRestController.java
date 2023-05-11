package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.DocumentUploadDto;
import zw.co.creative.microplanbackend.service.DocumentsUploadsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/documents")
@Slf4j
public class DocumentsRestController {
    DocumentsUploadsService documentsUploadsService;
    @PostMapping(value ="/uploadFiles", consumes={"multipart/form-data"})
    public CommonResponse uploadFile(@RequestParam("files") MultipartFile files){
        log.info("Received Documents size-----: {}",files.length);
        return documentsUploadsService.storeFiles(files);
    }
}
