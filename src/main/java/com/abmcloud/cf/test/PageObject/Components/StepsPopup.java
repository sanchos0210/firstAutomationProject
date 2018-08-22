package com.abmcloud.cf.test.PageObject.Components;

import com.abmcloud.cf.test.Driver.Driver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StepsPopup {

    private Driver driver;

    private List<WebElement> steps;
    private By parallelStepsInStep = By.cssSelector("ul li ul > li");


    public StepsPopup(Driver driver) {
        this.driver = driver;
        steps = driver.$$(By.cssSelector("step-view-approved .list-group > li"));
    }

    public String getStepName(int stepNum) {
        WebElement step = steps.get(stepNum - 1);
        return step.getText();
    }

    public String getParallelStepsNames(int stepNum) {
        List<WebElement> parallelSteps = driver.$$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") > ul > li"));
        List<String> stepsNames = new ArrayList<>();
        for(int i = 0; i < parallelSteps.size(); i++) {
            WebElement step = parallelSteps.get(i);
            stepsNames.add(i, step.findElement(By.cssSelector("div")).getText());
        }
        String result = StringUtils.join(stepsNames, ", ");
        return result;
    }

    public String[][] getChainSteps() {
        String[][] chainStepsList = new String[steps.size()][2];
        for(int i = 0; i < steps.size(); i++) {
            WebElement step = steps.get(i);
            List<WebElement> parallelStepsList = step.findElements(By.cssSelector("ul.parallel_steps li .list-group-item.steps_in_appl"));
            for(int j = 0; j < parallelStepsList.size(); j++) {
                WebElement nameOfStep = parallelStepsList.get(j).findElement(By.cssSelector("div"));
                if(nameOfStep.getText() != null) chainStepsList[i][j] = nameOfStep.getText();
            }
        }
        return chainStepsList;
    }

    public List<Object> getChainSteps2() {
        List<Object> chainStepsList = new ArrayList<>();
        List<WebElement> chainSteps = driver.$$(By.cssSelector("step-view-approved .list-group > li"));
        for(int i = 0; i < chainSteps.size(); i++) {
            List<Object> approversList = new ArrayList<>();
            WebElement step = chainSteps.get(i);
            List<WebElement> parallelStepsList = step.findElements(By.cssSelector("ul.parallel_steps li .list-group-item.steps_in_appl"));
            for(int j = 0; j < parallelStepsList.size(); j++) {
                WebElement nameOfStep = parallelStepsList.get(j).findElement(By.cssSelector("div"));
                if(nameOfStep.getText() != null) {
                    approversList.add(nameOfStep.getText());
                }
            }
            chainStepsList.add(approversList);
        }
        return chainStepsList;
    }

    public String getApprovers(int stepNum) {
        List<WebElement> approvers = driver.$$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") ul.parallel_steps li .list-group-item.steps_in_appl ul li"));
        List<String> approversName = new ArrayList<>();
        for(int i = 0; i < approvers.size(); i++) {
            approversName.add(i, approvers.get(i).findElement(By.cssSelector("img + span")).getText());
        }
        String result = StringUtils.join(approversName, ", ");
        return result;
    }
}
