package com.pmpt.Impl.ServiceImpl;

import com.pmpt.entities.GoodsPic;
import com.pmpt.interfaces.Dao.GoodPicDao;
import com.pmpt.interfaces.Service.GoodsPicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodPicServiceImpl implements GoodsPicService {

	@Autowired
    private GoodPicDao goodPicDao;

    @Override
	public void addGoodPic(GoodsPic pic) {
        goodPicDao.savePic(pic);
    }
}
