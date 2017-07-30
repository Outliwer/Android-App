package com.example.outlier.prictace_1.HomePage.Search;

import java.util.List;

/**
 * Created by outlier on 2017/7/14.
 */

public class SearchPriceListEntity {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<EntityListEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<EntityListEntity> entityList) {
        this.entityList = entityList;
    }

    private String type;
    List<EntityListEntity> entityList;
}
