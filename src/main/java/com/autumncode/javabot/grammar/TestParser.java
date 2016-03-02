package com.autumncode.javabot.grammar;

import com.github.fge.grappa.parsers.BaseParser;

public abstract class TestParser<T>
    extends BaseParser<T>
{
    <S extends T> S peekAs(final Class<S> theClass)
    {
        return theClass.cast(peek());
    }

    <S extends T> S popAs(final Class<S> theClass)
    {
        return theClass.cast(pop());
    }
}
