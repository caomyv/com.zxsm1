package com.zxsm.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxsm.entity.Employee;
import com.zxsm.mapper.EmployeeMapper;
import com.zxsm.Service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
