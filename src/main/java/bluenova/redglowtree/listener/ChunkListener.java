/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.listener;

import bluenova.redglowtree.RedGlowTree;
import bluenova.redglowtree.blocks.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.getspout.spoutapi.block.SpoutBlock;

/**
 *
 * @author Anwender
 */
public class ChunkListener implements Listener {

    @EventHandler
    public void onChunkLoad(final ChunkLoadEvent event) { // this was the event that triggered the method which had the error triggered
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 255; y++) {
                for (int z = 0; z < 16; z++) {
                    SpoutBlock blk = (SpoutBlock) event.getChunk().getBlock(x, y, z);
                    if (blk.isCustomBlock()) {
                        if (!RedGlowTree.BlockTimer.containsKey(blk)) {
                            if (blk.getCustomBlock() instanceof RedstonePlant) {
                                RedstonePlant.setupTimer(blk);
                            } else if (blk.getCustomBlock() instanceof RedstonePlant1) {
                                RedstonePlant1.setupTimer(blk);
                            } else if (blk.getCustomBlock() instanceof RedstonePlant2) {
                                RedstonePlant2.setupTimer(blk);
                            } else if (blk.getCustomBlock() instanceof RedstonePlant3) {
                                RedstonePlant3.setupTimer(blk);
                            }
                        }
                    }
                }
            }
        }
    }
}
