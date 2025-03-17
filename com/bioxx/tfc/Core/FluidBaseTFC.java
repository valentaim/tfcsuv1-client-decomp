/*    */ package com.bioxx.tfc.Core;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ public class FluidBaseTFC
/*    */   extends Fluid {
/*    */   private int color;
/*    */   
/*    */   public FluidBaseTFC(String fluidName) {
/* 13 */     super(fluidName);
/*    */ 
/*    */     
/* 16 */     this.color = 16777215;
/*    */   }
/*    */   
/*    */   public FluidBaseTFC setBaseColor(int c) {
/* 20 */     this.color = c;
/* 21 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColor(FluidStack fs) {
/* 27 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColor() {
/* 33 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon getStillIcon() {
/* 39 */     if (this.stillIcon == null)
/* 40 */       return TFCBlocks.hotWater.func_149691_a(0, 0); 
/* 41 */     return this.stillIcon;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon getFlowingIcon() {
/* 47 */     if (this.flowingIcon == null)
/* 48 */       return TFCBlocks.hotWater.func_149691_a(2, 0); 
/* 49 */     return this.flowingIcon;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Core\FluidBaseTFC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */