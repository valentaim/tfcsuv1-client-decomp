/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerSpecialCrafting;
/*    */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ 
/*    */ 
/*    */ public class KnappingUpdatePacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private byte index;
/*    */   
/*    */   public KnappingUpdatePacket() {}
/*    */   
/*    */   public KnappingUpdatePacket(int i) {
/* 19 */     this.index = (byte)i;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 25 */     buffer.writeByte(this.index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 31 */     this.index = buffer.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 42 */     if (player.field_71070_bA != null && player.field_71070_bA instanceof ContainerSpecialCrafting) {
/*    */       
/* 44 */       ((ContainerSpecialCrafting)player.field_71070_bA).craftMatrix.func_70299_a(this.index, (PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player)).specialCraftingTypeAlternate);
/* 45 */       ((ContainerSpecialCrafting)player.field_71070_bA).func_75130_a((IInventory)((ContainerSpecialCrafting)player.field_71070_bA).craftMatrix);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\Network\KnappingUpdatePacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */