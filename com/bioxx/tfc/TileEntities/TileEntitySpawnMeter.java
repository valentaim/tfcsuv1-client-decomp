/*    */ package com.bioxx.tfc.TileEntities;
/*    */ 
/*    */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Core.TFC_Time;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntitySpawnMeter
/*    */   extends TileEntity
/*    */ {
/* 17 */   private long timer = TFC_Time.getTotalTicks();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145845_h() {
/* 23 */     if (!this.field_145850_b.field_72995_K)
/*    */     {
/* 25 */       if (this.timer < TFC_Time.getTotalTicks()) {
/*    */         
/* 27 */         this.timer += 1000L;
/* 28 */         ChunkData cd = TFC_Core.getCDM(this.field_145850_b).getData(this.field_145851_c >> 4, this.field_145849_e >> 4);
/* 29 */         if (cd != null) {
/*    */           
/* 31 */           int protection = cd.spawnProtection;
/* 32 */           int meta = 0;
/* 33 */           meta = (protection > 384) ? 8 : (protection / 48);
/* 34 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta, 2);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 43 */     super.func_145839_a(nbttagcompound);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 49 */     super.func_145841_b(nbttagcompound);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet func_145844_m() {
/* 55 */     NBTTagCompound nbt = new NBTTagCompound();
/* 56 */     func_145841_b(nbt);
/* 57 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 63 */     func_145839_a(pkt.func_148857_g());
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TileEntitySpawnMeter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */