/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockChestTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.IHopper;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ public class TEHopper
/*     */   extends NetworkTileEntity
/*     */   implements IHopper
/*     */ {
/*  34 */   private ItemStack[] storage = new ItemStack[5];
/*     */   private String customName;
/*  36 */   private int cooldown = -1;
/*     */ 
/*     */   
/*     */   public int pressCooldown;
/*     */ 
/*     */   
/*     */   public ItemStack pressBlock;
/*     */ 
/*     */   
/*     */   private ItemStack pressableItem;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  50 */     super.func_145839_a(nbt);
/*  51 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  52 */     this.storage = new ItemStack[func_70302_i_()];
/*     */     
/*  54 */     if (nbt.func_150297_b("CustomName", 8))
/*     */     {
/*  56 */       this.customName = nbt.func_74779_i("CustomName");
/*     */     }
/*     */     
/*  59 */     this.cooldown = nbt.func_74762_e("TransferCooldown");
/*     */     
/*  61 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/*  63 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  64 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/*  66 */       if (b0 >= 0 && b0 < this.storage.length)
/*     */       {
/*  68 */         this.storage[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */     
/*  72 */     this.pressCooldown = nbt.func_74762_e("pressCooldown");
/*  73 */     this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/*  79 */     super.func_145841_b(nbt);
/*  80 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/*  82 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/*  84 */       if (this.storage[i] != null) {
/*     */         
/*  86 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  87 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  88 */         this.storage[i].func_77955_b(nbttagcompound1);
/*  89 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*  94 */     nbt.func_74768_a("TransferCooldown", this.cooldown);
/*     */     
/*  96 */     if (func_145818_k_())
/*     */     {
/*  98 */       nbt.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */     
/* 101 */     nbt.func_74768_a("pressCooldown", this.pressCooldown);
/*     */     
/* 103 */     if (this.pressBlock != null) {
/*     */       
/* 105 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 106 */       this.pressBlock.func_77955_b(nbttagcompound1);
/* 107 */       nbt.func_74782_a("pressBlock", (NBTBase)nbttagcompound1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/* 115 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 124 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 133 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int amount) {
/* 143 */     if (this.storage[i] != null) {
/*     */ 
/*     */ 
/*     */       
/* 147 */       if ((this.storage[i]).field_77994_a <= amount) {
/*     */         
/* 149 */         ItemStack itemStack = this.storage[i];
/* 150 */         this.storage[i] = null;
/* 151 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 155 */       ItemStack itemstack = this.storage[i].func_77979_a(amount);
/*     */       
/* 157 */       if ((this.storage[i]).field_77994_a == 0)
/*     */       {
/* 159 */         this.storage[i] = null;
/*     */       }
/*     */       
/* 162 */       return itemstack;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 178 */     if (this.storage[i] != null) {
/*     */       
/* 180 */       ItemStack itemstack = this.storage[i];
/* 181 */       this.storage[i] = null;
/* 182 */       return itemstack;
/*     */     } 
/*     */ 
/*     */     
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack is) {
/* 196 */     this.storage[i] = is;
/*     */     
/* 198 */     if (is != null && is.field_77994_a > func_70297_j_())
/*     */     {
/* 200 */       is.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 210 */     return func_145818_k_() ? this.customName : "container.hopper";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 219 */     return (this.customName != null && this.customName.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(String s) {
/* 224 */     this.customName = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 233 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer p) {
/* 242 */     return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((p.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack is) {
/* 257 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 263 */     if (this.field_145850_b.field_72995_K) {
/*     */       
/* 265 */       if (this.pressCooldown > 0) {
/* 266 */         this.pressCooldown--;
/*     */       } else {
/* 268 */         this.pressBlock = null;
/*     */       } 
/* 270 */     } else if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
/*     */       
/* 272 */       this.cooldown--;
/*     */       
/* 274 */       TFC_Core.handleItemTicking((IInventory)this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       if (getPressableItem() == -1) this.pressCooldown = 0;
/*     */       
/* 281 */       if (this.pressCooldown > 0) {
/*     */         
/* 283 */         this.pressCooldown--;
/* 284 */         if (this.pressCooldown % 20 == 0) {
/* 285 */           press();
/*     */         }
/* 287 */       } else if (this.pressCooldown == 0 && this.pressBlock != null) {
/*     */         
/* 289 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Block.func_149634_a(this.pressBlock.func_77973_b()), this.pressBlock.func_77960_j(), 2);
/* 290 */         this.pressBlock = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 318 */       if (!isCoolingDown())
/*     */       {
/* 320 */         setCooldown(0);
/*     */       }
/*     */       
/* 323 */       Block blockAbove = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 324 */       if (blockAbove != null && hasPressableItem() > 0)
/*     */       {
/* 326 */         if (this.pressBlock != null && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockGravel) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockSand) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockDirt)) {
/*     */           
/* 328 */           TFC_Core.setBlockToAirWithDrops(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/*     */         }
/* 330 */         else if (blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockSmooth) {
/*     */           
/* 332 */           this.pressBlock = new ItemStack(blockAbove, 1, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e));
/* 333 */           this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 334 */           sendPressPacket();
/* 335 */           beginPressing();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void press() {
/* 343 */     TEBarrel barrel = null;
/* 344 */     if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TEBarrel) {
/* 345 */       barrel = (TEBarrel)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*     */     }
/*     */     
/* 348 */     int num = getPressableItem();
/* 349 */     ItemStack item = null;
/* 350 */     if (num != -1) item = this.storage[num];
/*     */     
/* 352 */     if (item != null) {
/*     */       
/* 354 */       this.pressableItem = item;
/* 355 */       if (item.func_77973_b() == TFCItems.olive) {
/*     */         
/* 357 */         if (item.field_77994_a > 0)
/*     */         {
/* 359 */           Food.setWeight(item, Food.getWeight(item) - 0.64F);
/*     */         }
/*     */         
/* 362 */         if (barrel != null && barrel.canAcceptLiquids() && !barrel.getSealed())
/*     */         {
/* 364 */           barrel.addLiquid(new FluidStack(TFCFluids.OLIVEOIL, 1));
/*     */         }
/*     */       }
/* 367 */       else if (item.func_77973_b() == TFCItems.honeycomb) {
/*     */         
/* 369 */         if (item.field_77994_a > 0) {
/* 370 */           Food.setWeight(item, Food.getWeight(item) - 0.64F);
/*     */         }
/* 372 */         if (item.field_77994_a == 0 || Food.getWeight(item) < 0.64F) this.storage[num] = new ItemStack(TFCItems.paraffin);
/*     */         
/* 374 */         if (barrel != null && barrel.canAcceptLiquids() && !barrel.getSealed())
/*     */         {
/* 376 */           barrel.addLiquid(new FluidStack(TFCFluids.HONEY, 1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void beginPressing() {
/* 385 */     int pressWeight = hasPressableItem();
/* 386 */     if (pressWeight > 0) {
/*     */       
/* 388 */       this.pressCooldown = (int)(this.pressCooldown + pressWeight / 0.64F * 20.0F);
/* 389 */       sendCooldownPacket();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int hasPressableItem() {
/* 395 */     int amount = 0;
/* 396 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 398 */       if (this.storage[i] != null && (this.storage[i].func_77973_b() == TFCItems.olive || this.storage[i].func_77973_b() == TFCItems.honeycomb))
/*     */       {
/* 400 */         amount = (int)(amount + Math.floor(Food.getWeight(this.storage[i])));
/*     */       }
/*     */     } 
/* 403 */     return amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPressableItem() {
/* 408 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 410 */       if (this.storage[i] != null && (this.storage[i].func_77973_b() == TFCItems.olive || this.storage[i].func_77973_b() == TFCItems.honeycomb))
/*     */       {
/* 412 */         return i;
/*     */       }
/*     */     } 
/* 415 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCooldown(int time) {
/* 454 */     this.cooldown = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCoolingDown() {
/* 459 */     return (this.cooldown > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IInventory getInputInventory(IHopper hopper) {
/* 803 */     return searchForOutputInventory(hopper.func_145831_w(), hopper.func_96107_aA(), hopper.func_96109_aB() + 1.0D, hopper.func_96108_aC());
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityItem searchForLooseInput(World world, double x, double y, double z) {
/* 808 */     List<EntityItem> list = world.func_82733_a(EntityItem.class, AxisAlignedBB.func_72330_a(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), IEntitySelector.field_94557_a);
/* 809 */     return !list.isEmpty() ? list.get(0) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IInventory searchForOutputInventory(World world, double x, double y, double z) {
/* 814 */     IInventory iinventory = null;
/* 815 */     int i = MathHelper.func_76128_c(x);
/* 816 */     int j = MathHelper.func_76128_c(y);
/* 817 */     int k = MathHelper.func_76128_c(z);
/* 818 */     TileEntity tileentity = world.func_147438_o(i, j, k);
/*     */ 
/*     */     
/* 821 */     if (tileentity instanceof IInventory) {
/*     */       
/* 823 */       iinventory = (IInventory)tileentity;
/*     */       
/* 825 */       if (iinventory instanceof TEChest) {
/*     */         
/* 827 */         Block block = world.func_147439_a(i, j, k);
/*     */         
/* 829 */         if (block instanceof BlockChestTFC)
/*     */         {
/* 831 */           iinventory = ((BlockChestTFC)block).getInventory(world, i, j, k);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 836 */     if (iinventory == null) {
/*     */       
/* 838 */       List<IInventory> list = world.func_94576_a((Entity)null, AxisAlignedBB.func_72330_a(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), IEntitySelector.field_96566_b);
/*     */       
/* 840 */       if (list != null && !list.isEmpty())
/*     */       {
/* 842 */         iinventory = list.get(world.field_73012_v.nextInt(list.size()));
/*     */       }
/*     */     } 
/*     */     
/* 846 */     return iinventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 857 */     if (nbt.func_74764_b("pressBlock"))
/*     */     {
/* 859 */       this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
/*     */     }
/* 861 */     this.pressCooldown = nbt.func_74762_e("pressCooldown");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 867 */     if (this.pressBlock != null) {
/*     */       
/* 869 */       NBTTagCompound pb = new NBTTagCompound();
/* 870 */       this.pressBlock.func_77955_b(pb);
/* 871 */       nbt.func_74782_a("pressBlock", (NBTBase)pb);
/*     */     } 
/* 873 */     nbt.func_74768_a("pressCooldown", this.pressCooldown);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 879 */     if (nbt.func_74764_b("pressBlock"))
/*     */     {
/* 881 */       this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
/*     */     }
/* 883 */     if (nbt.func_74764_b("pressCooldown"))
/*     */     {
/* 885 */       this.pressCooldown = nbt.func_74762_e("pressCooldown");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendPressPacket() {
/* 891 */     NBTTagCompound nbt = new NBTTagCompound();
/* 892 */     if (this.pressBlock != null) {
/*     */       
/* 894 */       NBTTagCompound pb = new NBTTagCompound();
/* 895 */       this.pressBlock.func_77955_b(pb);
/* 896 */       nbt.func_74782_a("pressBlock", (NBTBase)pb);
/*     */     } 
/* 898 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendCooldownPacket() {
/* 903 */     NBTTagCompound nbt = new NBTTagCompound();
/* 904 */     nbt.func_74768_a("pressCooldown", this.pressCooldown);
/* 905 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_96107_aA() {
/* 910 */     return this.field_145851_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_96109_aB() {
/* 915 */     return this.field_145848_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_96108_aC() {
/* 920 */     return this.field_145849_e;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TEHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */