/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockFarmland;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TECrop
/*     */   extends NetworkTileEntity
/*     */ {
/*  37 */   public float growth = 0.1F;
/*  38 */   private long plantedTime = TFC_Time.getTotalTicks();
/*  39 */   private long growthTimer = TFC_Time.getTotalTicks();
/*  40 */   private byte sunLevel = 1;
/*     */   
/*     */   public int cropId;
/*     */   public int tendingLevel;
/*     */   private int killLevel;
/*     */   
/*     */   public void func_145845_h() {
/*  47 */     Random r = new Random();
/*  48 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/*  50 */       float timeMultiplier = 360.0F / TFC_Time.daysInYear;
/*  51 */       CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
/*  52 */       long time = TFC_Time.getTotalTicks();
/*     */       
/*  54 */       if (crop != null && this.growthTimer < time && this.sunLevel > 0) {
/*     */         
/*  56 */         this.sunLevel = (byte)(this.sunLevel - 1);
/*  57 */         if (crop.needsSunlight && hasSunlight(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*     */           
/*  59 */           this.sunLevel = (byte)(this.sunLevel + 1);
/*  60 */           if (this.sunLevel > 30) {
/*  61 */             this.sunLevel = 30;
/*     */           }
/*     */         } 
/*  64 */         TEFarmland tef = null;
/*  65 */         TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  66 */         if (te instanceof TEFarmland) {
/*  67 */           tef = (TEFarmland)te;
/*     */         }
/*  69 */         float ambientTemp = TFC_Climate.getHeightAdjustedTempSpecificDay(this.field_145850_b, TFC_Time.getDayOfYearFromTick(this.growthTimer), this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  70 */         float tempAdded = 0.0F;
/*  71 */         boolean isDormant = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  93 */         if (!crop.dormantInFrost && ambientTemp < crop.minGrowthTemp) {
/*  94 */           tempAdded = -0.03F * (crop.minGrowthTemp - ambientTemp);
/*  95 */         } else if (crop.dormantInFrost && ambientTemp < crop.minGrowthTemp) {
/*     */           
/*  97 */           if (this.growth > 1.0F)
/*  98 */             tempAdded = -0.03F * (crop.minGrowthTemp - ambientTemp); 
/*  99 */           isDormant = true;
/*     */         }
/* 101 */         else if (ambientTemp < 28.0F) {
/* 102 */           tempAdded = ambientTemp * 3.5E-4F;
/* 103 */         } else if (ambientTemp < 37.0F) {
/* 104 */           tempAdded = (28.0F - ambientTemp - 28.0F) * 3.0E-4F;
/*     */         } 
/* 106 */         if (!crop.dormantInFrost && ambientTemp < crop.minAliveTemp) {
/*     */           
/* 108 */           int baseKillChance = 6;
/* 109 */           if (this.field_145850_b.field_73012_v.nextInt(baseKillChance - this.killLevel) == 0) {
/* 110 */             killCrop(crop);
/*     */           
/*     */           }
/* 113 */           else if (this.killLevel < baseKillChance - 1) {
/* 114 */             this.killLevel++;
/*     */           }
/*     */         
/* 117 */         } else if (crop.dormantInFrost && ambientTemp < crop.minAliveTemp) {
/*     */           
/* 119 */           if (this.growth > 1.0F) {
/*     */             
/* 121 */             int baseKillChance = 6;
/* 122 */             if (this.field_145850_b.field_73012_v.nextInt(baseKillChance - this.killLevel) == 0) {
/* 123 */               killCrop(crop);
/*     */             
/*     */             }
/* 126 */             else if (this.killLevel < baseKillChance - 1) {
/* 127 */               this.killLevel++;
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 133 */           this.killLevel = 0;
/*     */         } 
/*     */         
/* 136 */         int nutriType = crop.cycleType;
/* 137 */         int nutri = (tef != null) ? tef.nutrients[nutriType] : 18000;
/* 138 */         int fert = (tef != null) ? tef.nutrients[3] : 0;
/* 139 */         int soilMax = (tef != null) ? tef.getSoilMax() : 18000;
/*     */         
/* 141 */         float waterBoost = BlockFarmland.isFreshWaterNearby(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) ? 0.1F : 0.0F;
/*     */ 
/*     */         
/* 144 */         nutri = Math.min(nutri + fert, (int)(soilMax * 1.25F));
/*     */         
/* 146 */         float nutriMult = 0.2F + nutri / soilMax * 0.5F + waterBoost;
/*     */         
/* 148 */         if (tef != null && !isDormant) {
/*     */           
/* 150 */           if (tef.nutrients[nutriType] > 0) {
/* 151 */             tef.drainNutrients(nutriType, crop.nutrientUsageMult);
/*     */           }
/* 153 */           if (tef.nutrients[3] > 0) {
/* 154 */             tef.drainNutrients(3, crop.nutrientUsageMult);
/*     */           }
/*     */         } 
/* 157 */         float growthRate = Math.max(0.0F, (crop.numGrowthStages / crop.growthTime * TFC_Time.timeRatio96 + tempAdded) * nutriMult * timeMultiplier * TFCOptions.cropGrowthMultiplier);
/* 158 */         if (tef != null && tef.isInfested)
/* 159 */           growthRate /= 2.0F; 
/* 160 */         int oldGrowth = (int)Math.floor(this.growth);
/*     */         
/* 162 */         if (!isDormant) {
/* 163 */           this.growth += growthRate;
/*     */         }
/* 165 */         if (oldGrowth < (int)Math.floor(this.growth))
/*     */         {
/*     */           
/* 168 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */ 
/*     */         
/* 172 */         if (((TFCOptions.enableCropsDie || !TFC_Core.isFarmland(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e))) && crop.maxLifespan == -1 && this.growth > crop.numGrowthStages + crop.numGrowthStages / 2.0F) || this.growth < 0.0F)
/*     */         {
/*     */           
/* 175 */           killCrop(crop);
/*     */         }
/*     */         
/* 178 */         this.growthTimer += (r.nextInt(2) + 23) * 1000L;
/*     */       
/*     */       }
/* 181 */       else if (crop != null && crop.needsSunlight && this.sunLevel <= 0) {
/*     */         
/* 183 */         killCrop(crop);
/*     */       } 
/*     */ 
/*     */       
/* 187 */       if (TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) && TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) < 0.0F)
/*     */       {
/* 189 */         if ((crop != null && !crop.dormantInFrost) || this.growth > 1.0F)
/*     */         {
/* 191 */           killCrop(crop);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasSunlight(World world, int x, int y, int z) {
/* 199 */     Chunk chunk = world.func_72938_d(x, z);
/* 200 */     int skylight = chunk.func_76614_a(EnumSkyBlock.Sky, x & 0xF, y, z & 0xF);
/* 201 */     boolean sky = world.func_72937_j(x, y + 1, z);
/* 202 */     return (sky || skylight > 13);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEstimatedGrowth(CropIndex crop) {
/* 207 */     return crop.numGrowthStages / (float)(this.growthTimer - this.plantedTime / 24000L) * 1.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onHarvest(World world, EntityPlayer player, boolean isBreaking) {
/* 212 */     if (!world.field_72995_K) {
/*     */       
/* 214 */       CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
/*     */       
/* 216 */       if (crop != null && this.growth >= (crop.numGrowthStages - 1)) {
/*     */         
/* 218 */         ItemStack is1 = crop.getOutput1(this);
/* 219 */         ItemStack is2 = crop.getOutput2(this);
/* 220 */         ItemStack seedStack = crop.getSeed();
/*     */         
/* 222 */         if (is1 != null) {
/* 223 */           world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is1));
/*     */         }
/* 225 */         if (is2 != null) {
/* 226 */           world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is2));
/*     */         }
/* 228 */         int skill = 20 - (int)(20.0F * TFC_Core.getSkillStats(player).getSkillMultiplier("skill.agriculture"));
/*     */         
/* 230 */         seedStack.field_77994_a = 1 + ((world.field_73012_v.nextInt(1 + skill) == 0) ? 1 : 0);
/* 231 */         if (isBreaking) {
/* 232 */           world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, seedStack));
/*     */         }
/* 234 */         TFC_Core.getSkillStats(player).increaseSkill("skill.agriculture", 1);
/*     */         
/* 236 */         if (TFC_Core.isSoil(world.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e))) {
/* 237 */           player.func_71064_a((StatBase)TFC_Achievements.achWildVegetable, 1);
/*     */         }
/* 239 */       } else if (crop != null) {
/*     */         
/* 241 */         ItemStack is = crop.getSeed();
/* 242 */         is.field_77994_a = 1;
/* 243 */         world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void killCrop(CropIndex crop) {
/* 250 */     ItemStack is = crop.getSeed();
/* 251 */     is.field_77994_a = 1;
/* 252 */     if (TFC_Core.isFarmland(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) && TFCOptions.enableSeedDrops) {
/*     */       
/* 254 */       if (this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.worldItem))
/*     */       {
/* 256 */         TEWorldItem te = (TEWorldItem)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 257 */         te.storage[0] = is;
/* 258 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 263 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 273 */     super.func_145839_a(nbt);
/* 274 */     this.growth = nbt.func_74760_g("growth");
/* 275 */     this.cropId = nbt.func_74762_e("cropId");
/* 276 */     this.growthTimer = nbt.func_74763_f("growthTimer");
/* 277 */     this.plantedTime = nbt.func_74763_f("plantedTime");
/* 278 */     this.killLevel = nbt.func_74762_e("killLevel");
/* 279 */     this.sunLevel = nbt.func_74771_c("sunLevel");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 288 */     super.func_145841_b(nbt);
/* 289 */     nbt.func_74776_a("growth", this.growth);
/* 290 */     nbt.func_74768_a("cropId", this.cropId);
/* 291 */     nbt.func_74772_a("growthTimer", this.growthTimer);
/* 292 */     nbt.func_74772_a("plantedTime", this.plantedTime);
/* 293 */     nbt.func_74768_a("killLevel", this.killLevel);
/* 294 */     nbt.func_74774_a("sunLevel", this.sunLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 299 */     this.growth = nbt.func_74760_g("growth");
/* 300 */     this.cropId = nbt.func_74762_e("cropId");
/* 301 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 307 */     this.growth = nbt.func_74760_g("growth");
/* 308 */     this.cropId = nbt.func_74762_e("cropId");
/* 309 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {
/* 314 */     nbt.func_74776_a("growth", this.growth);
/* 315 */     nbt.func_74768_a("cropId", this.cropId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 320 */     nbt.func_74776_a("growth", this.growth);
/* 321 */     nbt.func_74768_a("cropId", this.cropId);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\TileEntities\TECrop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */