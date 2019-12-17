package com.ayur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.model.Product;
import com.ayur.repository.ProductRepository;

@Service
public class ProductService extends AbstractService<Product, Long>{
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
