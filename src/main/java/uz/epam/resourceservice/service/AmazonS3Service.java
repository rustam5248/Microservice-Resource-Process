package uz.epam.resourceservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class AmazonS3Service {

  @Value("${aws.s3.bucket.name}")
  private String bucketName;

  @Autowired
  private AmazonS3 s3Client;

  public String uploadFile(MultipartFile file){
    File fileObject=convertMultiPartToFileFormat(file);
    String fileName=String.format("%s_%s",System.currentTimeMillis(), file.getOriginalFilename());
    s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObject));
    fileObject.delete();
    return String.format(fileName);

  }

  public byte[] downloadFile(String fileName){
    S3Object s3Object=s3Client.getObject(bucketName,fileName);
    try(S3ObjectInputStream inputStreams=s3Object.getObjectContent()){
      byte[] content= IOUtils.toByteArray(inputStreams);
      return content;
    }catch (IOException e){
      e.printStackTrace();
    }
    return  null;
  }

  public String deleteFile(String fileName){
    s3Client.deleteObject(bucketName,fileName);

    return String.format("%s removed ...", fileName);
  }
  private File convertMultiPartToFileFormat(MultipartFile file){
    File convertFile = new File(file.getOriginalFilename());
    try(FileOutputStream fos=new FileOutputStream(convertFile)){
      fos.write(file.getBytes());
    }catch (IOException e){
      log.error("Error converting multipartFile to file", e);
    }
      return convertFile;
  }
}
