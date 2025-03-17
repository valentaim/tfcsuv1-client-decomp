/*    */ package com.bioxx.tfc.TileEntities;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntitySapling
/*    */   extends TileEntity
/*    */ {
/*    */   public long growTime;
/*    */   
/*    */   public boolean canUpdate() {
/* 19 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 24 */     super.func_145839_a(nbttagcompound);
/* 25 */     this.growTime = nbttagcompound.func_74763_f("growTime");
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 30 */     super.func_145841_b(nbttagcompound);
/* 31 */     nbttagcompound.func_74772_a("growTime", this.growTime);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet func_145844_m() {
/* 37 */     NBTTagCompound nbt = new NBTTagCompound();
/* 38 */     func_145841_b(nbt);
/* 39 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 45 */     func_145839_a(pkt.func_148857_g());
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TileEntitySapling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */