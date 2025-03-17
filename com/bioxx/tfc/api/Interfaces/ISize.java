package com.bioxx.tfc.api.Interfaces;

import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import net.minecraft.item.ItemStack;

public interface ISize {
  EnumSize getSize(ItemStack paramItemStack);
  
  EnumWeight getWeight(ItemStack paramItemStack);
  
  EnumItemReach getReach(ItemStack paramItemStack);
  
  boolean canStack();
}


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Interfaces\ISize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */