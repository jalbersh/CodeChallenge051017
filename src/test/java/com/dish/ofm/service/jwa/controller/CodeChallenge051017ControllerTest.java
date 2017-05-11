package com.dish.ofm.service.jwa.controller;

import com.dish.ofm.service.jwa.service.FindSummerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CodeChallenge051017ControllerTest {
    private CodeChallenge051017Controller controller;

    @Mock
    private FindSummerService findSummerService;

    @Before
    public void setup() {
        controller = new CodeChallenge051017Controller(findSummerService);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        when(findSummerService.findSummer(any(),any(),any())).thenReturn(Collections.singletonList(list));
    }

    @Test
    public void controller_callsService() throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        when(findSummerService.findSummer(any(),any(),any())).thenReturn(Collections.singletonList(list));
        ResponseEntity response = controller.findSummer("1","3","5");
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(response.getBody(),equalTo(Collections.singletonList(list)));
    }
}
