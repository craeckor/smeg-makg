package Twofish;

import java.security.InvalidKeyException;

public final class Twofish_Algorithm {
  static final String NAME = "Twofish_Algorithm";
  
  static final boolean IN = true;
  
  static final boolean OUT = false;
  
  static final int BLOCK_SIZE = 16;
  
  private static final int ROUNDS = 16;
  
  private static final int MAX_ROUNDS = 16;
  
  private static final int INPUT_WHITEN = 0;
  
  private static final int OUTPUT_WHITEN = 4;
  
  private static final int ROUND_SUBKEYS = 8;
  
  private static final int TOTAL_SUBKEYS = 40;
  
  private static final int SK_STEP = 33686018;
  
  private static final int SK_BUMP = 16843009;
  
  private static final int SK_ROTL = 9;
  
  private static final byte[][] P = new byte[][] { { 
        -87, 103, -77, -24, 4, -3, -93, 118, -102, -110, 
        Byte.MIN_VALUE, 120, -28, -35, -47, 56, 13, -58, 53, -104, 
        24, -9, -20, 108, 67, 117, 55, 38, -6, 19, 
        -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 
        25, 91, 61, 89, -13, -82, -94, -126, 99, 1, 
        -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 
        22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 
        37, 11, -69, 78, -119, 107, 83, 106, -76, -15, 
        -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 
        3, 86, -44, 28, 30, -41, -5, -61, -114, -75, 
        -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 
        98, 113, -127, 121, 9, -83, 36, -51, -7, -40, 
        -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, 
        -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 
        49, -62, 39, -112, 32, -10, 96, -1, -106, 92, 
        -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, 
        -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 
        109, 70, -42, 62, 105, 100, 42, -50, -53, 47, 
        -4, -105, 5, 122, -84, Byte.MAX_VALUE, -43, 26, 75, 14, 
        -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, 
        -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, 
        -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 
        88, 7, -103, 52, 110, 80, -34, 104, 101, -68, 
        -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, 
        -54, 16, 33, -16, -45, 93, 15, 111, -99, 54, 
        66, 74, 94, -63, -32 }, { 
        117, -13, -58, -12, -37, 123, -5, -56, 74, -45, 
        -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 
        55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 
        6, 63, 94, -70, -82, 91, -118, -68, -99, 109, 
        -63, -79, 14, Byte.MIN_VALUE, 93, -46, -43, -96, -124, 7, 
        20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 
        116, 54, 81, 56, -80, -67, 90, -4, 96, 98, 
        -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, 
        -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, 
        -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 
        119, -61, -52, 3, 111, 8, -65, 64, -25, 43, 
        -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, 
        -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 
        29, 61, -16, -34, -77, 11, 114, -89, 28, -17, 
        -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 
        73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 
        57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 
        31, 78, 45, -7, 72, 79, -14, 101, -114, 120, 
        92, 88, 25, -115, -27, -104, 87, 103, Byte.MAX_VALUE, 5, 
        100, -81, 99, -74, -2, -11, -73, 60, -91, -50, 
        -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 
        21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 
        106, -49, -36, 34, -55, -64, -101, -119, -44, -19, 
        -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 
        97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 
        86, 85, 9, -66, -111 } };
  
  private static final int P_00 = 1;
  
  private static final int P_01 = 0;
  
  private static final int P_02 = 0;
  
  private static final int P_03 = 1;
  
  private static final int P_04 = 1;
  
  private static final int P_10 = 0;
  
  private static final int P_11 = 0;
  
  private static final int P_12 = 1;
  
  private static final int P_13 = 1;
  
  private static final int P_14 = 0;
  
  private static final int P_20 = 1;
  
  private static final int P_21 = 1;
  
  private static final int P_22 = 0;
  
  private static final int P_23 = 0;
  
  private static final int P_24 = 0;
  
  private static final int P_30 = 0;
  
  private static final int P_31 = 1;
  
  private static final int P_32 = 1;
  
  private static final int P_33 = 0;
  
  private static final int P_34 = 1;
  
  private static final int GF256_FDBK = 361;
  
  private static final int GF256_FDBK_2 = 180;
  
  private static final int GF256_FDBK_4 = 90;
  
  private static final int[][] MDS = new int[4][256];
  
  private static final int RS_GF_FDBK = 333;
  
  static {
    long l = System.currentTimeMillis();
    int[] arrayOfInt1 = new int[2];
    int[] arrayOfInt2 = new int[2];
    int[] arrayOfInt3 = new int[2];
    for (byte b = 0; b < 'Ā'; b++) {
      int i = P[0][b] & 0xFF;
      arrayOfInt1[0] = i;
      arrayOfInt2[0] = Mx_X(i) & 0xFF;
      arrayOfInt3[0] = Mx_Y(i) & 0xFF;
      i = P[1][b] & 0xFF;
      arrayOfInt1[1] = i;
      arrayOfInt2[1] = Mx_X(i) & 0xFF;
      arrayOfInt3[1] = Mx_Y(i) & 0xFF;
      MDS[0][b] = arrayOfInt1[1] << 0 | arrayOfInt2[1] << 8 | arrayOfInt3[1] << 16 | arrayOfInt3[1] << 24;
      MDS[1][b] = arrayOfInt3[0] << 0 | arrayOfInt3[0] << 8 | arrayOfInt2[0] << 16 | arrayOfInt1[0] << 24;
      MDS[2][b] = arrayOfInt2[1] << 0 | arrayOfInt3[1] << 8 | arrayOfInt1[1] << 16 | arrayOfInt3[1] << 24;
      MDS[3][b] = arrayOfInt2[0] << 0 | arrayOfInt1[0] << 8 | arrayOfInt3[0] << 16 | arrayOfInt2[0] << 24;
    } 
    l = System.currentTimeMillis() - l;
  }
  
  private static final int LFSR1(int paramInt) {
    return paramInt >> 1 ^ (((paramInt & 0x1) != 0) ? 180 : 0);
  }
  
  private static final int LFSR2(int paramInt) {
    return paramInt >> 2 ^ (((paramInt & 0x2) != 0) ? 180 : 0) ^ (((paramInt & 0x1) != 0) ? 90 : 0);
  }
  
  private static final int Mx_1(int paramInt) {
    return paramInt;
  }
  
  private static final int Mx_X(int paramInt) {
    return paramInt ^ LFSR2(paramInt);
  }
  
  private static final int Mx_Y(int paramInt) {
    return paramInt ^ LFSR1(paramInt) ^ LFSR2(paramInt);
  }
  
  public static synchronized Object makeKey(byte[] paramArrayOfbyte) throws InvalidKeyException {
    if (paramArrayOfbyte == null)
      throw new InvalidKeyException("Empty key"); 
    int i = paramArrayOfbyte.length;
    if (i != 8 && i != 16 && i != 24 && i != 32)
      throw new InvalidKeyException("Incorrect key length"); 
    int j = i / 8;
    byte b1 = 40;
    int[] arrayOfInt1 = new int[4];
    int[] arrayOfInt2 = new int[4];
    int[] arrayOfInt3 = new int[4];
    byte b3 = 0;
    byte b2 = 0;
    for (int k = j - 1; b2 < 4 && b3 < i; k--) {
      arrayOfInt1[b2] = paramArrayOfbyte[b3++] & 0xFF | (paramArrayOfbyte[b3++] & 0xFF) << 8 | (paramArrayOfbyte[b3++] & 0xFF) << 16 | (paramArrayOfbyte[b3++] & 0xFF) << 24;
      arrayOfInt2[b2] = paramArrayOfbyte[b3++] & 0xFF | (paramArrayOfbyte[b3++] & 0xFF) << 8 | (paramArrayOfbyte[b3++] & 0xFF) << 16 | (paramArrayOfbyte[b3++] & 0xFF) << 24;
      arrayOfInt3[k] = RS_MDS_Encode(arrayOfInt1[b2], arrayOfInt2[b2]);
      b2++;
    } 
    int[] arrayOfInt4 = new int[b1];
    int m = 0;
    while (b2 < b1 / 2) {
      int i4 = F32(j, m, arrayOfInt1);
      int i5 = F32(j, m + 16843009, arrayOfInt2);
      i5 = i5 << 8 | i5 >>> 24;
      i4 += i5;
      arrayOfInt4[2 * b2] = i4;
      i4 += i5;
      arrayOfInt4[2 * b2 + 1] = i4 << 9 | i4 >>> 23;
      b2++;
      m += 33686018;
    } 
    int n = arrayOfInt3[0];
    int i1 = arrayOfInt3[1];
    int i2 = arrayOfInt3[2];
    int i3 = arrayOfInt3[3];
    int[] arrayOfInt5 = new int[1024];
    for (b2 = 0; b2 < 'Ā'; b2++) {
      int i7 = b2;
      int i6 = i7;
      int i5 = i6;
      int i4 = i5;
      switch (j & 0x3) {
        case 1:
          arrayOfInt5[2 * b2] = MDS[0][P[0][i4] & 0xFF ^ b0(n)];
          arrayOfInt5[2 * b2 + 1] = MDS[1][P[0][i5] & 0xFF ^ b1(n)];
          arrayOfInt5[512 + 2 * b2] = MDS[2][P[1][i6] & 0xFF ^ b2(n)];
          arrayOfInt5[512 + 2 * b2 + 1] = MDS[3][P[1][i7] & 0xFF ^ b3(n)];
          break;
        case 0:
          i4 = P[1][i4] & 0xFF ^ b0(i3);
          i5 = P[0][i5] & 0xFF ^ b1(i3);
          i6 = P[0][i6] & 0xFF ^ b2(i3);
          i7 = P[1][i7] & 0xFF ^ b3(i3);
        case 3:
          i4 = P[1][i4] & 0xFF ^ b0(i2);
          i5 = P[1][i5] & 0xFF ^ b1(i2);
          i6 = P[0][i6] & 0xFF ^ b2(i2);
          i7 = P[0][i7] & 0xFF ^ b3(i2);
        case 2:
          arrayOfInt5[2 * b2] = MDS[0][P[0][P[0][i4] & 0xFF ^ b0(i1)] & 0xFF ^ b0(n)];
          arrayOfInt5[2 * b2 + 1] = MDS[1][P[0][P[1][i5] & 0xFF ^ b1(i1)] & 0xFF ^ b1(n)];
          arrayOfInt5[512 + 2 * b2] = MDS[2][P[1][P[0][i6] & 0xFF ^ b2(i1)] & 0xFF ^ b2(n)];
          arrayOfInt5[512 + 2 * b2 + 1] = MDS[3][P[1][P[1][i7] & 0xFF ^ b3(i1)] & 0xFF ^ b3(n)];
          break;
      } 
    } 
    return new Object[] { arrayOfInt5, arrayOfInt4 };
  }
  
  public static byte[] blockEncrypt(byte[] paramArrayOfbyte, int paramInt, Object paramObject) {
    Object[] arrayOfObject = (Object[])paramObject;
    int[] arrayOfInt1 = (int[])arrayOfObject[0];
    int[] arrayOfInt2 = (int[])arrayOfObject[1];
    int i = paramArrayOfbyte[paramInt++] & 0xFF | (paramArrayOfbyte[paramInt++] & 0xFF) << 8 | (paramArrayOfbyte[paramInt++] & 0xFF) << 16 | (paramArrayOfbyte[paramInt++] & 0xFF) << 24;
    int j = paramArrayOfbyte[paramInt++] & 0xFF | (paramArrayOfbyte[paramInt++] & 0xFF) << 8 | (paramArrayOfbyte[paramInt++] & 0xFF) << 16 | (paramArrayOfbyte[paramInt++] & 0xFF) << 24;
    int k = paramArrayOfbyte[paramInt++] & 0xFF | (paramArrayOfbyte[paramInt++] & 0xFF) << 8 | (paramArrayOfbyte[paramInt++] & 0xFF) << 16 | (paramArrayOfbyte[paramInt++] & 0xFF) << 24;
    int m = paramArrayOfbyte[paramInt++] & 0xFF | (paramArrayOfbyte[paramInt++] & 0xFF) << 8 | (paramArrayOfbyte[paramInt++] & 0xFF) << 16 | (paramArrayOfbyte[paramInt++] & 0xFF) << 24;
    i ^= arrayOfInt2[0];
    j ^= arrayOfInt2[1];
    k ^= arrayOfInt2[2];
    m ^= arrayOfInt2[3];
    byte b1 = 8;
    for (byte b2 = 0; b2 < 16; b2 += 2) {
      int n = Fe32(arrayOfInt1, i, 0);
      int i1 = Fe32(arrayOfInt1, j, 3);
      k ^= n + i1 + arrayOfInt2[b1++];
      k = k >>> 1 | k << 31;
      m = m << 1 | m >>> 31;
      m ^= n + 2 * i1 + arrayOfInt2[b1++];
      n = Fe32(arrayOfInt1, k, 0);
      i1 = Fe32(arrayOfInt1, m, 3);
      i ^= n + i1 + arrayOfInt2[b1++];
      i = i >>> 1 | i << 31;
      j = j << 1 | j >>> 31;
      j ^= n + 2 * i1 + arrayOfInt2[b1++];
    } 
    k ^= arrayOfInt2[4];
    m ^= arrayOfInt2[5];
    i ^= arrayOfInt2[6];
    j ^= arrayOfInt2[7];
    return new byte[] { 
        (byte)k, (byte)(k >>> 8), (byte)(k >>> 16), (byte)(k >>> 24), (byte)m, (byte)(m >>> 8), (byte)(m >>> 16), (byte)(m >>> 24), (byte)i, (byte)(i >>> 8), 
        (byte)(i >>> 16), (byte)(i >>> 24), (byte)j, (byte)(j >>> 8), (byte)(j >>> 16), (byte)(j >>> 24) };
  }
  
  private static final int b0(int paramInt) {
    return paramInt & 0xFF;
  }
  
  private static final int b1(int paramInt) {
    return paramInt >>> 8 & 0xFF;
  }
  
  private static final int b2(int paramInt) {
    return paramInt >>> 16 & 0xFF;
  }
  
  private static final int b3(int paramInt) {
    return paramInt >>> 24 & 0xFF;
  }
  
  private static final int RS_MDS_Encode(int paramInt1, int paramInt2) {
    int i = paramInt2;
    byte b;
    for (b = 0; b < 4; b++)
      i = RS_rem(i); 
    i ^= paramInt1;
    for (b = 0; b < 4; b++)
      i = RS_rem(i); 
    return i;
  }
  
  private static final int RS_rem(int paramInt) {
    int i = paramInt >>> 24 & 0xFF;
    int j = (i << 1 ^ (((i & 0x80) != 0) ? 333 : 0)) & 0xFF;
    int k = i >>> 1 ^ (((i & 0x1) != 0) ? 166 : 0) ^ j;
    return paramInt << 8 ^ k << 24 ^ j << 16 ^ k << 8 ^ i;
  }
  
  private static final int F32(int paramInt1, int paramInt2, int[] paramArrayOfint) {
    int i = b0(paramInt2);
    int j = b1(paramInt2);
    int k = b2(paramInt2);
    int m = b3(paramInt2);
    int n = paramArrayOfint[0];
    int i1 = paramArrayOfint[1];
    int i2 = paramArrayOfint[2];
    int i3 = paramArrayOfint[3];
    int i4 = 0;
    switch (paramInt1 & 0x3) {
      case 1:
        i4 = MDS[0][P[0][i] & 0xFF ^ b0(n)] ^ MDS[1][P[0][j] & 0xFF ^ b1(n)] ^ MDS[2][P[1][k] & 0xFF ^ b2(n)] ^ MDS[3][P[1][m] & 0xFF ^ b3(n)];
        break;
      case 0:
        i = P[1][i] & 0xFF ^ b0(i3);
        j = P[0][j] & 0xFF ^ b1(i3);
        k = P[0][k] & 0xFF ^ b2(i3);
        m = P[1][m] & 0xFF ^ b3(i3);
      case 3:
        i = P[1][i] & 0xFF ^ b0(i2);
        j = P[1][j] & 0xFF ^ b1(i2);
        k = P[0][k] & 0xFF ^ b2(i2);
        m = P[0][m] & 0xFF ^ b3(i2);
      case 2:
        i4 = MDS[0][P[0][P[0][i] & 0xFF ^ b0(i1)] & 0xFF ^ b0(n)] ^ MDS[1][P[0][P[1][j] & 0xFF ^ b1(i1)] & 0xFF ^ b1(n)] ^ MDS[2][P[1][P[0][k] & 0xFF ^ b2(i1)] & 0xFF ^ b2(n)] ^ MDS[3][P[1][P[1][m] & 0xFF ^ b3(i1)] & 0xFF ^ b3(n)];
        break;
    } 
    return i4;
  }
  
  private static final int Fe32(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    return paramArrayOfint[2 * _b(paramInt1, paramInt2)] ^ paramArrayOfint[2 * _b(paramInt1, paramInt2 + 1) + 1] ^ paramArrayOfint[512 + 2 * _b(paramInt1, paramInt2 + 2)] ^ paramArrayOfint[512 + 2 * _b(paramInt1, paramInt2 + 3) + 1];
  }
  
  private static final int _b(int paramInt1, int paramInt2) {
    int i = 0;
    switch (paramInt2 % 4) {
      case 0:
        i = b0(paramInt1);
        break;
      case 1:
        i = b1(paramInt1);
        break;
      case 2:
        i = b2(paramInt1);
        break;
      case 3:
        i = b3(paramInt1);
        break;
    } 
    return i;
  }
  
  public static int blockSize() {
    return 16;
  }
}


/* Location:              C:\Users\<REDACTED>\Downloads\generator.jar!\Twofish\Twofish_Algorithm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */