package com.example.sneaker.mapper.cart;

import com.example.sneaker.model.entity.Branch;
import com.example.sneaker.model.entity.Cart;
import com.example.sneaker.model.entity.Category;
import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.entity.Size;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.model.response.branch.BranchResponse;
import com.example.sneaker.model.response.cart.CartResponse;
import com.example.sneaker.model.response.category.CategoryResponse;
import com.example.sneaker.model.response.product.ProductResponse;
import com.example.sneaker.model.response.size.SizeResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart toEntity(CartRequest cartRequest) {
        if ( cartRequest == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        cart.id( cartRequest.getId() );
        cart.quantity( cartRequest.getQuantity() );
        cart.size( cartRequest.getSize() );

        return cart.build();
    }

    @Override
    public CartResponse toDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse cartResponse = new CartResponse();

        cartResponse.setId( cart.getId() );
        cartResponse.setQuantity( cart.getQuantity() );
        cartResponse.setSize( cart.getSize() );
        cartResponse.setProduct( productToProductResponse( cart.getProduct() ) );

        return cartResponse;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setName( category.getName() );
        categoryResponse.setSlug( category.getSlug() );

        return categoryResponse;
    }

    protected BranchResponse branchToBranchResponse(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchResponse branchResponse = new BranchResponse();

        branchResponse.setName( branch.getName() );
        branchResponse.setSlug( branch.getSlug() );

        return branchResponse;
    }

    protected SizeResponse sizeToSizeResponse(Size size) {
        if ( size == null ) {
            return null;
        }

        SizeResponse sizeResponse = new SizeResponse();

        sizeResponse.setSize( size.getSize() );
        sizeResponse.setQuantity( size.getQuantity() );

        return sizeResponse;
    }

    protected List<SizeResponse> sizeListToSizeResponseList(List<Size> list) {
        if ( list == null ) {
            return null;
        }

        List<SizeResponse> list1 = new ArrayList<SizeResponse>( list.size() );
        for ( Size size : list ) {
            list1.add( sizeToSizeResponse( size ) );
        }

        return list1;
    }

    protected ProductResponse productToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setThumbnail( product.getThumbnail() );
        productResponse.setDesciption( product.getDesciption() );
        productResponse.setImageDesc( product.getImageDesc() );
        productResponse.setDiscountPercent( product.getDiscountPercent() );
        productResponse.setPrice( product.getPrice() );
        productResponse.setSlug( product.getSlug() );
        productResponse.setCategory( categoryToCategoryResponse( product.getCategory() ) );
        productResponse.setBranch( branchToBranchResponse( product.getBranch() ) );
        productResponse.setSizes( sizeListToSizeResponseList( product.getSizes() ) );

        return productResponse;
    }
}
