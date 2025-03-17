/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelAlcoholRecipe;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelBriningRecipe;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelLiquidToLiquidRecipe;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelManager;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelMultiItemRecipe;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelRecipe;
/*     */ import com.bioxx.tfc.api.Crafting.BarrelVinegarRecipe;
/*     */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import java.util.Stack;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidContainerItem;
/*     */ 
/*     */ public class TEBarrel
/*     */   extends NetworkTileEntity
/*     */   implements IInventory {
/*     */   public FluidStack fluid;
/*     */   public byte rotation;
/*     */   public int barrelType;
/*     */   public int mode;
/*     */   public ItemStack[] storage;
/*     */   private boolean sealed;
/*     */   public int sealtime;
/*     */   
/*     */   public TEBarrel() {
/*  54 */     this.storage = new ItemStack[12];
/*     */   }
/*     */   public int unsealtime; private int processTimer; public static final int MODE_IN = 0; public static final int MODE_OUT = 1; public static final int INPUT_SLOT = 0; public BarrelRecipe recipe; public boolean shouldDropItem = true;
/*     */   
/*     */   public boolean getSealed() {
/*  59 */     return this.sealed;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTechLevel() {
/*  64 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/*  69 */     this.storage = new ItemStack[12];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  76 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSealed() {
/*  81 */     this.sealed = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnsealed(String reason) {
/*  86 */     if ("killing fuse".equals(reason)) {
/*  87 */       this.sealed = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  98 */     if (this.storage[i] != null) {
/*     */       
/* 100 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/* 102 */         ItemStack is = this.storage[i];
/* 103 */         this.storage[i] = null;
/* 104 */         return is;
/*     */       } 
/* 106 */       ItemStack isSplit = this.storage[i].func_77979_a(j);
/* 107 */       if ((this.storage[i]).field_77994_a == 0)
/* 108 */         this.storage[i] = null; 
/* 109 */       return isSplit;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 119 */     float f3 = 0.05F;
/*     */     
/* 121 */     Random rand = new Random();
/* 122 */     float f = rand.nextFloat() * 0.3F + 0.1F;
/* 123 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 124 */     float f2 = rand.nextFloat() * 0.3F + 0.1F;
/*     */     
/* 126 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 128 */       if (this.storage[i] != null) {
/*     */         
/* 130 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 131 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 132 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 133 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 134 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 142 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 148 */     return "Barrel";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 154 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 160 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 166 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInvCount() {
/* 171 */     int count = 0;
/* 172 */     for (ItemStack is : this.storage) {
/*     */       
/* 174 */       if (is != null)
/* 175 */         count++; 
/*     */     } 
/* 177 */     if (this.storage[0] != null && count == 1)
/* 178 */       return 0; 
/* 179 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGunPowderCount() {
/* 184 */     int count = 0;
/* 185 */     for (ItemStack is : this.storage) {
/*     */       
/* 187 */       if (is != null && is.func_77973_b() == Items.field_151016_H)
/* 188 */         count += is.field_77994_a; 
/*     */     } 
/* 190 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAcceptLiquids() {
/* 195 */     return (getInvCount() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack is) {
/* 212 */     if (!ItemStack.func_77989_b(this.storage[i], is)) {
/*     */       
/* 214 */       this.storage[i] = is;
/* 215 */       if (i == 0) {
/*     */         
/* 217 */         processItems();
/* 218 */         if (!getSealed()) {
/* 219 */           this.unsealtime = (int)TFC_Time.getTotalHours();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getFluidLevel() {
/* 226 */     if (this.fluid != null)
/* 227 */       return this.fluid.amount; 
/* 228 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getInputStack() {
/* 233 */     return this.storage[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack getFluidStack() {
/* 238 */     return this.fluid;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxLiquid() {
/* 243 */     return 10000;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addLiquid(FluidStack inFS) {
/* 248 */     if (inFS != null) {
/*     */ 
/*     */       
/* 251 */       if (inFS.getFluid() != null && inFS.getFluid().getTemperature(inFS) > 385) {
/* 252 */         return false;
/*     */       }
/* 254 */       if (this.fluid == null) {
/*     */         
/* 256 */         this.fluid = inFS.copy();
/* 257 */         if (this.fluid.amount > getMaxLiquid())
/*     */         {
/* 259 */           this.fluid.amount = getMaxLiquid();
/* 260 */           inFS.amount -= getMaxLiquid();
/*     */         }
/*     */         else
/*     */         {
/* 264 */           inFS.amount = 0;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 269 */         if (this.fluid.amount == getMaxLiquid() || !this.fluid.isFluidEqual(inFS)) {
/* 270 */           return false;
/*     */         }
/* 272 */         int a = this.fluid.amount + inFS.amount - getMaxLiquid();
/* 273 */         this.fluid.amount = Math.min(this.fluid.amount + inFS.amount, getMaxLiquid());
/* 274 */         if (a > 0) {
/* 275 */           inFS.amount = a;
/*     */         } else {
/* 277 */           inFS.amount = 0;
/*     */         } 
/* 279 */       }  this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 280 */       return true;
/*     */     } 
/*     */     
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack addLiquid(ItemStack is) {
/* 288 */     if (is == null || is.field_77994_a > 1)
/* 289 */       return is; 
/* 290 */     if (FluidContainerRegistry.isFilledContainer(is)) {
/*     */       
/* 292 */       FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(is);
/*     */       
/* 294 */       if (is.func_77973_b().func_77658_a().toLowerCase().contains("soup") && 
/* 295 */         is.func_77973_b() instanceof straywolfe.cookingwithtfc.common.item.ItemTFCMealTransform) {
/*     */         
/* 297 */         float weight = Food.getWeight(is);
/*     */ 
/*     */         
/* 300 */         fs.amount = (int)(weight * fs.amount) / 10;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 305 */       if (addLiquid(fs))
/*     */       {
/* 307 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 308 */         return FluidContainerRegistry.drainFluidContainer(is);
/*     */       }
/*     */     
/* 311 */     } else if (is.func_77973_b() instanceof IFluidContainerItem) {
/*     */       
/* 313 */       FluidStack isfs = ((IFluidContainerItem)is.func_77973_b()).getFluid(is);
/* 314 */       System.out.println(isfs.amount);
/* 315 */       if (isfs != null && addLiquid(isfs)) {
/*     */         
/* 317 */         ((IFluidContainerItem)is.func_77973_b()).drain(is, is.func_77958_k(), true);
/* 318 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     } 
/* 321 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack removeLiquid(ItemStack is) {
/* 329 */     if (is == null || is.field_77994_a > 1)
/* 330 */       return is; 
/* 331 */     if (FluidContainerRegistry.isEmptyContainer(is)) {
/*     */       
/* 333 */       ItemStack out = FluidContainerRegistry.fillFluidContainer(this.fluid, is);
/* 334 */       if (out != null)
/*     */       {
/* 336 */         FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(out);
/* 337 */         this.fluid.amount -= fs.amount;
/* 338 */         is = null;
/* 339 */         if (this.fluid.amount == 0)
/*     */         {
/* 341 */           this.fluid = null;
/*     */         }
/* 343 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 344 */         return out;
/*     */       }
/*     */     
/* 347 */     } else if (this.fluid != null && is.func_77973_b() instanceof IFluidContainerItem) {
/*     */       
/* 349 */       FluidStack isfs = ((IFluidContainerItem)is.func_77973_b()).getFluid(is);
/* 350 */       if (isfs == null || this.fluid.isFluidEqual(isfs)) {
/*     */         
/* 352 */         this.fluid.amount -= ((IFluidContainerItem)is.func_77973_b()).fill(is, this.fluid, true);
/* 353 */         if (this.fluid.amount == 0)
/* 354 */           this.fluid = null; 
/* 355 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     } 
/* 358 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drainLiquid(int amount) {
/* 366 */     if (!getSealed() && getFluidStack() != null) {
/*     */       
/* 368 */       (getFluidStack()).amount -= amount;
/* 369 */       if ((getFluidStack()).amount <= 0) {
/* 370 */         actionEmpty();
/*     */       } else {
/* 372 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getLiquidScaled(int i) {
/* 378 */     if (this.fluid != null)
/* 379 */       return this.fluid.amount * i / getMaxLiquid(); 
/* 380 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean actionSeal(int tab, EntityPlayer player) {
/* 385 */     NBTTagCompound nbt = new NBTTagCompound();
/* 386 */     nbt.func_74757_a("seal", true);
/* 387 */     nbt.func_74774_a("tab", (byte)tab);
/* 388 */     nbt.func_74778_a("player", player.func_70005_c_());
/* 389 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/* 390 */     this.sealed = true;
/* 391 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 392 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean actionUnSeal(int tab, EntityPlayer player) {
/* 397 */     NBTTagCompound nbt = new NBTTagCompound();
/* 398 */     nbt.func_74757_a("seal", false);
/* 399 */     nbt.func_74774_a("tab", (byte)tab);
/* 400 */     nbt.func_74778_a("player", player.func_70005_c_());
/* 401 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/* 402 */     this.sealed = false;
/* 403 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 404 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionEmpty() {
/* 409 */     this.fluid = null;
/* 410 */     NBTTagCompound nbt = new NBTTagCompound();
/* 411 */     nbt.func_74774_a("fluidID", (byte)-1);
/* 412 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionMode() {
/* 417 */     this.mode = (this.mode == 0) ? 1 : 0;
/* 418 */     NBTTagCompound nbt = new NBTTagCompound();
/* 419 */     nbt.func_74774_a("mode", (byte)this.mode);
/* 420 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionSwitchTab(int tab, EntityPlayer player) {
/* 425 */     NBTTagCompound nbt = new NBTTagCompound();
/* 426 */     nbt.func_74774_a("tab", (byte)tab);
/* 427 */     nbt.func_74778_a("player", player.func_70005_c_());
/* 428 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 434 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 440 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 446 */     super.func_145841_b(nbt);
/* 447 */     nbt.func_74757_a("Sealed", this.sealed);
/* 448 */     nbt.func_74768_a("SealTime", this.sealtime);
/* 449 */     nbt.func_74768_a("barrelType", this.barrelType);
/*     */     
/* 451 */     NBTTagCompound fluidNBT = new NBTTagCompound();
/* 452 */     if (this.fluid != null)
/* 453 */       this.fluid.writeToNBT(fluidNBT); 
/* 454 */     nbt.func_74782_a("fluidNBT", (NBTBase)fluidNBT);
/* 455 */     nbt.func_74774_a("rotation", this.rotation);
/* 456 */     NBTTagList nbttaglist = new NBTTagList();
/* 457 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 459 */       if (this.storage[i] != null) {
/*     */         
/* 461 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 462 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 463 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 464 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 467 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 473 */     super.func_145839_a(nbt);
/* 474 */     this.fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/* 475 */     this.sealed = nbt.func_74767_n("Sealed");
/* 476 */     this.sealtime = nbt.func_74762_e("SealTime");
/* 477 */     this.barrelType = nbt.func_74762_e("barrelType");
/*     */     
/* 479 */     this.rotation = nbt.func_74771_c("rotation");
/* 480 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 481 */     this.storage = new ItemStack[func_70302_i_()];
/* 482 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 484 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 485 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 486 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 487 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromItemNBT(NBTTagCompound nbt) {
/* 494 */     this.barrelType = nbt.func_74762_e("barrelType");
/* 495 */     this.fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/* 496 */     this.sealed = nbt.func_74767_n("Sealed");
/* 497 */     this.sealtime = nbt.func_74762_e("SealTime");
/* 498 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 499 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 501 */       NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
/* 502 */       byte byte0 = nbt1.func_74771_c("Slot");
/* 503 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 504 */         func_70299_a(byte0, ItemStack.func_77949_a(nbt1));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateGui() {
/* 510 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 518 */     this.rotation = nbt.func_74771_c("rotation");
/* 519 */     this.sealed = nbt.func_74767_n("sealed");
/* 520 */     this.sealtime = nbt.func_74762_e("SealTime");
/* 521 */     this.barrelType = nbt.func_74762_e("barrelType");
/* 522 */     if (nbt.func_74762_e("fluid") != -1) {
/*     */       
/* 524 */       if (this.fluid != null) {
/* 525 */         this.fluid.amount = nbt.func_74762_e("fluidAmount");
/*     */       } else {
/* 527 */         this.fluid = new FluidStack(nbt.func_74762_e("fluid"), nbt.func_74762_e("fluidAmount"));
/*     */       } 
/*     */     } else {
/*     */       
/* 531 */       this.fluid = null;
/*     */     } 
/* 533 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 539 */     nbt.func_74774_a("rotation", this.rotation);
/* 540 */     nbt.func_74757_a("sealed", this.sealed);
/* 541 */     nbt.func_74768_a("SealTime", this.sealtime);
/* 542 */     nbt.func_74768_a("fluid", (this.fluid != null) ? this.fluid.getFluidID() : -1);
/* 543 */     nbt.func_74768_a("fluidAmount", (this.fluid != null) ? this.fluid.amount : 0);
/* 544 */     nbt.func_74768_a("barrelType", this.barrelType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 550 */     if (nbt.func_74764_b("fluidID")) {
/*     */       
/* 552 */       if (nbt.func_74771_c("fluidID") == -1)
/* 553 */         this.fluid = null; 
/* 554 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     } 
/* 556 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 558 */       if (nbt.func_74764_b("mode")) {
/*     */         
/* 560 */         this.mode = nbt.func_74771_c("mode");
/*     */       }
/* 562 */       else if (nbt.func_74764_b("seal")) {
/*     */         
/* 564 */         this.sealed = nbt.func_74767_n("seal");
/* 565 */         if (!this.sealed) {
/*     */           
/* 567 */           this.unsealtime = (int)TFC_Time.getTotalHours();
/* 568 */           this.sealtime = 0;
/*     */         }
/*     */         else {
/*     */           
/* 572 */           this.sealtime = (int)TFC_Time.getTotalHours();
/* 573 */           this.unsealtime = 0;
/*     */         } 
/*     */ 
/*     */         
/* 577 */         NBTTagCompound timeTag = new NBTTagCompound();
/* 578 */         timeTag.func_74768_a("SealTime", this.sealtime);
/* 579 */         broadcastPacketInRange((AbstractPacket)createDataPacket(timeTag));
/*     */         
/* 581 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */       
/* 584 */       if (nbt.func_74764_b("tab"))
/*     */       {
/* 586 */         int tab = nbt.func_74771_c("tab");
/* 587 */         switchTab(this.field_145850_b.func_72924_a(nbt.func_74779_i("player")), tab);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 593 */     else if (nbt.func_74764_b("SealTime")) {
/* 594 */       this.sealtime = nbt.func_74762_e("SealTime");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void switchTab(EntityPlayer player, int tab) {
/* 600 */     if (player != null) {
/* 601 */       if (tab == 0) {
/* 602 */         player.openGui(TerraFirmaCraft.instance, 35, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 603 */       } else if (tab == 1) {
/* 604 */         player.openGui(TerraFirmaCraft.instance, 36, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145845_h() {
/* 610 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 612 */       ItemStack itemstack = this.storage[0];
/* 613 */       BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(this, itemstack, this.fluid, this.sealed);
/* 614 */       if (itemstack != null && this.fluid != null && this.fluid.getFluid() == TFCFluids.FRESHWATER)
/*     */       {
/* 616 */         if (TFC_ItemHeat.hasTemp(itemstack)) {
/*     */           
/* 618 */           float temp = TFC_ItemHeat.getTemp(itemstack);
/* 619 */           if (this.fluid.amount >= 1 && temp > 1.0F) {
/*     */             
/* 621 */             temp -= 50.0F;
/* 622 */             this.fluid.amount--;
/* 623 */             TFC_ItemHeat.setTemp(itemstack, temp);
/* 624 */             TFC_ItemHeat.handleItemHeat(itemstack);
/*     */           } 
/*     */         } 
/*     */       }
/* 628 */       if (this.fluid != null && itemstack != null && itemstack.func_77973_b() instanceof IFood) {
/*     */         
/* 630 */         float w = Food.getWeight(itemstack);
/* 631 */         if (this.fluid.getFluid() == TFCFluids.VINEGAR)
/*     */         {
/*     */           
/* 634 */           if (Food.isBrined(itemstack) && !Food.isPickled(itemstack) && w / this.fluid.amount <= 160.0F / getMaxLiquid() && getSealed() && this.sealtime != 0 && 
/* 635 */             TFC_Time.getTotalHours() - this.sealtime >= 4L) {
/*     */             
/* 637 */             this.fluid.amount = (int)(this.fluid.amount - 1.0F * w);
/* 638 */             Food.setPickled(itemstack, true);
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 643 */       if (preservative == null) {
/*     */ 
/*     */         
/* 646 */         TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */       else {
/*     */         
/* 650 */         float env = preservative.getEnvironmentalDecayFactor();
/* 651 */         float base = preservative.getBaseDecayModifier();
/* 652 */         if (Float.isNaN(env) || env < 0.0D) {
/*     */           
/* 654 */           TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/* 656 */         else if (Float.isNaN(base) || base < 0.0D) {
/*     */           
/* 658 */           TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env);
/*     */         }
/*     */         else {
/*     */           
/* 662 */           TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env, base);
/*     */         } 
/*     */       } 
/*     */       
/* 666 */       boolean shipsworld = false;
/* 667 */       if (TerraFirmaCraft.instance.ShipsExist) shipsworld = this.field_145850_b instanceof cuchaz.ships.ShipWorld;
/*     */       
/* 669 */       if (!shipsworld)
/*     */       {
/* 671 */         if (!getSealed() && TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*     */           
/* 673 */           int count = getInvCount();
/* 674 */           if (count == 0 || (count == 1 && getInputStack() != null))
/*     */           {
/* 676 */             if (this.fluid == null) {
/* 677 */               this.fluid = new FluidStack(TFCFluids.FRESHWATER, 1);
/* 678 */             } else if (this.fluid != null && this.fluid.getFluid() == TFCFluids.FRESHWATER) {
/* 679 */               this.fluid.amount = Math.min(this.fluid.amount + 1, getMaxLiquid());
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 685 */       this.processTimer++;
/* 686 */       if (this.processTimer > 100) {
/*     */         
/* 688 */         processItems();
/* 689 */         this.processTimer = 0;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 695 */       if (getFluidLevel() > 0 && getInputStack() != null) {
/*     */         
/* 697 */         int count = 1;
/* 698 */         while ((getInputStack()).field_77994_a > getInputStack().func_77976_d())
/*     */         {
/* 700 */           ItemStack is = getInputStack().func_77979_a(getInputStack().func_77976_d());
/* 701 */           if (count < this.storage.length && func_70301_a(count) == null) {
/*     */             
/* 703 */             func_70299_a(count, is);
/*     */           }
/*     */           else {
/*     */             
/* 707 */             this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, is));
/*     */           } 
/* 709 */           count++;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 714 */       else if (getFluidLevel() > 0 && getInputStack() == null && getInvCount() > 0) {
/*     */         
/* 716 */         for (int i = 0; i < this.storage.length; i++) {
/*     */           
/* 718 */           if (this.storage[i] != null) {
/*     */             
/* 720 */             this.storage[0] = this.storage[i].func_77946_l();
/* 721 */             this.storage[i] = null;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 729 */       if (this.fluid != null && this.fluid.amount == 0) {
/* 730 */         this.fluid = null;
/*     */       }
/*     */       
/* 733 */       if (this.mode == 0) {
/*     */         
/* 735 */         ItemStack container = getInputStack();
/* 736 */         FluidStack inLiquid = FluidContainerRegistry.getFluidForFilledItem(container);
/*     */         
/* 738 */         if (container != null && container.func_77973_b() instanceof IFluidContainerItem)
/*     */         {
/* 740 */           FluidStack isfs = ((IFluidContainerItem)container.func_77973_b()).getFluid(container);
/* 741 */           if (isfs != null && addLiquid(isfs))
/*     */           {
/* 743 */             ((IFluidContainerItem)container.func_77973_b()).drain(container, ((IFluidContainerItem)container.func_77973_b()).getCapacity(container), true);
/*     */           }
/*     */         }
/* 746 */         else if (inLiquid != null && container != null && container.field_77994_a == 1)
/*     */         {
/* 748 */           if (addLiquid(inLiquid))
/*     */           {
/* 750 */             func_70299_a(0, FluidContainerRegistry.drainFluidContainer(container));
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 755 */       else if (this.mode == 1) {
/*     */         
/* 757 */         ItemStack container = getInputStack();
/*     */         
/* 759 */         if (container != null && this.fluid != null && container.func_77973_b() instanceof IFluidContainerItem) {
/*     */           
/* 761 */           FluidStack isfs = ((IFluidContainerItem)container.func_77973_b()).getFluid(container);
/* 762 */           if (isfs == null || this.fluid.isFluidEqual(isfs)) {
/*     */             
/* 764 */             this.fluid.amount -= ((IFluidContainerItem)container.func_77973_b()).fill(container, this.fluid, true);
/* 765 */             if (this.fluid.amount == 0) {
/* 766 */               this.fluid = null;
/*     */             }
/*     */           } 
/* 769 */         } else if (FluidContainerRegistry.isEmptyContainer(container)) {
/*     */           
/* 771 */           ItemStack fullContainer = removeLiquid(getInputStack());
/* 772 */           if (fullContainer.func_77973_b() == TFCItems.woodenBucketMilk)
/*     */           {
/* 774 */             ItemCustomBucketMilk.createTag(fullContainer, 20.0F);
/*     */           }
/* 776 */           func_70299_a(0, fullContainer);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void processItems() {
/* 784 */     if (getInvCount() == 0) {
/*     */ 
/*     */ 
/*     */       
/* 788 */       boolean isCheese = false;
/*     */       
/* 790 */       if (getFluidStack() != null && !isCheese)
/*     */       
/* 792 */       { this.recipe = BarrelManager.getInstance().findMatchingRecipe(getInputStack(), getFluidStack(), this.sealed, getTechLevel());
/* 793 */         if (this.recipe != null && !this.field_145850_b.field_72995_K) {
/*     */           
/* 795 */           int time = 0;
/* 796 */           if (this.sealtime > 0) {
/* 797 */             time = (int)TFC_Time.getTotalHours() - this.sealtime;
/* 798 */           } else if (this.unsealtime > 0) {
/* 799 */             time = (int)TFC_Time.getTotalHours() - this.unsealtime;
/*     */           } 
/*     */           
/* 802 */           if (this.recipe.isSealedRecipe() && time < this.recipe.sealTime) {
/*     */             return;
/*     */           }
/* 805 */           ItemStack origIS = (getInputStack() != null) ? getInputStack().func_77946_l() : null;
/* 806 */           FluidStack origFS = (getFluidStack() != null) ? getFluidStack().copy() : null;
/* 807 */           if (this.fluid.isFluidEqual(this.recipe.getResultFluid(origIS, origFS, time)) && this.recipe.removesLiquid) {
/*     */             
/* 809 */             if (this.fluid.getFluid() == TFCFluids.BRINE && origIS != null && origIS.func_77973_b() instanceof IFood) {
/* 810 */               this.fluid.amount = (int)(this.fluid.amount - (this.recipe.getResultFluid(origIS, origFS, time)).amount * Food.getWeight(origIS));
/*     */             } else {
/* 812 */               this.fluid.amount -= (this.recipe.getResultFluid(origIS, origFS, time)).amount;
/*     */             } 
/*     */           } else {
/*     */             
/* 816 */             this.fluid = this.recipe.getResultFluid(origIS, origFS, time).copy();
/* 817 */             if (this.fluid != null && !(this.recipe instanceof BarrelLiquidToLiquidRecipe) && origFS != null) {
/* 818 */               this.fluid.amount = origFS.amount;
/*     */             }
/* 820 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           } 
/*     */           
/* 823 */           if (origFS != null && origFS.getFluid() != TFCFluids.MILKCURDLED && this.fluid.getFluid() == TFCFluids.MILKCURDLED) {
/* 824 */             this.sealtime = (int)TFC_Time.getTotalHours();
/*     */           }
/* 826 */           Stack<ItemStack> resultStacks = this.recipe.getResult(origIS, origFS, time);
/* 827 */           if (!resultStacks.isEmpty())
/*     */           {
/* 829 */             ItemStack result = resultStacks.pop();
/* 830 */             if (this.fluid != null && this.fluid.getFluid() == TFCFluids.BRINE) {
/*     */               
/* 832 */               if (result == null && origIS != null)
/* 833 */                 result = origIS.func_77946_l(); 
/* 834 */               if (result != null && result.func_77973_b() instanceof IFood && (result.func_77973_b() == TFCItems.cheese || ((IFood)result.func_77973_b()).getFoodGroup() != EnumFoodGroup.Grain))
/*     */               {
/* 836 */                 if (!Food.isBrined(result)) {
/* 837 */                   Food.setBrined(result, true);
/*     */                 }
/*     */               }
/*     */             } 
/* 841 */             this.storage[0] = result;
/*     */             
/* 843 */             for (int i = 1; i < this.storage.length; i++) {
/*     */               
/* 845 */               if (this.storage[i] == null && !resultStacks.isEmpty()) {
/* 846 */                 func_70299_a(i, resultStacks.pop());
/*     */               }
/*     */             } 
/* 849 */             while (!resultStacks.isEmpty()) {
/* 850 */               this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, resultStacks.pop()));
/*     */             }
/* 852 */             func_70299_a(0, result);
/*     */           }
/*     */         
/*     */         }  }
/* 856 */       else if (getFluidStack() == null && !isCheese) { this.recipe = null; }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean handleCheese() {
/* 865 */     ItemStack inIS = getInputStack();
/* 866 */     if (getSealed() && this.fluid != null && this.fluid.getFluid() == TFCFluids.MILKCURDLED && (inIS == null || (inIS
/* 867 */       .func_77973_b() instanceof IFood && ((IFood)inIS.func_77973_b()).getFoodGroup() != EnumFoodGroup.Dairy && ((IFood)inIS
/* 868 */       .func_77973_b()).isEdible(inIS) && Food.getWeight(inIS) <= 20.0F))) {
/*     */       
/* 870 */       this.recipe = (new BarrelRecipe(null, new FluidStack(TFCFluids.MILKCURDLED, 10000), ItemFoodTFC.createTag(new ItemStack(TFCItems.cheese, 1), 160.0F), null)).setMinTechLevel(0);
/* 871 */       if (!this.field_145850_b.field_72995_K) {
/*     */         
/* 873 */         int time = 0;
/* 874 */         if (this.sealtime > 0) {
/* 875 */           time = (int)TFC_Time.getTotalHours() - this.sealtime;
/* 876 */         } else if (this.unsealtime > 0) {
/* 877 */           time = (int)TFC_Time.getTotalHours() - this.unsealtime;
/*     */         } 
/*     */         
/* 880 */         if (time < this.recipe.sealTime)
/* 881 */           return true; 
/* 882 */         float w = this.fluid.amount / 62.5F;
/*     */         
/* 884 */         ItemStack is = ItemFoodTFC.createTag(new ItemStack(TFCItems.cheese), w);
/*     */         
/* 886 */         if (inIS != null && inIS.func_77973_b() instanceof IFood) {
/*     */           
/* 888 */           int[] profile = Food.getFoodTasteProfile(inIS);
/* 889 */           float ratio = Math.min((Food.getWeight(getInputStack()) - Food.getDecay(inIS)) / 20.0F, 1.0F);
/* 890 */           Food.setSweetMod(is, (int)Math.floor((profile[0] * ratio)));
/* 891 */           Food.setSourMod(is, (int)Math.floor((profile[1] * ratio)));
/* 892 */           Food.setSaltyMod(is, (int)Math.floor((profile[2] * ratio)));
/* 893 */           Food.setBitterMod(is, (int)Math.floor((profile[3] * ratio)));
/* 894 */           Food.setSavoryMod(is, (int)Math.floor((profile[4] * ratio)));
/* 895 */           Food.setInfusion(is, inIS.func_77973_b().func_77658_a());
/* 896 */           this.storage[0] = null;
/*     */         } 
/*     */         
/* 899 */         actionEmpty();
/* 900 */         func_70299_a(0, is);
/*     */       } 
/* 902 */       return true;
/*     */     } 
/* 904 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack createFullBarrel(FluidStack f, ItemStack is) {
/* 909 */     if (!is.func_77942_o()) {
/* 910 */       is.func_77982_d(new NBTTagCompound());
/*     */     }
/* 912 */     is.func_77978_p().func_74757_a("Sealed", true);
/*     */     
/* 914 */     NBTTagCompound fluidNBT = new NBTTagCompound();
/* 915 */     if (f != null)
/* 916 */       f.writeToNBT(fluidNBT); 
/* 917 */     is.func_77978_p().func_74782_a("fluidNBT", (NBTBase)fluidNBT);
/*     */     
/* 919 */     return is;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerRecipes() {
/* 924 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.VODKA, 10000)));
/*     */ 
/*     */     
/* 927 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.wheatGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.WHISKEY, 10000)));
/* 928 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.ryeGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RYEWHISKEY, 10000)));
/*     */ 
/*     */     
/* 931 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RUM, 10000)));
/* 932 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cornmealGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.CORNWHISKEY, 10000)));
/*     */ 
/*     */     
/* 935 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 936 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 937 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 3), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 938 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 4), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 939 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 5), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 940 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 6), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 941 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 9), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 942 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.powder, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 500), null, new FluidStack(TFCFluids.LIMEWATER, 500), 0)).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setAllowAnyStack(false));
/* 943 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 300), new ItemStack(TFCItems.prepHide, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 300))).setMinTechLevel(0));
/* 944 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 1), new FluidStack(TFCFluids.FRESHWATER, 400), new ItemStack(TFCItems.prepHide, 1, 1), new FluidStack(TFCFluids.FRESHWATER, 400))).setMinTechLevel(0));
/* 945 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 500), new ItemStack(TFCItems.prepHide, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 500))).setMinTechLevel(0));
/* 946 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 300), new ItemStack(TFCItems.soakedHide, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 300))).setMinTechLevel(0));
/* 947 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 1), new FluidStack(TFCFluids.LIMEWATER, 400), new ItemStack(TFCItems.soakedHide, 1, 1), new FluidStack(TFCFluids.LIMEWATER, 400))).setMinTechLevel(0));
/* 948 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 2), new FluidStack(TFCFluids.LIMEWATER, 500), new ItemStack(TFCItems.soakedHide, 1, 2), new FluidStack(TFCFluids.LIMEWATER, 500))).setMinTechLevel(0));
/* 949 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 0), new FluidStack(TFCFluids.TANNIN, 300), new ItemStack(TFCItems.leather, 1), new FluidStack(TFCFluids.TANNIN, 300))).setKeepStackSize(false).setMinTechLevel(0));
/* 950 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 1), new FluidStack(TFCFluids.TANNIN, 400), new ItemStack(TFCItems.leather, 2), new FluidStack(TFCFluids.TANNIN, 400))).setKeepStackSize(false).setMinTechLevel(0));
/* 951 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 2), new FluidStack(TFCFluids.TANNIN, 500), new ItemStack(TFCItems.leather, 3), new FluidStack(TFCFluids.TANNIN, 500))).setKeepStackSize(false).setMinTechLevel(0));
/* 952 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCBlocks.sand, 1, 32767), new FluidStack(TFCFluids.LIMEWATER, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(TFCFluids.LIMEWATER, 100))).setMinTechLevel(0));
/* 953 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCBlocks.sand2, 1, 32767), new FluidStack(TFCFluids.LIMEWATER, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(TFCFluids.LIMEWATER, 100))).setMinTechLevel(0));
/* 954 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.VODKA, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 955 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.CIDER, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 956 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.WHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 957 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.RYEWHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/*     */ 
/*     */     
/* 960 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.RUM, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 961 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.CORNWHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 962 */     BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.SALTWATER, 9000), new FluidStack(TFCFluids.VINEGAR, 1000), new FluidStack(TFCFluids.BRINE, 10000))).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
/* 963 */     BarrelManager.getInstance().addRecipe((new BarrelBriningRecipe(new FluidStack(TFCFluids.BRINE, 60), new FluidStack(TFCFluids.BRINE, 60))).setMinTechLevel(0));
/* 964 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugarcane), 1.0F), new FluidStack(TFCFluids.FRESHWATER, 60), ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 0.1F), new FluidStack(TFCFluids.FRESHWATER, 60))).setMinTechLevel(0));
/* 965 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.jute, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 155), new ItemStack(TFCItems.juteFiber, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 155))).setMinTechLevel(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 971 */     BarrelPreservativeRecipe picklePreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.VINEGAR, 31), "gui.barrel.preserving")).setAllowGrains(false).setRequiresPickled(true).setEnvironmentalDecayFactor(0.25F).setBaseDecayModifier(0.1F).setRequiresSealed(true);
/* 972 */     BarrelPreservativeRecipe brineInBrinePreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.BRINE, 62), "gui.barrel.preserving")).setAllowGrains(false).setRequiresBrined(true).setEnvironmentalDecayFactor(0.75F).setRequiresSealed(true);
/* 973 */     BarrelPreservativeRecipe brineInVinegarPreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.VINEGAR, 62), "gui.barrel.preserving")).setAllowGrains(false).setRequiresBrined(true).setEnvironmentalDecayFactor(0.75F).setRequiresSealed(true);
/* 974 */     BarrelManager.getInstance().addPreservative(picklePreservative);
/* 975 */     BarrelManager.getInstance().addPreservative(brineInBrinePreservative);
/* 976 */     BarrelManager.getInstance().addPreservative(brineInVinegarPreservative);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TEBarrel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */