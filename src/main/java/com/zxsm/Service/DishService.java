package com.zxsm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxsm.Dto.DishDto;
import com.zxsm.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
