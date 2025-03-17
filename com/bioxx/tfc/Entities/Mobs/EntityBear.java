/*      */ package com.bioxx.tfc.Entities.Mobs;
/*      */ 
/*      */ import com.bioxx.tfc.Core.TFC_Climate;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAITargetNonTamedTFC;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*      */ import com.bioxx.tfc.api.Entities.IAnimal;
/*      */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*      */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*      */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.Util.Helper;
/*      */ import fof.tfcsu.Entity.EntityBoar;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*      */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*      */ import net.minecraft.entity.ai.EntityAILookIdle;
/*      */ import net.minecraft.entity.ai.EntityAIMate;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAIWander;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EntityBear
/*      */   extends EntityTameable
/*      */   implements ICausesDamage, IAnimal, IInnateArmor
/*      */ {
/*      */   private static final float GESTATION_PERIOD = 7.0F;
/*      */   private static final float DIMORPHISM = 0.2182F;
/*      */   private static final int DEGREE_OF_DIVERSION = 4;
/*      */   private static final int FAMILIARITY_CAP = 80;
/*   64 */   private final Random rand = new Random();
/*      */   
/*      */   private float moveSpeed;
/*      */   
/*      */   private boolean isWet;
/*      */   private int sex;
/*      */   private int hunger;
/*      */   private boolean pregnant;
/*      */   private int pregnancyRequiredTime;
/*      */   private long timeOfConception;
/*      */   private float mateSizeMod;
/*      */   private float mateStrengthMod;
/*      */   private float mateAggroMod;
/*      */   private float mateObedMod;
/*      */   private float sizeMod;
/*      */   private float strengthMod;
/*   80 */   private float aggressionMod = 1.0F;
/*   81 */   private float obedienceMod = 1.0F;
/*      */   
/*      */   private boolean inLove;
/*      */   
/*      */   protected EntityAIAttackOnCollide attackAI;
/*      */   
/*      */   protected EntityAILeapAtTarget leapAI;
/*      */   
/*      */   protected EntityAITargetNonTamedTFC targetSheep;
/*      */   protected EntityAITargetNonTamedTFC targetDeer;
/*      */   protected EntityAITargetNonTamedTFC targetPig;
/*      */   protected EntityAITargetNonTamedTFC targetHorse;
/*      */   protected EntityAITargetNonTamedTFC targetPlayer;
/*      */   protected EntityAIHurtByTarget hurtAI;
/*      */   protected boolean isPeacefulAI;
/*      */   private boolean wasRoped;
/*      */   private int familiarity;
/*      */   private long lastFamiliarityUpdate;
/*      */   private boolean familiarizedToday;
/*      */   
/*      */   public EntityBear(World par1World) {
/*  102 */     super(par1World);
/*  103 */     func_70105_a(1.2F, 1.2F);
/*  104 */     this.moveSpeed = 0.4F;
/*  105 */     func_70661_as().func_75491_a(true);
/*  106 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  107 */     this.sizeMod = (float)Math.sqrt((((this.rand.nextInt(this.rand.nextInt(50) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.2182F * this.sex)));
/*      */     
/*  109 */     this.strengthMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  110 */     this.aggressionMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  111 */     this.obedienceMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*      */     
/*  113 */     this.sex = this.rand.nextInt(2);
/*  114 */     if (getGender() == IAnimal.GenderEnum.MALE)
/*  115 */       this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMate((EntityAnimal)this, this.moveSpeed)); 
/*  116 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, this.moveSpeed));
/*  117 */     this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  118 */     this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  119 */     this.attackAI = new EntityAIAttackOnCollide((EntityCreature)this, (this.moveSpeed * 1.5F), true);
/*  120 */     this.leapAI = new EntityAILeapAtTarget((EntityLiving)this, 0.4F);
/*      */ 
/*      */     
/*  123 */     this.targetSheep = new EntityAITargetNonTamedTFC(this, EntitySheepTFC.class, 200, false);
/*  124 */     this.targetDeer = new EntityAITargetNonTamedTFC(this, EntityDeer.class, 200, false);
/*      */     
/*  126 */     this.targetPig = new EntityAITargetNonTamedTFC(this, EntityBoar.class, 200, false);
/*  127 */     this.targetHorse = new EntityAITargetNonTamedTFC(this, EntityHorseTFC.class, 200, false);
/*  128 */     this.targetPlayer = new EntityAITargetNonTamedTFC(this, EntityPlayer.class, 20, false);
/*  129 */     this.hurtAI = new EntityAIHurtByTarget((EntityCreature)this, true);
/*      */     
/*  131 */     if (par1World.field_73013_u != EnumDifficulty.PEACEFUL) {
/*      */       
/*  133 */       this.isPeacefulAI = false;
/*  134 */       this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.attackAI);
/*  135 */       this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.leapAI);
/*  136 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetSheep);
/*  137 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetDeer);
/*  138 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPig);
/*  139 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetHorse);
/*  140 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPlayer);
/*  141 */       this.field_70715_bh.func_75776_a(3, (EntityAIBase)this.hurtAI);
/*      */     } else {
/*      */       
/*  144 */       this.isPeacefulAI = true;
/*      */     } 
/*      */     
/*  147 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 7.0F * (float)TFC_Time.ticksInMonth);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  153 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityBear(World par1World, IAnimal mother, List<Float> data) {
/*  159 */     this(par1World);
/*  160 */     float fatherSize = 1.0F;
/*  161 */     float fatherStr = 1.0F;
/*  162 */     float fatherAggro = 1.0F;
/*  163 */     float fatherObed = 1.0F;
/*  164 */     for (int i = 0; i < data.size(); i++) {
/*  165 */       switch (i) { case 0:
/*  166 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/*  167 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/*  168 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/*  169 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*      */           break; }
/*      */     
/*      */     } 
/*  173 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/*  174 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/*  175 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/*  176 */     float invSizeRatio = 0.5612302F;
/*  177 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/*  178 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/*  179 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/*  180 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*      */     
/*  182 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*      */ 
/*      */     
/*  185 */     setAge(TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70601_bi() {
/*  192 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  193 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  194 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*      */     
/*  196 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/*  197 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/*  198 */     float evt = 0.0F;
/*  199 */     if (TFC_Climate.getCacheManager(this.field_70170_p) != null && TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k) != null)
/*  200 */       evt = (TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k)).floatdata1; 
/*  201 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/*  202 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/*  203 */     if ((isMountainous && temp < 25.0F && temp > -10.0F && rain > 250.0F && evt < 0.75D) || (temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F))
/*  204 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/*  205 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_110147_ax() {
/*  212 */     super.func_110147_ax();
/*  213 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70652_k(Entity par1Entity) {
/*  219 */     int dam = (int)(275.0F * getStrengthMod() * getAggressionMod() * (getSizeMod() / 2.0F + 0.5F));
/*  220 */     return par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), dam);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean func_70692_ba() {
/*  229 */     return !this.wasRoped;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canFamiliarize() {
/*  236 */     return (!isAdult() || (isAdult() && this.familiarity <= 80));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70878_b(EntityAnimal par1EntityAnimal) {
/*  242 */     if (par1EntityAnimal == this)
/*  243 */       return false; 
/*  244 */     if (!(par1EntityAnimal instanceof EntityBear))
/*  245 */       return false; 
/*  246 */     EntityBear entitybear = (EntityBear)par1EntityAnimal;
/*  247 */     return (getInLove() && entitybear.getInLove());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(IAnimal animal) {
/*  253 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityBear);
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
/*      */   protected boolean func_70041_e_() {
/*  265 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/*  271 */     boolean flag = false;
/*  272 */     switch (interaction) { case MOUNT:
/*  273 */         flag = (this.familiarity > 15); break;
/*  274 */       case BREED: flag = (this.familiarity > 20); break;
/*  275 */       case NAME: flag = (this.familiarity > 70); break;
/*  276 */       case TOLERATEPLAYER: flag = (this.familiarity > 75);
/*      */         break; }
/*      */     
/*  279 */     if (!flag && player != null && !player.field_70170_p.field_72995_K) {
/*  280 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*      */     }
/*  282 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable func_90011_a(EntityAgeable entityageable) {
/*  288 */     return createChildTFC(entityageable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/*  294 */     ArrayList<Float> data = new ArrayList<>();
/*  295 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/*  296 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/*  297 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/*  298 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/*  299 */     return (EntityAgeable)new EntityBear(this.field_70170_p, this, data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70628_a(boolean par1, int par2) {
/*  305 */     float ageMod = TFC_Core.getPercentGrown(this);
/*      */     
/*  307 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
/*  308 */     func_145779_a(Items.field_151103_aS, (int)((this.rand.nextInt(6) + 2) * ageMod));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  314 */     super.func_70088_a();
/*  315 */     this.field_70180_af.func_75682_a(18, Float.valueOf(func_110143_aJ()));
/*  316 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/*  317 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*  318 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/*  319 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/*  320 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*      */   }
/*      */ 
/*      */   
/*      */   public void familiarize(EntityPlayer ep) {
/*  325 */     ItemStack stack = ep.func_70694_bm();
/*  326 */     if (stack != null && isFood(stack) && !this.familiarizedToday && canFamiliarize()) {
/*      */       
/*  328 */       if (!ep.field_71075_bZ.field_75098_d) {
/*      */         
/*  330 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*      */       }
/*      */       else {
/*      */         
/*  334 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */       } 
/*  336 */       this.hunger += 24000;
/*  337 */       this.familiarizedToday = true;
/*  338 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  339 */       func_70642_aH();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAggressionMod() {
/*  346 */     return this.aggressionMod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnimalTypeID() {
/*  353 */     return Helper.stringToInt("bear");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getAttackedVec() {
/*  359 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBirthDay() {
/*  365 */     return this.field_70180_af.func_75679_c(15);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCrushArmor() {
/*  371 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumDamageType getDamageType() {
/*  377 */     return EnumDamageType.SLASHING;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String func_70673_aS() {
/*  386 */     if (!func_70631_g_()) {
/*  387 */       return "terrafirmacraft:mob.bear.death";
/*      */     }
/*  389 */     return "terrafirmacraft:mob.bear.cub.cry";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Item func_146068_u() {
/*  399 */     return Item.func_150899_d(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDueDay() {
/*  405 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLiving getEntity() {
/*  411 */     return (EntityLiving)this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float func_70047_e() {
/*  417 */     return this.field_70131_O * 0.8F;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFamiliarity() {
/*  422 */     return this.familiarity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFamiliarizedToday() {
/*  428 */     return this.familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getFearSource() {
/*  434 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IAnimal.GenderEnum getGender() {
/*  440 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHunger() {
/*  446 */     return this.hunger;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String func_70621_aR() {
/*  455 */     if (!func_70631_g_()) {
/*  456 */       return "terrafirmacraft:mob.bear.hurt";
/*      */     }
/*  458 */     return "terrafirmacraft:mob.bear.cub.cry";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInLove() {
/*  464 */     return this.inLove;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastFamiliarityUpdate() {
/*  469 */     return this.lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String func_70639_aQ() {
/*  478 */     if (isAdult() && this.field_70170_p.field_73012_v.nextInt(100) < 5)
/*  479 */       return "terrafirmacraft:mob.bear.cry"; 
/*  480 */     if (func_70631_g_() && this.field_70170_p.field_73012_v.nextInt(100) < 5) {
/*  481 */       return "terrafirmacraft:mob.bear.cub.cry";
/*      */     }
/*  483 */     return func_70631_g_() ? null : "terrafirmacraft:mob.bear.say";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70641_bl() {
/*  492 */     return 2;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getMoveSpeed() {
/*  497 */     return this.moveSpeed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysToAdult() {
/*  503 */     return TFC_Time.daysInMonth * 60;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getObedienceMod() {
/*  509 */     return this.obedienceMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPierceArmor() {
/*  515 */     return -335;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPregnancyRequiredTime() {
/*  520 */     return this.pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSex() {
/*  525 */     return this.sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSizeMod() {
/*  531 */     return this.sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSlashArmor() {
/*  537 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float func_70599_aP() {
/*  546 */     return 0.4F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStrengthMod() {
/*  552 */     return this.strengthMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeOfConception() {
/*  557 */     return this.timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleFamiliarityUpdate() {
/*  562 */     int totalDays = TFC_Time.getTotalDays();
/*  563 */     if (this.lastFamiliarityUpdate < totalDays) {
/*  564 */       if (this.familiarizedToday && this.familiarity < 100) {
/*  565 */         this.lastFamiliarityUpdate = totalDays;
/*  566 */         this.familiarizedToday = false;
/*  567 */         float familiarityChange = 3.0F * this.obedienceMod / this.aggressionMod;
/*  568 */         if (isAdult() && this.familiarity <= 80) {
/*      */           
/*  570 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*      */         }
/*  572 */         else if (!isAdult()) {
/*  573 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/*  574 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/*  575 */           if (this.familiarity > 70) {
/*  576 */             this.obedienceMod *= 1.01F;
/*      */           }
/*      */         }
/*      */       
/*  580 */       } else if (this.familiarity < 30) {
/*  581 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/*  582 */         this.lastFamiliarityUpdate = totalDays;
/*      */       } 
/*      */     }
/*  585 */     if (this.familiarity > 100) this.familiarity = 100; 
/*  586 */     if (this.familiarity < 0) this.familiarity = 0;
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70103_a(byte par1) {
/*  592 */     if (par1 == 8) {
/*      */       
/*  594 */       this.isWet = true;
/*      */     }
/*      */     else {
/*      */       
/*  598 */       super.func_70103_a(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70085_c(EntityPlayer player) {
/*  605 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  607 */       if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {
/*      */         
/*  609 */         familiarize(player);
/*  610 */         return true;
/*      */       } 
/*  612 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
/*  613 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant)
/*      */       {
/*  615 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*      */       }
/*      */     } 
/*  618 */     ItemStack itemstack = player.func_70694_bm();
/*  619 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/*      */       
/*  621 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player))
/*      */       {
/*  623 */         itemstack.field_77994_a--;
/*      */       }
/*  625 */       return true;
/*      */     } 
/*  627 */     return super.func_70085_c(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAdult() {
/*  633 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70650_aV() {
/*  642 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70631_g_() {
/*  648 */     return !isAdult();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFood(ItemStack item) {
/*  653 */     return (item != null && item.func_77973_b().equals(TFCItems.fishRaw));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPregnant() {
/*  659 */     return this.pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWasRoped() {
/*  664 */     return this.wasRoped;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mate(IAnimal otherAnimal) {
/*  670 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*      */       
/*  672 */       otherAnimal.mate(this);
/*      */       return;
/*      */     } 
/*  675 */     this.timeOfConception = TFC_Time.getTotalTicks();
/*  676 */     this.pregnant = true;
/*  677 */     func_70875_t();
/*  678 */     otherAnimal.setInLove(false);
/*  679 */     this.mateSizeMod = otherAnimal.getSizeMod();
/*  680 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*  681 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/*  682 */     this.mateObedMod = otherAnimal.getObedienceMod();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70636_d() {
/*  692 */     TFC_Core.preventEntityDataUpdate = true;
/*  693 */     super.func_70636_d();
/*  694 */     TFC_Core.preventEntityDataUpdate = false;
/*      */     
/*  696 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  698 */       if (!this.isWet && !func_70781_l() && this.field_70122_E) {
/*      */         
/*  700 */         this.isWet = true;
/*  701 */         this.field_70170_p.func_72960_a((Entity)this, (byte)8);
/*      */       } 
/*      */       
/*  704 */       if (isPregnant())
/*      */       {
/*  706 */         if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*      */           
/*  708 */           int i = this.rand.nextInt(3) + 1;
/*  709 */           for (int x = 0; x < i; x++) {
/*      */             
/*  711 */             EntityBear baby = (EntityBear)createChildTFC((EntityAgeable)this);
/*  712 */             this.field_70170_p.func_72838_d((Entity)baby);
/*      */           } 
/*  714 */           this.pregnant = false;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  719 */     if (func_110167_bD() && !this.wasRoped) {
/*  720 */       this.wasRoped = true;
/*      */     }
/*  722 */     handleFamiliarityUpdate();
/*  723 */     syncData();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70071_h_() {
/*  732 */     super.func_70071_h_();
/*  733 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  735 */       if (!this.isPeacefulAI && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
/*      */         
/*  737 */         this.isPeacefulAI = true;
/*  738 */         this.field_70714_bg.func_85156_a((EntityAIBase)this.attackAI);
/*  739 */         this.field_70714_bg.func_85156_a((EntityAIBase)this.leapAI);
/*  740 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetSheep);
/*  741 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetDeer);
/*  742 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPig);
/*  743 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetHorse);
/*  744 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPlayer);
/*  745 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.hurtAI);
/*      */       }
/*  747 */       else if (this.isPeacefulAI && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {
/*      */         
/*  749 */         this.isPeacefulAI = false;
/*  750 */         this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.attackAI);
/*  751 */         this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.leapAI);
/*  752 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetSheep);
/*  753 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetDeer);
/*  754 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPig);
/*  755 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetHorse);
/*  756 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPlayer);
/*  757 */         this.field_70715_bh.func_75776_a(3, (EntityAIBase)this.hurtAI);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70037_a(NBTTagCompound nbt) {
/*  768 */     super.func_70037_a(nbt);
/*  769 */     this.sex = nbt.func_74762_e("Sex");
/*  770 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*      */     
/*  772 */     this.familiarity = nbt.func_74762_e("Familiarity");
/*  773 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/*  774 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*      */     
/*  776 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/*  777 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/*  778 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*      */     
/*  780 */     this.wasRoped = nbt.func_74767_n("wasRoped");
/*      */     
/*  782 */     this.hunger = nbt.func_74762_e("Hunger");
/*  783 */     this.pregnant = nbt.func_74767_n("Pregnant");
/*  784 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/*  785 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/*  786 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/*  787 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/*  788 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/*  789 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAge(int par1) {
/*  796 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAggressionMod(float aggression) {
/*  802 */     this.aggressionMod = aggression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttackedVec(Vec3 attackedVec) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBirthDay(int day) {
/*  813 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFamiliarity(int f) {
/*  819 */     this.familiarity = f;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFamiliarizedToday(boolean familiarizedToday) {
/*  824 */     this.familiarizedToday = familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFearSource(Entity fearSource) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70873_a(int par1) {
/*  835 */     if (!TFC_Core.preventEntityDataUpdate) {
/*  836 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHunger(int h) {
/*  842 */     this.hunger = h;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInLove(boolean b) {
/*  848 */     this.inLove = b;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/*  853 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMoveSpeed(float moveSpeed) {
/*  858 */     this.moveSpeed = moveSpeed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObedienceMod(float obedience) {
/*  864 */     this.obedienceMod = obedience;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/*  869 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnant(boolean pregnant) {
/*  874 */     this.pregnant = pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSex(int sex) {
/*  879 */     this.sex = sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSizeMod(float size) {
/*  885 */     this.sizeMod = size;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrengthMod(float strength) {
/*  891 */     this.strengthMod = strength;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTimeOfConception(long timeOfConception) {
/*  896 */     this.timeOfConception = timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWasRoped(boolean wasRoped) {
/*  901 */     this.wasRoped = wasRoped;
/*      */   }
/*      */ 
/*      */   
/*      */   public void syncData() {
/*  906 */     if (this.field_70180_af != null)
/*      */     {
/*  908 */       if (!this.field_70170_p.field_72995_K) {
/*      */         
/*  910 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  916 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  922 */         ByteBuffer buf = ByteBuffer.wrap(values);
/*  923 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/*  924 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/*  925 */         this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
/*      */       }
/*      */       else {
/*      */         
/*  929 */         this.sex = this.field_70180_af.func_75679_c(13);
/*      */         
/*  931 */         ByteBuffer buf = ByteBuffer.allocate(8);
/*  932 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/*  933 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/*  934 */         byte[] values = buf.array();
/*      */         
/*  936 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/*  937 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/*  938 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/*  939 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*      */         
/*  941 */         this.familiarity = values[4];
/*  942 */         this.familiarizedToday = (values[5] == 1);
/*  943 */         this.pregnant = (values[6] == 1);
/*      */ 
/*      */         
/*      */         try {
/*  947 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/*  948 */         } catch (NumberFormatException numberFormatException) {}
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean trySetName(String name, EntityPlayer player) {
/*  955 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*      */       
/*  957 */       func_94058_c(name);
/*  958 */       return true;
/*      */     } 
/*  960 */     func_85030_a(func_70631_g_() ? "terrafirmacraft:mob.bear.cub.cry" : "terrafirmacraft:mob.bear.cry", 6.0F, this.rand.nextFloat() / 2.0F + 0.75F);
/*  961 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70629_bd() {
/*  970 */     this.field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbt) {
/*  979 */     super.func_70014_b(nbt);
/*  980 */     nbt.func_74768_a("Sex", this.sex);
/*  981 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*      */     
/*  983 */     nbt.func_74768_a("Familiarity", this.familiarity);
/*  984 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/*  985 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*      */     
/*  987 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/*  988 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/*  989 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*      */     
/*  991 */     nbt.func_74757_a("wasRoped", this.wasRoped);
/*      */     
/*  993 */     nbt.func_74768_a("Hunger", this.hunger);
/*  994 */     nbt.func_74757_a("Pregnant", this.pregnant);
/*  995 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/*  996 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/*  997 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/*  998 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/*  999 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 1000 */     nbt.func_74768_a("Age", getBirthDay());
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Entities\Mobs\EntityBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */