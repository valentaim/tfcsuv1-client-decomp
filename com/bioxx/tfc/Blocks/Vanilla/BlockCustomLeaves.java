/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomLeaves
/*     */   extends BlockLeaves
/*     */   implements IShearable
/*     */ {
/*     */   protected int[][][] adjacentTreeBlocks;
/*     */   protected String[] woodNames;
/*     */   protected IIcon[] icons;
/*     */   protected IIcon[] iconsOpaque;
/*     */   
/*     */   public BlockCustomLeaves() {
/*  46 */     func_149675_a(false);
/*  47 */     this.woodNames = new String[16];
/*  48 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*  49 */     this.icons = new IIcon[16];
/*  50 */     this.iconsOpaque = new IIcon[16];
/*  51 */     func_149675_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List list) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
/*  69 */     return TerraFirmaCraft.proxy.foliageColorMultiplier(bAccess, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149743_a(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, Entity p_149743_7_) {
/*  74 */     if (p_149743_7_ instanceof EntityPlayer || p_149743_7_ instanceof net.minecraft.entity.passive.EntityCow || p_149743_7_ instanceof net.minecraft.entity.passive.EntityHorse)
/*  75 */       return;  AxisAlignedBB axisalignedbb1 = func_149668_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_);
/*     */     
/*  77 */     if (axisalignedbb1 != null && p_149743_5_.func_72326_a(axisalignedbb1))
/*     */     {
/*  79 */       p_149743_6_.add(axisalignedbb1);
/*     */     }
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
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
/*  92 */     entity.field_70159_w *= 0.1D;
/*  93 */     entity.field_70179_y *= 0.1D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
/* 100 */     Block block = world.func_147439_a(x, y, z);
/*     */ 
/*     */     
/* 103 */     if (side == 0 && this.field_149760_C > 0.0D)
/* 104 */       return true; 
/* 105 */     if (side == 1 && this.field_149756_F < 1.0D)
/* 106 */       return true; 
/* 107 */     if (side == 2 && this.field_149754_D > 0.0D)
/* 108 */       return true; 
/* 109 */     if (side == 3 && this.field_149757_G < 1.0D)
/* 110 */       return true; 
/* 111 */     if (side == 4 && this.field_149759_B > 0.0D)
/* 112 */       return true; 
/* 113 */     if (side == 5 && this.field_149755_E < 1.0D) {
/* 114 */       return true;
/*     */     }
/* 116 */     return !block.func_149662_c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 122 */     func_149695_a(world, x, y, z, (Block)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginLeavesDecay(World world, int x, int y, int z) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int xOrig, int yOrig, int zOrig, Block b) {
/* 134 */     if (!world.field_72995_K) {
/*     */       
/* 136 */       int var6 = world.func_72805_g(xOrig, yOrig, zOrig);
/*     */       
/* 138 */       byte searchRadius = 4;
/* 139 */       int maxDist = searchRadius + 1;
/* 140 */       byte searchDistance = 11;
/* 141 */       int center = searchDistance / 2;
/* 142 */       this.adjacentTreeBlocks = (int[][][])null;
/* 143 */       if (this.adjacentTreeBlocks == null) {
/* 144 */         this.adjacentTreeBlocks = new int[searchDistance][searchDistance][searchDistance];
/*     */       }
/* 146 */       if (world.func_72904_c(xOrig - maxDist, yOrig - maxDist, zOrig - maxDist, xOrig + maxDist, yOrig + maxDist, zOrig + maxDist)) {
/*     */         
/* 148 */         for (int xd = -searchRadius; xd <= searchRadius; xd++) {
/*     */           
/* 150 */           int searchY = searchRadius - Math.abs(xd);
/* 151 */           for (int yd = -searchY; yd <= searchY; yd++) {
/*     */             
/* 153 */             int searchZ = searchY - Math.abs(yd);
/* 154 */             for (int zd = -searchZ; zd <= searchZ; zd++) {
/*     */               
/* 156 */               Block block = world.func_147439_a(xOrig + xd, yOrig + yd, zOrig + zd);
/*     */               
/* 158 */               if (block == TFCBlocks.logNatural || block == TFCBlocks.logNatural2) {
/* 159 */                 this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = 0;
/* 160 */               } else if (block == this && var6 == world.func_72805_g(xOrig + xd, yOrig + yd, zOrig + zd)) {
/* 161 */                 this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -2;
/*     */               } else {
/* 163 */                 this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -1;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 168 */         for (int pass = 1; pass <= 4; pass++) {
/*     */           
/* 170 */           for (int i = -searchRadius; i <= searchRadius; i++) {
/*     */             
/* 172 */             int searchY = searchRadius - Math.abs(i);
/* 173 */             for (int yd = -searchY; yd <= searchY; yd++) {
/*     */               
/* 175 */               int searchZ = searchY - Math.abs(yd);
/* 176 */               for (int zd = -searchZ; zd <= searchZ; zd++) {
/*     */                 
/* 178 */                 if (this.adjacentTreeBlocks[i + center][yd + center][zd + center] == pass - 1) {
/*     */                   
/* 180 */                   if (this.adjacentTreeBlocks[i + center - 1][yd + center][zd + center] == -2) {
/* 181 */                     this.adjacentTreeBlocks[i + center - 1][yd + center][zd + center] = pass;
/*     */                   }
/* 183 */                   if (this.adjacentTreeBlocks[i + center + 1][yd + center][zd + center] == -2) {
/* 184 */                     this.adjacentTreeBlocks[i + center + 1][yd + center][zd + center] = pass;
/*     */                   }
/* 186 */                   if (this.adjacentTreeBlocks[i + center][yd + center - 1][zd + center] == -2) {
/* 187 */                     this.adjacentTreeBlocks[i + center][yd + center - 1][zd + center] = pass;
/*     */                   }
/* 189 */                   if (this.adjacentTreeBlocks[i + center][yd + center + 1][zd + center] == -2) {
/* 190 */                     this.adjacentTreeBlocks[i + center][yd + center + 1][zd + center] = pass;
/*     */                   }
/* 192 */                   if (this.adjacentTreeBlocks[i + center][yd + center][zd + center - 1] == -2) {
/* 193 */                     this.adjacentTreeBlocks[i + center][yd + center][zd + center - 1] = pass;
/*     */                   }
/* 195 */                   if (this.adjacentTreeBlocks[i + center][yd + center][zd + center + 1] == -2) {
/* 196 */                     this.adjacentTreeBlocks[i + center][yd + center][zd + center + 1] = pass;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 204 */       int res = this.adjacentTreeBlocks[center][center][center];
/*     */       
/* 206 */       if (res < 0)
/*     */       {
/* 208 */         if (world.func_72938_d(xOrig, zOrig) != null) {
/* 209 */           destroyLeaves(world, xOrig, yOrig, zOrig);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void destroyLeaves(World world, int x, int y, int z) {
/* 216 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeLeaves(World world, int x, int y, int z) {
/* 221 */     func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 222 */     if (world.field_73012_v.nextInt(100) < 30)
/* 223 */       func_149642_a(world, x, y, z, new ItemStack(TFCItems.stick, 1)); 
/* 224 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149745_a(Random rand) {
/* 230 */     return (rand.nextInt(20) != 0) ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random rand, int j) {
/* 236 */     return Item.func_150898_a(TFCBlocks.sapling);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149690_a(World world, int x, int y, int z, int meta, float f, int i1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
/* 248 */     if (!world.field_72995_K) {
/*     */       
/* 250 */       ItemStack itemstack = entityplayer.field_71071_by.func_70448_g();
/* 251 */       int[] equipIDs = OreDictionary.getOreIDs(itemstack);
/* 252 */       for (int id : equipIDs) {
/*     */         
/* 254 */         String name = OreDictionary.getOreName(id);
/* 255 */         if (name.startsWith("itemScythe")) {
/*     */           
/* 257 */           for (int x = -1; x < 2; x++) {
/*     */             
/* 259 */             for (int z = -1; z < 2; z++) {
/*     */               
/* 261 */               for (int y = -1; y < 2; y++) {
/*     */                 
/* 263 */                 if (world.func_147439_a(i + x, j + y, k + z).func_149688_o() == Material.field_151584_j && entityplayer.field_71071_by
/* 264 */                   .func_70301_a(entityplayer.field_71071_by.field_70461_c) != null) {
/*     */                   
/* 266 */                   entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 267 */                   entityplayer.func_71020_j(0.045F);
/* 268 */                   if (world.field_73012_v.nextInt(100) < 11) {
/* 269 */                     func_149642_a(world, i + x, j + y, k + z, new ItemStack(TFCItems.stick, 1));
/* 270 */                   } else if (world.field_73012_v.nextInt(100) < 4 && TFCOptions.enableSaplingDrops) {
/* 271 */                     dropSapling(world, i + x, j + y, k + z, meta);
/* 272 */                   }  removeLeaves(world, i + x, j + y, k + z);
/* 273 */                   super.func_149636_a(world, entityplayer, i + x, j + y, k + z, meta);
/*     */                   
/* 275 */                   itemstack.func_77972_a(1, (EntityLivingBase)entityplayer);
/* 276 */                   if (itemstack.field_77994_a == 0) {
/* 277 */                     entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, null);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 287 */       entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 288 */       entityplayer.func_71020_j(0.025F);
/* 289 */       if (world.field_73012_v.nextInt(100) < 28) {
/* 290 */         func_149642_a(world, i, j, k, new ItemStack(TFCItems.stick, 1));
/* 291 */       } else if (world.field_73012_v.nextInt(100) < 6 && TFCOptions.enableSaplingDrops) {
/* 292 */         dropSapling(world, i, j, k, meta);
/*     */       } 
/* 294 */       super.func_149636_a(world, entityplayer, i, j, k, meta);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropSapling(World world, int x, int y, int z, int meta) {
/* 301 */     if (meta != 9 && meta != 15) {
/* 302 */       func_149642_a(world, x, y, z, new ItemStack(func_149650_a(0, (Random)null, 0), 1, meta));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/* 308 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 314 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 320 */     if (meta > this.woodNames.length - 1)
/* 321 */       meta = 0; 
/* 322 */     if (TerraFirmaCraft.proxy.getGraphicsLevel()) {
/* 323 */       return this.icons[meta];
/*     */     }
/* 325 */     return this.iconsOpaque[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 331 */     for (int i = 0; i < this.woodNames.length; i++) {
/*     */       
/* 333 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Leaves Fancy");
/* 334 */       this.iconsOpaque[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Leaves");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] func_150125_e() {
/* 341 */     return (String[])this.woodNames.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
/* 347 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomLeaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */