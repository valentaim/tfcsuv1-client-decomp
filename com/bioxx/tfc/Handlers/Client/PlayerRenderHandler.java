/*    */ package com.bioxx.tfc.Handlers.Client;
/*    */ 
/*    */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*    */ import com.bioxx.tfc.Render.RenderLargeItem;
/*    */ import com.bioxx.tfc.Render.RenderQuiver;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerRenderHandler
/*    */ {
/* 24 */   public static final RenderQuiver RENDER_QUIVER = new RenderQuiver();
/* 25 */   public static final RenderLargeItem RENDER_LARGE = new RenderLargeItem();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onPlayerRenderTick(RenderPlayerEvent.Specials.Pre e) {
/* 35 */     EntityLivingBase el = e.entityLiving;
/* 36 */     if (el instanceof EntityPlayer && 
/* 37 */       ((EntityPlayer)el).field_71071_by instanceof InventoryPlayerTFC) {
/* 38 */       ItemStack[] equipables = ((InventoryPlayerTFC)((EntityPlayer)el).field_71071_by).extraEquipInventory;
/* 39 */       for (ItemStack i : equipables) {
/* 40 */         if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.ItemQuiver) {
/* 41 */           RENDER_QUIVER.render(e.entityLiving, i, e);
/*    */         }
/* 43 */         else if (i != null) {
/* 44 */           RENDER_LARGE.render(el, i, e);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onPlayerTick(TickEvent.PlayerTickEvent e) {}
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\Client\PlayerRenderHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */