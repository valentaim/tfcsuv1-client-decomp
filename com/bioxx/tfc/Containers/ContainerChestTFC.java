/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotChest;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.TileEntities.TEChest;
/*     */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.registry.GameData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerChestTFC
/*     */   extends ContainerTFC
/*     */ {
/*     */   private IInventory lowerChestInventory;
/*     */   private int numRows;
/*     */   
/*     */   public ContainerChestTFC(IInventory playerInv, IInventory chestInv, World world, int x, int y, int z) {
/*  31 */     TEChest chest = (TEChest)chestInv;
/*  32 */     this.lowerChestInventory = chestInv;
/*     */     
/*  34 */     if (chest.field_145991_k != null) {
/*  35 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145991_k, chestInv);
/*     */     }
/*  37 */     if (chest.field_145990_j != null) {
/*  38 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145990_j);
/*     */     }
/*  40 */     if (chest.field_145992_i != null) {
/*  41 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145992_i, chestInv);
/*     */     }
/*  43 */     if (chest.field_145988_l != null) {
/*  44 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145988_l);
/*     */     }
/*  46 */     this.numRows = this.lowerChestInventory.func_70302_i_() / 9;
/*  47 */     this.lowerChestInventory.func_70295_k_();
/*  48 */     int var3 = (this.numRows - 4) * 18;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     for (int var4 = 0; var4 < this.numRows; var4++) {
/*     */       
/*  56 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  58 */         func_75146_a((Slot)(new SlotChest(this.lowerChestInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18)).addItemException(getExceptions()));
/*     */       }
/*     */     } 
/*     */     
/*  62 */     PlayerInventory.buildInventoryLayout(this, (InventoryPlayer)playerInv, 8, var3 + 109, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Item> getExceptions() {
/*  67 */     List<Item> exceptions = new ArrayList<Item>();
/*  68 */     for (Item ingot : TEIngotPile.getIngots())
/*     */     {
/*  70 */       exceptions.add(ingot);
/*     */     }
/*  72 */     exceptions.add(TFCItems.logs);
/*  73 */     exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
/*  74 */     exceptions.add(Item.func_150898_a(TFCBlocks.vessel));
/*     */     
/*  76 */     Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
/*  77 */     if (fromcook != null) exceptions.add(fromcook); 
/*  78 */     return exceptions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
/*  84 */     return this.lowerChestInventory.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  93 */     ItemStack origStack = null;
/*  94 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  96 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  98 */       ItemStack slotStack = slot.func_75211_c();
/*  99 */       origStack = slotStack.func_77946_l();
/* 100 */       int chestSlotCount = this.numRows * 9;
/*     */ 
/*     */       
/* 103 */       if (slotNum < chestSlotCount) {
/*     */         
/* 105 */         if (!func_75135_a(slotStack, chestSlotCount, this.field_75151_b.size(), true)) {
/* 106 */           return null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 111 */       else if (!func_75135_a(slotStack, 0, chestSlotCount, false)) {
/* 112 */         return null;
/*     */       } 
/*     */       
/* 115 */       if (slotStack.field_77994_a <= 0) {
/* 116 */         slot.func_75215_d(null);
/*     */       } else {
/* 118 */         slot.func_75218_e();
/*     */       } 
/* 120 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 121 */         return null;
/*     */       }
/* 123 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 126 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/* 135 */     super.func_75134_a(par1EntityPlayer);
/* 136 */     this.lowerChestInventory.func_70305_f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IInventory getLowerChestInventory() {
/* 144 */     return this.lowerChestInventory;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\ContainerChestTFC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */