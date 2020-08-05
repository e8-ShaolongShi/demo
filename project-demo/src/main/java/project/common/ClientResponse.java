package project.common;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClientResponse {

    private boolean success;
    private String message;
    private int code;
    private Map<String, Object> data = new LinkedHashMap<String, Object>();

    public ClientResponse() {
        this.code = 200;
    }

    public ClientResponse(boolean success, String message) {
        this.success = success;
        this.code = 200;
        this.message = message;
    }

    public static ClientResponse createOk(String message) {
        ClientResponse response = new ClientResponse();
        response.setOkMessage(message);
        return response;
    }

    public static ClientResponse createError(String message) {
        ClientResponse response = new ClientResponse();
        response.setErrorMessage(message);
        return response;
    }

    public void setOkMessage(String message) {
        this.code = 200;
        this.message = message;
        this.success = true;
    }

    public void addData(String key, Object value) {
        data.put(key, value);
    }

    public void addData(Map<String, Object> map) {
        data.putAll(map);
    }

    public void setErrorMessage(String message) {
        this.code = 200;
        this.message = message;
        this.success = false;
    }

    public static String getErrorStack(Exception ex) {
        if (ex != null) {
            StringWriter writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(writer, true));
            String result = writer.toString();
            if (result.length() > 1500) {
                return result.substring(0, 1100);
            }
            return result;
        }
        return "";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void writeTo(HttpServletResponse response) {
        try {
            String json = this.toJSONString();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String toJSONString() {
        return JSON.toJSON(this).toString();
    }
}
