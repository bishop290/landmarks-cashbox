package org.aston.orders.servises;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestDto;
import org.aston.orders.dto.OrderResponseDto;
import org.aston.orders.exception.OrderNotFoundException;
import org.aston.orders.mappers.OrderMapper;
import org.aston.orders.model.Order;
import org.aston.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    public OrderResponseDto find(Long id) {
        return mapper.entityToDto(order(id));
    }

    @Override
    public DefaultResponse create(OrderRequestDto dto) {
        repository.save(mapper.dtoToEntity(dto));
        return new DefaultResponse(true, "Order saved");
    }

    @Override
    public DefaultResponse update(Long id, OrderRequestDto dto) {
        repository.save(mapper.dtoToEntityUpdate(dto, order(id)));
        return new DefaultResponse(true, "Order updated");
    }

    private Order order(long id) {
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }
}
