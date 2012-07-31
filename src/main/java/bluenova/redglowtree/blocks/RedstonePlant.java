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
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.SpoutServer;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

/**
 *
 * @author SvenBrnn
 */
public class RedstonePlant extends GenericCustomBlock {

    private int state = 0;
    private static String name;
    private String texturState0 = "http://img513.imageshack.us/img513/6789/redstonecrystal0.png";
    private static Plugin plugin;
    private SpoutItemStack drop;

    public RedstonePlant(Plugin plugin, String name) {
        super(plugin, name, 20);
        RedstonePlant.name = name;
        RedstonePlant.plugin = plugin;
        this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
        this.setHardness(0.01F);
        this.setLightLevel(15);
        GenericCubeBlockDesign des = Util.getdesignflower(plugin, texturState0, true);
        this.setBlockDesign(des);
        drop = new SpoutItemStack(MaterialData.redstone, 1);
    }

    @Override
    public void onBlockPlace(World world, int x, int y, int z) {
        SpoutBlock blk = (SpoutBlock) world.getBlockAt(x, y, z);
        if (world.getBlockAt(x, y - 1, z).getType() == Material.STONE) {
            super.onBlockPlace(world, x, y, z);
            RedstonePlant.setupTimer(blk);
        } else {
            blk.breakNaturally();
        }
    }

    @Override
    public void onBlockPlace(World world, int x, int y, int z, LivingEntity living) {
        SpoutBlock blk = (SpoutBlock) world.getBlockAt(x, y, z);
        if (world.getBlockAt(x, y - 1, z).getType() == Material.STONE) {
            super.onBlockPlace(world, x, y, z, living);
        } else {
            blk.breakNaturally();
        }
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
                        blk.setCustomBlock(new RedstonePlant1(RedstonePlant.plugin, RedstonePlant.name));
                        RedstonePlant1.setupTimer(blk);
                        timer.cancel();
                        System.out.println(blk.toString());
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
