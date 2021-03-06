package com.pmpt.Impl.ServiceImpl;

import com.pmpt.entities.Bill;
import com.pmpt.entities.BillVo;
import com.pmpt.entities.Locate;
import com.pmpt.entities.Pterocarpus;
import com.pmpt.interfaces.Dao.PublishDAO;
import com.pmpt.interfaces.Service.PublishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishDAO publishDAO;

    @Override
    public List<Locate> getLocate() {
        return publishDAO.getAllLocate();
    }

    @Override
    public List<Pterocarpus> getPterocarpus(Long id) {
        return publishDAO.getPterocarpusByLocateId(id);
    }

    @Override
    @Transactional
	public BillVo addBill(Bill bill) {
        publishDAO.addBill(bill);
        BillVo vo = new BillVo();
        vo.setId(bill.getId());
        vo.setPledgePrice(bill.getPledgePrice());
        vo.setFee(bill.getFee());
        return vo;
    }

	@Override
	public List<Object[]> exhibitContinent() {
		return publishDAO.exhibitContinent();
	}

	@Override
	public List<Object[]> countrys(Integer continentId) {
		return publishDAO.countrys(continentId);
	}
}
