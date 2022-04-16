package com.example.awsimageupload.profile;

import com.example.awsimageupload.bucket.BucketName;
import com.example.awsimageupload.filestore.FileStore;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;
    private final UserProfile profile;

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileID, MultipartFile file) {
        // 1. check image is empty
        isFileEmpty(file);
        // 2. if file is an image
        isImage(file);  //use ctrl alt M for extract as Method
        // 3. the user exist in out database
        UserProfile user = getUserProfileOrThrow(userProfileID);
        // 4. grab some metadata from file if any
        Map<String, String> metaData = extractMetaData(file);
        // 5. store the image in s3 and update database (userProfileImageLink) with s3 img link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),user.getId());
        String fileName = String.format("%s-%s",file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path,fileName,Optional.of(metaData),file.getInputStream());
            profile.setUserProfileImageLink(fileName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize())); // valueOf convert integer to string
        return metaData;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileID) {
        return userProfileDataAccessService.getUserProfiles().stream().filter(userProfile ->
                        userProfile.getId().equals(userProfileID))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User Profile %s not found", userProfileID)));
    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), //get the actual string
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot Upload Empty File [ " + file.getSize() +" ]");
        }
    }
}
