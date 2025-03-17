/*     */ package com.bioxx.tfc;
/*     */ import com.bioxx.tfc.Core.ColorizerFoliageTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Handlers.Client.BlockRenderHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.FMLClientEventHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.GuiHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.KeyBindingHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.PlayerRenderHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.RenderOverlayHandler;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderCrucible;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderSupportBeam;
/*     */ import com.bioxx.tfc.Render.Models.ModelBear;
/*     */ import com.bioxx.tfc.Render.Models.ModelWolfTFC;
/*     */ import com.bioxx.tfc.Render.RenderDeer;
/*     */ import com.bioxx.tfc.Render.RenderSkeletonTFC;
/*     */ import com.bioxx.tfc.Render.TESR.TESRQuern;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.Util.KeyBindings;
/*     */ import cpw.mods.fml.client.registry.ClientRegistry;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelSlime;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderMinecart;
/*     */ import net.minecraft.client.renderer.entity.RenderSpider;
/*     */ import net.minecraft.client.renderer.entity.RenderZombie;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.resources.IReloadableResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.GuiIngameForge;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class ClientProxy extends CommonProxy {
/*     */   public void registerFluidIcons() {
/*  48 */     TFCFluids.LAVA.setIcons(TFCBlocks.lava.func_149691_a(0, 0), TFCBlocks.lava.func_149691_a(2, 0));
/*  49 */     TFCFluids.SALTWATER.setIcons(TFCBlocks.saltWater.func_149691_a(0, 0), TFCBlocks.saltWater.func_149691_a(2, 0));
/*  50 */     TFCFluids.FRESHWATER.setIcons(TFCBlocks.freshWater.func_149691_a(0, 0), TFCBlocks.freshWater.func_149691_a(2, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void registerRenderInformation() {
/*  57 */     (Minecraft.func_71410_x()).field_71460_t = (EntityRenderer)new EntityRendererTFC(Minecraft.func_71410_x(), Minecraft.func_71410_x().func_110442_L());
/*  58 */     IReloadableResourceManager iRRM = (IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L();
/*  59 */     iRRM.func_110542_a((IResourceManagerReloadListener)new GrassColorReloadListener());
/*  60 */     iRRM.func_110542_a((IResourceManagerReloadListener)new FoliageColorReloadListener());
/*  61 */     iRRM.func_110542_a((IResourceManagerReloadListener)(Minecraft.func_71410_x()).field_71460_t);
/*     */     
/*  63 */     RenderingRegistry.registerEntityRenderingHandler(EntityJavelin.class, (Render)new RenderTerraJavelin());
/*  64 */     RenderingRegistry.registerEntityRenderingHandler(EntitySquidTFC.class, (Render)new RenderSquidTFC((ModelBase)new ModelSquidTFC(), 0.7F));
/*     */ 
/*     */     
/*  67 */     RenderingRegistry.registerEntityRenderingHandler(EntityWolfTFC.class, (Render)new RenderWolfTFC((ModelBase)new ModelWolfTFC(), (ModelBase)new ModelWolfTFC(), 0.5F));
/*  68 */     RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, (Render)new RenderBear((ModelBase)new ModelBear(), 0.9F));
/*  69 */     RenderingRegistry.registerEntityRenderingHandler(EntityPheasantTFC.class, (Render)new RenderPheasantTFC((ModelBase)new ModelPheasant(), 0.3F));
/*  70 */     RenderingRegistry.registerEntityRenderingHandler(EntityChickenTFC.class, (Render)new RenderChickenTFC((ModelBase)new ModelChickenTFC(), 0.3F));
/*     */     
/*  72 */     RenderingRegistry.registerEntityRenderingHandler(EntityDeer.class, (Render)new RenderDeer((ModelBase)new ModelDeer(), 0.9F));
/*  73 */     RenderingRegistry.registerEntityRenderingHandler(EntityHorseTFC.class, (Render)new RenderHorseTFC((ModelBase)new ModelHorseTFC(), 0.9F));
/*  74 */     RenderingRegistry.registerEntityRenderingHandler(EntityCustomMinecart.class, (Render)new RenderMinecart());
/*  75 */     RenderingRegistry.registerEntityRenderingHandler(EntityStand.class, (Render)new RenderEntityStand());
/*  76 */     RenderingRegistry.registerEntityRenderingHandler(EntityFishTFC.class, (Render)new RenderFishTFC((ModelBase)new ModelBass(), 0.7F));
/*  77 */     RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonTFC.class, (Render)new RenderSkeletonTFC());
/*  78 */     RenderingRegistry.registerEntityRenderingHandler(EntityZombieTFC.class, (Render)new RenderZombie());
/*  79 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpiderTFC.class, (Render)new RenderSpider());
/*  80 */     RenderingRegistry.registerEntityRenderingHandler(EntitySlimeTFC.class, (Render)new RenderSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25F));
/*  81 */     RenderingRegistry.registerEntityRenderingHandler(EntitySilverfishTFC.class, (Render)new RenderSilverfish());
/*  82 */     RenderingRegistry.registerEntityRenderingHandler(EntityGhastTFC.class, (Render)new RenderGhast());
/*  83 */     RenderingRegistry.registerEntityRenderingHandler(EntityCaveSpiderTFC.class, (Render)new RenderSpider());
/*  84 */     RenderingRegistry.registerEntityRenderingHandler(EntityBlazeTFC.class, (Render)new RenderBlaze());
/*  85 */     RenderingRegistry.registerEntityRenderingHandler(EntityEndermanTFC.class, (Render)new RenderEnderman());
/*  86 */     RenderingRegistry.registerEntityRenderingHandler(EntityPigZombieTFC.class, (Render)new RenderZombie());
/*  87 */     RenderingRegistry.registerEntityRenderingHandler(EntityIronGolemTFC.class, (Render)new RenderIronGolem());
/*     */     
/*  89 */     RenderingRegistry.registerEntityRenderingHandler(EntityProjectileTFC.class, (Render)new RenderArrow());
/*  90 */     RenderingRegistry.registerEntityRenderingHandler(EntityFishHookTFC.class, (Render)new RenderFish());
/*  91 */     RenderingRegistry.registerEntityRenderingHandler(EntityBarrel.class, (Render)new RenderBarrelEntity());
/*  92 */     RenderingRegistry.registerEntityRenderingHandler(EntityFallingBlockTFC.class, (Render)new RenderFallingBlock());
/*     */     
/*  94 */     RenderingRegistry.registerBlockHandler(TFCBlocks.chestRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderChest());
/*  95 */     RenderingRegistry.registerBlockHandler(TFCBlocks.clayGrassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/*  96 */     RenderingRegistry.registerBlockHandler(TFCBlocks.peatGrassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/*  97 */     RenderingRegistry.registerBlockHandler(TFCBlocks.sulfurRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/*  98 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodSupportRenderIdH = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSupportBeam());
/*  99 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodSupportRenderIdV = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSupportBeam());
/* 100 */     RenderingRegistry.registerBlockHandler(TFCBlocks.grassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 101 */     RenderingRegistry.registerBlockHandler(TFCBlocks.oreRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderOre());
/* 102 */     RenderingRegistry.registerBlockHandler(TFCBlocks.moltenRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 103 */     RenderingRegistry.registerBlockHandler(TFCBlocks.looseRockRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 104 */     RenderingRegistry.registerBlockHandler(TFCBlocks.firepitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 105 */     RenderingRegistry.registerBlockHandler(TFCBlocks.anvilRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderAnvil());
/* 106 */     RenderingRegistry.registerBlockHandler(TFCBlocks.bellowsRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBellows());
/* 107 */     RenderingRegistry.registerBlockHandler(TFCBlocks.forgeRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 108 */     RenderingRegistry.registerBlockHandler(TFCBlocks.sluiceRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 109 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodFruitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 110 */     RenderingRegistry.registerBlockHandler(TFCBlocks.leavesFruitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 111 */     RenderingRegistry.registerBlockHandler(TFCBlocks.stairRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 112 */     RenderingRegistry.registerBlockHandler(TFCBlocks.slabRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 113 */     RenderingRegistry.registerBlockHandler(TFCBlocks.cropRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 114 */     RenderingRegistry.registerBlockHandler(TFCBlocks.cookingPitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 115 */     RenderingRegistry.registerBlockHandler(TFCBlocks.leavesRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 116 */     RenderingRegistry.registerBlockHandler(TFCBlocks.detailedRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 117 */     RenderingRegistry.registerBlockHandler(TFCBlocks.wallRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWall());
/* 118 */     RenderingRegistry.registerBlockHandler(TFCBlocks.fenceRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFence());
/* 119 */     RenderingRegistry.registerBlockHandler(TFCBlocks.fenceGateRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFenceGate());
/* 120 */     RenderingRegistry.registerBlockHandler(TFCBlocks.toolRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderToolRack());
/*     */     
/* 122 */     RenderingRegistry.registerBlockHandler(TFCBlocks.quernRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new TESRQuern());
/* 123 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodConstructRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWoodConstruct());
/* 124 */     RenderingRegistry.registerBlockHandler(TFCBlocks.barrelRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBarrel());
/* 125 */     RenderingRegistry.registerBlockHandler(TFCBlocks.loomRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderLoom());
/* 126 */     RenderingRegistry.registerBlockHandler(TFCBlocks.standRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderStand());
/* 127 */     RenderingRegistry.registerBlockHandler(TFCBlocks.nestBoxRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderNestBox());
/* 128 */     RenderingRegistry.registerBlockHandler(TFCBlocks.potteryRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderPottery());
/* 129 */     RenderingRegistry.registerBlockHandler(TFCBlocks.tuyereRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderTuyere());
/* 130 */     RenderingRegistry.registerBlockHandler(TFCBlocks.crucibleRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderCrucible());
/* 131 */     RenderingRegistry.registerBlockHandler(TFCBlocks.waterPlantRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/*     */     
/* 133 */     RenderingRegistry.registerBlockHandler(TFCBlocks.bloomeryRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBloomery());
/* 134 */     RenderingRegistry.registerBlockHandler(TFCBlocks.metalsheetRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderMetalSheet());
/* 135 */     RenderingRegistry.registerBlockHandler(TFCBlocks.leatherRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderLeatherRack());
/* 136 */     RenderingRegistry.registerBlockHandler(TFCBlocks.grillRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderGrill());
/* 137 */     RenderingRegistry.registerBlockHandler(TFCBlocks.metalTrapDoorRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderMetalTrapDoor());
/* 138 */     RenderingRegistry.registerBlockHandler(TFCBlocks.vesselRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderVessel());
/* 139 */     RenderingRegistry.registerBlockHandler(TFCBlocks.torchRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderTorch());
/* 140 */     RenderingRegistry.registerBlockHandler(TFCBlocks.smokeRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSmoke());
/* 141 */     RenderingRegistry.registerBlockHandler(TFCBlocks.smokeRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSmokeRack());
/* 142 */     RenderingRegistry.registerBlockHandler(TFCBlocks.oilLampRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderOilLamp());
/* 143 */     RenderingRegistry.registerBlockHandler(TFCBlocks.hopperRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderHopper());
/* 144 */     RenderingRegistry.registerBlockHandler(TFCBlocks.flowerPotRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFlowerPot());
/*     */     
/* 146 */     MinecraftForge.EVENT_BUS.register(new RenderOverlayHandler());
/*     */ 
/*     */     
/* 149 */     RenderingRegistry.registerBlockHandler(TerraFirmaCraft.renderfoodprep = RenderingRegistry.getNextAvailableRenderId(), new RenderFoodPrep());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void registerBiomeEventHandler() {
/* 157 */     MinecraftForge.EVENT_BUS.register(new BiomeEventHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void setupGuiIngameForge() {
/* 164 */     GuiIngameForge.renderHealth = false;
/* 165 */     GuiIngameForge.renderArmor = false;
/* 166 */     GuiIngameForge.renderExperiance = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerTileEntities(boolean b) {
/* 173 */     super.registerTileEntities(false);
/* 174 */     ClientRegistry.registerTileEntity(TEChest.class, "chest", (TileEntitySpecialRenderer)new TESRChest());
/* 175 */     ClientRegistry.registerTileEntity(TEIngotPile.class, "ingotPile", (TileEntitySpecialRenderer)new TESRIngotPile());
/* 176 */     ClientRegistry.registerTileEntity(TEFirepit.class, "TerraFirepit", (TileEntitySpecialRenderer)new TESRFirepit());
/* 177 */     ClientRegistry.registerTileEntity(TELoom.class, "Loom", (TileEntitySpecialRenderer)new TESRLoom());
/*     */     
/* 179 */     ClientRegistry.registerTileEntity(TEPottery.class, "Pottery", (TileEntitySpecialRenderer)new TESRPottery());
/* 180 */     ClientRegistry.registerTileEntity(TEFoodPrep.class, "FoodPrep", (TileEntitySpecialRenderer)new TESRFoodPrep());
/* 181 */     ClientRegistry.registerTileEntity(TEBellows.class, "Bellows", (TileEntitySpecialRenderer)new TESRBellows());
/* 182 */     ClientRegistry.registerTileEntity(TEToolRack.class, "ToolRack", (TileEntitySpecialRenderer)new TESRToolrack());
/* 183 */     ClientRegistry.registerTileEntity(TEAnvil.class, "TerraAnvil", (TileEntitySpecialRenderer)new TESRAnvil());
/* 184 */     ClientRegistry.registerTileEntity(TEWorldItem.class, "worldItem", (TileEntitySpecialRenderer)new TESRWorldItem());
/* 185 */     ClientRegistry.registerTileEntity(TEQuern.class, "Quern", (TileEntitySpecialRenderer)new TESRQuern());
/* 186 */     ClientRegistry.registerTileEntity(TEGrill.class, "GrillTESR", (TileEntitySpecialRenderer)new TESRGrill());
/* 187 */     ClientRegistry.registerTileEntity(TESmokeRack.class, "SmokeRackTESR", (TileEntitySpecialRenderer)new TESRSmokeRack());
/* 188 */     ClientRegistry.registerTileEntity(TEHopper.class, "HopperTESR", (TileEntitySpecialRenderer)new TESRHopper());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClientLogin() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getCurrentWorld() {
/* 202 */     return (World)(Minecraft.func_71410_x()).field_71441_e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRemote() {
/* 208 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int waterColorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 214 */     return 3493173;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int grassColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
/* 220 */     int var5 = 0;
/* 221 */     int var6 = 0;
/* 222 */     int var7 = 0;
/*     */     
/* 224 */     for (int z = -1; z <= 1; z++) {
/*     */       
/* 226 */       for (int x = -1; x <= 1; x++) {
/*     */         
/* 228 */         int var10 = TFC_Climate.getGrassColor(getCurrentWorld(), i + x, j, k + z);
/* 229 */         var5 += (var10 & 0xFF0000) >> 16;
/* 230 */         var6 += (var10 & 0xFF00) >> 8;
/* 231 */         var7 += var10 & 0xFF;
/*     */       } 
/*     */     } 
/* 234 */     return (var5 / 9 & 0xFF) << 16 | (var6 / 9 & 0xFF) << 8 | var7 / 9 & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int foliageColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
/* 243 */     int[] rgb = { 0, 0, 0 };
/* 244 */     float temperature = TFC_Climate.getHeightAdjustedTempSpecificDay(getCurrentWorld(), TFC_Time.getDayOfYear(), i, j, k);
/*     */ 
/*     */     
/* 247 */     int meta = par1IBlockAccess.func_72805_g(i, j, k);
/* 248 */     if (par1IBlockAccess.func_147439_a(i, j, k) == TFCBlocks.fruitTreeLeaves) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 259 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 261 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 263 */           int var10 = TFC_Climate.getFoliageColor(getCurrentWorld(), i + m, j, k + var9);
/* 264 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 267 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 269 */     if (par1IBlockAccess.func_147439_a(i, j, k) == TFCBlocks.vine) {
/*     */       
/* 271 */       if (TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && 
/* 272 */         (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D && 
/* 273 */         TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F) {
/*     */         
/* 275 */         int color = 0;
/* 276 */         for (int n = -1; n <= 1; n++) {
/*     */           
/* 278 */           for (int var9 = -1; var9 <= 1; var9++) {
/*     */             
/* 280 */             color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + n, j, k + var9);
/* 281 */             rgb = applyColor(color, rgb);
/*     */           } 
/*     */         } 
/* 284 */         return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */       } 
/* 286 */       if (TFC_Time.getSeasonAdjustedMonth(k) >= 11 || (TFC_Time.getSeasonAdjustedMonth(k) <= 0 && 
/* 287 */         (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D && 
/* 288 */         TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F)) {
/*     */         
/* 290 */         for (int n = -1; n <= 1; n++) {
/*     */           
/* 292 */           for (int var9 = -1; var9 <= 1; var9++) {
/*     */             
/* 294 */             int color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + n, j, k + var9);
/* 295 */             rgb = applyColor(color, rgb);
/*     */           } 
/*     */         } 
/* 298 */         return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */       } 
/* 300 */       if (TFC_Time.getSeasonAdjustedMonth(k) >= 9 && 
/* 301 */         (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D && 
/* 302 */         TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F) {
/*     */         
/* 304 */         for (int n = -1; n <= 1; n++) {
/*     */           
/* 306 */           for (int var9 = -1; var9 <= 1; var9++) {
/*     */             
/* 308 */             int color = ColorizerFoliageTFC.getFoliageDead();
/* 309 */             rgb = applyColor(color, rgb);
/*     */           } 
/*     */         } 
/* 312 */         return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */       } 
/*     */ 
/*     */       
/* 316 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 318 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 320 */           int color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + m, j, k + var9);
/* 321 */           rgb = applyColor(color, rgb);
/*     */         } 
/*     */       } 
/* 324 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/*     */     
/* 327 */     if (TFC_Time.getSeasonAdjustedMonth(k) >= 6 && (EnumTree.values()[meta]).isEvergreen) {
/*     */       
/* 329 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 331 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 333 */           int var10 = TFC_Climate.getFoliageColorEvergreen(getCurrentWorld(), i + m, j, k + var9);
/* 334 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 337 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 339 */     if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && (meta == 4 || meta == 7 || meta == 5 || meta == 14)) {
/*     */       
/* 341 */       int color = 0;
/*     */       
/* 343 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 345 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 347 */           color = ColorizerFoliageTFC.getFoliageYellow();
/* 348 */           rgb = applyColor(color, rgb);
/*     */         } 
/*     */       } 
/* 351 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 353 */     if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && meta == 6) {
/*     */       
/* 355 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 357 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 359 */           int var10 = ColorizerFoliageTFC.getFoliageRed();
/* 360 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 363 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 365 */     if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && meta != 15) {
/*     */       
/* 367 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 369 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 371 */           int var10 = ColorizerFoliageTFC.getFoliageOrange();
/* 372 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 375 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 377 */     if (temperature <= 8.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && meta != 15) {
/*     */       
/* 379 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 381 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 383 */           int var10 = ColorizerFoliageTFC.getFoliageDead();
/* 384 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 387 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/*     */ 
/*     */     
/* 391 */     for (int var8 = -1; var8 <= 1; var8++) {
/*     */       
/* 393 */       for (int var9 = -1; var9 <= 1; var9++) {
/*     */         
/* 395 */         int var10 = TFC_Climate.getFoliageColor(getCurrentWorld(), i + var8, j, k + var9);
/* 396 */         rgb = applyColor(var10, rgb);
/*     */       } 
/*     */     } 
/* 399 */     return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
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
/*     */   private int[] applyColor(int c, int[] rgb) {
/* 411 */     rgb[0] = rgb[0] + ((c & 0xFF0000) >> 16);
/* 412 */     rgb[1] = rgb[1] + ((c & 0xFF00) >> 8);
/* 413 */     rgb[2] = rgb[2] + (c & 0xFF);
/* 414 */     return rgb;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArmorRenderID(String name) {
/* 420 */     return RenderingRegistry.addNewArmourRendererPrefix(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerKeys() {
/* 426 */     KeyBindings.addKeyBinding(KeyBindingHandler.keyToolMode);
/* 427 */     KeyBindings.addIsRepeating(false);
/* 428 */     KeyBindings.addKeyBinding(KeyBindingHandler.keyLockTool);
/* 429 */     KeyBindings.addIsRepeating(false);
/*     */ 
/*     */     
/* 432 */     uploadKeyBindingsToGame();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerKeyBindingHandler() {
/* 438 */     FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void uploadKeyBindingsToGame() {
/* 444 */     GameSettings settings = (Minecraft.func_71410_x()).field_71474_y;
/* 445 */     KeyBinding[] tfcKeyBindings = KeyBindings.gatherKeyBindings();
/* 446 */     KeyBinding[] allKeys = new KeyBinding[settings.field_74324_K.length + tfcKeyBindings.length];
/* 447 */     System.arraycopy(settings.field_74324_K, 0, allKeys, 0, settings.field_74324_K.length);
/* 448 */     System.arraycopy(tfcKeyBindings, 0, allKeys, settings.field_74324_K.length, tfcKeyBindings.length);
/* 449 */     settings.field_74324_K = allKeys;
/* 450 */     settings.func_74300_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerHandlers() {
/* 456 */     MinecraftForge.EVENT_BUS.register(new ChiselHighlightHandler());
/* 457 */     MinecraftForge.EVENT_BUS.register(new FarmlandHighlightHandler());
/* 458 */     MinecraftForge.EVENT_BUS.register(new PlankHighlightHandler());
/* 459 */     MinecraftForge.EVENT_BUS.register(new ArmourStandHighlightHandler());
/* 460 */     MinecraftForge.EVENT_BUS.register(new FamiliarityHighlightHandler());
/* 461 */     MinecraftForge.EVENT_BUS.register(new FogHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerPlayerRenderEventHandler() {
/* 468 */     PlayerRenderHandler pRHandler = new PlayerRenderHandler();
/* 469 */     MinecraftForge.EVENT_BUS.register(pRHandler);
/* 470 */     FMLCommonHandler.instance().bus().register(pRHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSoundHandler() {
/* 476 */     MinecraftForge.EVENT_BUS.register(new SoundHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerTickHandler() {
/* 482 */     super.registerTickHandler();
/* 483 */     FMLCommonHandler.instance().bus().register(new ClientTickHandler());
/* 484 */     FMLCommonHandler.instance().bus().register(new FMLClientEventHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerGuiHandler() {
/* 490 */     NetworkRegistry.INSTANCE.registerGuiHandler(TerraFirmaCraft.instance, (IGuiHandler)new GuiHandler());
/*     */     
/* 492 */     MinecraftForge.EVENT_BUS.register(new GuiHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrentLanguage() {
/* 498 */     return Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGraphicsLevel() {
/* 504 */     Minecraft.func_71410_x();
/* 505 */     return Minecraft.func_71375_t();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void hideNEIItems() {
/* 511 */     if (Loader.isModLoaded("NotEnoughItems")) NEIIntegration.hideNEIItems(); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */