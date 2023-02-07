package uz.ilm.resourceservice.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.ilm.resourceservice.entity.Resource;
import uz.ilm.resourceservice.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourceController {
  private ResourceService resourceService;

  @PostMapping
  public ResponseEntity<Resource> saveResource(@RequestParam(value = "file") MultipartFile file) {

    Resource saveResource = resourceService.saveResource(file);
    return new ResponseEntity<>(saveResource, HttpStatus.CREATED);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Resource> getResourceById(@PathVariable("id") Long resourceId) {
    Resource resource = resourceService.getResourceById(resourceId);
    return ResponseEntity.ok(resource);
  }

  @GetMapping("{id}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") Long resourceId) {
    byte[] data = resourceService.downloadFileById(resourceId);
    ByteArrayResource resource = new ByteArrayResource(data);
    return ResponseEntity.ok()
        .contentLength(data.length)
        .header("Content-type", "application/octet-stream")
        .header(
            "Content-disposition",
            "attachment; fileName=\""
                + resourceService.getResourceById(resourceId).getFileName()
                + "\"")
        .body(resource);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteFile(@PathVariable("id") Long resourceId){
    return new ResponseEntity<>(resourceService.deleteFileById(resourceId), HttpStatus.OK);
  }
}
