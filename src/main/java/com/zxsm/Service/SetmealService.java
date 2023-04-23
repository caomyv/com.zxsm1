package com.zxsm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxsm.Dto.SetmealDto;
import com.zxsm.entity.Setmeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐,同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    @Transactional
    void removeWithDish(List<Long> ids);

    public SetmealDto getByIdWithDish(Long id);

    @Transactional
    public void updateWithDish(SetmealDto setmealDto);
}
