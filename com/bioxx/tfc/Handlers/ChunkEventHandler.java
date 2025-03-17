/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenGrowCrops;
/*     */ import com.bioxx.tfc.WorldGen.WorldCacheManager;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent;
/*     */ import net.minecraftforge.event.world.ChunkEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkEventHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onLoad(ChunkEvent.Load event) {
/*  36 */     if (!event.world.field_72995_K && TFC_Core.getCDM(event.world) != null && event.getChunk() != null) {
/*     */       
/*  38 */       ChunkData cd = TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
/*  39 */       if (cd == null)
/*     */         return; 
/*  41 */       BiomeGenBase biome = event.world.func_72807_a((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
/*  42 */       int month = TFC_Time.getSeasonAdjustedMonth((event.getChunk()).field_76647_h << 4);
/*  43 */       if (TFC_Time.getYear() > cd.lastSpringGen && month > 1 && month < 6) {
/*     */         
/*  45 */         int chunkX = (event.getChunk()).field_76635_g;
/*  46 */         int chunkZ = (event.getChunk()).field_76647_h;
/*  47 */         if (TFC_Core.isWaterBiome(biome)) {
/*     */           
/*  49 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  50 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*     */         } 
/*  52 */         cd.lastSpringGen = TFC_Time.getYear();
/*     */         
/*  54 */         Random rand = new Random(event.world.func_72905_C() + (((chunkX >> 3) - (chunkZ >> 3)) * (chunkZ >> 3)));
/*  55 */         int cropid = rand.nextInt(CropManager.getInstance().getTotalCrops());
/*  56 */         CropIndex crop = CropManager.getInstance().getCropFromId(cropid);
/*  57 */         if (event.world.field_73012_v.nextInt(25) == 0 && crop != null)
/*     */         {
/*  59 */           int num = 1 + event.world.field_73012_v.nextInt(5);
/*  60 */           WorldGenGrowCrops cropGen = new WorldGenGrowCrops(cropid);
/*  61 */           int x = (chunkX << 4) + event.world.field_73012_v.nextInt(16) + 8;
/*  62 */           int z = (chunkZ << 4) + event.world.field_73012_v.nextInt(16) + 8;
/*  63 */           cropGen.generate(event.world, event.world.field_73012_v, x, z, num);
/*     */         }
/*     */       
/*  66 */       } else if (TFC_Time.getYear() > cd.lastSpringGen && month >= 6) {
/*     */ 
/*     */         
/*  69 */         if (TFC_Core.isWaterBiome(biome)) {
/*     */           
/*  71 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  72 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*     */         } 
/*  74 */         cd.lastSpringGen = TFC_Time.getYear();
/*     */       }
/*  76 */       else if (TFC_Time.getYear() > cd.lastSpringGen + 1) {
/*     */         
/*  78 */         if (TFC_Core.isWaterBiome(biome)) {
/*     */           
/*  80 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  81 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*     */         } 
/*  83 */         cd.lastSpringGen = TFC_Time.getYear();
/*     */       }
/*     */     
/*  86 */     } else if (TFC_Core.getCDM(event.world) != null && TFC_Climate.getCacheManager(event.world) != null) {
/*     */       
/*  88 */       Chunk chunk = event.getChunk();
/*  89 */       ChunkData data = (new ChunkData(chunk)).createNew(event.world, chunk.field_76635_g, chunk.field_76647_h);
/*  90 */       data.rainfallMap = TFC_Climate.getCacheManager(event.world).loadRainfallLayerGeneratorData(data.rainfallMap, (event.getChunk()).field_76635_g * 16, (event.getChunk()).field_76647_h * 16, 16, 16);
/*  91 */       TFC_Core.getCDM(event.world).addData(chunk, data);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUnload(ChunkEvent.Unload event) {
/*  98 */     if (TFC_Core.getCDM(event.world) != null && 
/*  99 */       TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h) != null)
/* 100 */       (TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h)).isUnloaded = true; 
/* 101 */     if (event.world.field_72995_K) {
/* 102 */       TFC_Core.getCDM(event.world).removeData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUnloadWorld(WorldEvent.Unload event) {
/* 108 */     TFC_Climate.removeCacheManager(event.world);
/* 109 */     TFC_Core.removeCDM(event.world);
/* 110 */     if (event.world.field_73011_w.field_76574_g == 0) {
/* 111 */       AnvilManager.getInstance().clearRecipes();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLoadWorld(WorldEvent.Load event) {
/* 117 */     if (event.world.field_73011_w.field_76574_g == 0 && event.world.func_82737_E() < 100L)
/* 118 */       createSpawn(event.world); 
/* 119 */     if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0 && AnvilManager.getInstance().getRecipeList().size() == 0)
/*     */     {
/* 121 */       TFC_Core.setupWorld(event.world);
/*     */     }
/* 123 */     TFC_Climate.worldPair.put(event.world, new WorldCacheManager(event.world));
/* 124 */     TFC_Core.addCDM(event.world);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onDataLoad(ChunkDataEvent.Load event) {
/* 130 */     if (!event.world.field_72995_K) {
/*     */       
/* 132 */       NBTTagCompound eventTag = event.getData();
/*     */       
/* 134 */       Chunk chunk = event.getChunk();
/* 135 */       if (eventTag.func_74764_b("ChunkData")) {
/*     */         
/* 137 */         NBTTagCompound spawnProtectionTag = eventTag.func_74775_l("ChunkData");
/* 138 */         ChunkData data = new ChunkData(chunk, spawnProtectionTag);
/* 139 */         if (TFC_Core.getCDM(event.world) != null) {
/* 140 */           TFC_Core.getCDM(event.world).addData(chunk, data);
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 146 */         NBTTagCompound levelTag = eventTag.func_74775_l("Level");
/* 147 */         ChunkData data = (new ChunkData(chunk)).createNew(event.world, levelTag.func_74762_e("xPos"), levelTag.func_74762_e("zPos"));
/* 148 */         if (TFC_Core.getCDM(event.world) != null) {
/* 149 */           TFC_Core.getCDM(event.world).addData(chunk, data);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onDataSave(ChunkDataEvent.Save event) {
/* 157 */     if (!event.world.field_72995_K && TFC_Core.getCDM(event.world) != null) {
/*     */       
/* 159 */       NBTTagCompound levelTag = event.getData().func_74775_l("Level");
/* 160 */       int x = levelTag.func_74762_e("xPos");
/* 161 */       int z = levelTag.func_74762_e("zPos");
/* 162 */       ChunkData data = TFC_Core.getCDM(event.world).getData(x, z);
/*     */       
/* 164 */       if (data != null) {
/*     */         
/* 166 */         NBTTagCompound spawnProtectionTag = data.getTag();
/*     */ 
/*     */         
/* 169 */         event.getData().func_74782_a("ChunkData", (NBTBase)spawnProtectionTag);
/* 170 */         if (data.isUnloaded) {
/* 171 */           TFC_Core.getCDM(event.world).removeData(x, z);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ChunkCoordinates createSpawn(World world) {
/* 178 */     List biomeList = world.func_72959_q().func_76932_a();
/* 179 */     long seed = world.func_72912_H().func_76063_b();
/* 180 */     Random rand = new Random(seed);
/*     */     
/* 182 */     ChunkPosition chunkCoord = null;
/* 183 */     int xOffset = 0;
/* 184 */     int xCoord = 0;
/*     */     
/* 186 */     int zCoord = 10000;
/* 187 */     int startingZ = 5000 + rand.nextInt(10000);
/*     */     
/* 189 */     while (chunkCoord == null) {
/*     */       
/* 191 */       chunkCoord = world.func_72959_q().func_150795_a(xOffset, -startingZ, 64, biomeList, rand);
/* 192 */       if (chunkCoord != null) {
/*     */         
/* 194 */         xCoord = chunkCoord.field_151329_a;
/* 195 */         zCoord = chunkCoord.field_151328_c;
/*     */         
/*     */         continue;
/*     */       } 
/* 199 */       xOffset += 64;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 204 */     int var9 = 0;
/* 205 */     while (!world.field_73011_w.func_76566_a(xCoord, zCoord)) {
/*     */       
/* 207 */       xCoord += rand.nextInt(16) - rand.nextInt(16);
/* 208 */       zCoord += rand.nextInt(16) - rand.nextInt(16);
/* 209 */       var9++;
/* 210 */       if (var9 == 1000) {
/*     */         break;
/*     */       }
/*     */     } 
/* 214 */     WorldInfo info = world.func_72912_H();
/* 215 */     info.func_76081_a(xCoord, world.func_72825_h(xCoord, zCoord), zCoord);
/* 216 */     if (!info.func_76066_a().func_74764_b("superseed"))
/* 217 */       info.func_76066_a().func_74772_a("superseed", System.currentTimeMillis()); 
/* 218 */     return new ChunkCoordinates(xCoord, world.func_72825_h(xCoord, zCoord), zCoord);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\ChunkEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */