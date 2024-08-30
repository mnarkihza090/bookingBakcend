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

    /*public static double calculatePrice(FlightClass flightClass, TicketType ticketType){

        double basePrice = 0.0;
        double classMultiplier = 1.0;
        double ticketTypeMultiplier = 1.0;

        switch (flightClass){
            case ECONOMY:
                classMultiplier = 0.9;
                break;
            case BUSINESS:
                classMultiplier = 1.2;
                break;
            case FIRST_CLASS:
                classMultiplier = 1.5;
                break;
        }

        switch (ticketType){
            case BASIC:
                ticketTypeMultiplier = 100;
                break;
            case FLEX:
                ticketTypeMultiplier = 600;
                break;
            case PREMIUM:
                ticketTypeMultiplier = 800;
                break;
        }

        basePrice = 100.0; // Base price for a single ticket
        basePrice *= classMultiplier;
        basePrice *= ticketTypeMultiplier;

        return basePrice;
    }

    public static double calculateBasePrice(FlightClass flightClass,TicketType ticketType,int adult,int children,int infant){
        double basePrice = calculatePrice(flightClass,ticketType);

        double totalAdultPrice = basePrice * adult;
        double totalChildPrice = basePrice* 0.5 * children; // discount %50 for children
        double totalInfantPrice = basePrice * 0.1 *infant; // discount %90 for infant

        return totalAdultPrice + totalChildPrice + totalInfantPrice;
    } */
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

        BigDecimal totalAdultPrice = basePrice.multiply(BigDecimal.valueOf(adultCount));
        BigDecimal totalChildrenPrice = basePrice.multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(childrenCount));  // Çocuklar için %50 indirim
        BigDecimal totalInfantPrice = basePrice.multiply(BigDecimal.valueOf(0.1)).multiply(BigDecimal.valueOf(infantCount));      // Bebekler için %90 indirim

        BigDecimal totalPrice = totalAdultPrice.add(totalChildrenPrice).add(totalInfantPrice);
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

}


















