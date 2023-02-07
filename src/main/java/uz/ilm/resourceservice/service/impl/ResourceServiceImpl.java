package uz.ilm.resourceservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;
import uz.ilm.resourceservice.entity.Resource;
import uz.ilm.resourceservice.repository.ResourceRepository;
import uz.ilm.resourceservice.service.AmazonS3Service;
import uz.ilm.resourceservice.service.ResourceService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

  private final ResourceRepository resourceRepository;
  private final AmazonS3Service amazonS3Service;
  @Override
  public Resource saveResource(MultipartFile file) {
   Resource resource=  new Resource();
   resource.setFileName(amazonS3Service.uploadFile(file));
   return resourceRepository.save(resource);
  }

  @Override
  public Resource getResourceById(Long resourceId) {
    amazonS3Service.downloadFile(resourceRepository.findById(resourceId).get().getFileName());
    return resourceRepository.findById(resourceId).get();
  }

  @Override
  public byte[] downloadFileById(Long songId){
    return amazonS3Service.downloadFile(resourceRepository.findById(songId).get().getFileName());
  }

  @Override
  public String deleteFileById(Long songId){

    String fileName=resourceRepository.getReferenceById(songId).getFileName();
    resourceRepository.deleteById(songId);
    if(fileName!=null){
     return amazonS3Service.deleteFile(fileName);
    }else return "File not found";



  }
}
