package com.kisankrishibazar.dao;

import com.kisankrishibazar.model.User;

public interface LoginDAO
{

	User  getUserDetail (String  userName  , String Password);
	
}
