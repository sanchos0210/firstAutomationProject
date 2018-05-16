package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.CalendarPage;
import com.abmcloud.cf.test.pages.LoginPage;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import static com.abmcloud.cf.test.API.Conditions.listSizeIsAtLeast;
import static com.abmcloud.cf.test.API.Conditions.visible;

public abstract class API {

    public abstract WebDriver getWebDriver();

    //Common objects
    public static LoginPage loginPage;
    public static AppListPage appListPage;
    public static AppEditPage appEditPage;
    public static CalendarPage calendarPage;

    //-----------------------------------------------CONSTANT VALUES----------------------------------------------------
    //URL constants
    public static final String TEST_PROLINE = "https://test5.cf.abmcloud.com/";
    public static final String TEST_LOTOK = "https://test12.cf.abmcloud.com";
    public static final String APP_FORM_DEMO_DB = "https://demo.cf.abmcloud.com";
    public static final String APP_LIST_DEMO_DB = "https://demo5.cf.abmcloud.com";
    public static final String CALENDAR_DEMO_DB = "https://demo12.cf.abmcloud.com";
    //types of pages constants
    public static final char APP_LIST = 'A';
    public static final char CALENDAR = 'B';
    //--------------------------------------Data information about test users-------------------------------------------
    //General test user
    public static final String USER = "Alexandr Verezhevych";
    public static final String EMAIL = "indonesia.cashflow@gmail.com";
    public static final String PASSWORD = "123456";
    //First test user
    public static final String USER1 = "User1 Test1";
    public static final String EMAIL1 = "user1.indonesia@gmail.com";
    public static final String PASSWORD1 = "123456";
    //Second user
    public static final String USER2 = "User4 Test4";
    public static final String EMAIL2 = "user4.indonesia@ukr.net";
    public static final String PASSWORD2 = "test4";
    //Third user
    public static final String USER3 = "Jack Nicholson";
    public static final String EMAIL3 = "tester.cashflow@gmail.com";
    public static final String PASSWORD3 = "test3";
    //Localize information
    public static final char EN = 'A';
    public static final char RU = 'B';
    //Fields
    public static final char SUM = 'A';
    public static final char CONTRACTOR = 'B';
    public static final char CONTRACTOR_AND_SUM = 'C';
    public static final char INCOME = 'A';
    public static final char OUTCOME = 'B';
    //-----------------------------------Constant names of application status-------------------------------------------
    public static final String NEW = "New";
    public static final String IN_PROGRESS = "In progress";
    public static final String APPROVED = "Approved";

    //--------------------------------Constant names for actions in status----------------------------------------------
    public static final char SEND_FOR_APPROVAL = 'A';
    public static final char APPROVE = 'C';
    public static final char CANCEL = 'E';

    //--------------------------------------------BaseAsserts---------------------------------------------------------------
    public void verificationThat(ExpectedCondition<Boolean> condition) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(getWebDriver(), 4)).until(condition);
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void checkThat(WebElement row, By locator, String text) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(getWebDriver(), 6)).until(
                ExpectedConditions.textToBePresentInElement(row.findElement(locator), text));
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
    //-----------------------------------------BaseAsserts methods------------------------------------------------------
    public boolean isElementPresent(WebElement element) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            waitForElementClickable(2, element);
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public boolean isButtonPresentInRow(WebElement row, By buttonLocator) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            waitForElementClickable(2, row.findElement(buttonLocator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public boolean compare(String referenseValue, String verificationValue) {
        return referenseValue.equals(verificationValue);
    }

    public boolean compare(String[][] referenseArray, String[][] verificationArray) {
        for(int i = 0; i < verificationArray.length; i++) {
            for(int j = 0; j < 2; j++) {
                String referenceValue = referenseArray[i][j];
                String verificationValue = verificationArray[i][j];
                if(referenceValue == null & verificationValue == null) break;
                Asserts asserts = new Asserts();
                asserts.assertTrue(referenceValue.equals(verificationValue));
            }
        }
        return true;
    }

    public boolean compare(String[] referenseArray, String[] verificationArray) {
        for(int i = 0; i < verificationArray.length; i++) {
            String referenceValue = referenseArray[i];
            String verificationValue = verificationArray[i];
            if(referenceValue == null & verificationValue == null) break;
            Asserts asserts = new Asserts();
            asserts.assertTrue(referenceValue.equals(verificationValue));
        }
        return true;
    }
    //--------------------------------------------Waiters---------------------------------------------------------------


    public void loginWait() {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            waitForClickable(20, By.cssSelector("tbody td:nth-of-type(3)"));
        }catch(TimeoutException e) {
            /*TimeoutException a = new TimeoutException("Тест упал. Логин занял больше 20 секунд.");
            throw a;*/
            System.out.println("Неуспешный логин или в списке нету заявок");
        }
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void waitForClickable(By locator) {
        waitForClickable(4, locator);
    }

    public void waitForClickable(int seconds, By locator) {
        (new WebDriverWait(getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementClickable(int seconds, WebElement element) {
        (new WebDriverWait(getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void preloadWait() {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                (By.cssSelector("div#preloader"), "style",
                        "display: block; background: none rgba(0, 0, 0, 0.7);"));
        (new WebDriverWait(getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                (By.cssSelector("div#preloader"), "style",
                        "display: none; background: none;"));
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void calendarPreloadWait() {
        verificationThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: block;"));
        verificationThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: none;"));
        waitForClickable(5, By.cssSelector(".cell-gr1 calendar-table-cell"));
    }
    //------------------------------------------------------------------------------------------------------------------

    class ProxiedWebElement implements WebElement{

        public void click() {

        }

        public void submit() {

        }

        public void sendKeys(CharSequence... keysToSend) {

        }

        public void clear() {

        }

        public String getTagName() {
            return null;
        }

        public String getAttribute(String name) {
            return null;
        }

        public boolean isSelected() {
            return false;
        }

        public boolean isEnabled() {
            return false;
        }

        public String getText() {
            return null;
        }

        public List<WebElement> findElements(By by) {
            return null;
        }

        public WebElement findElement(By by) {
            return null;
        }

        public boolean isDisplayed() {
            return false;
        }

        public Point getLocation() {
            return null;
        }

        public Dimension getSize() {
            return null;
        }

        public Rectangle getRect() {
            return null;
        }

        public String getCssValue(String propertyName) {
            return null;
        }

        public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
            return null;
        }
    }

    class ElementFinderProxy implements InvocationHandler {

        private By elementLocator;

        private ElementFinderProxy(By elementLocator) {
            this.elementLocator = elementLocator;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(assertThat(visible(elementLocator)), args);
            } catch (InvocationTargetException e) {
                // Unwrap the underlying exception
                throw e.getCause();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    private Object newElementFinderProxyInstance(Object obj, By elementLocator){
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new ElementFinderProxy(elementLocator));
    }

    protected WebElement $(By locator){
        return (WebElement) newElementFinderProxyInstance(new ProxiedWebElement(), locator);
    }

    protected WebElement $(String cssSelector){
        return $(By.cssSelector(cssSelector));
    }

    //------------------------------------------------------------------------------------------------------------------
    class ListOfWebElementsBait implements List<WebElement>{

        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean contains(Object o) {
            return false;
        }

        public Iterator<WebElement> iterator() {
            return null;
        }

        public Object[] toArray() {
            return new Object[0];
        }

        public <T> T[] toArray(T[] a) {
            return null;
        }

        public boolean add(WebElement element) {
            return false;
        }

        public boolean remove(Object o) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return false;
        }

        public boolean addAll(Collection<? extends WebElement> c) {
            return false;
        }

        public boolean addAll(int index, Collection<? extends WebElement> c) {
            return false;
        }

        public boolean removeAll(Collection<?> c) {
            return false;
        }

        public boolean retainAll(Collection<?> c) {
            return false;
        }

        public void clear() {

        }

        public WebElement get(int index) {
            return null;
        }

        public WebElement set(int index, WebElement element) {
            return null;
        }

        public void add(int index, WebElement element) {

        }

        public WebElement remove(int index) {
            return null;
        }

        public int indexOf(Object o) {
            return 0;
        }

        public int lastIndexOf(Object o) {
            return 0;
        }

        public ListIterator<WebElement> listIterator() {
            return null;
        }

        public ListIterator<WebElement> listIterator(int index) {
            return null;
        }

        public List<WebElement> subList(int fromIndex, int toIndex) {
            return null;
        }
    }

    class ElementsFinderProxy implements InvocationHandler{

        private By elementsLocator;

        private ElementsFinderProxy(By elementLocator) {
            this.elementsLocator = elementLocator;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(getWebDriver().findElements(elementsLocator), args);
            } catch (InvocationTargetException e) {
                // Unwrap the underlying exception
                throw e.getCause();
            }
        }
    }

    private Object newElementsFinderProxyInstance(Object obj, By elementsLocator){
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new ElementsFinderProxy(elementsLocator));
    }

    protected List<WebElement> $$(By locator){
        return (List<WebElement>) newElementsFinderProxyInstance(new ListOfWebElementsBait(), locator);
    }

    protected List<WebElement> $$(String cssSelector){
        return $$(By.cssSelector(cssSelector));
    }

    // todo: refactor to return the proxy
    protected WebElement $get(List<WebElement> list, int index){
        return assertThat(listSizeIsAtLeast(list, index + 1)).get(index);
    }

    /*protected void open(String url){
        getWebDriver().get(url);
    }*/

    protected <V> V waitUntil(Function<? super WebDriver, V> condition, int timeout){
        return (new WebDriverWait(getWebDriver(), timeout)).until(condition);
    }

    protected <V> V assertThat(Function<? super WebDriver, V> condition){
        return waitUntil(condition, Configuration.timeout);
    }

    protected void refresh(){
        $("body").sendKeys(Keys.F5);
    }
}