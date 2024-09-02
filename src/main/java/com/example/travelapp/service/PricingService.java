package com.example.travelapp.service;

import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.entity.Flight;
import com.example.travelapp.enums.FlightClass;
import com.example.travelapp.enums.TicketType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PricingService {

    // Constants for tax and fee rates
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.10);
    private static final BigDecimal FEE_RATE = BigDecimal.valueOf(0.20);

    public static BigDecimal calculateBasePrice(FlightClass flightClass, TicketType ticketType) {
        BigDecimal basePrice = BigDecimal.ZERO;

        switch (flightClass) {
            case ECONOMY:
                basePrice = BigDecimal.valueOf(100);
                break;
            case BUSINESS:
                basePrice = BigDecimal.valueOf(200);
                break;
            case FIRST_CLASS:
                basePrice = BigDecimal.valueOf(300);
                break;
        }

        switch (ticketType) {
            case BASIC:
                basePrice = basePrice.multiply(BigDecimal.valueOf(1.0));
                break;
            case FLEX:
                basePrice = basePrice.multiply(BigDecimal.valueOf(1.5));
                break;
            case PREMIUM:
                basePrice = basePrice.multiply(BigDecimal.valueOf(2.0));
                break;
        }

        return basePrice.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateTotalPrice(FlightClass flightClass, TicketType ticketType,
                                                 int adultCount, int childrenCount, int infantCount) {
        BigDecimal basePrice = calculateBasePrice(flightClass, ticketType);

        BigDecimal tax = basePrice.multiply(TAX_RATE);
        BigDecimal fee = basePrice.multiply(FEE_RATE);

        BigDecimal totalAdultPrice = basePrice.add(tax).add(fee).multiply(BigDecimal.valueOf(adultCount));
        BigDecimal totalChildrenPrice = basePrice.add(tax).add(fee)
                .multiply(BigDecimal.valueOf(0.5))
                .multiply(BigDecimal.valueOf(childrenCount));  // 50% discount for children
        BigDecimal totalInfantPrice = basePrice.add(tax).add(fee)
                .multiply(BigDecimal.valueOf(0.1))
                .multiply(BigDecimal.valueOf(infantCount));    // 90% discount for infants

        BigDecimal totalPrice = totalAdultPrice.add(totalChildrenPrice).add(totalInfantPrice);
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateFlightPrice(Flight flight){
        BigDecimal basePrice = BigDecimal.valueOf(100);
        BigDecimal tax = basePrice.multiply(TAX_RATE);
        BigDecimal fee = basePrice.multiply(FEE_RATE);

        BigDecimal totalPrice = basePrice.add(tax).add(fee);

        flight.setOriginalPrice(totalPrice);
        return totalPrice;
    }
}


















