package bks.pluginstore.teamchat.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static bks.pluginstore.teamchat.TeamChat.getInstance;

public class TeamChatCommand extends Command {

    public TeamChatCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if(!(cs instanceof ProxiedPlayer)){
           return;
        }

        ProxiedPlayer p = (ProxiedPlayer) cs;

        if(!p.hasPermission(String.valueOf(getInstance().getVar().getPermNode()))){
            p.sendMessage(new TextComponent(getInstance().getVar().getNoPerm()));
            return;
        }

        switch(args.length){
            case 0:
                sendInfo(p);
                break;
            case 1:
                switch(args[0]){
                    case "info":
                        sendInfo(p);
                        break;
                    case "toggle":
                            if(getInstance().getTeamFile().getData(p.getUniqueId().toString()) != null){
                                if(getInstance().getTeamFile().getData(p.getUniqueId().toString()).equals("1")){
                                    getInstance().getTeamFile().removeData(p.getUniqueId().toString());
                                    getInstance().getTeamFile().insertData(p.getUniqueId().toString(), "0");
                                    p.sendMessage(new TextComponent(getInstance().getVar().getDisabledTeamChat()));

                                }else{
                                    getInstance().getTeamFile().removeData(p.getUniqueId().toString());
                                    getInstance().getTeamFile().insertData(p.getUniqueId().toString(), "1");
                                    p.sendMessage(new TextComponent(String.valueOf(getInstance().getVar().getEnabledTeamChat())));
                                }
                            }else{
                                getInstance().getTeamFile().insertData(p.getUniqueId().toString(), "1");
                                p.sendMessage(new TextComponent(String.valueOf(getInstance().getVar().getEnabledTeamChat())));
                            }
                        break;
                    case "reload":
                        if(p.hasPermission("teamchat.reload")){
                            getInstance().getVar().reload();
                            p.sendMessage(new TextComponent(getInstance().getVar().getPrefix() + " " + getInstance().getVar().getPlaceholder() + " " +  getInstance().getVar().getReloadSuccessfull()));
                        }else{
                            p.sendMessage(new TextComponent(getInstance().getVar().getNoPerm()));
                        }
                        break;
                    default:
                        sendInfo(p);
                        break;
                }
                    break;
            default:
                sendInfo(p);
                break;
        }

    }

    private void sendInfo(ProxiedPlayer p){
        p.sendMessage(new TextComponent(String.valueOf(getInstance().getVar().getPrefix())));
        p.sendMessage(new TextComponent("§7- §einfo§7: " + getInstance().getVar().getInfoMessage()));
        p.sendMessage(new TextComponent("§7- §etoggle§7: " + getInstance().getVar().getToggleMessage()));
        if(p.hasPermission("teamchat.reload")){
            p.sendMessage(new TextComponent("§7- §ereload§7: " + getInstance().getVar().getReloadInfo()));
        }
    }
}
