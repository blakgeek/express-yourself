package com.blakgeek.spelcheck;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Carlos Lawton
 * Date: 11/2/13
 * Time: 8:58 AM
 */
public class SpelCheck {

    ExpressionParser parser;

    @Before
    public void setup() {
        parser = new SpelExpressionParser();
    }

    @Test
    public void numbers() {

        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        assertEquals("Hello World!", message);
        message = exp.getValue(String.class);
        assertEquals("Hello World!", message);
    }

    @Test
    public void jodaTime() {

        DateTime date = parser.parseExpression("'1976/12/15'").getValue(DateTime.class);
    }

    @Test
    public void context() {

        StandardEvaluationContext context = new StandardEvaluationContext();
        Expression expression = parser.parseExpression("T(Float).parseFloat(#bookmark)/100 > 100");
        context.setVariable("bookmark", "1000");
        assertFalse("10.00 is not greater than 100", expression.getValue(context, Boolean.class));
        context.setVariable("bookmark", 10000);
        assertFalse("100.00 is not graeter than 100", expression.getValue(context, Boolean.class));
        context.setVariable("bookmark", 100000);
        assertTrue("1000.00 is greater than 100", expression.getValue(context, Boolean.class));

    }
}
