package com.github.torczuk.infrastructure.resource;

import com.github.torczuk.domain.exception.CurrencyConversionException;
import com.github.torczuk.domain.model.Conversion;
import com.github.torczuk.domain.service.ConversionHistoryService;
import com.github.torczuk.domain.service.CurrencyConverterService;
import com.github.torczuk.infrastructure.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import static com.github.torczuk.infrastructure.utils.FormatUtils.applicationDecimalFormat;
import static com.github.torczuk.infrastructure.utils.LastModifiedUtils.lastModified;
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
        try {
            BigDecimal amountToConvert = (BigDecimal) applicationDecimalFormat().parse(amount);
            BigDecimal converted = currencyConverterService.convert(amountToConvert, baseCurrencyCode, targetCurrencyCode);
            return new ResponseEntity<>(converted, OK);
        } catch (ParseException e) {
            return new ResponseEntity("Invalid number format, e.g 1,000.0 is valid", BAD_REQUEST);
        } catch (CurrencyConversionException e) {
            return new ResponseEntity(e.getMessage(), BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/user/{id}/conversion", method = GET, produces = APPLICATION_JSON)
    public ResponseEntity<?> history(
            WebRequest request,
            @PathVariable("id") Long userId,
            @RequestParam(value = "limit", defaultValue = "0") Integer limit) {

        if (!UserUtils.checkIfCurrentUser(userId)) {
            return new ResponseEntity(METHOD_NOT_ALLOWED);
        }
        List<Conversion> userConversions = conversionHistoryService.getUserConversions(UserUtils.getCurrentUserId(), limit);

        if (userConversions.isEmpty()) {
            return ResponseEntity.ok(userConversions);
        } else if (request.checkNotModified(lastModified(userConversions).getTime())) {
            return new ResponseEntity(NOT_MODIFIED);
        }

        return response(userConversions);
    }

    private ResponseEntity<?> response(List<Conversion> conversions) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.lastModified(lastModified(conversions).getTime());
        return builder.body(conversions);
    }
}
