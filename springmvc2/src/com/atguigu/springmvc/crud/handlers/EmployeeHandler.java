package com.atguigu.springmvc.crud.handlers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springmvc.crud.dao.DepartmentDao;
import com.atguigu.springmvc.crud.dao.EmployeeDao;
import com.atguigu.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;
	//说明：员工和部门存在着单向多对一的关系

	/**
	 * 显示所有员工信息
	 * @param map
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(Map<String,Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}

	/**
	 * 显示目标页面（表单页面）
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}

	/**
	 * 新增员工
	 * @param employee
	 * @param result
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String save(@Valid Employee employee,
					   Errors result,
					   Map<String,Object> map){
		System.out.println("save: " + employee);

		if(result.getErrorCount() > 0){
			System.out.println("出错了!");

			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}

			//若验证出错, 则转向定制的页面
			map.put("departments", departmentDao.getDepartments());
			return "input";
		}

		employeeDao.save(employee);
		return "redirect:/emps";
	}

	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}

	/**
	 * 修改员工之前回显表单数据
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map){
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}

	/**
	 * 修改员工
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);

		return "redirect:/emps";
	}

	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false)Integer id,
							Map<String, Object> map){
		//id不为空，说明是修改操作
		if(id != null){
			map.put("employee", employeeDao.get(id));
		}
	}

//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setDisallowedFields("lastName");
//	}
	
}
