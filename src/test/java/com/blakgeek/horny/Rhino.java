package com.blakgeek.horny;

import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.Assert.*;

/**
 * User: Carlos Lawton
 * Date: 11/2/13
 * Time: 10:42 AM
 */
public class Rhino {

    ScriptEngine engine;

    @Before
    public void setup() {

        engine = new ScriptEngineManager().getEngineByName("JavaScript");
    }

    @Test
    public void basic() throws Exception {

        String sup = "print('sup yall?')";
        engine.eval(sup);
    }

    @Test
    public void variables() throws Exception {

        String sup = "'sup [' + name + '] ?'";
        engine.put("name", "Carlos");
        assertEquals("sup [Carlos] ?", engine.eval(sup));
    }

    @Test
    public void validator() throws Exception {

        String validationExpression = "bookmark/100 > 100";
        engine.put("bookmark", "1000");
        assertFalse("10.00 is not greater than 100", (Boolean) engine.eval(validationExpression));
        engine.put("bookmark", "10001");
        assertTrue("1000.01 is greater than 100", (Boolean) engine.eval(validationExpression));
    }

    @Test
    public void booleans() throws Exception {

        String notReallyABoolean = "!!(100 - bookmark)";
        engine.put("bookmark", 100);
        assertFalse("0 didn't evaluate to false", evalAnyToBoolean(notReallyABoolean));
        engine.put("bookmark", 101);
        assertTrue("-1 evaluated to false", evalAnyToBoolean(notReallyABoolean));
        engine.put("bookmark", 99);
        assertTrue("1 evaluated to false", evalAnyToBoolean(notReallyABoolean));
    }

    private boolean evalAnyToBoolean(String script) throws Exception {
        return (Boolean) engine.eval("!!(" + script + ")");
    }

    @Test
    public void dates() throws Exception {

        String expression = "date";
        String isoDateOnly = "1976-12-15";

    }
}
