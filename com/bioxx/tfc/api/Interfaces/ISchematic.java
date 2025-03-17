package com.bioxx.tfc.api.Interfaces;

import net.minecraft.nbt.NBTTagList;

public interface ISchematic {
  boolean load();
  
  short getSizeY();
  
  void setSizeY(short paramShort);
  
  short getSizeX();
  
  void setSizeX(short paramShort);
  
  short getSizeZ();
  
  void setSizeZ(short paramShort);
  
  int[] getBlockArray();
  
  void setBlockArray(int[] paramArrayOfint);
  
  byte[] getDataArray();
  
  void setDataArray(byte[] paramArrayOfbyte);
  
  NBTTagList getTileEntities();
  
  void setTileEntities(NBTTagList paramNBTTagList);
  
  NBTTagList getEntities();
  
  void setEntities(NBTTagList paramNBTTagList);
  
  String getPath();
  
  void setPath(String paramString);
  
  int getCenterX();
  
  int getCenterZ();
  
  int getIndex();
}


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\api\Interfaces\ISchematic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */