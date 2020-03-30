package com.solvd.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.solvd.carina.demo.gui.pages.HomePage;
import com.solvd.carina.demo.gui.pages.SignUpPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SignUpTest extends AbstractTest {

    @Test(dataProvider = "SingleDataProvider")
    @XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "GSMArena-signUp", dsUid = "TUID", dsArgs = "nickname, email, password")
    public void signUpSuccessful(String nickname, String email, String password) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not opened");

        SignUpPage signUpPage = homePage.getTopMenu().signUp();
        assertTrue(signUpPage.isPageOpened(), "Sign up page is not opened");

        signUpPage.signUp(nickname, email, password);
        assertTrue(signUpPage.wasSignUpSuccessful(), "Sign up was unsuccessful");
    }

    @Test(dataProvider = "SingleDataProvider")
    @XlsDataSourceParameters(path = "xls/demo.xlsx", sheet = "GSMArena-signUp", dsUid = "TUID", dsArgs = "nickname, email, password")
    public void signUpReservedNickName(String nickname, String email, String password) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not opened");

        SignUpPage signUpPage = homePage.getTopMenu().signUp();
        assertTrue(signUpPage.isPageOpened(), "Sign up page is not opened");

        signUpPage.signUp(nickname, email, password);

        assertTrue(signUpPage.isNickNameWarningPresent(), "Nickname warning is not present, probably because not duplicated nickname");

    }
}
