package uz.pdp.task2.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {


    private String  name;

    private boolean isDiscounted;

    private boolean isAvailable;

    private String  description;

    private String  factoryName;

    private boolean isOnlyInCollections;

    private boolean isRecommend;

    private List<Integer> categoryIds;

    private Integer photoId;
}
