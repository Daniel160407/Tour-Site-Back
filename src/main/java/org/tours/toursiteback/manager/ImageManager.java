package org.tours.toursiteback.manager;

import jakarta.servlet.http.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ImageManager {
    public static void imageDecoder(String path, Part filePart, String fileName) throws IOException {
        InputStream fileContent = filePart.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(path + fileName);
        int read;
        final byte[] bytes = new byte[1024];
        while ((read = fileContent.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, read);
        }
        fileOutputStream.close();
        fileContent.close();
    }

    public static List<String> imageEncoder(String path) throws IOException {
        List<String> base64Images = new ArrayList<>();
        File[] files = new File(path).listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fileInputStream.read(data);
                fileInputStream.close();
                String base64Image = Base64.getEncoder().encodeToString(data);
                base64Images.add(base64Image);
            }
        }

        return base64Images;
    }

    public static byte[] getImageAsBytesFromBase64(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }
}
