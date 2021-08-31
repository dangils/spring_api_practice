package com.koreait.day3_2.Service;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.entity.Category;
import com.koreait.day3_2.model.entity.OrderGroup;
import com.koreait.day3_2.model.enumclass.OrderType;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.CategoryApiRequest;
import com.koreait.day3_2.model.network.request.OrderGroupApiRequest;
import com.koreait.day3_2.model.network.response.CategoryApiResponse;
import com.koreait.day3_2.model.network.response.OrderGroupApiResponse;
import com.koreait.day3_2.model.network.response.Pagination;
import com.koreait.day3_2.repository.OrderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor


public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {


    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .id(orderGroupApiRequest.getId())
                .orderType(orderGroupApiRequest.getOrderType())
                .status(orderGroupApiRequest.getStatus())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .paymentType(orderGroupApiRequest.getPaymentType())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .orderAt(orderGroupApiRequest.getOrderAt())
                .arrivalDate(orderGroupApiRequest.getArrivalDate())
                .build();
       OrderGroup newOrderGroup= baseRepository.save(orderGroup);
        return Header.OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
       return null;
    }

    @Override
    public Header delete(Long id) {
       return null;
    }

    public OrderGroupApiResponse response(OrderGroup orderGroup){
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .build();
        return orderGroupApiResponse;
    }

    public Header<List<OrderGroupApiResponse>> search(Pageable pageable){
        Page<OrderGroup> orderGroups = baseRepository.findAll(pageable);
        List<OrderGroupApiResponse> orderGroupApiResponses = orderGroups.stream()
                .map(orderGroup -> response(orderGroup))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(orderGroups.getTotalPages())
                .totalElements(orderGroups.getTotalElements())
                .currentPage(orderGroups.getNumber())
                .currentElements(orderGroups.getNumberOfElements())
                .build();
        return Header.OK(orderGroupApiResponses, pagination);
    }



}
