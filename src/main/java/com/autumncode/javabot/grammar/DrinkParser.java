package com.autumncode.javabot.grammar;

import com.github.fge.grappa.parsers.BaseActions;
import com.github.fge.grappa.rules.Rule;
import com.google.common.annotations.VisibleForTesting;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrinkParser extends TestParser<Object> {
    protected static final Collection<String> POSSIBLE_VESSELS
        = Stream.of(Vessel.values()).map(Enum::name).collect(Collectors.toList());


    /**
     * "Utility" rule: run the given Runnable, and always return true
     *
     * <p>Parsers have two types of methods: rules, to match text, and actions,
     * for anything else.</p>
     *
     * <p>An action always has the parsing context available; and among the
     * information in the context is the match of the last rule.</p>
     *
     * @param runnable the runnable
     * @return always true
     */
    public boolean run(final Runnable runnable)
    {
        runnable.run();
        return true;
    }

    /**
     * Matcher for a vessel
     *
     * <p>We have built the list of possible enum values, now use it.</p>
     *
     * <p>We want to recognize any vessel: we can use a trie for that. And we
     * want to ignore the case.</p>
     *
     * @return a rule to match a vessel
     */
    @VisibleForTesting
    protected Rule matchVessel()
    {
        return trieIgnoreCase(POSSIBLE_VESSELS);
    }

    /**
     * Inject the vessel into the drink order
     *
     * <p>It supposes of course that the first element in the stack, which we
     * {@link BaseActions#peek() peek}, is a Drink order.</p>
     *
     * <p>In order:</p>
     *
     * <ul>
     *     <li>match a vessel (using the {@link #matchVessel()} rule above),</li>
     *     <li>grab the match, make it upper case,</li>
     *     <li>grab the order,</li>
     *     <li>assign the vessel.</li>
     * </ul>
     *
     * @return a rule
     */
    public Rule vessel()
    {
        return sequence(
            matchVessel(),
            push(Vessel.valueOf(match().toUpperCase()))
        );
    }
}
