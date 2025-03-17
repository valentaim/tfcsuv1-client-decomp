/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import com.bioxx.tfc.api.Food;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BarrelBriningRecipe
/*    */   extends BarrelRecipe
/*    */ {
/*    */   public BarrelBriningRecipe(FluidStack inputFluid, FluidStack outputFluid) {
/* 15 */     super(null, inputFluid, null, outputFluid, 4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean matches(ItemStack item, FluidStack fluid) {
/* 21 */     if (item != null && item.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && !Food.isBrined(item)) {
/*    */       
/* 23 */       float w = Food.getWeight(item);
/* 24 */       if (fluid.isFluidEqual(this.recipeFluid) && w <= 1.0F * (fluid.amount / this.recipeFluid.amount))
/*    */       {
/* 26 */         return Boolean.valueOf(true);
/*    */       }
/*    */     } 
/* 29 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Crafting\BarrelBriningRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */