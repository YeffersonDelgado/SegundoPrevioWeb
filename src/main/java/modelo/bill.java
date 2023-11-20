package modelo;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class bill {

	private Integer id;
	private Date date_bill;
	private Integer User_id;
	private Integer value;
	private Integer type;
	private String observation;
	
	
	public bill(Date date_bill, Integer value, Integer type, String observation) {
		this.date_bill = date_bill;
		
		this.value = value;
		this.type = type;
		this.observation = observation;
	}


	public bill(Integer id, Date date_bill, Integer value, Integer type, String observation) {
	
		this.id = id;
		this.date_bill = date_bill;
		this.value = value;
		this.type = type;
		this.observation = observation;
	}
	
	
	
	
	
}
