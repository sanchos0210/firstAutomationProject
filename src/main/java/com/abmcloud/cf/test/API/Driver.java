package com.abmcloud.cf.test.API;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class Driver {

    private WebDriver driver;

    public Driver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void get(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public List<WebElement> $$(By locator){
        return (List<WebElement>) newElementsFinderProxyInstance(new ListOfWebElementsBait(), locator);
    }

    public List<WebElement> $$(String cssSelector){
        return $$(By.cssSelector(cssSelector));
    }

    public <V> V waitUntil(Function<? super WebDriver, V> condition, int timeout){
        return (new WebDriverWait(getWebDriver(), timeout)).until(condition);
    }

    public <V> V assertThat(Function<? super WebDriver, V> condition){
        return waitUntil(condition, Configuration.timeout);
    }

    public void refresh(){
        $("body").sendKeys(Keys.F5);
    }

    // todo: refactor to return the proxy
    protected WebElement $get(List<WebElement> list, int index){
        return assertThat(listSizeIsAtLeast(list, index + 1)).get(index);
    }

    //------------------------------------------------------------------------------------------------------------------
    class ProxiedWebElement implements WebElement {

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

    private Object newElementFinderProxyInstance(Object obj, By elementLocator){
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new ElementFinderProxy(elementLocator));
    }

    public WebElement $(By locator){
        return (WebElement) newElementFinderProxyInstance(new ProxiedWebElement(), locator);
    }

    public WebElement $(String cssSelector){
        return $(By.cssSelector(cssSelector));
    }

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
}
