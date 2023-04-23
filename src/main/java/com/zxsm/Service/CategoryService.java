package com.zxsm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxsm.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
