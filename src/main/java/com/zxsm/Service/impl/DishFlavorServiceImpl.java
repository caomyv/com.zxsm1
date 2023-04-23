package com.zxsm.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxsm.Service.DishFlavorService;
import com.zxsm.entity.DishFlavor;
import com.zxsm.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
