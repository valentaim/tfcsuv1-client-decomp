/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*    */ import com.bioxx.tfc.api.Food;
/*    */ import com.bioxx.tfc.api.Interfaces.IFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BarrelVinegarRecipe
/*    */   extends BarrelRecipe
/*    */ {
/*    */   public BarrelVinegarRecipe(FluidStack inputFluid, FluidStack outputFluid) {
/* 16 */     super(null, inputFluid, null, outputFluid);
/* 17 */     setMinTechLevel(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean matches(ItemStack item, FluidStack fluid) {
/* 23 */     if (item != null && item.func_77973_b() instanceof IFood)
/*    */     {
/* 25 */       if (fluid.isFluidEqual(this.recipeFluid) && ((IFood)item.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit && Food.getWeight(item) >= 1.0F * (fluid.amount / 100))
/*    */       {
/* 27 */         return Boolean.valueOf(true);
/*    */       }
/*    */     }
/* 30 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Crafting\BarrelVinegarRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */