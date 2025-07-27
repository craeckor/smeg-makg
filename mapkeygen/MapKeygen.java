package mapkeygen;

import Twofish.Twofish_Algorithm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;

public class MapKeygen {
  private static final String map = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ0123456789ABCDEFGHJKLMNPQRSTUV";
  
  private static final byte[] init_vector = new byte[] { 
      -83, 44, -122, -99, -8, -54, -85, 85, 17, 42, 
      30, -126, -103, 108, 118, 107 };
  
  private static byte[] encrypt(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) throws InvalidKeyException {
    Object object = Twofish_Algorithm.makeKey(paramArrayOfbyte2);
    byte[] arrayOfByte = Twofish_Algorithm.blockEncrypt(paramArrayOfbyte1, 0, object);
    for (byte b = 0; b < 16; b++)
      arrayOfByte[b] = (byte)(paramArrayOfbyte2[b] ^ arrayOfByte[b]); 
    return arrayOfByte;
  }
  
  public static String generate(File paramFile, String paramString) throws IOException, InvalidKeyException {
    byte[] arrayOfByte1 = new byte[9];
    byte[] arrayOfByte2 = new byte[12];
    byte[] arrayOfByte3 = new byte[17];
    byte[] arrayOfByte4 = new byte[33];
    byte[] arrayOfByte5 = new byte[16];
    int i = 0;
    if (paramString.length() < 17)
      throw new RuntimeException("VIN too short"); 
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    try {
      fileInputStream.read(arrayOfByte1, 0, 8);
    } finally {
      fileInputStream.close();
    } 
    arrayOfByte1[8] = 0;
    for (byte b1 = 0; b1 < 9; b1++) {
      byte b = paramString.getBytes()[b1 + 8];
      arrayOfByte2[b1] = b;
      i += b;
    } 
    i = (i ^ 0xFFFFFFFF) + 1 & 0xFF;
    char[] arrayOfChar = String.format("%02d", new Object[] { Integer.valueOf(i) }).toCharArray();
    arrayOfByte2[10] = (byte)arrayOfChar[0];
    arrayOfByte2[11] = (byte)arrayOfChar[1];
    System.out.println("map_code=" + new String(arrayOfByte1));
    System.out.println("vin_code=" + new String(arrayOfByte2));
    byte b2;
    for (b2 = 0; b2 < 9; b2++) {
      arrayOfByte4[2 * b2] = arrayOfByte2[b2];
      arrayOfByte4[2 * b2 + 1] = arrayOfByte2[b2];
    } 
    for (b2 = 0; b2 < 7; b2++) {
      arrayOfByte4[18 + 2 * b2] = arrayOfByte1[b2];
      arrayOfByte4[18 + 2 * b2 + 1] = arrayOfByte1[b2];
    } 
    arrayOfByte4[31] = arrayOfByte1[7];
    arrayOfByte4[32] = 0;
    for (b2 = 0; b2 < 16; b2++)
      arrayOfByte5[b2] = init_vector[b2]; 
    byte[] arrayOfByte6 = encrypt(arrayOfByte4, arrayOfByte5);
    for (b2 = 0; b2 < 16; b2++) {
      arrayOfByte5[b2] = arrayOfByte6[b2];
      arrayOfByte4[b2] = arrayOfByte4[b2 + 16];
    } 
    arrayOfByte6 = encrypt(arrayOfByte4, arrayOfByte5);
    for (b2 = 0; b2 < 16; b2++)
      arrayOfByte3[b2] = (byte)"0123456789ABCDEFGHJKLMNPQRSTUVWXYZ0123456789ABCDEFGHJKLMNPQRSTUV".charAt(arrayOfByte6[b2] >> 1 & 0x3F); 
    arrayOfByte3[16] = 0;
    System.out.println("Activation key: " + new String(arrayOfByte3));
    return new String(arrayOfByte3);
  }
  
  public static void main(String[] paramArrayOfString) {}
}


/* Location:              C:\Users\Linus\Downloads\generator.jar!\mapkeygen\MapKeygen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */