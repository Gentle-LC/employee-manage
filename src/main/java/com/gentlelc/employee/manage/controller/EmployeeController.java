package com.gentlelc.employee.manage.controller;

import com.gentlelc.employee.manage.domain.AjaxResult;
import com.gentlelc.employee.manage.domain.Employee;
import com.gentlelc.employee.manage.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gentle_LC
 * @version 1.0.0
 * @ClassName EmployeeController
 * @Description
 * @date 2023-07-30 10:45:00
 */
@Api(tags = "职工信息管理")
@RestController
@RequestMapping("/employee")
public class EmployeeController{

    @Autowired
    private EmployeeService employeeService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Employee> queryById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(employeeService.queryById(id));
    }

    /**
     * 分页查询
     *
     * @param employee 筛选条件
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public AjaxResult<Map<String, Object>> paginQuery(Employee employee, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize")Integer pageSize){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Employee> list =  employeeService.pageQuery(employee,pageNum,pageSize);
        PageInfo<Employee> pageInfo=new PageInfo<>(list);
        map.put("total",pageInfo.getTotal());
        map.put("data",list);
        return AjaxResult.success(map);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Employee> add(Employee employee){
        return ResponseEntity.ok(employeeService.insert(employee));
    }

    /**
     * 更新数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Employee> edit(Employee employee){
        return ResponseEntity.ok(employeeService.update(employee));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id){
        return ResponseEntity.ok(employeeService.deleteById(id));
    }
}