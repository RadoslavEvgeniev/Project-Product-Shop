package org.softuni.productshop.service;

import org.softuni.productshop.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    List<OfferServiceModel> findAllOffers();
}
