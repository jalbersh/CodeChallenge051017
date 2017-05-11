package com.dish.ofm.service.jwa.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FindSummerServiceTest {
    private FindSummerService findSummerService;

    @Before
    public void setup() {
        findSummerService = new FindSummerService();
    }

    @Test
    public void testService_testsBaseCase() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        List<List<Integer>> expected = new ArrayList<List<Integer>>();
        expected.add(list);
        List<List<Integer>> actual = findSummerService.findSummer(1,3,5);
        assertThat(expected, equalTo(actual));
    }

}