/*    */ package com.bioxx.tfc.GUI;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerLogPile;
/*    */ import com.bioxx.tfc.TileEntities.TELogPile;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class GuiLogPile
/*    */   extends GuiContainerTFC
/*    */ {
/* 13 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_logpile.png");
/*    */ 
/*    */   
/*    */   public GuiLogPile(InventoryPlayer inventoryplayer, TELogPile te, World world, int x, int y, int z) {
/* 17 */     super((Container)new ContainerLogPile(inventoryplayer, te, world, x, y, z), 176, 85);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float f, int i, int j) {
/* 23 */     drawGui(texture);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\GUI\GuiLogPile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */