package com.sebastian_daschner.jamazon.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class TestBoundaryMassTest {

    private final String controlString;
    private final String expected;
    private TestBoundary cut;

    public TestBoundaryMassTest(final String controlString, final String expected) {
        this.controlString = controlString;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        cut = new TestBoundary();
        cut.testControl = mock(TestControl.class);
    }

    @Test
    public void test() {
        when(cut.testControl.getString()).thenReturn(controlString);
        String actual = cut.getStringLength();
        assertThat(actual, is(expected));
        verify(cut.testControl).getString();
        verifyNoMoreInteractions(cut.testControl);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{"hello", "hello:5"},
                new Object[]{"he", "he:2"},
                new Object[]{"h", "h:1"},
                new Object[]{"", ":0"}
        );
    }

}
