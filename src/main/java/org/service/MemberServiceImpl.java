package org.service;

import org.dao.MemberDaoImpl;

public class MemberServiceImpl {
    private MemberDaoImpl memberDao;

    public MemberDaoImpl getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDaoImpl memberDao) {
        this.memberDao = memberDao;
    }

    public void add() {
        System.out.println("MemberServiceImpl.add()");
        memberDao.add();
    }
}
