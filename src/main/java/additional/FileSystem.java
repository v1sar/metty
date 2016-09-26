package additional;

import org.jetbrains.annotations.Nullable;

import java.io.*;

/**
 * Created by qwerty on 26.09.16.
 */
public class FileSystem {
    public static final String INDEX_DIR = "/index.html";
    public static String DOCUMENT_ROOT = "/users/qwerty/Documents/TP/3sem/Highload/MyServerV1/metty/";
    private File file;
    private boolean isIndexDir = false;

    public FileSystem(String path){
        file = new File(DOCUMENT_ROOT + path);
        if(file.isDirectory()){
            StringBuilder builder = new StringBuilder();
            builder.append(DOCUMENT_ROOT).append(path).append(INDEX_DIR);
            file = new File(builder.toString());
            isIndexDir = true;
        }
    }

    public FileInputStream getFile() throws FileNotFoundException{
        FileInputStream reader = new FileInputStream(file.getAbsoluteFile());
        return reader;
    }

    public boolean isFileExists(){
        return (file.exists());
    }

    public boolean isIndexDir(){
        return isIndexDir;
    }

    public boolean canRead(){
        return file.canRead();
    }

    public long fileSize(){
        return file.length();
    }

    @Nullable
    public String getContentType(){
        String path = file.getAbsolutePath();
        int index = path.lastIndexOf('.');
        if(index == -1) {
            return null;
        }
        String contentType = path.substring(index+1);
        String result = null;
        if(contentType.equals("html")){
            result = "text/html";
        }
        if(contentType.equals("css")){
            result = "text/css";
        }
        if(contentType.equals("js")){
            result = "text/javascript";
        }
        if(contentType.equals("jpg")){
            result = "image/jpeg";
        }
        if(contentType.equals("jpeg")){
            result = "image/jpeg";
        }
        if(contentType.equals("png")){
            result = "image/png";
        }
        if(contentType.equals("gif")){
            result = "image/gif";
        }
        if(contentType.equals("swf")){
            result = "application/x-shockwave-flash";
        }
        return result;
    }

}