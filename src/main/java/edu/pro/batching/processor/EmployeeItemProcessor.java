package edu.pro.batching.processor;
/*
  @author   george
  @project   batching
  @class  EmployeeItemProcessor
  @version  1.0.0 
  @since 18.04.22 - 11.46
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.pro.batching.model.Employee;
import edu.pro.batching.model.EmployeeDetail;
import org.springframework.batch.item.ItemProcessor;


public class EmployeeItemProcessor implements ItemProcessor<EmployeeDetail, Employee> {
    private static final Logger log = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Override
    public Employee process(EmployeeDetail item) throws Exception {
        log.info("processing employee data.....{}", item);
        Employee employee = new Employee();
        employee.setName(item.getName());
        employee.setEmail(item.getEmail());
        employee.setCountry(item.getCountry());
        employee.setTeam(item.getTeam());
        return employee;
    }

}
