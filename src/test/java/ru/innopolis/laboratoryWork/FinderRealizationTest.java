package ru.innopolis.laboratoryWork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class FinderRealizationTest {
    ProcessingFiles spyProcessingFiles;

    @BeforeEach
    void init() {
        this.spyProcessingFiles = Mockito.spy(new ProcessingFiles(null, new ConcurrentLinkedQueue(), Pattern.compile("(.*)\\b(?i)Test\\b(.*)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
    }

    @Test
    void addFoundOffersTest() {
        Scanner scanner = new Scanner("Test! Test").useDelimiter("[.!?]");
        when(spyProcessingFiles.connection()).thenReturn(scanner);
        spyProcessingFiles.addFoundOffers();
        assertEquals(spyProcessingFiles.getFinishList().size(), 2);
    }

    @Test
    void addFoundOffersNullTest() {
        when(spyProcessingFiles.connection()).thenReturn(null);
        spyProcessingFiles.addFoundOffers();
        assertEquals(spyProcessingFiles.getFinishList().size(), 0);
    }


}
