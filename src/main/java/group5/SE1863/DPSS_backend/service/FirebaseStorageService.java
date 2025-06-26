package group5.SE1863.DPSS_backend.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FirebaseStorageService {

    @Value("${path.serviceAccountKey}")
    private String serviceAccountKeyPath;

    public String uploadFile(MultipartFile file, String fileName) throws IOException {

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath)))
                .build()
                .getService();
        String bucketName = "dpss-b2b3e.appspot.com";
        String objectName = fileName + file.getOriginalFilename();
        String encodedObjectName = java.net.URLEncoder.encode(objectName, StandardCharsets.UTF_8);

        System.out.println("Uploading to bucket: " + bucketName);
        System.out.println("File path: " + objectName);
        System.out.println(fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, objectName)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getBytes());
        String imageUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, encodedObjectName);
        System.out.println("Generated image URL: " + imageUrl);
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, encodedObjectName);
    }

    public void deleteFile(String imageUrl) throws IOException {
        // Check if the imageUrl is not null or empty
        if (imageUrl == null || imageUrl.isEmpty()) {
            System.out.println("No file to delete.");
            return;
        }

        // Extract the object name from the full Firebase Storage URL
        String bucketName = "dpss-b2b3e.appspot.com";
        String objectName = imageUrl.substring(imageUrl.indexOf("/o/") + 3, imageUrl.indexOf("?alt="));
        objectName = java.net.URLDecoder.decode(objectName, StandardCharsets.UTF_8);

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath)))
                .build()
                .getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        boolean deleted = storage.delete(blobId);
        if (deleted) {
            System.out.println("File deleted successfully: " + objectName);
        } else {
            System.out.println("File not found or could not be deleted: " + objectName);
        }
    }
}
