package com.autumncode.javabot.grammar;

import com.autumncode.javabot.grammar.helpers.ParserTestBase;
import com.autumncode.javabot.grammar.helpers.ParsingData;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Test
public final class DrinkParserTest
    extends ParserTestBase<Object, DrinkParser>
{

    public DrinkParserTest()
    {
        super(DrinkParser.class, DrinkParser::vessel);
    }

    @Override
    public Collection<ParsingData<Object>> parsingData()
    {
        final List<ParsingData<Object>> list = new ArrayList<>();

        String input;
        boolean match;
        int index;
        Object value;
        ParsingData<Object> data;

        input = "no match for you";
        match = false;
        index = 0;
        value = null;
        data = new ParsingData<>(input, match, index, value);
        list.add(data);

        input = "bowl of sugar";
        match = true;
        index = 4;
        value = Vessel.BOWL;
        data = new ParsingData<>(input, match, index, value);
        list.add(data);

        return list;
    }
}
