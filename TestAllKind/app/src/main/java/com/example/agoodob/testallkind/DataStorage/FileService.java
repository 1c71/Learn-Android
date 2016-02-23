package com.example.agoodob.testallkind.DataStorage;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by agoodob on 2016/2/7.
 *
 * 存到 SD 卡里
 *
 */
public class FileService {
    private Context context;

    public FileService(Context context){
        this.context = context;
    }

    public FileService(){

    }

    public boolean saveContentToSDCard(String fileName, String fileContent){
        boolean flag = false;
        FileOutputStream fileOutputStream = null;

        //File 函数通过路径和文件名构建新文件(来自文档)
        File file = new File(Environment.getExternalStorageDirectory(), fileName);

        // 判断 sd 卡是否可用
        if (Environment.MEDIA_MOUNTED
                .equals(Environment.getExternalStorageState())){
            try {
                fileOutputStream = new FileOutputStream(file);
                // FileOutputStream 这个函数的用途是:
                // 构建一个输出流对象，用于写入到那个文件里,
                // Constructs a new FileOutputStream that writes to file.
                // The file will be truncated if it exists, and created if it doesn't exist.
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }



        return true;
    }

}
