package nl.devlieren.models;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GenerateQRCode {
    public static void generateQRCode(String plantName) {
        // URL for QR code
        String url = "http://62.45.248.146:8080/index.html?id=" + plantName;
        String folderPath = "C:/Users/joshu/OneDrive - Stichting Hogeschool Utrecht/SD Minor/IPASS/GroeneDuim/qrcodes";

        // Generate QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1); // Set margin

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Create output directory if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Write QR code to file
            Path path = FileSystems.getDefault().getPath(folderPath, plantName + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("QR code for plant '" + plantName + "' saved as " + path.toString());

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}