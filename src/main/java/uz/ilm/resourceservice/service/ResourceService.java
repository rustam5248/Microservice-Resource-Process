package uz.ilm.resourceservice.service;

import org.springframework.web.multipart.MultipartFile;
import uz.ilm.resourceservice.entity.Resource;

public interface ResourceService {
  Resource saveResource(MultipartFile file);

  Resource getResourceById(Long resourceId);

  byte[] downloadFileById(Long songId);

  String deleteFileById(Long songId);

}
