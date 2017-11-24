/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum UOIMutator implements MethodMutatorFactory {
    
    UOI_MUTATOR;
    
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new UOIVartoUnaryMethodVisitor(this, context, methodVisitor);
    }
    
    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }
    
    @Override
    public String getName() {
        return name();
    }
}

class UOIVartoUnaryMethodVisitor extends MethodVisitor {
    
    private final MethodMutatorFactory factory;
    private final MutationContext context;
    
    UOIVartoUnaryMethodVisitor(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }
    
    @Override
    public void visitVarInsn(final int opcode, final int var) {
        
        if (opcode == Opcodes.ISTORE) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory,
                    "Increment variable from " + opcode);
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.ICONST_1);
                super.visitInsn(Opcodes.IADD);
            }
        } else if (opcode == Opcodes.LSTORE) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory,
                    "Increment variable from " + opcode);
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.LCONST_1);
                super.visitInsn(Opcodes.LADD);
            }
        } else if (opcode == Opcodes.DSTORE) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory,
                    "Increment variable from " + opcode);
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.DCONST_1);
                super.visitInsn(Opcodes.DADD);
            }
        } else if (opcode == Opcodes.FSTORE) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory,
                    "Increment variable from " + opcode);
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.FCONST_1);
                super.visitInsn(Opcodes.FADD);
            }
        }
        
        super.visitVarInsn(opcode, var);
        
    }
    
    @Override
    public void visitIincInsn(final int var, final int increment) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Changed increment from " + increment + " to "
              + "0");
      if (this.context.shouldMutate(newId)) {
        this.mv.visitIincInsn(var, 0);
      } else {
        this.mv.visitIincInsn(var, 0);
      }
    }
    
}