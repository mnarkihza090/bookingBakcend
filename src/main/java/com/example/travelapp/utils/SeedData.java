package com.example.travelapp.utils;

import com.example.travelapp.entity.Amenity;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.Room;
import com.example.travelapp.entity.User;
import com.example.travelapp.repository.HotelRepository;
import com.example.travelapp.repository.ReviewRepository;
import com.example.travelapp.repository.RoomRepository;
import com.example.travelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
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
    @Autowired
    private RoomRepository roomRepository;

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
                        ),
                        Arrays.asList(
                                new Room("101", 2, 100.0, "Standard", "Comfortable room with city view", Arrays.asList("room1.avif", "room2.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("102", 4, 150.0, "Suite", "Spacious suite with sea view", Arrays.asList("room3.avif", "room4.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ), LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("201", 1, 80.0, "Single", "Cozy single room with a view of the city", Arrays.asList("room5.avif", "room6.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now().plusDays(5),
                                        LocalDate.now().plusDays(15)),
                                new Room("202", 3, 120.0, "Family", "Spacious room suitable for families", Arrays.asList("room7.webp", "room8.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now().plusDays(2),
                                        LocalDate.now().plusDays(9))
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
                        ),
                        Arrays.asList(
                                new Room("301", 2, 250.0, "Deluxe", "Luxurious room with skyline view", Arrays.asList("room9.webp", "room10.avif"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("302", 4, 350.0, "Penthouse", "Elegant penthouse suite with cityscape view", Arrays.asList("room11.avif", "room12.avif"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now().plusDays(7),
                                        LocalDate.now().plusDays(20))
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
                        ),
                        Arrays.asList(
                                new Room("401", 2, 200.0, "Standard", "Comfortable room with pool view", Arrays.asList("room13.avif", "room14.webp"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("402", 3, 280.0, "Executive", "Luxurious room with city view", Arrays.asList("room15.webp", "room16.webp"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("501", 2, 140.0, "Standard", "Cozy room with city view", Arrays.asList("room17.webp", "room18.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("502", 4, 200.0, "Suite", "Spacious suite with pool view", Arrays.asList("room19.webp", "room20.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Vancouver", "Canada", 90, "Vancouver",
                        "Charming and welcoming, Hotel Vancouver is perfect for travelers seeking a serene escape. Enjoy a complimentary breakfast each morning, and stay active at the gym. The hotel’s classic design and warm service make for an unforgettable stay.",
                        160.0,
                        Arrays.asList("image11.avif", "image12.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        ),
                        Arrays.asList(
                                new Room("601", 1, 120.0, "Single", "Charming single room with city view", Arrays.asList("room21.avif", "room22.jpg"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(6),
                                        LocalDate.now().plusDays(10)),
                                new Room("602", 2, 180.0, "Double", "Comfortable double room with city view", Arrays.asList("room23.webp", "room24.avif"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("701", 2, 170.0, "Deluxe", "Elegant room with city view", Arrays.asList("room25.avif", "room26.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("702", 4, 250.0, "Suite", "Luxurious suite with Eiffel Tower view", Arrays.asList("room27.webp", "room28.avif"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("801", 2, 160.0, "Standard", "Comfortable room with historical view", Arrays.asList("room29.avif", "room30.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("802", 3, 220.0, "Suite", "Spacious suite with ancient city view", Arrays.asList("room31.avif", "room32.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("901", 2, 210.0, "Deluxe", "Stylish room with city view", Arrays.asList("room33.webp", "room34.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("902", 4, 300.0, "Suite", "Luxurious suite with Tokyo skyline view", Arrays.asList("room35.jpg", "room36.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("1001", 2, 120.0, "Standard", "Cozy room with traditional décor", Arrays.asList("room37.avif", "room38.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now().plusDays(1),
                                        LocalDate.now().plusDays(10)),
                                new Room("1002", 3, 180.0, "Family", "Spacious room with traditional Japanese design", Arrays.asList("room39.avif", "room40.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
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
                        ),
                        Arrays.asList(
                                new Room("1101", 2, 200.0, "Deluxe", "Luxurious room with harbor view", Arrays.asList("room41.webp", "room42.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1102", 4, 300.0, "Suite", "Elegant suite with panoramic view", Arrays.asList("room43.webp", "room44.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(13))
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
                        ),
                        Arrays.asList(
                                new Room("1201", 2, 180.0, "Standard", "Modern room with city view", Arrays.asList("room45.webp", "room46.avif"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1202", 3, 250.0, "Suite", "Spacious suite with cityscape view", Arrays.asList("room47.webp", "room48.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(7))
                        )
                ),
                new Hotel("Hotel Barcelona", "Spain", 110, "Barcelona",
                        "Cozy hotel in Barcelona",
                        180.0,
                        Arrays.asList("image31.avif", "image32.avif"),
                        Arrays.asList(
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png")
                        ),
                        Arrays.asList(
                                new Room("1301", 2, 140.0, "Standard", "Comfortable room with city view", Arrays.asList("room49.webp", "room50.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(20)),
                                new Room("1302", 4, 200.0, "Suite", "Spacious suite with city view", Arrays.asList("room51.webp", "room52.avif"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Beijing", "China", 180, "Beijing",
                        "Modern hotel in the heart of Beijing",
                        260.0,
                        Arrays.asList("image33.webp", "image34.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("1401", 2, 200.0, "Standard", "Modern room with city view", Arrays.asList("room53.webp", "room54.webp"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1402", 4, 280.0, "Suite", "Luxurious suite with panoramic city view", Arrays.asList("room55.avif", "room56.jpg"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Madrid", "Spain", 140, "Madrid",
                        "Elegant hotel in the vibrant city of Madrid",
                        200.0,
                        Arrays.asList("image35.jpeg", "image36.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png")
                        ),
                        Arrays.asList(
                                new Room("1501", 2, 160.0, "Standard", "Elegant room with city view", Arrays.asList("room57.webp", "room58.avif"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1502", 3, 220.0, "Suite", "Luxurious suite with city view", Arrays.asList("room59.avif", "room60.webp"),
                                        Arrays.asList(
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Buenos Aires", "Argentina", 160, "Buenos Aires",
                        "Luxurious hotel in Buenos Aires",
                        220.0,
                        Arrays.asList("image37.avif", "image38.webp"),
                        Arrays.asList(
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Parking", "/icons/parking.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("1601", 2, 180.0, "Standard", "Luxurious room with city view", Arrays.asList("room61.webp", "room62.avif"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1602", 4, 250.0, "Suite", "Spacious suite with city view", Arrays.asList("room63.avif", "room64.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Parking", "/icons/parking.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Cape Town", "South Africa", 130, "Cape Town",
                        "Stylish hotel with stunning views of Cape Town",
                        240.0,
                        Arrays.asList("image39.avif", "image40.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        ),
                        Arrays.asList(
                                new Room("1701", 2, 200.0, "Standard", "Stylish room with harbor view", Arrays.asList("room65.avif", "room66.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(5)),
                                new Room("1702", 4, 270.0, "Suite", "Luxurious suite with panoramic view", Arrays.asList("room67.webp", "room68.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(17))
                        )
                ),
                new Hotel("Hotel Athens", "Greece", 110, "Athens",
                        "Historic hotel in Athens",
                        180.0,
                        Arrays.asList("image41.avif", "image42.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("1801", 2, 140.0, "Standard", "Historic room with city view", Arrays.asList("room69.webp", "room70.webp"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1802", 3, 200.0, "Suite", "Spacious suite with ancient city view", Arrays.asList("room71.avif", "room72.jpg"),
                                        Arrays.asList(
                                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(9))
                        )
                ),
                new Hotel("Hotel Dubai", "UAE", 200, "Dubai",
                        "Luxurious hotel in Dubai",
                        300.0,
                        Arrays.asList("image43.avif", "image44.avif"),
                        Arrays.asList(
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png")
                        ),
                        Arrays.asList(
                                new Room("1901", 2, 250.0, "Deluxe", "Luxurious room with city view", Arrays.asList("room73.webp", "room74.avif"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("1902", 4, 320.0, "Suite", "Elegant suite with panoramic view", Arrays.asList("room75.avif", "room76.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Doha", "Qatar", 150, "Doha",
                        "Elegant hotel in Doha",
                        220.0,
                        Arrays.asList("image45.avif", "image46.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2001", 2, 180.0, "Standard", "Elegant room with city view", Arrays.asList("room77.webp", "room78.avif"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2002", 3, 250.0, "Suite", "Luxurious suite with panoramic view", Arrays.asList("room79.avif", "room80.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Moscow", "Russia", 170, "Moscow",
                        "Sophisticated hotel in Moscow",
                        260.0,
                        Arrays.asList("image47.avif", "image48.avif"),
                        Arrays.asList(
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Bar", "/icons/bar.png"),
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Gym", "/icons/gym.png")
                        ),
                        Arrays.asList(
                                new Room("2101", 2, 220.0, "Deluxe", "Sophisticated room with city view", Arrays.asList("room81.avif", "room82.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(7)),
                                new Room("2102", 4, 300.0, "Suite", "Elegant suite with panoramic view", Arrays.asList("room83.webp", "room84.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(12))
                        )
                ),
                new Hotel("Hotel Mumbai", "India", 130, "Mumbai",
                        "Luxurious hotel in Mumbai",
                        200.0,
                        Arrays.asList("image49.avif", "image50.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2201", 2, 160.0, "Standard", "Comfortable room with city view", Arrays.asList("room85.jpg", "room86.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2202", 4, 230.0, "Suite", "Spacious suite with city view", Arrays.asList("room87.avif", "room88.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Bangalore", "India", 120, "Bangalore",
                        "Modern hotel in Bangalore",
                        180.0,
                        Arrays.asList("image51.avif", "image52.avif"),
                        Arrays.asList(
                                new Amenity("Free Breakfast", "/icons/breakfast.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2301", 2, 140.0, "Standard", "Modern room with city view", Arrays.asList("room89.avif", "room90.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2302", 3, 200.0, "Suite", "Spacious suite with city view", Arrays.asList("room91.webp", "room92.webp"),
                                        Arrays.asList(
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Bar", "/icons/bar.png"),
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(8))
                        )
                ),
                new Hotel("Hotel Singapore", "Singapore", 180, "Singapore",
                        "Luxurious hotel in Singapore",
                        280.0,
                        Arrays.asList("image53.avif", "image54.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2401", 2, 220.0, "Deluxe", "Luxurious room with city view", Arrays.asList("room93.webp", "room94.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2402", 4, 320.0, "Suite", "Elegant suite with panoramic view", Arrays.asList("room95.webp", "room96.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Hong Kong", "Hong Kong", 150, "Hong Kong",
                        "Modern hotel with stunning harbor views",
                        230.0,
                        Arrays.asList("image55.avif", "image56.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Spa", "/icons/spa.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2501", 2, 190.0, "Standard", "Modern room with harbor view", Arrays.asList("room97.webp", "room98.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2502", 3, 260.0, "Suite", "Luxurious suite with panoramic view", Arrays.asList("room99.webp", "room100.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Spa", "/icons/spa.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Seoul", "South Korea", 130, "Seoul",
                        "Chic hotel in Seoul",
                        190.0,
                        Arrays.asList("image57.avif", "image58.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2601", 2, 160.0, "Standard", "Chic room with city view", Arrays.asList("room101.avif", "room102.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2602", 3, 210.0, "Suite", "Spacious suite with city view", Arrays.asList("room103.avif", "room104.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Jakarta", "Indonesia", 140, "Jakarta",
                        "Modern hotel in Jakarta",
                        200.0,
                        Arrays.asList("image59.avif", "image60.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2701", 2, 170.0, "Standard", "Modern room with city view", Arrays.asList("room105.avif", "room106.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2702", 3, 230.0, "Suite", "Luxurious suite with city view", Arrays.asList("room107.webp", "room108.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Manila", "Philippines", 110, "Manila",
                        "Comfortable hotel in Manila",
                        170.0,
                        Arrays.asList("image61.avif", "image62.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2801", 2, 140.0, "Standard", "Comfortable room with city view", Arrays.asList("room109.webp", "room110.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2802", 3, 190.0, "Suite", "Spacious suite with city view", Arrays.asList("room111.avif", "room112.avif"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                ),
                new Hotel("Hotel Kuala Lumpur", "Malaysia", 120, "Kuala Lumpur",
                        "Modern hotel in Kuala Lumpur",
                        190.0,
                        Arrays.asList("image63.avif", "image64.avif"),
                        Arrays.asList(
                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                new Amenity("Pool", "/icons/pool.png"),
                                new Amenity("Gym", "/icons/gym.png"),
                                new Amenity("Restaurant", "/icons/restaurant.png")
                        ),
                        Arrays.asList(
                                new Room("2901", 2, 150.0, "Standard", "Modern room with city view", Arrays.asList("room113.avif", "room114.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10)),
                                new Room("2902", 3, 200.0, "Suite", "Spacious suite with city view", Arrays.asList("room115.webp", "room116.webp"),
                                        Arrays.asList(
                                                new Amenity("Free WiFi", "/icons/wifi.png"),
                                                new Amenity("Pool", "/icons/pool.png"),
                                                new Amenity("Gym", "/icons/gym.png"),
                                                new Amenity("Restaurant", "/icons/restaurant.png")
                                        ),LocalDate.now(),
                                        LocalDate.now().plusDays(10))
                        )
                )
        );

        hotelRepository.saveAll(hotels);
        for (Hotel hotel : hotels) {
            for (Room room : hotel.getRooms()) {
                room.setHotel(hotel);
                roomRepository.save(room); // Odayı veritabanına kaydet
            }

        }


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
