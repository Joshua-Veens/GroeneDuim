package nl.devlieren.models;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GenerateQRCode {

    private static final String QR_CODES_FOLDER = "/opt/tomcat/qrcodes/";

    public static void generateQRCode(String plantName) {
        String url = "http://113.30.188.22:8080/index.html?id=" + plantName;

        // Generate QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200, hints);

            File folder = new File(QR_CODES_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Save QR code to file
            Path qrCodePath = Paths.get(QR_CODES_FOLDER, plantName + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodePath);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] generateZipFile() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        File qrCodesDirectory = new File(QR_CODES_FOLDER);
        File[] qrCodeFiles = qrCodesDirectory.listFiles();

        if (qrCodeFiles != null) {
            for (File qrCodeFile : qrCodeFiles) {
                if (qrCodeFile.isFile()) {
                    String fileName = qrCodeFile.getName();
                    Path qrCodePath = qrCodeFile.toPath();

                    zos.putNextEntry(new ZipEntry(fileName));
                    Files.copy(qrCodePath, zos);
                    zos.closeEntry();
                }
            }
        }

        zos.close();
        return baos.toByteArray();
    }
}
