/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestPacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private String msg;
/*    */   
/*    */   public TestPacket() {}
/*    */   
/*    */   public TestPacket(String s) {
/* 20 */     this.msg = s;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 26 */     ByteBufUtils.writeUTF8String(buffer, this.msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 33 */     this.msg = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {
/* 39 */     TerraFirmaCraft.LOG.info("++++++++++++Client: " + this.msg);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 45 */     TerraFirmaCraft.LOG.info("++++++++++++Server: " + this.msg);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\Network\TestPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */