package com.github.torczuk.domain.service;

import com.github.torczuk.domain.model.Conversion;
import com.github.torczuk.domain.repository.ConversionRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ConversionHistoryService {

    @Autowired private ConversionRepository conversionRepository;
    @Autowired private UserService userService;

    public Conversion saveUserConversion(Conversion conversion) {
        Long currentUserId = userService.getCurrentUserId();
        conversion.setUserId(currentUserId);
        return conversionRepository.save(conversion);
    }

    public List<Conversion> getUserConversions(Long userId, int limit) {
        List<Conversion> userConversion = conversionRepository.findAllByUserId(userId);
        Collections.sort(userConversion);
        if (userConversion.size() <= limit || limit == 0) {
            return ImmutableList.copyOf(userConversion);
        } else {
            return ImmutableList.copyOf(userConversion.subList(0, limit));
        }
    }
}
