package bks.pluginstore.teamchat.events;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static bks.pluginstore.teamchat.TeamChat.getInstance;

public class TeamChatEventListener implements Listener {

    @EventHandler
    public void on(ServerSwitchEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if(event.getPlayer().hasPermission(getInstance().getVar().getPermNode())){
            if (getInstance().getTeamFile().getData(uuid.toString()) == null) {
                getInstance().getTeamFile().insertData(uuid.toString(), "1");
            }
        }
    }

    @EventHandler
    public void on(ChatEvent event) {

        if (event.isCancelled()) {
            return;
        }

        if (!(event.getSender() instanceof ProxiedPlayer)) {
            return;
        }
        if (event.getMessage().toLowerCase().startsWith(String.valueOf(getInstance().getFileManager().getData("TeamChatTrigger")))) {
            String permNode = getInstance().getVar().getPermNode();
            String prefix = getInstance().getVar().getPrefix();
            String placeholder = getInstance().getVar().getPlaceholder();
            String chatColor = getInstance().getVar().getChatColor();
            Boolean useOwnChatColor = getInstance().getVar().getUseOwnChatColor();


            if (permNode != null) {

                List<ProxiedPlayer> team = ProxyServer.getInstance().getPlayers()
                        .stream()
                        .filter(proxiedPlayer -> proxiedPlayer.hasPermission(permNode))
                        .filter(proxiedPlayer -> Boolean.parseBoolean(String.valueOf(getInstance().getTeamFile().getData(proxiedPlayer.getUniqueId().toString()).equals("1"))))
                        .collect(Collectors.toList());
                BaseComponent[] msg;
                if (useOwnChatColor) {
                    msg = TextComponent.fromLegacyText(prefix + " " + placeholder + " " + ((ProxiedPlayer) event.getSender()).getDisplayName() + "§7:" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceFirst(getInstance().getVar().getTeamChatTrigger(), "")));
                } else {
                    msg = TextComponent.fromLegacyText(prefix + " " + placeholder + " " + ((ProxiedPlayer) event.getSender()).getDisplayName() + "§7:"
                            + chatColor + event.getMessage().replaceFirst(getInstance().getVar().getTeamChatTrigger(), ""));
                }

                team.forEach(t -> {

                    if (getInstance().getTeamFile().getData(((ProxiedPlayer) event.getSender()).getUniqueId().toString()).equals("0")) {
                        event.setCancelled(true);
                        ((ProxiedPlayer) event.getSender()).sendMessage(new TextComponent(getInstance().getVar().getTeamChatDisabled()));
                        return;
                    }

                    event.setCancelled(true);
                    t.sendMessage(msg);

                });
            }
        }


    }

}
