package Company.Drive;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

@Service
public class GoogleDriveService {

    public String uploadFile(java.io.File filePath, String mimeType) throws IOException, GeneralSecurityException {
        Drive driveService = GoogleDriveConfig.getDriveService();
        
        File fileMetadata = new File();
        fileMetadata.setName(filePath.getName());
        
        FileContent mediaContent = new FileContent(mimeType, filePath);
        File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        
        return "File uploaded successfully. File ID: " + uploadedFile.getId();
    }
}

