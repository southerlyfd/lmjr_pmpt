package com.pmpt.interfaces.Service;


import java.util.List;

import com.pmpt.entities.Bill;
import com.pmpt.entities.BillVo;
import com.pmpt.entities.Locate;
import com.pmpt.entities.Pterocarpus;

public interface PublishService {
    List<Locate> getLocate();

    List<Pterocarpus> getPterocarpus(Long id);

	BillVo addBill(Bill bill);
	
	List<Object[]> exhibitContinent();
	
	List<Object[]> countrys(Integer continentId);
}
