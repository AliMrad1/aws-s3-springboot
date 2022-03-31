package com.example.awsimageupload.profile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }
}
