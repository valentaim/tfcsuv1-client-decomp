/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Items.ItemTFCArmor;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Events.EntityArmorCalcEvent;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityMultiPart;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.boss.EntityDragonPart;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ 
/*     */ public class EntityDamageHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onEntityHurt(LivingHurtEvent event) {
/*  40 */     EntityLivingBase entity = event.entityLiving;
/*  41 */     if (entity instanceof EntityPlayer) {
/*     */       
/*  43 */       float curMaxHealth = (float)((EntityPlayer)entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/*  44 */       float newMaxHealth = FoodStatsTFC.getMaxHealth((EntityPlayer)entity);
/*  45 */       float h = ((EntityPlayer)entity).func_110143_aJ();
/*  46 */       if (newMaxHealth != curMaxHealth)
/*  47 */         ((EntityPlayer)entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth); 
/*  48 */       if (newMaxHealth < h) {
/*  49 */         ((EntityPlayer)entity).func_70606_j(newMaxHealth);
/*     */       }
/*     */     } 
/*  52 */     if (event.source == DamageSource.field_76370_b) {
/*     */       
/*  54 */       event.ammount = 50.0F;
/*     */     }
/*  56 */     else if (event.source == DamageSource.field_76379_h) {
/*     */       
/*  58 */       float healthMod = TFC_Core.getEntityMaxHealth(entity) / 1000.0F;
/*  59 */       event.ammount *= 80.0F * healthMod;
/*     */     }
/*  61 */     else if (event.source == DamageSource.field_76369_e) {
/*     */       
/*  63 */       event.ammount = 50.0F;
/*     */     }
/*  65 */     else if (event.source == DamageSource.field_76371_c) {
/*     */       
/*  67 */       event.ammount = 100.0F;
/*     */     }
/*  69 */     else if (event.source == DamageSource.field_76368_d) {
/*     */       
/*  71 */       event.ammount = 100.0F;
/*     */     }
/*  73 */     else if (event.source == DamageSource.field_82729_p) {
/*     */       
/*  75 */       event.ammount = 100.0F;
/*     */     }
/*  77 */     else if (event.source.func_94541_c()) {
/*     */       
/*  79 */       event.ammount *= 30.0F;
/*     */     }
/*  81 */     else if (event.source == DamageSource.field_76376_m && entity.func_110143_aJ() > 25.0F) {
/*     */       
/*  83 */       event.ammount = 25.0F;
/*     */     }
/*  85 */     else if ("player".equals(event.source.field_76373_n) || "mob".equals(event.source.field_76373_n) || "arrow".equals(event.source.field_76373_n)) {
/*     */       
/*  87 */       event.ammount = applyArmorCalculations(entity, event.source, event.ammount);
/*  88 */       if ("arrow".equals(event.source.field_76373_n)) {
/*     */         
/*  90 */         Entity e = ((EntityDamageSourceIndirect)event.source).func_76364_f();
/*  91 */         if (e instanceof EntityJavelin) {
/*     */           
/*  93 */           ((EntityJavelin)e).setDamageTaken((short)(((EntityJavelin)e).damageTaken + 10));
/*  94 */           if (((EntityJavelin)e).damageTaken >= ((EntityJavelin)e).pickupItem.func_77612_l())
/*     */           {
/*  96 */             e.func_70106_y();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int applyArmorCalculations(EntityLivingBase entity, DamageSource source, float originalDamage) {
/* 105 */     ItemStack[] armor = entity.func_70035_c();
/* 106 */     int pierceRating = 0;
/* 107 */     int slashRating = 0;
/* 108 */     int crushRating = 0;
/*     */     
/* 110 */     EntityArmorCalcEvent eventPre = new EntityArmorCalcEvent(entity, originalDamage, EntityArmorCalcEvent.EventType.PRE);
/* 111 */     MinecraftForge.EVENT_BUS.post((Event)eventPre);
/* 112 */     float damage = eventPre.incomingDamage;
/*     */     
/* 114 */     if (!source.func_76363_c() && armor != null) {
/*     */ 
/*     */       
/* 117 */       int location = getRandomSlot(entity.func_70681_au());
/*     */ 
/*     */       
/* 120 */       if (armor[location] != null && armor[location].func_77973_b() instanceof ItemTFCArmor) {
/*     */         
/* 122 */         pierceRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getPiercingAR();
/* 123 */         slashRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getSlashingAR();
/* 124 */         crushRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getCrushingAR();
/* 125 */         if (entity instanceof IInnateArmor) {
/*     */           
/* 127 */           pierceRating += ((IInnateArmor)entity).getPierceArmor();
/* 128 */           slashRating += ((IInnateArmor)entity).getSlashArmor();
/* 129 */           crushRating += ((IInnateArmor)entity).getCrushArmor();
/*     */         } 
/*     */ 
/*     */         
/* 133 */         float pierceMult = getDamageReduction(pierceRating);
/* 134 */         float slashMult = getDamageReduction(slashRating);
/* 135 */         float crushMult = getDamageReduction(crushRating);
/*     */ 
/*     */         
/* 138 */         damage = processDamageSource(source, damage, pierceMult, slashMult, crushMult);
/*     */ 
/*     */ 
/*     */         
/* 142 */         armor[location].func_77972_a((int)processArmorDamage(armor[location], damage), entity);
/*     */       }
/* 144 */       else if (armor[location] == null || (armor[location] != null && !(armor[location].func_77973_b() instanceof ItemTFCArmor))) {
/*     */         
/* 146 */         if (entity instanceof IInnateArmor) {
/*     */           
/* 148 */           pierceRating += ((IInnateArmor)entity).getPierceArmor();
/* 149 */           slashRating += ((IInnateArmor)entity).getSlashArmor();
/* 150 */           crushRating += ((IInnateArmor)entity).getCrushArmor();
/*     */         } 
/*     */         
/* 153 */         float pierceMult = getDamageReduction(pierceRating);
/* 154 */         float slashMult = getDamageReduction(slashRating);
/* 155 */         float crushMult = getDamageReduction(crushRating);
/*     */         
/* 157 */         damage = processDamageSource(source, damage, pierceMult, slashMult, crushMult);
/*     */ 
/*     */ 
/*     */         
/* 161 */         if (location == 3) {
/* 162 */           damage *= 1.75F;
/* 163 */         } else if (location == 0) {
/* 164 */           entity.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 100, 1));
/*     */         } 
/*     */       } 
/* 167 */       EntityArmorCalcEvent eventPost = new EntityArmorCalcEvent(entity, damage, EntityArmorCalcEvent.EventType.POST);
/* 168 */       MinecraftForge.EVENT_BUS.post((Event)eventPost);
/*     */       
/* 170 */       float hasHealth = entity.func_110143_aJ();
/* 171 */       entity.func_70606_j(entity.func_110143_aJ() - eventPost.incomingDamage);
/* 172 */       entity.func_110142_aN().func_94547_a(source, hasHealth, eventPost.incomingDamage);
/*     */     } 
/* 174 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float processDamageSource(DamageSource source, float damage, float pierceMult, float slashMult, float crushMult) {
/* 180 */     EnumDamageType damageType = getDamageType(source);
/*     */     
/* 182 */     if (damageType == EnumDamageType.PIERCING) {
/*     */       
/* 184 */       damage *= pierceMult;
/*     */     }
/* 186 */     else if (damageType == EnumDamageType.SLASHING) {
/*     */       
/* 188 */       damage *= slashMult;
/*     */     }
/* 190 */     else if (damageType == EnumDamageType.CRUSHING) {
/*     */       
/* 192 */       damage *= crushMult;
/*     */     }
/* 194 */     else if (damageType == EnumDamageType.GENERIC) {
/*     */       
/* 196 */       damage = (float)(damage * (((crushMult + slashMult + pierceMult) / 3.0F) - 0.25D));
/*     */     } 
/* 198 */     return Math.max(0.0F, damage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private EnumDamageType getDamageType(DamageSource source) {
/* 204 */     if (source.func_76364_f() instanceof EntityPlayer) {
/*     */       
/* 206 */       EntityPlayer player = (EntityPlayer)source.func_76364_f();
/* 207 */       if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ICausesDamage)
/*     */       {
/* 209 */         return ((ICausesDamage)player.func_71045_bC().func_77973_b()).getDamageType();
/*     */       }
/*     */     } 
/*     */     
/* 213 */     if (source.func_76364_f() instanceof EntityLiving) {
/*     */       
/* 215 */       EntityLiving el = (EntityLiving)source.func_76364_f();
/* 216 */       if (el.func_70694_bm() != null && el.func_70694_bm().func_77973_b() instanceof ICausesDamage)
/*     */       {
/* 218 */         return ((ICausesDamage)el.func_70694_bm().func_77973_b()).getDamageType();
/*     */       }
/*     */     } 
/*     */     
/* 222 */     if (source.func_76364_f() instanceof ICausesDamage)
/*     */     {
/* 224 */       return ((ICausesDamage)source.func_76364_f()).getDamageType();
/*     */     }
/*     */     
/* 227 */     return EnumDamageType.GENERIC;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getRandomSlot(Random rand) {
/* 232 */     int chance = rand.nextInt(100);
/* 233 */     if (chance < 10)
/* 234 */       return 3; 
/* 235 */     if (chance < 20)
/* 236 */       return 0; 
/* 237 */     if (chance < 80) {
/* 238 */       return 2;
/*     */     }
/* 240 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private float processArmorDamage(ItemStack armor, float baseDamage) {
/* 245 */     if (armor.func_77942_o()) {
/*     */       
/* 247 */       NBTTagCompound nbt = armor.func_77978_p();
/* 248 */       if (nbt.func_74764_b("armorReductionBuff")) {
/*     */         
/* 250 */         float reductBuff = nbt.func_74771_c("armorReductionBuff") / 100.0F;
/* 251 */         return baseDamage - baseDamage * reductBuff;
/*     */       } 
/*     */     } 
/* 254 */     return baseDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getDamageReduction(int armorRating) {
/* 263 */     if (armorRating == -1000)
/* 264 */       armorRating = -999; 
/* 265 */     return 1000.0F / (1000.0F + armorRating);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onAttackEntity(AttackEntityEvent event) {
/* 271 */     if (event.entityLiving.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 274 */     EntityLivingBase attacker = event.entityLiving;
/* 275 */     EntityPlayer player = event.entityPlayer;
/* 276 */     Entity target = event.target;
/* 277 */     ItemStack stack = attacker.func_71124_b(0);
/* 278 */     if (stack != null && stack.func_77973_b().onLeftClickEntity(stack, player, target)) {
/*     */       return;
/*     */     }
/* 281 */     if (target.func_70075_an())
/*     */     {
/* 283 */       if (!target.func_85031_j(target)) {
/*     */         
/* 285 */         float damageAmount = 10.0F;
/* 286 */         if (stack != null) {
/*     */           
/* 288 */           damageAmount = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */           
/* 290 */           if (damageAmount == 1.0F)
/*     */           {
/* 292 */             damageAmount = 10.0F;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 297 */         if (player.func_70644_a(Potion.field_76420_g)) {
/* 298 */           damageAmount += (3 << player.func_70660_b(Potion.field_76420_g).func_76458_c());
/*     */         }
/* 300 */         if (player.func_70644_a(Potion.field_76437_t)) {
/* 301 */           damageAmount -= (2 << player.func_70660_b(Potion.field_76437_t).func_76458_c());
/*     */         }
/* 303 */         int knockback = 0;
/* 304 */         float enchantmentDamage = 0.0F;
/*     */         
/* 306 */         if (target instanceof EntityLiving) {
/*     */           
/* 308 */           enchantmentDamage = EnchantmentHelper.func_77512_a((EntityLivingBase)player, (EntityLivingBase)target);
/* 309 */           knockback += EnchantmentHelper.func_77507_b((EntityLivingBase)player, (EntityLivingBase)target);
/*     */         } 
/*     */         
/* 312 */         if (player.func_70051_ag()) {
/* 313 */           knockback++;
/*     */         }
/* 315 */         if (damageAmount > 0.0F || enchantmentDamage > 0.0F) {
/*     */ 
/*     */ 
/*     */           
/* 319 */           boolean criticalHit = (player.field_70143_R > 0.0F && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(Potion.field_76440_q) && player.field_70154_o == null && target instanceof EntityLiving);
/*     */ 
/*     */           
/* 322 */           if (criticalHit && damageAmount > 0.0F) {
/* 323 */             damageAmount += event.entity.field_70170_p.field_73012_v.nextInt((int)(damageAmount / 2.0F + 2.0F));
/*     */           }
/* 325 */           damageAmount += enchantmentDamage;
/* 326 */           boolean onFire = false;
/* 327 */           int fireAspect = EnchantmentHelper.func_90036_a((EntityLivingBase)player);
/*     */           
/* 329 */           if (target instanceof EntityLiving && fireAspect > 0 && !target.func_70027_ad()) {
/*     */             
/* 331 */             onFire = true;
/* 332 */             target.func_70015_d(1);
/*     */           } 
/*     */           
/* 335 */           boolean entityAttacked = target.func_70097_a(DamageSource.func_76365_a(player), damageAmount);
/*     */           
/* 337 */           if (entityAttacked) {
/*     */             
/* 339 */             if (knockback > 0) {
/*     */               
/* 341 */               target.func_70024_g((-MathHelper.func_76126_a(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (
/* 342 */                   MathHelper.func_76134_b(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
/* 343 */               player.field_70159_w *= 0.6D;
/* 344 */               player.field_70179_y *= 0.6D;
/* 345 */               player.func_70031_b(false);
/*     */             } 
/*     */             
/* 348 */             if (criticalHit) {
/* 349 */               player.func_71009_b(target);
/*     */             }
/* 351 */             if (enchantmentDamage > 0.0F) {
/* 352 */               player.func_71047_c(target);
/*     */             }
/* 354 */             if (damageAmount >= 18.0F) {
/* 355 */               player.func_71029_a((StatBase)AchievementList.field_75999_E);
/*     */             }
/* 357 */             player.func_130011_c(target);
/*     */             
/* 359 */             if (target instanceof EntityLiving) {
/* 360 */               target.func_70097_a(DamageSource.func_92087_a((Entity)attacker), damageAmount);
/*     */             }
/*     */           } 
/* 363 */           ItemStack itemstack = player.func_71045_bC();
/* 364 */           Object object = target;
/*     */           
/* 366 */           if (target instanceof EntityDragonPart) {
/*     */             
/* 368 */             IEntityMultiPart ientitymultipart = ((EntityDragonPart)target).field_70259_a;
/* 369 */             if (ientitymultipart instanceof EntityLiving) {
/* 370 */               object = ientitymultipart;
/*     */             }
/*     */           } 
/* 373 */           if (itemstack != null && object instanceof EntityLiving) {
/*     */             
/* 375 */             itemstack.func_77961_a((EntityLivingBase)object, player);
/* 376 */             if (itemstack.field_77994_a <= 0) {
/* 377 */               player.func_71028_bD();
/*     */             }
/*     */           } 
/* 380 */           if (target instanceof EntityLivingBase) {
/*     */             
/* 382 */             player.func_71064_a(StatList.field_75951_w, Math.round(damageAmount * 10.0F));
/* 383 */             if (fireAspect > 0 && entityAttacked) {
/* 384 */               target.func_70015_d(fireAspect * 4);
/* 385 */             } else if (onFire) {
/* 386 */               target.func_70066_B();
/*     */             } 
/*     */           } 
/* 389 */           player.func_71020_j(0.3F);
/*     */         } 
/*     */       } 
/*     */     }
/* 393 */     event.setCanceled(true);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Handlers\EntityDamageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */