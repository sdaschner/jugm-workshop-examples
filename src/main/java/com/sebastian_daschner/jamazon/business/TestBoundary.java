package com.sebastian_daschner.jamazon.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TestBoundary {

    @Inject
    TestControl testControl;

    public String getStringLength() {
        final String string = testControl.getString();
        return string + ":" + string.length();
    }

}
