/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.Interfaces.ISize;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlotSizeMedium
/*    */   extends SlotSize
/*    */ {
/*    */   public SlotSizeMedium(IInventory iinventory, int i, int j, int k) {
/* 12 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 18 */     if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize)
/* 19 */       return true; 
/* 20 */     if (!(itemstack.func_77973_b() instanceof ISize))
/* 21 */       return true; 
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotSizeMedium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */