package com.medisphere.doctor.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    /**
     * Uploads a doctor profile image to Cloudinary under the "medisphere/doctors" folder.
     *
     * @param file the image file to upload
     * @return the secure URL of the uploaded image
     * @throws IOException if the upload fails
     */
    public String uploadProfileImage(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "medisphere/doctors",
                        "resource_type", "image"
                )
        );
        return (String) uploadResult.get("secure_url");
    }

    /**
     * Deletes a profile image from Cloudinary using its URL.
     *
     * @param imageUrl the Cloudinary URL of the resource to delete
     */
    public void deleteImage(String imageUrl) {
        try {
            if (imageUrl == null || imageUrl.isEmpty()) return;
            String publicId = extractPublicId(imageUrl);
            if (publicId != null) {
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            }
        } catch (Exception e) {
            System.err.println("Failed to delete from Cloudinary: " + e.getMessage());
        }
    }

    /**
     * Extracts the public ID from a Cloudinary URL.
     */
    private String extractPublicId(String url) {
        try {
            int uploadIndex = url.indexOf("/upload/");
            if (uploadIndex == -1) return null;
            String afterUpload = url.substring(uploadIndex + 8);
            int firstSlash = afterUpload.indexOf("/");
            String publicIdWithExtension = afterUpload.substring(firstSlash + 1);
            int lastDot = publicIdWithExtension.lastIndexOf(".");
            if (lastDot == -1) return publicIdWithExtension;
            return publicIdWithExtension.substring(0, lastDot);
        } catch (Exception e) {
            return null;
        }
    }
}
