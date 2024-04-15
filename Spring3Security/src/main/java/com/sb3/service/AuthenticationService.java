package com.sb3.service;

import com.sb3.beans.LoginRequestBean;
import com.sb3.beans.ServiceResponseBean;
import com.sb3.beans.UserDetailBean;

public interface AuthenticationService {

    ServiceResponseBean registerUser(UserDetailBean userDetailBean);

    ServiceResponseBean loginUser(LoginRequestBean loginRequestBean);

}
