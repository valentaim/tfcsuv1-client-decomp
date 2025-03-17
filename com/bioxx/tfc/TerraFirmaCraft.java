/*     */ package com.bioxx.tfc;
/*     */ import com.bioxx.tfc.Commands.CommandTime;
/*     */ import com.bioxx.tfc.Commands.DebugModeCommand;
/*     */ import com.bioxx.tfc.Commands.GetBioTempCommand;
/*     */ import com.bioxx.tfc.Commands.GetBodyTemp;
/*     */ import com.bioxx.tfc.Commands.GetRocksCommand;
/*     */ import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
/*     */ import com.bioxx.tfc.Core.ItemHeat;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Food.TFCPotion;
/*     */ import com.bioxx.tfc.Handlers.AnvilCraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.ChunkEventHandler;
/*     */ import com.bioxx.tfc.Handlers.CraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.EntitySpawnHandler;
/*     */ import com.bioxx.tfc.Handlers.FoodCraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.Network.PacketPipeline;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenFissure;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenForests;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenLooseRocks;
/*     */ import com.bioxx.tfc.WorldGen.TFCProvider;
/*     */ import com.bioxx.tfc.WorldGen.TFCWorldType;
/*     */ import com.bioxx.tfc.api.SkillsManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.client.event.ConfigChangedEvent;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.IFuelHandler;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLServerStartingEvent;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.ForgeModContainer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ @Mod(modid = "terrafirmacraft", name = "TerraFirmaCraft", version = "0.79.29", dependencies = "required-after:tfc_coremod", guiFactory = "com.bioxx.tfc.Core.Config.TFC_GuiFactory")
/*     */ public class TerraFirmaCraft {
/*  46 */   public static final Logger LOG = LogManager.getLogger("TerraFirmaCraft");
/*     */ 
/*     */   
/*     */   @Instance("terrafirmacraft")
/*     */   public static TerraFirmaCraft instance;
/*     */   
/*     */   @SidedProxy(clientSide = "com.bioxx.tfc.ClientProxy", serverSide = "com.bioxx.tfc.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   
/*  55 */   public static final PacketPipeline PACKET_PIPELINE = new PacketPipeline();
/*     */ 
/*     */ 
/*     */   
/*     */   public static int renderfoodprep;
/*     */ 
/*     */   
/*     */   public boolean ShipsExist = false;
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/*  68 */     TFC_ConfigFiles.preInit(event.getModConfigurationDirectory());
/*  69 */     TFC_ConfigFiles.reloadGeneral();
/*     */ 
/*     */     
/*  72 */     proxy.registerTickHandler();
/*     */     
/*  74 */     proxy.registerFluids();
/*     */     
/*  76 */     BlockSetup.loadBlocks();
/*  77 */     BlockSetup.registerBlocks();
/*  78 */     BlockSetup.setupFire();
/*     */ 
/*     */ 
/*     */     
/*  82 */     proxy.registerKeys();
/*     */     
/*  84 */     proxy.registerKeyBindingHandler();
/*     */     
/*  86 */     proxy.registerHandlers();
/*     */     
/*  88 */     proxy.registerTileEntities(true);
/*     */     
/*  90 */     proxy.registerSoundHandler();
/*     */     
/*  92 */     proxy.registerPlayerRenderEventHandler();
/*     */     
/*  94 */     SkillsManager.instance.registerSkill("skill.gensmith", 250);
/*  95 */     SkillsManager.instance.registerSkill("skill.toolsmith", 100);
/*  96 */     SkillsManager.instance.registerSkill("skill.armorsmith", 100);
/*  97 */     SkillsManager.instance.registerSkill("skill.weaponsmith", 100);
/*  98 */     SkillsManager.instance.registerSkill("skill.agriculture", 300);
/*  99 */     SkillsManager.instance.registerSkill("skill.cooking", 200);
/* 100 */     SkillsManager.instance.registerSkill("skill.prospecting", 1500);
/* 101 */     SkillsManager.instance.registerSkill("skill.butchering", 100);
/*     */ 
/*     */     
/* 104 */     ItemSetup.setup();
/*     */ 
/*     */     
/* 107 */     proxy.registerGuiHandler();
/*     */ 
/*     */ 
/*     */     
/* 111 */     GameRegistry.registerWorldGenerator((IWorldGenerator)(new WorldGenFissure(TFCBlocks.lava, 2, true, TFCOptions.lavaFissureRarity)).setUnderground(true, 20).setSeed(1), 0);
/* 112 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenFissure(TFCBlocks.freshWaterStationary, 2, false, TFCOptions.waterFissureRarity), 0);
/*     */     
/* 114 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenFissureCluster(), 1);
/* 115 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenOre(), 2);
/* 116 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenCaveDecor(), 3);
/* 117 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenForests(), 4);
/* 118 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenLooseRocks(), 5);
/* 119 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenSoilPits(), 6);
/* 120 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenLargeRock(), 7);
/* 121 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenPlants(), 8);
/*     */     
/* 123 */     WorldType.field_77137_b = (WorldType)new TFCWorldType(0, "TFCDefault");
/* 124 */     WorldType.field_77138_c = (WorldType)new TFCWorldType(1, "TFCFlat");
/* 125 */     WorldType.field_77135_d = (WorldType)new TFCWorldType(2, "TFCLargeBiomes");
/* 126 */     WorldType.field_151360_e = (WorldType)new TFCWorldType(3, "TFCAmplified");
/*     */     
/* 128 */     DimensionManager.unregisterDimension(-1);
/* 129 */     DimensionManager.unregisterDimension(0);
/* 130 */     DimensionManager.unregisterDimension(1);
/*     */     
/* 132 */     DimensionManager.unregisterProviderType(-1);
/* 133 */     DimensionManager.unregisterProviderType(0);
/* 134 */     DimensionManager.unregisterProviderType(1);
/* 135 */     DimensionManager.registerProviderType(-1, TFCProviderHell.class, false);
/* 136 */     DimensionManager.registerProviderType(0, TFCProvider.class, true);
/* 137 */     DimensionManager.registerProviderType(1, TFCProvider.class, false);
/*     */     
/* 139 */     DimensionManager.registerDimension(-1, -1);
/* 140 */     DimensionManager.registerDimension(0, 0);
/* 141 */     DimensionManager.registerDimension(1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void initialize(FMLInitializationEvent event) {
/* 148 */     PACKET_PIPELINE.initalise();
/*     */ 
/*     */     
/* 151 */     FMLCommonHandler.instance().bus().register(new PlayerTracker());
/*     */ 
/*     */     
/* 154 */     proxy.registerToolClasses();
/*     */ 
/*     */     
/* 157 */     TFC_Achievements.init();
/*     */ 
/*     */     
/* 160 */     FMLCommonHandler.instance().bus().register(new CraftingHandler());
/* 161 */     FMLCommonHandler.instance().bus().register(new FoodCraftingHandler());
/* 162 */     FMLCommonHandler.instance().bus().register(instance);
/*     */ 
/*     */     
/* 165 */     MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());
/*     */ 
/*     */     
/* 168 */     MinecraftForge.EVENT_BUS.register(new EntitySpawnHandler());
/*     */ 
/*     */     
/* 171 */     MinecraftForge.EVENT_BUS.register(new EntityDamageHandler());
/*     */ 
/*     */     
/* 174 */     MinecraftForge.EVENT_BUS.register(new ChatListenerTFC());
/*     */ 
/*     */     
/* 177 */     MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());
/*     */ 
/*     */     
/* 180 */     MinecraftForge.EVENT_BUS.register(new EnteringChunkHandler());
/*     */ 
/*     */     
/* 183 */     MinecraftForge.EVENT_BUS.register(new AnvilCraftingHandler());
/*     */ 
/*     */     
/* 186 */     MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
/*     */ 
/*     */     
/* 189 */     proxy.registerRenderInformation();
/*     */     
/* 191 */     proxy.registerBiomeEventHandler();
/* 192 */     proxy.setupGuiIngameForge();
/*     */ 
/*     */     
/* 195 */     proxy.setupFluids();
/* 196 */     proxy.registerFluidIcons();
/*     */ 
/*     */     
/* 199 */     TFCPotion.setup();
/*     */ 
/*     */     
/* 202 */     TFC_OreDictionary.registerOreDict();
/* 203 */     Recipes.registerRecipes();
/*     */     
/* 205 */     ItemHeat.setupItemHeat();
/*     */     
/* 207 */     TFC_Climate.initCache();
/*     */ 
/*     */ 
/*     */     
/* 211 */     ItemSetup.registerFurnaceFuel();
/* 212 */     GameRegistry.registerFuelHandler((IFuelHandler)new TFCFuelHandler());
/*     */ 
/*     */     
/* 215 */     proxy.registerChiselModes();
/*     */ 
/*     */     
/* 218 */     proxy.registerWailaClasses();
/* 219 */     proxy.hideNEIItems();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void postInit(FMLPostInitializationEvent evt) {
/* 225 */     PACKET_PIPELINE.postInitialise();
/*     */ 
/*     */     
/* 228 */     TFC_ConfigFiles.reloadOres();
/*     */     
/* 230 */     ForgeModContainer.zombieBabyChance = 0.0F;
/*     */     
/* 232 */     this.ShipsExist = isShipsAvailable();
/* 233 */     if (this.ShipsExist) LOG.info("Ships mod detected!");
/*     */   
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void serverStarting(FMLServerStartingEvent evt) throws Exception {
/* 239 */     evt.registerServerCommand((ICommand)new GetBioTempCommand());
/* 240 */     evt.registerServerCommand((ICommand)new GetTreesCommand());
/* 241 */     evt.registerServerCommand((ICommand)new GetRocksCommand());
/* 242 */     evt.registerServerCommand((ICommand)new GetSpawnProtectionCommand());
/* 243 */     evt.registerServerCommand((ICommand)new SetPlayerStatsCommand());
/* 244 */     evt.registerServerCommand((ICommand)new GetBodyTemp());
/* 245 */     evt.registerServerCommand((ICommand)new RemoveChunkCommand());
/* 246 */     evt.registerServerCommand((ICommand)new StripChunkCommand());
/* 247 */     evt.registerServerCommand((ICommand)new ClearChunkCommand());
/* 248 */     evt.registerServerCommand((ICommand)new GSPVisualCommand());
/* 249 */     evt.registerServerCommand((ICommand)new RemoveAreaCommand());
/* 250 */     evt.registerServerCommand((ICommand)new DebugModeCommand());
/* 251 */     evt.registerServerCommand((ICommand)new CommandTime());
/* 252 */     evt.registerServerCommand((ICommand)new GenCommand());
/* 253 */     evt.registerServerCommand((ICommand)new PrintImageMapCommand());
/* 254 */     evt.registerServerCommand((ICommand)new GiveSkillCommand());
/* 255 */     evt.registerServerCommand((ICommand)new CommandTransferTamed());
/*     */ 
/*     */ 
/*     */     
/* 259 */     throw new Exception();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
/* 266 */     if (eventArgs.modID.equals("terrafirmacraft")) TFC_ConfigFiles.reloadAll(); 
/*     */   }
/*     */   
/*     */   public static boolean isShipsAvailable() {
/*     */     boolean clazzExists;
/*     */     try {
/* 272 */       Class.forName("cuchaz.ships.ShipWorld", false, instance.getClass().getClassLoader());
/* 273 */       clazzExists = true;
/* 274 */     } catch (ClassNotFoundException e) {
/* 275 */       clazzExists = false;
/*     */     } 
/* 277 */     return clazzExists;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TerraFirmaCraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */