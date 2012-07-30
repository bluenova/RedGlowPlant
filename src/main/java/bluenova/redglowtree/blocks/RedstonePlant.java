/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.blocks;

import bluenova.redglowtree.util.Util;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

/**
 *
 * @author SvenBrnn
 */
public class RedstonePlant extends GenericCustomBlock {

    private int state = 0;
    private String texturState0 = "http://img513.imageshack.us/img513/6789/redstonecrystal0.png";
    private String texturState1 = "http://img689.imageshack.us/img689/9743/redstonecrystal1.png";
    private String texturState2 = "http://img337.imageshack.us/img337/8669/redstonecrystal2.png";
    private String texturState3 = "http://img217.imageshack.us/img217/4816/redstonecrystal3.png";
    private String texturState4 = "http://img651.imageshack.us/img651/1400/redstonecrystal4.png";
    private Plugin plugin;
    private SpoutItemStack drop;

    public RedstonePlant(Plugin plugin, String name) {
        super(plugin, name, 20);
        this.plugin = plugin;
        this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
        this.setHardness(0.01F);
        this.setLightLevel(15);
        GenericCubeBlockDesign des = Util.getdesignflower(plugin, texturState0, true);
        this.setBlockDesign(des);
        drop = new SpoutItemStack(MaterialData.redstone, 1);
    }

    @Override
    public void onBlockPlace(World world, int x, int y, int z) {
        if (world.getBlockAt(x, y - 1, z).getType() == Material.STONE) {
            super.onBlockPlace(world, x, y, z);
        }
    }   

    @Override
    public void onBlockPlace(World world, int x, int y, int z, LivingEntity living) {
        if (world.getBlockAt(x, y - 1, z).getType() == Material.STONE) {
            super.onBlockPlace(world, x, y, z, living);
        }
    }

    @Override
    public SpoutItemStack getItemDrop() {
        return drop;
    }

    public static void setupTimer(World world, int x, int y, int z) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            }
        };
        timer.scheduleAtFixedRate(timerTask, 30000, 30000);
    }   
}
