package com.example.travelapp.utils;

import com.example.travelapp.entity.Amenity;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.User;
import com.example.travelapp.repository.HotelRepository;
import com.example.travelapp.repository.ReviewRepository;
import com.example.travelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Oluşturulacak otel verileri
        List<Hotel> hotels = Arrays.asList(
                new Hotel("Hotel Istanbul", "Turkey", 100, "Istanbul",
                        "Hotel Istanbul offers a luxurious experience with breathtaking views of the Bosphorus. Guests can enjoy modern amenities including a rooftop pool, free WiFi, and a state-of-the-art gym. The hotel's elegant bar serves refreshing cocktails, while the on-site restaurant offers a variety of gourmet dishes.",
                        150.0,
                        Arrays.asList("image1.avif", "image2.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Bar", "/icons/bar.png")
                        )
                ),
                new Hotel("Hotel Ankara", "Turkey", 80, "Ankara",
                        "Experience a comfortable stay at Hotel Ankara, situated in the heart of Turkey's capital. The hotel offers complimentary breakfast, a fully equipped gym, and a relaxing spa. Guests can unwind at the hotel's cozy bar after a day of exploring the city.",
                        120.0,
                        Arrays.asList("image3.avif", "image4.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        )
                ),
                new Hotel("Hotel New York", "USA", 200, "New York",
                        "Hotel New York is a luxurious haven in the heart of Manhattan. Enjoy world-class amenities including a tranquil spa, a chic bar, and convenient parking. The hotel’s restaurant offers exquisite dining options, perfect for a sophisticated urban experience.",
                        300.0,
                        Arrays.asList("image5.avif", "image6.avif"),
                        Arrays.asList(
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        )
                ),
                new Hotel("Hotel Los Angeles", "USA", 150, "Los Angeles",
                        "Hotel Los Angeles is a modern retreat in the entertainment capital of the world. Guests can relax by the outdoor pool, stay connected with free WiFi, and enjoy fine dining at the hotel’s trendy restaurant. The hotel also offers ample parking for a stress-free stay.",
                        250.0,
                        Arrays.asList("image7.avif", "image8.avif"),

                        Arrays.asList(
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png")
                        )
                ),
                new Hotel("Hotel Toronto", "Canada", 120, "Toronto",
                        "Hotel Toronto offers a cozy atmosphere with top-notch amenities. Guests can start their day with a complimentary breakfast, work out in the gym, or take a dip in the indoor pool. The hotel’s location provides easy access to the city’s main attractions.",
                        180.0,
                        Arrays.asList("image9.avif", "image10.avif"),

                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        )
                ),
                new Hotel("Hotel Vancouver", "Canada", 90, "Vancouver",
                        "Charming and welcoming, Hotel Vancouver is perfect for travelers seeking a serene escape. Enjoy a complimentary breakfast each morning, and stay active at the gym. The hotel’s classic design and warm service make for an unforgettable stay.",
                        160.0,
                        Arrays.asList("image11.avif", "image12.avif"),

                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        )
                ),
                new Hotel("Hotel Paris", "France", 130, "Paris",
                        "Located in the heart of Paris, Hotel Paris offers elegance and comfort. Guests can relax in the spa, enjoy a delicious breakfast, and stay fit at the gym. The hotel’s bar is perfect for unwinding after a day of exploring the city’s famous landmarks.",
                        220.0,
                        Arrays.asList("image13.avif", "image14.avif"),

                        Arrays.asList(
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        )
                ),
                new Hotel("Hotel Rome", "Italy", 110, "Rome",
                        "Hotel Rome offers a historic ambiance with modern comforts. Guests can enjoy a hearty breakfast, dine in the hotel’s restaurant, and relax in the gym. With its prime location, this hotel is perfect for those wishing to explore the ancient city.",
                        200.0,
                        Arrays.asList("image15.avif", "image16.avif"),

                        Arrays.asList(
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        )
                ),
                new Hotel("Hotel Tokyo", "Japan", 170, "Tokyo",
                        "Stylish and sophisticated, Hotel Tokyo offers a unique experience in Japan's capital. Guests can swim in the indoor pool, enjoy a complimentary breakfast, and stay connected with free WiFi. The hotel’s gym is equipped with the latest fitness equipment.",
                        280.0,
                        Arrays.asList("image17.webp", "image18.avif"),

                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        )
                ),
                new Hotel("Hotel Kyoto", "Japan", 95, "Kyoto",
                        "Experience traditional Japanese hospitality at Hotel Kyoto. The hotel offers a complimentary breakfast, a relaxing spa, and a fully equipped gym. After a day of sightseeing, unwind at the hotel’s serene bar, perfect for a quiet evening.",
                        150.0,
                        Arrays.asList("image19.avif", "image20.avif"),

                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png")
                        )
                ),
                new Hotel("Hotel Sydney", "Australia", 140, "Sydney",
                        "Hotel Sydney offers seaside luxury with stunning views of the harbor. Guests can indulge in the spa, enjoy a drink at the bar, and dine at the hotel’s restaurant. With easy access to Sydney’s attractions, this hotel is the perfect coastal getaway.",
                        240.0,
                        Arrays.asList("image21.avif", "image22.avif"),

                        Arrays.asList(
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        )
                ),
                new Hotel("Hotel Melbourne", "Australia", 100, "Melbourne",
                        "A modern and vibrant hotel, Hotel Melbourne is ideal for both business and leisure travelers. The hotel features a relaxing spa, a lively bar, and convenient parking. Guests can also enjoy fine dining at the on-site restaurant.",
                        220.0,
                        Arrays.asList("image23.avif", "image24.avif"),

                        Arrays.asList(
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png")
                        )
                ),new Hotel("Hotel Barcelona", "Spain", 110, "Barcelona", "Cozy hotel in Barcelona", 180.0, Arrays.asList("image31.avif", "image32.avif"),  Arrays.asList(
                        new Amenity("Parking", "/icons/parking.png"),
                        new Amenity("Restaurant", "/icons/restaurant.png"),
                        new Amenity("Free WiFi", "/icons/wifi.png"),
                        new Amenity("Pool", "/icons/pool.png")
                )),
                new Hotel("Hotel Beijing", "China", 180, "Beijing", "Modern hotel in the heart of Beijing", 260.0, Arrays.asList("image33.webp", "image34.avif"), Arrays.asList(
                        new Amenity("Free WiFi", "/icons/wifi.png"),
                        new Amenity("Pool", "/icons/pool.png"),
                        new Amenity("Parking", "/icons/parking.png"),
                        new Amenity("Restaurant", "/icons/restaurant.png")
                )),
                new Hotel("Hotel Madrid", "Spain", 140, "Madrid", "Elegant hotel in the vibrant city of Madrid", 200.0, Arrays.asList("image35.jpeg", "image36.avif"),  Arrays.asList(
                        new Amenity("Free Breakfast", "/icons/breakfast.png"),
                        new Amenity("Gym", "/icons/gym.png"),
                        new Amenity("Spa", "/icons/spa.png"),
                        new Amenity("Bar", "/icons/bar.png")
                )),
                new Hotel("Hotel Buenos Aires", "Argentina", 160, "Buenos Aires", "Luxurious hotel in Buenos Aires", 220.0, Arrays.asList("image37.avif", "image38.webp"),  Arrays.asList(
                        new Amenity("Spa", "/icons/spa.png"),
                        new Amenity("Bar", "/icons/bar.png"),
                        new Amenity("Parking", "/icons/parking.png"),
                        new Amenity("Restaurant", "/icons/restaurant.png")
                )),
                new Hotel("Hotel Cape Town", "South Africa", 130, "Cape Town", "Stylish hotel with stunning views of Cape Town", 240.0, Arrays.asList("image39.avif", "image40.avif"),  Arrays.asList(
                        new Amenity("Free WiFi", "/icons/wifi.png"),
                        new Amenity("Pool", "/icons/pool.png"),
                        new Amenity("Free Breakfast", "/icons/breakfast.png"),
                        new Amenity("Gym", "/icons/gym.png")
                )),
                new Hotel("Hotel Athens", "Greece", 110, "Athens", "Historic hotel in the ancient city of Athens", 180.0, Arrays.asList("image41.avif", "image42.avif"),  Arrays.asList(
                        new Amenity("Free Breakfast", "/icons/breakfast.png"),
                        new Amenity("Gym", "/icons/gym.png"),
                        new Amenity("Spa", "/icons/spa.png"),
                        new Amenity("Bar", "/icons/bar.png")
                )),
                new Hotel("Hotel Cairo", "Egypt", 140, "Cairo", "Elegant hotel near the Pyramids in Cairo", 220.0, Arrays.asList("image43.avif", "image44.webp"),  Arrays.asList(
                        new Amenity("Free WiFi", "/icons/wifi.png"),
                        new Amenity("Pool", "/icons/pool.png"),
                        new Amenity("Parking", "/icons/parking.png"),
                        new Amenity("Restaurant", "/icons/restaurant.png")
                )),
                new Hotel("Hotel Bangkok", "Thailand", 120, "Bangkok", "Luxury hotel in the vibrant city of Bangkok", 200.0, Arrays.asList("image45.avif", "image46.webp"),  Arrays.asList(
                        new Amenity("Free Breakfast", "/icons/breakfast.png"),
                        new Amenity("Gym", "/icons/gym.png"),
                        new Amenity("Spa", "/icons/spa.png"),
                        new Amenity("Bar", "/icons/bar.png")
                )),
                new Hotel("Hotel Vienna", "Austria", 100, "Vienna", "Charming hotel in the cultural heart of Vienna", 180.0, Arrays.asList("image47.webp", "image48.avif"),  Arrays.asList(
                        new Amenity("Free WiFi", "/icons/wifi.png"),
                        new Amenity("Pool", "/icons/pool.png"),
                        new Amenity("Free Breakfast", "/icons/breakfast.png"),
                        new Amenity("Gym", "/icons/gym.png")
                )),
                new Hotel("Hotel Seoul", "South Korea", 150, "Seoul", "Modern hotel in the bustling city of Seoul", 240.0, Arrays.asList("image49.webp", "image50.avif"),  Arrays.asList(
                        new Amenity("Spa", "/icons/spa.png"),
                        new Amenity("Bar", "/icons/bar.png"),
                        new Amenity("Parking", "/icons/parking.png"),
                        new Amenity("Restaurant", "/icons/restaurant.png")
                ))
        );
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
        hotelRepository.saveAll(hotels);

        User user = new User();
        user.setEmail("mustafa.glr@hotmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        user.setConfirmPassword(user.getPassword());
        user.setPhoneNumber("05384843853");
        user.setFirstName("Mustafa");
        user.setLastName("Güler");
        user.setEnabled(true);
        user.setProfilePicture("profile.png");
        user.setUsername("mustafa");

        userRepository.save(user);

    }
}
