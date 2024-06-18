package com.xtl.ebusiness.service.replyRateBooster;

import com.xtl.eSdk.kimi.api.KimiApi;
import com.xtl.ebusiness.service.QuestionAnswerService;
import com.xtl.ebusiness.utils.AppiumUtils;
import com.xtl.ecore.entity.CommonResult;
import com.xtl.ecore.entity.eDSTimer;
import com.xtl.ecore.exception.BusinessException;
import com.xtl.ecore.utils.ResultUtils;
import com.xtl.ecore.utils.SpringUtils;
import com.xtl.ecore.utils.StringUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import static com.xtl.ebusiness.config.InitBean.eDSTimerList;

/**
 * 自动刷高商家回复率
 */
public abstract class ReplyRateBooster {

    // 应用包名称
    protected String appPackage;
    // 应用包启动入口
    protected String appActivity;

    QuestionAnswerService questionAnswerService = SpringUtils.getBean(QuestionAnswerService.class);

    protected ReplyRateBooster(String appPackage, String appActivity) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
    }


    /**
     * 公共方法，组装并执行自动聊天任务
     *
     * @param type                  类型 0商家  1顾客
     * @param kimiSystem            如果type=0商家,则需要传这个
     * @param deviceID              设备ID
     * @param chatName              聊天对象名称
     * @return CommonResult 任务结果
     */
    protected CommonResult<?> startReplyRateTask(int type,String kimiSystem, String deviceID, String chatName) {

        if(type==0 && StringUtils.isEmpty(kimiSystem)){
            throw new BusinessException("AI角色定位未输入!");
        }


        AndroidDriver driver = null;
        eDSTimer timer = null;
        String taskId;
        AtomicReference<String> question = new AtomicReference<>();
        try {
            // 启动App
            driver = startApp(deviceID);

            // 拉起聊天
            WebElement chatIconElement = getChatIconBoxElement(driver);
            chatIconElement.click();

            // 选择聊天对象
            WebElement chatSelectElement = getChatSelectElement(driver, chatName);
            chatSelectElement.click();

            // 定位文本框
            WebElement editTextBox = getEditTextBox(driver);

            // 定位发送按钮
            WebElement sendButton = getSendButton(driver);

            // 启动定时任务
            final AndroidDriver finalDriver = driver;
            timer = new eDSTimer();
            taskId = timer.getId();
            eDSTimerList.add(timer);

            TimerTask task;
            switch (type) {
                case 0:
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            // 顾客是否已回复
                            String answer = isCustomerReply(finalDriver);
                            if (StringUtils.isNotEmpty(answer)) {
                                String answerChange = KimiApi.kimiAiChat(kimiSystem, answer);
                                editTextBox.sendKeys(answerChange);
                                sendButton.click();
                            }
                        }
                    };
                    timer.schedule(task, 0, 10000); // 每隔10秒执行一次任务
                    break;
                case 1:
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            // 商家回复,则继续问
                            if (isBusinessReply(finalDriver)) {
                                question.set(questionAnswerService.getQuestionAnswer(question.get()).getQuestion());
                                editTextBox.sendKeys(question.get());
                                sendButton.click();
                            }
                        }
                    };
                    timer.schedule(task, 0, 30000); // 每隔30秒执行一次任务
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type: " + type);
            }

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




    /**
     * 启动安卓应用(目前默认安卓配置)
     *
     * @param deviceID 设备ID
     * @return AndroidDriver 实例
     */
    public AndroidDriver startApp(String deviceID) {
        String automationName = "UiAutomator2";
        String platformName = "Android";
        return AppiumUtils.startApp(deviceID, automationName, platformName, appPackage, appActivity);
    }

    /**
     * 获取聊天图标按钮页面元素
     *
     * @param driver AndroidDriver 实例
     * @return WebElement
     */
    protected abstract WebElement getChatIconBoxElement(AndroidDriver driver);

    /**
     * 获取聊天对象元素
     *
     * @param driver AndroidDriver 实例
     * @param chatName 聊天对象名称
     * @return 聊天对象元素 WebElement
     */
    protected abstract WebElement getChatSelectElement(AndroidDriver driver, String chatName);

    /**
     * 获取文本框元素
     *
     * @param driver AndroidDriver 实例
     * @return 文本框元素 WebElement
     */
    protected abstract WebElement getEditTextBox(AndroidDriver driver);

    /**
     * 获取发送按钮元素
     *
     * @param driver AndroidDriver 实例
     * @return 发送按钮元素 WebElement
     */
    protected abstract WebElement getSendButton(AndroidDriver driver);

    /**
     * 判断商家是否已回复
     *
     * @param driver AndroidDriver 实例
     * @return boolean
     */
    protected abstract boolean isBusinessReply(AndroidDriver driver);

    /**
     * 判断客户是否已回复，并且获取问题对应的答案
     *
     * @param driver AndroidDriver 实例
     * @return String 客户回复的答案
     */
    protected abstract String isCustomerReply(AndroidDriver driver);

}
