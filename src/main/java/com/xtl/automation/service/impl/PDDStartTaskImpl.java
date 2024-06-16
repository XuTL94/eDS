package com.xtl.automation.service.impl;

import com.xtl.automation.entity.CommonResult;
import com.xtl.automation.entity.CustomTimer;
import com.xtl.automation.enums.AppElementEnum;
import com.xtl.automation.sdk.kimi.api.KimiApi;
import com.xtl.automation.service.QuestionAnswerService;
import com.xtl.automation.service.StartTask;
import com.xtl.automation.utils.AppiumUtils;
import com.xtl.automation.utils.ResultUtils;
import com.xtl.automation.utils.StringUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import static com.xtl.automation.config.InitBean.customTimer;

/**
 * 拼多多任务
 */
@Service
public class PDDStartTaskImpl extends StartTask {

    private final String KIMI_MODEL = "moonshot-v1-8k";
    private final String KIMI_SYSTEM = "你现在是电商客服助手，我的橘子，帮我重组下，尽量不要出现重复的，并且带表情，表情数量随机，不固定，回答的内容中，不要带有AI字样，因为你是人工客服,语句精短,不要过长。";

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Override
    public CommonResult<?> customerStartChartTask(String deviceID, String chatName) {

        AndroidDriver driver = null;
        CustomTimer timer = null;
        // 任务ID
        String taskId = null;
        // 当前问题
        AtomicReference<String> question = new AtomicReference<>();

        try {

            // 启动App
            driver = startApp(deviceID);

            // 拉起聊天
            WebElement chatElement = AppiumUtils.uiautomatorBytext(driver, "text", "聊天");
            chatElement.click();

            // 选择聊天对象
            String chatSelectStr = String.format("new UiSelector().%s(\"%s\")", "text", chatName);
            WebElement chatSelectElement = AppiumUtils.waitForElementVisibility(driver, AppiumBy.androidUIAutomator(chatSelectStr), 0.3);
            chatSelectElement.click();

            // 定位文本框
            WebElement editTextBox = AppiumUtils.waitForElementVisibility(driver, By.className(AppElementEnum.ANDROID_EDIT_TEXT.getCode()), 3);
            // 随机问题发送
            question.set(questionAnswerService.getQuestionAnswer("").getQuestion());
            question.set("");
            editTextBox.sendKeys(question.get());

            // 定位发送按钮
            String sendMagStr = String.format("new UiSelector().%s(\"%s\")", "text", "发送");
            WebElement sendMagBox = AppiumUtils.waitForElementVisibility(driver, AppiumBy.androidUIAutomator(sendMagStr), 3);
            sendMagBox.click();


            // 启动定时任务
            final AndroidDriver finalDriver = driver;
            timer = new CustomTimer();
            taskId = timer.getId();
            customTimer.add(timer);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 商家回复,则继续问
                    if (isReply(finalDriver)) {
                        question.set(questionAnswerService.getQuestionAnswer(question.get()).getQuestion());
                        editTextBox.sendKeys(question.get());
                        sendMagBox.click();
                    }
                }
            }, 0, 30000); // 每隔30秒执行一次任务

        } catch (Exception e) {
            if (timer != null) {
                timer.cancel();
            }
            if (driver != null) {
                driver.quit();
            }
            throw e;
        }
        return ResultUtils.success(taskId);
    }


    @Override
    public CommonResult<?> cancelChartTask(String taskId) {
        Iterator<CustomTimer> iterator = customTimer.iterator();
        while (iterator.hasNext()) {
            CustomTimer timer = iterator.next();
            if (taskId.equals(timer.getId())) {
                // 停止 timer
                timer.cancel();
                // 移除 timer
                iterator.remove();
            }
        }
        return ResultUtils.success();
    }


    @Override
    public CommonResult<?> businessStartChartTask(String deviceID, String chatName) {

        AndroidDriver driver = null;
        CustomTimer timer = null;
        // 任务ID
        String taskId = null;

        try {

            // 启动App
            driver = startBusinessApp(deviceID);

            // 等待拉起聊天
            String chatBoxStr = String.format("new UiSelector().%s(\"%s\")", "text", "聊天");
            WebElement chatBoxElement = AppiumUtils.waitForElementVisibility(driver, AppiumBy.androidUIAutomator(chatBoxStr), 3);
            chatBoxElement.click();

            // 选择聊天对象
            String chatSelectStr = String.format("new UiSelector().%s(\"%s\")", "text", chatName);
            WebElement chatSelectElement = AppiumUtils.waitForElementVisibility(driver, AppiumBy.androidUIAutomator(chatSelectStr), 0.3);
            chatSelectElement.click();

            // 定位文本框
            WebElement editTextBox = AppiumUtils.waitForElementVisibility(driver, By.className(AppElementEnum.ANDROID_EDIT_TEXT.getCode()), 3);
            editTextBox.sendKeys("在的");


            // 定位发送按钮
            String sendMagStr = String.format("new UiSelector().%s(\"%s\")", "text", "发送");
            WebElement sendMagBox = AppiumUtils.waitForElementVisibility(driver, AppiumBy.androidUIAutomator(sendMagStr), 3);
            editTextBox.clear();


            // 启动定时任务
            final AndroidDriver finalDriver = driver;
            timer = new CustomTimer();
            taskId = timer.getId();
            customTimer.add(timer);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 顾客是否已回复
                    String answer = queryAnswerReply(finalDriver);
                    if (StringUtils.isNotEmpty(answer)) {
                        // 答案AI转换，不同语句
                        String answerChange = KimiApi.kimiAiChat(KIMI_MODEL, KIMI_SYSTEM, answer);
                        editTextBox.sendKeys(answerChange);
                        sendMagBox.click();
                    }

                }
            }, 0, 10000); // 每隔3秒执行一次任务

        } catch (Exception e) {
            if (timer != null) {
                timer.cancel();
            }
            if (driver != null) {
                driver.quit();
            }
            throw e;
        }
        return ResultUtils.success(taskId);
    }

    // 判断商家是否已回复
    boolean isReply(AndroidDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id=\"com.xunmeng.pinduoduo:id/pdd\"]/android.widget.LinearLayout"));
        WebElement webElement = elements.get(elements.size() - 1);
        List<WebElement> imageViews = webElement.findElements(By.xpath(".//android.widget.ImageView[@content-desc=\"商家头像\"]"));
        // 如果最后一条消息商家头像存在，则商家已回复
        return !imageViews.isEmpty();
    }


    // 查询顾客回复的内容答案
    String queryAnswerReply(AndroidDriver driver) {
        String answer = "";
        List<WebElement> elements = driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.xunmeng.merchant:id/pdd\"]/android.widget.LinearLayout"));
        for (int i = elements.size() - 1; i >= 0; i--) {
            WebElement element = elements.get(i);
            List<WebElement> relativeLayouts = element.findElements(By.xpath(".//android.widget.RelativeLayout"));
            if (!relativeLayouts.isEmpty()) {
                WebElement relativeLayout = relativeLayouts.get(0); // 获取第一个 RelativeLayout
                List<WebElement> lastcontentWebElements = relativeLayout.findElements(By.xpath(".//android.widget.LinearLayout"));
                if(!lastcontentWebElements.isEmpty()){
                    WebElement lastcontentWebElement = lastcontentWebElements.get(0);
                    List<WebElement> textViews = lastcontentWebElement.findElements(By.xpath(".//android.widget.TextView"));
                    if (!textViews.isEmpty()) {
                        answer = questionAnswerService.getQuestionAnswer(textViews.get(0).getText()).getAnswer(); //获取第一个 TextView 的文本
                        break;
                    }
                }
            }
        }
        return answer;
    }

}
