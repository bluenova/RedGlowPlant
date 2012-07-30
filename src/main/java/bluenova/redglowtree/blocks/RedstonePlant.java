/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.blocks;

import bluenova.redglowtree.util.Util;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

/**
 *
 * @author SvenBrnn
 */
public class RedstonePlant extends GenericCustomBlock {

    private int state = 0;
    private String texturState0 = "http://img51.imageshack.us/img51/8669/redstonecrystal2.png";
    private String texturState1 = "http://img51.imageshack.us/img51/8669/redstonecrystal2.png";
    private String texturState2 = "http://img51.imageshack.us/img51/8669/redstonecrystal2.png";
    private String texturState3 = "http://img51.imageshack.us/img51/8669/redstonecrystal2.png";
    private String texturState4 = "http://img51.imageshack.us/img51/8669/redstonecrystal2.png";
    private Plugin plugin;

    public RedstonePlant(Plugin plugin, String name) {
        super(plugin, "Spike Block");
        this.plugin = plugin;
        this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
        this.setHardness(0.01F);
        this.setLightLevel(15);
        checkState();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
                                  @Override
                                  public void run() {
                                     updateState();
                                  }
                              };
        timer.scheduleAtFixedRate(timerTask, 30000, 30000);
    }

    
    @Override
    public void onBlockPlace(World world, int x, int y, int z) {
        if(world.getBlockAt(x, y-1,z).getType() == Material.STONE) {
            super.onBlockPlace(world, x, y, z);
        }
    }

    @Override
    public void onBlockPlace(World world, int x, int y, int z, LivingEntity living) {
        if(world.getBlockAt(x, y-1,z).getType() == Material.STONE) {
            super.onBlockPlace(world, x, y, z, living);
        }
    }

    @Override
    public void onEntityMoveAt(World world, int x, int y, int z, Entity entity) {
        
    }     

    private void updateState() {
        Random rnd = new Random();
        int nextInt = rnd.nextInt(4);
        
        if(state < 4 && nextInt % 4 == 0)
            state++;
        
        checkState();
    }

    private void checkState() {
        GenericCubeBlockDesign des;
        switch (state) {
            case 1:
                des =  Util.getdesignflower(plugin, texturState1, false);
                this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
                break;
            case 2:
               des =  Util.getdesignflower(plugin, texturState2, false);
                this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
                break;
            case 3:
                des =  Util.getdesignflower(plugin, texturState3, false);
                this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
                break;
            case 4:
                des =  Util.getdesignflower(plugin, texturState4, false);
                this.setItemDrop(new ItemStack(Material.REDSTONE, 5));
                break;
            default: 
                des =  Util.getdesignflower(plugin, texturState0, false);
                this.setItemDrop(new ItemStack(Material.REDSTONE, 1));
        }
        des.setRenderPass(1);
        this.setBlockDesign(des);
    }
}
