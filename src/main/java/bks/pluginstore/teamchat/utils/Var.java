package bks.pluginstore.teamchat.utils;

import bks.pluginstore.teamchat.TeamChat;

public class Var {
    private String
            permNode,
            prefix,
            placeholder,
            chatColor,
            noPerm,
            disabledTeamChat,
            enabledTeamChat,
            reloadSuccessfull,
            teamChatTrigger,
            infoMessage,
            toggleMessage,
            reloadInfo,
            teamChatDisabled;

    private Boolean useOwnChatColor;
    public void reload() {
        this.permNode = String.valueOf(TeamChat.getInstance().getFileManager().getData("TeamchatPermission"));
        this.prefix = String.valueOf(TeamChat.getInstance().getFileManager().getData("Prefix"));
        this.placeholder = String.valueOf(TeamChat.getInstance().getFileManager().getData("Placeholder"));
        this.chatColor = String.valueOf(TeamChat.getInstance().getFileManager().getData("Chatcolor"));
        this.useOwnChatColor = Boolean.valueOf(String.valueOf(TeamChat.getInstance().getFileManager().getData("UseOwnChatColour")));

        this.noPerm = String.valueOf(TeamChat.getInstance().getFileManager().getData("NoPermissionsMessage"));
        this.disabledTeamChat = String.valueOf(TeamChat.getInstance().getFileManager().getData("DisableTeamChat"));
        this.enabledTeamChat = String.valueOf(TeamChat.getInstance().getFileManager().getData("EnabledTeamChat"));
        this.reloadSuccessfull = String.valueOf(TeamChat.getInstance().getFileManager().getData("ReloadSuccessful"));
        this.teamChatTrigger = String.valueOf(TeamChat.getInstance().getFileManager().getData("TeamChatTrigger"));
        this.reloadInfo = String.valueOf(TeamChat.getInstance().getFileManager().getData("ReloadInfo"));
        this.toggleMessage = String.valueOf(TeamChat.getInstance().getFileManager().getData("ToggleMessage"));
        this.infoMessage = String.valueOf(TeamChat.getInstance().getFileManager().getData("InfoMessage"));
        this.teamChatDisabled = String.valueOf(TeamChat.getInstance().getFileManager().getData("TeamChatDisabled"));
    }

    public String getPermNode() {
        return this.permNode;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public String getChatColor() {
        return this.chatColor;
    }

    public Boolean getUseOwnChatColor() {
        return this.useOwnChatColor;
    }

    public String getNoPerm() {
        return this.noPerm;
    }

    public String getDisabledTeamChat() {
        return this.disabledTeamChat;
    }

    public String getEnabledTeamChat() {
        return this.enabledTeamChat;
    }

    public String getReloadSuccessfull() {
        return this.reloadSuccessfull;
    }

    public String getInfoMessage() {
        return this.infoMessage;
    }

    public String getReloadInfo() {
        return this.reloadInfo;
    }

    public String getTeamChatTrigger() {
        return this.teamChatTrigger;
    }

    public String getToggleMessage() {
        return this.toggleMessage;
    }

    public String getTeamChatDisabled() {
        return this.teamChatDisabled;
    }
}
