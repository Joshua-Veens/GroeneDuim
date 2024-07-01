package nl.devlieren;

import com.google.zxing.WriterException;
import nl.devlieren.models.GenerateQRCode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateQRCodeTest {

    private static final String QR_CODES_FOLDER = "/opt/tomcat/qrcodes/";

    @Test
    public void testGenerateQRCode() throws IOException, WriterException {
        String plantName = "testPlant";
        GenerateQRCode.generateQRCode(plantName);

        File qrCodeFile = new File(QR_CODES_FOLDER + plantName + ".png");
        assertTrue(qrCodeFile.exists(), "QR code file should be created.");
    }

    @Test
    public void testGenerateZipFile() throws IOException, WriterException {
        // Generate a QR code file first
        String plantName = "testPlant";
        GenerateQRCode.generateQRCode(plantName);

        // Generate the ZIP file
        byte[] zipFileBytes = GenerateQRCode.generateZipFile();

        assertNotNull(zipFileBytes, "ZIP file bytes should not be null.");
        assertTrue(zipFileBytes.length > 0, "ZIP file should have content.");
    }
}
