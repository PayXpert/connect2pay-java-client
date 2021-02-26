package com.payxpert.connect2pay.client.containers;

import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.MaxLength;

public class CartProduct {
  private Integer cartProductId;
  
  @MaxLength(256)
  private String cartProductName;
  
  private Float cartProductUnitPrice;
  
  private Integer cartProductQuantity;
  
  @MaxLength(128)
  private String cartProductBrand;
  
  @MaxLength(128)
  private String cartProductMPN;
  
  @MaxLength(128)
  private String cartProductCategoryName;
  
  private Integer cartProductCategoryID;

  public Integer getCartProductId() {
    return cartProductId;
  }

  public CartProduct setCartProductId(Integer cartProductId) {
    this.cartProductId = cartProductId;
    return this;
  }

  public String getCartProductName() {
    return cartProductName;
  }

  public CartProduct setCartProductName(String cartProductName) {
    this.cartProductName = Utils.limitLength(cartProductName, 256);
    return this;
  }

  public Float getCartProductUnitPrice() {
    return cartProductUnitPrice;
  }

  public CartProduct setCartProductUnitPrice(Float cartProductUnitPrice) {
    this.cartProductUnitPrice = cartProductUnitPrice;
    return this;
  }

  public Integer getCartProductQuantity() {
    return cartProductQuantity;
  }

  public CartProduct setCartProductQuantity(Integer cartProductQuantity) {
    this.cartProductQuantity = cartProductQuantity;
    return this;
  }

  public String getCartProductBrand() {
    return cartProductBrand;
  }

  public CartProduct setCartProductBrand(String cartProductBrand) {
    this.cartProductBrand = Utils.limitLength(cartProductBrand, 128);
    return this;
  }

  public String getCartProductMPN() {
    return cartProductMPN;
  }

  public CartProduct setCartProductMPN(String cartProductMPN) {
    this.cartProductMPN = Utils.limitLength(cartProductMPN, 128);
    return this;
  }

  public String getCartProductCategoryName() {
    return cartProductCategoryName;
  }

  public CartProduct setCartProductCategoryName(String cartProductCategoryName) {
    this.cartProductCategoryName = Utils.limitLength(cartProductCategoryName, 128);
    return this;
  }

  public Integer getCartProductCategoryID() {
    return cartProductCategoryID;
  }

  public CartProduct setCartProductCategoryID(Integer cartProductCategoryID) {
    this.cartProductCategoryID = cartProductCategoryID;
    return this;
  }
}
