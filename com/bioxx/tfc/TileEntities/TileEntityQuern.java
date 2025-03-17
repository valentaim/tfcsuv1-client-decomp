/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityCowTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Crafting.QuernManager;
/*     */ import com.bioxx.tfc.api.Crafting.QuernRecipe;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TileEntityQuern
/*     */   extends NetworkTileEntity
/*     */   implements IInventory {
/*  29 */   public ItemStack[] storage = new ItemStack[3];
/*  30 */   public int rotation = 0;
/*     */   public boolean shouldRotate = false;
/*  32 */   public int rotatetimer = 0;
/*     */   
/*     */   public boolean hasQuern = false;
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/*  38 */     if (!this.field_145850_b.field_72995_K) {
/*  39 */       TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*  41 */     this.hasQuern = (this.storage[2] != null);
/*     */     
/*  43 */     if (this.shouldRotate) {
/*     */       
/*  45 */       this.rotatetimer++;
/*  46 */       if (this.rotatetimer == 90) {
/*     */         
/*  48 */         this.rotatetimer = 0;
/*  49 */         this.shouldRotate = false;
/*  50 */         if (!this.field_145850_b.field_72995_K)
/*     */         {
/*  52 */           if (processItem() && this.storage[2] != null) {
/*  53 */             damageStackInSlot(2);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  63 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*  64 */     return bb;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processItem() {
/*  69 */     if (this.storage[0] != null) {
/*     */       
/*  71 */       QuernRecipe qr = QuernManager.getInstance().findMatchingRecipe(this.storage[0]);
/*  72 */       if (qr == null) {
/*     */         
/*  74 */         System.out.println("QUERN RECIPE NOT FOUND! This is a BUG! -- " + this.storage[0].func_77973_b().func_77658_a());
/*  75 */         return false;
/*     */       } 
/*     */ 
/*     */       
/*  79 */       if (this.storage[1] != null && (this.storage[1].func_77973_b() != qr.getResult().func_77973_b() || this.storage[1].func_77960_j() != qr.getResult().func_77960_j())) {
/*     */         
/*  81 */         ejectItem(this.storage[1]);
/*  82 */         this.storage[1] = null;
/*     */       } 
/*     */       
/*  85 */       if (qr.getInItem().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */         
/*  87 */         if (this.storage[1] != null && this.storage[1].func_77942_o() && this.storage[1].func_77978_p().func_74764_b("foodWeight") && this.storage[1].func_77978_p().func_74764_b("foodDecay") && this.storage[0].func_77942_o() && this.storage[0].func_77978_p().func_74764_b("foodWeight") && this.storage[0].func_77978_p().func_74764_b("foodDecay")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  95 */           float flourDecay = this.storage[0].func_77978_p().func_74760_g("foodDecay");
/*  96 */           float slot0Weight = this.storage[0].func_77978_p().func_74760_g("foodWeight");
/*  97 */           float slot1Weight = this.storage[1].func_77978_p().func_74760_g("foodWeight");
/*  98 */           float newWeight = slot0Weight + slot1Weight;
/*     */           
/* 100 */           if (newWeight > Global.FOOD_MAX_WEIGHT) {
/*     */             
/* 102 */             this.storage[1].func_77978_p().func_74776_a("foodWeight", newWeight - Global.FOOD_MAX_WEIGHT);
/*     */             
/* 104 */             ItemStack tossStack = this.storage[1].func_77946_l();
/* 105 */             tossStack.func_77978_p().func_74776_a("foodWeight", Global.FOOD_MAX_WEIGHT);
/* 106 */             ejectItem(tossStack);
/*     */           }
/*     */           else {
/*     */             
/* 110 */             this.storage[1].func_77978_p().func_74776_a("foodWeight", newWeight);
/*     */           } 
/* 112 */           this.storage[0] = null;
/* 113 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 114 */           return true;
/*     */         } 
/*     */         
/* 117 */         if (this.storage[1] == null && this.storage[0].func_77942_o() && this.storage[0].func_77978_p().func_74764_b("foodWeight") && this.storage[0].func_77978_p().func_74764_b("foodDecay"))
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 122 */           this.storage[1] = qr.getResult().func_77946_l();
/* 123 */           float flourWeight = this.storage[0].func_77978_p().func_74760_g("foodWeight");
/* 124 */           float flourDecay = this.storage[0].func_77978_p().func_74760_g("foodDecay");
/* 125 */           ItemFoodTFC.createTag(this.storage[1], flourWeight, flourDecay);
/* 126 */           this.storage[0] = null;
/* 127 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 128 */           return true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 133 */         if ((this.storage[0]).field_77994_a == 1) {
/*     */           
/* 135 */           this.storage[0] = null;
/* 136 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } else {
/*     */           
/* 139 */           (this.storage[0]).field_77994_a--;
/*     */         } 
/* 141 */         if (this.storage[1] == null) {
/* 142 */           this.storage[1] = qr.getResult().func_77946_l();
/* 143 */         } else if ((this.storage[1]).field_77994_a < this.storage[1].func_77976_d()) {
/*     */           
/* 145 */           if ((qr.getResult()).field_77994_a + (this.storage[1]).field_77994_a > this.storage[1].func_77976_d()) {
/*     */             
/* 147 */             int amountleft = (qr.getResult()).field_77994_a - this.storage[1].func_77976_d() - (this.storage[1]).field_77994_a;
/* 148 */             ItemStack tossStack = qr.getResult().func_77946_l();
/* 149 */             tossStack.field_77994_a = tossStack.func_77976_d();
/* 150 */             ejectItem(tossStack);
/* 151 */             ItemStack remainStack = qr.getResult().func_77946_l();
/* 152 */             remainStack.field_77994_a = amountleft;
/* 153 */             this.storage[1] = remainStack;
/*     */           } else {
/*     */             
/* 156 */             (this.storage[1]).field_77994_a += (qr.getResult()).field_77994_a;
/*     */           } 
/*     */         } else {
/*     */           
/* 160 */           ejectItem(this.storage[1]);
/* 161 */           this.storage[1] = qr.getResult().func_77946_l();
/*     */         } 
/* 163 */         return true;
/*     */       } 
/*     */     } 
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damageStackInSlot(int slot) {
/* 171 */     if (this.storage[slot] != null) {
/*     */       
/* 173 */       this.storage[slot].func_77972_a(slot, (EntityLivingBase)new EntityCowTFC(this.field_145850_b));
/* 174 */       if ((this.storage[slot]).field_77994_a == 0 || this.storage[slot].func_77960_j() == this.storage[slot].func_77958_k()) {
/*     */         
/* 176 */         func_70299_a(slot, (ItemStack)null);
/* 177 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int slot, int amount) {
/* 185 */     if (this.storage[slot] != null) {
/*     */       
/* 187 */       if ((this.storage[slot]).field_77994_a <= amount) {
/*     */         
/* 189 */         ItemStack itemstack = this.storage[slot];
/* 190 */         func_70299_a(slot, (ItemStack)null);
/* 191 */         return itemstack;
/*     */       } 
/* 193 */       ItemStack itemstack1 = this.storage[slot].func_77979_a(amount);
/* 194 */       if ((this.storage[slot]).field_77994_a == 0)
/* 195 */         func_70299_a(slot, (ItemStack)null); 
/* 196 */       return itemstack1;
/*     */     } 
/*     */     
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 204 */     float f3 = 0.05F;
/*     */     
/* 206 */     Random rand = new Random();
/* 207 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 208 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 209 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 211 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 213 */       if (this.storage[i] != null) {
/*     */         
/* 215 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 216 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 217 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 218 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 219 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectItem(ItemStack is) {
/* 226 */     float f3 = 0.05F;
/*     */     
/* 228 */     Random rand = new Random();
/* 229 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 230 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 231 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 233 */     if (this.storage[1] != null) {
/*     */       
/* 235 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), is);
/* 236 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 237 */       entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.05F);
/* 238 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 239 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 246 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int slot) {
/* 252 */     return this.storage[slot];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int slot, ItemStack is) {
/* 258 */     this.storage[slot] = is;
/* 259 */     if (is != null && is.field_77994_a > func_70297_j_()) {
/* 260 */       is.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 266 */     return "Quern";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 272 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer player) {
/* 278 */     return false;
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
/*     */   public ItemStack func_70304_b(int slot) {
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 300 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int slot, ItemStack is) {
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 312 */     super.func_145839_a(nbttagcompound);
/* 313 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 314 */     this.storage = new ItemStack[func_70302_i_()];
/* 315 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 317 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 318 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 319 */       if (byte0 >= 0 && byte0 < this.storage.length)
/* 320 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */     } 
/* 322 */     this.hasQuern = nbttagcompound.func_74767_n("hasQuern");
/* 323 */     this.shouldRotate = nbttagcompound.func_74767_n("shouldRotate");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 329 */     super.func_145841_b(nbttagcompound);
/* 330 */     NBTTagList nbttaglist = new NBTTagList();
/* 331 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 333 */       if (this.storage[i] != null) {
/*     */         
/* 335 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 336 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 337 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 338 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 341 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/* 342 */     nbttagcompound.func_74757_a("hasQuern", this.hasQuern);
/* 343 */     nbttagcompound.func_74757_a("shouldRotate", this.shouldRotate);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 349 */     NBTTagCompound nbt = new NBTTagCompound();
/* 350 */     func_145841_b(nbt);
/* 351 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 357 */     func_145839_a(pkt.func_148857_g());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 363 */     this.hasQuern = nbt.func_74767_n("hasQuern");
/* 364 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 382 */     nbt.func_74757_a("hasQuern", this.hasQuern);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TileEntityQuern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */