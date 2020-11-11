package rbac;

import cn.wolfcode.rbac.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mvc.xml")
public class TestDepartmentMapper {
    @Autowired
   private DepartmentMapper departmentMapper;



    @Test
    public void test(){
        System.out.println(departmentMapper.listDepartment());
        System.out.println(departmentMapper.selectById(2L));
    }
}
