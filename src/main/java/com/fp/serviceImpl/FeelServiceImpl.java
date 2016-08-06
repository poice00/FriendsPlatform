package com.fp.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.Feel;
import com.fp.service.FeelService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class FeelServiceImpl extends DaoSupportImpl<Feel> implements FeelService {

}
