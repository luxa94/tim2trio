package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "restaurant")
public class Restaurant {

        @Id
        @GeneratedValue
        private long id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "description", nullable = false)
        private String description;

        @Column(name = "pib", nullable = false, unique = true)
        private String pib;

        @Column(name = "phone_number", nullable = false)
        private String phone_number;

        @Column(name = "address", nullable = false)
        private String address;

        @Column(name = "email", unique = true)
        private String email;

        @OneToMany(fetch = FetchType.LAZY)
        private Set<RestaurantManager> restaurantManagers;

        @OneToMany(fetch = FetchType.LAZY)
        private Set<Auction> auctions;

        @OneToMany(fetch = FetchType.LAZY)
        private Set<Menu> menus;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPib() {
            return pib;
        }

        public void setPib(String pib) {
            this.pib = pib;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Set<RestaurantManager> getRestaurantManagers() {
            return restaurantManagers;
        }

        public void setRestaurantManagers(Set<RestaurantManager> restaurantManagers) {
            this.restaurantManagers = restaurantManagers;
        }

        public Set<Auction> getAuctions() {
            return auctions;
        }

        public void setAuctions(Set<Auction> auctions) {
            this.auctions = auctions;
        }

        public Set<Menu> getMenus() {
            return menus;
        }

        public void setMenus(Set<Menu> menus) {
            this.menus = menus;
        }
}
