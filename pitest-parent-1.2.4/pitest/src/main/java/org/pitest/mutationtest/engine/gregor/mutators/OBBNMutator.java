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

public enum OBBNMutator implements MethodMutatorFactory {

    OBBN_MUTATOR;
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        return new OBBNMethodVisitor(this, methodInfo, context, methodVisitor);
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

class OBBNMethodVisitor extends AbstractInsnMutator {
    public OBBNMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo,
            final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();
    static {
        MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND, "Replace bitwise | with &"));
        MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR, "Replace bitwise & with |"));

        MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND, "Replace bitwise | with &"));
        MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR, "Replace bitwise & with |"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}