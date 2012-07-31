/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 *
 * @author Anwender
 */
public class RedGlowListener implements org.bukkit.event.Listener {

    @EventHandler
    public void onChunkLoad(final ChunkLoadEvent event) { // this was the event that triggered the method which had the error triggered

    }
}
