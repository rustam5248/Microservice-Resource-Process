package uz.epam.resourceservice.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import uz.epam.resourceservice.entity.Resource;

public interface ResourceService {
  Resource saveResource(MultipartFile file);

  Resource getResourceById(Long resourceId);

  byte[] downloadFileById(Long songId);

  String deleteFileById(Long songId);

}
