/*      */ package com.bioxx.tfc.WAILA;
/*      */ import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
/*      */ import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
/*      */ import com.bioxx.tfc.Core.Player.SkillStats;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Food.CropIndex;
/*      */ import com.bioxx.tfc.Food.FloraIndex;
/*      */ import com.bioxx.tfc.Food.FloraManager;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*      */ import com.bioxx.tfc.TileEntities.TEBerryBush;
/*      */ import com.bioxx.tfc.TileEntities.TEBlastFurnace;
/*      */ import com.bioxx.tfc.TileEntities.TEBloom;
/*      */ import com.bioxx.tfc.TileEntities.TEBloomery;
/*      */ import com.bioxx.tfc.TileEntities.TECrop;
/*      */ import com.bioxx.tfc.TileEntities.TEFarmland;
/*      */ import com.bioxx.tfc.TileEntities.TEFruitLeaves;
/*      */ import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
/*      */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*      */ import com.bioxx.tfc.TileEntities.TELoom;
/*      */ import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
/*      */ import com.bioxx.tfc.TileEntities.TEOilLamp;
/*      */ import com.bioxx.tfc.TileEntities.TEOre;
/*      */ import com.bioxx.tfc.TileEntities.TESmokeRack;
/*      */ import com.bioxx.tfc.api.Constant.Global;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.LoomRecipe;
/*      */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*      */ import com.bioxx.tfc.api.Food;
/*      */ import com.bioxx.tfc.api.HeatRegistry;
/*      */ import com.bioxx.tfc.api.Interfaces.IFood;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCFluids;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.Util.Helper;
/*      */ import java.util.List;
/*      */ import mcp.mobius.waila.api.IWailaConfigHandler;
/*      */ import mcp.mobius.waila.api.IWailaDataAccessor;
/*      */ import mcp.mobius.waila.api.IWailaRegistrar;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.fluids.FluidStack;
/*      */ 
/*      */ public class WAILAData implements IWailaDataProvider {
/*      */   public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*   56 */     Block block = accessor.getBlock();
/*   57 */     TileEntity tileEntity = accessor.getTileEntity();
/*      */     
/*   59 */     World world = accessor.getWorld();
/*   60 */     MovingObjectPosition pos = accessor.getPosition();
/*      */     
/*   62 */     if (block instanceof BlockAnvil) {
/*   63 */       return anvilStack(accessor, config);
/*      */     }
/*   65 */     if (tileEntity instanceof TEBerryBush) {
/*   66 */       return berryBushStack(accessor, config);
/*      */     }
/*   68 */     if (tileEntity instanceof TEBloom) {
/*   69 */       return new ItemStack(TFCItems.rawBloom);
/*      */     }
/*   71 */     if (block instanceof BlockCharcoal) {
/*   72 */       return new ItemStack(TFCItems.coal, accessor.getMetadata(), 1);
/*      */     }
/*   74 */     if (TFC_Core.isClay(block) || TFC_Core.isClayGrass(block)) {
/*   75 */       return new ItemStack(TFCItems.clayBall);
/*      */     }
/*   77 */     if (block instanceof BlockCobble) {
/*   78 */       return new ItemStack(block, 1, accessor.getMetadata() % 8);
/*      */     }
/*   80 */     if (tileEntity instanceof TECrop) {
/*   81 */       return cropStack(accessor, config);
/*      */     }
/*   83 */     if (block instanceof BlockCustomDoor) {
/*   84 */       return new ItemStack(Recipes.doors[((BlockCustomDoor)block).getWoodType() / 2]);
/*      */     }
/*   86 */     if (tileEntity instanceof TEFruitLeaves) {
/*   87 */       return fruitLeavesStack(accessor, config);
/*      */     }
/*   89 */     if (tileEntity instanceof TEFruitTreeWood) {
/*   90 */       return fruitTreeWoodStack(accessor, config);
/*      */     }
/*   92 */     if (tileEntity instanceof TEIngotPile) {
/*   93 */       return ingotPileStack(accessor, config);
/*      */     }
/*   95 */     if (tileEntity instanceof TELoom) {
/*   96 */       return loomStack(accessor, config);
/*      */     }
/*   98 */     if (tileEntity instanceof TEMetalSheet) {
/*   99 */       return metalSheetStack(accessor, config);
/*      */     }
/*  101 */     if (tileEntity instanceof TEMetalTrapDoor) {
/*  102 */       return metalTrapDoorStack(accessor, config);
/*      */     }
/*  104 */     if (tileEntity instanceof TEOilLamp) {
/*  105 */       return oilLampStack(accessor, config);
/*      */     }
/*  107 */     if (tileEntity instanceof TEOre) {
/*  108 */       return oreStack(accessor, config);
/*      */     }
/*  110 */     if (block instanceof BlockPartial) {
/*  111 */       return partialStack(accessor, config);
/*      */     }
/*  113 */     if (block instanceof BlockWaterPlant && TFC_Core.isSaltWater(world.func_147439_a(pos.field_72311_b, pos.field_72312_c + 1, pos.field_72309_d))) {
/*  114 */       return ItemFoodTFC.createTag(new ItemStack(TFCItems.seaWeed, 1, 0));
/*      */     }
/*  116 */     if (tileEntity instanceof TEWorldItem) {
/*  117 */       return worldItemStack(accessor, config);
/*      */     }
/*  119 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  125 */     Block block = accessor.getBlock();
/*  126 */     TileEntity tileEntity = accessor.getTileEntity();
/*      */     
/*  128 */     if (tileEntity instanceof TEBarrel) {
/*  129 */       currenttip = barrelHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  131 */     else if (tileEntity instanceof TEFruitLeaves) {
/*  132 */       currenttip = fruitLeavesHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  134 */     else if (tileEntity instanceof TEFruitTreeWood) {
/*  135 */       currenttip = fruitTreeWoodHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  137 */     else if (tileEntity instanceof TEOre) {
/*  138 */       currenttip = oreHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  140 */     else if (tileEntity instanceof TESmokeRack) {
/*  141 */       currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.SmokeRack.name"));
/*      */     }
/*  143 */     else if (block instanceof BlockWaterPlant) {
/*  144 */       currenttip = waterPlantHead(itemStack, currenttip, accessor, config);
/*      */     } 
/*  146 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  152 */     Block block = accessor.getBlock();
/*  153 */     TileEntity tileEntity = accessor.getTileEntity();
/*  154 */     if (tileEntity instanceof TEAnvil) {
/*  155 */       currenttip = anvilBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  157 */     else if (tileEntity instanceof TEBarrel) {
/*  158 */       currenttip = barrelBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  160 */     else if (tileEntity instanceof TEBerryBush) {
/*  161 */       currenttip = berryBushBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  163 */     else if (tileEntity instanceof TEBlastFurnace) {
/*  164 */       currenttip = blastFurnaceBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  166 */     else if (tileEntity instanceof TEBloom) {
/*  167 */       currenttip = bloomBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  169 */     else if (tileEntity instanceof TEBloomery) {
/*  170 */       currenttip = bloomeryBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  172 */     else if (tileEntity instanceof TECrop) {
/*  173 */       currenttip = cropBody(itemStack, currenttip, accessor, config);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  179 */     else if (tileEntity instanceof TEFirepit) {
/*  180 */       currenttip = firepitBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  182 */     else if (tileEntity instanceof TEForge) {
/*  183 */       currenttip = forgeBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  185 */     else if (tileEntity instanceof TEFruitLeaves) {
/*  186 */       currenttip = fruitLeavesBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  188 */     else if (tileEntity instanceof TELogPile) {
/*  189 */       currenttip = logPileBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  191 */     else if (tileEntity instanceof TELoom) {
/*  192 */       currenttip = loomBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  194 */     else if (tileEntity instanceof TEMetalTrapDoor) {
/*  195 */       currenttip = metalTrapDoorBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  197 */     else if (tileEntity instanceof TENestBox) {
/*  198 */       currenttip = nestBoxBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  200 */     else if (tileEntity instanceof TEOilLamp) {
/*  201 */       currenttip = oilLampBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  203 */     else if (tileEntity instanceof TEOre) {
/*  204 */       currenttip = oreBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  206 */     else if (tileEntity instanceof TEPottery) {
/*  207 */       currenttip = potteryBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  209 */     else if (tileEntity instanceof TESapling) {
/*  210 */       currenttip = saplingBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  212 */     else if (tileEntity instanceof TESluice) {
/*  213 */       currenttip = sluiceBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  215 */     else if (tileEntity instanceof TESmokeRack) {
/*  216 */       currenttip = smokeRackBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  218 */     else if (TFC_Core.isSoilWAILA(block)) {
/*  219 */       currenttip = soilBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  221 */     else if (tileEntity instanceof TESpawnMeter) {
/*  222 */       currenttip = spawnMeterBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  224 */     else if (block == TFCBlocks.torch) {
/*  225 */       currenttip = torchBody(itemStack, currenttip, accessor, config);
/*      */     } 
/*  227 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  233 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
/*  239 */     if (te != null)
/*  240 */       te.func_145841_b(tag); 
/*  241 */     return tag;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void callbackRegister(IWailaRegistrar reg) {
/*  246 */     reg.addConfig("TerraFirmaCraft", "tfc.oreQuality");
/*      */     
/*  248 */     reg.registerStackProvider(new WAILAData(), BlockAnvil.class);
/*  249 */     reg.registerBodyProvider(new WAILAData(), TEAnvil.class);
/*  250 */     reg.registerNBTProvider(new WAILAData(), TEAnvil.class);
/*      */     
/*  252 */     reg.registerHeadProvider(new WAILAData(), TEBarrel.class);
/*  253 */     reg.registerBodyProvider(new WAILAData(), TEBarrel.class);
/*  254 */     reg.registerNBTProvider(new WAILAData(), TEBarrel.class);
/*      */     
/*  256 */     reg.registerStackProvider(new WAILAData(), TEBerryBush.class);
/*  257 */     reg.registerBodyProvider(new WAILAData(), TEBerryBush.class);
/*  258 */     reg.registerNBTProvider(new WAILAData(), TEBerryBush.class);
/*      */     
/*  260 */     reg.registerBodyProvider(new WAILAData(), TEBlastFurnace.class);
/*  261 */     reg.registerNBTProvider(new WAILAData(), TEBlastFurnace.class);
/*      */     
/*  263 */     reg.registerStackProvider(new WAILAData(), TEBloom.class);
/*  264 */     reg.registerBodyProvider(new WAILAData(), TEBloom.class);
/*  265 */     reg.registerNBTProvider(new WAILAData(), TEBloom.class);
/*      */     
/*  267 */     reg.registerBodyProvider(new WAILAData(), TEBloomery.class);
/*  268 */     reg.registerNBTProvider(new WAILAData(), TEBloomery.class);
/*      */     
/*  270 */     reg.registerStackProvider(new WAILAData(), BlockCharcoal.class);
/*  271 */     reg.registerStackProvider(new WAILAData(), BlockClay.class);
/*  272 */     reg.registerStackProvider(new WAILAData(), BlockClayGrass.class);
/*  273 */     reg.registerStackProvider(new WAILAData(), BlockCobble.class);
/*      */     
/*  275 */     reg.registerStackProvider(new WAILAData(), TECrop.class);
/*  276 */     reg.registerBodyProvider(new WAILAData(), TECrop.class);
/*  277 */     reg.registerNBTProvider(new WAILAData(), TECrop.class);
/*      */     
/*  279 */     reg.registerStackProvider(new WAILAData(), BlockCustomDoor.class);
/*      */     
/*  281 */     reg.registerBodyProvider(new WAILAData(), TEFarmland.class);
/*  282 */     reg.registerNBTProvider(new WAILAData(), TEFarmland.class);
/*      */     
/*  284 */     reg.registerBodyProvider(new WAILAData(), TEFirepit.class);
/*  285 */     reg.registerNBTProvider(new WAILAData(), TEFirepit.class);
/*      */     
/*  287 */     reg.registerBodyProvider(new WAILAData(), TEForge.class);
/*  288 */     reg.registerNBTProvider(new WAILAData(), TEForge.class);
/*      */     
/*  290 */     reg.registerStackProvider(new WAILAData(), TEFruitLeaves.class);
/*  291 */     reg.registerHeadProvider(new WAILAData(), TEFruitLeaves.class);
/*  292 */     reg.registerBodyProvider(new WAILAData(), TEFruitLeaves.class);
/*  293 */     reg.registerNBTProvider(new WAILAData(), TEFruitLeaves.class);
/*      */     
/*  295 */     reg.registerStackProvider(new WAILAData(), TEFruitTreeWood.class);
/*  296 */     reg.registerHeadProvider(new WAILAData(), TEFruitTreeWood.class);
/*      */     
/*  298 */     reg.registerStackProvider(new WAILAData(), TEIngotPile.class);
/*  299 */     reg.registerHeadProvider(new WAILAData(), TEIngotPile.class);
/*  300 */     reg.registerNBTProvider(new WAILAData(), TEIngotPile.class);
/*      */     
/*  302 */     reg.registerBodyProvider(new WAILAData(), TELogPile.class);
/*  303 */     reg.registerNBTProvider(new WAILAData(), TELogPile.class);
/*      */     
/*  305 */     reg.registerStackProvider(new WAILAData(), TELoom.class);
/*  306 */     reg.registerBodyProvider(new WAILAData(), TELoom.class);
/*  307 */     reg.registerNBTProvider(new WAILAData(), TELoom.class);
/*      */     
/*  309 */     reg.registerStackProvider(new WAILAData(), TEMetalSheet.class);
/*  310 */     reg.registerNBTProvider(new WAILAData(), TEMetalSheet.class);
/*      */     
/*  312 */     reg.registerStackProvider(new WAILAData(), TEMetalTrapDoor.class);
/*  313 */     reg.registerBodyProvider(new WAILAData(), TEMetalTrapDoor.class);
/*  314 */     reg.registerNBTProvider(new WAILAData(), TEMetalTrapDoor.class);
/*      */     
/*  316 */     reg.registerBodyProvider(new WAILAData(), TENestBox.class);
/*  317 */     reg.registerNBTProvider(new WAILAData(), TENestBox.class);
/*      */     
/*  319 */     reg.registerStackProvider(new WAILAData(), TEOilLamp.class);
/*  320 */     reg.registerBodyProvider(new WAILAData(), TEOilLamp.class);
/*  321 */     reg.registerNBTProvider(new WAILAData(), TEOilLamp.class);
/*      */     
/*  323 */     reg.registerStackProvider(new WAILAData(), TEOre.class);
/*  324 */     reg.registerHeadProvider(new WAILAData(), TEOre.class);
/*  325 */     reg.registerBodyProvider(new WAILAData(), TEOre.class);
/*      */     
/*  327 */     reg.registerStackProvider(new WAILAData(), BlockPartial.class);
/*  328 */     reg.registerNBTProvider(new WAILAData(), BlockPartial.class);
/*      */     
/*  330 */     reg.registerBodyProvider(new WAILAData(), TEPottery.class);
/*  331 */     reg.registerNBTProvider(new WAILAData(), TEPottery.class);
/*      */     
/*  333 */     reg.registerBodyProvider(new WAILAData(), TESapling.class);
/*  334 */     reg.registerNBTProvider(new WAILAData(), TESapling.class);
/*      */     
/*  336 */     reg.registerBodyProvider(new WAILAData(), TESluice.class);
/*  337 */     reg.registerNBTProvider(new WAILAData(), TESluice.class);
/*      */     
/*  339 */     reg.registerHeadProvider(new WAILAData(), TESmokeRack.class);
/*  340 */     reg.registerBodyProvider(new WAILAData(), TESmokeRack.class);
/*  341 */     reg.registerNBTProvider(new WAILAData(), TESmokeRack.class);
/*      */     
/*  343 */     reg.registerBodyProvider(new WAILAData(), TESpawnMeter.class);
/*  344 */     reg.registerNBTProvider(new WAILAData(), TESpawnMeter.class);
/*      */ 
/*      */     
/*  347 */     reg.registerBodyProvider(new WAILAData(), BlockDirt.class);
/*  348 */     reg.registerBodyProvider(new WAILAData(), BlockSand.class);
/*  349 */     reg.registerBodyProvider(new WAILAData(), BlockGrass.class);
/*  350 */     reg.registerBodyProvider(new WAILAData(), BlockGravel.class);
/*      */     
/*  352 */     reg.registerBodyProvider(new WAILAData(), BlockTorch.class);
/*  353 */     reg.registerNBTProvider(new WAILAData(), BlockTorch.class);
/*      */     
/*  355 */     reg.registerStackProvider(new WAILAData(), BlockWaterPlant.class);
/*  356 */     reg.registerHeadProvider(new WAILAData(), BlockWaterPlant.class);
/*      */     
/*  358 */     reg.registerStackProvider(new WAILAData(), TEWorldItem.class);
/*  359 */     reg.registerNBTProvider(new WAILAData(), TEWorldItem.class);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack anvilStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  365 */     Block block = accessor.getBlock();
/*  366 */     int meta = accessor.getMetadata();
/*  367 */     int type = BlockAnvil.getAnvilTypeFromMeta(meta);
/*      */     
/*  369 */     return new ItemStack(block, 1, type);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack berryBushStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  374 */     NBTTagCompound tag = accessor.getNBTData();
/*  375 */     boolean hasFruit = tag.func_74767_n("hasFruit");
/*  376 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockBerryBush.metaNames[accessor.getMetadata()]);
/*      */     
/*  378 */     if (hasFruit) {
/*  379 */       return ItemFoodTFC.createTag(index.getOutput());
/*      */     }
/*  381 */     return null;
/*      */   }
/*      */   
/*      */   public ItemStack cropStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*      */     ItemStack itemstack;
/*  386 */     NBTTagCompound tag = accessor.getNBTData();
/*  387 */     int cropId = tag.func_74762_e("cropId");
/*      */     
/*  389 */     CropIndex crop = CropManager.getInstance().getCropFromId(cropId);
/*      */ 
/*      */     
/*  392 */     if (crop.output2 != null) {
/*  393 */       itemstack = new ItemStack(crop.output2);
/*      */     } else {
/*  395 */       itemstack = new ItemStack(crop.output1);
/*      */     } 
/*  397 */     ItemFoodTFC.createTag(itemstack);
/*  398 */     return itemstack;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack fruitLeavesStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  403 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata() % 8));
/*      */     
/*  405 */     if (index != null) {
/*  406 */       return ItemFoodTFC.createTag(index.getOutput());
/*      */     }
/*  408 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack fruitTreeWoodStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  413 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitWood.getType(accessor.getMetadata()));
/*      */     
/*  415 */     if (index != null) {
/*  416 */       return ItemFoodTFC.createTag(index.getOutput());
/*      */     }
/*  418 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack ingotPileStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  423 */     NBTTagCompound tag = accessor.getNBTData();
/*  424 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  426 */     if (storage[0] != null) {
/*  427 */       return storage[0];
/*      */     }
/*  429 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack loomStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  434 */     NBTTagCompound tag = accessor.getNBTData();
/*  435 */     boolean finished = tag.func_74767_n("finished");
/*  436 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  438 */     if (finished && storage[1] != null)
/*      */     {
/*  440 */       return storage[1];
/*      */     }
/*  442 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack metalSheetStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  447 */     NBTTagCompound tag = accessor.getNBTData();
/*  448 */     return ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack metalTrapDoorStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  453 */     NBTTagCompound tag = accessor.getNBTData();
/*  454 */     return ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack oilLampStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  459 */     int meta = accessor.getMetadata();
/*  460 */     if ((meta & 0x8) != 0) {
/*  461 */       meta -= 8;
/*      */     }
/*  463 */     return new ItemStack(TFCBlocks.oilLamp, 1, meta);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack oreStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  468 */     int meta = accessor.getMetadata();
/*  469 */     TEOre te = (TEOre)accessor.getTileEntity();
/*  470 */     ItemStack itemstack = null;
/*      */     
/*  472 */     if (accessor.getBlock() == TFCBlocks.ore) {
/*      */       
/*  474 */       if (config.getConfig("tfc.oreQuality")) {
/*  475 */         itemstack = new ItemStack(TFCItems.oreChunk, 1, getOreGrade(te, meta));
/*      */       } else {
/*  477 */         itemstack = new ItemStack(TFCItems.oreChunk, 1, meta);
/*      */       } 
/*  479 */       if (meta == 14 || meta == 15) {
/*  480 */         itemstack = new ItemStack(TFCItems.coal);
/*      */       }
/*  482 */       return itemstack;
/*      */     } 
/*  484 */     if (accessor.getBlock() == TFCBlocks.ore2) {
/*      */       
/*  486 */       itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length);
/*  487 */       if (meta == 5) {
/*  488 */         itemstack = new ItemStack(TFCItems.gemDiamond);
/*  489 */       } else if (meta == 13) {
/*  490 */         itemstack = new ItemStack(TFCItems.powder, 1, 4);
/*      */       } 
/*  492 */       return itemstack;
/*      */     } 
/*  494 */     if (accessor.getBlock() == TFCBlocks.ore3) {
/*      */       
/*  496 */       itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length);
/*  497 */       return itemstack;
/*      */     } 
/*      */     
/*  500 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack partialStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  505 */     NBTTagCompound tag = accessor.getNBTData();
/*  506 */     byte metaID = tag.func_74771_c("metaID");
/*  507 */     int typeID = tag.func_74765_d("typeID");
/*      */     
/*  509 */     return new ItemStack(Block.func_149729_e(typeID), 1, metaID);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack worldItemStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  514 */     NBTTagCompound tag = accessor.getNBTData();
/*  515 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  516 */     return storage[0];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> barrelHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  522 */     String head = currenttip.get(0);
/*  523 */     NBTTagCompound tag = accessor.getNBTData();
/*  524 */     FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluidNBT"));
/*      */     
/*  526 */     if (fluid != null) {
/*      */       
/*  528 */       head = head + " (" + fluid.getLocalizedName() + ")";
/*  529 */       currenttip.set(0, head);
/*      */     } 
/*  531 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> fruitLeavesHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  536 */     NBTTagCompound tag = accessor.getNBTData();
/*  537 */     boolean hasFruit = tag.func_74767_n("hasFruit");
/*  538 */     String type = BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata());
/*      */     
/*  540 */     if (!hasFruit) {
/*  541 */       currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));
/*      */     }
/*  543 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> fruitTreeWoodHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  548 */     String type = BlockFruitWood.getType(accessor.getMetadata());
/*      */     
/*  550 */     currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));
/*      */     
/*  552 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> ingotPileHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  557 */     String head = currenttip.get(0);
/*  558 */     currenttip.set(0, head + " " + TFC_Core.translate("gui.pile"));
/*  559 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> oreHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  564 */     int meta = accessor.getMetadata();
/*      */     
/*  566 */     if (accessor.getBlock() == TFCBlocks.ore) {
/*      */       
/*  568 */       if (meta == 14)
/*      */       {
/*  570 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Bituminous Coal.name"));
/*      */       }
/*  572 */       else if (meta == 15)
/*      */       {
/*  574 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Lignite.name"));
/*      */       }
/*      */     
/*  577 */     } else if (accessor.getBlock() == TFCBlocks.ore2) {
/*      */       
/*  579 */       if (meta == 5) {
/*      */         
/*  581 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Kimberlite.name"));
/*      */       }
/*  583 */       else if (meta == 13) {
/*      */         
/*  585 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Saltpeter.name"));
/*      */       } 
/*      */     } 
/*      */     
/*  589 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> waterPlantHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  594 */     MovingObjectPosition pos = accessor.getPosition();
/*  595 */     World world = accessor.getWorld();
/*  596 */     Block blockDirectlyAbove = world.func_147439_a(pos.field_72311_b, pos.field_72312_c + 1, pos.field_72309_d);
/*  597 */     boolean airTwoAbove = world.func_147437_c(pos.field_72311_b, pos.field_72312_c + 2, pos.field_72309_d);
/*      */     
/*  599 */     if (TFC_Core.isFreshWater(blockDirectlyAbove))
/*      */     {
/*  601 */       if (airTwoAbove) {
/*      */         
/*  603 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.Flora.Cat Tails.name"));
/*      */       }
/*      */       else {
/*      */         
/*  607 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.Flora.Pond Weed.name"));
/*      */       } 
/*      */     }
/*      */     
/*  611 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> anvilBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  617 */     NBTTagCompound tag = accessor.getNBTData();
/*      */     
/*  619 */     int tier = tag.func_74762_e("Tier");
/*  620 */     currenttip.add(TFC_Core.translate("gui.tier") + " : " + tier);
/*      */     
/*  622 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  623 */     ItemStack flux = storage[6];
/*      */     
/*  625 */     if (flux != null && flux.func_77973_b() == TFCItems.powder && flux.func_77960_j() == 0 && flux.field_77994_a > 0) {
/*  626 */       currenttip.add(TFC_Core.translate("item.Powder.Flux.name") + " : " + flux.field_77994_a);
/*      */     }
/*  628 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> barrelBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  633 */     TEBarrel te = (TEBarrel)accessor.getTileEntity();
/*  634 */     NBTTagCompound tag = accessor.getNBTData();
/*  635 */     ItemStack[] storage = getStorage(tag, (TileEntity)te);
/*  636 */     ItemStack inStack = storage[0];
/*      */     
/*  638 */     Boolean sealed = Boolean.valueOf(te.getSealed());
/*  639 */     int sealTime = accessor.getNBTInteger(tag, "SealTime");
/*  640 */     FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluidNBT"));
/*  641 */     BarrelRecipe recipe = BarrelManager.getInstance().findMatchingRecipe(inStack, fluid, sealed.booleanValue(), te.getTechLevel());
/*      */     
/*  643 */     if (sealed.booleanValue() && fluid != null && fluid.getFluid() == TFCFluids.MILKCURDLED && (inStack == null || (inStack
/*  644 */       .func_77973_b() instanceof IFood && ((IFood)inStack.func_77973_b()).getFoodGroup() != EnumFoodGroup.Dairy && ((IFood)inStack.func_77973_b()).isEdible(inStack) && Food.getWeight(inStack) <= 20.0F))) {
/*  645 */       recipe = (new BarrelRecipe(null, new FluidStack(TFCFluids.MILKCURDLED, 10000), ItemFoodTFC.createTag(new ItemStack(TFCItems.cheese, 1), 160.0F), null)).setMinTechLevel(0);
/*      */     }
/*      */     
/*  648 */     if (fluid != null)
/*      */     {
/*  650 */       currenttip.add(fluid.amount + "/" + te.getMaxLiquid() + " mB");
/*      */     }
/*      */ 
/*      */     
/*  654 */     if (sealed.booleanValue() && sealTime != 0)
/*      */     {
/*  656 */       currenttip.add(TFC_Core.translate("gui.Barrel.SealedOn") + " : " + TFC_Time.getDateStringFromHours(sealTime));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  661 */     BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(te, inStack, fluid, sealed.booleanValue());
/*      */ 
/*      */     
/*  664 */     if (recipe != null) {
/*      */       
/*  666 */       if (!(recipe instanceof com.bioxx.tfc.api.Crafting.BarrelBriningRecipe))
/*      */       {
/*  668 */         currenttip.add(TFC_Core.translate("gui.Output") + " : " + recipe.getRecipeName());
/*      */       }
/*  670 */       else if (sealed.booleanValue() && fluid != null && fluid.getFluid() == TFCFluids.BRINE)
/*      */       {
/*  672 */         if (inStack != null && inStack.func_77973_b() instanceof IFood && (((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) && !Food.isBrined(inStack))
/*      */         {
/*  674 */           currenttip.add(TFC_Core.translate("gui.barrel.brining"));
/*      */         }
/*      */       }
/*      */     
/*  678 */     } else if (sealed.booleanValue() && fluid != null && inStack != null && inStack.func_77973_b() instanceof IFood && fluid.getFluid() == TFCFluids.VINEGAR) {
/*      */       
/*  680 */       if (!Food.isPickled(inStack) && Food.getWeight(inStack) / fluid.amount <= 160.0F / te.getMaxLiquid()) {
/*      */         
/*  682 */         if ((((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) && Food.isBrined(inStack))
/*      */         {
/*  684 */           currenttip.add(TFC_Core.translate("gui.barrel.pickling"));
/*      */         }
/*      */       }
/*  687 */       else if (Food.isPickled(inStack) && Food.getWeight(inStack) / fluid.amount <= 160.0F / te.getMaxLiquid() * 2.0F) {
/*      */         
/*  689 */         currenttip.add(TFC_Core.translate("gui.barrel.preserving"));
/*      */       } 
/*  691 */     } else if (preservative != null) {
/*  692 */       currenttip.add(TFC_Core.translate(preservative.getPreservingString()));
/*      */     } 
/*      */     
/*  695 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> berryBushBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  700 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockBerryBush.metaNames[accessor.getMetadata()]);
/*  701 */     currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]);
/*  702 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> blastFurnaceBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  707 */     TEBlastFurnace te = (TEBlastFurnace)accessor.getTileEntity();
/*  708 */     NBTTagCompound tag = accessor.getNBTData();
/*      */     
/*  710 */     int charcoalCount = tag.func_74762_e("charcoalCount");
/*  711 */     int oreCount = tag.func_74771_c("oreCount");
/*  712 */     int stackSize = tag.func_74762_e("maxValidStackSize");
/*  713 */     float temperature = 0.0F;
/*      */     
/*  715 */     ItemStack[] storage = getStorage(tag, (TileEntity)te);
/*  716 */     ItemStack oreStack = storage[0];
/*      */     
/*  718 */     HeatRegistry manager = HeatRegistry.getInstance();
/*      */     
/*  720 */     if (oreStack != null) {
/*      */       
/*  722 */       HeatIndex index = manager.findMatchingIndex(oreStack);
/*  723 */       if (index != null)
/*      */       {
/*  725 */         temperature = TFC_ItemHeat.getTemp(oreStack);
/*      */       }
/*      */     } 
/*  728 */     String temp = TFC_ItemHeat.getHeatColor(temperature, te.maxFireTempScale);
/*      */     
/*  730 */     currenttip.add(TFC_Core.translate("gui.Bloomery.Charcoal") + " : " + charcoalCount + "/" + (stackSize * 4));
/*  731 */     currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount + "/" + (stackSize * 4));
/*      */     
/*  733 */     if (te.storage[1] != null) {
/*  734 */       currenttip.add(TFC_Core.translate("gui.plans.tuyere") + EnumChatFormatting.GREEN.toString() + " ✔");
/*      */     } else {
/*  736 */       currenttip.add(TFC_Core.translate("gui.plans.tuyere") + EnumChatFormatting.RED.toString() + " ✘");
/*      */     } 
/*  738 */     if (temperature > 0.0F)
/*      */     {
/*  740 */       currenttip.add(temp);
/*      */     }
/*      */     
/*  743 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> bloomBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  748 */     NBTTagCompound tag = accessor.getNBTData();
/*  749 */     int size = tag.func_74762_e("size");
/*      */     
/*  751 */     currenttip.add(TFC_Core.translate("gui.units") + " : " + size);
/*  752 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> bloomeryBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  757 */     NBTTagCompound tag = accessor.getNBTData();
/*  758 */     boolean isLit = tag.func_74767_n("isLit");
/*  759 */     int charcoalCount = tag.func_74762_e("charcoalCount");
/*  760 */     int oreCount = tag.func_74762_e("oreCount");
/*      */     
/*  762 */     currenttip.add(TFC_Core.translate("gui.Blast.Charcoal") + " : " + charcoalCount);
/*      */     
/*  764 */     currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount);
/*      */     
/*  766 */     if (isLit) {
/*      */       
/*  768 */       long hours = tag.func_74763_f("fuelTimeLeft") / 1000L - TFC_Time.getTotalHours();
/*      */       
/*  770 */       if (hours > 0L) {
/*      */         
/*  772 */         float percent = Helper.roundNumber(Math.min(100.0F - (float)hours / TFCOptions.bloomeryBurnTime * 100.0F, 100.0F), 10.0F);
/*  773 */         currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
/*      */       } 
/*      */     } 
/*      */     
/*  777 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> cropBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  782 */     NBTTagCompound tag = accessor.getNBTData();
/*  783 */     float growth = tag.func_74760_g("growth");
/*  784 */     int cropId = tag.func_74762_e("cropId");
/*      */     
/*  786 */     CropIndex crop = CropManager.getInstance().getCropFromId(cropId);
/*  787 */     int percentGrowth = (int)Math.min(growth / crop.numGrowthStages * 100.0F, 100.0F);
/*      */     
/*  789 */     if (percentGrowth < 100) {
/*  790 */       currenttip.add(TFC_Core.translate("gui.growth") + " : " + percentGrowth + "%");
/*      */     } else {
/*  792 */       currenttip.add(TFC_Core.translate("gui.growth") + " : " + TFC_Core.translate("gui.mature"));
/*      */     } 
/*  794 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> farmlandBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  799 */     SkillStats.SkillRank rank = TFC_Core.getSkillStats(accessor.getPlayer()).getSkillRank("skill.agriculture");
/*  800 */     if (rank == SkillStats.SkillRank.Expert || rank == SkillStats.SkillRank.Master) {
/*      */       
/*  802 */       TEFarmland te = (TEFarmland)accessor.getTileEntity();
/*  803 */       NBTTagCompound tag = accessor.getNBTData();
/*      */       
/*  805 */       int[] nutrients = tag.func_74759_k("nutrients");
/*  806 */       int soilMax = te.getSoilMax();
/*      */       
/*  808 */       for (int i = 0; i < nutrients.length; i++) {
/*      */         
/*  810 */         int percent = Math.max(nutrients[i] * 100 / soilMax, 0);
/*      */         
/*  812 */         if (i == 0) {
/*  813 */           currenttip.add(EnumChatFormatting.RED + TFC_Core.translate("gui.Nutrient.A") + " : " + percent + "%");
/*  814 */         } else if (i == 1) {
/*  815 */           currenttip.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.Nutrient.B") + " : " + percent + "%");
/*  816 */         } else if (i == 2) {
/*  817 */           currenttip.add(EnumChatFormatting.YELLOW + TFC_Core.translate("gui.Nutrient.C") + " : " + percent + "%");
/*  818 */         } else if (i == 3 && percent != 0) {
/*  819 */           currenttip.add(EnumChatFormatting.WHITE + TFC_Core.translate("item.Fertilizer.name") + " : " + percent + "%");
/*      */         } 
/*      */       } 
/*      */     } 
/*  823 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> firepitBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  828 */     NBTTagCompound tag = accessor.getNBTData();
/*  829 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  831 */     if (storage != null) {
/*      */       
/*  833 */       int fuelCount = 0;
/*  834 */       for (ItemStack is : storage) {
/*      */         
/*  836 */         if (is != null && is.func_77973_b() != null && (is.func_77973_b() == TFCItems.logs || is.func_77973_b() == Item.func_150898_a(TFCBlocks.peat))) {
/*  837 */           fuelCount++;
/*      */         }
/*      */       } 
/*  840 */       if (fuelCount > 0) {
/*  841 */         currenttip.add(TFC_Core.translate("gui.fuel") + " : " + fuelCount + "/4");
/*      */       }
/*      */     } 
/*  844 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> forgeBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  849 */     NBTTagCompound tag = accessor.getNBTData();
/*  850 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  852 */     if (storage != null) {
/*      */       
/*  854 */       int fuelCount = 0;
/*  855 */       boolean hasMold = false;
/*      */       
/*  857 */       for (int i = 5; i <= 9; i++) {
/*      */         
/*  859 */         if (storage[i] != null && storage[i].func_77973_b() != null && storage[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemCoal) {
/*  860 */           fuelCount++;
/*      */         }
/*      */       } 
/*  863 */       if (fuelCount > 0) {
/*  864 */         currenttip.add(TFC_Core.translate("gui.fuel") + " : " + fuelCount + "/5");
/*      */       }
/*  866 */       for (int j = 10; j <= 13; j++) {
/*      */         
/*  868 */         if (storage[j] != null && storage[j].func_77973_b() == TFCItems.ceramicMold && (storage[j]).field_77994_a > 0)
/*  869 */           hasMold = true; 
/*      */       } 
/*  871 */       if (hasMold) {
/*  872 */         currenttip.add(TFC_Core.translate("item.Mold.Ceramic Mold.name") + EnumChatFormatting.GREEN.toString() + " ✔");
/*      */       } else {
/*  874 */         currenttip.add(TFC_Core.translate("item.Mold.Ceramic Mold.name") + EnumChatFormatting.RED.toString() + " ✘");
/*      */       } 
/*      */     } 
/*  877 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> fruitLeavesBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  882 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata()));
/*  883 */     if (index != null)
/*  884 */       currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]); 
/*  885 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> logPileBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  890 */     NBTTagCompound tag = accessor.getNBTData();
/*  891 */     Boolean isOnFire = Boolean.valueOf(tag.func_74767_n("isOnFire"));
/*      */     
/*  893 */     if (isOnFire.booleanValue()) {
/*      */       
/*  895 */       int fireTimer = tag.func_74762_e("fireTimer");
/*  896 */       int hours = (int)(TFCOptions.charcoalPitBurnTime - (float)(TFC_Time.getTotalHours() - fireTimer));
/*  897 */       currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F - hours / TFCOptions.charcoalPitBurnTime * 100.0F, 10.0F) + "%)");
/*      */     }
/*      */     else {
/*      */       
/*  901 */       ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  902 */       boolean[] counted = { false, false, false, false };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  909 */       for (int j = 0; j < storage.length; j++) {
/*      */         
/*  911 */         if (storage[j] != null && !counted[j]) {
/*      */           
/*  913 */           String log = storage[j].func_82833_r() + " : ";
/*  914 */           int count = (storage[j]).field_77994_a;
/*  915 */           for (int k = j + 1; k < storage.length; k++) {
/*      */             
/*  917 */             if (storage[k] != null && storage[j].func_77969_a(storage[k])) {
/*      */               
/*  919 */               count += (storage[k]).field_77994_a;
/*  920 */               counted[k] = true;
/*      */             } 
/*      */           } 
/*  923 */           currenttip.add(log + count);
/*  924 */           counted[j] = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  929 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> loomBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  934 */     NBTTagCompound tag = accessor.getNBTData();
/*  935 */     boolean finished = tag.func_74767_n("finished");
/*  936 */     int wovenStrings = tag.func_74762_e("cloth");
/*  937 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  939 */     if (!finished && storage[0] != null) {
/*      */       
/*  941 */       LoomRecipe recipe = LoomManager.getInstance().findPotentialRecipes(storage[0]);
/*  942 */       int maxStrings = recipe.getReqSize();
/*      */       
/*  944 */       if ((storage[0]).field_77994_a < maxStrings) {
/*      */         
/*  946 */         String name = storage[0].func_82833_r() + " : ";
/*  947 */         currenttip.add(name + (storage[0]).field_77994_a + "/" + maxStrings);
/*      */       }
/*      */       else {
/*      */         
/*  951 */         String name = recipe.getOutItemStack().func_82833_r() + " : ";
/*  952 */         int percent = (int)(100.0D * wovenStrings / maxStrings);
/*  953 */         currenttip.add(TFC_Core.translate("gui.weaving") + " " + name + percent + "%");
/*      */       } 
/*      */     } 
/*      */     
/*  957 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> metalTrapDoorBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  962 */     NBTTagCompound tag = accessor.getNBTData();
/*  963 */     ItemStack sheetStack = ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
/*      */     
/*  965 */     String metalType = BlockMetalTrapDoor.metalNames[sheetStack.func_77960_j() & 0x1F];
/*  966 */     currenttip.add(TFC_Core.translate("gui.metal." + metalType.replaceAll("\\s", "")));
/*  967 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> nestBoxBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  972 */     NBTTagCompound tag = accessor.getNBTData();
/*  973 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  974 */     int eggCount = 0, fertEggCount = 0;
/*      */     
/*  976 */     for (ItemStack is : storage) {
/*      */       
/*  978 */       if (is != null && is.func_77973_b() == TFCItems.egg)
/*      */       {
/*  980 */         if (is.func_77942_o() && is.func_77978_p().func_74764_b("Fertilized")) {
/*  981 */           fertEggCount++;
/*      */         } else {
/*  983 */           eggCount++;
/*      */         } 
/*      */       }
/*      */     } 
/*  987 */     if (eggCount > 0)
/*  988 */       currenttip.add(TFC_Core.translate("gui.eggs") + " : " + eggCount); 
/*  989 */     if (fertEggCount > 0) {
/*  990 */       currenttip.add(TFC_Core.translate("gui.fertEggs") + " : " + fertEggCount);
/*      */     }
/*  992 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> oilLampBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  997 */     NBTTagCompound tag = accessor.getNBTData();
/*  998 */     if (tag.func_74764_b("Fuel")) {
/*      */       
/* 1000 */       FluidStack fuel = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("Fuel"));
/* 1001 */       int hours = fuel.amount * TFCOptions.oilLampFuelMult / 8;
/* 1002 */       if (fuel.getFluid() == TFCFluids.OLIVEOIL) {
/* 1003 */         currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(hours / 250.0F * TFCOptions.oilLampFuelMult * 100.0F, 10.0F) + "%)");
/* 1004 */       } else if (fuel.getFluid() == TFCFluids.LAVA) {
/* 1005 */         currenttip.add(TFC_Core.translate("gui.infinite") + " " + TFC_Core.translate("gui.hoursRemaining"));
/*      */       } 
/* 1007 */     }  return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> oreBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1012 */     int meta = accessor.getMetadata();
/*      */     
/* 1014 */     if (accessor.getBlock() == TFCBlocks.ore) {
/*      */       
/* 1016 */       switch (meta) {
/*      */         
/*      */         case 0:
/*      */         case 9:
/*      */         case 13:
/* 1021 */           currenttip.add(TFC_Core.translate("gui.metal.Copper"));
/*      */           break;
/*      */         case 1:
/* 1024 */           currenttip.add(TFC_Core.translate("gui.metal.Gold"));
/*      */           break;
/*      */         case 2:
/* 1027 */           currenttip.add(TFC_Core.translate("gui.metal.Platinum") + " - " + TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */         case 3:
/*      */         case 10:
/*      */         case 11:
/* 1032 */           currenttip.add(TFC_Core.translate("gui.metal.Iron"));
/*      */           break;
/*      */         case 4:
/* 1035 */           currenttip.add(TFC_Core.translate("gui.metal.Silver"));
/*      */           break;
/*      */         case 5:
/* 1038 */           currenttip.add(TFC_Core.translate("gui.metal.Tin"));
/*      */           break;
/*      */         case 6:
/* 1041 */           currenttip.add(TFC_Core.translate("gui.metal.Lead") + " - " + TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */         case 7:
/* 1044 */           currenttip.add(TFC_Core.translate("gui.metal.Bismuth"));
/*      */           break;
/*      */         case 8:
/* 1047 */           currenttip.add(TFC_Core.translate("gui.metal.Nickel"));
/*      */           break;
/*      */         case 12:
/* 1050 */           currenttip.add(TFC_Core.translate("gui.metal.Zinc"));
/*      */           break;
/*      */         case 14:
/*      */         case 15:
/* 1054 */           currenttip.add(TFC_Core.translate("item.coal.coal.name"));
/* 1055 */           return currenttip;
/*      */       } 
/*      */       
/* 1058 */       if (config.getConfig("tfc.oreQuality"))
/*      */       {
/* 1060 */         TEOre te = (TEOre)accessor.getTileEntity();
/*      */         
/* 1062 */         int ore = getOreGrade(te, meta);
/*      */         
/* 1064 */         int units = (ore < 14) ? TFCOptions.normalOreUnits : ((ore < 49) ? TFCOptions.richOreUnits : ((ore < 63) ? TFCOptions.poorOreUnits : 0));
/* 1065 */         if (units > 0) {
/* 1066 */           currenttip.add(TFC_Core.translate("gui.units") + " : " + units);
/*      */         }
/*      */       }
/*      */     
/* 1070 */     } else if (accessor.getBlock() == TFCBlocks.ore2) {
/*      */       
/* 1072 */       switch (meta) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 6:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 14:
/* 1082 */           currenttip.add(TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */         case 5:
/* 1085 */           currenttip.add(TFC_Core.translate("item.Diamond.Normal.name"));
/*      */           break;
/*      */         case 11:
/*      */         case 12:
/* 1089 */           currenttip.add(TFC_Core.translate("item.redstone.name"));
/*      */           break;
/*      */         case 15:
/* 1092 */           currenttip.add(TFC_Core.translate("item.Fertilizer.name"));
/*      */           break;
/*      */       } 
/*      */     
/* 1096 */     } else if (accessor.getBlock() == TFCBlocks.ore3) {
/*      */       
/* 1098 */       switch (meta) {
/*      */         
/*      */         case 0:
/* 1101 */           currenttip.add(TFC_Core.translate("item.Powder.Flux.name"));
/*      */           break;
/*      */         case 1:
/* 1104 */           currenttip.add(TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/* 1109 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> potteryBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1114 */     NBTTagCompound tag = accessor.getNBTData();
/* 1115 */     long burnStart = tag.func_74763_f("burnStart");
/* 1116 */     int wood = tag.func_74762_e("wood");
/* 1117 */     int straw = tag.func_74762_e("straw");
/*      */     
/* 1119 */     if (straw > 0 && straw < 8) {
/* 1120 */       currenttip.add(TFC_Core.translate("item.Straw.name") + " : " + straw + "/8");
/* 1121 */     } else if (wood > 0 && wood < 8) {
/* 1122 */       currenttip.add(TFC_Core.translate("gui.logs") + " : " + wood + "/8");
/* 1123 */     } else if (burnStart > 0L) {
/*      */       
/* 1125 */       int hours = (int)(TFCOptions.pitKilnBurnTime - (float)(TFC_Time.getTotalHours() - burnStart / 1000L));
/* 1126 */       currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F - hours / TFCOptions.pitKilnBurnTime * 100.0F, 10.0F) + "%)");
/*      */     } 
/*      */     
/* 1129 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> saplingBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1134 */     NBTTagCompound tag = accessor.getNBTData();
/* 1135 */     boolean enoughSpace = tag.func_74767_n("enoughSpace");
/* 1136 */     long growTime = tag.func_74763_f("growTime");
/* 1137 */     int days = (int)((growTime - TFC_Time.getTotalTicks()) / 24000L);
/* 1138 */     if (days > 0) {
/*      */       
/* 1140 */       currenttip.add(days + " " + TFC_Core.translate("gui.daysRemaining"));
/*      */     }
/* 1142 */     else if (!enoughSpace) {
/*      */       
/* 1144 */       currenttip.add(TFC_Core.translate("gui.enoughSpace"));
/*      */     } 
/*      */     
/* 1147 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> sluiceBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1152 */     NBTTagCompound tag = accessor.getNBTData();
/* 1153 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/* 1154 */     int soilAmount = tag.func_74762_e("soilAmount");
/*      */     
/* 1156 */     if (soilAmount == -1) {
/* 1157 */       currenttip.add(TFC_Core.translate("gui.Sluice.Overworked"));
/* 1158 */     } else if (soilAmount > 0) {
/*      */       
/* 1160 */       currenttip.add(TFC_Core.translate("gui.Sluice.Soil") + " : " + soilAmount + "/50");
/*      */     } 
/*      */     
/* 1163 */     int gemCount = 0, oreCount = 0;
/* 1164 */     for (ItemStack is : storage) {
/*      */       
/* 1166 */       if (is != null)
/*      */       {
/* 1168 */         if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemGem) {
/* 1169 */           gemCount++;
/* 1170 */         } else if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) {
/* 1171 */           oreCount++;
/*      */         } 
/*      */       }
/*      */     } 
/* 1175 */     if (gemCount > 0)
/* 1176 */       currenttip.add(TFC_Core.translate("gui.gems") + " : " + gemCount); 
/* 1177 */     if (oreCount > 0) {
/* 1178 */       currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount);
/*      */     }
/* 1180 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> smokeRackBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1185 */     NBTTagCompound tag = accessor.getNBTData();
/* 1186 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/* 1188 */     for (int i = 0; i < storage.length; i++) {
/*      */       
/* 1190 */       ItemStack is = storage[i];
/* 1191 */       if (is != null) {
/*      */         
/* 1193 */         int dryHours = 4 - Food.getDried(is);
/* 1194 */         int smokeHours = 12 - Food.getSmokeCounter(is);
/*      */         
/* 1196 */         if (smokeHours < 12 && !Food.isSmoked(is)) {
/*      */           
/* 1198 */           smokeHours++;
/* 1199 */           float percent = Helper.roundNumber(100.0F - 100.0F * smokeHours / 12.0F, 10.0F);
/* 1200 */           currenttip.add(TFC_Core.translate("word.smoking") + " " + is.func_82833_r());
/* 1201 */           currenttip.add("· " + smokeHours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
/*      */         }
/* 1203 */         else if (dryHours < 4 && !Food.isDried(is)) {
/*      */           
/* 1205 */           float percent = Helper.roundNumber(100.0F - 100.0F * dryHours / 4.0F, 10.0F);
/* 1206 */           currenttip.add(TFC_Core.translate("word.drying") + " " + is.func_82833_r());
/* 1207 */           currenttip.add("· " + dryHours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
/*      */         } else {
/*      */           
/* 1210 */           currenttip.add(is.func_82833_r());
/*      */         } 
/*      */       } 
/*      */     } 
/* 1214 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> spawnMeterBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1219 */     NBTTagCompound tag = accessor.getNBTData();
/* 1220 */     int hours = tag.func_74762_e("protectionHours");
/*      */     
/* 1222 */     if (hours > 0)
/*      */     {
/* 1224 */       currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining"));
/*      */     }
/*      */     
/* 1227 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> soilBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1232 */     Block b = accessor.getBlock();
/* 1233 */     int dam = itemStack.func_77960_j();
/* 1234 */     if (b == TFCBlocks.dirt2 || b == TFCBlocks.sand2 || TFC_Core.isGrassType2(b) || b == TFCBlocks.gravel2)
/*      */     {
/* 1236 */       dam += 16;
/*      */     }
/*      */     
/* 1239 */     if (dam < Global.STONE_ALL.length) {
/* 1240 */       currenttip.add(Global.STONE_ALL[dam]);
/*      */     } else {
/* 1242 */       currenttip.add(EnumChatFormatting.DARK_RED + "Unknown");
/*      */     } 
/* 1244 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> torchBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1249 */     if (accessor.getMetadata() < 8 && TFCOptions.torchBurnTime != 0) {
/*      */       
/* 1251 */       NBTTagCompound tag = accessor.getNBTData();
/* 1252 */       long hours = TFCOptions.torchBurnTime - TFC_Time.getTotalHours() - tag.func_74762_e("hourPlaced");
/*      */       
/* 1254 */       if (hours > 0L)
/* 1255 */         currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F * (float)hours / TFCOptions.torchBurnTime, 10.0F) + "%)"); 
/*      */     } 
/* 1257 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ItemStack[] getStorage(NBTTagCompound tag, TileEntity te) {
/* 1263 */     if (te instanceof IInventory) {
/*      */       
/* 1265 */       IInventory inv = (IInventory)te;
/* 1266 */       NBTTagList tagList = tag.func_150295_c("Items", 10);
/* 1267 */       ItemStack[] storage = new ItemStack[inv.func_70302_i_()];
/* 1268 */       for (int i = 0; i < tagList.func_74745_c(); i++) {
/*      */         
/* 1270 */         NBTTagCompound itemTag = tagList.func_150305_b(i);
/* 1271 */         byte slot = itemTag.func_74771_c("Slot");
/* 1272 */         if (slot >= 0 && slot < storage.length) {
/* 1273 */           storage[slot] = ItemStack.func_77949_a(itemTag);
/*      */         }
/*      */       } 
/* 1276 */       return storage;
/*      */     } 
/*      */     
/* 1279 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getOreGrade(TEOre te, int ore) {
/* 1284 */     if (te != null) {
/*      */       
/* 1286 */       int grade = te.extraData & 0x7;
/* 1287 */       if (grade == 1) {
/* 1288 */         ore += 35;
/* 1289 */       } else if (grade == 2) {
/* 1290 */         ore += 49;
/*      */       } 
/* 1292 */     }  return ore;
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\WAILA\WAILAData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */