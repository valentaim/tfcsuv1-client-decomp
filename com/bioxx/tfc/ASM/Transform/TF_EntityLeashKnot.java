/*    */ package com.bioxx.tfc.ASM.Transform;
/*    */ 
/*    */ import com.bioxx.tfc.ASM.ClassTransformer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.objectweb.asm.tree.AbstractInsnNode;
/*    */ import org.objectweb.asm.tree.InsnList;
/*    */ import org.objectweb.asm.tree.MethodInsnNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TF_EntityLeashKnot
/*    */   extends ClassTransformer
/*    */ {
/*    */   public TF_EntityLeashKnot() {
/* 24 */     List<ClassTransformer.InstrSet> nodes = new ArrayList<ClassTransformer.InstrSet>();
/* 25 */     InsnList list = new InsnList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ServerOverrides", "isValidSurface", "(Lnet/minecraft/block/Block;)I"));
/*    */     
/* 37 */     nodes.add(new ClassTransformer.InstrSet(this, list, 11, ClassTransformer.InstrOpType.Replace));
/*    */     
/* 39 */     this.mcpMethodNodes.put("onValidSurface | ()Z", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/* 40 */     this.obfMethodNodes.put("e | ()Z", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\Source_other\[1.7.10]TerraFirmaCraft_client.jar!\com\bioxx\tfc\ASM\Transform\TF_EntityLeashKnot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */