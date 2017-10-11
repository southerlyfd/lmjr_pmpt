package com.pmpt.interfaces.Dao;


import java.util.List;

import com.pmpt.entities.Bill;
import com.pmpt.entities.Locate;
import com.pmpt.entities.Pterocarpus;

public interface PublishDAO {
    List<Locate> getAllLocate();

    List<Pterocarpus> getPterocarpusByLocateId(Long id);

	void addBill(Bill bill);
	
	List<Object[]> exhibitContinent();
	
	List<Object[]> countrys(Integer continentId);
}
