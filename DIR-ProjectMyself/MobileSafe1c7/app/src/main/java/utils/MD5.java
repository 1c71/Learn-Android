package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 2016/2/26.
 */
public class MD5 {

    public static String encode(String password){
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5"); // 获取MD5算法对象
            byte[] digest = instance.digest(password.getBytes()); // 对字符串加密, 返回字节数组
            StringBuffer sb = new StringBuffer();

            for (byte b : digest){
                int i = b & 0xff; // 获取字节的低8位有效值
                String hexString = Integer.toHexString(i); // 整数 -> 16进制

                if (hexString.length() < 2){
                    hexString = "0" + hexString; // 如果是1位。补0
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
