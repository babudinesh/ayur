package com.ayur.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.model.Product;

@Service
public class ProductService extends AbstractService<Product, Long>{

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        // TODO Auto-generated method stub
        return null;
    }

}
