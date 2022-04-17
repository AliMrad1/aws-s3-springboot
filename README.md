
# aws-image-upload

In this Project , I use some important topic for upload Image from React Client App to Springboot backend application and store it into Amazon S3 bucket, as well I get
Images back from Amazon S3 for displayed them in the react app, by the way its a simple fullstack App ,so lets explain what app make for us, first store some information
in the database (real or mock), id and username and image, and show  up in the react app.
lets jump to explain what happen in the background I mean in
the code place.

# Getting Started
### Frontend Side:
-App.js: I use axios for make an http request to the server , I use multiple request method GET AND POST.
* GET->to get all information about all users (id and usernmae)
* [react-dropzone](https://react-dropzone.js.org/): its a react component to drag any file and drop it, i use this to drag image and drop above it , in this moment
  I make a POST to pass this image to backend with user id  and store it into S3 bucket;
* when i refresh page , I make another GET Request to download this images to each specific user according to ID and displayed in the img tag.

### backend side:
* Amazon Configuration(AmazonConfig.java): I make a credential with AWS accroding to accesskey and secretkey.
* Bucket Details(BucketName.java): contain details like bucket name .
* File Service(FileStore.java) : have two method inside this class , first method to save image in the S3 bucket
  and second method to download image from S3 bucket
* Class Model(UserProfile.java): specific the type of data you would to use (id, username, imgLink).
* Dao (UserProfileDataAccessService.java) : interact directly with database and get all userprofiles.
* User Profile Service(UserProfileService.java) : the business logic implement is:
                                                  -get the metadata of image
                                                  -check user if exist in database
                                                  -check if the file uploaded is image or not
                                                  -check the file uploaded is empty
                                                  and use the whole this for upload real image in S3,and
                                                  in opposite side for download real image.
* User Controller(UserProfileController.java): in post endpoint link , I capture the user id and the image sent from this id.
                                               in get endpoint link , I capture the user id to get the image for this specific id.
* application.properties: specific the max size can be sent to this application.

### aws side
*create a bucket inside S3 service and the name of her into Bucket Details Class.
*go to security credentials and generate access and secret key.

###Happy Coding , and GOOD LUCK!



