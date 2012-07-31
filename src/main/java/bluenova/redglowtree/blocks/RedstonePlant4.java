/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.blocks;

import bluenova.redglowtree.RedGlowTree;
import bluenova.redglowtree.util.Util;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
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
public class RedstonePlant4 extends GenericCustomBlock {

    private int state = 0;
    private String texturState4 = "http://img651.imageshack.us/img651/1400/redstonecrystal4.png";
    private Plugin plugin;
    private SpoutItemStack drop;

    public RedstonePlant4(Plugin plugin, String name) {
        super(plugin, name + " State 5", 20);
        this.plugin = plugin;
        this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
        this.setHardness(0.01F);
        this.setLightLevel(15);
        GenericCubeBlockDesign des = Util.getdesignflower(plugin, texturState4, true);
        this.setBlockDesign(des);
        drop = new SpoutItemStack(MaterialData.redstone, 5);
    }

    @Override
    public SpoutItemStack getItemDrop() {
        return drop;
    }

    public static void setupTimer(SpoutBlock blk) {
        if (RedGlowTree.BlockTimer.containsKey(blk)) {
            RedGlowTree.BlockTimer.remove(blk);
        }
    }
}
