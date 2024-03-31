package kasuga.lib.core.annos;

/**
 * This annotation means that the marked method is not necessary for your registration. Your registration could
 * run well without them. Only use them if you need their functions.
 * 这个注释意味着这个标记的方法(Method)对于你的注册并不重要
 * 你的注册进程将在不使用这些代码的时候运行正常
 * 仅在需要使用相关功能时使用这些功能
 */
public @interface Optional {}

