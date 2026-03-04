package com.curso.xideral.proyectosemanacuatro.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.curso.xideral.proyectosemanacuatro.dto.ProductRequest;
import com.curso.xideral.proyectosemanacuatro.dto.ProductResponse;
import com.curso.xideral.proyectosemanacuatro.exception.ResourceNotFoundException;
import com.curso.xideral.proyectosemanacuatro.models.Product;
import com.curso.xideral.proyectosemanacuatro.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // BONUS ejercicio 6 requiere este overload
    public Page<ProductResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    public ProductResponse create(ProductRequest request) {
        Product p = toEntity(request);
        Product saved = repository.save(p);
        return toResponse(saved);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));

        p.setName(request.nombre());
        p.setPrice(request.precio());
        p.setDescription(request.descripcion());
        p.setCategory(request.categoria());
        p.setStock(request.inventario());

        Product saved = repository.save(p);
        return toResponse(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product", id);
        }
        repository.deleteById(id);
    }

    public List<ProductResponse> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::toResponse)
                .toList();
    }

    private Product toEntity(ProductRequest r) {
        Product p = new Product();
        p.setName(r.nombre());
        p.setPrice(r.precio());
        p.setDescription(r.descripcion());
        p.setCategory(r.categoria());
        p.setStock(r.inventario());
        return p;
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getDescription(),
                p.getCategory(),
                p.getStock(),
                p.getCreatedAt()
        );
    }
}