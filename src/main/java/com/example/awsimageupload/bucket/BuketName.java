package com.example.awsimageupload.bucket;

public enum BuketName {

    PROFILE_IMAGE("mystorage0101");

    private final String bucketName;

    BuketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
