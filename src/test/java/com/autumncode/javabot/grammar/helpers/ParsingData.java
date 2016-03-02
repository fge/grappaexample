package com.autumncode.javabot.grammar.helpers;

public final class ParsingData<T>
{
    private final String input;
    private final boolean match;
    private final int bufferIndex;
    private final T stackValue;

    public ParsingData(final String input, final boolean match,
        final int bufferIndex, final T stackValue)
    {
        this.input = input;
        this.match = match;
        this.bufferIndex = bufferIndex;
        this.stackValue = stackValue;
    }

    public Object[] toArray()
    {
        return new Object[] {
            input,
            match,
            bufferIndex,
            stackValue
        };
    }
}
