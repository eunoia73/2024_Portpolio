package com.portfolio.www.contact.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.portfolio.www.contact.dto.ContactDto;

@Repository
public interface ContactRepository {
	
	//insert
	public int addContactInfo(ContactDto contactDto);
	
	
}
