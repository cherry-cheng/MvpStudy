package com.weizhan.superlook.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by neal on 17-6-5.
 */

public class FileUtils {
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static void copy2DestinationAndDeleteOriginal(File original, File destination) throws IOException {
        if (!original.exists()) {
            return;
        }
        if (!destination.exists()) {
            destination.getParentFile().mkdirs();
            destination.createNewFile();
        }
        int length = 524288;
        FileInputStream in = new FileInputStream(original);
        FileOutputStream out = new FileOutputStream(destination);
        byte[] buffer = new byte[length];
        while (true) {
            int ins = in.read(buffer);
            if (ins < 0) {
                in.close();
                out.flush();
                out.close();
                break;
            } else {
                out.write(buffer, 0, ins);
            }
        }
        original.delete();
    }

    public static long getCacheSize() {
        long size = 0;
        //外部缓存区
        if (AppUtils.isExternalStorageAllowed) {
            size = getDirSize(AppUtils.getAvailableCacheDir());
        }
        //内部缓存区
        size += getDirSize(AppUtils.getInternalCacheDir());
        return size;
    }

    public static void clearCache() {
        FileUtils.deleteDirectory(AppUtils.getInternalCacheDir());
        if (AppUtils.isExternalStorageAllowed) {
            FileUtils.deleteDirectory(AppUtils.getAvailableCacheDir());
        }
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹的路径
     */
    public static void delFolder(String folderPath) {
        deleteDirectory(new File(folderPath));
    }

    /**
     * 删除指定目录下的所有文件.
     *
     * @param path 作者:fighter <br />
     *             创建时间:2013-4-25<br />
     *             修改时间:<br />
     */
    public static void deleteDirectory(File path) {
        if (path != null && path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            if (files == null) {
                path.delete();
                return;
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else if (f.isFile()) {
                    f.delete();
                }
            }
            if (path.exists() && path.isDirectory()) {
                path.delete();
            }
        }
    }

}
