/*      */ package com.bioxx.tfc.Core;
/*      */ 
/*      */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*      */ import com.bioxx.tfc.Chunkdata.ChunkDataManager;
/*      */ import com.bioxx.tfc.Core.Player.BodyTempStats;
/*      */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*      */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*      */ import com.bioxx.tfc.Core.Player.SkillStats;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
/*      */ import com.bioxx.tfc.Items.ItemOre;
/*      */ import com.bioxx.tfc.Items.ItemTerra;
/*      */ import com.bioxx.tfc.TerraFirmaCraft;
/*      */ import com.bioxx.tfc.TileEntities.TEMetalSheet;
/*      */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*      */ import com.bioxx.tfc.api.Constant.Global;
/*      */ import com.bioxx.tfc.api.Entities.IAnimal;
/*      */ import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
/*      */ import com.bioxx.tfc.api.Enums.EnumSize;
/*      */ import com.bioxx.tfc.api.Food;
/*      */ import com.bioxx.tfc.api.Interfaces.IFood;
/*      */ import com.bioxx.tfc.api.Interfaces.ISize;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*      */ import cpw.mods.fml.common.FMLCommonHandler;
/*      */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import growthcraft.rice.GrowthCraftRice;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TFC_Core
/*      */ {
/*   81 */   private static Map<Integer, ChunkDataManager> cdmMap = new HashMap<Integer, ChunkDataManager>();
/*      */   
/*      */   public static boolean preventEntityDataUpdate;
/*      */   
/*      */   public static ChunkDataManager getCDM(World world) {
/*   86 */     int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
/*   87 */     return cdmMap.get(Integer.valueOf(key));
/*      */   }
/*      */ 
/*      */   
/*      */   public static ChunkDataManager addCDM(World world) {
/*   92 */     int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
/*   93 */     if (!cdmMap.containsKey(Integer.valueOf(key)))
/*   94 */       return cdmMap.put(Integer.valueOf(key), new ChunkDataManager(world)); 
/*   95 */     return cdmMap.get(Integer.valueOf(key));
/*      */   }
/*      */ 
/*      */   
/*      */   public static ChunkDataManager removeCDM(World world) {
/*  100 */     int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
/*  101 */     return cdmMap.remove(Integer.valueOf(key));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public static int getMouseX() {
/*  108 */     ScaledResolution scaledresolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
/*  109 */     int i = scaledresolution.func_78326_a();
/*  110 */     int k = Mouse.getX() * i / (Minecraft.func_71410_x()).field_71443_c;
/*      */     
/*  112 */     return k;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public static int getMouseY() {
/*  119 */     ScaledResolution scaledresolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
/*  120 */     int j = scaledresolution.func_78328_b();
/*  121 */     int l = j - Mouse.getY() * j / (Minecraft.func_71410_x()).field_71440_d - 1;
/*      */     
/*  123 */     return l;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Boolean isBlockAboveSolid(IBlockAccess blockAccess, int i, int j, int k) {
/*  128 */     if (TerraFirmaCraft.proxy.getCurrentWorld().func_147439_a(i, j + 1, k).func_149662_c())
/*  129 */       return Boolean.valueOf(true); 
/*  130 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getExtraEquipInventorySize() {
/*  135 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static InventoryPlayer getNewInventory(EntityPlayer player) {
/*  140 */     InventoryPlayer ip = player.field_71071_by;
/*  141 */     NBTTagList nbt = new NBTTagList();
/*  142 */     nbt = player.field_71071_by.func_70442_a(nbt);
/*  143 */     InventoryPlayerTFC inventoryPlayerTFC = new InventoryPlayerTFC(player);
/*  144 */     inventoryPlayerTFC.func_70443_b(nbt);
/*  145 */     return (InventoryPlayer)inventoryPlayerTFC;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static ItemStack getRandomGem(Random random, int meta) {
/*  151 */     ItemStack[] items = { new ItemStack(TFCItems.gemAgate, 1, meta), new ItemStack(TFCItems.gemAmethyst, 1, meta), new ItemStack(TFCItems.gemBeryl, 1, meta), new ItemStack(TFCItems.gemEmerald, 1, meta), new ItemStack(TFCItems.gemGarnet, 1, meta), new ItemStack(TFCItems.gemJade, 1, meta), new ItemStack(TFCItems.gemJasper, 1, meta), new ItemStack(TFCItems.gemOpal, 1, meta), new ItemStack(TFCItems.gemRuby, 1, meta), new ItemStack(TFCItems.gemSapphire, 1, meta), new ItemStack(TFCItems.gemTourmaline, 1, meta), new ItemStack(TFCItems.gemTopaz, 1, meta) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  164 */     return items[random.nextInt(items.length)];
/*      */   }
/*      */ 
/*      */   
/*      */   public static ItemStack randomGem(Random random, int rockType) {
/*  169 */     ItemStack is = null;
/*  170 */     if (random.nextInt(500) == 0) { is = getRandomGem(random, 0); }
/*  171 */     else if (random.nextInt(1000) == 0) { is = getRandomGem(random, 1); }
/*  172 */     else if (random.nextInt(2000) == 0) { is = getRandomGem(random, 2); }
/*  173 */     else if (random.nextInt(4000) == 0) { is = getRandomGem(random, 3); }
/*  174 */     else if (random.nextInt(8000) == 0) { is = getRandomGem(random, 4); }
/*  175 */      return is;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void surroundWithLeaves(World world, int i, int j, int k, int meta, Random r) {
/*  180 */     for (int y = 2; y >= -2; y--) {
/*      */       
/*  182 */       for (int x = 2; x >= -2; x--) {
/*      */         
/*  184 */         for (int z = 2; z >= -2; z--) {
/*      */           
/*  186 */           if (world.func_147437_c(i + x, j + y, k + z)) {
/*  187 */             world.func_147465_d(i + x, j + y, k + z, TFCBlocks.leaves, meta, 2);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setupWorld(World world) {
/*  195 */     long seed = world.func_72905_C();
/*  196 */     Random r = new Random(seed);
/*  197 */     world.field_73011_w.func_76558_a(world);
/*  198 */     Recipes.registerAnvilRecipes(r, world);
/*  199 */     TFC_Time.updateTime(world);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setupWorld(World w, long seed) {
/*      */     try {
/*  209 */       ReflectionHelper.setPrivateValue(WorldInfo.class, w.func_72912_H(), Long.valueOf(seed), 0);
/*  210 */       setupWorld(w);
/*      */     }
/*  212 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRawStone(World world, int x, int y, int z) {
/*  219 */     Block block = world.func_147439_a(x, y, z);
/*  220 */     return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneSed || block == TFCBlocks.stoneMM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSmoothStone(World world, int x, int y, int z) {
/*  228 */     Block block = world.func_147439_a(x, y, z);
/*  229 */     return (block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneMMSmooth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSmoothStone(Block block) {
/*  237 */     return (block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneMMSmooth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isBrickStone(Block block) {
/*  245 */     return (block == TFCBlocks.stoneIgExBrick || block == TFCBlocks.stoneIgInBrick || block == TFCBlocks.stoneSedBrick || block == TFCBlocks.stoneMMBrick);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRawStone(Block block) {
/*  253 */     return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneSed || block == TFCBlocks.stoneMM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isOreStone(Block block) {
/*  261 */     return (block == TFCBlocks.ore || block == TFCBlocks.ore2 || block == TFCBlocks.ore3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNaturalStone(Block block) {
/*  268 */     return (isRawStone(block) || isOreStone(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCobbleStone(Block block) {
/*  273 */     return (block == TFCBlocks.stoneIgExCobble || block == TFCBlocks.stoneIgInCobble || block == TFCBlocks.stoneSedCobble || block == TFCBlocks.stoneMMCobble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneIgEx(Block block) {
/*  281 */     return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgExCobble || block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgExBrick || block == TFCBlocks.wallRawIgEx || block == TFCBlocks.wallCobbleIgEx || block == TFCBlocks.wallBrickIgEx || block == TFCBlocks.wallSmoothIgEx);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneIgIn(Block block) {
/*  293 */     return (block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneIgInCobble || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneIgInBrick || block == TFCBlocks.wallRawIgIn || block == TFCBlocks.wallCobbleIgIn || block == TFCBlocks.wallBrickIgIn || block == TFCBlocks.wallSmoothIgIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneSed(Block block) {
/*  305 */     return (block == TFCBlocks.stoneSed || block == TFCBlocks.stoneSedCobble || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneSedBrick || block == TFCBlocks.wallRawSed || block == TFCBlocks.wallCobbleSed || block == TFCBlocks.wallBrickSed || block == TFCBlocks.wallSmoothSed);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneMM(Block block) {
/*  317 */     return (block == TFCBlocks.stoneMM || block == TFCBlocks.stoneMMCobble || block == TFCBlocks.stoneMMSmooth || block == TFCBlocks.stoneMMBrick || block == TFCBlocks.wallRawMM || block == TFCBlocks.wallCobbleMM || block == TFCBlocks.wallBrickMM || block == TFCBlocks.wallSmoothMM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isDirt(Block block) {
/*  329 */     return (block == TFCBlocks.dirt || block == TFCBlocks.dirt2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFarmland(Block block) {
/*  335 */     return (block == TFCBlocks.tilledSoil || block == TFCBlocks.tilledSoil2 || block == GrowthCraftRice.blocks.paddyField
/*      */       
/*  337 */       .getBlock());
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGrass(Block block) {
/*  342 */     return (block == TFCBlocks.grass || block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2 || block == TFCBlocks.peatGrass || block == TFCBlocks.dryGrass || block == TFCBlocks.dryGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGrassNormal(Block block) {
/*  353 */     return (block == TFCBlocks.grass || block == TFCBlocks.grass2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isLushGrass(Block block) {
/*  360 */     return (block == TFCBlocks.grass || block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2 || block == TFCBlocks.peatGrass);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isClayGrass(Block block) {
/*  369 */     return (block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isPeatGrass(Block block) {
/*  375 */     return (block == TFCBlocks.peatGrass);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDryGrass(Block block) {
/*  380 */     return (block == TFCBlocks.dryGrass || block == TFCBlocks.dryGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGrassType1(Block block) {
/*  386 */     return (block == TFCBlocks.grass || block == TFCBlocks.clayGrass || block == TFCBlocks.dryGrass);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGrassType2(Block block) {
/*  393 */     return (block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass2 || block == TFCBlocks.dryGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isClay(Block block) {
/*  400 */     return (block == TFCBlocks.clay || block == TFCBlocks.clay2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSand(Block block) {
/*  405 */     return (block == TFCBlocks.sand || block == TFCBlocks.sand2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isPeat(Block block) {
/*  411 */     return (block == TFCBlocks.peat);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isHotWater(Block block) {
/*  416 */     return (block == TFCBlocks.hotWater || block == TFCBlocks.hotWaterStationary);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWater(Block block) {
/*  421 */     return (isSaltWater(block) || 
/*  422 */       isFreshWater(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWaterFlowing(Block block) {
/*  427 */     return (block == TFCBlocks.saltWater || block == TFCBlocks.freshWater);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSaltWater(Block block) {
/*  432 */     return (block == TFCBlocks.saltWater || block == TFCBlocks.saltWaterStationary);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSaltWaterIncludeIce(Block block, int meta, Material mat) {
/*  437 */     return (block == TFCBlocks.saltWater || block == TFCBlocks.saltWaterStationary || (mat == Material.field_151588_w && meta == 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFreshWater(Block block) {
/*  443 */     return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFreshWaterIncludeIce(Block block, int meta) {
/*  448 */     return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary || (block == TFCBlocks.ice && meta != 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFreshWaterIncludeIce(Block block, int meta, Material mat) {
/*  454 */     return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary || (mat == Material.field_151588_w && meta != 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSoil(Block block) {
/*  460 */     return (isGrass(block) || 
/*  461 */       isDirt(block) || 
/*  462 */       isClay(block) || 
/*  463 */       isPeat(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSoilOrGravel(Block block) {
/*  468 */     return (isGrass(block) || 
/*  469 */       isDirt(block) || 
/*  470 */       isClay(block) || 
/*  471 */       isPeat(block) || 
/*  472 */       isGravel(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGravel(Block block) {
/*  477 */     return (block == TFCBlocks.gravel || block == TFCBlocks.gravel2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGround(Block block) {
/*  482 */     return (isSoilOrGravel(block) || 
/*  483 */       isRawStone(block) || 
/*  484 */       isSand(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGroundType1(Block block) {
/*  489 */     return (isGrassType1(block) || block == TFCBlocks.dirt || block == TFCBlocks.gravel || block == TFCBlocks.sand);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSoilWAILA(Block block) {
/*  494 */     return (isDirt(block) || isGravel(block) || isSand(block) || isGrassNormal(block) || isDryGrass(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getSoilMetaFromStone(Block inBlock, int inMeta) {
/*  499 */     if (inBlock == TFCBlocks.stoneIgIn)
/*  500 */       return inMeta; 
/*  501 */     if (inBlock == TFCBlocks.stoneSed)
/*  502 */       return inMeta + 3; 
/*  503 */     if (inBlock == TFCBlocks.stoneIgEx) {
/*  504 */       return inMeta + 11;
/*      */     }
/*      */     
/*  507 */     if (inMeta == 0)
/*  508 */       return inMeta + 15; 
/*  509 */     return inMeta - 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSoilMeta(int inMeta) {
/*  515 */     return inMeta & 0xF;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getItemMetaFromStone(Block inBlock, int inMeta) {
/*  520 */     if (inBlock == TFCBlocks.stoneIgIn)
/*  521 */       return inMeta; 
/*  522 */     if (inBlock == TFCBlocks.stoneSed)
/*  523 */       return inMeta + 3; 
/*  524 */     if (inBlock == TFCBlocks.stoneIgEx)
/*  525 */       return inMeta + 11; 
/*  526 */     if (inBlock == TFCBlocks.stoneMM) {
/*  527 */       return inMeta + 15;
/*      */     }
/*  529 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassWithRain(int inMeta, float rain) {
/*  534 */     if (rain >= 500.0F)
/*  535 */       return getTypeForGrass(inMeta); 
/*  536 */     return getTypeForDryGrass(inMeta);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassWithRainByBlock(Block block, float rain) {
/*  541 */     if (rain >= 500.0F)
/*  542 */       return getTypeForGrassFromSoil(block); 
/*  543 */     return getTypeForDryGrassFromSoil(block);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrass(int inMeta) {
/*  548 */     if (inMeta < 16)
/*  549 */       return TFCBlocks.grass; 
/*  550 */     return TFCBlocks.grass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassFromDirt(Block block) {
/*  555 */     if (block == TFCBlocks.dirt)
/*  556 */       return TFCBlocks.grass; 
/*  557 */     return TFCBlocks.grass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDryGrass(int inMeta) {
/*  562 */     if (inMeta < 16)
/*  563 */       return TFCBlocks.dryGrass; 
/*  564 */     return TFCBlocks.dryGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDryGrassFromSoil(Block block) {
/*  569 */     if (block == TFCBlocks.grass)
/*  570 */       return TFCBlocks.dryGrass; 
/*  571 */     if (block == TFCBlocks.dirt)
/*  572 */       return TFCBlocks.dryGrass; 
/*  573 */     return TFCBlocks.dryGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassFromSoil(Block block) {
/*  578 */     if (block == TFCBlocks.dryGrass)
/*  579 */       return TFCBlocks.grass; 
/*  580 */     if (block == TFCBlocks.dryGrass2)
/*  581 */       return TFCBlocks.grass2; 
/*  582 */     if (block == TFCBlocks.dirt)
/*  583 */       return TFCBlocks.grass; 
/*  584 */     return TFCBlocks.grass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClayGrass(int inMeta) {
/*  589 */     if (inMeta < 16)
/*  590 */       return TFCBlocks.clayGrass; 
/*  591 */     return TFCBlocks.clayGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClayGrass(Block block) {
/*  596 */     if (isGroundType1(block))
/*  597 */       return TFCBlocks.clayGrass; 
/*  598 */     return TFCBlocks.clayGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDirt(int inMeta) {
/*  603 */     if (inMeta < 16)
/*  604 */       return TFCBlocks.dirt; 
/*  605 */     return TFCBlocks.dirt2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDirtFromGrass(Block block) {
/*  610 */     if (isDirt(block))
/*  611 */       return block; 
/*  612 */     if (block == TFCBlocks.grass || block == TFCBlocks.dryGrass)
/*  613 */       return TFCBlocks.dirt; 
/*  614 */     return TFCBlocks.dirt2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForSoil(Block block) {
/*  619 */     if (isGrass(block)) {
/*      */       
/*  621 */       if (isGrassType1(block))
/*  622 */         return TFCBlocks.dirt; 
/*  623 */       if (isGrassType2(block))
/*  624 */         return TFCBlocks.dirt2; 
/*  625 */       if (isPeatGrass(block)) {
/*  626 */         return TFCBlocks.peat;
/*      */       }
/*      */     } 
/*  629 */     return block;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClay(int inMeta) {
/*  634 */     if (inMeta < 16)
/*  635 */       return TFCBlocks.clay; 
/*  636 */     return TFCBlocks.clay2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClay(Block block) {
/*  641 */     if (isGroundType1(block))
/*  642 */       return TFCBlocks.clay; 
/*  643 */     return TFCBlocks.clay2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForSand(int inMeta) {
/*  648 */     if (inMeta < 16)
/*  649 */       return TFCBlocks.sand; 
/*  650 */     return TFCBlocks.sand2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGravel(int inMeta) {
/*  655 */     if (inMeta < 16)
/*  656 */       return TFCBlocks.gravel; 
/*  657 */     return TFCBlocks.gravel2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getRockLayerFromHeight(World world, int x, int y, int z) {
/*  662 */     ChunkData cd = getCDM(world).getData(x >> 4, z >> 4);
/*  663 */     if (cd != null) {
/*      */       
/*  665 */       int[] hm = cd.heightmap;
/*  666 */       int localX = x & 0xF;
/*  667 */       int localZ = z & 0xF;
/*  668 */       int localY = localX + localZ * 16;
/*  669 */       if (y <= TFCOptions.rockLayer3Height + hm[localY])
/*  670 */         return 2; 
/*  671 */       if (y <= TFCOptions.rockLayer2Height + hm[localY]) {
/*  672 */         return 1;
/*      */       }
/*  674 */       return 0;
/*      */     } 
/*  676 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean convertGrassToDirt(World world, int i, int j, int k) {
/*  681 */     Block block = world.func_147439_a(i, j, k);
/*  682 */     int meta = world.func_72805_g(i, j, k);
/*  683 */     if (isGrass(block)) {
/*      */       
/*  685 */       if (isGrassType1(block)) {
/*      */         
/*  687 */         world.func_147465_d(i, j, k, TFCBlocks.dirt, meta, 2);
/*  688 */         return true;
/*      */       } 
/*  690 */       if (isGrassType2(block)) {
/*      */         
/*  692 */         world.func_147465_d(i, j, k, TFCBlocks.dirt2, meta, 2);
/*  693 */         return true;
/*      */       } 
/*      */     } 
/*  696 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumFuelMaterial getFuelMaterial(ItemStack is) {
/*  703 */     if (is.func_77973_b() == Item.func_150898_a(TFCBlocks.peat))
/*  704 */       return EnumFuelMaterial.PEAT; 
/*  705 */     if (is.func_77973_b() == TFCItems.coal && is.func_77960_j() == 0)
/*  706 */       return EnumFuelMaterial.COAL; 
/*  707 */     if (is.func_77973_b() == TFCItems.coal && is.func_77960_j() == 1)
/*  708 */       return EnumFuelMaterial.CHARCOAL; 
/*  709 */     if (is.func_77960_j() == 0)
/*  710 */       return EnumFuelMaterial.ASH; 
/*  711 */     if (is.func_77960_j() == 1)
/*  712 */       return EnumFuelMaterial.ASPEN; 
/*  713 */     if (is.func_77960_j() == 2)
/*  714 */       return EnumFuelMaterial.BIRCH; 
/*  715 */     if (is.func_77960_j() == 3)
/*  716 */       return EnumFuelMaterial.CHESTNUT; 
/*  717 */     if (is.func_77960_j() == 4)
/*  718 */       return EnumFuelMaterial.DOUGLASFIR; 
/*  719 */     if (is.func_77960_j() == 5)
/*  720 */       return EnumFuelMaterial.HICKORY; 
/*  721 */     if (is.func_77960_j() == 6)
/*  722 */       return EnumFuelMaterial.MAPLE; 
/*  723 */     if (is.func_77960_j() == 7)
/*  724 */       return EnumFuelMaterial.OAK; 
/*  725 */     if (is.func_77960_j() == 8)
/*  726 */       return EnumFuelMaterial.PINE; 
/*  727 */     if (is.func_77960_j() == 9)
/*  728 */       return EnumFuelMaterial.REDWOOD; 
/*  729 */     if (is.func_77960_j() == 10)
/*  730 */       return EnumFuelMaterial.SPRUCE; 
/*  731 */     if (is.func_77960_j() == 11)
/*  732 */       return EnumFuelMaterial.SYCAMORE; 
/*  733 */     if (is.func_77960_j() == 12)
/*  734 */       return EnumFuelMaterial.WHITECEDAR; 
/*  735 */     if (is.func_77960_j() == 13)
/*  736 */       return EnumFuelMaterial.WHITEELM; 
/*  737 */     if (is.func_77960_j() == 14)
/*  738 */       return EnumFuelMaterial.WILLOW; 
/*  739 */     if (is.func_77960_j() == 15)
/*  740 */       return EnumFuelMaterial.KAPOK; 
/*  741 */     if (is.func_77960_j() == 16)
/*  742 */       return EnumFuelMaterial.ACACIA; 
/*  743 */     return EnumFuelMaterial.ASPEN;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean showShiftInformation() {
/*  748 */     return (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(42));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean showCtrlInformation() {
/*  753 */     return (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(29));
/*      */   }
/*      */ 
/*      */   
/*      */   public static FoodStatsTFC getPlayerFoodStats(EntityPlayer player) {
/*  758 */     FoodStatsTFC foodstats = new FoodStatsTFC(player);
/*  759 */     foodstats.readNBT(player.getEntityData());
/*  760 */     return foodstats;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setPlayerFoodStats(EntityPlayer player, FoodStatsTFC foodstats) {
/*  765 */     foodstats.writeNBT(player.getEntityData());
/*      */   }
/*      */ 
/*      */   
/*      */   public static BodyTempStats getBodyTempStats(EntityPlayer player) {
/*  770 */     BodyTempStats body = new BodyTempStats();
/*  771 */     body.readNBT(player.getEntityData());
/*  772 */     return body;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setBodyTempStats(EntityPlayer player, BodyTempStats tempStats) {
/*  777 */     tempStats.writeNBT(player.getEntityData());
/*      */   }
/*      */ 
/*      */   
/*      */   public static SkillStats getSkillStats(EntityPlayer player) {
/*  782 */     SkillStats skills = new SkillStats(player);
/*  783 */     skills.readNBT(player.getEntityData());
/*  784 */     return skills;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setSkillStats(EntityPlayer player, SkillStats skills) {
/*  789 */     skills.writeNBT(player.getEntityData());
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTopFaceSolid(World world, int x, int y, int z) {
/*  794 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  795 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  797 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  798 */       if (te.topExists()) return true; 
/*      */     } 
/*  800 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBottomFaceSolid(World world, int x, int y, int z) {
/*  805 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  806 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  808 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  809 */       if (te.bottomExists()) return true; 
/*      */     } 
/*  811 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.DOWN);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isNorthFaceSolid(World world, int x, int y, int z) {
/*  816 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  817 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  819 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  820 */       if (te.northExists()) return true; 
/*      */     } 
/*  822 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.NORTH);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSouthFaceSolid(World world, int x, int y, int z) {
/*  827 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  828 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  830 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  831 */       if (te.southExists()) return true; 
/*      */     } 
/*  833 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.SOUTH);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isEastFaceSolid(World world, int x, int y, int z) {
/*  838 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  839 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  841 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  842 */       if (te.eastExists()) return true; 
/*      */     } 
/*  844 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.EAST);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWestFaceSolid(World world, int x, int y, int z) {
/*  849 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  850 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  852 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  853 */       if (te.westExists()) return true; 
/*      */     } 
/*  855 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.WEST);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSurroundedSolid(World world, int x, int y, int z) {
/*  860 */     return (isNorthFaceSolid(world, x, y, z + 1) && 
/*  861 */       isSouthFaceSolid(world, x, y, z - 1) && 
/*  862 */       isEastFaceSolid(world, x - 1, y, z) && 
/*  863 */       isWestFaceSolid(world, x + 1, y, z) && 
/*  864 */       isTopFaceSolid(world, x, y - 1, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSurroundedStone(World world, int x, int y, int z) {
/*  869 */     return (world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151576_e && world
/*  870 */       .func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151576_e && world
/*  871 */       .func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151576_e && world
/*  872 */       .func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151576_e && world
/*  873 */       .func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151576_e);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOreIron(ItemStack is) {
/*  878 */     return (is.func_77973_b() instanceof ItemOre && ((ItemOre)is.func_77973_b()).getMetalType(is) == Global.PIGIRON);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getEntityMaxHealth(EntityLivingBase entity) {
/*  883 */     return (float)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getPercentGrown(IAnimal animal) {
/*  888 */     float birth = animal.getBirthDay();
/*  889 */     float time = TFC_Time.getTotalDays();
/*  890 */     float percent = (time - birth) / animal.getNumberOfDaysToAdult();
/*  891 */     return Math.min(percent, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void bindTexture(ResourceLocation texture) {
/*  896 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPlayerInDebugMode(EntityPlayer player) {
/*  901 */     return TFCOptions.enableDebugMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addPlayerExhaustion(EntityPlayer player, float exhaustion) {
/*  909 */     FoodStatsTFC foodstats = getPlayerFoodStats(player);
/*  910 */     foodstats.addFoodExhaustion(exhaustion);
/*      */     
/*  912 */     setPlayerFoodStats(player, foodstats);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getEnvironmentalDecay(float temp) {
/*  917 */     if (temp > 0.0F) {
/*      */       
/*  919 */       float tempFactor = 1.0F - 15.0F / (15.0F + temp);
/*  920 */       return tempFactor * 2.0F;
/*      */     } 
/*      */     
/*  923 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z) {
/*  932 */     handleItemTicking(iinv, world, x, y, z, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z) {
/*  941 */     handleItemTicking(iinv, world, x, y, z, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/*  950 */     for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {
/*      */       
/*  952 */       ItemStack is = iinv.func_70301_a(i);
/*  953 */       if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }
/*      */       
/*  955 */       else if (is != null)
/*      */       
/*  957 */       { if (is.field_77994_a == 0) {
/*      */           
/*  959 */           iinv.func_70299_a(i, null);
/*      */         
/*      */         }
/*  962 */         else if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/*  963 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/*  964 */           is = tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
/*  965 */           if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/*  966 */           iinv.func_70299_a(i, is);
/*      */         }  }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte getByteFromSmallFloat(float f) {
/*  975 */     MathHelper.func_76131_a(f, 0.5F, 1.5F);
/*  976 */     return (byte)(Float.floatToIntBits(f) >> 16 & 0xFF);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getSmallFloatFromByte(byte b) {
/*  981 */     return ByteBuffer.wrap(new byte[] { 63, b, 0, 0 }).getFloat();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/*  990 */     for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {
/*      */       
/*  992 */       ItemStack is = iinv.func_70301_a(i);
/*  993 */       if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }
/*      */       
/*  995 */       else if (is != null)
/*      */       
/*  997 */       { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/*  998 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/*  999 */           is = tickDecay(is, world, x, y, z, environmentalDecayFactor, baseDecayMod);
/* 1000 */           if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/* 1001 */           iinv.func_70299_a(i, is);
/*      */         }  }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/* 1012 */     for (int i = 0; !world.field_72995_K && i < iinv.length; i++) {
/*      */       
/* 1014 */       ItemStack is = iinv[i];
/* 1015 */       if (is != null && is.field_77994_a <= 0) { iinv[i] = null; }
/*      */       
/* 1017 */       else if (is != null)
/*      */       
/* 1019 */       { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/* 1020 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/* 1021 */           is = tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
/* 1022 */           if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/* 1023 */           iinv[i] = is;
/*      */         }  }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemStack tickDecay(ItemStack is, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/* 1036 */     NBTTagCompound nbt = is.func_77978_p();
/* 1037 */     if (nbt == null || !nbt.func_74764_b("foodWeight") || !nbt.func_74764_b("foodDecay")) return is;
/*      */     
/* 1039 */     int decayTimer = Food.getDecayTimer(is);
/*      */     
/* 1041 */     if (decayTimer < TFC_Time.getTotalHours()) {
/*      */       
/* 1043 */       int timeDiff = (int)(TFC_Time.getTotalHours() - decayTimer);
/* 1044 */       float protMult = 1.0F;
/*      */       
/* 1046 */       if (TFCOptions.useDecayProtection)
/*      */       {
/* 1048 */         if (timeDiff > TFCOptions.decayProtectionDays * 24) {
/*      */           
/* 1050 */           decayTimer = (int)TFC_Time.getTotalHours() - 24;
/*      */         }
/* 1052 */         else if (timeDiff > 24) {
/*      */           
/* 1054 */           protMult = (1 - timeDiff / TFCOptions.decayProtectionDays * 24);
/*      */         } 
/*      */       }
/*      */       
/* 1058 */       float decay = Food.getDecay(is);
/* 1059 */       float thisDecayRate = 1.0F;
/*      */       
/* 1061 */       if (is.func_77973_b() instanceof IFood) {
/* 1062 */         thisDecayRate = ((IFood)is.func_77973_b()).getDecayRate(is);
/*      */       } else {
/*      */         
/* 1065 */         thisDecayRate = Food.getDecayRate(is);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1074 */       float temp = getCachedTemp(world, x, y, z, decayTimer);
/* 1075 */       float environmentalDecay = getEnvironmentalDecay(temp) * environmentalDecayFactor;
/*      */       
/* 1077 */       if (decay < 0.0F) {
/*      */         
/* 1079 */         float d = 1.0F * thisDecayRate * baseDecayMod * environmentalDecay;
/* 1080 */         if (decay + d < 0.0F) {
/* 1081 */           decay += d;
/*      */         } else {
/* 1083 */           decay = 0.0F;
/*      */         } 
/* 1085 */       } else if (decay == 0.0F) {
/*      */         
/* 1087 */         decay = Food.getWeight(is) * world.field_73012_v.nextFloat() * 0.005F * TFCOptions.decayMultiplier;
/*      */       }
/*      */       else {
/*      */         
/* 1091 */         double fdr = (TFCOptions.foodDecayRate - 1.0F);
/* 1092 */         fdr *= (thisDecayRate * baseDecayMod * environmentalDecay * protMult * TFCOptions.decayMultiplier);
/* 1093 */         decay = (float)(decay * (1.0D + fdr));
/*      */       } 
/* 1095 */       Food.setDecayTimer(is, decayTimer + 1);
/* 1096 */       Food.setDecay(is, decay);
/*      */     } 
/*      */     
/* 1099 */     if (Food.getDecay(is) / Food.getWeight(is) > 0.9F)
/*      */     {
/* 1101 */       if (is.func_77973_b() instanceof IFood) {
/* 1102 */         is = ((IFood)is.func_77973_b()).onDecayed(is, world, x, y, z);
/*      */       } else {
/* 1104 */         is.field_77994_a = 0;
/*      */       } 
/*      */     }
/* 1107 */     return is;
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getCachedTemp(World world, int x, int y, int z, int th) {
/* 1112 */     float cacheTemp = TFC_Climate.getCacheManager(world).getTemp(x, z, th);
/* 1113 */     if (cacheTemp != Float.MIN_VALUE)
/*      */     {
/* 1115 */       return cacheTemp;
/*      */     }
/* 1117 */     float temp = TFC_Climate.getHeightAdjustedTempSpecificDay(world, TFC_Time.getDayFromTotalHours(th), TFC_Time.getHourOfDayFromTotalHours(th), x, y, z);
/* 1118 */     addCachedTemp(world, x, z, th, temp);
/* 1119 */     return temp;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addCachedTemp(World world, int x, int z, int th, float temp) {
/* 1124 */     TFC_Climate.getCacheManager(world).addTemp(x, z, th, temp);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void animalDropMeat(Entity e, Item i, float foodWeight) {
/* 1130 */     ItemStack is = ItemFoodTFC.createTag(new ItemStack(i, 1), foodWeight);
/* 1131 */     Random r = new Random(e.func_110124_au().getLeastSignificantBits() + e.func_110124_au().getMostSignificantBits());
/* 1132 */     Food.adjustFlavor(is, r);
/* 1133 */     e.capturedDrops.add(new EntityItem(e.field_70170_p, e.field_70165_t, e.field_70163_u, e.field_70161_v, is));
/*      */   }
/*      */ 
/*      */   
/*      */   public static Vec3 getEntityPos(Entity e) {
/* 1138 */     return Vec3.func_72443_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void giveItemToPlayer(ItemStack is, EntityPlayer player) {
/* 1143 */     if (player.field_70170_p.field_72995_K)
/* 1144 */       return;  EntityItem ei = player.func_70099_a(is, 1.0F);
/* 1145 */     ei.field_145804_b = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFence(Block b) {
/* 1150 */     return (b == TFCBlocks.fence || b == TFCBlocks.fence2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isVertSupport(Block b) {
/* 1155 */     return (b == TFCBlocks.woodSupportV || b == TFCBlocks.woodSupportV2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isHorizSupport(Block b) {
/* 1160 */     return (b == TFCBlocks.woodSupportH || b == TFCBlocks.woodSupportH2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOceanicBiome(int id) {
/* 1165 */     return (id == TFCBiome.OCEAN.field_76756_M || id == TFCBiome.DEEP_OCEAN.field_76756_M);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isMountainBiome(int id) {
/* 1170 */     return (id == TFCBiome.MOUNTAINS.field_76756_M || id == TFCBiome.MOUNTAINS_EDGE.field_76756_M);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBeachBiome(int id) {
/* 1175 */     return (id == TFCBiome.BEACH.field_76756_M || id == TFCBiome.GRAVEL_BEACH.field_76756_M);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isValidCharcoalPitCover(Block block) {
/* 1180 */     if (Blocks.field_150480_ab.getFlammability(block) > 0 && block != TFCBlocks.logPile) return false;
/*      */     
/* 1182 */     return (block == TFCBlocks.logPile || 
/* 1183 */       isCobbleStone(block) || 
/* 1184 */       isBrickStone(block) || 
/* 1185 */       isSmoothStone(block) || 
/* 1186 */       isGround(block) || block == Blocks.field_150359_w || block == Blocks.field_150399_cn || block == TFCBlocks.metalTrapDoor || block == Blocks.field_150454_av || block
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1191 */       .func_149662_c());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage) {
/* 1196 */     writeInventoryToNBT(nbt, storage, "Items");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage, String name) {
/* 1201 */     NBTTagList nbttaglist = new NBTTagList();
/* 1202 */     for (int i = 0; i < storage.length; i++) {
/*      */       
/* 1204 */       if (storage[i] != null) {
/*      */         
/* 1206 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 1207 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 1208 */         storage[i].func_77955_b(nbttagcompound1);
/* 1209 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */       } 
/*      */     } 
/* 1212 */     nbt.func_74782_a(name, (NBTBase)nbttaglist);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage) {
/* 1217 */     readInventoryFromNBT(nbt, storage, "Items");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage, String name) {
/* 1222 */     NBTTagList nbttaglist = nbt.func_150295_c(name, 10);
/* 1223 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */       
/* 1225 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 1226 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 1227 */       if (byte0 >= 0 && byte0 < storage.length) {
/* 1228 */         storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ItemStack getItemInInventory(Item item, IInventory iinv) {
/* 1234 */     for (int i = 0; i < iinv.func_70302_i_(); i++) {
/*      */       
/* 1236 */       ItemStack is = iinv.func_70301_a(i);
/* 1237 */       if (is != null && is.func_77973_b() == item) return is; 
/*      */     } 
/* 1239 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void destroyBlock(World world, int x, int y, int z) {
/* 1244 */     if (world.func_147439_a(x, y, z) != Blocks.field_150350_a) {
/*      */       
/* 1246 */       world.func_147439_a(x, y, z).func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 1247 */       world.func_147468_f(x, y, z);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areItemsEqual(ItemStack is1, ItemStack is2) {
/* 1253 */     Item i1 = null; int d1 = 0;
/* 1254 */     Item i2 = null; int d2 = 0;
/* 1255 */     if (is1 != null) {
/*      */       
/* 1257 */       i1 = is1.func_77973_b(); d1 = is1.func_77960_j();
/*      */     } 
/* 1259 */     if (is2 != null) {
/*      */       
/* 1261 */       i2 = is2.func_77973_b(); d2 = is2.func_77960_j();
/*      */     } 
/* 1263 */     return (i1 == i2 && d1 == d2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean setBlockWithDrops(World world, int x, int y, int z, Block b, int meta) {
/* 1268 */     Block block = world.func_147439_a(x, y, z);
/*      */     
/* 1270 */     if (block.func_149688_o() != Material.field_151579_a) {
/*      */       
/* 1272 */       int l = world.func_72805_g(x, y, z);
/* 1273 */       world.func_72926_e(2001, x, y, z, Block.func_149682_b(block) + (l << 12));
/* 1274 */       block.func_149697_b(world, x, y, z, l, 0);
/*      */     } 
/* 1276 */     return world.func_147465_d(x, y, z, b, meta, 3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean setBlockToAirWithDrops(World world, int x, int y, int z) {
/* 1284 */     return world.func_147480_a(x, y, z, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWaterBiome(BiomeGenBase b) {
/* 1289 */     return (isBeachBiome(b.field_76756_M) || isOceanicBiome(b.field_76756_M) || b == TFCBiome.LAKE || b == TFCBiome.RIVER);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String translate(String s) {
/* 1294 */     return StatCollector.func_74838_a(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendInfoMessage(EntityPlayer player, IChatComponent text) {
/* 1299 */     text.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.valueOf(true));
/* 1300 */     player.func_146105_b(text);
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getSuperSeed(World w) {
/* 1305 */     return w.func_72905_C() + w.func_72912_H().func_76066_a().func_74763_f("superseed");
/*      */   }
/*      */   
/*      */   public static boolean isExposedToRain(World world, int x, int y, int z) {
/* 1309 */     if (world.func_72896_J()) {
/*      */       
/* 1311 */       int highestY = world.func_72874_g(x, z) - 1;
/* 1312 */       Block checkfire = world.func_147439_a(x, y, z);
/* 1313 */       boolean isfire = false;
/* 1314 */       if (checkfire instanceof com.bioxx.tfc.Blocks.BlockTerraContainer)
/* 1315 */         isfire = true; 
/* 1316 */       boolean isExposed = true;
/* 1317 */       if (world.func_72937_j(x, y + 1, z)) {
/*      */ 
/*      */         
/* 1320 */         if (world.func_147439_a(x, highestY, z) instanceof net.minecraft.block.BlockGlass || world
/* 1321 */           .func_147439_a(x, highestY, z) instanceof net.minecraft.block.BlockStainedGlass || (world
/*      */           
/* 1323 */           .isSideSolid(x, highestY, z, ForgeDirection.UP) && !isfire) || (world
/* 1324 */           .isSideSolid(x, highestY, z, ForgeDirection.DOWN) && !isfire))
/*      */         {
/* 1326 */           isExposed = false;
/*      */         }
/*      */       } else {
/* 1329 */         isExposed = false;
/*      */       } 
/* 1331 */       return isExposed;
/*      */     } 
/* 1333 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isItemHopperValid(ItemStack stackInSlot) {
/* 1338 */     if (stackInSlot.func_77973_b() instanceof net.minecraft.item.ItemTool || stackInSlot.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemTerraTool || stackInSlot.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon) return false; 
/* 1339 */     if (stackInSlot.func_77973_b() instanceof net.minecraft.item.ItemHoe || (stackInSlot.func_77973_b() instanceof ISize && (((ISize)stackInSlot.func_77973_b()).getSize(stackInSlot)).stackSize < EnumSize.SMALL.stackSize)) return false;
/*      */     
/* 1341 */     return true;
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Core\TFC_Core.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */