package springContext.dto;

import javax.persistence.*;

/**
 * Created by Martha on 7/29/2016.
 */
@Entity
@Table(name = "productInOrder")
public class ProductInOrderDTO {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    private ProductDTO productDTO;

    @OneToOne
    private OrderDTO order;

    private Integer amount;

    private boolean isActive;

    public ProductInOrderDTO() {
    }

    public ProductInOrderDTO(ProductDTO productDTO, OrderDTO order, Integer amount, boolean isActive) {
        this.productDTO = productDTO;
        this.order = order;
        this.amount = amount;
        this.isActive = isActive;
    }

    public void activate(){
        isActive = true;
    }

    public void cancel(){
        isActive = false;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
