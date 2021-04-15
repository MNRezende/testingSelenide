package tests;

import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;


public class LoginTests extends BaseTest {

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"reink@ninjaplus.com", "abc123", "Usuário e/ou senha inválidos"},
                {"batman@ninjaplus.com", "pwd123", "Usuário e/ou senha inválidos"},
                {"", "abc123", "Opps. Cadê o email?"},
                {"reink@ninjaplus.com", "", "Opps. Cadê a senha?"}

        };
    }

    @Test
    public void shouldSeeLoggedUser() {

        login
                .open()
                .with("jack@ninjaplus.com", "pwd123");

        side.loggedUser().shouldHave(text("Jack Skellington"));
    }

    //DDT: Data Driven Test = Teste orientado a dados
    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pass, String expectAlert) {

        login
                .open()
                .with(email, pass)
                .alert().shouldHave(text(expectAlert));
    }

    @AfterMethod
    public void cleanUp() {
        login.clearSession();
    }
}
