package com.digitalsoftware.accounting.mapper.repositories;

import com.digitalsoftware.accounting.domain.generated.UserLog;
import com.digitalsoftware.accounting.domain.models.LogDTO;
import com.digitalsoftware.accounting.mapper.DAO.UserLogMapper;
import com.digitalsoftware.accounting.mapper.RootRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserLogRepositoryImpl extends RootRepository<UserLog, Integer> implements UserLogRepository {

    private final UserLogMapper userLogMapper;


    @Autowired
    public UserLogRepositoryImpl(UserLogMapper userLogMapper) {
        this.userLogMapper = userLogMapper;
    }

    @Override
    public Optional<UserLog> findOne(Integer id) {
        return genericFindOne(id);
    }

    @Override
    public Integer save(UserLog record) {
        return genericSave(record);
    }

    @Override
    public Integer delete(Integer id) {
        return genericDelete(id);
    }


    @Override
    public PageInfo<LogDTO> findByPage(int pageNum, int limit, String orderBy, List<String> methods) {

        PageHelper.startPage(pageNum, limit, orderBy);
        return PageInfo.of(userLogMapper.findAllByMethods(methods));
    }

}
