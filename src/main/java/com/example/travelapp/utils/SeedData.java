package com.example.travelapp.utils;

import com.example.travelapp.entity.Hotel;
import com.example.travelapp.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        // Oluşturulacak otel verileri
        List<Hotel> hotels = Arrays.asList(
                new Hotel("Hotel Istanbul", "Turkey", 100, "Istanbul", "Beautiful hotel in Istanbul", 150.0, Arrays.asList("image1.avif", "image2.avif"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Ankara", "Turkey", 80, "Ankara", "Comfortable stay in Ankara", 120.0, Arrays.asList("image3.jpg", "image4.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel New York", "USA", 200, "New York", "Luxurious hotel in New York", 300.0, Arrays.asList("image5.jpg", "image6.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Los Angeles", "USA", 150, "Los Angeles", "Modern hotel in Los Angeles", 250.0, Arrays.asList("image7.jpg", "image8.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Toronto", "Canada", 120, "Toronto", "Cozy hotel in Toronto", 180.0, Arrays.asList("image9.jpg", "image10.jpg"), "4 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Vancouver", "Canada", 90, "Vancouver", "Charming hotel in Vancouver", 160.0, Arrays.asList("image11.jpg", "image12.jpg"), "3 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Paris", "France", 130, "Paris", "Elegant hotel in Paris", 220.0, Arrays.asList("image13.jpg", "image14.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Rome", "Italy", 110, "Rome", "Historic hotel in Rome", 200.0, Arrays.asList("image15.jpg", "image16.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Tokyo", "Japan", 170, "Tokyo", "Stylish hotel in Tokyo", 280.0, Arrays.asList("image17.jpg", "image18.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Kyoto", "Japan", 95, "Kyoto", "Traditional hotel in Kyoto", 150.0, Arrays.asList("image19.jpg", "image20.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Sydney", "Australia", 140, "Sydney", "Seaside hotel in Sydney", 240.0, Arrays.asList("image21.jpg", "image22.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Melbourne", "Australia", 100, "Melbourne", "Modern hotel in Melbourne", 220.0, Arrays.asList("image23.jpg", "image24.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Dubai", "UAE", 200, "Dubai", "Luxury hotel in Dubai", 400.0, Arrays.asList("image25.jpg", "image26.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Abu Dhabi", "UAE", 150, "Abu Dhabi", "Elegant hotel in Abu Dhabi", 350.0, Arrays.asList("image27.jpg", "image28.jpg"), "5 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel London", "UK", 180, "London", "Classic hotel in London", 280.0, Arrays.asList("image29.jpg", "image30.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Edinburgh", "UK", 90, "Edinburgh", "Charming hotel in Edinburgh", 160.0, Arrays.asList("image31.jpg", "image32.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Berlin", "Germany", 140, "Berlin", "Modern hotel in Berlin", 200.0, Arrays.asList("image33.jpg", "image34.jpg"), "4 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Munich", "Germany", 110, "Munich", "Traditional hotel in Munich", 180.0, Arrays.asList("image35.jpg", "image36.jpg"), "3 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Rio", "Brazil", 130, "Rio de Janeiro", "Beachfront hotel in Rio", 220.0, Arrays.asList("image37.jpg", "image38.jpg"), "4 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Sao Paulo", "Brazil", 150, "Sao Paulo", "Urban hotel in Sao Paulo", 200.0, Arrays.asList("image39.jpg", "image40.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Moscow", "Russia", 160, "Moscow", "Luxury hotel in Moscow", 260.0, Arrays.asList("image41.jpg", "image42.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel St Petersburg", "Russia", 120, "St Petersburg", "Charming hotel in St Petersburg", 200.0, Arrays.asList("image43.jpg", "image44.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Madrid", "Spain", 140, "Madrid", "Elegant hotel in Madrid", 240.0, Arrays.asList("image45.jpg", "image46.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Barcelona", "Spain", 110, "Barcelona", "Cozy hotel in Barcelona", 180.0, Arrays.asList("image47.jpg", "image48.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Beijing", "China", 180, "Beijing", "Modern hotel in Beijing", 260.0, Arrays.asList("image49.jpg", "image50.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool"))
                /*new Hotel("Hotel Shanghai", "China", 150, "Shanghai", "Stylish hotel in Shanghai", 240.0, Arrays.asList("image51.jpg", "image52.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Mumbai", "India", 170, "Mumbai", "Luxury hotel in Mumbai", 220.0, Arrays.asList("image53.jpg", "image54.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Delhi", "India", 130, "Delhi", "Comfortable hotel in Delhi", 200.0, Arrays.asList("image55.jpg", "image56.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Cairo", "Egypt", 160, "Cairo", "Historic hotel in Cairo", 180.0, Arrays.asList("image57.jpg", "image58.jpg"), "4 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Alexandria", "Egypt", 140, "Alexandria", "Elegant hotel in Alexandria", 160.0, Arrays.asList("image59.jpg", "image60.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Athens", "Greece", 120, "Athens", "Modern hotel in Athens", 210.0, Arrays.asList("image61.jpg", "image62.jpg"), "4 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Thessaloniki", "Greece", 90, "Thessaloniki", "Comfortable hotel in Thessaloniki", 150.0, Arrays.asList("image63.jpg", "image64.jpg"), "3 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Buenos Aires", "Argentina", 170, "Buenos Aires", "Luxurious hotel in Buenos Aires", 270.0, Arrays.asList("image65.jpg", "image66.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Santiago", "Chile", 140, "Santiago", "Modern hotel in Santiago", 230.0, Arrays.asList("image67.jpg", "image68.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Johannesburg", "South Africa", 130, "Johannesburg", "Elegant hotel in Johannesburg", 200.0, Arrays.asList("image69.jpg", "image70.jpg"), "4 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Cape Town", "South Africa", 120, "Cape Town", "Seaside hotel in Cape Town", 220.0, Arrays.asList("image71.jpg", "image72.jpg"), "5 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Seoul", "South Korea", 160, "Seoul", "Modern hotel in Seoul", 240.0, Arrays.asList("image73.jpg", "image74.jpg"), "4 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Busan", "South Korea", 100, "Busan", "Cozy hotel in Busan", 180.0, Arrays.asList("image75.jpg", "image76.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Auckland", "New Zealand", 140, "Auckland", "Elegant hotel in Auckland", 210.0, Arrays.asList("image77.jpg", "image78.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Wellington", "New Zealand", 90, "Wellington", "Charming hotel in Wellington", 160.0, Arrays.asList("image79.jpg", "image80.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Oslo", "Norway", 130, "Oslo", "Modern hotel in Oslo", 230.0, Arrays.asList("image81.jpg", "image82.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Bergen", "Norway", 110, "Bergen", "Cozy hotel in Bergen", 170.0, Arrays.asList("image83.jpg", "image84.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Copenhagen", "Denmark", 120, "Copenhagen", "Elegant hotel in Copenhagen", 250.0, Arrays.asList("image85.jpg", "image86.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Aarhus", "Denmark", 100, "Aarhus", "Comfortable hotel in Aarhus", 190.0, Arrays.asList("image87.jpg", "image88.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Stockholm", "Sweden", 140, "Stockholm", "Modern hotel in Stockholm", 260.0, Arrays.asList("image89.jpg", "image90.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Gothenburg", "Sweden", 110, "Gothenburg", "Cozy hotel in Gothenburg", 210.0, Arrays.asList("image91.jpg", "image92.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym")),
                new Hotel("Hotel Helsinki", "Finland", 130, "Helsinki", "Stylish hotel in Helsinki", 240.0, Arrays.asList("image93.jpg", "image94.jpg"), "5 stars", Arrays.asList("Spa", "Bar")),
                new Hotel("Hotel Turku", "Finland", 90, "Turku", "Comfortable hotel in Turku", 170.0, Arrays.asList("image95.jpg", "image96.jpg"), "4 stars", Arrays.asList("Parking", "Restaurant")),
                new Hotel("Hotel Reykjavik", "Iceland", 120, "Reykjavik", "Modern hotel in Reykjavik", 260.0, Arrays.asList("image97.jpg", "image98.jpg"), "5 stars", Arrays.asList("Free WiFi", "Pool")),
                new Hotel("Hotel Akureyri", "Iceland", 80, "Akureyri", "Cozy hotel in Akureyri", 180.0, Arrays.asList("image99.jpg", "image100.jpg"), "4 stars", Arrays.asList("Free Breakfast", "Gym"))*/
        );

        hotelRepository.saveAll(hotels);
    }
}
