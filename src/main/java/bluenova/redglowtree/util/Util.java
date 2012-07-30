/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.redglowtree.util;

import bluenova.redglowtree.RedGlowTree;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.Texture;

/**
 *
 * @author BoneBoy
 */
public class Util {

    /**
     * Checks if Player Has Permission
     *
     * @param pl Player to Check
     * @param perm Permission to Check
     * @return Has Permission or Not
     */
    public static boolean hasPermission(Player pl, String perm) {
        if (RedGlowTree.Permissions != null) {
            if (RedGlowTree.Permissions.has(pl, perm)) {
                return true;
            } else {
                pl.sendMessage(ChatColor.RED + "No Permission!");
                return false;
            }
        } else {
            if (pl.hasPermission(perm) || pl.isOp()) {
                return true;
            } else {
                pl.sendMessage(ChatColor.RED + "No Permission!");
                return false;
            }
        }
    }

    public static GenericCubeBlockDesign getdesignflower(Plugin plugin,
            String textur, Boolean translucide) {
        GenericCubeBlockDesign bd = new GenericCubeBlockDesign(plugin, textur,
                16);
        Texture texture = new Texture(plugin, textur, 16, 16, 16);

        if (translucide == true) {
            bd.setRenderPass(1);
        }
        bd.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F).setQuadNumber(4);
        bd.setTexture(plugin, texture.getTexture());
        
        Quad front = new Quad(0, texture.getSubTexture(0));
        front.addVertex(3, 0.0F, 0.0F, 1.0F);
        front.addVertex(2, 0.0F, 1.0F, 1.0F);
        front.addVertex(1, 1.0F, 1.0F, 0.0F);
        front.addVertex(0, 1.0F, 0.0F, 0.0F);

        Quad back = new Quad(1, texture.getSubTexture(0));
        back.addVertex(0, 0.0F, 0.0F, 1.0F);
        back.addVertex(1, 0.0F, 1.0F, 1.0F);
        back.addVertex(2, 1.0F, 1.0F, 0.0F);
        back.addVertex(3, 1.0F, 0.0F, 0.0F);

        Quad left = new Quad(2, texture.getSubTexture(0));
        left.addVertex(3, 0.0F, 0.0F, 0.0F);
        left.addVertex(2, 0.0F, 1.0F, 0.0F);
        left.addVertex(1, 1.0F, 1.0F, 1.0F);
        left.addVertex(0, 1.0F, 0.0F, 1.0F);

        Quad right = new Quad(3, texture.getSubTexture(0));
        right.addVertex(0, 0.0F, 0.0F, 0.0F);
        right.addVertex(1, 0.0F, 1.0F, 0.0F);
        right.addVertex(2, 1.0F, 1.0F, 1.0F);
        right.addVertex(3, 1.0F, 0.0F, 1.0F);

        bd.setQuad(front).setQuad(back).setQuad(left).setQuad(right);
        return bd;
    }
}
