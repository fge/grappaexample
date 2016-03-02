package com.autumncode.javabot.grammar.helpers;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.matchers.base.Matcher;
import com.github.fge.grappa.parsers.BaseParser;
import com.github.fge.grappa.rules.Rule;
import com.github.fge.grappa.run.context.MatcherContext;
import com.google.common.base.Function;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Iterator;

@Test
public abstract class ParserTestBase<T, P extends BaseParser<T>>
{
    protected final Matcher matcher;

    protected ParserTestBase(final Class<P> parserClass,
        final Function<P, Rule> function)
    {
        final P parser = Grappa.createParser(parserClass);
        matcher = (Matcher) function.apply(parser);
    }

    public abstract Collection<ParsingData<T>> parsingData();

    @DataProvider
    protected Iterator<Object[]> getParsingData()
    {
        return parsingData().stream().map(ParsingData::toArray).iterator();
    }

    @Test
    public void matchTest(final String input, final boolean match,
        final int bufferIndex, final T stackValue)
    {
        final MatcherContext<T> context = new MatcherContextBuilder<T>()
            .withInput(input)
            .withIndex(0)
            .withMatcher(matcher)
            .build();

        try (
            final AutoCloseableSoftAssertions soft
                = new AutoCloseableSoftAssertions();
        ) {
            soft.assertThat(matcher.match(context))
                .as("match/no match")
                .isEqualTo(match);
            soft.assertThat(context.getCurrentIndex())
                .as("index post match")
                .isEqualTo(bufferIndex);
            if (stackValue != null)
                soft.assertThat(context.getValueStack()).isNotEmpty()
                    .containsExactly(stackValue);
        }
    }
}
