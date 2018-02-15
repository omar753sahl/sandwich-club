package com.udacity.sandwichclub.utils;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Created by Omar on 15-Feb-18 5:02 PM
 */
public class StringUtilsTest extends TestCase {
    public void testJoin() throws Exception {
        assertEquals("The generated string doesn't match the expected string!", StringUtils.join(Arrays.asList("bla", "bla", "bla", "bla"), ", "), "bla, bla, bla, bla");
    }

}