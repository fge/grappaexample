/*
 * Copyright (C) 2014 Francis Galiegue <fgaliegue@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.autumncode.javabot.grammar.helpers;

import com.github.fge.grappa.buffers.CharSequenceInputBuffer;
import com.github.fge.grappa.buffers.InputBuffer;
import com.github.fge.grappa.matchers.base.Matcher;
import com.github.fge.grappa.run.context.DefaultMatcherContext;
import com.github.fge.grappa.run.context.MatcherContext;
import com.github.fge.grappa.stack.DefaultValueStack;
import com.github.fge.grappa.stack.ValueStack;

import java.util.Objects;

public final class MatcherContextBuilder<T>
{
    private final ValueStack<T> stack = new DefaultValueStack<>();
    private InputBuffer buffer = null;
    private Matcher matcher = null;
    private int index = 0;

    public MatcherContextBuilder<T> withInput(final String input)
    {
        buffer = new CharSequenceInputBuffer(input);
        return this;
    }

    public MatcherContextBuilder<T> withMatcher(final Matcher matcher)
    {
        this.matcher = Objects.requireNonNull(matcher);
        return this;
    }

    public MatcherContextBuilder<T> withIndex(final int index)
    {
        this.index = index;
        return this;
    }

    public MatcherContext<T> build()
    {
        final DefaultMatcherContext<T> ret = new DefaultMatcherContext<>(
            buffer, stack, SimpleMatchHandler.INSTANCE, matcher);

        ret.setCurrentIndex(index);
        return ret;
    }
}
