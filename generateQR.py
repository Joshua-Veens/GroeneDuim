import qrcode
import urllib.parse
import os
import sys

def generate_qr_code(url, folder_path):
    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=10,
        border=4,
    )
    
    qr.add_data(url)
    qr.make(fit=True)
    
    img = qr.make_image(fill='black', back_color='white')
    
    if not os.path.exists(folder_path):
        os.makedirs(folder_path)
    
    parsed_url = urllib.parse.urlparse(url)
    query = parsed_url.query
    query_params = urllib.parse.parse_qs(query)
    plant_name = query_params.get('id', [''])[0]
    file_name = os.path.join(folder_path, f"{plant_name}.png")
    
    # Save QR code image to a file
    img.save(file_name)
    
    return file_name

if __name__ == '__main__':
    if len(sys.argv) < 2:
        print("Usage: python generate_qr.py <plant_name>")
        sys.exit(1)
        
    url = "http://62.45.248.146:8080/index.html?id=" + sys.argv[1]
    
    folder_path = "./qrcodes"  # Specify the folder where QR codes should be saved
    
    file_name = generate_qr_code(url, folder_path)
    print(f"QR code for plant saved as {file_name}")
