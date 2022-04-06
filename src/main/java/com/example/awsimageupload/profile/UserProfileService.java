package com.example.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileID, MultipartFile file) {
        // 1. check image is empty
        // 2. if file is an image
        // 3. the user exist in out database
        // 4. grab some metadata from file if any
        // 5. store the image in s3 and update database (userProfileImageLink) with s3 img link
    }
}
