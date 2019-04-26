package ru.job4j.models;

import java.io.Serializable;
import java.util.Objects;

public class BrandModel implements Serializable {
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    private long id;
    private long brandId;
    private long modelId;

    public BrandModel() {
    }

    public BrandModel(long brandId, long modelId) {
        this.brandId = brandId;
        this.modelId = modelId;
    }

    public BrandModel(long id) {
        this.id = id;
    }

    public long getBrandId() {
        return this.brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getModelId() {
        return this.modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandModel that = (BrandModel) o;
        return brandId == that.brandId &&
                modelId == that.modelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, modelId);
    }
}
