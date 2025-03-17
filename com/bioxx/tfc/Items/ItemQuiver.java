/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Enums.EnumAmmo;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.Interfaces.IQuiverAmmo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemQuiver
/*     */   extends ItemTerra
/*     */   implements IEquipable
/*     */ {
/*     */   public ItemQuiver() {
/*  30 */     func_77637_a(TFCTabs.TFC_ARMOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_77618_c(int par1, int par2) {
/*  38 */     return this.field_77791_bV;
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
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack is, World world, Entity entity, int i, boolean isSelected) {
/*  52 */     super.func_77663_a(is, world, entity, i, isSelected);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
/*  58 */     entityplayer.openGui(TerraFirmaCraft.instance, 40, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
/*  59 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  65 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:quiver");
/*     */   }
/*     */   
/*     */   public int getQuiverArrows(ItemStack item) {
/*  69 */     int n = 0;
/*  70 */     ItemStack[] inventory = loadInventory(item);
/*  71 */     for (ItemStack i : inventory) {
/*  72 */       if (i != null && (i.func_77973_b() instanceof ItemArrow || (i
/*  73 */         .func_77973_b() instanceof IQuiverAmmo && ((IQuiverAmmo)i.func_77973_b()).getAmmoType() == EnumAmmo.ARROW)))
/*  74 */         n += i.field_77994_a; 
/*     */     } 
/*  76 */     return n;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getQuiverJavelins(ItemStack item) {
/*  81 */     int n = 0;
/*  82 */     ItemStack[] inventory = loadInventory(item);
/*  83 */     for (ItemStack i : inventory) {
/*  84 */       if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin)
/*  85 */         n += i.field_77994_a; 
/*     */     } 
/*  87 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List[] getQuiverJavelinTypes(ItemStack item) {
/*  94 */     ArrayList[] pair = new ArrayList[2];
/*  95 */     ArrayList<String> list = new ArrayList<String>();
/*  96 */     ArrayList<Integer> listNum = new ArrayList<Integer>();
/*  97 */     ItemStack[] inventory = loadInventory(item);
/*  98 */     for (ItemStack i : inventory) {
/*     */       
/* 100 */       if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
/*     */         
/* 102 */         String s = i.func_77973_b().func_77653_i(i);
/* 103 */         if (!list.contains(s))
/* 104 */           list.add(s); 
/* 105 */         int n = list.indexOf(s);
/* 106 */         if (listNum.size() == n)
/* 107 */           listNum.add(Integer.valueOf(0)); 
/* 108 */         listNum.set(n, Integer.valueOf(((Integer)listNum.get(n)).intValue() + 1));
/*     */       } 
/*     */     } 
/* 111 */     pair[0] = list;
/* 112 */     pair[1] = listNum;
/* 113 */     return (List[])pair;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 119 */     ItemTerra.addSizeInformation(is, arraylist);
/*     */     
/* 121 */     if (TFC_Core.showShiftInformation()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.Advanced") + ":");
/* 129 */       arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Bow.Arrows") + ": " + EnumChatFormatting.YELLOW + getQuiverArrows(is));
/* 130 */       arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Bow.Javelins") + ": " + EnumChatFormatting.YELLOW + getQuiverJavelins(is));
/* 131 */       List[] javData = getQuiverJavelinTypes(is);
/* 132 */       for (int i = 0; i < javData[0].size(); i++) {
/*     */         
/* 134 */         String s = javData[0].get(i);
/* 135 */         int n = ((Integer)javData[1].get(i)).intValue();
/* 136 */         arraylist.add(EnumChatFormatting.ITALIC + "  -" + s + ": " + EnumChatFormatting.YELLOW + n);
/*     */       } 
/* 138 */       if (is.func_77942_o()) {
/*     */         
/* 140 */         NBTTagCompound stackTagCompound = is.func_77978_p();
/* 141 */         if (stackTagCompound.func_74764_b("creator")) {
/* 142 */           arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Armor.ForgedBy") + " " + stackTagCompound.func_74779_i("creator"));
/*     */         }
/*     */       } 
/*     */     } else {
/* 146 */       arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.Advanced") + ": (" + TFC_Core.translate("gui.Hold") + " " + EnumChatFormatting.GRAY + TFC_Core.translate("gui.Shift") + EnumChatFormatting.DARK_GRAY + ")");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack addItem(ItemStack quiver, ItemStack ammo) {
/* 153 */     ItemStack[] inventory = loadInventory(quiver);
/* 154 */     for (int i = 0; i < inventory.length && ammo != null; i++) {
/*     */       
/* 156 */       if (inventory[i] != null && inventory[i].func_77969_a(ammo)) {
/*     */         
/* 158 */         if (ammo.field_77994_a + (inventory[i]).field_77994_a <= ammo.func_77976_d())
/*     */         {
/* 160 */           (inventory[i]).field_77994_a += ammo.field_77994_a;
/* 161 */           ammo = null;
/*     */         }
/* 163 */         else if (ammo.field_77994_a + (inventory[i]).field_77994_a > ammo.func_77976_d())
/*     */         {
/* 165 */           int diff = ammo.func_77976_d() - (inventory[i]).field_77994_a;
/* 166 */           (inventory[i]).field_77994_a = ammo.func_77976_d();
/* 167 */           ammo.field_77994_a -= diff;
/*     */         }
/*     */       
/* 170 */       } else if (inventory[i] == null) {
/*     */         
/* 172 */         inventory[i] = ammo.func_77946_l();
/* 173 */         ammo = null;
/*     */       } 
/*     */     } 
/* 176 */     saveInventory(quiver, inventory);
/* 177 */     return ammo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack consumeAmmo(ItemStack quiver, EnumAmmo ammoType, boolean shouldConsume) {
/* 182 */     ItemStack[] inventory = loadInventory(quiver);
/* 183 */     for (int i = 0; i < inventory.length; i++) {
/*     */       
/* 185 */       if (inventory[i] != null && inventory[i].func_77973_b() instanceof IQuiverAmmo && ((IQuiverAmmo)inventory[i].func_77973_b()).getAmmoType() == ammoType) {
/*     */         
/* 187 */         ItemStack out = inventory[i].func_77946_l();
/* 188 */         out.field_77994_a = 1;
/* 189 */         if (shouldConsume)
/* 190 */           (inventory[i]).field_77994_a--; 
/* 191 */         if ((inventory[i]).field_77994_a <= 0)
/* 192 */           inventory[i] = null; 
/* 193 */         saveInventory(quiver, inventory);
/* 194 */         return out;
/*     */       } 
/*     */     } 
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] loadInventory(ItemStack quiver) {
/* 202 */     ItemStack[] inventory = new ItemStack[8];
/* 203 */     NBTTagCompound nbt = quiver.func_77978_p();
/* 204 */     if (nbt != null && nbt.func_74764_b("Items")) {
/*     */       
/* 206 */       NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*     */       
/* 208 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */         
/* 210 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 211 */         byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 212 */         if (byte0 >= 0 && byte0 < 8)
/* 213 */           inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */       } 
/*     */     } 
/* 216 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveInventory(ItemStack quiver, ItemStack[] inventory) {
/* 221 */     NBTTagList nbttaglist = new NBTTagList();
/* 222 */     for (int i = 0; i < inventory.length; i++) {
/*     */       
/* 224 */       if (inventory[i] != null) {
/*     */         
/* 226 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 227 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 228 */         inventory[i].func_77955_b(nbttagcompound1);
/* 229 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 232 */     if (quiver != null) {
/*     */       
/* 234 */       if (!quiver.func_77942_o())
/* 235 */         quiver.func_77982_d(new NBTTagCompound()); 
/* 236 */       quiver.func_77978_p().func_74782_a("Items", (NBTBase)nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IEquipable.EquipType getEquipType(ItemStack is) {
/* 243 */     return IEquipable.EquipType.BACK;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEquippedRender() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTooHeavyToCarry(ItemStack is) {
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77639_j() {
/* 259 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 265 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\Items\ItemQuiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */