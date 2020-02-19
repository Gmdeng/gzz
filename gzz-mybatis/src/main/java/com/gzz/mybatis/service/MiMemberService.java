package com.gzz.mybatis.service;

import com.gzz.mybatis.dao.MiMemberMapper;
import com.gzz.mybatis.pojo.MiMember;
import com.gzz.mybatis.pojo.MiMemberExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiMemberService {
    @Autowired
    private MiMemberMapper miMemberMapper;

    public MiMember getMiMember(String memberNo){
        MiMemberExample example = new MiMemberExample();
        example.createCriteria().andMemberNoEqualTo(memberNo);

        MiMember member = miMemberMapper.selectByExample(example);

        return member;
    }

    public List<MiMember> getMemberList(){
        String memberNo = "";
        MiMemberExample example = new MiMemberExample();
        example.createCriteria().andMemberNoEqualTo(memberNo);
        long total = miMemberMapper.countByExample(example);
        example.setPageNo(2);
        example.setPageSize(10);
        List<MiMember> list = miMemberMapper.selectByExamples(example);
        return list;
    }
    public void generateMember(MiMember member){
        miMemberMapper.insert(member);
    }

    public void deleteMember(MiMember member){
        miMemberMapper.deleteByPrimaryKey(member.getId());
    }
}
