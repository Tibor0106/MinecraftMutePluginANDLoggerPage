package me.jack.cons;

import me.jack.muteplayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class runnable {
    public static void Start(){
        Plugin plugin = me.jack.muteplayers.getPlugin(me.jack.muteplayers.class);

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                if(User.mutes.size() != 0){
                    for(Mute i : User.mutes){
                        if(i.duration <= 0){
                            User.mutes.remove(i);
                             Player p;
                            try{
                                p = Bukkit.getPlayer(i.username);
                                p.sendMessage(muteplayers.getPrefix()+" §a§lYou aren't muted longer!");
                            }catch (NullPointerException e){

                            }
                        }
                        i.duration-=1;
                    }
                }
            }
        }, 20, 20);

    }
}
