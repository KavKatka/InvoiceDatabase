package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import cz.itnetwork.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {

    private final InvoiceFilter filter;

    /**
     * Filters
     */
    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (filter.getSellerID() != null){
            Join<InvoiceEntity, PersonEntity> sellerJoin = root.join(InvoiceEntity_.SELLER);
            predicateList.add(criteriaBuilder.equal(sellerJoin.get(PersonEntity_.ID), filter.getSellerID()));
        }
        if (filter.getBuyerID() != null){
            Join<InvoiceEntity, PersonEntity> buyerJoin = root.join(InvoiceEntity_.BUYER);
            predicateList.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.ID), filter.getBuyerID()));
        }
        if(filter.getProduct() != null){
          //  predicateList.add(criteriaBuilder.equal())
        }
        if(filter.getMinPrice() != null){

        }
        if (filter.getMaxPrice() != null){

        }
        return null;
    }
}
