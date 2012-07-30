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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

/**
 *
 * @author SvenBrnn
 */
public class GlowstonePlant extends GenericCustomBlock {

    private int state = 0;
    private String texturState0 = "";
    private String texturState1 = "";
    private String texturState2 = "";
    private String texturState3 = "";
    private String texturState4 = "";
    private Plugin plugin;

    public GlowstonePlant(Plugin plugin, String name) {
        super(plugin, name, 20);
        this.plugin = plugin;
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

    private void updateState() {
        Random rnd = new Random();
        int nextInt = rnd.nextInt(4);

        if (state < 4 && nextInt % 4 == 0) {
            state++;
        }

        checkState();
    }

    private void checkState() {
        GenericBlockDesign des;
        switch (state) {
            case 1:
                des =  Util.getdesignflower(plugin, texturState1, false);
                this.setItemDrop(new ItemStack(Material.GLOWSTONE_DUST, 1));
                break;
            case 2:
               des =  Util.getdesignflower(plugin, texturState2, false);
                this.setItemDrop(new ItemStack(Material.GLOWSTONE_DUST, 1));
                break;
            case 3:
                des =  Util.getdesignflower(plugin, texturState3, false);
                this.setItemDrop(new ItemStack(Material.GLOWSTONE_DUST, 1));
                break;
            case 4:
                des =  Util.getdesignflower(plugin, texturState4, false);
                this.setItemDrop(new ItemStack(Material.GLOWSTONE_DUST, 5));
                break;
            default:
                des =  Util.getdesignflower(plugin, texturState0, false);
                this.setItemDrop(new ItemStack(Material.GLOWSTONE_DUST, 1));
        }
        des.setRenderPass(1);
        this.setBlockDesign(des);
    }
}
