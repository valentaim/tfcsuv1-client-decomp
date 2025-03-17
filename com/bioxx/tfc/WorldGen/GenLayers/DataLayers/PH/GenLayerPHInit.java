/*    */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.PH;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*    */ 
/*    */ public class GenLayerPHInit
/*    */   extends GenLayerTFC
/*    */ {
/*    */   public GenLayerPHInit(long par1) {
/*  9 */     super(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] func_75904_a(int par1, int par2, int maxX, int maxZ) {
/* 15 */     int[] outCache = new int[maxX * maxZ];
/*    */     
/* 17 */     for (int z = 0; z < maxZ; z++) {
/*    */       
/* 19 */       for (int x = 0; x < maxX; x++) {
/*    */         
/* 21 */         func_75903_a((par1 + x), (par2 + z));
/* 22 */         int out = GenPHLayer.MIN + func_75902_a(4);
/* 23 */         outCache[x + z * maxX] = out;
/*    */       } 
/*    */     } 
/*    */     
/* 27 */     return outCache;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\PH\GenLayerPHInit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */