package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum AODMutator implements MethodMutatorFactory {

    AOD_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        return new AODMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AODMethodVisitor extends AbstractInsnMutator {

    AODMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context,
            final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final String DESCRIPTION = "Replaced an arithmetic expression by one of the operand";
    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, DESCRIPTION));

        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP, DESCRIPTION));

        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP, DESCRIPTION));

        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, DESCRIPTION));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}
