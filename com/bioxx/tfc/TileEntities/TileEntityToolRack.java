/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityToolRack
/*     */   extends NetworkTileEntity
/*     */   implements IInventory
/*     */ {
/*  25 */   public ItemStack[] storage = new ItemStack[4];
/*  26 */   public byte woodType = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public void addContents(int index, ItemStack is) {
/*  31 */     if (this.storage[index] == null) {
/*  32 */       this.storage[index] = is;
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearContents() {
/*  37 */     this.storage[0] = null;
/*  38 */     this.storage[1] = null;
/*  39 */     this.storage[2] = null;
/*  40 */     this.storage[3] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  47 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*  48 */     return bb;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contentsMatch(int index, ItemStack is) {
/*  53 */     if (this.storage[index] != null && this.storage[index].func_77973_b() == is.func_77973_b() && this.storage[index].func_77960_j() == is.func_77960_j() && (this.storage[index]).field_77994_a < this.storage[index].func_77976_d())
/*     */     {
/*     */ 
/*     */       
/*  57 */       return true;
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  65 */     if (this.storage[i] != null) {
/*     */       
/*  67 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/*  69 */         ItemStack itemstack = this.storage[i];
/*  70 */         this.storage[i] = null;
/*  71 */         return itemstack;
/*     */       } 
/*  73 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/*  74 */       if ((this.storage[i]).field_77994_a == 0)
/*  75 */         this.storage[i] = null; 
/*  76 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/*  86 */     float f3 = 0.05F;
/*     */     
/*  88 */     Random rand = new Random();
/*  89 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/*  90 */     float f1 = rand.nextFloat() * 0.8F + 0.4F;
/*  91 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/*  93 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/*  95 */       if (this.storage[i] != null) {
/*     */         
/*  97 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/*  98 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/*  99 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 100 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 101 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 102 */         this.storage[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectItem(int index, int dir) {
/* 109 */     float f3 = 0.05F;
/*     */     
/* 111 */     Random rand = new Random();
/* 112 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 113 */     float f1 = rand.nextFloat() * 0.2F + 0.1F;
/* 114 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 116 */     if (this.storage[index] != null) {
/*     */       
/* 118 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
/* 119 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 120 */       entityitem.field_70181_x = 0.0D;
/* 121 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 122 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/* 123 */       this.storage[index] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 130 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 136 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 142 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void injectContents(int index, int count) {
/* 153 */     if (this.storage[index] != null) {
/* 154 */       this.storage[index] = new ItemStack(this.storage[index].func_77973_b(), (this.storage[index]).field_77994_a + count, this.storage[index].func_77960_j());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 166 */     this.storage[i] = itemstack;
/* 167 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 168 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 174 */     TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 186 */     return "Tool Rack";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 208 */     super.func_145839_a(nbttagcompound);
/* 209 */     this.woodType = nbttagcompound.func_74771_c("woodType");
/* 210 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 211 */     this.storage = new ItemStack[func_70302_i_()];
/* 212 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 214 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 215 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 216 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 217 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 224 */     super.func_145841_b(nbt);
/* 225 */     NBTTagList nbttaglist = new NBTTagList();
/* 226 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 228 */       if (this.storage[i] != null) {
/*     */         
/* 230 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 231 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 232 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 233 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 236 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/* 237 */     nbt.func_74774_a("woodType", this.woodType);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 242 */     func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 248 */     func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {
/* 253 */     func_145841_b(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 259 */     func_145841_b(nbt);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TileEntityToolRack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */