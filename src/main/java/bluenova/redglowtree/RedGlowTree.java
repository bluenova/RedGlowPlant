package bluenova.redglowtree;


import bluenova.redglowtree.blocks.RedstonePlant;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

/**
 * RedGlowTree for Bukkit
 * 
 * @author SvenBrnn
 */
public class RedGlowTree extends JavaPlugin {
    public static RedGlowTree plugin;
    public static PluginManager pm;
    public static Server server;
    public static PermissionHandler Permissions;
   
    @Override
    public void onEnable() { 
        RedGlowTree.plugin = this;
        RedGlowTree.pm = getServer().getPluginManager();
        RedGlowTree.server = getServer();
                      
        /*myExecutor = new RedGlowTreeCommandExecutor(this, RedGlowTree.rails);
	getCommand("rm").setExecutor(myExecutor);
        getCommand("RedGlowTree").setExecutor(myExecutor);
        
        RedGlowTree.pm.registerEvents(new PlayerEvents(RedGlowTree.rails), this); 
        */
        
        SpoutShapedRecipe recipeRedstonePlant = new SpoutShapedRecipe(new SpoutItemStack(new RedstonePlant(plugin, "Redstone Plant"), 4));
        recipeRedstonePlant.shape("   ", "WRW", "   ");
        recipeRedstonePlant.setIngredient('W', MaterialData.wheat);
        recipeRedstonePlant.setIngredient('R', MaterialData.redstone);
        SpoutManager.getMaterialManager().registerSpoutRecipe(recipeRedstonePlant);
        
        Plugin spoutTest = this.getServer().getPluginManager().getPlugin("SpoutPlugin");        
    }
    
    
    
    private void setupPermissions() {
        
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        
        if (RedGlowTree.Permissions == null) {
            
            if (test != null) {
                RedGlowTree.Permissions = ((Permissions) test).getHandler();
                System.out.println("[RedGlowTree] Permission system detected!");
            } else {
                System.out.println("[RedGlowTree] Permission system not detected, defaulting to OP");
            }
        }
    }
}
