package com.github.torczuk.domain.service;

import com.github.torczuk.domain.model.Conversion;
import com.github.torczuk.domain.repository.ConversionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.Long.valueOf;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ConversionHistoryServiceTest {
    private static final int CONVERSION_SIZE = 4;
    private static final String AMOUNT = "10.2";
    private static final String BASE_CURRENCY = "USD";
    private static final String TARGET_CURRENCY = "PLN";
    private static final Long USER_ID = 1L;

    private @Mock ConversionRepository conversionRepository;
    private @InjectMocks ConversionHistoryService conversionHistoryService;

    @Test
    public void shouldReturnConversionsOrderedByTimestampWhenRequestedSizeOfConversionIsBiggerThatLimit() {
        List<Conversion> conversions = generateConversion(CONVERSION_SIZE);
        given(conversionRepository.findAllByUserId(USER_ID)).willReturn(conversions);

        List<Conversion> userConversions = conversionHistoryService.getUserConversions(USER_ID, CONVERSION_SIZE + 1);

        assertThat(userConversions)
                .containsAll(conversions)
                .isSorted();
    }

    @Test
    public void shouldReturnConversionsOrderedByTimestampWhenRequestedSizeOfConversionIsLessThatLimit() {
        List<Conversion> conversions = generateConversion(CONVERSION_SIZE);
        given(conversionRepository.findAllByUserId(USER_ID)).willReturn(conversions);

        List<Conversion> userConversions = conversionHistoryService.getUserConversions(USER_ID, CONVERSION_SIZE - 1);

        assertThat(userConversions)
                .containsAll(conversions.subList(0, CONVERSION_SIZE - 1))
                .isSorted();
    }

    private static List<Conversion> generateConversion(int size) {
        return IntStream.range(0, size + 1)
                .mapToObj(n -> new Conversion(id(), valueOf(size - n), USER_ID, AMOUNT, AMOUNT, BASE_CURRENCY, TARGET_CURRENCY))
                .collect(toList());
    }

    private static Long id() {
        return valueOf(UUID.randomUUID().hashCode());
    }
}