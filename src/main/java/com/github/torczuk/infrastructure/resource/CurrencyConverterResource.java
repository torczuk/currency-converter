package com.github.torczuk.infrastructure.resource;

import com.github.torczuk.domain.model.Conversion;
import com.github.torczuk.domain.service.ConversionHistoryService;
import com.github.torczuk.domain.service.CurrencyConverterService;
import com.github.torczuk.infrastructure.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CurrencyConverterResource {
    private @Autowired CurrencyConverterService currencyConverterService;
    private @Autowired ConversionHistoryService conversionHistoryService;

    @RequestMapping(value = "/user/{id}/conversion",
            method = RequestMethod.POST
    )
    public ResponseEntity<?> convert(
            @PathVariable("id") Long userId,
            @RequestParam("amount") String amount,
            @RequestParam("baseCurrencyCode") String baseCurrencyCode,
            @RequestParam("targetCurrencyCode") String targetCurrencyCode) {

        if (!UserUtils.checkIfCurrentUser(userId)) {
            return new ResponseEntity(METHOD_NOT_ALLOWED);
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        try {
            BigDecimal amountToConvert = (BigDecimal) decimalFormat.parse(amount);
            BigDecimal converted = currencyConverterService.convert(amountToConvert, baseCurrencyCode, targetCurrencyCode);
            return new ResponseEntity<>(converted, OK);
        } catch (ParseException e) {
            return new ResponseEntity("Invalid number format", BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/{id}/conversion", method = GET, produces = APPLICATION_JSON)
    public ResponseEntity<List<Conversion>> history(@PathVariable("id") Long userId, @RequestParam(value = "limit", defaultValue = "0") Integer limit) {
        if (!UserUtils.checkIfCurrentUser(userId)) {
            return new ResponseEntity(METHOD_NOT_ALLOWED);
        }
        List<Conversion> userConversions = conversionHistoryService.getUserConversions(UserUtils.getCurrentUserId(), limit);

        return new ResponseEntity<>(userConversions, OK);
    }

}
