/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlotQuern
/*    */   extends Slot
/*    */ {
/*    */   public SlotQuern(IInventory iinventory, int i, int j, int k) {
/* 13 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack is) {
/* 19 */     return (is.func_77973_b() == TFCItems.quern);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_75219_a() {
/* 25 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75215_d(ItemStack is) {
/* 31 */     if (is != null) is.field_77994_a = 1; 
/* 32 */     super.func_75215_d(is);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotQuern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */