package com.example.travelapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

//@Component
/*public class SeedData implements CommandLineRunner {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        // Oluşturulacak otel verileri
        Hotel[] hotels = {
                new Hotel("Hotel Castle Salam", Arrays.asList("/images/beijing1.jpg", "/images/beijing2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool"), "Beijing", "Beijing", "China",
                        99.00, "A luxurious hotel with all modern amenities.", "4.8", 200),
                new Hotel("Hotel Royal", Arrays.asList("/images/tokyo1.jpg", "/images/tokyo2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Restaurant"), "Tokyo", "Tokyo", "Japan",
                        120.00, "Experience the royalty at Hotel Royal.", "4.9", 150),
                new Hotel("Grand Plaza", Arrays.asList("/images/ny1.jpg", "/images/ny2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Pool", "Conference Room"), "New York", "New York", "USA",
                        150.00, "Located in the heart of the city with the best services.", "4.7", 300),
                new Hotel("Sea Breeze Resort", Arrays.asList("/images/sydney1.jpg", "/images/sydney2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Beach Access"), "Sydney", "New South Wales", "Australia",
                        110.00, "Enjoy the sea breeze with top-notch facilities.", "4.6", 180),
                new Hotel("Mountain View Inn", Arrays.asList("/images/denver1.jpg", "/images/denver2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool"), "Denver", "Colorado", "USA",
                        85.00, "A cozy inn with a great view of the mountains.", "4.5", 90),
                new Hotel("City Lights Hotel", Arrays.asList("/images/paris1.jpg", "/images/paris2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Restaurant", "Bar"), "Paris", "Île-de-France", "France",
                        140.00, "Experience the city of lights with luxury and comfort.", "4.7", 220),
                new Hotel("Desert Oasis", Arrays.asList("/images/dubai1.jpg", "/images/dubai2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Desert Safari"), "Dubai", "Dubai", "UAE",
                        200.00, "An oasis in the desert with the best hospitality.", "4.8", 250),
                new Hotel("Lakeview Hotel", Arrays.asList("/images/zurich1.jpg", "/images/zurich2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Lake Access"), "Zurich", "Zurich", "Switzerland",
                        130.00, "Stay by the lake with serene views and comfort.", "4.9", 170),
                new Hotel("Safari Lodge", Arrays.asList("/images/nairobi1.jpg", "/images/nairobi2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Safari Tours"), "Nairobi", "Nairobi", "Kenya",
                        95.00, "Experience the wild with luxury at Safari Lodge.", "4.5", 100),
                new Hotel("Beachside Resort", Arrays.asList("/images/malibu1.jpg", "/images/malibu2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Beach Access"), "Malibu", "California", "USA",
                        180.00, "Enjoy the beachside with top facilities and comfort.", "4.7", 200),
                new Hotel("Cultural Heritage Hotel", Arrays.asList("/images/kyoto1.jpg", "/images/kyoto2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Cultural Tours"), "Kyoto", "Kyoto", "Japan",
                        100.00, "Immerse in cultural heritage with luxury stay.", "4.6", 150),
                new Hotel("Northern Lights Inn", Arrays.asList("/images/reykjavik1.jpg", "/images/reykjavik2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Northern Lights Tours"), "Reykjavik", "Capital Region", "Iceland",
                        120.00, "Witness the Northern Lights with a comfortable stay.", "4.8", 80),
                new Hotel("Historical Hotel", Arrays.asList("/images/rome1.jpg", "/images/rome2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Historical Tours"), "Rome", "Lazio", "Italy",
                        130.00, "Stay in a hotel with rich historical background.", "4.7", 190),
                new Hotel("Tropical Paradise", Arrays.asList("/images/honolulu1.jpg", "/images/honolulu2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Beach Access"), "Honolulu", "Hawaii", "USA",
                        220.00, "Enjoy the tropical paradise with best amenities.", "4.9", 210),
                new Hotel("Winter Wonderland", Arrays.asList("/images/aspen1.jpg", "/images/aspen2.jpg"),
                        Arrays.asList("Free Wi-Fi", "Gym", "Spa", "Pool", "Ski Access"), "Aspen", "Colorado", "USA",
                        200.00, "Experience the winter wonderland with luxury.", "4.8", 170)
        };

        // Otelleri veritabanına kaydedin
        hotelRepository.saveAll(Arrays.asList(hotels));
    }
}*/
