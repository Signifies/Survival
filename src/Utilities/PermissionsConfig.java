package Utilities;

import me.ES96.Survival.com.Survival;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class PermissionsConfig {
    private FileConfiguration Permissions = null;
    private File PermissionsFile = null;
    private static final String fileName = "Config.yml";

    private Survival main;

    public PermissionsConfig(Survival instance) {
        main = instance;
    }


    public void reloadPermissions() {
        if (PermissionsFile == null) {
            PermissionsFile = new File(main.getDataFolder(), fileName);
        }
        Permissions = YamlConfiguration.loadConfiguration(PermissionsFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            Permissions.setDefaults(defConfig);
        }
    }

    public FileConfiguration getPermissions() {
        if (Permissions == null) {
            reloadPermissions();
        }
        return Permissions;
    }

    public void savePermissions() {
        if (Permissions == null || PermissionsFile == null) {
            return;
        }
        try {
            getPermissions().save(PermissionsFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + PermissionsFile, ex);
        }
    }

    public void saveDefaultPermissions() {
        if (PermissionsFile == null) {
            PermissionsFile = new File(main.getDataFolder(), fileName);
        }
        if (!PermissionsFile.exists()) {
            main.saveResource(fileName, false);
        }
    }
}