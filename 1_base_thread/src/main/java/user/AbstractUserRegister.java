package user;

/**
 * @author zhul
 * @create 2022/11/9 14:31
 */
public abstract class AbstractUserRegister {

    void execute(UserRegisterContext context) {

        postProcessBeforeVerification(context);

        commonVerification(context);

        postProcessAfterVerification(context);

        userRegister(context);

        postProcessAfterRegister(context);

        registerFinishNotice(context);
    }

    //通用校验前 钩子方法
    abstract void postProcessBeforeVerification(UserRegisterContext context);

    //通用校验
    void commonVerification(UserRegisterContext context) {}

    //通用校验后钩子方法
    abstract void postProcessAfterVerification(UserRegisterContext context);

    //用户注册
    void userRegister(UserRegisterContext context) {}

    //用户注册后钩子方法
    abstract void postProcessAfterRegister(UserRegisterContext context);

    //注册完成后通知方法
    abstract void registerFinishNotice(UserRegisterContext context);
}
