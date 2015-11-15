package com.github.torczuk.infrastructure.resource;

import com.github.torczuk.domain.model.User;
import com.github.torczuk.domain.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CurrencyConverterResource {

    private @Autowired CurrencyConverterService currencyConverterService;

    @RequestMapping(value="/user/{id}/conversion",
            method= RequestMethod.POST
    )
    public ResponseEntity<?> convert(
            @PathVariable("id") String userId,
            @RequestParam("amount") String amount,
            @RequestParam("baseCurrencyCode") String baseCurrencyCode,
            @RequestParam("targetCurrencyCode") String targetCurrencyCode) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null || !String.valueOf(user.getId()).equals(userId)) {
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

    @RequestMapping(value="/user/{id}/conversion", method = GET, produces = APPLICATION_JSON)
    public ResponseEntity<?> history(@PathVariable("id") String userId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null || !String.valueOf(user.getId()).equals(userId)) {
            return new ResponseEntity(METHOD_NOT_ALLOWED);
        }



        return new ResponseEntity<>(OK);
    }

}
