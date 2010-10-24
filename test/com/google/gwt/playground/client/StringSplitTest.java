package com.google.gwt.playground.client;

import junit.framework.Assert;

import com.google.gwt.junit.client.GWTTestCase;

public class StringSplitTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "com.google.gwt.playground.Playground";
    }

    public void testStringSplit() {

        String[] urls = { "http://www.foo.com/path/to/file.html",
                "http://www.foo.com/path/to/file.html?foo=bar",
                "http://www.foo.com/path/to/file.html?foo=bar&extension=html" };

        for (String url : urls) {
            String base = url.split("\\?")[0];
            Assert.assertTrue(base.endsWith("html"));
        }

    }

}
