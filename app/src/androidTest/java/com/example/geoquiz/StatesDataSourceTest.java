package com.example.geoquiz;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static androidx.test.InstrumentationRegistry.getTargetContext;

public class StatesDataSourceTest extends TestCase {

    private StatesDataSource statesDataSource;

    @Before
    public void setUp() {
        statesDataSource = new StatesDataSource(getTargetContext());
        statesDataSource.open();
    }

    @Test
    public void testQueryWithImageLink() {
        String[] test = {"Hawaii","Fun Fact: "};
        assertEquals(test[0],statesDataSource.queryWithImageLink("https://suncatcherstudio.com/uploads/patterns/us-states/map-outlines/svg/hawaii-map-outline-dddddd.png")[0]);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        statesDataSource.close();
    }
}