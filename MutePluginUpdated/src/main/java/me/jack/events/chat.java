package me.jack.events;

import me.jack.cons.Mute;
import me.jack.cons.User;
import me.jack.muteplayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chat implements Listener {

    @EventHandler
    public void onchat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        for (Mute i : User.mutes){
            if(p.getName().toString().equalsIgnoreCase(i.username.toString())){
                p.sendMessage(muteplayers.getPrefix()+" §CYou are muted!\n§r§7 - §6Time §c"+muteplayers.ConvertSecToTime(i.duration)+ "\n §r§7- §6Staff: §c"
                        +i.Staff+ "\n §r§7- §6Reason: §c§o"+i.reason);
                e.setCancelled(true);
            }
        }
    }
}
