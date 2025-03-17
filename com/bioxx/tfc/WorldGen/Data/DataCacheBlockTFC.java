/*    */ package com.bioxx.tfc.WorldGen.Data;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.DataLayer;
/*    */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
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
/*    */ public class DataCacheBlockTFC
/*    */ {
/*    */   public DataLayer[] data;
/*    */   public int xPosition;
/*    */   public int zPosition;
/*    */   public long lastAccessTime;
/*    */   private int index;
/*    */   
/*    */   public DataCacheBlockTFC(DataCache datacache, GenLayerTFC indexLayers, int par2, int par3, int ind) {
/* 24 */     this.data = new DataLayer[256];
/* 25 */     this.xPosition = par2;
/* 26 */     this.zPosition = par3;
/* 27 */     this.index = ind;
/* 28 */     DataCache.getChunkManager(datacache).getDataLayerAt(datacache, this.data, indexLayers, par2 << 4, par3 << 4, 16, 16, false, this.index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DataCacheBlockTFC(DataCache datacache, int par2, int par3) {
/* 34 */     this.xPosition = par2;
/* 35 */     this.zPosition = par3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DataLayer getDataLayerAt(int par1, int par2) {
/* 43 */     return this.data[par1 & 0xF | (par2 & 0xF) << 4];
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\WorldGen\Data\DataCacheBlockTFC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */