package com.bioxx.tfc.api.Interfaces;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFood {
  EnumFoodGroup getFoodGroup();
  
  int getFoodID();
  
  float getDecayRate(ItemStack paramItemStack);
  
  @Deprecated
  float getFoodWeight(ItemStack paramItemStack);
  
  float getFoodMaxWeight(ItemStack paramItemStack);
  
  ItemStack onDecayed(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  boolean isEdible(ItemStack paramItemStack);
  
  boolean isUsable(ItemStack paramItemStack);
  
  int getTasteSweet(ItemStack paramItemStack);
  
  int getTasteSour(ItemStack paramItemStack);
  
  int getTasteSalty(ItemStack paramItemStack);
  
  int getTasteBitter(ItemStack paramItemStack);
  
  int getTasteSavory(ItemStack paramItemStack);
  
  boolean renderDecay();
  
  boolean renderWeight();
}


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Interfaces\IFood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */