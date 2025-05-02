package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderDTO;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado"));
        return new OrderDTO(order);
    }

//    @Transactional
//    public OrderDTO insert(OrderDTO dto) {
//        Order entity = new Order();
//        copyDtoToEntity (dto, entity);
//        entity = repository.save(entity);
//        return new OrderDTO(entity);
//    }
//
//    private void copyDtoToEntity(OrderDTO dto, Order entity) {
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setPrice(dto.getPrice());
//        entity.setImgUrl(dto.getImgUrl());
//        entity.getCategories().clear();
//        dto.getCategories().stream()
//                .map(catDto -> {
//                    Category category = new Category();
//                    category.setId(catDto.getId());
//                    return category;
//                })
//                .forEach(entity.getCategories()::add);
//    }
}
