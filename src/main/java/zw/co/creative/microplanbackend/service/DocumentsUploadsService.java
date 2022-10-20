package zw.co.creative.microplanbackend.service;


import org.springframework.web.multipart.MultipartFile;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.DocumentUploadDto;

import java.util.List;

public interface DocumentsUploadsService {
    CommonResponse storeFiles(MultipartFile[] files);
}
