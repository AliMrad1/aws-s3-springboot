package com.example.awsimageupload.datastore;

import com.example.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("cc55286a-77cc-4a10-ac82-3f7f5235405b"), "alimrad", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("7a021ee7-56cb-49b7-add7-ee9b02ba6d09"), "amirmrad", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("2226e602-96a3-49d6-90e9-2bdb5535f28f"), "husseinmrad", null));
    }

    public List<UserProfile> getUserProfiles(){
        return  USER_PROFILES;
    }
}
