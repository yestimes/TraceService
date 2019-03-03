package utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {

    private static String object2Json(Object bean){
        ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = null;
        try {
            mapJakcson = mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(mapJakcson + "SEP");
        return mapJakcson;

    }

    public static int returnJson(HttpServletResponse response, Object bean){
        response.setContentType("application/json;charset=utf-8");
        String mapJakcson = object2Json(bean);
        try {
            response.getWriter().print(mapJakcson);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }
}