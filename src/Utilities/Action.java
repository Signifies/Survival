package Utilities;

public enum Action
{

    SPEAK(SUtils.color("")),
    PLACE(SUtils.color("")),
    DESTROY(SUtils.color("")),
    INTERACT(SUtils.color("")),
    PICKUP(SUtils.color("")),
    DROP(SUtils.color(""))
    ,BAN(SUtils.color("&4[Ban]&r"))
    ,KICK(SUtils.color("&fKick&9&l> &r")),
    CHAT(SUtils.color("&fChat&9&l> &r")),
    PERMISSION(SUtils.color("&fPermission&9&l> &r")),
    USAGE(SUtils.color("&fUsage&9&l> &r")),
    WHITELIST(SUtils.color("&fWhitelist&9&l> ")),
    MUTE(SUtils.color("&9[Mute]&r")),
    REPORT(SUtils.color("&6[Report]&r")),
    WARN(SUtils.color("&c[Warn]&r")),
    STANDARD_ALERT("&c&l>>&6&l!!&c&l<< &c*** {} &c*** &c&l>>&6&l!!&c&l<<"),
    NOTIFY_ADMIN("&7[&4&lA&r&7] &7&o@{3}> {2}"),
    NOTIFY_GLOBAL("&7[&b&lN&r&7] {2}");
    String msg;
    Action(String msg)
    {
        this.msg = msg;
    }

    public String getMessage()
    {
        return msg;
    }

    public void setMessage(String format)
    {
        this.msg = format;
    }


}