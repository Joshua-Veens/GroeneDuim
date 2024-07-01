package nl.devlieren.webservices;

import nl.devlieren.models.GenerateQRCode;
import nl.devlieren.models.Plant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/qr-codes")
public class QRCodeResource {

    @GET
    @Produces("application/zip")
    public Response downloadQRCodes() {
        try {
            byte[] zipFileBytes = GenerateQRCode.generateZipFile();

            return Response.ok(zipFileBytes)
                    .header("Content-Disposition", "attachment; filename=\"qrcodes.zip\"")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to generate or download ZIP file")
                    .build();
        }
    }
}
