package com.example.travelapp.entity;

import com.example.travelapp.enums.BookingStatus;
import com.example.travelapp.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private String firstName;
    private String lastName;
    private String email;
    @Pattern(regexp = "^\\d{11}$",message = "Phone number must be 11 digits")
    private String phoneNumber;
    private String address;
    private String state;
    private String city;
    private String country;

    private String bookingType;
    private int children;
    private int adults;
    private int infant;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private String note;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
    private String bookingReferenceNumber;

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = calculateTotalPrice();
    }

    @OneToOne(mappedBy = "roomBooking",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    public boolean isBookingValid() {
        if (checkInDate == null || checkOutDate == null) {
            return false;
        }
        if (checkOutDate.isBefore(checkInDate)) {
            return false; // Check-out date cannot be earlier than check-in date
        }
        if (adults <= 0) {
            return false; // must be at least an adult
        }
        return true;
    }

    public BigDecimal calculateTotalPrice() {
        if (!isBookingValid()) {
            throw new IllegalArgumentException("Rezervasyon bilgileri geçersiz.");
        }

        long daysStayed = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        BigDecimal roomPrice = BigDecimal.valueOf(room.getPricePerNight());
        totalPrice = roomPrice.multiply(BigDecimal.valueOf(daysStayed));

        BigDecimal additionalAdultCharge = BigDecimal.ZERO;
        if (adults > room.getCapacity()) {
            additionalAdultCharge = BigDecimal.valueOf(adults - room.getCapacity())
                    .multiply(room.getAdultPrice());
        }

        BigDecimal additionalChildCharge =
                    BigDecimal.valueOf(children).multiply(room.getChildPrice());

        BigDecimal additionalInfantCharge =
                    BigDecimal.valueOf(infant).multiply(room.getInfantPrice());

        totalPrice =
                totalPrice.add(additionalAdultCharge)
                        .add(additionalChildCharge)
                        .add(additionalInfantCharge);

        return totalPrice;
    }

    public boolean isPaid() {
        return payment != null &&
                payment.getPaymentStatus() == PaymentStatus.PAID;
    }

    public void cancelBooking() {
        this.payment.setPaymentStatus(PaymentStatus.CANCELLED);
    }
}


















