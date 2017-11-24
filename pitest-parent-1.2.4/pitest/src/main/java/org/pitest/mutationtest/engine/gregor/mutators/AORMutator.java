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

public enum AORMutator implements MethodMutatorFactory {
    AOR_MUTATOR;
    @Override
    public MethodVisitor create(MutationContext context, MethodInfo methodInfo, MethodVisitor methodVisitor) {
        return new AORMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor extends AbstractInsnMutator {
    
    AORMethodVisitor(MethodMutatorFactory factory, MethodInfo methodInfo, MutationContext context,
            MethodVisitor delegateMethodVisitor) {
        super(factory, methodInfo, context, delegateMethodVisitor);
    }
    
    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();
    
    static {
        //integer
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL,
                "Replaced integer addition with multiplication"));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV,
                "Replaced integer subtraction with division"));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IADD,
                "Replaced integer multiplication with addition"));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB,
                "Replaced integer division with subtraction"));
        
        //longs
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL,
                "Replaced long addition with multiplication"));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV,
                "Replaced long subtraction with division"));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD,
                "Replaced long multiplication with addition"));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB,
                "Replaced long division with subtraction"));
        
        
        //floats
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL,
                "Replaced float addition with multiplication"));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV,
                "Replaced float subtraction with division"));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD,
                "Replaced float multiplication with addition"));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB,
                "Replaced float division with subtraction"));
        
        //double
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL,
                "Replaced double addition with multiplication"));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV,
                "Replaced double subtraction with division"));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD,
                "Replaced double multiplication with addition"));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB,
                "Replaced double division with subtraction"));

    }
    
    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        // TODO Auto-generated method stub
        return MUTATIONS;
    }
    
}
