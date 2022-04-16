package com.example.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileStore {

    private final AmazonS3 s3;

    public void save(String path , String fileName, Optional<Map<String ,String>> metaDataMap, InputStream inputStream){

        ObjectMetadata metaData = new ObjectMetadata();
        metaDataMap.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach(metaData::addUserMetadata); // replace by method reference
            }
        });
        try {
            s3.putObject(path, fileName, inputStream, metaData);
        } catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store data in Amazon S3",e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object s3Object = s3.getObject(path, key);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        }catch (AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to Download file to s3",e);
        }
    }
}
