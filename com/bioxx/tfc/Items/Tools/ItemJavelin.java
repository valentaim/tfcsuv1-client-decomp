/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Items.ItemQuiver;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumAmmo;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.IProjectile;
/*     */ import com.bioxx.tfc.api.Interfaces.IQuiverAmmo;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemJavelin
/*     */   extends ItemTerraTool
/*     */   implements ICausesDamage, IProjectile, IQuiverAmmo
/*     */ {
/*     */   public float weaponDamage;
/*     */   private float weaponRangeDamage;
/*     */   
/*     */   public ItemJavelin(Item.ToolMaterial par2EnumToolMaterial, float damage) {
/*  42 */     super(10.0F, par2EnumToolMaterial, Sets.newHashSet((Object[])new Block[] { Blocks.field_150350_a }));
/*  43 */     this.field_77777_bU = 1;
/*  44 */     this.weaponDamage = damage;
/*  45 */     this.weaponRangeDamage = damage;
/*  46 */     func_77656_e(par2EnumToolMaterial.func_77997_a() / 2);
/*  47 */     func_77637_a(TFCTabs.TFC_WEAPONS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*  53 */     list.add(new ItemStack((Item)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  65 */     String name = func_77658_a().replace("item.", "");
/*  66 */     name = name.replace("IgIn ", "");
/*  67 */     name = name.replace("IgEx ", "");
/*  68 */     name = name.replace("Sed ", "");
/*  69 */     name = name.replace("MM ", "");
/*  70 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77619_b() {
/*  79 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack) {
/*  88 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack par1ItemStack) {
/*  97 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 103 */     Block b = world.func_147439_a(x, y, z);
/* 104 */     return (b == TFCBlocks.toolRack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 113 */     par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
/* 114 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int itemInUseCount) {
/* 123 */     if (!world.field_72995_K) {
/*     */       
/* 125 */       int var6 = func_77626_a(itemstack) - itemInUseCount;
/* 126 */       float force = Math.min(var6 / 20.0F, 1.0F);
/*     */       
/* 128 */       EntityJavelin javelin = new EntityJavelin(world, (EntityLivingBase)player, 1.5F * force);
/* 129 */       javelin.func_70239_b(getRangedDamage(itemstack));
/* 130 */       javelin.duraBuff = AnvilManager.getDurabilityBuff(itemstack);
/* 131 */       javelin.damageBuff = AnvilManager.getDamageBuff(itemstack);
/*     */       
/* 133 */       int var9 = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, itemstack);
/*     */       
/* 135 */       if (var9 > 0)
/*     */       {
/* 137 */         javelin.func_70239_b(javelin.func_70242_d() + var9 * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 140 */       int var10 = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, itemstack);
/*     */       
/* 142 */       if (var10 > 0)
/*     */       {
/* 144 */         javelin.func_70239_b(javelin.func_70242_d() + var10);
/*     */       }
/*     */       
/* 147 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, itemstack) > 0)
/*     */       {
/* 149 */         javelin.func_70015_d(100);
/*     */       }
/*     */       
/* 152 */       world.func_72956_a((Entity)player, "random.bow", 1.0F, 0.3F);
/* 153 */       javelin.setDamageTaken((short)itemstack.func_77960_j());
/* 154 */       javelin.setPickupItem(itemstack.func_77973_b());
/*     */       
/* 156 */       player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
/*     */       
/* 158 */       if (!consumeJavelin(player))
/*     */       {
/* 160 */         player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = consumeJavelinInQuiver(player, true);
/*     */       }
/*     */       
/* 163 */       if (!world.field_72995_K)
/*     */       {
/* 165 */         world.func_72838_d((Entity)javelin);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getInventorySlotContainJavelin(EntityPlayer player) {
/* 172 */     for (int j = 0; j < player.field_71071_by.field_70462_a.length; j++) {
/*     */       
/* 174 */       if (player.field_71071_by.field_70462_a[j] != null && player.field_71071_by.field_70462_a[j].func_77973_b() instanceof ItemJavelin)
/*     */       {
/* 176 */         return j;
/*     */       }
/*     */     } 
/*     */     
/* 180 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack consumeJavelinInQuiver(EntityPlayer player, boolean shouldConsume) {
/* 185 */     ItemStack quiver = player.field_71071_by.func_70301_a(36);
/* 186 */     if (quiver != null && quiver.func_77973_b() instanceof ItemQuiver)
/* 187 */       return ((ItemQuiver)quiver.func_77973_b()).consumeAmmo(quiver, EnumAmmo.JAVELIN, shouldConsume); 
/* 188 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean consumeJavelin(EntityPlayer player) {
/* 193 */     int active = player.field_71071_by.field_70461_c;
/* 194 */     int nextJav = getInventorySlotContainJavelin(player);
/*     */     
/* 196 */     if (nextJav < 0) {
/*     */       
/* 198 */       player.field_71071_by.field_70462_a[active] = null;
/* 199 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 203 */     player.field_71071_by.field_70462_a[active] = player.field_71071_by.field_70462_a[nextJav].func_77946_l();
/* 204 */     if (--(player.field_71071_by.field_70462_a[nextJav]).field_77994_a <= 0)
/*     */     {
/* 206 */       player.field_71071_by.field_70462_a[nextJav] = null;
/*     */     }
/*     */     
/* 209 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77662_d() {
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDamageType getDamageType() {
/* 221 */     return EnumDamageType.PIERCING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRangedDamage(ItemStack is) {
/* 227 */     if (is != null) {
/* 228 */       return this.weaponRangeDamage + this.weaponRangeDamage * AnvilManager.getDamageBuff(is);
/*     */     }
/* 230 */     return this.weaponRangeDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap getAttributeModifiers(ItemStack stack) {
/* 236 */     HashMultimap hashMultimap = HashMultimap.create();
/* 237 */     hashMultimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", getWeaponDamage(stack), 0));
/* 238 */     return (Multimap)hashMultimap;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getWeaponDamage(ItemStack is) {
/* 243 */     return Math.floor((this.weaponDamage + this.weaponDamage * AnvilManager.getDamageBuff(is)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack is) {
/* 249 */     return (int)Math.floor((func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(is)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAmmo getAmmoType() {
/* 255 */     return EnumAmmo.JAVELIN;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 260 */     return EnumItemReach.FAR;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\Tools\ItemJavelin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */