package com.sebastian_daschner.jamazon.business;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TestBoundaryTest {

    private TestBoundary cut;

    @Before
    public void setUp() {
        cut = new TestBoundary();
        cut.testControl = mock(TestControl.class);
    }

    @Test
    public void testGetStringLength() {
        when(cut.testControl.getString()).thenReturn("hello");
        String actual = cut.getStringLength();
        assertThat(actual, is("hello:5"));

        when(cut.testControl.getString()).thenReturn("h");
        actual = cut.getStringLength();
        assertThat(actual, is("h:1"));

        when(cut.testControl.getString()).thenReturn("");
        actual = cut.getStringLength();
        assertThat(actual, is(":0"));

        verify(cut.testControl, times(3)).getString();
        verifyNoMoreInteractions(cut.testControl);
    }

    @Test(expected = NullPointerException.class)
    public void testGetStringLengthNull() {
        when(cut.testControl.getString()).thenReturn(null);
        cut.getStringLength();
    }

}