package bks.pluginstore.teamchat.utils;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private File file;
    private Configuration cfg;

    public FileManager(String path, String filename) {
        this.file = new File(path, filename);
        if (this.file.exists()) {
            try {
                this.cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.cfg, this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String path, String value) {
        this.cfg.set(path, value);
        save();
    }

    public void removeData(String path) {
        this.cfg.set(path, null);
        save();
    }

    public Object getData(String path) {
        try {
            this.cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.cfg.get(path);
    }

    public File getFile() {
        return this.file;
    }
}
