package org.pitest.mutationtest.engine.gregor.mutators;

import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ABSMutator implements MethodMutatorFactory {
    ABS_Mutator;

    @Override
    public MethodVisitor create(final MutationContext context,
        final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
      return new ABSMethodVisitor(this, context, methodVisitor);
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


class ABSMethodVisitor extends MethodVisitor {
    private final MethodMutatorFactory factory;
    private final MutationContext      context;

    ABSMethodVisitor(final MethodMutatorFactory factory,
        final MutationContext context, final MethodVisitor delegateMethodVisitor) {
      super(Opcodes.ASM6, delegateMethodVisitor);
      this.factory = factory;
      this.context = context;
    }
    
    @Override
    public void visitVarInsn(final int opcode, final int var) {
        
        super.visitVarInsn(opcode, var);
        
        if (opcode == Opcodes.ILOAD ) {
            final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Negate variable ");
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.INEG);
            }
        } else if (opcode == Opcodes.DLOAD) {
            final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Negate variable ");
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.DNEG);
            }
        } else if (opcode == Opcodes.FLOAD) {
            final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Negate variable ");
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.FNEG);
            }
        } else if (opcode == Opcodes.LLOAD) {
            final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Negate variable ");
            if (this.context.shouldMutate(newId)) {
                super.visitInsn(Opcodes.LNEG);
            }
        }
        
     }
    
}
