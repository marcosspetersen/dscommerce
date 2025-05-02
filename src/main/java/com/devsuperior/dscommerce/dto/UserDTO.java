package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private List<String> roles = new ArrayList<>();

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        for(GrantedAuthority role: entity.getAuthorities()){
            roles.add(role.getAuthority());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class OrderDTO {

        private Long id;
        private Instant moment;
        private OrderStatus status;

        private ClientDTO client;

        private PaymentDTO payment;

        @NotEmpty(message = "Deve haver ao menos um Item")
        private List<OrderItemDTO> items = new ArrayList<>();

        public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
            this.id = id;
            this.moment = moment;
            this.status = status;
            this.client = client;
            this.payment = payment;
        }

        public OrderDTO(Order entity) {
            id = entity.getId();
            moment = entity.getMoment();
            status = entity.getStatus();
            client = new ClientDTO(entity.getClient());
            payment = (entity.getPayment() != null) ? new PaymentDTO(entity.getPayment()) : null;

            entity.getItems().forEach(itemDTO -> items.add(new OrderItemDTO(itemDTO)));
        }

        public Long getId() {
            return id;
        }

        public Instant getMoment() {
            return moment;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public ClientDTO getClient() {
            return client;
        }

        public PaymentDTO getPayment() {
            return payment;
        }

        public List<OrderItemDTO> getItems() {
            return items;
        }

        public Double getTotal() {
            return items.stream().mapToDouble(OrderItemDTO::getSubTotal).sum();
        }
    }

    public static class OrderItemDTO {

        private Long productId;
        private String name;
        private Double price;
        private Integer quantity;
        private String imgUrl;

        public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.imgUrl = imgUrl;
        }

        public OrderItemDTO(OrderItem entity) {
            productId = entity.getProduct().getId();
            name = entity.getProduct().getName();
            price = entity.getPrice();
            quantity = entity.getQuantity();
            imgUrl = entity.getProduct().getImgUrl();
        }

        public Long getProductId() {
            return productId;
        }

        public String getName() {
            return name;
        }

        public Double getPrice() {
            return price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Double getSubTotal() {
            return price * quantity;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }
}
