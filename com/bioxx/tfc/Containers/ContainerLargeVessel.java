/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotChest;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import cpw.mods.fml.common.FMLLog;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerLargeVessel
/*     */   extends ContainerBarrel
/*     */ {
/*     */   TileEntity vessel;
/*     */   
/*     */   public ContainerLargeVessel(InventoryPlayer inventoryplayer, TEVessel tileentitybarrel, World world, int x, int y, int z, int tab) {
/*  25 */     super(inventoryplayer, (TEBarrel)tileentitybarrel, world, x, y, z, tab);
/*  26 */     this.vessel = (TileEntity)tileentitybarrel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buildLayout() {
/*  32 */     if (this.guiTab == 0) {
/*     */ 
/*     */       
/*  35 */       if (!this.barrel.getSealed()) {
/*  36 */         func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, 0, 80, 29)).setSize(EnumSize.MEDIUM).addItemException(ContainerBarrel.getExceptions()));
/*     */       } else {
/*  38 */         func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, 0, 80, 29));
/*     */       } 
/*  40 */     } else if (this.guiTab == 1) {
/*     */       
/*  42 */       for (int i = 0; i < 3; i++) {
/*     */         
/*  44 */         for (int k = 0; k < 3; k++) {
/*     */           
/*  46 */           if (!this.barrel.getSealed()) {
/*  47 */             func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, k + i * 3, 71 + i * 18, 17 + k * 18)).setSize(EnumSize.MEDIUM).addItemException(ContainerChestTFC.getExceptions()));
/*     */           } else {
/*  49 */             func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, k + i * 3, 71 + i * 18, 17 + k * 18));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  58 */     if (this.vessel.func_145831_w() instanceof cuchaz.ships.ShipWorld) {
/*  59 */       if (!player.field_70170_p.field_72995_K) FMLLog.getLogger().warn("Ship Duper found: " + player.getDisplayName()); 
/*  60 */       return null;
/*     */     } 
/*  62 */     ItemStack origStack = null;
/*  63 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  65 */     if (!this.barrel.getSealed() && slot != null && slot.func_75216_d()) {
/*     */       
/*  67 */       ItemStack slotStack = slot.func_75211_c();
/*  68 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/*  71 */       if (slotNum < 1 && this.guiTab == 0) {
/*     */         
/*  73 */         if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
/*  74 */           return null;
/*     */         }
/*     */       }
/*  77 */       else if (slotNum < 9 && this.guiTab == 1) {
/*     */         
/*  79 */         if (!func_75135_a(slotStack, 9, this.field_75151_b.size(), true)) {
/*  80 */           return null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  85 */       else if (this.guiTab == 1) {
/*     */         
/*  87 */         if (!func_75135_a(slotStack, 0, 9, false)) {
/*  88 */           return null;
/*     */         }
/*     */       }
/*  91 */       else if (this.guiTab == 0) {
/*     */         
/*  93 */         if (!func_75135_a(slotStack, 0, 1, false)) {
/*  94 */           return null;
/*     */         }
/*     */       } 
/*     */       
/*  98 */       if (slotStack.field_77994_a <= 0) {
/*  99 */         slot.func_75215_d(null);
/*     */       } else {
/* 101 */         slot.func_75218_e();
/*     */       } 
/* 103 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 104 */         return null;
/*     */       }
/* 106 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 109 */     return origStack;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\ContainerLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */