package com.deanalvero.upb.presenter;

import com.deanalvero.upb.network.ILoginService;
import com.deanalvero.upb.view.LoginActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

//import static org.junit.Assert.*;

/**
 * Created by dean on 09/08/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginActivityPresenterTest {

    private LoginActivityPresenter presenter;

    @Mock
    private LoginActivityView view;

    @Mock
    private ILoginService loginService;

    @Before
    public void setUp(){
        presenter = new LoginActivityPresenter(view, loginService);
    }

    @Test
    public void whenRegisterCalledAndSuccessful(){
        String username = "Test";
        String password = "test123";

        presenter.register(username, password);
        Mockito.verify(view).showProgressDialog();
        Mockito.verify(loginService).register(Matchers.eq("Test"), Matchers.eq("test123"), Matchers.isA(ILoginService.RegisterCallback.class));


    }



}