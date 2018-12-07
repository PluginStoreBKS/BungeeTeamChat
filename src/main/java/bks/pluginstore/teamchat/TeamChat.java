package bks.pluginstore.teamchat;

import bks.pluginstore.teamchat.commands.TeamChatCommand;
import bks.pluginstore.teamchat.events.TeamChatEventListener;
import bks.pluginstore.teamchat.utils.FileManager;
import bks.pluginstore.teamchat.utils.Var;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class TeamChat extends Plugin {

    private static TeamChat instance;
    private FileManager fileManager;
    private FileManager teamFile;
    private Var var;

    @Override
    public void onEnable() {
        instance = this;
        init();
        initListener();
        initCommands();
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(getVar().getPrefix() + " §aenabled successfull§7!"));
    }

    public static TeamChat getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }
    public FileManager getTeamFile() { return this.teamFile; }

    public Var getVar() { return var; }

    private void initListener(){
        getProxy().getPluginManager().registerListener(this, new TeamChatEventListener());
    }

    private void initCommands(){ getProxy().getPluginManager().registerCommand(this, new TeamChatCommand("teamchat")); }

    private void init(){
        if(!getDataFolder().exists()){ //noinspection ResultOfMethodCallIgnored
            getDataFolder().mkdir(); }
            File file = new File(getDataFolder().getPath(), "config.yml");
        if(!file.exists()){
            try {
                System.out.println("trying copy of " + exportResource("/config.yml"));
                System.out.print("trying copy of " + exportResource("/team.yml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.fileManager = new FileManager(getDataFolder().getPath(), "config.yml");
        this.teamFile = new FileManager(getDataFolder().getPath(), "team.yml");
        this.var = new Var();
        this.var.reload();
    }

    private String exportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = TeamChat.class.getResourceAsStream(resourceName);
            if(stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(TeamChat.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream( jarFolder + "/Teamchat" + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } finally {
            assert stream != null;
            stream.close();
            assert resStreamOut != null;
            resStreamOut.close();
        }

        return jarFolder + "/Teamchat" + resourceName;
    }

}