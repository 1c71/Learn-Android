package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by agoodob on 2016/2/25.
 */
public class StreamUtils {

    /**
    * 将输入流读取成 String 后返回
    * @param in
    * @return
    */
    public static String readFromSteam(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int len = 0;
        byte[] buffer = new byte[1024];

        while((len=in.read(buffer))!=-1){
            out.write(buffer, 0, len);
        }

        String result = out.toString();

        in.close();
        out.close();
        return result;
    }
}
