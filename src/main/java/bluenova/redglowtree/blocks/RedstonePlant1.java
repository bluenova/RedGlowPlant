/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.blocks;

import bluenova.redglowtree.RedGlowTree;
import bluenova.redglowtree.util.Util;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

/**
 *
 * @author SvenBrnn
 */
public class RedstonePlant1 extends GenericCustomBlock {

    private int state = 0;
    private static String name;
    private String texturState1 = "http://img689.imageshack.us/img689/9743/redstonecrystal1.png";
    private static Plugin plugin;
    private SpoutItemStack drop;

    public RedstonePlant1(Plugin plugin, String name) {
        super(plugin, name + " State 2", 20);
        RedstonePlant1.name = name;
        RedstonePlant1.plugin = plugin;
        this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
        this.setHardness(0.01F);
        this.setLightLevel(15);
        GenericCubeBlockDesign des = Util.getdesignflower(plugin, texturState1, true);
        this.setBlockDesign(des);
        drop = new SpoutItemStack(MaterialData.redstone, 1);
    }

    @Override
    public SpoutItemStack getItemDrop() {
        return drop;
    }

    public static void setupTimer(final SpoutBlock blk) {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if (blk.getChunk().isLoaded()) {
                    Random rnd = new Random();
                    int next = rnd.nextInt(4);
                    if (next % 4 == 0) {
                        blk.setCustomBlock(new RedstonePlant2(RedstonePlant1.plugin, RedstonePlant1.name));
                        RedstonePlant2.setupTimer(blk);
                        timer.cancel();
                    }
                } else {
                    if (RedGlowTree.BlockTimer.containsKey(blk)) {
                        RedGlowTree.BlockTimer.remove(blk);
                        timer.cancel();
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(timerTask, 30000, 30000);
        RedGlowTree.BlockTimer.put(blk, timer);
    }
}
