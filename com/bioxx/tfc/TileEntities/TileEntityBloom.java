/*    */ package com.bioxx.tfc.TileEntities;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityBloom
/*    */   extends TileEntity
/*    */ {
/* 12 */   public int size = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(int iron) {
/* 17 */     this.size = iron;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 23 */     super.func_145841_b(nbttagcompound);
/* 24 */     nbttagcompound.func_74768_a("size", this.size);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 30 */     super.func_145839_a(nbttagcompound);
/* 31 */     this.size = nbttagcompound.func_74762_e("size");
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TileEntityBloom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */