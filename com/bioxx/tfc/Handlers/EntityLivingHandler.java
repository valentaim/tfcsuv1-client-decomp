/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.EntityProjectileTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
/*     */ import com.bioxx.tfc.Items.ItemQuiver;
/*     */ import com.bioxx.tfc.Items.Tools.ItemCustomBow;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.TFCAttributes;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraftforge.client.event.FOVUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityLivingHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onEntityLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  59 */     if (event.entityLiving instanceof EntityPlayer) {
/*     */       
/*  61 */       EntityPlayer player = (EntityPlayer)event.entityLiving;
/*     */       
/*  63 */       float newMaxHealth = FoodStatsTFC.getMaxHealth(player);
/*  64 */       float oldMaxHealth = (float)player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/*  65 */       if (oldMaxHealth != newMaxHealth)
/*     */       {
/*  67 */         player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth);
/*     */       }
/*     */       
/*  70 */       if (!player.field_70170_p.field_72995_K) {
/*     */ 
/*     */         
/*  73 */         TFC_Core.handleItemTicking(player.field_71071_by.field_70462_a, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  82 */         player.func_71024_bL().func_75122_a(20 - player.func_71024_bL().func_75116_a(), 0.0F);
/*     */         
/*  84 */         FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/*  85 */         foodstats.onUpdate(player);
/*  86 */         TFC_Core.setPlayerFoodStats(player, foodstats);
/*     */         
/*  88 */         if (foodstats.shouldSendUpdate()) {
/*     */           
/*  90 */           PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(player, 0);
/*  91 */           TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)player);
/*     */         } 
/*  93 */         if (foodstats.waterLevel / foodstats.getMaxWater(player) <= 0.25F) {
/*     */           
/*  95 */           setThirsty(player, true);
/*     */         }
/*  97 */         else if (foodstats.waterLevel / foodstats.getMaxWater(player) <= 0.5F) {
/*     */           
/*  99 */           if (player.func_70051_ag()) {
/* 100 */             player.func_70031_b(false);
/*     */           }
/*     */         } else {
/*     */           
/* 104 */           setThirsty(player, false);
/*     */         } 
/* 106 */         if (foodstats.stomachLevel / foodstats.getMaxStomach(player) <= 0.25F) {
/*     */           
/* 108 */           player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 20, 1));
/* 109 */           player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 20, 1));
/*     */         } 
/*     */ 
/*     */         
/* 113 */         boolean isOverburdened = false;
/* 114 */         if (!player.field_71075_bZ.field_75098_d)
/*     */         {
/* 116 */           for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
/*     */             
/* 118 */             ItemStack is = player.field_71071_by.func_70301_a(i);
/* 119 */             if (is != null && is.func_77973_b() instanceof IEquipable) {
/*     */               
/* 121 */               isOverburdened = ((IEquipable)is.func_77973_b()).getTooHeavyToCarry(is);
/* 122 */               if (isOverburdened) {
/*     */                 break;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/* 128 */         setOverburdened(player, isOverburdened);
/*     */ 
/*     */         
/* 131 */         NBTTagCompound nbt = player.getEntityData();
/* 132 */         long spawnProtectionTimer = nbt.func_74764_b("spawnProtectionTimer") ? nbt.func_74763_f("spawnProtectionTimer") : (TFC_Time.getTotalTicks() + 1000L);
/* 133 */         if (spawnProtectionTimer < TFC_Time.getTotalTicks())
/*     */         {
/*     */           
/* 136 */           for (int i = -2; i < 3; i++) {
/*     */             
/* 138 */             for (int k = -2; k < 3; k++) {
/*     */               
/* 140 */               int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
/* 141 */               int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;
/* 142 */               TFC_Core.getCDM(player.field_70170_p).addProtection(lastChunkX + i, lastChunkZ + k, TFCOptions.protectionGain);
/*     */             } 
/*     */           } 
/*     */           
/* 146 */           spawnProtectionTimer += 1000L;
/* 147 */           nbt.func_74772_a("spawnProtectionTimer", spawnProtectionTimer);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 152 */         PlayerInfo pi = PlayerManagerTFC.getInstance().getClientPlayer();
/* 153 */         FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/* 154 */         foodstats.clientUpdate();
/*     */         
/* 156 */         if (pi != null && pi.playerUUID.equals(player.func_110124_au())) {
/*     */           
/* 158 */           foodstats.onUpdate(player);
/* 159 */           if (player.field_71071_by.func_70448_g() != null) {
/*     */             
/* 161 */             if (player.field_71071_by.func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) {
/*     */               
/* 163 */               pi.guishowFoodRestoreAmount = true;
/* 164 */               pi.guiFoodRestoreAmount = Food.getWeight(player.field_71071_by.func_70448_g());
/*     */             }
/* 166 */             else if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemFoodTFC) {
/*     */               
/* 168 */               pi.guishowFoodRestoreAmount = true;
/* 169 */               pi.guiFoodRestoreAmount = Food.getWeight(player.field_71071_by.func_70448_g());
/*     */             } else {
/*     */               
/* 172 */               pi.guishowFoodRestoreAmount = false;
/*     */             } 
/*     */           } else {
/* 175 */             pi.guishowFoodRestoreAmount = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThirsty(EntityPlayer player, boolean b) {
/* 184 */     IAttributeInstance iattributeinstance = player.func_110148_a(SharedMonsterAttributes.field_111263_d);
/*     */     
/* 186 */     if (iattributeinstance.func_111127_a(TFCAttributes.THIRSTY_UUID) != null)
/*     */     {
/* 188 */       iattributeinstance.func_111124_b(TFCAttributes.THIRSTY);
/*     */     }
/*     */     
/* 191 */     if (b)
/*     */     {
/* 193 */       iattributeinstance.func_111121_a(TFCAttributes.THIRSTY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOverburdened(EntityPlayer player, boolean b) {
/* 199 */     IAttributeInstance iattributeinstance = player.func_110148_a(SharedMonsterAttributes.field_111263_d);
/*     */     
/* 201 */     if (iattributeinstance.func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null)
/*     */     {
/* 203 */       iattributeinstance.func_111124_b(TFCAttributes.OVERBURDENED);
/*     */     }
/*     */     
/* 206 */     if (b)
/*     */     {
/* 208 */       iattributeinstance.func_111121_a(TFCAttributes.OVERBURDENED);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleFOV(FOVUpdateEvent event) {
/* 216 */     EntityPlayerSP entityPlayerSP = event.entity;
/*     */ 
/*     */     
/* 219 */     IAttributeInstance iattributeinstance = entityPlayerSP.func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 220 */     if (iattributeinstance.func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null) {
/*     */       
/* 222 */       event.newfov = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 227 */     if (entityPlayerSP.func_71039_bw() && entityPlayerSP.func_71011_bu().func_77973_b() instanceof ItemCustomBow) {
/*     */       
/* 229 */       float fov = 1.0F;
/* 230 */       int duration = entityPlayerSP.func_71057_bx();
/* 231 */       float speed = ItemCustomBow.getUseSpeed((EntityPlayer)entityPlayerSP);
/* 232 */       float force = duration / speed;
/*     */       
/* 234 */       if (force > 1.0F) {
/*     */         
/* 236 */         force = 1.0F;
/*     */       }
/*     */       else {
/*     */         
/* 240 */         force *= force;
/*     */       } 
/*     */       
/* 243 */       fov *= 1.0F - force * 0.15F;
/* 244 */       event.newfov = fov;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleItemPickup(EntityItemPickupEvent event) {
/* 251 */     EntityPlayer player = event.entityPlayer;
/* 252 */     ItemStack item = event.item.func_92059_d();
/* 253 */     if (player.field_71071_by instanceof InventoryPlayerTFC) {
/*     */       
/* 255 */       ItemStack backItem = ((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory[0];
/*     */ 
/*     */       
/* 258 */       if (backItem == null && item.func_77973_b() instanceof IEquipable) {
/*     */         
/* 260 */         IEquipable equipment = (IEquipable)item.func_77973_b();
/* 261 */         if (equipment.getEquipType(item) == IEquipable.EquipType.BACK && (equipment == TFCItems.quiver || equipment.getTooHeavyToCarry(item)))
/*     */         {
/* 263 */           player.field_71071_by.func_70299_a(36, item.func_77946_l());
/* 264 */           item.field_77994_a = 0;
/* 265 */           event.item.func_92058_a(item);
/*     */         }
/*     */       
/*     */       }
/* 269 */       else if (backItem != null && backItem.func_77973_b() instanceof ItemQuiver) {
/*     */         
/* 271 */         ItemQuiver quiver = (ItemQuiver)backItem.func_77973_b();
/*     */ 
/*     */         
/* 274 */         if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemArrow) {
/*     */           
/* 276 */           ItemStack is = quiver.addItem(backItem, item);
/* 277 */           if (is != null) {
/* 278 */             event.item.func_92058_a(is);
/*     */           } else {
/*     */             
/* 281 */             is = item;
/* 282 */             is.field_77994_a = 0;
/* 283 */             event.item.func_92058_a(is);
/* 284 */             event.setResult(Event.Result.DENY);
/*     */           }
/*     */         
/* 287 */         } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
/*     */ 
/*     */           
/* 290 */           boolean foundJav = false;
/* 291 */           for (int i = 0; i < 9; i++) {
/*     */             
/* 293 */             if (player.field_71071_by.func_70301_a(i) != null && player.field_71071_by.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
/* 294 */               foundJav = true;
/*     */             }
/*     */           } 
/*     */           
/* 298 */           if (foundJav) {
/*     */             
/* 300 */             ItemStack is = quiver.addItem(backItem, item);
/* 301 */             if (is == null) {
/*     */               
/* 303 */               is = item;
/* 304 */               is.field_77994_a = 0;
/* 305 */               event.item.func_92058_a(is);
/* 306 */               event.setResult(Event.Result.DENY);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 313 */     if (item.func_77973_b() == TFCItems.looseRock) {
/* 314 */       player.func_71029_a((StatBase)TFC_Achievements.achLooseRock);
/* 315 */     } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOreSmall) {
/* 316 */       player.func_71029_a((StatBase)TFC_Achievements.achSmallOre);
/* 317 */     } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBloom) {
/* 318 */       player.func_71029_a((StatBase)TFC_Achievements.achIronAge);
/* 319 */     } else if (item.func_77973_b().equals(TFCItems.gemDiamond)) {
/* 320 */       player.func_71029_a((StatBase)TFC_Achievements.achDiamond);
/* 321 */     } else if (item.func_77973_b().equals(TFCItems.onion) && TFCOptions.onionsAreGross) {
/* 322 */       player.func_71029_a((StatBase)TFC_Achievements.achRutabaga);
/* 323 */     } else if (item.func_77973_b().equals(TFCItems.oreChunk) && (item.func_77960_j() == 11 || item.func_77960_j() == 46 || item.func_77960_j() == 60)) {
/* 324 */       player.func_71029_a((StatBase)TFC_Achievements.achLimonite);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityDeath(LivingDeathEvent event) {
/* 330 */     EntityLivingBase entity = event.entityLiving;
/*     */     
/* 332 */     if (entity instanceof EntityPlayer) {
/*     */       
/* 334 */       EntityPlayer player = (EntityPlayer)entity;
/* 335 */       SkillStats skills = TFC_Core.getSkillStats(player);
/* 336 */       PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
/* 337 */       pi.tempSkills = skills;
/*     */ 
/*     */       
/* 340 */       if (entity.field_70170_p.func_82736_K().func_82766_b("keepInventory") && player.field_71071_by instanceof InventoryPlayerTFC)
/*     */       {
/* 342 */         pi.tempEquipment = (ItemStack[])((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory.clone();
/*     */       }
/*     */     } 
/*     */     
/* 346 */     if (event.entity.field_71093_bK == 1) {
/* 347 */       event.entity.func_71027_c(0);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLivingDrop(LivingDropsEvent event) {
/* 353 */     boolean processed = false;
/* 354 */     if (!event.entity.field_70170_p.field_72995_K && event.recentlyHit && !(event.entity instanceof EntityPlayer) && !(event.entity instanceof net.minecraft.entity.monster.EntityZombie))
/*     */     {
/* 356 */       if (event.source.func_76364_f() instanceof EntityPlayer || event.source.func_76352_a()) {
/*     */         
/* 358 */         boolean foundFood = false;
/* 359 */         processed = true;
/* 360 */         ArrayList<EntityItem> drop = new ArrayList<EntityItem>();
/* 361 */         EntityPlayer p = null;
/* 362 */         if (event.source.func_76364_f() instanceof EntityPlayer) {
/* 363 */           p = (EntityPlayer)event.source.func_76364_f();
/* 364 */         } else if (event.source.func_76364_f() instanceof EntityProjectileTFC) {
/*     */           
/* 366 */           EntityProjectileTFC proj = (EntityProjectileTFC)event.source.func_76364_f();
/* 367 */           if (proj.field_70250_c instanceof EntityPlayer)
/* 368 */             p = (EntityPlayer)proj.field_70250_c; 
/*     */         } 
/* 370 */         for (EntityItem ei : event.drops) {
/*     */           
/* 372 */           ItemStack is = ei.func_92059_d();
/* 373 */           if (is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */             
/* 375 */             if (p == null)
/*     */               continue; 
/* 377 */             foundFood = true;
/*     */             
/* 379 */             int sweetMod = Food.getSweetMod(is);
/* 380 */             int sourMod = Food.getSourMod(is);
/* 381 */             int saltyMod = Food.getSaltyMod(is);
/* 382 */             int bitterMod = Food.getBitterMod(is);
/* 383 */             int umamiMod = Food.getSavoryMod(is);
/*     */             
/* 385 */             float oldWeight = Food.getWeight(is);
/* 386 */             Food.setWeight(is, 0.0F);
/* 387 */             float newWeight = oldWeight * (TFC_Core.getSkillStats(p).getSkillMultiplier("skill.butchering") + 0.01F);
/* 388 */             while (newWeight >= 0.1F) {
/*     */               
/* 390 */               float fw = Helper.roundNumber(Math.min(160.0F, newWeight), 10.0F);
/* 391 */               if (fw < 160.0F)
/* 392 */                 newWeight = 0.0F; 
/* 393 */               newWeight -= fw;
/*     */               
/* 395 */               ItemStack result = ItemFoodTFC.createTag(new ItemStack(is.func_77973_b(), 1), fw);
/*     */               
/* 397 */               if (sweetMod != 0)
/* 398 */                 Food.setSweetMod(result, sweetMod); 
/* 399 */               if (sourMod != 0)
/* 400 */                 Food.setSourMod(result, sourMod); 
/* 401 */               if (saltyMod != 0)
/* 402 */                 Food.setSaltyMod(result, saltyMod); 
/* 403 */               if (bitterMod != 0)
/* 404 */                 Food.setBitterMod(result, bitterMod); 
/* 405 */               if (umamiMod != 0) {
/* 406 */                 Food.setSavoryMod(result, umamiMod);
/*     */               }
/* 408 */               drop.add(new EntityItem(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, result));
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/* 413 */           drop.add(ei);
/*     */         } 
/*     */         
/* 416 */         event.drops.clear();
/* 417 */         event.drops.addAll(drop);
/* 418 */         if (foundFood && p != null)
/*     */         {
/* 420 */           TFC_Core.getSkillStats(p).increaseSkill("skill.butchering", 1);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 425 */     if (!processed && !(event.entity instanceof EntityPlayer) && !(event.entity instanceof net.minecraft.entity.monster.EntityZombie)) {
/*     */       
/* 427 */       ArrayList<EntityItem> drop = new ArrayList<EntityItem>();
/* 428 */       for (EntityItem ei : event.drops) {
/*     */         
/* 430 */         if (!(ei.func_92059_d().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood))
/*     */         {
/* 432 */           drop.add(ei);
/*     */         }
/*     */       } 
/* 435 */       event.drops.clear();
/* 436 */       event.drops.addAll(drop);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\EntityLivingHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */