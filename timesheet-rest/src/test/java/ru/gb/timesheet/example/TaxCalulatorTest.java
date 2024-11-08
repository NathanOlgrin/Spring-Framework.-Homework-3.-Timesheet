package ru.gb.timesheet.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaxCalulatorTest {


    @Mock
    TaxResolver mock;
    @Test
    void testGetPriceWithTax(){
        //TaxResolver mock = Mockito.mock(TaxResolver.class);
        //when(mock.getCurrentTax()).thenReturn(0.2);

        doReturn(0.2).when(mock).getCurrentTax();

        TaxCalulator taxCalulator = new TaxCalulator(mock);
        Assertions.assertEquals(120.0, taxCalulator.getPriceWithTax(100.0), 0.0000000009);

        verify(mock).getCurrentTax();
    }

}