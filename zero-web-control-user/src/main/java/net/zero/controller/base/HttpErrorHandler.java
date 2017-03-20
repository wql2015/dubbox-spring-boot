package net.zero.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 404处理(目前为合理结局，与全局异常冲突)
 * @author unique
 *
 */
@Controller
@EnableConfigurationProperties({ServerProperties.class})
public class HttpErrorHandler implements ErrorController {
	
    private final static String ERROR_PATH = "error";

    /**
     * Supports the HTML Error View
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorJsp(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return "404";
    }

    /**
     * Supports other formats like JSON, XML
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        return "404";
    }
    
    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}