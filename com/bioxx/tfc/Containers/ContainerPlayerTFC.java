/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotArmorTFC;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Handlers.CraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.FoodCraftingHandler;
/*     */ import com.bioxx.tfc.Items.ItemTFCArmor;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.inventory.SlotCrafting;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ContainerPlayerTFC
/*     */   extends ContainerPlayer
/*     */ {
/*     */   private final EntityPlayer thePlayer;
/*     */   
/*     */   public ContainerPlayerTFC(InventoryPlayer playerInv, boolean par2, EntityPlayer player) {
/*  27 */     super(playerInv, par2, player);
/*  28 */     this.field_75181_e = new InventoryCrafting((Container)this, 3, 3);
/*  29 */     this.field_75151_b.clear();
/*  30 */     this.field_75153_a.clear();
/*  31 */     this.thePlayer = player;
/*  32 */     func_75146_a((Slot)new SlotCrafting(player, (IInventory)this.field_75181_e, this.field_75179_f, 0, 152, 36));
/*     */     
/*     */     int x;
/*     */     
/*  36 */     for (x = 0; x < 2; x++) {
/*     */       
/*  38 */       for (int y = 0; y < 2; y++) {
/*  39 */         func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*     */       }
/*     */     } 
/*  42 */     for (x = 0; x < playerInv.field_70460_b.length; x++) {
/*     */       
/*  44 */       int index = playerInv.func_70302_i_() - 3 - x;
/*     */       
/*  46 */       func_75146_a((Slot)new SlotArmorTFC(this, (IInventory)playerInv, index, 8, 8 + x * 18, x));
/*     */     } 
/*  48 */     PlayerInventory.buildInventoryLayout((Container)this, playerInv, 8, 90, false, true);
/*     */ 
/*     */     
/*  51 */     if (player.getEntityData().func_74764_b("craftingTable") || !player.field_70170_p.field_72995_K) {
/*     */       
/*  53 */       x = 2; int y = 0; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  54 */       x = 2; y = 1; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  55 */       x = 0; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  56 */       x = 1; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  57 */       x = 2; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  62 */       x = 2; int y = 0; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  63 */       x = 2; y = 1; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  64 */       x = 0; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  65 */       x = 1; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  66 */       x = 2; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*     */     } 
/*  68 */     PlayerInventory.addExtraEquipables((Container)this, playerInv, 8, 90, false);
/*  69 */     func_75130_a((IInventory)this.field_75181_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75130_a(IInventory iinventory) {
/*  78 */     super.func_75130_a(iinventory);
/*     */     
/*  80 */     Slot craftOut = this.field_75151_b.get(0);
/*  81 */     if (craftOut != null && craftOut.func_75216_d()) {
/*     */       
/*  83 */       ItemStack craftResult = craftOut.func_75211_c();
/*  84 */       if (craftResult != null)
/*     */       {
/*  86 */         if (craftResult.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC) {
/*  87 */           FoodCraftingHandler.updateOutput(this.thePlayer, craftResult, (IInventory)this.field_75181_e);
/*     */         } else {
/*  89 */           CraftingHandler.transferNBT(false, this.thePlayer, craftResult, (IInventory)this.field_75181_e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/*  97 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/*  99 */       super.func_75134_a(player);
/*     */       
/* 101 */       for (int i = 0; i < 9; i++) {
/*     */         
/* 103 */         ItemStack itemstack = this.field_75181_e.func_70304_b(i);
/* 104 */         if (itemstack != null) {
/* 105 */           player.func_71019_a(itemstack, false);
/*     */         }
/*     */       } 
/* 108 */       this.field_75179_f.func_70299_a(0, (ItemStack)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer player, int slotNum) {
/* 115 */     ItemStack origStack = null;
/* 116 */     Slot slot = this.field_75151_b.get(slotNum);
/* 117 */     Slot equipmentSlot = this.field_75151_b.get(50);
/*     */     
/* 119 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/* 121 */       ItemStack slotStack = slot.func_75211_c();
/* 122 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/* 125 */       if (slotNum == 0) {
/*     */         
/* 127 */         FoodCraftingHandler.preCraft(player, slotStack, (IInventory)this.field_75181_e);
/* 128 */         CraftingHandler.preCraft(player, slotStack, (IInventory)this.field_75181_e);
/*     */         
/* 130 */         if (!mergeItemStack(slotStack, 9, 45, true, true)) {
/* 131 */           return null;
/*     */         }
/* 133 */         slot.func_75220_a(slotStack, origStack);
/*     */       
/*     */       }
/* 136 */       else if ((slotNum >= 1 && slotNum < 5) || (player.getEntityData().func_74764_b("craftingTable") && slotNum >= 45 && slotNum < 50)) {
/*     */         
/* 138 */         if (!mergeItemStack(slotStack, 9, 45, true, false)) {
/* 139 */           return null;
/*     */         }
/*     */       }
/* 142 */       else if ((slotNum >= 5 && slotNum < 9) || slotNum == 50) {
/*     */         
/* 144 */         if (!mergeItemStack(slotStack, 9, 45, true, false)) {
/* 145 */           return null;
/*     */         }
/*     */       }
/* 148 */       else if (origStack.func_77973_b() instanceof ItemArmor) {
/*     */         
/* 150 */         int armorSlotNum = 5 + ((ItemArmor)origStack.func_77973_b()).field_77881_a;
/* 151 */         if (origStack.func_77973_b() instanceof ItemTFCArmor) {
/*     */           
/* 153 */           armorSlotNum = 5 + ((ItemTFCArmor)origStack.func_77973_b()).getUnadjustedArmorType();
/*     */           
/* 155 */           if (!((Slot)this.field_75151_b.get(armorSlotNum)).func_75216_d())
/*     */           {
/* 157 */             if (!mergeItemStack(slotStack, armorSlotNum, armorSlotNum + 1, false, false)) {
/* 158 */               return null;
/*     */             }
/*     */           }
/* 161 */         } else if (!((Slot)this.field_75151_b.get(armorSlotNum)).func_75216_d()) {
/*     */           
/* 163 */           if (!mergeItemStack(slotStack, armorSlotNum, armorSlotNum + 1, false, false)) {
/* 164 */             return null;
/*     */           }
/*     */         }
/*     */       
/* 168 */       } else if (!equipmentSlot.func_75216_d() && origStack.func_77973_b() instanceof IEquipable) {
/*     */         
/* 170 */         IEquipable equipment = (IEquipable)origStack.func_77973_b();
/* 171 */         if (equipment.getEquipType(origStack) == IEquipable.EquipType.BACK && (equipment == TFCItems.quiver || equipment.getTooHeavyToCarry(origStack)))
/*     */         {
/* 173 */           ItemStack backStack = slotStack.func_77946_l();
/* 174 */           backStack.field_77994_a = 1;
/* 175 */           equipmentSlot.func_75215_d(backStack);
/* 176 */           slotStack.field_77994_a--;
/*     */         }
/*     */       
/*     */       }
/* 180 */       else if (slotNum >= 9 && slotNum < 45 && origStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && !(origStack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) && !isCraftingGridFull()) {
/*     */         
/* 182 */         if (!mergeItemStack(slotStack, 1, 5, false, false) && slotStack.field_77994_a == 0)
/* 183 */           return null; 
/* 184 */         if (slotStack.field_77994_a > 0 && player.getEntityData().func_74764_b("craftingTable") && !func_75135_a(slotStack, 45, 50, false))
/* 185 */           return null; 
/* 186 */         if (slotStack.field_77994_a > 0 && slotNum >= 9 && slotNum < 36) {
/*     */           
/* 188 */           if (!mergeItemStack(slotStack, 36, 45, false, false)) {
/* 189 */             return null;
/*     */           }
/* 191 */         } else if (slotStack.field_77994_a > 0 && slotNum >= 36 && slotNum < 45) {
/*     */           
/* 193 */           if (!mergeItemStack(slotStack, 9, 36, false, false)) {
/* 194 */             return null;
/*     */           }
/*     */         }
/*     */       
/* 198 */       } else if (slotNum >= 9 && slotNum < 36) {
/*     */         
/* 200 */         if (!mergeItemStack(slotStack, 36, 45, false, false)) {
/* 201 */           return null;
/*     */         }
/*     */       }
/* 204 */       else if (slotNum >= 36 && slotNum < 45) {
/*     */         
/* 206 */         if (!mergeItemStack(slotStack, 9, 36, false, false)) {
/* 207 */           return null;
/*     */         }
/*     */       } 
/* 210 */       if (slotStack.field_77994_a <= 0) {
/* 211 */         slot.func_75215_d(null);
/*     */       } else {
/* 213 */         slot.func_75218_e();
/*     */       } 
/* 215 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 216 */         return null;
/*     */       }
/* 218 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 221 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_75144_a(int sourceSlotID, int destSlotID, int clickType, EntityPlayer p) {
/* 228 */     if (sourceSlotID >= 0 && sourceSlotID < this.field_75151_b.size()) {
/*     */       
/* 230 */       Slot sourceSlot = this.field_75151_b.get(sourceSlotID);
/* 231 */       ItemStack slotStack = sourceSlot.func_75211_c();
/*     */ 
/*     */       
/* 234 */       if (clickType == 2 && sourceSlotID == 0 && slotStack != null) {
/*     */         
/* 236 */         CraftingHandler.preCraft(p, slotStack, (IInventory)this.field_75181_e);
/*     */       
/*     */       }
/* 239 */       else if (clickType == 7 && sourceSlotID >= 9 && sourceSlotID < 45) {
/*     */         
/* 241 */         if (sourceSlot.func_82869_a(p))
/*     */         {
/* 243 */           Slot destSlot = this.field_75151_b.get(destSlotID);
/* 244 */           destSlot.func_75215_d(slotStack);
/* 245 */           sourceSlot.func_75215_d(null);
/* 246 */           return null;
/*     */         }
/*     */       
/*     */       }
/* 250 */       else if (clickType == 1 && sourceSlotID == 0 && isInventoryFull() && slotStack != null && slotStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/* 251 */         return null;
/*     */       } 
/* 253 */     }  return super.func_75144_a(sourceSlotID, destSlotID, clickType, p);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCraftingGridFull() {
/* 258 */     for (int i = 0; i < this.field_75181_e.func_70302_i_(); i++) {
/*     */       
/* 260 */       if (this.field_75181_e.func_70301_a(i) == null)
/* 261 */         return false; 
/*     */     } 
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isInventoryFull() {
/* 269 */     for (int i = 9; i < 45; i++) {
/*     */       
/* 271 */       if (((Slot)this.field_75151_b.get(i)).func_75211_c() == null)
/* 272 */         return false; 
/*     */     } 
/* 274 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer getPlayer() {
/* 279 */     return this.thePlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack is, int slotStart, int slotFinish, boolean reverseOrder, boolean craftingOutput) {
/* 284 */     boolean merged = false;
/* 285 */     int slotIndex = slotStart;
/*     */     
/* 287 */     if (reverseOrder) {
/* 288 */       slotIndex = slotFinish - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 293 */     if (is.func_77985_e())
/*     */     {
/* 295 */       while (is.field_77994_a > 0 && ((!reverseOrder && slotIndex < slotFinish) || (reverseOrder && slotIndex >= slotStart))) {
/*     */         
/* 297 */         Slot slot = this.field_75151_b.get(slotIndex);
/* 298 */         ItemStack slotstack = slot.func_75211_c();
/*     */         
/* 300 */         if (slotstack != null && slotstack.func_77973_b() == is.func_77973_b() && is
/*     */           
/* 302 */           .func_77960_j() == slotstack.func_77960_j() && ItemStack.func_77970_a(is, slotstack) && slotstack.field_77994_a < slot.func_75219_a()) {
/*     */           
/* 304 */           int mergedStackSize = is.field_77994_a + getSmaller(slotstack.field_77994_a, slot.func_75219_a());
/*     */ 
/*     */           
/* 307 */           if (mergedStackSize <= is.func_77976_d() && mergedStackSize <= slot.func_75219_a()) {
/*     */             
/* 309 */             is.field_77994_a = 0;
/* 310 */             slotstack.field_77994_a = mergedStackSize;
/* 311 */             slot.func_75218_e();
/* 312 */             merged = true;
/*     */           
/*     */           }
/* 315 */           else if (!craftingOutput && slotstack.field_77994_a < is.func_77976_d() && slotstack.field_77994_a < slot.func_75219_a()) {
/*     */ 
/*     */             
/* 318 */             if (slot.func_75219_a() >= is.func_77976_d()) {
/*     */               
/* 320 */               is.field_77994_a -= is.func_77976_d() - slotstack.field_77994_a;
/* 321 */               slotstack.field_77994_a = is.func_77976_d();
/* 322 */               slot.func_75218_e();
/* 323 */               merged = true;
/*     */             
/*     */             }
/* 326 */             else if (slot.func_75219_a() < is.func_77976_d()) {
/*     */               
/* 328 */               is.field_77994_a -= slot.func_75219_a() - slotstack.field_77994_a;
/* 329 */               slotstack.field_77994_a = slot.func_75219_a();
/* 330 */               slot.func_75218_e();
/* 331 */               merged = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 336 */         if (reverseOrder) {
/* 337 */           slotIndex--; continue;
/*     */         } 
/* 339 */         slotIndex++;
/*     */       } 
/*     */     }
/*     */     
/* 343 */     if (is.field_77994_a > 0) {
/*     */       
/* 345 */       if (reverseOrder) {
/* 346 */         slotIndex = slotFinish - 1;
/*     */       } else {
/* 348 */         slotIndex = slotStart;
/*     */       } 
/* 350 */       while ((!reverseOrder && slotIndex < slotFinish) || (reverseOrder && slotIndex >= slotStart)) {
/*     */         
/* 352 */         Slot slot = this.field_75151_b.get(slotIndex);
/* 353 */         ItemStack slotstack = slot.func_75211_c();
/* 354 */         if (slotstack == null && slot.func_75214_a(is) && slot.func_75219_a() < is.field_77994_a) {
/*     */           
/* 356 */           ItemStack copy = is.func_77946_l();
/* 357 */           copy.field_77994_a = slot.func_75219_a();
/* 358 */           is.field_77994_a -= slot.func_75219_a();
/* 359 */           slot.func_75215_d(copy);
/* 360 */           slot.func_75218_e();
/* 361 */           merged = true;
/*     */           
/*     */           break;
/*     */         } 
/* 365 */         if (slotstack == null && slot.func_75214_a(is)) {
/*     */           
/* 367 */           slot.func_75215_d(is.func_77946_l());
/* 368 */           slot.func_75218_e();
/* 369 */           is.field_77994_a = 0;
/* 370 */           merged = true;
/*     */           
/*     */           break;
/*     */         } 
/* 374 */         if (reverseOrder) {
/* 375 */           slotIndex--; continue;
/*     */         } 
/* 377 */         slotIndex++;
/*     */       } 
/*     */     } 
/*     */     
/* 381 */     return merged;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSmaller(int i, int j) {
/* 386 */     if (i < j) {
/* 387 */       return i;
/*     */     }
/* 389 */     return j;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\ContainerPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */