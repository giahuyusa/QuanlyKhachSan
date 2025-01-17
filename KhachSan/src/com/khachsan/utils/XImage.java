package com.khachsan.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XImage {
    public static Image getAppIcon(){
        // Try to locate the resource 'hotel2' in the '/com/KhachSan/icon/' directory
        URL url = XImage.class.getResource("/com/KhachSan/icon/hotel2.png");

        // Ensure the resource was found
        assert url != null;

        // Create an ImageIcon from the URL and return the Image object
        return new ImageIcon(url).getImage();

    }
    public static ImageIcon read(String fileName){
        File path = new File("logos",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    public static void save(File src){
        File dst = new File("logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdir();
        }
        try{
            Path from = Paths.get(src.getAbsolutePath());
            Path to   = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
