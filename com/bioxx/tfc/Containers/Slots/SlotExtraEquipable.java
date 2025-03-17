/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotExtraEquipable
/*    */   extends Slot
/*    */ {
/*    */   public final IEquipable.EquipType armorType;
/*    */   
/*    */   public SlotExtraEquipable(IInventory inv, int index, int x, int y, IEquipable.EquipType armortype) {
/* 14 */     super(inv, index, x, y);
/* 15 */     this.armorType = armortype;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_75219_a() {
/* 25 */     return 1;
/*    */   }
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
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack is) {
/* 41 */     if (is != null && is.func_77973_b() instanceof IEquipable && (
/* 42 */       (IEquipable)is.func_77973_b()).getEquipType(is) == this.armorType) {
/* 43 */       return true;
/*    */     }
/* 45 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotExtraEquipable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */