/*     */ package com.bioxx.tfc.Containers.Slots;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.GUI.GuiKnapping;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlotCraftingMetal
/*     */   extends Slot
/*     */ {
/*     */   private final IInventory craftMatrix;
/*     */   private EntityPlayer thePlayer;
/*     */   private List<Item> valids;
/*     */   private Container container;
/*     */   
/*     */   public SlotCraftingMetal(EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
/*  28 */     super(iinventory1, i, j, k);
/*  29 */     this.thePlayer = entityplayer;
/*  30 */     this.craftMatrix = iinventory;
/*  31 */     this.valids = new ArrayList<Item>();
/*     */   }
/*     */ 
/*     */   
/*     */   public SlotCraftingMetal(Container container, EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
/*  36 */     super(iinventory1, i, j, k);
/*  37 */     this.container = container;
/*  38 */     this.thePlayer = entityplayer;
/*  39 */     this.craftMatrix = iinventory;
/*  40 */     this.valids = new ArrayList<Item>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75218_e() {
/*  46 */     super.func_75218_e();
/*  47 */     if (this.field_75224_c.func_70301_a(0) != null)
/*     */     {
/*     */       
/*  50 */       if (this.valids.contains(func_75211_c().func_77973_b()) && this.container != null && func_75211_c().func_77960_j() == (PlayerManagerTFC.getInstance().getPlayerInfoFromName(this.thePlayer.getDisplayName())).specialCraftingType.func_77960_j())
/*     */       {
/*     */         
/*  53 */         this.container.func_75130_a(this.craftMatrix);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75214_a(ItemStack itemstack) {
/*  61 */     if (this.valids.contains(itemstack.func_77973_b()) && this.container != null)
/*  62 */       this.container.func_75130_a(this.craftMatrix); 
/*  63 */     return this.valids.contains(itemstack.func_77973_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValidity(Item item, boolean TF) {
/*  68 */     if (TF) {
/*     */       
/*  70 */       if (!this.valids.contains(item)) {
/*  71 */         this.valids.add(item);
/*     */       
/*     */       }
/*     */     }
/*  75 */     else if (this.valids.contains(item)) {
/*  76 */       this.valids.remove(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82870_a(EntityPlayer player, ItemStack itemstack) {
/*  83 */     itemstack.func_77980_a(this.thePlayer.field_70170_p, this.thePlayer, this.field_75222_d);
/*  84 */     TerraFirmaCraft.proxy.takenFromCrafting(this.thePlayer, itemstack, this.craftMatrix);
/*     */     
/*  86 */     for (int i = 0; i < this.craftMatrix.func_70302_i_(); i++) {
/*     */       
/*  88 */       ItemStack itemstack1 = this.craftMatrix.func_70301_a(i);
/*  89 */       if (itemstack1 != null) {
/*     */         
/*  91 */         this.craftMatrix.func_70298_a(i, 1);
/*  92 */         if (player.field_70170_p.field_72995_K && player.field_71070_bA instanceof com.bioxx.tfc.Containers.ContainerSpecialCrafting) {
/*  93 */           ((GuiKnapping)(Minecraft.func_71410_x()).field_71462_r).resetButton(i);
/*     */         }
/*  95 */         if (itemstack1.func_77973_b().func_77668_q() != null) {
/*     */           
/*  97 */           ItemStack itemstack2 = new ItemStack(itemstack1.func_77973_b().func_77668_q());
/*  98 */           if (!itemstack1.func_77973_b().func_77630_h(itemstack1) || !this.thePlayer.field_71071_by.func_70441_a(itemstack2))
/*     */           {
/* 100 */             if (this.craftMatrix.func_70301_a(i) == null) {
/* 101 */               this.craftMatrix.func_70299_a(i, itemstack2);
/*     */             } else {
/* 103 */               this.thePlayer.func_70099_a(itemstack2, 0.0F);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Containers\Slots\SlotCraftingMetal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */