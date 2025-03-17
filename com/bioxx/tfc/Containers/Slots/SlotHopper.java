/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Interfaces.ISize;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotHopper
/*    */   extends Slot
/*    */ {
/* 21 */   private EnumSize size = EnumSize.LARGE;
/*    */   
/*    */   private List<Item> exceptions;
/*    */ 
/*    */   
/*    */   public SlotHopper(IInventory iinventory, int i, int j, int k) {
/* 27 */     super(iinventory, i, j, k);
/* 28 */     this.exceptions = new ArrayList<Item>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 33 */     if (itemstack.func_77973_b() instanceof net.minecraft.item.ItemTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemTerraTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemLogs || itemstack
/* 34 */       .func_77973_b() instanceof net.minecraft.item.ItemHoe || (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.SMALL.stackSize)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     boolean except = this.exceptions.contains(itemstack.func_77973_b());
/* 39 */     if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize && !except)
/*    */     {
/* 41 */       return true;
/*    */     }
/* 43 */     if (!(itemstack.func_77973_b() instanceof ISize) && !except) {
/* 44 */       return true;
/*    */     }
/*    */     
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotHopper setSize(EnumSize s) {
/* 52 */     this.size = s;
/* 53 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotHopper addItemException(List<Item> ex) {
/* 58 */     this.exceptions.addAll(ex);
/* 59 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */