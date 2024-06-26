import qrcode

def generate_qr_code(url, file_name):
    # Generate QR code instance
    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=10,
        border=4,
    )
    
    # Add URL to QR code instance
    qr.add_data(url)
    qr.make(fit=True)
    
    # Generate QR code image
    img = qr.make_image(fill='black', back_color='white')
    
    # Save QR code image to a file
    img.save(file_name)

if __name__ == '__main__':
    url = input("Enter the URL for QR code generation: ")
    file_name = input("Enter the file name for the QR code image (e.g., qr_code.png): ")
    
    generate_qr_code(url, file_name)
    
    print(f"QR code saved as {file_name}")
