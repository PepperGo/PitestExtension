/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.pitest.functional.prelude.Prelude;
import org.pitest.help.Help;
import org.pitest.help.PitHelpError;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.mutators.ABSMutator;
import org.pitest.mutationtest.engine.gregor.mutators.AODMutator;
import org.pitest.mutationtest.engine.gregor.mutators.AODMutatorSecond;
import org.pitest.mutationtest.engine.gregor.mutators.AORMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ArgumentPropagationMutator;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConstructorCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InlineConstantMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InvertNegsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.MathMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NonVoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.OBBNMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RORMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator.Choice;
import org.pitest.mutationtest.engine.gregor.mutators.ReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.UOIMutator;
import org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.NakedReceiverMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveIncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveSwitchMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator;

public final class Mutator {

    private static final Map<String, Iterable<MethodMutatorFactory>> MUTATORS = new LinkedHashMap<String, Iterable<MethodMutatorFactory>>();

    static {

        /**
         * Default mutator that inverts the negation of integer and floating point
         * numbers.
         */
        add("INVERT_NEGS", InvertNegsMutator.INVERT_NEGS_MUTATOR);

        /**
         * Default mutator that mutates the return values of methods.
         */
        add("RETURN_VALS", ReturnValsMutator.RETURN_VALS_MUTATOR);

        /**
         * Optional mutator that mutates integer and floating point inline constants.
         */
        add("INLINE_CONSTS", new InlineConstantMutator());

        /**
         * Default mutator that mutates binary arithmetic operations.
         */
        add("MATH", MathMutator.MATH_MUTATOR);

        /**
         * Default mutator that removes method calls to void methods.
         *
         */
        add("VOID_METHOD_CALLS", VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR);

        /**
         * Default mutator that negates conditionals.
         */
        add("NEGATE_CONDITIONALS", NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR);

        /**
         * Default mutator that replaces the relational operators with their boundary
         * counterpart.
         */
        add("CONDITIONALS_BOUNDARY", ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR);

        /**
         * Default mutator that mutates increments, decrements and assignment increments
         * and decrements of local variables.
         */
        add("INCREMENTS", IncrementsMutator.INCREMENTS_MUTATOR);

        /**
         * Optional mutator that removes local variable increments.
         */

        add("REMOVE_INCREMENTS", RemoveIncrementsMutator.REMOVE_INCREMENTS_MUTATOR);

        /**
         * Optional mutator that removes method calls to non void methods.
         */
        add("NON_VOID_METHOD_CALLS", NonVoidMethodCallMutator.NON_VOID_METHOD_CALL_MUTATOR);

        /**
         * Optional mutator that replaces constructor calls with null values.
         */
        add("CONSTRUCTOR_CALLS", ConstructorCallMutator.CONSTRUCTOR_CALL_MUTATOR);

        /**
         * Removes conditional statements so that guarded statements always execute The
         * EQUAL version ignores LT,LE,GT,GE, which is the default behavior, ORDER
         * version mutates only those.
         */

        add("REMOVE_CONDITIONALS_EQ_IF", new RemoveConditionalMutator(Choice.EQUAL, true));
        add("REMOVE_CONDITIONALS_EQ_ELSE", new RemoveConditionalMutator(Choice.EQUAL, false));
        add("REMOVE_CONDITIONALS_ORD_IF", new RemoveConditionalMutator(Choice.ORDER, true));
        add("REMOVE_CONDITIONALS_ORD_ELSE", new RemoveConditionalMutator(Choice.ORDER, false));
        addGroup("REMOVE_CONDITIONALS", RemoveConditionalMutator.makeMutators());

        /**
         * Experimental mutator that removed assignments to member variables.
         */
        add("EXPERIMENTAL_MEMBER_VARIABLE",
                new org.pitest.mutationtest.engine.gregor.mutators.experimental.MemberVariableMutator());

        /**
         * Experimental mutator that swaps labels in switch statements
         */
        add("EXPERIMENTAL_SWITCH", new org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator());

        /**
         * Experimental mutator that replaces method call with one of its parameters of
         * matching type
         */
        add("EXPERIMENTAL_ARGUMENT_PROPAGATION", ArgumentPropagationMutator.ARGUMENT_PROPAGATION_MUTATOR);

        /**
         * Experimental mutator that replaces method call with this
         */
        add("EXPERIMENTAL_NAKED_RECEIVER", NakedReceiverMutator.NAKED_RECEIVER);

        /* new mutators add here */
        add("OBBN", OBBNMutator.OBBN_MUTATOR);
        add("ROR", RORMutator.ROR_MUTATOR);
        add("AOD", AODMutator.AOD_MUTATOR);
        add("AOD2", AODMutatorSecond.AOD_MUTATOR_2);
        add("AOR", AORMutator.AOR_MUTATOR);
        add("UOI", UOIMutator.UOI_MUTATOR);
        add("ABS", ABSMutator.ABS_Mutator);
        add("CRCR", new CRCRMutator());

        addGroup("REMOVE_SWITCH", RemoveSwitchMutator.makeMutators());
        addGroup("DEFAULTS", defaults());
        addGroup("STRONGER", stronger());
        addGroup("ALL", all());
    }

    public static Collection<MethodMutatorFactory> all() {
        return fromStrings(MUTATORS.keySet());
    }

    private static Collection<MethodMutatorFactory> stronger() {
        return combine(defaults(), group(new RemoveConditionalMutator(Choice.EQUAL, false), new SwitchMutator()));
    }

    private static Collection<MethodMutatorFactory> combine(Collection<MethodMutatorFactory> a,
            Collection<MethodMutatorFactory> b) {
        List<MethodMutatorFactory> l = new ArrayList<MethodMutatorFactory>(a);
        l.addAll(b);
        return l;
    }

    /**
     * Default set of mutators - designed to provide balance between strength and
     * performance
     */
    public static Collection<MethodMutatorFactory> defaults() {
        return group(InvertNegsMutator.INVERT_NEGS_MUTATOR, ReturnValsMutator.RETURN_VALS_MUTATOR,
                MathMutator.MATH_MUTATOR, VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR,
                NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR,
                ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR, IncrementsMutator.INCREMENTS_MUTATOR);
    }

    private static Collection<MethodMutatorFactory> group(final MethodMutatorFactory... ms) {
        return Arrays.asList(ms);
    }

    public static Collection<MethodMutatorFactory> byName(final String name) {
        return FCollection.map(MUTATORS.get(name), Prelude.id(MethodMutatorFactory.class));
    }

    private static void add(final String key, final MethodMutatorFactory value) {
        MUTATORS.put(key, Collections.singleton(value));
    }

    private static void addGroup(final String key, final Iterable<MethodMutatorFactory> value) {
        MUTATORS.put(key, value);
    }

    public static Collection<MethodMutatorFactory> fromStrings(final Collection<String> names) {
        final Set<MethodMutatorFactory> unique = new TreeSet<MethodMutatorFactory>(compareId());

        FCollection.flatMapTo(names, fromString(), unique);
        return unique;
    }

    private static Comparator<? super MethodMutatorFactory> compareId() {
        return new Comparator<MethodMutatorFactory>() {
            @Override
            public int compare(final MethodMutatorFactory o1, final MethodMutatorFactory o2) {
                return o1.getGloballyUniqueId().compareTo(o2.getGloballyUniqueId());
            }
        };
    }

    private static F<String, Iterable<MethodMutatorFactory>> fromString() {
        return new F<String, Iterable<MethodMutatorFactory>>() {
            @Override
            public Iterable<MethodMutatorFactory> apply(final String a) {
                Iterable<MethodMutatorFactory> i = MUTATORS.get(a);
                if (i == null) {
                    throw new PitHelpError(Help.UNKNOWN_MUTATOR, a);
                }
                return i;
            }
        };
    }

}
