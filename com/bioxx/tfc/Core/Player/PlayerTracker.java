/*     */ package com.bioxx.tfc.Core.Player;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.ConfigSyncPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.InitClientWorldPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerTracker
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
/*  31 */     (PlayerManagerTFC.getInstance()).players.add(new PlayerInfo(event.player
/*  32 */           .func_70005_c_(), event.player
/*  33 */           .func_110124_au()));
/*  34 */     TFC_ConfigFiles.reloadAll();
/*  35 */     InitClientWorldPacket initClientWorldPacket = new InitClientWorldPacket(event.player);
/*  36 */     TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)initClientWorldPacket, (EntityPlayerMP)event.player);
/*  37 */     TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)new ConfigSyncPacket(), (EntityPlayerMP)event.player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
/*  51 */     TerraFirmaCraft.proxy.onClientLogin();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onClientDisconnect(FMLNetworkEvent.ServerDisconnectionFromClientEvent event) {}
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/*  62 */     float foodLevel = event.player.field_70170_p.field_73012_v.nextFloat() * 12.0F + 12.0F;
/*  63 */     FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(event.player);
/*  64 */     foodstats.setFoodLevel(foodLevel);
/*  65 */     TFC_Core.setPlayerFoodStats(event.player, foodstats);
/*  66 */     event.player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0D);
/*  67 */     event.player.func_70606_j(1000.0F * (0.25F + event.player.field_70170_p.field_73012_v.nextFloat() * 0.25F));
/*     */     
/*  69 */     PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(event.player);
/*  70 */     if (pi.tempSkills != null) {
/*  71 */       TFC_Core.setSkillStats(event.player, pi.tempSkills);
/*     */     }
/*     */     
/*  74 */     if (pi.tempEquipment != null && event.player.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
/*     */       
/*  76 */       InventoryPlayerTFC invPlayer = (InventoryPlayerTFC)event.player.field_71071_by;
/*  77 */       invPlayer.extraEquipInventory = (ItemStack[])pi.tempEquipment.clone();
/*  78 */       pi.tempEquipment = null;
/*     */     } 
/*     */ 
/*     */     
/*  82 */     PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(event.player, 3);
/*  83 */     TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)event.player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void notifyPickup(PlayerEvent.ItemPickupEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerTossEvent(ItemTossEvent event) {
/* 111 */     if (event.entityItem == null)
/* 112 */       event.setCanceled(true); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Core\Player\PlayerTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */