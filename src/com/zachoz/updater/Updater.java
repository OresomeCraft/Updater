package com.zachoz.updater;

import net.minecraft.util.org.apache.commons.io.FilenameUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class Updater extends JavaPlugin {

    private File updatesDirectory, pluginsDirectory;

    public void onEnable() {
        updatesDirectory = this.getDataFolder();
        pluginsDirectory = new File("plugins/");

        getConfig(); // Create data folder
    }

    public void onDisable() { // Update plugins
        for (File toUpdate : updatesDirectory.listFiles()) {
            if (FilenameUtils.getExtension(toUpdate.getName()).equals("jar")) {
                new File(pluginsDirectory.getAbsoluteFile() + "/" + toUpdate.getName()).delete();
                try {
                    copy(toUpdate, new File(pluginsDirectory.getAbsoluteFile() + "/" + toUpdate.getName()));
                    getLogger().info("Updated file: " + toUpdate.getName());
                    toUpdate.delete(); // Remove file now that we're done
                } catch (IOException ex) {
                    getLogger().severe("Unable to update file: " + toUpdate.getName());
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void copy(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) dest.mkdir();
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                copy(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) out.write(buffer, 0, length);
            in.close();
            out.close();
        }
    }
}
