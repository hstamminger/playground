package info.selfhost.stammingerit.playground.webapptest.web;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import info.selfhost.stammingerit.playground.webapptest.web.page.HomePage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/testApplicationContext.xml" })
public class HomePageTest {

    @Test
    public void testRenderHomePage() {
        WicketTester homePageTester = new WicketTester();
        homePageTester.startPage(HomePage.class);
        homePageTester.assertRenderedPage(HomePage.class);
        homePageTester.assertContains("version");
        homePageTester.assertContains("loggedin");
    }
}
