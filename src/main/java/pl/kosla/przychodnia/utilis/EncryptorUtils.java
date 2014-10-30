/*
 * Mój jest kawałek podłogi...  * 
 */
package pl.kosla.przychodnia.utilis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author patryk
 * from http://stackoverflow.com/questions/20832008
 */
public final class EncryptorUtils {
    public void EncryptorUtils(){};
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    
    public static String bytesToHex(byte[] bytes) {
      char[] hexChars = new char[bytes.length * 2];
      int v;
      for (int j = 0; j < bytes.length; j++) {
        v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
      }
      return new String(hexChars);
    }
    
    private static final String SALT = "DoktorŻiwago";
    
    
    // A password hashing method.
    public static String hashPassword(String in) {
      try {
        MessageDigest md = MessageDigest
            .getInstance("SHA-256");
        md.update(SALT.getBytes());        // <-- Prepend SALT.
        md.update(in.getBytes());
        // md.update(SALT.getBytes());     // <-- Or, append SALT.

        byte[] out = md.digest();
        return bytesToHex(out);            // <-- Return the Hex Hash.
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      }
      return "";
    }
    
    
   
}
