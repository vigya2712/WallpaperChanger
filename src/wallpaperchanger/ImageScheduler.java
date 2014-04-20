/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallpaperchanger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vigya
 */
public class ImageScheduler implements Runnable {

    private String dir;
    private List<File> imageFiles = new ArrayList<>();

    void setDir(String dirPath) {
        dir = dirPath;
        imageFiles.clear();
    }

    @Override
    public void run() {

        if (imageFiles.isEmpty()) {
            File directory = new File(dir);
            imageFiles.addAll(Arrays.asList(directory.listFiles()));
        }

        File wallpaper = selectWallpaper();
        WallpaperUtil.setWallpaper(wallpaper.getAbsolutePath());

    }

    private File selectWallpaper() {
        File wallpaper = null;
        while (wallpaper == null) {
            wallpaper = selectRandomWallpaper();
        }
        return wallpaper;
    }

    private File selectRandomWallpaper() {
        int random = (int) ((Math.random()) * (imageFiles.size()));
        return imageFiles.get(random);
    }

}
