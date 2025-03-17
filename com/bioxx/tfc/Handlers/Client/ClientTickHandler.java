/*    */ package com.bioxx.tfc.Handlers.Client;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Time;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientTickHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onClientPlayerTick(TickEvent.PlayerTickEvent event) {
/* 24 */     if (event.phase == TickEvent.Phase.END) {
/*    */ 
/*    */       
/* 27 */       EntityPlayer player = event.player;
/* 28 */       World world = player.field_70170_p;
/*    */ 
/*    */       
/* 31 */       TFC_Time.updateTime(world);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\Client\ClientTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */