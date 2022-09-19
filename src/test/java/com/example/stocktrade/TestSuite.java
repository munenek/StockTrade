package com.example.stocktrade;

import com.example.stocktrade.requests.*;
import com.hackerrank.test.utility.TestWatchman;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TradesControllerTest.class,
    StocksControllerTest.class,
    ResourcesControllerTest.class,
    NoResourcesStocksControllerTest.class,
    NoResourcesTradesControllerTest.class
})
public class TestSuite {

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(TestSuite.class);
    }
}
