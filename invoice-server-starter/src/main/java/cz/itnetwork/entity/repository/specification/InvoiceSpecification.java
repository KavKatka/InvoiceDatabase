package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import cz.itnetwork.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Expr;
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
            Expression<String> productJoin = root.join(InvoiceEntity_.PRODUCT);
            predicateList.add(productJoin.in(filter.getProduct()));
        }
        if(filter.getMinPrice() != null){
            Join<InvoiceEntity,PersonEntity> minPriceJoin = root.join(InvoiceEntity_.PRICE);
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(minPriceJoin.get(InvoiceEntity_.PRICE),filter.getMinPrice()));
        }
        if (filter.getMaxPrice() != null){
            Join<InvoiceEntity, PersonEntity> maxPriceJoin = root.join(InvoiceEntity_.PRICE);
            predicateList.add(criteriaBuilder.lessThanOrEqualTo(maxPriceJoin.get(InvoiceEntity_.PRICE), filter.getMaxPrice()));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
