/*      */ package com.bioxx.tfc.Entities.Mobs;
/*      */ import com.bioxx.tfc.Core.TFC_Climate;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Entities.AI.AIEatGrass;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIPanicTFC;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.TerraFirmaCraft;
/*      */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*      */ import com.bioxx.tfc.api.Entities.IAnimal;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.Util.Helper;
/*      */ import fof.tfcsu.Entity.EntityBear3D;
/*      */ import fof.tfcsu.Weight.InventoryWeight;
/*      */ import fof.tfcsu.utils.ExpBonus;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*      */ import net.minecraft.entity.ai.EntityAILookIdle;
/*      */ import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAITempt;
/*      */ import net.minecraft.entity.ai.EntityAIWander;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.ai.attributes.IAttribute;
/*      */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.AnimalChest;
/*      */ import net.minecraft.inventory.IInvBasic;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class EntityHorseTFC extends EntityHorse implements IInvBasic, IAnimal {
/*   64 */   private static final IEntitySelector HORSE_BREEDING_SELECTOR = new EntityHorseBredSelector();
/*   65 */   private static final IAttribute HORSE_JUMP_STRENGTH = (IAttribute)(new RangedAttribute("horse.jumpStrengthTFC", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump StrengthTFC").func_111112_a(true);
/*      */   
/*      */   private static final float GESTATION_PERIOD = 11.17F;
/*      */   
/*      */   private static final float DIMORPHISM = 0.0813F;
/*      */   
/*      */   private static final int DEGREE_OF_DIVERSION = 2;
/*      */   
/*      */   private static final int FAMILIARITY_CAP = 35;
/*      */   
/*   75 */   protected final AIEatGrass aiEatGrass = new AIEatGrass(this);
/*      */   
/*      */   private long animalID;
/*      */   private int sex;
/*      */   private int hunger;
/*      */   private boolean pregnant;
/*      */   private int pregnancyRequiredTime;
/*      */   private long timeOfConception;
/*      */   private float sizeMod;
/*      */   private float strengthMod;
/*   85 */   private float aggressionMod = 1.0F;
/*   86 */   private float obedienceMod = 1.0F;
/*      */   
/*      */   private float mateSizeMod;
/*      */   private float mateStrengthMod;
/*      */   private float mateAggroMod;
/*      */   private float mateObedMod;
/*      */   private int mateType;
/*      */   private int mateVariant;
/*   94 */   private double mateMaxHealth = calcMaxHealth();
/*   95 */   private double mateJumpStrength = calcJumpStrength();
/*   96 */   private double mateMoveSpeed = calcMoveSpeed();
/*      */   
/*      */   private boolean inLove;
/*      */   
/*      */   private Vec3 attackedVec;
/*      */   private Entity fearSource;
/*      */   private int familiarity;
/*      */   private long lastFamiliarityUpdate;
/*      */   private boolean familiarizedToday;
/*      */   
/*      */   public EntityHorseTFC(World par1World) {
/*  107 */     super(par1World);
/*  108 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/*  109 */     this.hunger = 168000;
/*  110 */     this.pregnant = false;
/*  111 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 11.17F * (float)TFC_Time.ticksInMonth);
/*  112 */     this.timeOfConception = 0L;
/*  113 */     this.sex = this.field_70146_Z.nextInt(2);
/*  114 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0813F * this.sex)));
/*  115 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  116 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  117 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*  118 */     func_70105_a(1.4F, 1.6F);
/*  119 */     func_70661_as().func_75491_a(true);
/*  120 */     this.field_70714_bg.field_75782_a.clear();
/*  121 */     this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  122 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIRunAroundLikeCrazy(this, 1.2D));
/*  123 */     this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.0D));
/*  124 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.7D));
/*  125 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  126 */     this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  127 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/*  128 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
/*  129 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityBear3D.class, 16.0F, 0.25D, 0.30000001192092896D));
/*  130 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
/*  131 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
/*  132 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
/*  133 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
/*  134 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
/*  135 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
/*  136 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
/*  137 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanicTFC((EntityCreature)this, 1.2D, true));
/*  138 */     updateChestSaddle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  144 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityHorseTFC(World par1World, IAnimal mother, List<Float> data, int type, int variant) {
/*  151 */     this(par1World);
/*  152 */     float fatherSize = 1.0F;
/*  153 */     float fatherStr = 1.0F;
/*  154 */     float fatherAggro = 1.0F;
/*  155 */     float fatherObed = 1.0F;
/*  156 */     for (int i = 0; i < data.size(); i++) {
/*  157 */       switch (i) { case 0:
/*  158 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/*  159 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/*  160 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/*  161 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*      */           break; }
/*      */     
/*      */     } 
/*  165 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/*  166 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/*  167 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/*  168 */     float invSizeRatio = 0.52118623F;
/*  169 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/*  170 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/*  171 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/*  172 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*      */     
/*  174 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*      */ 
/*      */     
/*  177 */     setAge(TFC_Time.getTotalDays());
/*  178 */     func_110214_p(type);
/*  179 */     func_110235_q(variant);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70601_bi() {
/*  186 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  187 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  188 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*  189 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/*  190 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/*  191 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/*  192 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/*  193 */     if (!isMountainous && temp > 0.0F && temp < 30.0F && rain > 100.0F && rain <= 500.0F) {
/*      */       
/*  195 */       func_110262_ch();
/*  196 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
/*      */     } 
/*  198 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_110147_ax() {
/*  204 */     super.func_110147_ax();
/*  205 */     func_110140_aT().func_111150_b(HORSE_JUMP_STRENGTH);
/*  206 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((1250.0F * this.sizeMod * this.strengthMod));
/*  207 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22499999403953552D * this.strengthMod * this.obedienceMod / (this.sizeMod * this.sizeMod));
/*  208 */     func_70606_j(func_110138_aP());
/*      */   }
/*      */ 
/*      */   
/*      */   private double calcJumpStrength() {
/*  213 */     return 0.4000000059604645D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D;
/*      */   }
/*      */ 
/*      */   
/*      */   private float calcMaxHealth() {
/*  218 */     return 1000.0F + this.field_70146_Z.nextInt(101) + this.field_70146_Z.nextInt(151);
/*      */   }
/*      */ 
/*      */   
/*      */   private double calcMoveSpeed() {
/*  223 */     return (0.44999998807907104D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D) * 0.25D;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canFamiliarize() {
/*  229 */     return (!isAdult() || (isAdult() && this.familiarity <= 35));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70878_b(EntityAnimal animal) {
/*  238 */     if (animal == this)
/*      */     {
/*  240 */       return false;
/*      */     }
/*  242 */     if (animal.getClass() != getClass())
/*      */     {
/*  244 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  248 */     EntityHorseTFC entityhorse = (EntityHorseTFC)animal;
/*      */     
/*  250 */     if (getBreedable() && entityhorse.getBreedable()) {
/*      */       
/*  252 */       int i = func_110265_bP();
/*  253 */       int j = entityhorse.func_110265_bP();
/*  254 */       return (i == j || (i == 0 && j == 1) || (i == 1 && j == 0));
/*      */     } 
/*      */ 
/*      */     
/*  258 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(IAnimal animal) {
/*  266 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityHorseTFC);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/*  272 */     boolean flag = false;
/*  273 */     switch (interaction) { case MOUNT:
/*  274 */         flag = (this.familiarity > 15); break;
/*  275 */       case BREED: flag = (this.familiarity > 20); break;
/*  276 */       case NAME: flag = (this.familiarity > 40);
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
/*      */   public void func_110160_i(boolean par1, boolean par2) {
/*  288 */     Entity entity = func_110166_bE();
/*  289 */     if (entity instanceof EntityPlayer) {
/*      */ 
/*      */       
/*  292 */       if (entity.func_70093_af()) {
/*  293 */         super.func_110160_i(par1, true);
/*      */       }
/*      */     } else {
/*      */       
/*  297 */       super.func_110160_i(par1, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable func_90011_a(EntityAgeable eAgeable) {
/*  304 */     return createChildTFC(eAgeable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/*  310 */     ArrayList<Float> data = new ArrayList<>();
/*  311 */     data.add(Float.valueOf(this.mateSizeMod));
/*  312 */     data.add(Float.valueOf(this.mateStrengthMod));
/*  313 */     data.add(Float.valueOf(this.mateAggroMod));
/*  314 */     data.add(Float.valueOf(this.mateObedMod));
/*      */     
/*  316 */     int momType = func_110265_bP();
/*  317 */     int dadType = this.mateType;
/*  318 */     int babyType = 0;
/*  319 */     int babyVariant = 0;
/*      */     
/*  321 */     if (momType == dadType) {
/*      */       
/*  323 */       babyType = momType;
/*      */     }
/*  325 */     else if ((momType == 0 && dadType == 1) || (momType == 1 && dadType == 0)) {
/*      */       
/*  327 */       babyType = 2;
/*      */     } 
/*      */     
/*  330 */     if (babyType == 0) {
/*      */       
/*  332 */       int l = this.field_70146_Z.nextInt(9);
/*      */       
/*  334 */       if (l < 4) {
/*      */         
/*  336 */         babyVariant = func_110202_bQ() & 0xFF;
/*      */       }
/*  338 */       else if (l < 8) {
/*      */         
/*  340 */         babyVariant = this.mateVariant & 0xFF;
/*      */       }
/*      */       else {
/*      */         
/*  344 */         babyVariant = this.field_70146_Z.nextInt(7);
/*      */       } 
/*      */       
/*  347 */       int j1 = this.field_70146_Z.nextInt(5);
/*      */       
/*  349 */       if (j1 < 4) {
/*      */         
/*  351 */         babyVariant |= func_110202_bQ() & 0xFF00;
/*      */       }
/*  353 */       else if (j1 < 8) {
/*      */         
/*  355 */         babyVariant |= this.mateVariant & 0xFF00;
/*      */       }
/*      */       else {
/*      */         
/*  359 */         babyVariant |= this.field_70146_Z.nextInt(5) << 8 & 0xFF00;
/*      */       } 
/*      */     } 
/*      */     
/*  363 */     EntityHorseTFC baby = new EntityHorseTFC(this.field_70170_p, this, data, babyType, babyVariant);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  369 */     double health = formula(func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b(), this.mateMaxHealth);
/*  370 */     baby.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((health > 1262.0D) ? 1252.0D : health);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  376 */     double jumpstr = formula(func_110148_a(HORSE_JUMP_STRENGTH).func_111125_b(), this.mateJumpStrength);
/*  377 */     baby.func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a((jumpstr > 1.0D) ? 1.0D : jumpstr);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  383 */     double speed = formula(func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b(), this.mateMoveSpeed);
/*  384 */     baby.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((speed > 0.3374999970197678D) ? 0.3374999970197678D : speed);
/*      */ 
/*      */     
/*  387 */     baby.func_70606_j((float)baby.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
/*  388 */     return (EntityAgeable)baby;
/*      */   }
/*      */ 
/*      */   
/*      */   private double formula(double par1, double par2) {
/*  393 */     Random random = new Random();
/*  394 */     double kk = random.nextDouble() / 2.0D + 0.05D;
/*  395 */     return (par1 + par2) / 2.0D * (0.7D + kk);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_110224_ci() {
/*  403 */     if (!this.field_70170_p.field_72995_K && func_110261_ca())
/*      */     {
/*  405 */       func_110207_m(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70628_a(boolean par1, int par2) {
/*  412 */     float ageMod = TFC_Core.getPercentGrown(this);
/*      */     
/*  414 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
/*  415 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(8) + 3) * ageMod));
/*  416 */     if (func_110167_bD()) {
/*  417 */       func_70099_a(new ItemStack(TFCItems.rope), 0.0F);
/*      */     }
/*      */     
/*  420 */     float foodWeight = ageMod * this.sizeMod * 4000.0F / 2.0F;
/*      */     
/*  422 */     TFC_Core.animalDropMeat((Entity)this, TFCItems.horseMeatRaw, foodWeight);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  428 */     super.func_70088_a();
/*  429 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/*  430 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*      */     
/*  432 */     this.field_70180_af.func_75682_a(26, Integer.valueOf(0));
/*  433 */     this.field_70180_af.func_75682_a(24, Integer.valueOf(0));
/*  434 */     this.field_70180_af.func_75682_a(25, String.valueOf("0"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void familiarize(EntityPlayer ep) {
/*  440 */     ItemStack stack = ep.func_70694_bm();
/*  441 */     if ((this.field_70153_n == null || !this.field_70153_n.equals(ep)) && !this.familiarizedToday && stack != null && isFood(stack) && canFamiliarize()) {
/*      */       
/*  443 */       if (!ep.field_71075_bZ.field_75098_d) {
/*      */         
/*  445 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*      */       }
/*      */       else {
/*      */         
/*  449 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */       } 
/*  451 */       this.hunger += 24000;
/*  452 */       this.familiarizedToday = true;
/*  453 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  454 */       func_70642_aH();
/*  455 */       ExpBonus.FEED.give(ep);
/*      */     } 
/*  457 */     if (this.field_70153_n != null && this.field_70153_n.equals(ep) && isAdult()) {
/*      */       
/*  459 */       this.familiarizedToday = true;
/*  460 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  461 */       func_70642_aH();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAggressionMod() {
/*  468 */     return this.aggressionMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnimalTypeID() {
/*  474 */     return Helper.stringToInt("horse");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getAttackedVec() {
/*  480 */     return this.attackedVec;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBirthDay() {
/*  486 */     return this.field_70180_af.func_75679_c(15);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean getBreedable() {
/*  491 */     return (this.field_70153_n == null && this.field_70154_o == null && func_110248_bS() && func_110228_bR() && 
/*  492 */       !func_110222_cv() && func_110143_aJ() >= func_110138_aP());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityHorseTFC getClosestHorse(Entity entity, double range) {
/*  499 */     double maxDistance = Double.MAX_VALUE;
/*  500 */     EntityHorseTFC closestHorse = null;
/*  501 */     List<EntityHorseTFC> list = this.field_70170_p.func_94576_a(entity, entity.field_70121_D.func_72321_a(range, range, range), HORSE_BREEDING_SELECTOR);
/*  502 */     Iterator<EntityHorseTFC> iterator = list.iterator();
/*      */     
/*  504 */     while (iterator.hasNext()) {
/*      */       
/*  506 */       EntityHorseTFC nearbyHorse = iterator.next();
/*  507 */       double distance = nearbyHorse.func_70092_e(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*  508 */       if (distance < maxDistance) {
/*      */         
/*  510 */         closestHorse = nearbyHorse;
/*  511 */         maxDistance = distance;
/*      */       } 
/*      */     } 
/*      */     
/*  515 */     return closestHorse;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDueDay() {
/*  521 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLiving getEntity() {
/*  527 */     return (EntityLiving)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFamiliarity() {
/*  532 */     return this.familiarity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFamiliarizedToday() {
/*  538 */     return this.familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getFearSource() {
/*  544 */     return this.fearSource;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IAnimal.GenderEnum getGender() {
/*  550 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*      */   }
/*      */ 
/*      */   
/*      */   public AnimalChest getHorseChest() {
/*  555 */     return this.field_110296_bG;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHunger() {
/*  561 */     return this.hunger;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInLove() {
/*  567 */     return this.inLove;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastFamiliarityUpdate() {
/*  572 */     return this.lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_110167_bD() {
/*  579 */     if (super.func_110167_bD() && func_110166_bE() instanceof EntityPlayer && ((EntityPlayer)
/*  580 */       func_110166_bE()).field_71071_by.func_70448_g() == null && func_110174_bM() != -1.0F)
/*      */     {
/*  582 */       return false;
/*      */     }
/*  584 */     return super.func_110167_bD();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_110218_cm() {
/*  590 */     return (int)(500.0F * this.aggressionMod);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double func_70042_X() {
/*  599 */     float offset = this.sizeMod * this.field_70131_O * 0.75F;
/*  600 */     int type = func_110265_bP();
/*      */     
/*  602 */     if (type == 1) {
/*  603 */       offset *= 0.87F;
/*  604 */     } else if (type == 2) {
/*  605 */       offset *= 0.92F;
/*      */     } 
/*  607 */     return offset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysToAdult() {
/*  613 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 30.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private int getNumChestSlots() {
/*  618 */     int i = func_110265_bP();
/*  619 */     return (func_110261_ca() && (i == 1 || i == 2)) ? 17 : 2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getObedienceMod() {
/*  625 */     return this.obedienceMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPregnancyRequiredTime() {
/*  630 */     return this.pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSex() {
/*  635 */     return this.sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSizeMod() {
/*  641 */     return this.sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStrengthMod() {
/*  647 */     return this.strengthMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeOfConception() {
/*  652 */     return this.timeOfConception;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleFamiliarityUpdate() {
/*  658 */     int totalDays = TFC_Time.getTotalDays();
/*  659 */     if (this.lastFamiliarityUpdate < totalDays) {
/*  660 */       if (this.familiarizedToday && this.familiarity < 100) {
/*  661 */         this.lastFamiliarityUpdate = totalDays;
/*  662 */         this.familiarizedToday = false;
/*  663 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/*  664 */         if (isAdult() && this.familiarity <= 35) {
/*      */           
/*  666 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*      */         }
/*  668 */         else if (!isAdult()) {
/*  669 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/*  670 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/*  671 */           if (this.familiarity > 70) {
/*  672 */             this.obedienceMod *= 1.01F;
/*      */           }
/*      */         }
/*      */       
/*  676 */       } else if (this.familiarity < 30) {
/*  677 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/*  678 */         this.lastFamiliarityUpdate = totalDays;
/*      */       } 
/*      */     }
/*  681 */     if (this.familiarity > 100) this.familiarity = 100; 
/*  682 */     if (this.familiarity < 0) this.familiarity = 0;
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_110198_t(int temper) {
/*  688 */     temper *= 5;
/*  689 */     int j = MathHelper.func_76125_a(func_110252_cg() + (int)(temper * this.obedienceMod * 1.0F / this.aggressionMod), 0, func_110218_cm());
/*  690 */     func_110238_s(j);
/*  691 */     return j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70085_c(EntityPlayer player) {
/*  700 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*  701 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  703 */       if (player.func_70093_af() && !this.familiarizedToday && itemstack != null && canFamiliarize()) {
/*      */         
/*  705 */         familiarize(player);
/*  706 */         return true;
/*      */       } 
/*  708 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
/*  709 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
/*  710 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*      */       }
/*      */     } 
/*      */     
/*  714 */     if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || 
/*  715 */       !canFamiliarize())) {
/*      */       
/*  717 */       if (!player.field_71075_bZ.field_75098_d)
/*  718 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this)); 
/*  719 */       this.hunger += 24000;
/*  720 */       setInLove(true);
/*  721 */       return true;
/*      */     } 
/*  723 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/*  724 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/*  725 */         itemstack.field_77994_a--;
/*      */       }
/*  727 */       return true;
/*      */     } 
/*  729 */     if (itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx)
/*      */     {
/*  731 */       return super.func_70085_c(player);
/*      */     }
/*  733 */     if (func_110248_bS() && func_110228_bR() && player.func_70093_af() && !func_110167_bD()) {
/*      */       
/*  735 */       func_110199_f(player);
/*  736 */       return true;
/*      */     } 
/*  738 */     if (func_110248_bS() && func_110228_bR() && player.func_70093_af() && func_110167_bD()) {
/*      */       
/*  740 */       func_110160_i(true, true);
/*  741 */       return true;
/*      */     } 
/*  743 */     if (func_110228_bR() && this.field_70153_n != null)
/*      */     {
/*  745 */       return super.func_70085_c(player);
/*      */     }
/*      */ 
/*      */     
/*  749 */     if (itemstack != null) {
/*      */       
/*  751 */       if (!func_110248_bS()) {
/*      */         
/*  753 */         if (itemstack.func_111282_a(player, (EntityLivingBase)this))
/*      */         {
/*  755 */           return true;
/*      */         }
/*      */         
/*  758 */         func_110231_cz();
/*      */       } 
/*      */       
/*  761 */       boolean flag = false;
/*      */       
/*  763 */       if (func_110229_cs() && !func_110261_ca() && itemstack.func_77973_b() == Item.func_150898_a(TFCBlocks.chest)) {
/*      */         
/*  765 */         func_110207_m(true);
/*  766 */         func_85030_a("mob.chickenplop", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/*  767 */         flag = true;
/*  768 */         updateChestSaddle();
/*      */       } 
/*      */       
/*  771 */       if (flag) {
/*      */         
/*  773 */         if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a == 0)
/*      */         {
/*  775 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*      */         }
/*      */         
/*  778 */         return true;
/*      */       } 
/*      */     } 
/*  781 */     if (func_110228_bR() && this.field_70153_n == null) {
/*      */       
/*  783 */       if (itemstack != null && itemstack.func_111282_a(player, (EntityLivingBase)this))
/*      */       {
/*  785 */         return true;
/*      */       }
/*      */ 
/*      */       
/*  789 */       if (func_110166_bE() instanceof EntityPlayer && func_110166_bE() == player)
/*      */       {
/*  791 */         mountHorse(player);
/*      */       }
/*  793 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  798 */     return super.func_70085_c(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAdult() {
/*  804 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70877_b(ItemStack par1ItemStack) {
/*  810 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBreedingItemTFC(ItemStack item) {
/*  815 */     return (!this.pregnant && isFood(item));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70631_g_() {
/*  821 */     return !isAdult();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFood(ItemStack item) {
/*  826 */     return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
/*  827 */       .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPregnant() {
/*  833 */     return this.pregnant;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mate(IAnimal otherAnimal) {
/*  839 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*      */       
/*  841 */       otherAnimal.mate(this);
/*  842 */       setInLove(false);
/*  843 */       func_70875_t();
/*      */       return;
/*      */     } 
/*  846 */     this.timeOfConception = TFC_Time.getTotalTicks();
/*  847 */     this.pregnant = true;
/*  848 */     func_70875_t();
/*  849 */     setInLove(false);
/*  850 */     otherAnimal.setInLove(false);
/*  851 */     this.mateSizeMod = otherAnimal.getSizeMod();
/*  852 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*  853 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/*  854 */     this.mateObedMod = otherAnimal.getObedienceMod();
/*  855 */     this.mateType = ((EntityHorse)otherAnimal).func_110265_bP();
/*  856 */     this.mateVariant = ((EntityHorse)otherAnimal).func_110202_bQ();
/*  857 */     this.mateMaxHealth = ((EntityLivingBase)otherAnimal).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
/*  858 */     this.mateJumpStrength = ((EntityLivingBase)otherAnimal).func_110148_a(HORSE_JUMP_STRENGTH).func_111125_b();
/*  859 */     this.mateMoveSpeed = ((EntityLivingBase)otherAnimal).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b();
/*      */   }
/*      */ 
/*      */   
/*      */   private void mountHorse(EntityPlayer player) {
/*  864 */     player.field_70177_z = this.field_70177_z;
/*  865 */     player.field_70125_A = this.field_70125_A;
/*  866 */     func_110227_p(false);
/*  867 */     func_110219_q(false);
/*      */     
/*  869 */     if (!this.field_70170_p.field_72995_K && checkFamiliarity(IAnimal.InteractionEnum.MOUNT, player)) {
/*  870 */       player.func_70078_a((Entity)this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70636_d() {
/*  877 */     if (this.hunger > 168000)
/*  878 */       this.hunger = 168000; 
/*  879 */     if (this.hunger > 0) {
/*  880 */       this.hunger--;
/*      */     }
/*  882 */     if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70146_Z.nextInt(600) == 0 && !this.familiarizedToday && canFamiliarize())
/*      */     {
/*  884 */       familiarize((EntityPlayer)this.field_70153_n);
/*      */     }
/*      */     
/*  887 */     syncData();
/*  888 */     if (isAdult()) {
/*  889 */       func_70873_a(0);
/*      */     } else {
/*  891 */       func_70873_a(-1);
/*      */     } 
/*  893 */     handleFamiliarityUpdate();
/*  894 */     if (!this.field_70170_p.field_72995_K && isPregnant())
/*      */     {
/*  896 */       if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*      */         
/*  898 */         EntityHorseTFC baby = (EntityHorseTFC)createChildTFC((EntityAgeable)this);
/*  899 */         baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/*  900 */         baby.field_70759_as = baby.field_70177_z;
/*  901 */         baby.field_70761_aq = baby.field_70177_z;
/*  902 */         this.field_70170_p.func_72838_d((Entity)baby);
/*  903 */         baby.setAge(TFC_Time.getTotalDays());
/*  904 */         this.pregnant = false;
/*      */       } 
/*      */     }
/*      */     
/*  908 */     if (this.field_70170_p.func_82737_E() % 20L == 0L && 
/*  909 */       isOverweight()) {
/*  910 */       func_110244_cA();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  918 */     TFC_Core.preventEntityDataUpdate = true;
/*  919 */     super.func_70636_d();
/*  920 */     TFC_Core.preventEntityDataUpdate = false;
/*      */     
/*  922 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*      */       
/*  924 */       func_70691_i(1.0F);
/*      */     }
/*  926 */     else if (this.hunger < 144000 && func_70880_s()) {
/*  927 */       setInLove(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IEntityLivingData func_110161_a(IEntityLivingData livingData) {
/*  934 */     IEntityLivingData data = super.func_110161_a(livingData);
/*  935 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1250.0D);
/*  936 */     func_70691_i(1250.0F);
/*  937 */     return data;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_110199_f(EntityPlayer player) {
/*  943 */     Entity temp = player.field_70154_o;
/*      */     
/*  945 */     if (temp == null) {
/*      */       
/*  947 */       player.field_70154_o = (Entity)this;
/*  948 */       if (player instanceof EntityPlayerMP) {
/*      */         
/*  950 */         EntityPlayerMP playerMP = (EntityPlayerMP)player;
/*  951 */         playerMP.field_71135_a.func_147359_a((Packet)new S1BPacketEntityAttach(0, (Entity)playerMP, (Entity)this));
/*  952 */         openHorseContainer(player);
/*  953 */         playerMP.field_71135_a.func_147359_a((Packet)new S1BPacketEntityAttach(0, (Entity)playerMP, temp));
/*      */       }
/*      */       else {
/*      */         
/*  957 */         openHorseContainer(player);
/*      */       } 
/*  959 */       player.field_70154_o = null;
/*      */     } 
/*      */     
/*  962 */     if (temp != null && temp == this)
/*      */     {
/*  964 */       openHorseContainer(player);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void openHorseContainer(EntityPlayer player) {
/*  970 */     if (!this.field_70170_p.field_72995_K && (this.field_70153_n == null || this.field_70153_n == player) && func_110248_bS())
/*      */     {
/*  972 */       player.openGui(TerraFirmaCraft.instance, 42, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70037_a(NBTTagCompound nbttc) {
/*  982 */     super.func_70037_a(nbttc);
/*  983 */     NBTTagCompound nbt = nbttc;
/*  984 */     this.animalID = nbt.func_74763_f("Animal ID");
/*  985 */     this.sex = nbt.func_74762_e("Sex");
/*  986 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*      */     
/*  988 */     this.familiarity = nbt.func_74762_e("Familiarity");
/*  989 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/*  990 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*      */     
/*  992 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/*  993 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/*  994 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*      */     
/*  996 */     this.hunger = nbt.func_74762_e("Hunger");
/*  997 */     this.pregnant = nbt.func_74767_n("Pregnant");
/*  998 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/*  999 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/* 1000 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/* 1001 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/* 1002 */     this.mateType = nbt.func_74762_e("MateType");
/* 1003 */     this.mateVariant = nbt.func_74762_e("MateVariant");
/* 1004 */     this.mateMaxHealth = nbt.func_74769_h("MateHealth");
/* 1005 */     this.mateJumpStrength = nbt.func_74769_h("MateJump");
/* 1006 */     this.mateMoveSpeed = nbt.func_74769_h("MateSpeed");
/* 1007 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/* 1008 */     setAge(nbt.func_74762_e("Age"));
/*      */     
/* 1010 */     if (func_110261_ca()) {
/*      */       
/* 1012 */       NBTTagList nbttaglist = nbttc.func_150295_c("Items", 10);
/* 1013 */       updateChestSaddle();
/*      */       
/* 1015 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */         
/* 1017 */         NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
/* 1018 */         int j = nbt1.func_74771_c("Slot") & 0xFF;
/*      */         
/* 1020 */         if (j >= 2 && j < this.field_110296_bG.func_70302_i_())
/*      */         {
/* 1022 */           this.field_110296_bG.func_70299_a(j, ItemStack.func_77949_a(nbt1));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1029 */     if (nbttc.func_150297_b("ArmorItem", 10)) {
/*      */       
/* 1031 */       ItemStack itemstack = ItemStack.func_77949_a(nbttc.func_74775_l("ArmorItem"));
/*      */       
/* 1033 */       if (itemstack != null && EntityHorse.func_146085_a(itemstack.func_77973_b()))
/*      */       {
/* 1035 */         this.field_110296_bG.func_70299_a(1, itemstack);
/*      */       }
/*      */     } 
/*      */     
/* 1039 */     if (nbttc.func_150297_b("SaddleItem", 10)) {
/*      */       
/* 1041 */       ItemStack itemstack = ItemStack.func_77949_a(nbttc.func_74775_l("SaddleItem"));
/* 1042 */       if (itemstack != null && itemstack.func_77973_b() == Items.field_151141_av)
/*      */       {
/* 1044 */         this.field_110296_bG.func_70299_a(0, itemstack);
/*      */       }
/*      */     }
/* 1047 */     else if (nbttc.func_74767_n("Saddle")) {
/*      */       
/* 1049 */       this.field_110296_bG.func_70299_a(0, new ItemStack(Items.field_151141_av));
/*      */     } 
/*      */     
/* 1052 */     updateSaddle();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAge(int par1) {
/* 1059 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAggressionMod(float aggressionMod) {
/* 1066 */     this.aggressionMod = aggressionMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttackedVec(Vec3 attackedVec) {
/* 1072 */     this.attackedVec = attackedVec;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBirthDay(int day) {
/* 1078 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFamiliarity(int familiarity) {
/* 1084 */     this.familiarity = familiarity;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 1089 */     this.familiarizedToday = familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFearSource(Entity fearSource) {
/* 1095 */     this.fearSource = fearSource;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70873_a(int par1) {
/* 1101 */     if (!TFC_Core.preventEntityDataUpdate)
/*      */     {
/* 1103 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHunger(int h) {
/* 1110 */     this.hunger = h;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInLove(boolean b) {
/* 1116 */     this.inLove = b;
/* 1117 */     if (b) {
/*      */       
/* 1119 */       this.field_70789_a = null;
/* 1120 */       this.field_70170_p.func_72960_a((Entity)this, (byte)18);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 1126 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObedienceMod(float obedienceMod) {
/* 1132 */     this.obedienceMod = obedienceMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/* 1137 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnant(boolean pregnant) {
/* 1142 */     this.pregnant = pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSex(int sex) {
/* 1147 */     this.sex = sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSizeMod(float sizeMod) {
/* 1153 */     this.sizeMod = sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrengthMod(float strengthMod) {
/* 1159 */     this.strengthMod = strengthMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTimeOfConception(long timeOfConception) {
/* 1164 */     this.timeOfConception = timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void syncData() {
/* 1169 */     if (this.field_70180_af != null)
/*      */     {
/* 1171 */       if (!this.field_70170_p.field_72995_K) {
/*      */         
/* 1173 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1179 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1185 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 1186 */         this.field_70180_af.func_75692_b(26, Integer.valueOf(buf.getInt()));
/* 1187 */         this.field_70180_af.func_75692_b(24, Integer.valueOf(buf.getInt()));
/* 1188 */         this.field_70180_af.func_75692_b(25, String.valueOf(this.timeOfConception));
/*      */       }
/*      */       else {
/*      */         
/* 1192 */         this.sex = this.field_70180_af.func_75679_c(13);
/*      */         
/* 1194 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 1195 */         buf.putInt(this.field_70180_af.func_75679_c(26));
/* 1196 */         buf.putInt(this.field_70180_af.func_75679_c(24));
/* 1197 */         byte[] values = buf.array();
/*      */         
/* 1199 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 1200 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 1201 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 1202 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*      */         
/* 1204 */         this.familiarity = values[4];
/* 1205 */         this.familiarizedToday = (values[5] == 1);
/* 1206 */         this.pregnant = (values[6] == 1);
/*      */ 
/*      */         
/*      */         try {
/* 1210 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(25));
/* 1211 */         } catch (NumberFormatException numberFormatException) {}
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean trySetName(String name, EntityPlayer player) {
/* 1218 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*      */       
/* 1220 */       func_94058_c(name);
/* 1221 */       return true;
/*      */     } 
/* 1223 */     func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 1224 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateChestSaddle() {
/* 1229 */     AnimalChest animalchest = this.field_110296_bG;
/* 1230 */     this.field_110296_bG = new AnimalChest("HorseChest", getNumChestSlots());
/*      */     
/* 1232 */     if (animalchest != null) {
/*      */       
/* 1234 */       animalchest.func_110132_b(this);
/* 1235 */       int i = Math.min(animalchest.func_70302_i_(), this.field_110296_bG.func_70302_i_());
/* 1236 */       for (int j = 0; j < i; j++) {
/*      */         
/* 1238 */         ItemStack itemstack = animalchest.func_70301_a(j);
/* 1239 */         if (itemstack != null)
/* 1240 */           this.field_110296_bG.func_70299_a(j, itemstack.func_77946_l()); 
/*      */       } 
/* 1242 */       animalchest = null;
/*      */     } 
/*      */     
/* 1245 */     this.field_110296_bG.func_110134_a(this);
/* 1246 */     updateSaddle();
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateSaddle() {
/* 1251 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/* 1253 */       func_110251_o((this.field_110296_bG.func_70301_a(0) != null));
/* 1254 */       if (func_110265_bP() == 0) {
/* 1255 */         func_146086_d(this.field_110296_bG.func_70301_a(1));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbttc) {
/* 1265 */     super.func_70014_b(nbttc);
/* 1266 */     nbttc.func_74768_a("Sex", this.sex);
/* 1267 */     nbttc.func_74772_a("Animal ID", this.animalID);
/* 1268 */     nbttc.func_74776_a("Size Modifier", this.sizeMod);
/*      */     
/* 1270 */     nbttc.func_74768_a("Familiarity", this.familiarity);
/* 1271 */     nbttc.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 1272 */     nbttc.func_74757_a("Familiarized Today", this.familiarizedToday);
/*      */     
/* 1274 */     NBTTagCompound nbt = nbttc;
/* 1275 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 1276 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 1277 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*      */     
/* 1279 */     nbttc.func_74768_a("Hunger", this.hunger);
/* 1280 */     nbttc.func_74757_a("Pregnant", this.pregnant);
/* 1281 */     nbttc.func_74776_a("MateSize", this.mateSizeMod);
/* 1282 */     nbttc.func_74776_a("MateStrength", this.mateStrengthMod);
/* 1283 */     nbttc.func_74776_a("MateAggro", this.mateAggroMod);
/* 1284 */     nbttc.func_74776_a("MateObed", this.mateObedMod);
/* 1285 */     nbttc.func_74768_a("MateType", this.mateType);
/* 1286 */     nbttc.func_74768_a("MateVariant", this.mateVariant);
/* 1287 */     nbttc.func_74780_a("MateHealth", this.mateMaxHealth);
/* 1288 */     nbttc.func_74780_a("MateJump", this.mateJumpStrength);
/* 1289 */     nbttc.func_74780_a("MateSpeed", this.mateMoveSpeed);
/* 1290 */     nbttc.func_74772_a("ConceptionTime", this.timeOfConception);
/* 1291 */     nbttc.func_74768_a("Age", getBirthDay());
/*      */     
/* 1293 */     if (func_110261_ca()) {
/*      */       
/* 1295 */       NBTTagList nbttaglist = new NBTTagList();
/*      */       
/* 1297 */       for (int i = 2; i < this.field_110296_bG.func_70302_i_(); i++) {
/*      */         
/* 1299 */         ItemStack itemstack = this.field_110296_bG.func_70301_a(i);
/*      */         
/* 1301 */         if (itemstack != null) {
/*      */           
/* 1303 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 1304 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 1305 */           itemstack.func_77955_b(nbttagcompound1);
/* 1306 */           nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */         } 
/*      */       } 
/*      */       
/* 1310 */       nbttc.func_74782_a("Items", (NBTBase)nbttaglist);
/*      */     } 
/*      */     
/* 1313 */     if (this.field_110296_bG.func_70301_a(1) != null)
/*      */     {
/* 1315 */       nbttc.func_74782_a("ArmorItem", (NBTBase)this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
/*      */     }
/*      */     
/* 1318 */     if (this.field_110296_bG.func_70301_a(0) != null)
/*      */     {
/* 1320 */       nbttc.func_74782_a("SaddleItem", (NBTBase)this.field_110296_bG.func_70301_a(0).func_77955_b(new NBTTagCompound()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean func_70692_ba() {
/* 1327 */     return !func_110248_bS();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOverweight() {
/* 1332 */     return (getWeight() > 80000);
/*      */   }
/*      */   
/*      */   public int getWeight() {
/* 1336 */     ItemStack[] inv = new ItemStack[getHorseChest().func_70302_i_()];
/* 1337 */     for (int i = 0; i < getHorseChest().func_70302_i_(); ) { inv[i] = getHorseChest().func_70301_a(i); i++; }
/* 1338 */      InventoryWeight iv = new InventoryWeight(inv);
/* 1339 */     return iv.getWeight();
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Entities\Mobs\EntityHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */