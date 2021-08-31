package com.koreait.day3_2.Service;

import com.koreait.day3_2.ifs.CrudInterface;
import com.koreait.day3_2.model.entity.Category;
import com.koreait.day3_2.model.entity.Partner;
import com.koreait.day3_2.model.network.Header;
import com.koreait.day3_2.model.network.request.PartnerApiRequest;
import com.koreait.day3_2.model.network.response.CategoryApiResponse;
import com.koreait.day3_2.model.network.response.Pagination;
import com.koreait.day3_2.model.network.response.PartnerApiResponse;
import com.koreait.day3_2.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor

public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    private  final PartnerRepository partnerRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                .id(partnerApiRequest.getId())
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .regDate(LocalDateTime.now())
                .build();
        Partner newPartner = partnerRepository.save(partner);
        return Header.OK(response(newPartner));
    }
    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        return null;
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = partnerRepository.findById(id);
        return optional.map(partner -> {
            partnerRepository.delete(partner);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .regDate(partner.getRegDate())
                .build();
        return partnerApiResponse;
    }


    public Header<List<PartnerApiResponse>> search(Pageable pageable){
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map(partner -> response(partner))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();
        return Header.OK(partnerApiResponseList, pagination);
    }


}
