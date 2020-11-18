package edu.conformity.util;

import java.util.HashMap;

public class RtnResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0),
        /**失败*/
        FAIL(1),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /** 状态类型 */
    private Type type;

    /** 状态码 */
    private int code;

    /** 返回内容 */
    private String msg;

    /** 数据对象 */
    private Object data;

    /**
     * 初始化一个新创建的 RtnResult 对象，使其表示一个空消息。
     */
    public RtnResult()
    {
    }

    /**
     * 初始化一个新创建的 RtnResult 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     */
    public RtnResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 RtnResult 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public RtnResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static RtnResult success()
    {
        return RtnResult.success("操作成功");
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static RtnResult success(String msg)
    {
        return RtnResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static RtnResult success(String msg, Object data)
    {
        return new RtnResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回失败消息
     *
     * @return 成功消息
     */
    public static RtnResult fail()
    {
        return RtnResult.fail("操作失败");
    }

    /**
     * 返回失败消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static RtnResult fail(String msg)
    {
        return RtnResult.fail(msg, null);
    }

    /**
     * 返回失败消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static RtnResult fail(String msg, Object data)
    {
        return new RtnResult(Type.FAIL, msg, data);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static RtnResult warn(String msg)
    {
        return RtnResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static RtnResult warn(String msg, Object data)
    {
        return new RtnResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static RtnResult error()
    {
        return RtnResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static RtnResult error(String msg)
    {
        return RtnResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static RtnResult error(String msg, Object data)
    {
        return new RtnResult(Type.ERROR, msg, data);
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
	
}
